package com.exercise.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.exercise.domain.Product;
import com.exercise.service.AdminProductService;

public class AdminEditorProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String pid = request.getParameter("pid");
		AdminProductService service=new AdminProductService();
		try {
			service.editorProductService(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/adminShowAllProduct.jsp");*/
		request.setCharacterEncoding("utf-8");
		//1.获得表单数据
		Product product = new Product();
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(product, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		product.setPimage("products/1/c_0016.jpg");
		product.setPdate(new Date());//上架时间
		product.setPflag(1);//上架状态
//		System.out.println(product.getMarket_price()+":"+product.getShop_price());	
		AdminProductService service = new AdminProductService();
		try {
			service.editorProductService(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		response.sendRedirect(request.getContextPath()+"/adminShowAllProductServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}