package com.exercise.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.exercise.domain.User;

import utils.C3P0Utils;

public class UserDao {

	public void addProductDao(User user, String JSESSIONID) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into user(username,password,realname,email,telephone,JSESSIONID) values(?,?,?,?,?,?)";
//		qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getRealname(),user.getEmail(),user.getTelephone());
		System.out.println(user.getUsername()+user.getPassword()+user.getRealname()+user.getEmail()+user.getTelephone()+"JSESSIONID===="+JSESSIONID);
		qr.update(sql,user.getUsername(),user.getPassword(),user.getRealname(),user.getEmail(),user.getTelephone(),JSESSIONID);
	}

	public User addProductDao(String username, String password) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from user where username=? and password=?";
		User user=qr.query(sql, new BeanHandler<User>(User.class),username,password);
		return user;
	}
	//×¢²áÖØÃû
	public User checkDupUsernameDao(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where username=?";
		return qr.query(sql, new BeanHandler<User>(User.class),username);
	}

}
