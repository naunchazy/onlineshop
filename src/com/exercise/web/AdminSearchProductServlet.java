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

import com.exercise.service.AdminCategoryService;
import com.exercise.service.AdminProductService;

import com.exercise.domain.Category;
import com.exercise.domain.Product;
import com.exercise.domain.vo.PageBean;
import com.exercise.domain.vo.Requirement;

public class AdminSearchProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//在搜索之前，使用工具类BeanUtils将从request中得到的搜索的条件放入reqirement中
		Requirement requirement=new Requirement();
		Map map=request.getParameterMap();
		try {
			BeanUtils.populate(requirement, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AdminProductService service=new AdminProductService();
		List<Product> products=null;
		try {
			products=service.searchProductService(requirement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 一开始在访问showAllProduct.jsp商品详情页面会有商品类别的显示，
		 * 	是因为在请求显示商品详情页先访问了ShowAllProductServlet。
		 	它将categorys和products都放进了reuqest域对象中
		 * 但在输入了搜索项之后的请求中没有携带商品分类信息【cid】和商品信息【is_hot】。
		 * 所以需要将products和categorys再放进本次的request中，以便显示
		*/	
		AdminCategoryService service2=new AdminCategoryService();
		List<Category> categorys=null;
		try {
			categorys = service2.showCategoryService();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//按条件查询得到的products信息需要分页显示，就需要用到分页的PageBean
		String currentPage=request.getParameter("currentPage");
		int currentPageInt=1;
		if(currentPage!=null&&!"".equals(currentPage)){
			currentPageInt=Integer.parseInt(currentPage);
		}
		int productCount=10;
		PageBean<Product> adminFindProductsByPB=null;
		try {
			adminFindProductsByPB=service.findProductsByPageBean(requirement,currentPageInt,productCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("adminFindProductsByPB", adminFindProductsByPB);
		HttpSession session = request.getSession();//**
		session.setAttribute("requirement", requirement);//**所以为了带着搜索条件的分页商品详情能在不点击搜索发出request情况下有效，将搜索条件放在session中更为合适
//		request.setAttribute("requirement", requirement);//**不将搜索条件放在request，因为request只在一次搜索中有效，而搜索结果分页若点击下一页，搜索条件便没有作用了
		request.setAttribute("categorys", categorys);
		request.setAttribute("products", products);
		request.getRequestDispatcher("/admin/adminShowAllProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}