package com.tibame.tga104.g2.oladesign.promotion.model.promoItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PromoItemJNDIDAO implements PromoItemDAOInterface{
	@Autowired
	private DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA104G2"); // 放在context.xml
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	private static final String TEST="select PMI.PROD_ID, P.NAME, PMI.CODE, PMT.NAME, PMI.DISCOUNT, P.PRICE, P.STOCK, PMI.CREATE_TIME, PMI.MODIFY_TIME from PROMOTION_ITEM PMI\r\n"
			+ "	join PRODUCT P on P.PROD_ID=PMI.PROD_ID\r\n"
			+ "    join PROMOTION_TYPE PMT on PMT.CODE = PMI.CODE\r\n"
			+ "    where PMI.PROD_ID=?;";
	private static final String GET_PROMO = "select PROMO_ID, PROMO_NAME, START_DATE, END_DATE, COUPON from PROMOTION where PROMO_ID=? ";
	private static final String INSERT_STMT ="insert into PROMOTION_ITEM(PROMO_ID, PROD_ID, CODE, DISCOUNT,CREATE_TIME,MODIFY_TIME) values (?,?,?,?,now(),now())";  //新增促銷明細
	private static final String GET_ALL_BY_PROMOID_STMT ="SELECT PM.PROMO_ID,P.PROMO_NAME, PM.PROD_ID, PD.NAME, PD.PRICE, PM.CODE, PT.NAME, PM.DISCOUNT, PD.STOCK, PM.CREATE_TIME,PM.MODIFY_TIME,P.START_DATE,P.END_DATE,P.COUPON\r\n"
			+ "	FROM TGA104G2.PROMOTION_ITEM PM\r\n"
			+ "    join PRODUCT PD\r\n"
			+ "		on PM.PROD_ID = PD.PROD_ID\r\n"
			+ "    join PROMOTION_TYPE PT\r\n"
			+ "		on PM.CODE = PT.CODE\r\n"
			+ "	join PROMOTION P\r\n"
			+ "		on P.PROMO_ID = PM.PROMO_ID\r\n"
			+ "    where PM.PROMO_ID=?;"; //依活動編號查詢做活動的商品明細
	private static final String GET_ALL_BY_PRODID_STMT ="select PMI.PROMO_ID,PM.PROMO_NAME,PM.START_DATE,PM.END_DATE,PMI.PROD_ID,P.NAME,P.PRICE,PMI.CODE,PMT.NAME,PMI.DISCOUNT,PM.COUPON,PM.CREATE_TIME,PM.MODIFY_TIME\r\n"
			+ "from PROMOTION_ITEM PMI\r\n"
			+ "	join PROMOTION PM\r\n"
			+ "		on PMI.PROMO_ID = PM.PROMO_ID\r\n"
			+ "	join PRODUCT P\r\n"
			+ "		on PMI.PROD_ID = P.PROD_ID\r\n"
			+ "	join PROMOTION_TYPE PMT\r\n"
			+ "		on PMI.CODE = PMT.CODE\r\n"
			+ "\r\n"
			+ "where PMI.PROD_ID=?;"; //依商品編號查詢有做過的促銷方案
//	private static final String GET_ONE_STMT ="select * from PROMOTION_ITEM where PROMO_ID=? and PROD_ID=?"; 應該沒這功能??????????????
	private static final String DELETE ="delete from PROMOTION_ITEM where PROMO_ID = ? and PROD_ID=?";  //刪除促銷明細的單個商品
	private static final String UPDATE ="update PROMOTION_ITEM set CODE=?, DISCOUNT=? ,MODIFY_TIME=NOW() where PROMO_ID=? and PROD_ID=? "; //只能編輯折扣類型和程度

	
	@Override
	public void insert(PromoItemVO promoItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, promoItemVO.getPromoId());
			pstmt.setInt(2, promoItemVO.getProdId());
			pstmt.setString(3, promoItemVO.getCode());
			pstmt.setInt(4, promoItemVO.getDiscount());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
	public void update(PromoItemVO promoItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			int idx = 1;
			pstmt.setString(idx++, promoItemVO.getCode());
			pstmt.setInt(idx++, promoItemVO.getDiscount());
			pstmt.setInt(idx++, promoItemVO.getPromoId());
			pstmt.setInt(idx++, promoItemVO.getProdId());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
	public int delete(Integer promoId, Integer prodId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, promoId);
			pstmt.setInt(2, prodId);
			
			count = pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
		
		return count;
	}
	@Override
	public List<PromoItemVO> findAllByPromoId(Integer promoId) {
		List<PromoItemVO> list = new ArrayList<PromoItemVO>();
		PromoItemVO promoItemVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_PROMOID_STMT);
			pstmt.setInt(1, promoId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				promoItemVO = new PromoItemVO();
				promoItemVO.setProdId(rs.getInt("PM.PROD_ID"));
				promoItemVO.setProdName(rs.getString("PD.NAME"));
				promoItemVO.setPrice(rs.getInt("PD.PRICE"));
				promoItemVO.setCode(rs.getString("PM.CODE"));
				promoItemVO.setCodeName(rs.getString("PT.NAME"));
				promoItemVO.setDiscount(rs.getInt("PM.DISCOUNT"));
				promoItemVO.setStock(rs.getInt("PD.STOCK"));
				promoItemVO.setCreateTime(rs.getTimestamp("PM.CREATE_TIME"));
				promoItemVO.setModifyTime(rs.getTimestamp("PM.MODIFY_TIME"));
				list.add(promoItemVO);
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
//		Arrays.asList(list);
		return list;
	}
	
	@Override
	public List<PromoItemVO> findAllByProdId(Integer prodId) {
		List<PromoItemVO> list = new ArrayList<PromoItemVO>();
		PromoItemVO promoItemVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_PRODID_STMT);
			
			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promoItemVO = new PromoItemVO();
				promoItemVO.setPromoId(rs.getInt("PMI.PROMO_ID"));
				promoItemVO.setPromoName(rs.getString("PM.PROMO_NAME"));
				promoItemVO.setPromoStartDate(rs.getDate("PM.START_DATE"));
				promoItemVO.setPromoEndDate(rs.getDate("PM.END_DATE"));
				promoItemVO.setProdId(rs.getInt("PMI.PROD_ID"));
				promoItemVO.setProdName(rs.getString("P.NAME"));
				promoItemVO.setPrice(rs.getInt("P.PRICE"));
				promoItemVO.setCode(rs.getString("PMI.CODE"));
				promoItemVO.setCodeName(rs.getString("PMT.NAME"));
				promoItemVO.setDiscount(rs.getInt("PMI.DISCOUNT"));
				promoItemVO.setCoupon(rs.getString("PM.COUPON"));
				promoItemVO.setCreateTime(rs.getTimestamp("PM.CREATE_TIME"));
				promoItemVO.setModifyTime(rs.getTimestamp("PM.MODIFY_TIME"));
				list.add(promoItemVO);
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
