package com.exercise.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.exercise.domain.Product;
import com.sun.org.apache.bcel.internal.generic.Select;

import utils.C3P0Utils;

public class ProductDao {

	public Product showProductDetail(String pid) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where pid=?";
		Product productDetail=qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		return productDetail;
	}

	/*
	 * public List<Product> showSCProducts(String[] pidSums) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from product where 1!=1";
		for (String pid : pidSums) {
			sql+=" or pid="+pid;
		}
		List<Product> sc_products=qr.query(sql, new BeanListHandler<Product>(Product.class));
		return sc_products;
	}*/

}
