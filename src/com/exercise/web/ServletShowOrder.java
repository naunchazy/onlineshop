package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exercise.domain.Order;
import com.exercise.service.OrderService;

public class ServletShowOrder extends HttpServlet {
//ServletByNowºÍServletSettlement×ªÀ´¡£
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oid = request.getParameter("oid");
		String uid = request.getParameter("uid");
		OrderService service=new OrderService();
		List<Order> cartOrder=null;
		try {
			cartOrder = service.showOrderService(oid,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int countSum=0;
		double sumPrice=0;
		for (Order order : cartOrder) {
			countSum+=order.getCount();
			sumPrice+=order.getShop_price()*order.getCount();
		}
		request.setAttribute("countSum", countSum);
		request.setAttribute("sumPrice", sumPrice);
		request.setAttribute("cartOrder", cartOrder);
		request.getRequestDispatcher("/settlement.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}