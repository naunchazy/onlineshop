package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exercise.service.AdminCategoryService;
import com.exercise.service.AdminProductService;

import com.exercise.domain.Category;
import com.exercise.domain.Product;

public class AdminEditorShowProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		AdminProductService service=new AdminProductService();
		Product editProduct=null;
		try {
			editProduct = service.editorShowProductService(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AdminCategoryService service1=new AdminCategoryService();
		List<Category> editCatagory=null;
		try {
			editCatagory = service1.showCategoryService();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("editProduct", editProduct);
		request.setAttribute("editCatagory", editCatagory);
		request.getRequestDispatcher("/admin/adminEditorProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}