package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.Product;
import com.exercise.domain.User;
import com.exercise.service.ProductService;

public class ServletShowShoppingcart extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String message = null;
		Map<Product, Integer> myCart = (Map<Product, Integer>) session.getAttribute("myCart");
		/*if(myCart==null||"".equals(myCart)){*/
		if(myCart==null||myCart.size()==0){
			message = "您还未添加任何商品！";
		}
		int size=0;
		if(myCart!=null){
			for (Product p : myCart.keySet()) {
				size+=myCart.get(p);
			}
		}
		session.setAttribute("size", size);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/showShoppingcart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}