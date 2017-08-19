package cn.ccenjie.shiro.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

public class DBUtils {
	
	private static DruidDataSource dds;
	
	static {
		dds = new DruidDataSource();
		dds.setDriverClassName("com.mysql.jdbc.Driver");
		dds.setUrl("jdbc:mysql://127.0.0.1:3306/shiro");
		dds.setUsername("root");
		dds.setPassword("123");
	}
	
	public static Connection getConnection() {
		try {
			return dds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
