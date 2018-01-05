//package com.exercise.dao;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//
//import com.exercise.domain.Shoppingcart;
//
//import utils.C3P0Utils;
//
//public class SCDao {
//
//	public Shoppingcart searchDBSCDao(int uid, String pid) throws SQLException {
//		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
//		String sql="select * from shoppingcart where uid=? and pid=?";
//		Shoppingcart scRecord=qr.query(sql, new BeanHandler<Shoppingcart>(Shoppingcart.class),uid,pid);
//		return scRecord;
//	}
//
//	public void updateDBSCDao(int uid, String pid, int i) throws SQLException {
//		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
//		String sql="update shoppingcart set uid=?,pid=?,count=?";
//		qr.update(sql,uid,pid,i+1);
//		
//	}
//
//	public void addDBSCDao(int uid, String pid) throws SQLException {
//		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
//		String sql="insert into shoppingcart values(?,?,?)";
//		qr.update(sql,uid,pid,1);
//		
//	}
//
////	public List<Shoppingcart> showDBSCDao(int uid) throws SQLException {
////		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
////		String sql="select * from shoppingcart where uid=?";
////		Shoppingcart query = qr.query(sql, new BeanHandler<Shoppingcart>(Shoppingcart.class),uid);
////		return SC;
////	}
//
//}
