//package com.exercise.service;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import com.exercise.dao.SCDao;
//import com.exercise.dao.UserDao;
//import com.exercise.domain.Shoppingcart;
//
//public class SCService {
//	//登录用户加入购物车时，判断是该加记录还是夹数量
//	public void addDBSCService(int uid, String pid) throws SQLException {
//		SCDao dao=new SCDao();
//		Shoppingcart scRecord=dao.searchDBSCDao(uid,pid);
//		if(scRecord!=null){
//			dao.updateDBSCDao(uid,pid,scRecord.getCount());
//		}else{
//			dao.addDBSCDao(uid,pid);
//		}
//	}
//	//登录用户查看购物车，要得到用户ID、商品、数量
//	public ShoppingcartRedord showDBSCService(int uid) throws SQLException {
//		
//		//遍历数据库该用户的所有商品和商品数量，已知用户ID
//		//得到商品
//		List<String> userPids
//		for (String string : userPids) {
//			
//		}
//		return null;
//	}
//}
