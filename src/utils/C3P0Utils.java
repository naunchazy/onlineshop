package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	private static ComboPooledDataSource pool = new ComboPooledDataSource();
	//获得一个Connection对象
	public static Connection getConnection() throws SQLException{
		return pool.getConnection();
	}
	//获得一个连接池对象
	public static DataSource getDataSource(){
		return pool;
	}
}
