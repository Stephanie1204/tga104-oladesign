package com.tibame.tga104.g2.oladesign.utils;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnectionUtils {

	private static DataSource dataSource = null;

	private DBConnectionUtils() {
	}

	static {
		System.out.println("pass jdbc connect");
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
		config.setUsername("root");
		config.setPassword("password");
		dataSource = new HikariDataSource(config);
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

}
