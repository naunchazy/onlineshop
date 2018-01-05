package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exercise.service.AdminProductService;

public class AdminDeleteSomeProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] delBoxes = request.getParameterValues("delBox");
		AdminProductService service=new AdminProductService();
		try {
			service.DeleteSomeProductService(delBoxes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 删除之后应该重定向到用于显示全部商品信息的showAllProductServlet
		 * 他会在得到所有的商品信息后，再重定向到商品详情页面showAllProduct.jsp
		 * */		
		response.sendRedirect(request.getContextPath()+"/adminShowAllProductServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}