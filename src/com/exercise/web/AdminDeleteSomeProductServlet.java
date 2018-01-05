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
		 * ɾ��֮��Ӧ���ض���������ʾȫ����Ʒ��Ϣ��showAllProductServlet
		 * �����ڵõ����е���Ʒ��Ϣ�����ض�����Ʒ����ҳ��showAllProduct.jsp
		 * */		
		response.sendRedirect(request.getContextPath()+"/adminShowAllProductServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}