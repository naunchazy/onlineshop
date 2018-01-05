package com.exercise.service;

import java.sql.SQLException;
import java.util.List;

import com.exercise.dao.AdminCategoryDao;
import com.exercise.domain.Category;

public class AdminCategoryService {

	public List<Category> showCategoryService() throws SQLException {
		AdminCategoryDao dao=new AdminCategoryDao(); 
		List<Category> categorys=dao.showCategoryDao();
//		for (Category category : categorys) {
//			System.out.println(category.getCname());
//		}
		return categorys;
	}

}
