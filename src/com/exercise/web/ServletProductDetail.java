package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exercise.domain.Product;
import com.exercise.service.ProductService;

public class ServletProductDetail extends HttpServlet {
//若在主页showAllProduct.jsp中点击商品的图片，则跳转到该页面去数据库寻找商品详情
//再跳转到productDetail.jsp显示商品详情
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");//得到要显示的商品详情的pid
		ProductService service=new ProductService();
		Product productDetail=null;
		try {
			productDetail=service.showProductDetail(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("productDetail", productDetail);
		request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}