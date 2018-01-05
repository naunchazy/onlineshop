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
		//currentPage当前页
		String currentPage=request.getParameter("currentPage");
		int currentPageInt=1;//默认第一次访问的时候，没有带需要显示的页面参数currentPage，默认显示第一页
		if(currentPage!=null && !"".equals(currentPage)){		
			currentPageInt=Integer.parseInt(currentPage);
		}
		int productCount=12;//每页显示的条数
		//数据总条数productTotalCount
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