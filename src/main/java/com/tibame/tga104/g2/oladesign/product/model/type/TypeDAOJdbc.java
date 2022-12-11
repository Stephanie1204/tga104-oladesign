package com.tibame.tga104.g2.oladesign.product.model.type;

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

public class TypeDAOJdbc implements TypeDAO{

	private DataSource dataSource;
	public TypeDAOJdbc() {
		System.out.println("pass jdbc connect");
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_TYPE = "SELECT TYPE_CODE, TYPE_NAME FROM PRODUCT_TYPE";

	@Override
	public List<TypeBean>  getAll() {
		List<TypeBean> result = null;
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL_TYPE);
				ResultSet rset = stmt.executeQuery();) {
				
				result = new ArrayList<TypeBean>();
				
				while(rset.next()) {
					TypeBean bean = new TypeBean();
					bean.setTypeCode(rset.getString("TYPE_CODE"));
					bean.setTypeName(rset.getString("TYPE_NAME"));
	
					result.add(bean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
	}
}
