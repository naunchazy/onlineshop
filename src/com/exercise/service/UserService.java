package com.exercise.service;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.exercise.dao.AdminProductDao;
import com.exercise.dao.UserDao;
import com.exercise.domain.User;

import utils.C3P0Utils;

public class UserService {

	public void addUserService(User user, String JSESSIONID) throws SQLException {
		UserDao dao=new UserDao();
		dao.addProductDao(user,JSESSIONID);
	}

	public User findUserService(String username, String password) throws SQLException {
		UserDao dao=new UserDao();
		User user=dao.addProductDao(username,password);
		return user;
	}
	
	//×¢²áÖØÃû
	public User checkDupUsernameService(String username) throws SQLException {
		UserDao dao = new UserDao();
		return dao.checkDupUsernameDao(username);
	}

	
}
