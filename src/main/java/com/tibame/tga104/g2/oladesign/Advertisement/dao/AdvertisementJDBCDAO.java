package com.tibame.tga104.g2.oladesign.Advertisement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementVO;

public class AdvertisementJDBCDAO implements AdvertisementDAO_interface {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String INSERT_STMT = "insert into ADVERTISEMENT(AD_ID,COM_TAXID,START_DATE,END_DATE,AD_IMAGES,STORE_LINK) values(?,?,STR_TO_DATE(?,'%m/%d/%Y'),STR_TO_DATE(?,'%m/%d/%Y'),?,?);";

	private static final String FIND_BY_COM = "select AD_ID,COM_TAXID,DATE_FORMAT(START_DATE,'%m/%d/%Y') AS START_DATE ,DATE_FORMAT(END_DATE,'%m/%d/%Y') AS END_DATE ,AD_STATUS from ADVERTISEMENT where COM_TAXID =?";

	private static final String GET_ALL_STMT = "select AD_ID,COM_TAXID,DATE_FORMAT(START_DATE,'%m/%d/%Y') AS START_DATE ,DATE_FORMAT(END_DATE,'%m/%d/%Y') AS END_DATE,AD_IMAGES,STORE_LINK,AD_STATUS from ADVERTISEMENT order by AD_ID";

	private static final String GET_ONE_STMT = "select AD_ID,COM_TAXID,DATE_FORMAT(START_DATE,'%m/%d/%Y') AS START_DATE ,DATE_FORMAT(END_DATE,'%m/%d/%Y') AS END_DATE ,AD_IMAGES,STORE_LINK,AD_STATUS from ADVERTISEMENT where AD_ID =?";

	private static final String GET_IS_INSERT_ABLE = "SELECT CASE WHEN COUNT(1) >=3 THEN FALSE ELSE TRUE END AS IS_INSERT_ABLE FROM TGA104G2.ADVERTISEMENT WHERE ( START_DATE >= STR_TO_DATE(?,'%m/%d/%Y') AND START_DATE <= STR_TO_DATE(?,'%m/%d/%Y')) OR (END_DATE >= STR_TO_DATE(?,'%m/%d/%Y') AND END_DATE <= STR_TO_DATE(?,'%m/%d/%Y'));";
	/*
	 * //因VO中的日期型態為Date故傳進DB時必須轉型為Date,STR_TO_DATE(第一個是傳入參數,第二個是轉換格式'%Y-%m-%d')
	 * 當where條件count總數>=3回傳false,若無>=3則回傳true
	 */
//  private static final String DELETE = "delete from ADVERTISEMENT where AD_ID =?";
	private static final String UPDATE_BY_ADMIN = "update advertisement set ad_status = true where ad_id = ? and ad_status = false";

	private static final String GET_ADSTUS = "select AD_STATUS FROM ADVERTISEMENT where AD_ID=?";
	
	private static final String GET_TODAY_AD = "SELECT AD_IMAGES, COM_TAXID FROM advertisement WHERE (CURDATE() BETWEEN START_DATE AND END_DATE) AND (AD_STATUS = TRUE)";

	@Override
	public Boolean getIsInsertAble(AdvertisementVO advertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Boolean result = false; // 習慣預設要用反面,故:預設不給新增

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_IS_INSERT_ABLE);

			pstmt.setString(1, advertisementVO.getStartDate()); // MM/DD/YYYY
			pstmt.setString(2, advertisementVO.getEndDate()); // MM/DD/YYYY
			pstmt.setString(3, advertisementVO.getStartDate()); // MM/DD/YYYY
			pstmt.setString(4, advertisementVO.getEndDate()); // MM/DD/YYYY

			rs = pstmt.executeQuery(); // pstmt送給DB後回傳的結果為rs

			rs.next();// 結果為boolean只會有一筆資料,故不需要迴圈
			result = rs.getBoolean("IS_INSERT_ABLE");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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

		return result;
	}

	@Override
	public AdvertisementVO getAdStatus(String adId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		AdvertisementVO advertisementVO = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ADSTUS);

			pstmt.setString(1, adId);

			rs = pstmt.executeQuery();

			advertisementVO = new AdvertisementVO();
			advertisementVO.setAdStatus(rs.getBoolean("AD_STATUS"));

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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

		return advertisementVO;

	}

	@Override
	public void insert(AdvertisementVO advertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, advertisementVO.getAdId());
			pstmt.setString(2, advertisementVO.getComTaxId());
			pstmt.setString(3, advertisementVO.getStartDate());
			pstmt.setString(4, advertisementVO.getEndDate());
			pstmt.setBytes(5, advertisementVO.getAdImages());
			pstmt.setString(6, advertisementVO.getStoreLink());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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

	@Override
	public List<AdvertisementVO> ADRecordByComtaxId(String comTaxId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO advertisementVO;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_COM);
			pstmt.setString(1, comTaxId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				advertisementVO = new AdvertisementVO();
				advertisementVO.setAdId(rs.getString("AD_ID"));
				advertisementVO.setComTaxId(rs.getString("COM_TAXID"));
				advertisementVO.setStartDate(rs.getString("START_DATE"));
				advertisementVO.setEndDate(rs.getString("END_DATE"));
				advertisementVO.setAdStatus(rs.getBoolean("AD_STATUS"));

				list.add(advertisementVO);
			}
			;

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	@Override
	public void updateAdStatus(String adId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_BY_ADMIN);

			pstmt.setString(1, adId);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
			}
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<AdvertisementVO> getTodayAD() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO advertisementVO;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_TODAY_AD);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				advertisementVO = new AdvertisementVO();
				advertisementVO.setComTaxId(rs.getString("COM_TAXID"));
				advertisementVO.setAdImages(rs.getBytes("AD_IMAGES"));
				list.add(advertisementVO);
			}
			;

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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

		return list;

	}

	@Override
	public AdvertisementVO findByPrimaryKey(String advertisementno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		AdvertisementVO advertisementVO = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, advertisementno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				advertisementVO = new AdvertisementVO();
				advertisementVO.setAdId(rs.getString("AD_ID"));
				advertisementVO.setComTaxId(rs.getString("COM_TAXID"));
				advertisementVO.setStartDate(rs.getString("START_DATE"));
				advertisementVO.setEndDate(rs.getString("END_DATE"));
				advertisementVO.setAdImages(rs.getBytes("AD_IMAGES"));
				advertisementVO.setStoreLink(rs.getString("STORE_LINK"));
				advertisementVO.setAdStatus(rs.getBoolean("AD_STATUS"));
			}
			;

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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

		return advertisementVO;

	}

	@Override
	public List<AdvertisementVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO advertisementVO;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				advertisementVO = new AdvertisementVO();
				advertisementVO.setAdId(rs.getString("AD_ID"));
				advertisementVO.setComTaxId(rs.getString("COM_TAXID"));
				advertisementVO.setStartDate(rs.getString("START_DATE"));
				advertisementVO.setEndDate(rs.getString("END_DATE"));
				advertisementVO.setAdImages(rs.getBytes("AD_IMAGES"));
				advertisementVO.setStoreLink(rs.getString("STORE_LINK"));
				advertisementVO.setAdStatus(rs.getBoolean("AD_STATUS"));

				list.add(advertisementVO);
			}
			;

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;

	}

}