package com.tibame.tga104.g2.oladesign.intermail.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class Intermail_qnJDBCDAO implements Intermail_qnDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";
	private static DataSource dataSource = null;
	static {
//		System.out.println("pass jdbc connect");
//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
//		config.setUsername("root");
//		config.setPassword("password");
//		dataSource = new HikariDataSource(config);

		dataSource = com.tibame.tga104.g2.oladesign.utils.DBConnectionUtils.getDataSource();
	}

	
	private static final String INSERT_STMT = 
			"INSERT INTO INTERMAIL_QN (NUM_QUE,TYPE) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * from INTERMAIL_QN";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM INTERMAIL_QN where NUM_QUE = ?";
	private static final String DELETE = 
			"DELETE FROM INTERMAIL_QN where NUM_QUE = ?";
	private static final String UPDATE = 
			"UPDATE INTERMAIL_QN SET TYPE = ? where NUM_QUE = ?";
	@Override
	public void insert(Intermail_qnVO intermail_qnVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, intermail_qnVO.getNumQue());
			pstmt.setString(2, intermail_qnVO.getType());
		

			pstmt.executeUpdate();


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
	public void update(Intermail_qnVO intermail_qnVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, intermail_qnVO.getNumQue());
			pstmt.setString(2, intermail_qnVO.getType());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String numQue) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, numQue);

			pstmt.executeUpdate();

			// Handle any driver errors

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
	public Intermail_qnVO findByPrimaryKey(String numQue) {
		
		Intermail_qnVO intermail_qnVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, numQue);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				intermail_qnVO = new Intermail_qnVO();
				intermail_qnVO.setNumQue(rs.getString("NUM_QUE"));
				intermail_qnVO.setType(rs.getString("TYPE"));

			}


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
		return intermail_qnVO;
	}

	@Override
	public List<Intermail_qnVO> getAll() {
		List<Intermail_qnVO> list = new ArrayList<Intermail_qnVO>();
		Intermail_qnVO intermail_qnVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				intermail_qnVO = new Intermail_qnVO();
				intermail_qnVO.setNumQue(rs.getString("NUM_QUE"));
				intermail_qnVO.setType(rs.getString("TYPE"));
				list.add(intermail_qnVO); // Store the row in the list
			}


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
	private static final String GET_TYPE = 
			"SELECT TYPE FROM INTERMAIL_QN WHERE NUM_QUE = ? ";
	@Override
	public List<Intermail_qnVO> getType() {
		List<Intermail_qnVO> list = new ArrayList<Intermail_qnVO>();
		Intermail_qnVO intermail_qnVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_TYPE);
			pstmt.setString(1, intermail_qnVO.getNumQue());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				intermail_qnVO = new Intermail_qnVO();
				intermail_qnVO.setNumQue(rs.getString("NUM_QUE"));
				intermail_qnVO.setType(rs.getString("TYPE"));
				list.add(intermail_qnVO); // Store the row in the list
			}


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
