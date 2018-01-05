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

import com.exercise.service.AdminProductService;
import com.exercise.service.AdminCategoryService;

import com.exercise.domain.Category;
import com.exercise.domain.Product;
import com.exercise.domain.vo.PageBean;
import com.exercise.domain.vo.Requirement;

public class AdminShowAllProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//��ñ�����
				request.setCharacterEncoding("utf-8");
				//**����ʾ��Ʒǰ�����ж�һ��session���Ƿ�����������,����������������������serviceȥ������ʾ��������������Ʒ
				HttpSession session = request.getSession();//**
				Requirement requirement = (Requirement) session.getAttribute("requirement");//**
				AdminProductService service=new AdminProductService();
				List<Product> products=null;
				try {
					products=service.searchProductService(requirement);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//**
				//�������ķ�����Ϣ��Ҫ��ʾ
				AdminCategoryService service1=new AdminCategoryService();
				List<Category> categorys=null;
				try {
					categorys = service1.showCategoryService();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//��̨�ķ�ҳ��ʾ��Ҫ����service��Ĳ���:��ǰҳ��ÿҳ��ʾ����Ϣ������
				//������Ϣ������Ҫservice����dao�����ݿ�õ���
				//��ҳ����Ҫservice�õ���Ϣ����֮�����ÿҳ��ʾ����Ϣ�����ܵõ���
				//����Ҫ��ÿҳ��ʾ����Ϣ������service��
				//��Ҫ��ҳ��ʾ����Ϣ����Ҫ����ǰҳ��ÿҳ��ʾ����Ϣ������ȥ������һ��PageBean����Ϳ�֪����ҳ��Ҫ��ʾ��Щ����
				String currentPage=request.getParameter("currentPage");
				int currentPageInt=1;
				if(currentPage!=null&&!"".equals(currentPage)){
					currentPageInt=Integer.parseInt(currentPage);
				}
				int productCount=10;
				PageBean<Product> adminFindProductsByPB=null;
				try {
					adminFindProductsByPB = service.findProductsByPageBean(requirement,currentPageInt,productCount);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("adminFindProductsByPB", adminFindProductsByPB);
				request.setAttribute("categorys", categorys);
				//��������ݸ�ǰ��ҳ��
				request.setAttribute("products", products);
				//ת������
				request.getRequestDispatcher("/admin/adminShowAllProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}