package com.exercise.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.exercise.service.AdminProductService;
import com.exercise.service.AdminCategoryService;

import com.exercise.domain.Category;
import com.exercise.domain.Product;
import com.exercise.domain.vo.PageBean;
import com.exercise.domain.vo.Requirement;

public class AdminShowAllProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//获得表单数据
				request.setCharacterEncoding("utf-8");
				//**在显示商品前，先判断一下session中是否有搜索条件,若搜索条件，则将条件传给service去搜索显示出符合条件的商品
				HttpSession session = request.getSession();//**
				Requirement requirement = (Requirement) session.getAttribute("requirement");//**
				AdminProductService service=new AdminProductService();
				List<Product> products=null;
				try {
					products=service.searchProductService(requirement);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//**
				//搜索栏的分类信息需要显示
				AdminCategoryService service1=new AdminCategoryService();
				List<Category> categorys=null;
				try {
					categorys = service1.showCategoryService();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//后台的分页显示需要传给service层的参数:当前页和每页显示的信息数量。
				//还有信息总数需要service调用dao层数据库得到；
				//总页数需要service得到信息总数之后除以每页显示的信息数才能得到。
				//所以要将每页显示的信息数传给service。
				//需要当页显示的信息，需要将当前页和每页显示的信息数传过去，返回一个PageBean对象就可知道当页需要显示哪些东西
				String currentPage=request.getParameter("currentPage");
				int currentPageInt=1;
				if(currentPage!=null&&!"".equals(currentPage)){
					currentPageInt=Integer.parseInt(currentPage);
				}
				int productCount=10;
				PageBean<Product> adminFindProductsByPB=null;
				try {
					adminFindProductsByPB = service.findProductsByPageBean(requirement,currentPageInt,productCount);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("adminFindProductsByPB", adminFindProductsByPB);
				request.setAttribute("categorys", categorys);
				//将结果传递给前端页面
				request.setAttribute("products", products);
				//转发定向
				request.getRequestDispatcher("/admin/adminShowAllProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}