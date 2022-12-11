package com.tibame.tga104.g2.oladesign.ChatRoom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Chat_RoomVO;

public class Chat_RoomJDBCDAO implements Chat_RoomDAO_interface {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String INSERT_STMT = "insert into CHATROOM(CHATROOM_ID,MEM_0,MEM_1) values(?,?,?)";

	private static final String GET_ALL_STMT = "select CHATROOM_ID,MEM_0,MEM_1 from CHATROOM order by CHATROOM_ID";

	private static final String GET_ONE_STMT = "select CHATROOM_ID,MEM_0,MEM_1 from CHATROOM where CHATROOM_ID =?";

	private static final String DELETE = "delete from CHATROOM where CHATROOM_ID =?";

	private static final String UPDATE = "update CHATROOM set MEM_0=? MEM_1=? where CHATROOM_ID =?";

	@Override
	public void insert(Chat_RoomVO chat_roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chat_roomVO.getChatRoomId());
			pstmt.setString(2, chat_roomVO.getMem0());
			pstmt.setString(3, chat_roomVO.getMem1());

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

	@Override
	public void update(Chat_RoomVO chat_roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chat_roomVO.getMem0());
			pstmt.setString(2, chat_roomVO.getMem1());
			pstmt.setString(3, chat_roomVO.getChatRoomId());
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

	@Override
	public void delete(String chat_roomno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chat_roomno);

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

	}

	@Override
	public Chat_RoomVO findByPrimaryKey(String chat_roomno) {
		Chat_RoomVO chat_roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chat_roomno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chat_roomVO = new Chat_RoomVO();
				chat_roomVO.setChatRoomId(rs.getString("CHATROOM_ID"));
				chat_roomVO.setMem0(rs.getString("MEM_0"));
				chat_roomVO.setMem1(rs.getString("MEM_1"));
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

		return chat_roomVO;
	}

	@Override
	public List<Chat_RoomVO> getAll() {
		List<Chat_RoomVO> list = new ArrayList<Chat_RoomVO>();
		Chat_RoomVO chat_roomVO;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				chat_roomVO = new Chat_RoomVO();
				chat_roomVO.setChatRoomId(rs.getString("CHATROOM_ID"));
				chat_roomVO.setChatRoomId(rs.getString("MEM_0"));
				chat_roomVO.setChatRoomId(rs.getString("MEM_1"));
				list.add(chat_roomVO);
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

	public static void main(String[] args) {
		Chat_RoomJDBCDAO dao = new Chat_RoomJDBCDAO();
		// 查詢單筆資料---ok
//	Chat_RoomVO chat_roomVO1 = dao.findByPrimaryKey("990005");
//	System.out.print(chat_roomVO1.getChatRoomId() + ",");
//	System.out.print(chat_roomVO1.getMem0() + ",");

		// 查詢多筆資料---set的地方忘記把新增的list放進去
//		List<Chat_RoomVO> list = dao.getAll();
//		for (Chat_RoomVO chat_room01 : list) {
//			System.out.print(chat_room01.getChatRoomId());
//			System.out.print(chat_room01.getMem0());
//			System.out.print(chat_room01.getMem1());
//		}

		// 新增---ok日期可以不用送資料,MySQL會直接幫我們建
//	Chat_RoomVO chat_roomVO02 = new Chat_RoomVO();
//	chat_roomVO02.setChatRoomId("990008");
//	chat_roomVO02.setMem1("220001");
//	chat_roomVO02.setMem0("220003");
//
//	dao.insert(chat_roomVO02);

		// 修改
//	Chat_RoomVO chat_roomVO03 = new Chat_RoomVO();
//
//	chat_roomVO03.setMem0("220002");
//	chat_roomVO03.setMem1("220013");
//	chat_roomVO03.setChatRoomId("990008");
//	dao.update(chat_roomVO03);

		// 刪除
		dao.delete("990005");

	}
}
