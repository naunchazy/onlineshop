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
//������ҳshowAllProduct.jsp�е����Ʒ��ͼƬ������ת����ҳ��ȥ���ݿ�Ѱ����Ʒ����
//����ת��productDetail.jsp��ʾ��Ʒ����
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");//�õ�Ҫ��ʾ����Ʒ�����pid
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