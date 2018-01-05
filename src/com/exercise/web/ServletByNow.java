package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.Product;
import com.exercise.domain.User;
import com.exercise.service.OrderService;
import com.exercise.service.ProductService;

public class ServletByNow extends HttpServlet {
//������������档productDetail.jspת����תȥservletShowOrderչʾ��������
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
//		String[] pids = request.getParameterValues("pid");
		//�ж��û��Ƿ��¼����δ��¼������ת����¼ҳ�棬���е�¼������䶩����
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/loginTip.jsp");
		}else{
			request.setCharacterEncoding("utf-8");
			String oid=null;//Ϊÿ��������һ��������
			String pid = request.getParameter("pid");
			String countStr = request.getParameter("count");
			int count=Integer.parseInt(countStr);
			if(pid!=null && !"".equals(pid)){
				oid=UUID.randomUUID().toString();
				ProductService service=new ProductService();
				Product product=null;
				OrderService orderService=new OrderService();
				try {
					product = service.showProductDetail(pid);
					orderService.addOrderService(oid,user.getUid(),product,count);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String buyMethod="now";//����ʽ
				session.setAttribute("buyMethod", buyMethod);
				request.getRequestDispatcher("/servletShowOrder?oid="+oid+"&uid="+user.getUid()).forward(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}