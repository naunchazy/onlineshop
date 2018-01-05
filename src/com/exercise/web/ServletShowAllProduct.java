package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exercise.domain.Product;
import com.exercise.domain.vo.PageBean;
import com.exercise.domain.vo.Requirement;
import com.exercise.service.AdminProductService;


public class ServletShowAllProduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//currentPage��ǰҳ
		String currentPage=request.getParameter("currentPage");
		int currentPageInt=1;//Ĭ�ϵ�һ�η��ʵ�ʱ��û�д���Ҫ��ʾ��ҳ�����currentPage��Ĭ����ʾ��һҳ
		if(currentPage!=null && !"".equals(currentPage)){		
			currentPageInt=Integer.parseInt(currentPage);
		}
		int productCount=12;//ÿҳ��ʾ������
		//����������productTotalCount
		AdminProductService service=new AdminProductService();
		PageBean<Product> pageBean=null;
		Requirement requirement=new Requirement();
		try {
			pageBean = service.findProductsByPageBean(requirement,currentPageInt,productCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/showAllProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}