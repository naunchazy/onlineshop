package com.exercise.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.exercise.domain.Category;
import utils.C3P0Utils;

public class AdminCategoryDao {

	public List<Category> showCategoryDao() throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from category";
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	}

}
