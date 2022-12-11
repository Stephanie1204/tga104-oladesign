package com.tibame.tga104.g2.oladesign.intermail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IntermailJDBCDAO implements IntermailDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Aa82822232";
	
	private static final String INSERT_STMT = 
			"INSERT INTO INTERMAIL (INTERMAIL_ID,MEM_ID,ADMIN_ID,NUM_QUE,CONTENT) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT * from  INTERMAIL";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM INTERMAIL where INTERMAIL_ID = ?";
	private static final String DELETE = 
			"DELETE FROM INTERMAIL where INTERMAIL_ID = ?";
	private static final String UPDATE = 
			"UPDATE INTERMAIL SET TYPE = ? where INTERMAIL_ID = ?";
	@Override
	public void insert(IntermailVO intermailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, intermailVO.getInterMailId());
			pstmt.setInt(2, intermailVO.getMemId());
			pstmt.setString(3, intermailVO.getAdminId());
			pstmt.setString(4, intermailVO.getNumQue());
			pstmt.setString(5, intermailVO.getConTent());
//			pstmt.setBoolean(6, intermailVO.getIsSend());
		

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		}

		
		
	}
	@Override
	public void update(IntermailVO intermailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, intermailVO.getInterMailId());
			pstmt.setInt(2, intermailVO.getMemId());
			pstmt.setString(3, intermailVO.getAdminId());
			pstmt.setString(4, intermailVO.getNumQue());
			pstmt.setString(5, intermailVO.getConTent());
//			pstmt.setBoolean(6, intermailVO.getIsSend());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		}
		
		
	}
	@Override
	public void delete(String interMailId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, interMailId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		}
		
	}
	@Override
	public IntermailVO findByPrimaryKey(String interMailId) {
		IntermailVO intermailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, interMailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getString("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
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
		}
		return intermailVO;
	}
	@Override
	public List<IntermailVO> getAll() {
		List<IntermailVO> list = new ArrayList<IntermailVO>();
		IntermailVO intermailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getString("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));
				list.add(intermailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
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
		}
		return list;
	}
}
