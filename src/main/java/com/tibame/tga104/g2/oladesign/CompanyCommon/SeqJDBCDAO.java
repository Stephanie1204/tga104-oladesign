package com.tibame.tga104.g2.oladesign.CompanyCommon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeqJDBCDAO implements SeqDAO_interface {
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
    String USERID = "root";
    String PASSWORD = "password";

    private static final String GET_ONE_SEQ = "SELECT TGA104G2.FN_GET_SEQ_BY_TYPE(?) AS SEQ FROM DUAL;";

    @Override
    public String getOneSeq(String typeNm) {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String result = "";
	try {
	    Class.forName(DRIVER);
	    con = DriverManager.getConnection(URL, USERID, PASSWORD);
	    pstmt = con.prepareStatement(GET_ONE_SEQ);

	    pstmt.setString(1, typeNm);

	    rs = pstmt.executeQuery();

	    rs.next();
	    System.out.println(rs.getString("SEQ"));
	    result = rs.getString("SEQ");
	} catch (ClassNotFoundException ce) {
	    ce.printStackTrace();
	} catch (SQLException se) {
	    se.printStackTrace();
	}
	if (pstmt != null) {
	    try {
		pstmt.close();
	    } catch (SQLException se) {
		se.printStackTrace(System.err);
	    }
	}
	if (con != null) {
	    try {
		con.close();
	    } catch (Exception e) {
		e.printStackTrace(System.err);
	    }
	}

	return result;
    }
}