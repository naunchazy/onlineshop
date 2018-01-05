package com.exercise.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.exercise.service.AdminCategoryService;
import com.exercise.service.AdminProductService;

import com.exercise.domain.Category;
import com.exercise.domain.Product;
import com.exercise.domain.vo.PageBean;
import com.exercise.domain.vo.Requirement;

public class AdminSearchProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//������֮ǰ��ʹ�ù�����BeanUtils����request�еõ�����������������reqirement��
		Requirement requirement=new Requirement();
		Map map=request.getParameterMap();
		try {
			BeanUtils.populate(requirement, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AdminProductService service=new AdminProductService();
		List<Product> products=null;
		try {
			products=service.searchProductService(requirement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * һ��ʼ�ڷ���showAllProduct.jsp��Ʒ����ҳ�������Ʒ������ʾ��
		 * 	����Ϊ��������ʾ��Ʒ����ҳ�ȷ�����ShowAllProductServlet��
		 	����categorys��products���Ž���reuqest�������
		 * ����������������֮���������û��Я����Ʒ������Ϣ��cid������Ʒ��Ϣ��is_hot����
		 * ������Ҫ��products��categorys�ٷŽ����ε�request�У��Ա���ʾ
		*/	
		AdminCategoryService service2=new AdminCategoryService();
		List<Category> categorys=null;
		try {
			categorys = service2.showCategoryService();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��������ѯ�õ���products��Ϣ��Ҫ��ҳ��ʾ������Ҫ�õ���ҳ��PageBean
		String currentPage=request.getParameter("currentPage");
		int currentPageInt=1;
		if(currentPage!=null&&!"".equals(currentPage)){
			currentPageInt=Integer.parseInt(currentPage);
		}
		int productCount=10;
		PageBean<Product> adminFindProductsByPB=null;
		try {
			adminFindProductsByPB=service.findProductsByPageBean(requirement,currentPageInt,productCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("adminFindProductsByPB", adminFindProductsByPB);
		HttpSession session = request.getSession();//**
		session.setAttribute("requirement", requirement);//**����Ϊ�˴������������ķ�ҳ��Ʒ�������ڲ������������request�������Ч����������������session�и�Ϊ����
//		request.setAttribute("requirement", requirement);//**����������������request����Ϊrequestֻ��һ����������Ч�������������ҳ�������һҳ������������û��������
		request.setAttribute("categorys", categorys);
		request.setAttribute("products", products);
		request.getRequestDispatcher("/admin/adminShowAllProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}