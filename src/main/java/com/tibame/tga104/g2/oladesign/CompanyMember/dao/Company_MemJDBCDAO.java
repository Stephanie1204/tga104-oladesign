package com.tibame.tga104.g2.oladesign.CompanyMember.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;

public class Company_MemJDBCDAO implements Company_MemDAO_interface {

	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String INSERT_STMT = "insert into COMPANY_MEM(COM_TAXID,MEM_ID,COM_NAME,COM_ADDRESS,COM_PHONE,COM_OWNER,OWNER_PHONE,COM_BANKACCOUNT) values(?,?,?,?,?,?,?,?)";

	private static final String FIND_BY_MEM_ID = "SELECT COM_TAXID,MEM_ID,COM_NAME,COM_ADDRESS,COM_PHONE,COM_OWNER,OWNER_PHONE,COM_BANKACCOUNT, STORE_NAME,COM_REGDATE,STORE_INTRO,STORE_LOGO,STORE_BANNER FROM TGA104G2.COMPANY_MEM WHERE MEM_ID =?";

	private static final String GET_ALL_STMT = "select COM_TAXID,MEM_ID,COM_NAME,COM_ADDRESS,COM_PHONE,COM_OWNER,OWNER_PHONE,COM_BANKACCOUNT,STORE_NAME,COM_REGDATE,STORE_INTRO,STORE_LOGO,STORE_BANNER from COMPANY_MEM order by COM_TAXID";

	private static final String GET_ONE_STMT = "select COM_TAXID,MEM_ID,COM_NAME,COM_ADDRESS,COM_PHONE,COM_OWNER,OWNER_PHONE,COM_BANKACCOUNT,STORE_NAME,COM_REGDATE,STORE_INTRO,STORE_LOGO,STORE_BANNER from COMPANY_MEM where COM_TAXID =?";

	// private static final String DELETE = "delete from COMPANY_MEM where COM_TAXID
	// =?";

	private static final String UPDATE = "update COMPANY_MEM set COM_NAME=?,COM_ADDRESS=?,COM_PHONE=?,COM_OWNER=?,OWNER_PHONE=?,COM_BANKACCOUNT=? where COM_TAXID=?";

	private static final String UPDATEFORSHOP = "update COMPANY_MEM set STORE_NAME=?,STORE_INTRO=?,STORE_LOGO=ifnull(?,STORE_LOGO),STORE_BANNER=ifnull(?,STORE_BANNER) where COM_TAXID=?";

	private static final String GET_MEM_BY_COM = "SELECT MEM_ID FROM COMPANY_MEM WHERE COM_TAXID = ?";

	// ifnull=>第一個參數是在servlet
	// get到的值,會以第一個參數去判斷是否為null,如果非null則回傳第一個參數,如果是null則回傳第二參數的值(維持現有欄位的值不做更動)

	@Override
	public Company_MemVO findByMemId(Integer memId) {

		Company_MemVO company_memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEM_ID);

			pstmt.setInt(1, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				company_memVO = new Company_MemVO();
				company_memVO.setComTaxId(rs.getString("COM_TAXID"));
				company_memVO.setMemId(rs.getInt("MEM_ID"));
				company_memVO.setComName(rs.getString("COM_NAME"));
				company_memVO.setComAddress(rs.getString("COM_ADDRESS"));
				company_memVO.setComPhone(rs.getString("COM_PHONE"));
				company_memVO.setComOwner(rs.getString("COM_OWNER"));
				company_memVO.setOwnerPhone(rs.getString("OWNER_PHONE"));
				company_memVO.setComBankaccount(rs.getString("COM_BANKACCOUNT"));
				company_memVO.setStoreName(rs.getString("STORE_NAME"));
				company_memVO.setComRegdate(rs.getDate("COM_REGDATE"));
				company_memVO.setStoreIntro(rs.getString("STORE_INTRO"));
				company_memVO.setStoreLogo(rs.getBytes("STORE_LOGO"));
				company_memVO.setStoreBanner(rs.getBytes("STORE_BANNER"));
			}

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

