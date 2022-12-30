package com.tibame.tga104.g2.oladesign.productbyshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductByShopJDBCDAO implements ProductByShopDAO{
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/tga104g2?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";
	private static final String GET_PROD_BY_COM = "SELECT COM_TAXID,PROD_ID,NAME,PRICE,STATUS,IMG FROM tga104g2.product WHERE COM_TAXID = ? AND STATUS = TRUE;";
			
	@Override
	public List<ProductByShopVO> getProductByComTaxId(String comTaxId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ProductByShopVO> list = new ArrayList<ProductByShopVO>();
		ProductByShopVO productbyshopVO;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GET_PROD_BY_COM);

			pstmt.setString(1, comTaxId);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				productbyshopVO = new ProductByShopVO();
				productbyshopVO.setComTaxId(rs.getString("COM_TAXID"));
				productbyshopVO.setProductId(rs.getInt("PROD_ID"));
				productbyshopVO.setProductName(rs.getNString("NAME"));
				productbyshopVO.setProductPrice(rs.getInt("PRICE"));
				productbyshopVO.setProductStatus(rs.getBoolean("STATUS"));
				byte[] productImg = rs.getBytes("IMG");
				if(productImg == null || productImg.length == 0) {
					productbyshopVO.setProductImage("");
				}else {
					productbyshopVO.setProductImage(
							"data:image/jpg;base64, " + new String(Base64.getEncoder().encode(productImg)));							
				}

				list.add(productbyshopVO);
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
