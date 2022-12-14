package com.tibame.tga104.g2.oladesign.promotion.model.promoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class PromoTypeJNDIDAO implements PromoTypeDAOInterface {

	@Autowired
	private DataSource ds = null;
	
//	private static DataSource ds = null;
//	static {
//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
//		config.setUsername("root");
//		config.setPassword("password");
//
//		ds = new HikariDataSource(config);
//		
////		try {
////			Context ctx = new InitialContext();
////			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA104G2"); // 放在context.xml
////		} catch (NamingException e) {
////			e.printStackTrace();
////		}
//	}

	private static final String INSERT_STMT = "insert into PROMOTION_TYPE(CODE,NAME) values(?,?)";
	private static final String GET_ALL_STMT = "select * from PROMOTION_TYPE";
	private static final String GET_ONE_STMT = "select * from PROMOTION_TYPE where code = ?";
	private static final String DELETE = "delete from PROMOTION_TYPE where CODE = ?";
	private static final String UPDATE = "update PROMOTION_TYPE set NAME=? where CODE=? ";

	@Override
	public void insert(PromoTypeVO promoTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promoTypeVO.getCode());
			pstmt.setString(2, promoTypeVO.getName());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	}

	@Override
	public void update(PromoTypeVO promoTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, promoTypeVO.getName());
			pstmt.setString(2, promoTypeVO.getCode());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	}

	@Override
	public void delete(String code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, code);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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

	}
	
	@Override
	public PromoTypeVO findByPrimaryKey(String code) {
		PromoTypeVO promoTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promoTypeVO = new PromoTypeVO();
				promoTypeVO.setCode(rs.getString("CODE"));
				promoTypeVO.setName(rs.getString("NAME"));
			
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
		return promoTypeVO;
	}

	@Override
	public List<PromoTypeVO> getAll() {
		List<PromoTypeVO> list = new ArrayList<PromoTypeVO>();
		PromoTypeVO promoTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery(); // listAll不用得到參數就能執行搜尋

			while (rs.next()) {
				promoTypeVO = new PromoTypeVO();
				promoTypeVO.setCode(rs.getString("CODE"));
				promoTypeVO.setName(rs.getString("NAME"));

				list.add(promoTypeVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
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
		return list;
	}

	public static void main(String[] args) {

		PromoTypeJNDIDAO dao = new PromoTypeJNDIDAO();
		
		PromoTypeVO VO = new PromoTypeVO();
		VO.setCode("A005");
		VO.setName("onlyforuse");
		dao.insert(VO);
//	PromoTypeVO test22 = new PromoTypeVO("22", "22222");
//	new PromoTypeJNDIDAO().insert(test22);


	}
}