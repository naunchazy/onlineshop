package com.exercise.service;

import java.sql.SQLException;
import java.util.List;

import com.exercise.dao.OrderDao;
import com.exercise.domain.Order;
import com.exercise.domain.Product;

public class OrderService {

	public void addOrderService(String oid, String uid, Product p,int count) throws SQLException {
		OrderDao dao=new OrderDao();
		dao.addOrderdao(oid,uid,p,count);
		
	}

	public List<Order> showOrderService(String oid, String uid) throws SQLException {
		OrderDao dao=new OrderDao();
		List<Order> cartOrder=dao.showOrderDao(oid,uid);
		return cartOrder;
	}

}
