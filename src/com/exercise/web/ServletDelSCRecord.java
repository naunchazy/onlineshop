package com.exercise.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.Product;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;


public class ServletDelSCRecord extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		Map<Product, Integer> myCart = (Map<Product, Integer>) session.getAttribute("myCart");
		/*for(Product p : myCart.keySet()){
			if(p.getPid().equals(pid)){
				myCart.remove(p);
			}
		}*/
		Set<Entry<Product, Integer>> s = myCart.entrySet();
		Iterator<Entry<Product, Integer>> it = s.iterator();
		while(it.hasNext()) {
			Map.Entry<Product, Integer> entry = it.next();
			Product p = entry.getKey();
			if(p.getPid().equals(pid)){
				it.remove();
			}
		}

		/*if(myCart.size()==0){
			message="您还未添加任何商品！";
		}*/
		session.setAttribute("myCart", myCart);
		if(myCart==null||myCart.size()==0){
			session.removeAttribute("myCart");
		}
		//购物车的商品件数
		int size=0;
		if(myCart!=null&&myCart.size()!=0){
			for (Product p : myCart.keySet()) {
				size+=myCart.get(p);
			}
		}
		session.setAttribute("size", size);
		request.getRequestDispatcher("/servletShowShoppingcart").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}