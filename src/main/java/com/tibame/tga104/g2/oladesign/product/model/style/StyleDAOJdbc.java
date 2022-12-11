package com.tibame.tga104.g2.oladesign.product.model.style;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Style.model.StyleBean;
import Style.model.StyleDAO;

public class StyleDAOJdbc implements StyleDAO{

	private DataSource dataSource;
	public StyleDAOJdbc() {
		System.out.println("pass jdbc connect");
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_TYPE = "SELECT STYLE_CODE, STYLE_NAME FROM PRODUCT_STYLE";

	@Override
	public List<StyleBean>  getAll() {
		List<StyleBean> result = null;
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL_TYPE);
				ResultSet rset = stmt.executeQuery();) {
				
				result = new ArrayList<StyleBean>();
				
				while(rset.next()) {
					StyleBean bean = new StyleBean();
					bean.setStyleCode(rset.getString("STYLE_CODE"));
					bean.setStyleName(rset.getString("STYLE_NAME"));
	
					result.add(bean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
	}
}
