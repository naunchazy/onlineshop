package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.Order;
import com.exercise.domain.Product;
import com.exercise.service.OrderService;

public class ServletPay extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String oid = request.getParameter("oid");
		HttpSession session = request.getSession();
		String buyMethod = (String) session.getAttribute("buyMethod");//得到支付的方式
		//若时商品页立即购买，则不需从购物车删除记录，若是从购物车支付，则需删除购物车结算过的商品记录
		if(!"now".equals(buyMethod)){
			Map<Product, Integer> myCart = (Map<Product, Integer>) session.getAttribute("myCart");
			OrderService service=new OrderService();
			List<Order> cartOrder=null;
			try {
				cartOrder = service.showOrderService(oid,uid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Set<Map.Entry<Product, Integer>> set=myCart.entrySet();
			Iterator<Map.Entry<Product, Integer>> it=set.iterator();
			while(it.hasNext()){
				Map.Entry<Product, Integer> next = it.next();
				for (Order order : cartOrder) {
					if (next.getKey().getPid().equals(order.getPid())) {
						it.remove();
					}
				}
				
			}
			if(myCart==null||myCart.size()==0){
				session.removeAttribute("myCart");
			}
			int size=0;
			if(myCart!=null){
				for (Product p : myCart.keySet()) {
					size+=myCart.get(p);
				}
			}
			session.setAttribute("myCart", myCart);
		}
		response.sendRedirect(request.getContextPath()+"/paySuccess.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}