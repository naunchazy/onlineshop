package com.exercise.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.Product;

public class ServletEditSCCount extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String countStr = request.getParameter("count");
		int count=Integer.parseInt(countStr);
		HttpSession session = request.getSession();
		Map<Product, Integer> myCart=null;
		String tips=null;
		if(count>=1){
			myCart = (Map<Product, Integer>) session.getAttribute("myCart");
			for (Product p : myCart.keySet()) {
				if (p.getPid().equals(pid)) {
					myCart.put(p, count);
				}
			}
			session.setAttribute("myCart", myCart);
			
			/*for (Product p : myCart.keySet()) {
				if (p.getPid().equals(pid)) {
					System.out.println(myCart.get(p));
				}
			}*/
			
		}else{
			count=1;
			tips="ÔÙ¼õ¾ÍÃ»À²~~~~~";
		}
		int size=0;
		if(myCart!=null&&myCart.size()!=0){
			for (Product p : myCart.keySet()) {
				size+=myCart.get(p);
			}
		}
		session.setAttribute("size", size);
		request.setAttribute("tips", tips);
		request.getRequestDispatcher("/showShoppingcart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}