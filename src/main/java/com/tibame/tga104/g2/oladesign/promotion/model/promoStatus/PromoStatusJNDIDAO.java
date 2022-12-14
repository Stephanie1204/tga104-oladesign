package com.tibame.tga104.g2.oladesign.promotion.model.promoStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PromoStatusJNDIDAO implements PromoStatusDAOInterface {
	
	@Autowired
	private DataSource ds = null;

	// 不交給spring控管的版本寫法
//		static {
//			HikariConfig config = new HikariConfig();
//			config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
//			config.setUsername("root");
//			config.setPassword("password");
//
//			ds = new HikariDataSource(config);
	// 原始servlet寫法
//			try {
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA104G2"); // 放在context.xml
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//	}

	private static final String INSERT_STMT = "insert into PROMOTION_STATUS(CODE,NAME) values(?,?)";
	private static final String GET_ALL_STMT = "select * from PROMOTION_STATUS";
	private static final String GET_ONE_STMT = "select * from PROMOTION_STATUS where code = ?";
	private static final String DELETE = "delete from PROMOTION_STATUS where CODE = ?";
	private static final String UPDATE = "update PROMOTION_STATUS set NAME=? where CODE=? ";

	@Override
		public void insert(PromoStatusVO promoStatusVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, promoStatusVO.getCode());
				pstmt.setString(2, promoStatusVO.getName());

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
		public void update(PromoStatusVO promoStatusVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, promoStatusVO.getName());
				pstmt.setString(2, promoStatusVO.getCode());

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
		public PromoStatusVO findByPrimaryKey(String code) {
			PromoStatusVO promoStatusVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, code);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					promoStatusVO = new PromoStatusVO();
					promoStatusVO.setCode(rs.getString("CODE"));
					promoStatusVO.setName(rs.getString("NAME"));
				
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
			return promoStatusVO;
		}

	@Override
		public List<PromoStatusVO> getAll() {
			List<PromoStatusVO> list = new ArrayList<PromoStatusVO>();
			PromoStatusVO promoStatusVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery(); // listAll不用得到參數就能執行搜尋

				while (rs.next()) {
					promoStatusVO = new PromoStatusVO();
					promoStatusVO.setCode(rs.getString("CODE"));
					promoStatusVO.setName(rs.getString("NAME"));

					list.add(promoStatusVO);
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

}
