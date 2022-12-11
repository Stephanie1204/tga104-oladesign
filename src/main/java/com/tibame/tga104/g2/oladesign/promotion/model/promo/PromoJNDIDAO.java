package com.tibame.tga104.g2.oladesign.promotion.model.promo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PromoJNDIDAO implements PromoDAOInterface {

	@Autowired
	private DataSource ds = null;
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA104G2"); // 放在context.xml
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	private static final String INSERT_STMT = " insert into PROMOTION(COM_TAXID, PROMO_NAME, START_DATE, END_DATE, COUPON) values(?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from PROMOTION order by PROMO_ID desc"; // 反序排
	private static final String GET_ONE_STMT = "select * from PROMOTION where PROMO_ID = ? "; 
	private static final String DELETE = "delete from PROMOTION where PROMO_ID = ?";
	private static final String UPDATE = "update PROMOTION set PROMO_NAME=?, START_DATE=?, END_DATE=?, COUPON=? where PROMO_ID = ?";
	
	public static void main(String[] args) {
		new PromoJNDIDAO().getAll();	}

	@Override
	public void insert(PromoVO promoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promoVO.getComTaxId());
			pstmt.setString(2, promoVO.getPromoName());
			pstmt.setObject(3, promoVO.getStartDate());// 型別怎麼設?
			pstmt.setObject(4, promoVO.getEndDate());
			pstmt.setString(5, promoVO.getCoupon());

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
	public void update(PromoVO promoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promoVO.getComTaxId());
			pstmt.setString(2, promoVO.getPromoName());
			pstmt.setObject(3, promoVO.getStartDate());
			pstmt.setObject(4, promoVO.getEndDate());
			pstmt.setString(5, promoVO.getCoupon());

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
	public void delete(Integer promoId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promoId);

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
	public PromoVO findByPrimaryKey(Integer promoId) {
		PromoVO promoVO = null;  
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promoId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promoVO = new PromoVO();
				promoVO.setPromoId(rs.getInt("PROMO_ID"));
				promoVO.setComTaxId(rs.getString("COM_TAXID"));
				promoVO.setPromoName(rs.getString("PROMO_NAME"));
//				promoVO.setStartDate(rs.getObject("START_DATE",LocalDateTime.class));
//				promoVO.setEndDate(rs.getObject("END_DATE",LocalDateTime.class));
				promoVO.setStartDate(rs.getDate("START_DATE"));
				promoVO.setEndDate(rs.getDate("END_DATE"));
				promoVO.setCoupon(rs.getString("COUPON"));
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
		return promoVO;
	}

	@Override
	public List<PromoVO> getAll() {
		List<PromoVO> list = new ArrayList<PromoVO>();
		PromoVO promoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promoVO = new PromoVO();
				promoVO.setPromoId(rs.getInt("PROMO_ID"));  
				promoVO.setComTaxId(rs.getString("COM_TAXID"));
				promoVO.setPromoName(rs.getString("PROMO_NAME"));
//				promoVO.setStartDate(rs.getObject("START_DATE",LocalDateTime.class));
//				promoVO.setEndDate(rs.getObject("END_DATE",LocalDateTime.class));
				promoVO.setStartDate(rs.getDate("START_DATE"));
				promoVO.setEndDate(rs.getDate("END_DATE"));
				promoVO.setCoupon(rs.getString("COUPON"));
				list.add(promoVO);
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

		return list;
	}

}
