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
			//若勾选了自动登录按钮，则将该用户本地持久化
			if(autoLogin!=null&&!"".equals(autoLogin)){
				Cookie cookie_username = new Cookie("username",user.getUsername());
				Cookie cookie_password = new Cookie("password",user.getPassword());
				cookie_username.setMaxAge(60*60*24*7);
				cookie_password.setMaxAge(60*60*24*7);
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
			}
			//将登录成功的user存入session域中，整个会话中，都能得到当前登录的用户的信息
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{
			request.setAttribute("message", "用户名或密码错误");
			//登录失败
			//使用重定向，能实现错误信息的回显
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}