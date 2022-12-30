package com.tibame.tga104.g2.oladesign.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberIsComJDBCDAO implements MemberIsComDAO {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String UPDATE_BY_ADMIN = "update member set is_com = true, is_regcom = false where mem_id = ? and (is_com = false or is_regcom = true);";

	@Override
	public void updateIsCom(Integer memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_BY_ADMIN);

			pstmt.setInt(1, memId);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
			}
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
