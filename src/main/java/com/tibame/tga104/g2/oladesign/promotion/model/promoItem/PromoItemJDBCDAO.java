package com.tibame.tga104.g2.oladesign.promotion.model.promoItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PromoItemJDBCDAO implements PromoItemDAOInterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String GET_PROMO = "select PROMO_ID, PROMO_NAME, START_DATE, END_DATE, COUPON from PROMOTION where PROMO_ID=? ";
	private static final String INSERT_STMT = "insert into PROMOTION_ITEM(PROMO_ID, PROD_ID, CODE, DISCOUNT, CREATE_TIME,MODIFY_TIME) values (?,?,?,?,now(),now())"; // 新增促銷明細
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
	private static final String DELETE = "delete from PROMOTION_ITEM where PROMO_ID = ? and PROD_ID=?"; // 刪除促銷明細的單個商品
	private static final String UPDATE = "update PROMOTION_ITEM set CODE=?, DISCOUNT=? where PROMO_ID=? and PROD_ID=? "; // 只能編輯折扣類型和程度

	@Override
	public void insert(PromoItemVO promoItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, promoItemVO.getPromoId());
			pstmt.setInt(2, promoItemVO.getProdId());
			pstmt.setString(3, promoItemVO.getCode());
			pstmt.setInt(4, promoItemVO.getDiscount());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
	public void update(PromoItemVO promoItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promoItemVO.getCode());
			pstmt.setInt(2, promoItemVO.getDiscount());
			pstmt.setInt(3, promoItemVO.getPromoId());
			pstmt.setInt(4, promoItemVO.getProdId());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
	public int delete(Integer promoId, Integer prodId) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, promoId);
//			pstmt.setInt(2, prodId);
//
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return 0;

	}

	@Override
	public List<PromoItemVO>  findAllByPromoId(Integer promoId) {
		List<PromoItemVO> list = new ArrayList<PromoItemVO>(promoId);
		PromoItemVO promoItemVO = null;
		PromoItemVO promoItemVO1 = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt1 = con.prepareStatement(GET_PROMO);
//
//			pstmt1.setInt(1, promoId);
//			rs1 = pstmt1.executeQuery();
//			while (rs1.next()) {
//			promoItemVO = new PromoItemVO();
//			promoItemVO.setPromoId(rs1.getInt("PROMO_ID"));
//			promoItemVO.setPromoName(rs1.getString("PROMO_NAME"));
//			promoItemVO.setPromoStartDate(rs1.getDate("START_DATE"));
//			promoItemVO.setPromoEndDate(rs1.getDate("END_DATE"));
//			promoItemVO.setCoupon(rs1.getString("COUPON"));
//			list.add(promoItemVO);
//			}
			
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
		}return list;
	}
	
	
	public static void main(String[] args) {
		List<PromoItemVO> test = new PromoItemJDBCDAO().findAllByPromoId(1);
		for(PromoItemVO testprint:test) {
		System.out.println(testprint);
		}	
	}

	@Override
	public List<PromoItemVO> findAllByProdId(Integer prodId) {
		List<PromoItemVO> list = new ArrayList<PromoItemVO>();
		PromoItemVO promoItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_PROMOID_STMT);

			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promoItemVO = new PromoItemVO();
				pstmt.setInt(1, promoItemVO.getPromoId());
				pstmt.setInt(2, promoItemVO.getProdId());
				pstmt.setString(3, promoItemVO.getCode());
				pstmt.setInt(4, promoItemVO.getDiscount());
				list.add(promoItemVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
