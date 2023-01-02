package com.tibame.tga104.g2.oladesign.product_type.model;
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
public class Product_typeJDBCDAO implements Product_typeDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";
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
			"INSERT INTO PRODUCT_TYPE (TYPE_CODE,TYPE_NAME) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * from PRODUCT_TYPE";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM PRODUCT_TYPE where TYPE_CODE = ?";
	private static final String DELETE = 
			"DELETE FROM PRODUCT_TYPE where TYPE_CODE = ?";
	private static final String UPDATE = 
			"UPDATE PRODUCT_TYPE SET TYPE = ? where TYPE_CODE = ?";
	@Override
	public void insert(Product_typeVO product_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_typeVO.getTypeCode());
			pstmt.setString(2, product_typeVO.getTypeName());
		

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
	public void update(Product_typeVO product_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_typeVO.getTypeCode());
			pstmt.setString(2, product_typeVO.getTypeName());

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
	public void delete(String typeCode) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, typeCode);

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
	public Product_typeVO findByPrimaryKey(String typeCode) {
		Product_typeVO product_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, typeCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_typeVO = new Product_typeVO();
				product_typeVO.setTypeCode(rs.getString("TYPE_CODE"));
				product_typeVO.setTypeName(rs.getString("TYPE_NAME"));

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
		return product_typeVO;
	}

	@Override
	public List<Product_typeVO> getAll() {
		List<Product_typeVO> list = new ArrayList<Product_typeVO>();
		Product_typeVO product_typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				product_typeVO = new Product_typeVO();
				product_typeVO.setTypeCode(rs.getString("TYPE_CODE"));
				product_typeVO.setTypeName(rs.getString("TYPE_NAME"));
				list.add(product_typeVO); // Store the row in the list
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
