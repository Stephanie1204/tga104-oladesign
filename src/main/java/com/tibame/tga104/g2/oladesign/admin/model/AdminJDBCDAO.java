package com.tibame.tga104.g2.oladesign.admin.model;

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

public class AdminJDBCDAO implements AdminDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "Aa82822232";
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
	
	private static final String INSERT_STMT = 
			"INSERT INTO ADMIN (ADMIN_ID,ADMIN_NAME,ACCOUNT,PASSWORD,CREATDATE) VALUES (?, ?, ?, ?, NOW())";
	private static final String GET_ALL_STMT = 
			"SELECT * from ADMIN";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM ADMIN where ADMIN_ID = ?";
	private static final String DELETE = 
			"DELETE FROM ADMIN where ADMIN_ID = ?";
	private static final String UPDATE = 
			"UPDATE ADMIN SET ACCOUNT = ? , PASSWORD = ? , ADMIN_NAME =? where ADMIN_ID = ?";
	

	
	@Override
	public void insert(AdminVO adminVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSource.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminVO.getAdminId());
			pstmt.setString(2, adminVO.getAdminName());
			pstmt.setString(3, adminVO.getAccount());
			pstmt.setString(4, adminVO.getPassword());

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
	public void update(AdminVO adminVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, adminVO.getAccount());
			pstmt.setString(2, adminVO.getPassword());
			pstmt.setString(3, adminVO.getAdminName());
			pstmt.setString(4, adminVO.getAdminId());
			

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
	public void delete(String adminId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adminId);

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
	public AdminVO findByPrimaryKey(String adminId) {

		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adminId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				adminVO = new AdminVO();
				adminVO.setAdminId(rs.getString("ADMIN_ID"));
				adminVO.setAdminName(rs.getString("ADMIN_NAME"));
				adminVO.setAccount(rs.getString("ACCOUNT"));
				adminVO.setPassword(rs.getString("PASSWORD"));

			}

			// Handle any driver errors

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
		return adminVO;
	}

	@Override
	public List<AdminVO> getAll() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				adminVO = new AdminVO();
				adminVO.setAdminId(rs.getString("ADMIN_ID"));
				adminVO.setAdminName(rs.getString("ADMIN_NAME"));
				adminVO.setAccount(rs.getString("ACCOUNT"));
				adminVO.setPassword(rs.getString("PASSWORD"));
				list.add(adminVO); // Store the row in the list
			}

			// Handle any driver errors

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
	
	
	private static final String LoginSQL = "SELECT ADMIN_ID,ADMIN_NAME,ACCOUNT,PASSWORD "
			+ "from ADMIN where ACCOUNT = ? and PASSWORD = ? ;";
	@Override
	public AdminVO login(String account, String password) {
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(LoginSQL);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				adminVO = new AdminVO();
				adminVO.setAdminId(rs.getString("ADMIN_ID"));
				adminVO.setAdminName(rs.getString("ADMIN_NAME"));
				adminVO.setAccount(rs.getString("ACCOUNT"));
				adminVO.setPassword(rs.getString("PASSWORD"));
				
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
		return adminVO;
	}
	
//	public static void main(String[] args) {
//
//		AdminJDBCDAO dao = new AdminJDBCDAO();
//		AdminVO adminVO1 = new AdminVO();
//		adminVO1.setAccount("admin001@tibame.com.tw");
//		adminVO1.setPassword("121212");
//		dao.login(adminVO1);
		
//
//		
//		AdminVO adminVO1 = new AdminVO();
//		adminVO1.setAdminId("�޲z��2");
//		adminVO1.setAdminName("MANAGER");
//		adminVO1.setAccount("�b��");
//		adminVO1.setPassword("�K�X");
//		dao.insert(adminVO1);
//
//		
//		AdminVO adminVO2 = new AdminVO();
//		adminVO1.setAdminId("A003");
//		adminVO1.setAdminName("�d�ç�2");
//		adminVO1.setAccount("MANAGER2");
//		adminVO1.setPassword("�K�X1111");
//		dao.update(adminVO2);
//
//		
//		dao.delete("A004");
//
//		
//		AdminVO adminVO3 = dao.findByPrimaryKey("A001");
//		System.out.print(adminVO3.getAdminId() + ",");
//		System.out.print(adminVO3.getAdminName() + ",");
//		System.out.print(adminVO3.getAccount() + ",");
//		System.out.print(adminVO3.getPassword() + ",");
//		System.out.println("---------------------");
//
//		
//		List<AdminVO> list = dao.getAll();
//		for (AdminVO adin : list) {
//			System.out.print(adin.getAdminId() + ",");
//			System.out.print(adin.getAdminName() + ",");
//			System.out.print(adin.getAccount() + ",");
//			System.out.print(adin.getPassword() + ",");
//			System.out.println();
//		}
//	}


//	@Override
//	public AdminVO getAdmin(String account, String password) {
//		AdminVO adminVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = dataSource.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				
//				adminVO = new AdminVO();
//				adminVO.setAccount(rs.getString("ACCOUNT"));
//				adminVO.setPassword(rs.getString("PASSWORD"));
//				
////				list.add(adminVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return adminVO;
//	}



}
