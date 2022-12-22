package com.tibame.tga104.g2.oladesign.favorite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.tibame.tga104.g2.oladesign.favorite.bean.FavorVO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class FavorDAOImpl implements FavorDAO{
	private static DataSource ds = null;

	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
		config.setUsername("root");
		config.setPassword("password");

		ds = new HikariDataSource(config);
		
	}
	
	private static final String InsertSQL = "insert into FAVORITE(MEM_ID, PROD_ID) values(?, ?);";

	@Override
	public Map<Integer, Integer> insert(FavorVO favorVO) {
		Connection connection = null;
		PreparedStatement psmt = null;
		Map<Integer, Integer> favorMap = new HashMap<Integer, Integer>();
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(InsertSQL);
			
			psmt.setInt(1, favorVO.getMemId());
			psmt.setInt(2, favorVO.getProductId());
			
			psmt.executeUpdate();
			favorMap.put(favorVO.getMemId(), favorVO.getProductId());
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
		
		return favorMap;
	}
	
	private static final String DeleteSQL = "delete from FAVORITE where MEM_ID = ? and PROD_ID = ?;";

	@Override
	public void delete(Integer memId, Integer prodId) {
		Connection connection = null;
		PreparedStatement psmt = null;
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(DeleteSQL);
			
			psmt.setInt(1, memId);
			psmt.setInt(2, prodId);
			
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
	
	private static final String GetOneMemAllSQL = "select pf.MEM_ID, pf.PROD_ID, pf.COM_TAXID, pf.TYPE_CODE, pf.STYLE_CODE, pf.NAME, pf.PRICE, i.IMG_NUM, i.IMG "
			+ "from ( "
			+ "	select f.MEM_ID, p.PROD_ID, p.COM_TAXID, p.TYPE_CODE, p.STYLE_CODE, p.NAME, p.PRICE "
			+ "	from PRODUCT p  "
			+ "	join FAVORITE f "
			+ "	on p.PROD_ID = f.PROD_ID "
			+ ") as pf "
			+ "	join PRODUCT_IMG i "
			+ "		on pf.PROD_ID = i.PROD_ID "
			+ "where pf.MEM_ID = ?;";

	@Override
	public List<FavorVO> getOneMemAll(Integer memId) {
		List<FavorVO> favorList = new ArrayList<FavorVO>();
		FavorVO favorVO = null;
		
		Connection connection = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			psmt = connection.prepareStatement(GetOneMemAllSQL);
			psmt.setInt(1, memId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				favorVO = new FavorVO();
				favorVO.setMemId(rs.getInt("pf.MEM_ID"));
				favorVO.setProductId(rs.getInt("pf.PROD_ID"));
				favorVO.setComTaxId(rs.getString("pf.COM_TAXID"));
				favorVO.setTypeCode(rs.getString("pf.TYPE_CODE"));
				favorVO.setStyleCode(rs.getString("pf.STYLE_CODE"));
				favorVO.setName(rs.getString("pf.NAME"));
				favorVO.setPrice(rs.getInt("pf.PRICE"));
				favorVO.setImgNum(rs.getInt("i.IMG_NUM"));
				favorVO.setImg(rs.getBytes("i.IMG"));	
				if(rs.getBytes("i.IMG") != null) {
					favorVO.setImgBase64(rs.getBytes("i.IMG"));
				}
				
				favorList.add(favorVO);
			}
		}catch(SQLException e) {
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
		return favorList;
	}

}
