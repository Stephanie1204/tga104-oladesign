package com.tibame.tga104.g2.oladesign.promotion.model.promoType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromoTypeJDBCDAO implements PromoTypeDAOInterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promoTypeVO.getCode());
			pstmt.setString(2, promoTypeVO.getName());

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
	public void update(PromoTypeVO promoTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promoTypeVO.getName());
			pstmt.setString(2, promoTypeVO.getCode());

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
	public void delete(String code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, code);

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
	public PromoTypeVO findByPrimaryKey(String code) {
		PromoTypeVO promoTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promoTypeVO = new PromoTypeVO();
				promoTypeVO.setCode(rs.getString("CODE"));
				promoTypeVO.setName(rs.getString("NAME"));

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery(); // listAll不用得到參數就能執行搜尋

			while (rs.next()) {
				promoTypeVO = new PromoTypeVO();
				promoTypeVO.setCode(rs.getString("CODE"));
				promoTypeVO.setName(rs.getString("NAME"));

				list.add(promoTypeVO);
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

//	public static void main(String[] args) {
//		
//		PromoTypeJDBCDAO dao = new PromoTypeJDBCDAO();
//		
//		//新增
//		PromoTypeVO VO = new PromoTypeVO();
//		VO.setCode("A005");
//		VO.setName("onlyforuse");
//		dao.insert(VO);

//		//修改
//		PromoTypeVO VO = new PromoTypeVO();
//		VO.setName("only");
//		VO.setCode("A55555005");
//		dao.update(VO);

//		//刪除
//		dao.delete("A55555005");

//		//查全部
//		List<PromoTypeVO> list = dao.getAll();
//		for (PromoTypeVO promoT : list) {
//			System.out.print(promoT.getCode() + ",");
//			System.out.print(promoT.getName());
//			System.out.println();
//		}

//	}
}
