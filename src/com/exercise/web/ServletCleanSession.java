package com.exercise.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.User;

public class ServletCleanSession extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		System.out.println("-"+session.getId());	
		if(session.getAttribute("user")!=null){
			session.removeAttribute("user");
		}
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if("username".equals(cookie.getName())){
					Cookie cookie2=new Cookie("username", "");
					cookie2.setMaxAge(0);
					response.addCookie(cookie2);
				}
				if("password".equals(cookie.getName())){
					Cookie cookie2=new Cookie("password", "");
					cookie2.setMaxAge(0);
					response.addCookie(cookie2);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/cleanSessionSuccess.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}