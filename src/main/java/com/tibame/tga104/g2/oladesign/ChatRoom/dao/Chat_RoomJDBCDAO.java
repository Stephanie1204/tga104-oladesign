package com.tibame.tga104.g2.oladesign.ChatRoom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Chat_RoomJDBCDAO implements Chat_RoomDAO_interface {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String INSERT_STMT = "insert into CHATROOM(CHATROOM_ID,MEM_0,MEM_1) values(?,?,?)";

	private static final String CR_FIND_BY_MEM_ID = "select CHATROOM_ID from CHATROOM where (MEM_0=? AND MEM_1=?) OR (MEM_0=? AND MEM_1=?)";

//	private static final String GET_ALL_STMT = "select CHATROOM_ID,MEM_0,MEM_1 from CHATROOM order by CHATROOM_ID";
//
//	private static final String GET_ONE_STMT = "select CHATROOM_ID,MEM_0,MEM_1 from CHATROOM where CHATROOM_ID =?";
//
//	private static final String DELETE = "delete from CHATROOM where CHATROOM_ID =?";
//
//	private static final String UPDATE = "update CHATROOM set MEM_0=? MEM_1=? where CHATROOM_ID =?";

	@Override
	public String chatRoomfindByMemId(Integer memId0, Integer memId1) {

		String chatRoomId = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(CR_FIND_BY_MEM_ID);

			pstmt.setInt(1, memId0);
			pstmt.setInt(2, memId1);
			pstmt.setInt(3, memId1);
			pstmt.setInt(4, memId0);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				chatRoomId = rs.getString("CHATROOM_ID");
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

		return chatRoomId;
	}

	@Override
	public void insert(String chatRoomId, Integer memId0, Integer memId1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chatRoomId);
			pstmt.setInt(2, memId0);
			pstmt.setInt(3, memId1);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}

		finally {
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

//	@Override
//	public void update(Chat_RoomVO chat_roomVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USERID, PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, chat_roomVO.getMem0());
//			pstmt.setString(2, chat_roomVO.getMem1());
//			pstmt.setString(3, chat_roomVO.getChatRoomId());
//			pstmt.executeUpdate();
//
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		}
//
//		finally {
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
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//	}
//
//	@Override
//	public void delete(String chat_roomno) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USERID, PASSWORD);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, chat_roomno);
//
//			pstmt.executeUpdate();
//
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		}
//
//		finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//	}
//
//	@Override
//	public Chat_RoomVO findByPrimaryKey(String chat_roomno) {
//		Chat_RoomVO chat_roomVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USERID, PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, chat_roomno);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				chat_roomVO = new Chat_RoomVO();
//				chat_roomVO.setChatRoomId(rs.getString("CHATROOM_ID"));
//				chat_roomVO.setMem0(rs.getString("MEM_0"));
//				chat_roomVO.setMem1(rs.getString("MEM_1"));
//			}
//
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return chat_roomVO;
//	}
//
//	@Override
//	public List<Chat_RoomVO> getAll() {
//		List<Chat_RoomVO> list = new ArrayList<Chat_RoomVO>();
//		Chat_RoomVO chat_roomVO;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USERID, PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				chat_roomVO = new Chat_RoomVO();
//				chat_roomVO.setChatRoomId(rs.getString("CHATROOM_ID"));
//				chat_roomVO.setChatRoomId(rs.getString("MEM_0"));
//				chat_roomVO.setChatRoomId(rs.getString("MEM_1"));
//				list.add(chat_roomVO);
//			}
//
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return list;
//	}
}