		return company_memVO;
	}

	@Override
	public void insert(Company_MemVO company_memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, company_memVO.getComTaxId());
			pstmt.setInt(2, company_memVO.getMemId());
			pstmt.setString(3, company_memVO.getComName());
			pstmt.setString(4, company_memVO.getComAddress());
			pstmt.setString(5, company_memVO.getComPhone());
			pstmt.setString(6, company_memVO.getComOwner());
			pstmt.setString(7, company_memVO.getOwnerPhone());
			pstmt.setString(8, company_memVO.getComBankaccount());
			// pstmt.setDate(10, company_memVO.getComRegdate());

			pstmt.executeUpdate();
		}catch (ClassNotFoundException ce) {
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

	}

	@Override
	public void update(Company_MemVO company_memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, company_memVO.getComName());
			pstmt.setString(2, company_memVO.getComAddress());
			pstmt.setString(3, company_memVO.getComPhone());
			pstmt.setString(4, company_memVO.getComOwner());
			pstmt.setString(5, company_memVO.getOwnerPhone());
			pstmt.setString(6, company_memVO.getComBankaccount());
			pstmt.setString(7, company_memVO.getComTaxId());
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

	}

	@Override
	public void updateforshop(Company_MemVO company_memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATEFORSHOP);

			pstmt.setString(1, company_memVO.getStoreName());
			pstmt.setString(2, company_memVO.getStoreIntro());
			pstmt.setBytes(3, company_memVO.getStoreLogo());
			pstmt.setBytes(4, company_memVO.getStoreBanner());
			pstmt.setString(5, company_memVO.getComTaxId());
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

	}

	@Override
	public Company_MemVO findByPrimaryKey(String company_memno) {

		Company_MemVO company_memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, company_memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				company_memVO = new Company_MemVO();
				company_memVO.setComTaxId(rs.getString("COM_TAXID"));
				company_memVO.setMemId(rs.getInt("MEM_ID"));
				company_memVO.setComName(rs.getString("COM_NAME"));
				company_memVO.setComAddress(rs.getString("COM_ADDRESS"));
				company_memVO.setComPhone(rs.getString("COM_PHONE"));
				company_memVO.setComOwner(rs.getString("COM_OWNER"));
				company_memVO.setOwnerPhone(rs.getString("OWNER_PHONE"));
				company_memVO.setComBankaccount(rs.getString("COM_BANKACCOUNT"));
				company_memVO.setStoreName(rs.getString("STORE_NAME"));
				company_memVO.setComRegdate(rs.getDate("COM_REGDATE"));
				company_memVO.setStoreIntro(rs.getString("STORE_INTRO"));
				company_memVO.setStoreLogo(rs.getBytes("STORE_LOGO"));
				company_memVO.setStoreBanner(rs.getBytes("STORE_BANNER"));
			}

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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
		}

		return company_memVO;
	}

	@Override
	public List<Company_MemVO> getAll() {
		Company_MemVO company_memVO;
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Company_MemVO> list = new ArrayList<Company_MemVO>();
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				company_memVO = new Company_MemVO();
				company_memVO.setComTaxId(rs.getString("COM_TAXID"));
				company_memVO.setMemId(rs.getInt("MEM_ID"));
				company_memVO.setComName(rs.getString("COM_NAME"));
				company_memVO.setComAddress(rs.getString("COM_ADDRESS"));
				company_memVO.setComPhone(rs.getString("COM_PHONE"));
				company_memVO.setComOwner(rs.getString("COM_OWNER"));
				company_memVO.setOwnerPhone(rs.getString("OWNER_PHONE"));
				company_memVO.setComBankaccount(rs.getString("COM_BANKACCOUNT"));
				company_memVO.setStoreName(rs.getString("STORE_NAME"));
				company_memVO.setComRegdate(rs.getDate("COM_REGDATE"));
				company_memVO.setStoreIntro(rs.getString("STORE_INTRO"));
				company_memVO.setStoreLogo(rs.getBytes("STORE_LOGO"));
				company_memVO.setStoreBanner(rs.getBytes("STORE_BANNER"));

				list.add(company_memVO);
			}

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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
		}

		return list;
	}

	@Override
	public Integer doGetMemIdByComTaxId(String comTaxId) {
		Integer memId = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_MEM_BY_COM);

			pstmt.setString(1, comTaxId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memId = rs.getInt("MEM_ID");
			}

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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
		}

		return memId;
	}
}