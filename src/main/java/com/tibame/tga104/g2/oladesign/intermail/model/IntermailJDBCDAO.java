package com.tibame.tga104.g2.oladesign.intermail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags.Flag;
import javax.mail.Session;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class IntermailJDBCDAO implements IntermailDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO INTERMAIL (MEM_ID,ADMIN_ID,NUM_QUE,CONTENT,IS_SEND,IS_REPLY) VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
//			"SELECT * from  INTERMAIL";
			"SELECT * from  INTERMAIL  inner join intermail_qn on intermail.NUM_QUE = intermail_qn.NUM_QUE order by INTERMAIL.INTERMAIL_ID  ";
//	private static final String GET_ALL_STMT = 
	private static final String GET_ONE_STMT = "SELECT * FROM INTERMAIL where INTERMAIL_ID = ?";
	private static final String DELETE = "DELETE FROM INTERMAIL where INTERMAIL_ID = ?";
	private static final String UPDATE = "UPDATE INTERMAIL SET TYPE = ? where INTERMAIL_ID = ?";
	private static final String RECEIVE =
//			"SELECT * from INTERMAIL where IS_SEND = '0' and IS_REPLY = '0' ";
			"select * from intermail inner join intermail_qn on intermail.NUM_QUE = intermail_qn.NUM_QUE Where IS_SEND = '0' and IS_REPLY = '0'  order by INTERMAIL.INTERMAIL_ID ";

	@Override
	public void insert(IntermailVO intermailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, intermailVO.getInterMailId());
			pstmt.setInt(1, intermailVO.getMemId());
			pstmt.setString(2, intermailVO.getAdminId());
			pstmt.setString(3, intermailVO.getNumQue());
			pstmt.setString(4, intermailVO.getConTent());
			pstmt.setBoolean(5, true);
			pstmt.setBoolean(6, true);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(IntermailVO intermailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, intermailVO.getInterMailId());
			pstmt.setInt(2, intermailVO.getMemId());
			pstmt.setString(3, intermailVO.getAdminId());
			pstmt.setString(4, intermailVO.getNumQue());
			pstmt.setString(5, intermailVO.getConTent());
