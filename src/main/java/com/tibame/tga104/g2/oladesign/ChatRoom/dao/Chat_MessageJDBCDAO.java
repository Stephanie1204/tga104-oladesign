package com.tibame.tga104.g2.oladesign.ChatRoom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tibame.tga104.g2.oladesign.ChatRoom.vo.ChatMessage;

public class Chat_MessageJDBCDAO implements Chat_MessageDAO_interface {
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

	private static final String INSERT_STMT = "insert into CHAT_MESSAGE(MESSAGE_ID,CHATROOM_ID,CONTENT,SENDER) values(?,?,?,?)";

	private static final String MS_FIND_BY_CRID = "select SENDER, CONTENT from CHAT_MESSAGE where CHATROOM_ID =?";

//    private static final String GET_ALL_STMT = "select MESSAGE_ID,CHATROOM_ID,IS_SEND,CONTENT,SEND_TIME,IS_READ from CHAT_MESSAGE order by MESSAGE_ID";

//    private static final String DELETE = "delete from CHAT_MESSAGE where MESSAGE_ID =?";
//
//    private static final String UPDATE = "update CHAT_MESSAGE set CHATROOM_ID=?,IS_SEND=?,CONTENT=?,SEND_TIME=?,IS_READ=? where CHATROOM_ID =?";

	@Override
	public List<ChatMessage> chatMessageLog(String chatRroomId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ChatMessage> list = new ArrayList<ChatMessage>();
		ChatMessage chatmessage;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(MS_FIND_BY_CRID);
			pstmt.setString(1, chatRroomId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chatmessage = new ChatMessage();
				chatmessage.setMessage(rs.getString("CONTENT"));
				chatmessage.setSender(rs.getString("SENDER"));

				list.add(chatmessage);
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
	public void insert(ChatMessage chatmessage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chatmessage.getMessageid());
			pstmt.setString(2, chatmessage.getChatroomid());
			pstmt.setString(3, chatmessage.getMessage());
			pstmt.setString(4, chatmessage.getSender());

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

//    @Override
//    public void update(Chat_MessageVO chat_messageVO) {
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	try {
//	    Class.forName(DRIVER);
//	    con = DriverManager.getConnection(URL, USERID, PASSWORD);
//	    pstmt = con.prepareStatement(UPDATE);
//
//	    pstmt.setString(1, chat_messageVO.getMessageId());
//	    pstmt.setString(2, chat_messageVO.getChatRoomId());
//	    pstmt.setBoolean(3, chat_messageVO.getIsSend());
//	    pstmt.setString(4, chat_messageVO.getContent());
//	    pstmt.setDate(5, chat_messageVO.getSendTime());
//	    pstmt.setBoolean(6, chat_messageVO.getIsRead());
//
//	    pstmt.executeUpdate();
//
//	} catch (ClassNotFoundException ce) {
//	    ce.printStackTrace();
//	} catch (SQLException se) {
//	    se.printStackTrace();
//	}
//	if (pstmt != null) {
//	    try {
//		pstmt.close();
//	    } catch (SQLException se) {
//		se.printStackTrace();
//	    }
//	}
//	if (con != null) {
//	    try {
//		con.close();
//	    } catch (Exception e) {
//		e.printStackTrace();
//	    }
//	}
//
//    }
//
//    @Override
//    public void delete(String chat_messageno) {
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	try {
//	    Class.forName(DRIVER);
//	    con = DriverManager.getConnection(URL, USERID, PASSWORD);
//	    pstmt = con.prepareStatement(DELETE);
//
//	    pstmt.setString(1, chat_messageno);
//
//	    pstmt.executeUpdate();
//
//	} catch (ClassNotFoundException ce) {
//	    ce.printStackTrace();
//	} catch (SQLException se) {
//	    se.printStackTrace();
//	}
//	if (pstmt != null) {
//	    try {
//		pstmt.close();
//	    } catch (SQLException se) {
//		se.printStackTrace();
//	    }
//	}
//	if (con != null) {
//	    try {
//		con.close();
//	    } catch (Exception e) {
//		e.printStackTrace();
//	    }
//	}
//
//    }
//
//    @Override
//    public Chat_MessageVO findByPrimaryKey(String chat_messageno) {
//	Chat_MessageVO chat_messageVO = null;
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//
//	try {
//	    Class.forName(DRIVER);
//	    con = DriverManager.getConnection(URL, USERID, PASSWORD);
//	    pstmt = con.prepareStatement(GET_ONE_STMT);
//
//	    pstmt.setString(1, chat_messageno);
//
//	    rs = pstmt.executeQuery();
//
//	    while (rs.next()) {
//		chat_messageVO = new Chat_MessageVO();
//		chat_messageVO.setMessageId(rs.getString("MESSAGE_ID"));
//		chat_messageVO.setChatRoomId(rs.getString("CHATROOM_ID"));
//		chat_messageVO.setIsRead(rs.getBoolean("IS_READ"));
//		chat_messageVO.setContent(rs.getString("CONTENT"));
//		chat_messageVO.setSendTime(rs.getDate("SEND_TIME"));
//		chat_messageVO.setIsSend(rs.getBoolean("IS_READ"));
//	    }
//
//	} catch (ClassNotFoundException ce) {
//	    ce.printStackTrace();
//	} catch (SQLException se) {
//	    se.printStackTrace();
//	}
//	if (pstmt != null) {
//	    try {
//		pstmt.close();
//	    } catch (SQLException se) {
//		se.printStackTrace();
//	    }
//	}
//	if (con != null) {
//	    try {
//		con.close();
//	    } catch (Exception e) {
//		e.printStackTrace();
//	    }
//	}
//
//	return chat_messageVO;
//    }
//
//    @Override
//    public List<Chat_MessageVO> getAll() {
//	List<Chat_MessageVO> list = new ArrayList<Chat_MessageVO>();
//	Chat_MessageVO chat_messageVO = null;
//	
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//
//	try {
//	    Class.forName(DRIVER);
//	    con = DriverManager.getConnection(URL, USERID, PASSWORD);
//	    pstmt = con.prepareStatement(GET_ALL_STMT);
//
//	    rs = pstmt.executeQuery();
//
//	    while (rs.next()) {
//		chat_messageVO = new Chat_MessageVO();
//		chat_messageVO.setMessageId(rs.getString("MESSAGE_ID"));
//		chat_messageVO.setChatRoomId(rs.getString("CHATROOM_ID"));
//		chat_messageVO.setIsRead(rs.getBoolean("IS_READ"));
//		chat_messageVO.setContent(rs.getString("CONTENT"));
//		chat_messageVO.setSendTime(rs.getDate("SEND_TIME"));
//		chat_messageVO.setIsSend(rs.getBoolean("IS_READ"));
//		list.add(chat_messageVO);
//	    }
//
//	} catch (ClassNotFoundException ce) {
//	    ce.printStackTrace();
//	} catch (SQLException se) {
//	    se.printStackTrace();
//	}
//	if (pstmt != null) {
//	    try {
//		pstmt.close();
//	    } catch (SQLException se) {
//		se.printStackTrace();
//	    }
//	}
//	if (con != null) {
//	    try {
//		con.close();
//	    } catch (Exception e) {
//		e.printStackTrace();
//	    }
//	}
//
//	return list;
//    
//    }

}
