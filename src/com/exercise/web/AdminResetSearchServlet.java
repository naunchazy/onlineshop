package com.exercise.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exercise.domain.Product;
import com.exercise.domain.vo.Requirement;
import com.exercise.service.AdminProductService;

public class AdminResetSearchServlet extends HttpServlet {
//Çå¿ÕËÑË÷¿ò
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Requirement requirement = (Requirement) session.getAttribute("requirement");
		requirement.setPname("");
		requirement.setIs_hot("");
		requirement.setCid("");
		if(requirement!=null&&!"".equals(requirement)){
			session.setAttribute("requirement", "");
//			session.invalidate();
		}
		session.setAttribute("requirement", requirement);
		request.getRequestDispatcher("/adminShowAllProductServlet").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}