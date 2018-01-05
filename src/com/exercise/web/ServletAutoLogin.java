package com.exercise.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.User;
import com.exercise.service.UserService;

public class ServletAutoLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("autoLogin");
		
		UserService service=new UserService();
		User user=null;
		try {
			user=service.findUserService(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user!=null){
			//����ѡ���Զ���¼��ť���򽫸��û����س־û�
			if(autoLogin!=null&&!"".equals(autoLogin)){
				Cookie cookie_username = new Cookie("username",user.getUsername());
				Cookie cookie_password = new Cookie("password",user.getPassword());
				cookie_username.setMaxAge(60*60*24*7);
				cookie_password.setMaxAge(60*60*24*7);
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
			}
			//����¼�ɹ���user����session���У������Ự�У����ܵõ���ǰ��¼���û�����Ϣ
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{
			request.setAttribute("message", "�û������������");
			//��¼ʧ��
			//ʹ���ض�����ʵ�ִ�����Ϣ�Ļ���
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}