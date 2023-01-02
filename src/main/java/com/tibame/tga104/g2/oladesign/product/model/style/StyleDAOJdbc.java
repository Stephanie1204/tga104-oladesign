package com.tibame.tga104.g2.oladesign.product.model.style;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class StyleDAOJdbc implements StyleDAO {

	private static DataSource dataSource = null;
	static {
		// System.out.println("pass jdbc connect");
		// HikariConfig config = new HikariConfig();
		// config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
		// config.setUsername("root");
		// config.setPassword("password");
		// dataSource = new HikariDataSource(config);

		dataSource = com.tibame.tga104.g2.oladesign.utils.DBConnectionUtils.getDataSource();
	}

	private static final String GET_ALL_TYPE = "SELECT STYLE_CODE, STYLE_NAME FROM PRODUCT_STYLE";

	@Override
	public List<StyleBean> getAll() {
		List<StyleBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
//		System.out.println("jdbc style");
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ALL_TYPE);
			ResultSet rset = stmt.executeQuery();
			result = new ArrayList<StyleBean>();

			while (rset.next()) {
				StyleBean bean = new StyleBean();
				bean.setStyleCode(rset.getString("STYLE_CODE"));
				bean.setStyleName(rset.getString("STYLE_NAME"));

				result.add(bean);
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
