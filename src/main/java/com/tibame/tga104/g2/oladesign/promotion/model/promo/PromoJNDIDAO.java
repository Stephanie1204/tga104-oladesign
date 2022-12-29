package com.tibame.tga104.g2.oladesign.promotion.model.promo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tibame.tga104.g2.oladesign.promotion.model.promoItem.PromoItemVO;

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
	private static final String GET_ALL_STMT = "select * from PROMOTION where COM_TAXID=? order by PROMO_ID desc"; // 反序排
	private static final String GET_ONE_STMT = "select * from PROMOTION where PROMO_ID = ? "; 
	private static final String DELETE = "update PROMOTION set PROMO_STATUS=? where PROMO_ID = ?";
	private static final String UPDATE = "update PROMOTION set PROMO_NAME=?, START_DATE=?, END_DATE=?, COUPON=?, PROMO_STATUS=? where PROMO_ID = ?";
	private static final String CHECK_COUPON = "select promo_ID from promotion where coupon=?";
	private static final String GETALLPROMO = "select * from promotion";
	private static final String GETCURRENTPROMO = "select * from PROMOTION where PROMO_STATUS=\"PS002\" and COM_TAXID=?;";
	
//	public static void main(String[] args) {
//		new PromoJNDIDAO().getAll();	}

	@Override
	public PromoVO getCurrentPromo(String comTaxId) {
	
		PromoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETCURRENTPROMO);
			pstmt.setString(1, comTaxId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo = new PromoVO();
				vo.setPromoId(rs.getInt("PROMO_ID"));  
				vo.setComTaxId(rs.getString("COM_TAXID"));
				vo.setPromoName(rs.getString("PROMO_NAME"));
				vo.setStartDate(rs.getDate("START_DATE"));
				vo.setEndDate(rs.getDate("END_DATE"));
				vo.setCoupon(rs.getString("COUPON"));
				vo.setPromoStatus(rs.getString("PROMO_STATUS"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
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
		return vo;
	}
	
	@Override
	public Boolean checkCoupon(String coupon) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK_COUPON);

			pstmt.setString(1, coupon);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 資料庫已存在資料
				return false; //不能使用
			}else {
				return true; //可以使用
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
	}
	
	@Override
	public void insert(PromoVO promoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

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
	public void update(PromoVO promoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			int idx = 1;
			pstmt.setString(idx++, promoVO.getPromoName());
			pstmt.setObject(idx++, promoVO.getStartDate());
			pstmt.setObject(idx++, promoVO.getEndDate());
			pstmt.setString(idx++, promoVO.getCoupon());
			pstmt.setString(idx++, promoVO.getPromoStatus());
			pstmt.setInt(idx++, promoVO.getPromoId());

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
	public int delete(String promoStatus, Integer promoId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		int count=0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			int idx = 1;
			pstmt.setString(idx++, promoStatus);
			pstmt.setInt(idx++, promoId);		

			count = pstmt.executeUpdate();
			

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
		}return count;
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
				promoVO.setPromoStatus(rs.getString("PROMO_STATUS"));
				promoVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				promoVO.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
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
	public List<PromoVO> getAll(String comTaxId) {
		List<PromoVO> list = new ArrayList<PromoVO>();
		PromoVO promoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, comTaxId);
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
				promoVO.setPromoStatus(rs.getString("PROMO_STATUS"));
				promoVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				promoVO.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
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
	
	public List<PromoVO> getAllPromo() {
		List<PromoVO> list = new ArrayList<PromoVO>();
		PromoVO promoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALLPROMO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promoVO = new PromoVO();
				promoVO.setPromoId(rs.getInt("PROMO_ID"));  
				promoVO.setComTaxId(rs.getString("COM_TAXID"));
				promoVO.setPromoName(rs.getString("PROMO_NAME"));
				promoVO.setStartDate(rs.getDate("START_DATE"));
				promoVO.setEndDate(rs.getDate("END_DATE"));
				promoVO.setCoupon(rs.getString("COUPON"));
				promoVO.setPromoStatus(rs.getString("PROMO_STATUS"));
				promoVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				promoVO.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
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