//			pstmt.setBoolean(6, intermailVO.getIsSend());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer interMailId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, interMailId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public IntermailVO findByPrimaryKey(Integer interMailId) {
		IntermailVO intermailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, interMailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getInt("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return intermailVO;
	}

	@Override
	public List<IntermailVO> getAll() {
		List<IntermailVO> list = new ArrayList<IntermailVO>();
		IntermailVO intermailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getInt("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));
				intermailVO.setType(rs.getString("TYPE"));
				list.add(intermailVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public List<IntermailVO> getReceive() {

		List<IntermailVO> list = new ArrayList<IntermailVO>();
		IntermailVO intermailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(RECEIVE);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getInt("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));
				intermailVO.setType(rs.getString("TYPE"));
				list.add(intermailVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	private static final String CHECK =
//			"SELECT * FROM INTERMAIL where INTERMAIL_ID = ? and IS_SEND = '0' ";
			"SELECT * FROM INTERMAIL inner join intermail_qn on intermail.NUM_QUE = intermail_qn.NUM_QUE where INTERMAIL_ID = ? and IS_SEND = '0' ";

	@Override
	public IntermailVO getCheck(Integer interMailId) {
		IntermailVO intermailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CHECK);

			pstmt.setInt(1, interMailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getInt("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));
				intermailVO.setType(rs.getString("TYPE"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return intermailVO;
	}

	private static final String REPLY =
			"UPDATE INTERMAIL SET IS_REPLY = ? where INTERMAIL_ID = ?";

	@Override
	public void getReply(IntermailVO intermailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(REPLY);

			pstmt.setBoolean(1, true);
			pstmt.setInt(2, intermailVO.getInterMailId());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	private static final String MEMINSERT_STMT = "INSERT INTO INTERMAIL (MEM_ID,ADMIN_ID,NUM_QUE,CONTENT,IS_SEND,IS_REPLY) VALUES (?,?,?,?,?,?)";

	@Override
	public Integer meminsert(IntermailVO intermailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		int count=0;
		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(MEMINSERT_STMT);

//			pstmt.setInt(1, intermailVO.getInterMailId());
			pstmt.setInt(1, intermailVO.getMemId());
			pstmt.setString(2, intermailVO.getAdminId());
			pstmt.setString(3, intermailVO.getNumQue());
			pstmt.setString(4, intermailVO.getConTent());
			pstmt.setBoolean(5, false);
			pstmt.setBoolean(6, false);

			count =  pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		} return count;

	}
	

//	private static final String MemRECEIVE = 
//			"SELECT * from INTERMAIL where IS_SEND = '1' ";
	private static final String MemRECEIVE =
//			"SELECT * from INTERMAIL where MEM_ID = ? AND IS_SEND = '1' ";
			"SELECT * from INTERMAIL inner join intermail_qn on intermail.NUM_QUE = intermail_qn.NUM_QUE where MEM_ID = ? order by INTERMAIL.INTERMAIL_ID desc";

	@Override
	public List<IntermailVO> getMemReceive(Integer memId) {
		List<IntermailVO> list = new ArrayList<IntermailVO>();
		IntermailVO intermailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(MemRECEIVE);
			pstmt.setInt(1, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getInt("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));
				intermailVO.setType(rs.getString("TYPE"));
				list.add(intermailVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//	public List<IntermailVO> getMemReceive(Integer memId) {
//		List<IntermailVO> list = new ArrayList<IntermailVO>();
//		IntermailVO intermailVO = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			
//			con = dataSource.getConnection();
//			pstmt = con.prepareStatement(MemRECEIVE);
//			pstmt.setInt(1, memId);
//			
//			
//
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				
//				intermailVO = new IntermailVO();
//				intermailVO.setInterMailId(rs.getInt("INTERMAIL_ID"));
//				intermailVO.setMemId(rs.getInt("MEM_ID"));
//				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
//				intermailVO.setNumQue(rs.getString("NUM_QUE"));
//				intermailVO.setConTent(rs.getString("CONTENT"));
//				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
//				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));
//				list.add(intermailVO); // Store the row in the list
//			}
//
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

	private static final String MemCheck = "SELECT * FROM INTERMAIL inner join intermail_qn on intermail.NUM_QUE = intermail_qn.NUM_QUE where INTERMAIL_ID = ?  ";

	@Override
	public IntermailVO getMemCheck(Integer interMailId) {
		IntermailVO intermailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(MemCheck);

			pstmt.setInt(1, interMailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getInt("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));
				intermailVO.setType(rs.getString("TYPE"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return intermailVO;
	}

	private static final String CHECKAll =
			"SELECT * FROM INTERMAIL inner join intermail_qn on intermail.NUM_QUE = intermail_qn.NUM_QUE where INTERMAIL_ID = ? ";

	@Override
	public IntermailVO getCheckAll(Integer interMailId) {
		IntermailVO intermailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(CHECKAll);

			pstmt.setInt(1, interMailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				intermailVO = new IntermailVO();
				intermailVO.setInterMailId(rs.getInt("INTERMAIL_ID"));
				intermailVO.setMemId(rs.getInt("MEM_ID"));
				intermailVO.setAdminId(rs.getString("ADMIN_ID"));
				intermailVO.setNumQue(rs.getString("NUM_QUE"));
				intermailVO.setConTent(rs.getString("CONTENT"));
				intermailVO.setSentTime(rs.getTimestamp("SEND_TIME"));
				intermailVO.setIsSend(rs.getBoolean("IS_SEND"));
				intermailVO.setType(rs.getString("TYPE"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return intermailVO;
	}
	private static final String MEMDELETE = "DELETE FROM INTERMAIL where INTERMAIL_ID = ?";
	@Override
	public void Memdelete(Integer interMailId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, interMailId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
}

//	private static final String GET_ALLMEM_STMT = 
//			"SELECT * from  INTERMAIL";
//	@Override
//	public List<IntermailVO> getAllmem() {
//		// TODO Auto-generated method stub
//		return null;
//	}
