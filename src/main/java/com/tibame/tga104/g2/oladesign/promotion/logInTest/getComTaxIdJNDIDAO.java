package com.tibame.tga104.g2.oladesign.promotion.logInTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class getComTaxIdJNDIDAO {
	private static DataSource ds = null;
	static {
//		try {
			ds = com.tibame.tga104.g2.oladesign.utils.DBConnectionUtils.getDataSource();
//			 Context ctx = new InitialContext();
//			 ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA104G2"); // 放在context.xml
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	}
	
	private static final String GETCOMTAXID="SELECT * FROM COMPANY_MEM where MEM_ID=?";
	
	public ToGetComTaxIdVO getComTaxId(Integer memId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ToGetComTaxIdVO toGetComTaxIdVO=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETCOMTAXID);

			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			toGetComTaxIdVO = new ToGetComTaxIdVO();
			toGetComTaxIdVO.setMemId(rs.getInt("MEM_ID")); 
			toGetComTaxIdVO.setComTaxId(rs.getString("COM_TAXID")); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs!= null) {
				try {
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return toGetComTaxIdVO;
	}
}
