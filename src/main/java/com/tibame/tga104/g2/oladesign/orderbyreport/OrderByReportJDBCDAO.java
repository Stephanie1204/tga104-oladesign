package com.tibame.tga104.g2.oladesign.orderbyreport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderByReportJDBCDAO implements OrderByReportDAO {

	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String GET_COM_REPORT = "SELECT COM_TAXID, DATE_FORMAT(ORDER_TIME, '%m') MONT, COUNT(*) DAY_NUM,SUM(AMOUNT) DAY_PRICE FROM tga104g2.orders WHERE COM_TAXID = ? AND DATE_FORMAT(ORDER_TIME, '%Y') = ? GROUP BY DATE_FORMAT(ORDER_TIME, '%Y-%m') ORDER BY MONT ASC";

	@Override
	public List<OrderByReportVO> getsalesreport(String comTaxId, String reportYears) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<OrderByReportVO> list = new ArrayList<OrderByReportVO>();
		OrderByReportVO orderByReportVO;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_COM_REPORT);

			pstmt.setString(1, comTaxId);
			pstmt.setString(2, reportYears);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				orderByReportVO = new OrderByReportVO();
				orderByReportVO.setDayNum(rs.getString("DAY_NUM"));
				orderByReportVO.setDayPrice(rs.getString("DAY_PRICE"));
				orderByReportVO.setComTaxId(rs.getString("COM_TAXID"));
				orderByReportVO.setMonth(rs.getString("MONT"));

				list.add(orderByReportVO);
			}
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

		return list;
	}
}