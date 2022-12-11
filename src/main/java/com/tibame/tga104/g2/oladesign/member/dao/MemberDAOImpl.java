package com.tibame.tga104.g2.oladesign.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



public class MemberDAOImpl implements MemberDAO {
	private boolean checkMail = true;
	private static DataSource ds = null;

	public boolean isCheckMail() {
		return checkMail;
	}

	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
		config.setUsername("root");
		config.setPassword("password");

		ds = new HikariDataSource(config);
		
	}

	private static final String InsertSQL = 
			"insert into MEMBER(MEM_NAME, ACCOUNT, PASSWORD, MEM_PHONE, MEM_ADDRESS, SEX, MEM_PHOTO)"
			+ "	values (?, ?, ?, ?, ?, ?, ?)";

	@Override
	public void insert(MemberVO memberVO) {
		Connection connection = null;
		PreparedStatement psmt = null;
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(InsertSQL);
			
			psmt.setString(1, memberVO.getMemName());
			psmt.setString(2, memberVO.getAccount());
			psmt.setString(3, memberVO.getPassword());
			psmt.setString(4, memberVO.getMemPhone());
			psmt.setString(5, memberVO.getMemAddress());
			psmt.setString(6, memberVO.getSex());
			psmt.setBytes(7, memberVO.getMemPhoto());
			
			psmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Duplicate mail entry");
			this.checkMail = false;  //帳號已經存在時
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static final String UpdatSQL = "update member  "
			+ "set MEM_NAME = ?, PASSWORD = ?, MEM_PHONE = ?, "
			+ " MEM_ADDRESS = ?, SEX = ?, POINT = ?, IS_BAN = ?, IS_COM = ?, MEM_PHOTO = ifnull(?, MEM_PHOTO) "
			+ "where MEM_ID = ?;";
	
	@Override
	public void update(MemberVO memberVO) {
		Connection connection = null;
		PreparedStatement psmt = null;
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(UpdatSQL);
			
			psmt.setString(1, memberVO.getMemName());
			psmt.setString(2, memberVO.getPassword());
			psmt.setString(3, memberVO.getMemPhone());
			psmt.setString(4, memberVO.getMemAddress());
			psmt.setString(5, memberVO.getSex());
			psmt.setInt(6, memberVO.getPoint());
			psmt.setBoolean(7, memberVO.isBan());
			psmt.setBoolean(8, memberVO.isCom());
			psmt.setBytes(9, memberVO.getMemPhoto());
			psmt.setInt(10, memberVO.getMemId());
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	private static final String DeleteSQL = 
			"delete from member where MEM_ID = ?;";
	
	@Override
	public void delete(Integer memId) {
		Connection connection = null;
		PreparedStatement psmt = null;
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(DeleteSQL);
			
			psmt.setInt(1, memId);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private static final String GetOneSQL = 
			"select MEM_ID, MEM_NAME, ACCOUNT, PASSWORD, MEM_PHONE, MEM_ADDRESS, MEM_REGDATE, SEX, POINT, IS_BAN, IS_COM, MEM_PHOTO"
			+ " from member where MEM_ID = ?;";
	
	@Override
	public MemberVO findByPrimaryKey(Integer memId) {
		MemberVO memberVO = null;
		Connection connection = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(GetOneSQL);
			psmt.setInt(1, memId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemId(rs.getInt("MEM_ID"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setAccount(rs.getString("ACCOUNT"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setMemPhone(rs.getString("MEM_PHONE"));
				memberVO.setMemAddress(rs.getString("MEM_ADDRESS"));
				memberVO.setMemRegdate(rs.getDate("MEM_REGDATE"));
				memberVO.setSex(rs.getString("SEX"));
				memberVO.setPoint(rs.getInt("POINT"));
				memberVO.setBan(rs.getBoolean("IS_BAN"));
				memberVO.setCom(rs.getBoolean("IS_COM"));
				memberVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
				memberVO.setMemPhotoBase64(rs.getBytes("MEM_PHOTO")); //將byte[] memPhoto轉為Base64格式
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return memberVO;
	}

	private static final String GetAllSQL = 
			"select MEM_ID, MEM_NAME, ACCOUNT, PASSWORD, MEM_PHONE, MEM_ADDRESS, MEM_REGDATE, SEX, POINT, IS_BAN, IS_COM, MEM_PHOTO"
			+ " from member order by MEM_ID;";

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection connection = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(GetAllSQL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemId(rs.getInt("MEM_ID"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setAccount(rs.getString("ACCOUNT"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setMemPhone(rs.getString("MEM_PHONE"));
				memberVO.setMemAddress(rs.getString("MEM_ADDRESS"));
				memberVO.setMemRegdate(rs.getDate("MEM_REGDATE"));
				memberVO.setSex(rs.getString("SEX"));
				memberVO.setPoint(rs.getInt("POINT"));
				memberVO.setBan(rs.getBoolean("IS_BAN"));
				memberVO.setCom(rs.getBoolean("IS_COM"));
				memberVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
				
				if(rs.getBytes("MEM_PHOTO") != null) {
					memberVO.setMemPhotoBase64(rs.getBytes("MEM_PHOTO")); //將byte[] memPhoto轉為Base64格式
				}

				memList.add(memberVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return memList;
	}

	private static final String CheckLoginSQL = "select MEM_ID, MEM_NAME, ACCOUNT, `PASSWORD`, IS_BAN, IS_COM from MEMBER "
			+ "where ACCOUNT = ? and `PASSWORD` = ?;";
	
	@Override
	public MemberVO login(String inputAccount, String inputPassword) {
		MemberVO memberVO = null;
		Connection connection = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(CheckLoginSQL);
			psmt.setString(1, inputAccount);
			psmt.setString(2, inputPassword);
			
			rs = psmt.executeQuery();
						
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemId(rs.getInt("MEM_ID"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setAccount(rs.getString("ACCOUNT"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setBan(rs.getBoolean("IS_BAN"));
				memberVO.setCom(rs.getBoolean("IS_COM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return memberVO;
	}

	private static final String ActiveMemberSQL = "update MEMBER set IS_BAN = 0 where ACCOUNT = ?;";
	@Override
	public void activeMember(String account) {
		Connection connection = null;
		PreparedStatement psmt = null;
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(ActiveMemberSQL);
			psmt.setString(1, account);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	

}
