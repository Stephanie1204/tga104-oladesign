package com.tibame.tga104.g2.oladesign.ChatRoom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Chat_RoomVO;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Hist_Chat_RoomVO;

public class Chat_RoomJDBCDAO implements Chat_RoomDAO_interface {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String INSERT_STMT = "insert into CHATROOM(CHATROOM_ID,MEM_0,MEM_1) values(?,?,?)";

	private static final String CR_FIND_BY_MEM_ID = "select CHATROOM_ID from CHATROOM where (MEM_0=? AND MEM_1=?) OR (MEM_0=? AND MEM_1=?)";

	private static final String CR_ALL_BY_MEM_ID = "select CHATROOM_ID from CHATROOM where MEM_0=? OR MEM_1=?";

	private static final String GET_ALL_CHATROOM = "SELECT A.MEM_ID, A.MEM_PHOTO, A.MEM_NAME, A.MESSAGE FROM(SELECT M.MEM_ID, M.MEM_PHOTO, M.MEM_NAME, (SELECT CME.CONTENT  FROM CHAT_MESSAGE CME WHERE CME.CHATROOM_ID = C.CHATROOM_ID ORDER BY CME.SEND_TIME DESC LIMIT 1) AS MESSAGE	FROM CHATROOM C INNER JOIN MEMBER M ON (M.MEM_ID = IF(C.MEM_0 = ?, C.MEM_1, C.MEM_0)) WHERE C.MEM_0 = ? OR C.MEM_1 = ?)A WHERE A.MESSAGE IS NOT NULL";

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
			while (rs.next()) {
				chatRoomId = rs.getString("CHATROOM_ID");
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

	@Override
	public List<Chat_RoomVO> chatRoomLogs(Integer memId0, Integer memId1) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Chat_RoomVO> list = new ArrayList<Chat_RoomVO>();
		Chat_RoomVO chat_roomVO;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(CR_ALL_BY_MEM_ID);
			pstmt.setInt(1, memId0);
			pstmt.setInt(2, memId1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chat_roomVO = new Chat_RoomVO();
				chat_roomVO.setChatRoomId(rs.getString("CHATROOM_ID"));

				list.add(chat_roomVO);

			}
			System.out.println(list);
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
	public List<Hist_Chat_RoomVO> doGetAllChatroom(Integer mem_0) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Hist_Chat_RoomVO> list = new ArrayList<Hist_Chat_RoomVO>();
		Hist_Chat_RoomVO hist_Chat_RoomVO;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_CHATROOM);
			pstmt.setInt(1, mem_0);
			pstmt.setInt(2, mem_0);
			pstmt.setInt(3, mem_0);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hist_Chat_RoomVO = new Hist_Chat_RoomVO();
				hist_Chat_RoomVO.setMemId(rs.getInt("MEM_ID"));
				hist_Chat_RoomVO.setMessage(rs.getString("MESSAGE"));

				hist_Chat_RoomVO.setStoreName(rs.getString("MEM_NAME"));

				byte[] storeLoge = rs.getBytes("MEM_PHOTO");
				if (storeLoge == null || storeLoge.length == 0) {
					hist_Chat_RoomVO.setStoreLogo("");
				} else {
					hist_Chat_RoomVO.setStoreLogo(
							"data:image/jpg;base64, " + new String(Base64.getEncoder().encode(storeLoge)));
				}

				list.add(hist_Chat_RoomVO);
			}
			System.out.println(list);
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