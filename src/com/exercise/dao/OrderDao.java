package com.exercise.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.exercise.domain.Order;
import com.exercise.domain.Product;

import utils.C3P0Utils;

public class OrderDao {

	public void addOrderdao(String oid, String uid, Product p, int count) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into cartorder values(?,?,?,?,?,?,?)";
		qr.update(sql,oid,uid,p.getPid(),p.getPimage(),p.getPname(),p.getShop_price(),count);
		
	}

	public List<Order> showOrderDao(String oid, String uid) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from cartorder where oid=? and uid=?";
		return qr.query(sql, new BeanListHandler<Order>(Order.class),oid,uid);
	}

}
