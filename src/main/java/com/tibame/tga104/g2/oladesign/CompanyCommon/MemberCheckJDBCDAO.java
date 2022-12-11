package com.tibame.tga104.g2.oladesign.CompanyCommon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberCheckJDBCDAO implements MemberCheckDAO_interface {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String DO_CHECK_MEMBER_HAS_COM = "SELECT IS_COM FROM TGA104G2.MEMBER WHERE MEM_ID = ?;";

	@Override
	public Boolean doCheckMemberHasCom(String memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(DO_CHECK_MEMBER_HAS_COM);

			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			rs.next();

			result = rs.getBoolean("IS_COM");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}