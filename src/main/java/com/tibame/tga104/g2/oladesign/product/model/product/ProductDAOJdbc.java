package com.tibame.tga104.g2.oladesign.product.model.product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAOJdbc implements ProductDAO {
	private DataSource dataSource;

	public ProductDAOJdbc() {
		System.out.println("pass jdbc connect");
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	//
	private static final String GET_ALL_PRODUCT = "SELECT pt.TYPE_NAME, ps.STYLE_NAME, p.PROD_ID, p.COM_TAXID, p.TYPE_CODE, p.STYLE_CODE, p.NAME, p.PRICE, p.INTRO, p.AMOUNT_SOLD, p.STOCK, p.SAFE_STOCK, p.AMOUNT_FAVOR, p.STATUS, p.IMG FROM PRODUCT p\n"
			+ "LEFT JOIN PRODUCT_TYPE pt\n" + "ON p.TYPE_CODE = pt.TYPE_CODE\n" + "LEFT JOIN PRODUCT_STYLE ps\n"
			+ "ON p.STYLE_CODE = ps.STYLE_CODE";

	@Override
	public List<ProductBean> select() {
		List<ProductBean> result = null;
		try (
//Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL_PRODUCT);
				ResultSet rset = stmt.executeQuery();) {

			result = new ArrayList<ProductBean>();
			while (rset.next()) {
				ProductBean bean = new ProductBean();
				bean.setProductId(rset.getInt("PROD_ID"));
				bean.setComTaxId(rset.getString("COM_TAXID"));
				bean.setTypeCode(rset.getString("TYPE_CODE"));
				bean.setStyleCode(rset.getString("STYLE_CODE"));
				bean.setName(rset.getString("NAME"));
				bean.setPrice(rset.getInt("PRICE"));
				bean.setIntro(rset.getString("INTRO"));
				bean.setAmountSold(rset.getInt("AMOUNT_SOLD"));
				bean.setStock(rset.getInt("STOCK"));
				bean.setSafeStock(rset.getInt("SAFE_STOCK"));
				bean.setStatus(rset.getBoolean("STATUS"));
//
				byte[] buffer = rset.getBytes("IMG");
				if (buffer.length != 0 && buffer != null) {
					bean.setProductImgBase64(buffer);
				}
				bean.setTypeName(rset.getString("TYPE_NAME"));
				bean.setStyleName(rset.getString("STYLE_NAME"));
				result.add(bean);
//				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//
	private static final String GET_ALL_PRODUCT_BYNAME = "SELECT pt.TYPE_NAME, ps.STYLE_NAME, p.PROD_ID, p.COM_TAXID, p.TYPE_CODE, p.STYLE_CODE, p.NAME, p.PRICE, p.INTRO, p.AMOUNT_SOLD, p.STOCK, p.SAFE_STOCK, p.AMOUNT_FAVOR, p.STATUS, p.IMG FROM PRODUCT p\n"
			+ "LEFT JOIN PRODUCT_TYPE pt\n" + "ON p.TYPE_CODE = pt.TYPE_CODE\n" + "LEFT JOIN PRODUCT_STYLE ps\n"
			+ "ON p.STYLE_CODE = ps.STYLE_CODE\n" + "WHERE p.NAME LIKE ?";

	@Override
	public List<ProductBean> select(String name) {
		List<ProductBean> result = null;
		if (name != null) {

			ResultSet rset = null;
			try (Connection conn = dataSource.getConnection();
					PreparedStatement stmt = conn.prepareStatement(GET_ALL_PRODUCT_BYNAME);) {

				String tempName = "%" + name.trim() + "%";
				stmt.setString(1, tempName);
				rset = stmt.executeQuery();

				result = new ArrayList<ProductBean>();

				while (rset.next()) {
					ProductBean bean = new ProductBean();
					bean.setProductId(rset.getInt("PROD_ID"));
					bean.setComTaxId(rset.getString("COM_TAXID"));
					bean.setTypeCode(rset.getString("TYPE_CODE"));
					bean.setStyleCode(rset.getString("STYLE_CODE"));
					bean.setName(rset.getString("NAME"));
					bean.setPrice(rset.getInt("PRICE"));
					bean.setIntro(rset.getString("INTRO"));
					bean.setAmountSold(rset.getInt("AMOUNT_SOLD"));
					bean.setStock(rset.getInt("STOCK"));
					bean.setSafeStock(rset.getInt("SAFE_STOCK"));
					bean.setStatus(rset.getBoolean("STATUS"));
//
					bean.setTypeName(rset.getString("TYPE_NAME"));
					bean.setStyleName(rset.getString("STYLE_NAME"));

//
					byte[] buffer = rset.getBytes("IMG");
					if (buffer.length != 0 && buffer != null) {
						bean.setProductImgBase64(buffer);
					}

					result.add(bean);
					System.out.println(result);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rset != null) {
					try {
						rset.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	//
	private static final String GET_ALL_PRODUCT_BYCONDITION = "SELECT pt.TYPE_NAME, ps.STYLE_NAME, p.PROD_ID, p.COM_TAXID, p.TYPE_CODE, p.STYLE_CODE, p.NAME, p.PRICE, p.INTRO, p.AMOUNT_SOLD, p.STOCK, p.SAFE_STOCK, p.AMOUNT_FAVOR, p.STATUS, p.IMG FROM PRODUCT p\n"
			+ "LEFT JOIN PRODUCT_TYPE pt\n" + "ON p.TYPE_CODE = pt.TYPE_CODE\n" + "LEFT JOIN PRODUCT_STYLE ps\n"
			+ "ON p.STYLE_CODE = ps.STYLE_CODE\n" + "WHERE p.NAME LIKE ? and\n"
			+ "p.PRICE <= ? and p.TYPE_CODE like ? and p.STYLE_CODE like ?";

	@Override
	public List<ProductBean> select(String name, String typeCode, String styleCode, int price) {
		List<ProductBean> result = null;
		if (name != null) {

			ResultSet rset = null;
			try (Connection conn = dataSource.getConnection();
					PreparedStatement stmt = conn.prepareStatement(GET_ALL_PRODUCT_BYCONDITION);) {

				String tempName = "%" + name.trim() + "%";
				stmt.setString(1, tempName);

				if(price <= 0) {
					price = Integer.MAX_VALUE;
				}
				stmt.setInt(2, price);

				String tempType = "%" + typeCode.trim() + "%";
				stmt.setString(3, tempType);

				String tempStyle = "%" + styleCode.trim() + "%";
				stmt.setString(4, tempStyle);
				rset = stmt.executeQuery();

				result = new ArrayList<ProductBean>();
				while (rset.next()) {
					ProductBean bean = new ProductBean();
					bean.setProductId(rset.getInt("PROD_ID"));
					bean.setComTaxId(rset.getString("COM_TAXID"));
					bean.setTypeCode(rset.getString("TYPE_CODE"));
					bean.setStyleCode(rset.getString("STYLE_CODE"));
					bean.setName(rset.getString("NAME"));
					bean.setPrice(rset.getInt("PRICE"));
					bean.setIntro(rset.getString("INTRO"));
					bean.setAmountSold(rset.getInt("AMOUNT_SOLD"));
					bean.setStock(rset.getInt("STOCK"));
					bean.setSafeStock(rset.getInt("SAFE_STOCK"));
					bean.setStatus(rset.getBoolean("STATUS"));
//
					byte[] buffer = rset.getBytes("IMG");
					if (buffer.length != 0 && buffer != null) {
						bean.setProductImgBase64(buffer);
					}

					bean.setTypeName(rset.getString("TYPE_NAME"));
					bean.setStyleName(rset.getString("STYLE_NAME"));
					result.add(bean);
//					System.out.println(result);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rset != null) {
					try {
						rset.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	//
	//
	private static final String GET_ALL_PRODUCT_BY_COMTAXID = "SELECT pt.TYPE_NAME, ps.STYLE_NAME, p.PROD_ID, p.COM_TAXID, p.TYPE_CODE, p.STYLE_CODE, p.NAME, p.PRICE, p.INTRO, p.AMOUNT_SOLD, p.STOCK, p.SAFE_STOCK, p.AMOUNT_FAVOR, p.STATUS, p.IMG FROM PRODUCT p\n"
			+ "LEFT JOIN PRODUCT_TYPE pt\n" + "ON p.TYPE_CODE = pt.TYPE_CODE\n" + "LEFT JOIN PRODUCT_STYLE ps\n"
			+ "ON p.STYLE_CODE = ps.STYLE_CODE WHERE COM_TAXID = ?";

	@Override
	public List<ProductBean> selectByComTaxId(String comTaxId) {
		List<ProductBean> result = null;
		if (comTaxId != null) {

			ResultSet rset = null;
			try (Connection conn = dataSource.getConnection();
					PreparedStatement stmt = conn.prepareStatement(GET_ALL_PRODUCT_BY_COMTAXID);) {

				stmt.setString(1, comTaxId);
				rset = stmt.executeQuery();

				result = new ArrayList<ProductBean>();
				while (rset.next()) {
					ProductBean bean = new ProductBean();
					bean.setProductId(rset.getInt("PROD_ID"));
					bean.setTypeCode(rset.getString("TYPE_CODE"));
					bean.setStyleCode(rset.getString("STYLE_CODE"));
					bean.setName(rset.getString("NAME"));
					bean.setPrice(rset.getInt("PRICE"));
					bean.setIntro(rset.getString("INTRO"));
					bean.setAmountSold(rset.getInt("AMOUNT_SOLD"));
					bean.setStock(rset.getInt("STOCK"));
					bean.setSafeStock(rset.getInt("SAFE_STOCK"));
					bean.setStatus(rset.getBoolean("STATUS"));
					
					byte[] buffer = rset.getBytes("IMG");
					if (buffer.length != 0 && buffer != null) {
						bean.setProductImgBase64(buffer);
					}
//
					bean.setTypeName(rset.getString("TYPE_NAME"));
					bean.setStyleName(rset.getString("STYLE_NAME"));
					result.add(bean);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rset != null) {
					try {
						rset.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	//
	private static final String GET_PRODUCT_BY_PRODID = "SELECT pt.TYPE_NAME, ps.STYLE_NAME, p.PROD_ID, p.COM_TAXID, p.TYPE_CODE, p.STYLE_CODE, p.NAME, p.PRICE, p.INTRO, p.AMOUNT_SOLD, p.STOCK, p.SAFE_STOCK, p.AMOUNT_FAVOR, p.STATUS, p.IMG FROM PRODUCT p\n"
			+ "LEFT JOIN PRODUCT_TYPE pt\n" + "ON p.TYPE_CODE = pt.TYPE_CODE\n" + "LEFT JOIN PRODUCT_STYLE ps\n"
			+ "ON p.STYLE_CODE = ps.STYLE_CODE WHERE PROD_ID = ?";

	@Override
	public ProductBean selectByProdId(int prodId) {
		ProductBean result = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_PRODUCT_BY_PRODID);) {

			stmt.setInt(1, prodId);
			ResultSet rset = stmt.executeQuery();
			result = new ProductBean();
			while (rset.next()) {
				result.setProductId(rset.getInt("PROD_ID"));
				result.setComTaxId(rset.getString("COM_TAXID"));
				result.setTypeCode(rset.getString("TYPE_CODE"));
				result.setStyleCode(rset.getString("STYLE_CODE"));
				result.setName(rset.getString("NAME"));
				result.setPrice(rset.getInt("PRICE"));
				result.setIntro(rset.getString("INTRO"));
				result.setAmountSold(rset.getInt("AMOUNT_SOLD"));
				result.setStock(rset.getInt("STOCK"));
				result.setSafeStock(rset.getInt("SAFE_STOCK"));
				result.setStatus(rset.getBoolean("STATUS"));
				
				byte[] buffer = rset.getBytes("IMG");
				if (buffer.length != 0 && buffer != null) {
					result.setProductImgBase64(buffer);
				}
				//
				result.setTypeName(rset.getString("TYPE_NAME"));
				result.setStyleName(rset.getString("STYLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//
	private static final String INSERT = "INSERT INTO PRODUCT (COM_TAXID, TYPE_CODE, STYLE_CODE, NAME, PRICE, INTRO, STOCK, SAFE_STOCK, STATUS, IMG) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";
	private static final String GET_MAX_PRODID = "SELECT MAX(PROD_ID) FROM PRODUCT";
	private static final String INSERT_IMG = "INSERT INTO PRODUCT_IMG (PROD_ID, IMG) values (?, ?)";
	private static final String GET_TYPENAME = "SELECT TYPE_NAME FROM PRODUCT_TYPE WHERE TYPE_CODE = ?";
	private static final String GET_STYLENAME = "SELECT STYLE_NAME FROM PRODUCT_STYLE WHERE STYLE_CODE = ?";

	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;

		//
		if (bean != null && bean.getComTaxId() != null) {
			try (Connection conn = dataSource.getConnection();
					PreparedStatement stmt = conn.prepareStatement(INSERT);
					PreparedStatement stmt_maxId = conn.prepareStatement(GET_MAX_PRODID);
					PreparedStatement stmt_type = conn.prepareStatement(GET_TYPENAME);
					PreparedStatement stmt_style = conn.prepareStatement(GET_STYLENAME);
					PreparedStatement stmt_img = conn.prepareStatement(INSERT_IMG);) {
				stmt.setString(1, bean.getComTaxId());
				stmt.setString(2, bean.getTypeCode());
				stmt.setString(3, bean.getStyleCode());
				stmt.setString(4, bean.getName());
				stmt.setInt(5, bean.getPrice());
				stmt.setString(6, bean.getIntro());
				stmt.setInt(7, bean.getStock());
				stmt.setInt(8, bean.getSafeStock());
				stmt.setBoolean(9, bean.isStatus());
				
				InputStream is = bean.getProductImg();
				stmt.setBlob(10, is);
				
				//
				int i = stmt.executeUpdate();
				stmt_type.setString(1, bean.getTypeCode());
				ResultSet typeName = stmt_type.executeQuery();
				stmt_style.setString(1, bean.getStyleCode());
				ResultSet styleName = stmt_style.executeQuery();

				//
//				ResultSet rset_insert = stmt_maxId.executeQuery();
//				int currentProdId;
//				while (rset_insert.next()) {
//					currentProdId = rset_insert.getInt("MAX(PROD_ID)");
//					System.out.println(currentProdId);
//				}
				if (i == 1) {
					result = bean;
				}
				while (typeName.next()) {
					result.setTypeName(typeName.getString("TYPE_NAME"));
				}
				while (styleName.next()) {
					result.setStyleName(styleName.getString("STYLE_NAME"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static final String UPDATE = "UPDATE PRODUCT SET TYPE_CODE =?, STYLE_CODE=?, NAME=?, PRICE=?, INTRO=?, STOCK=?, SAFE_STOCK=?, STATUS=?, IMG=? where PROD_ID=?";

	@Override
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			try (Connection conn = dataSource.getConnection();
					PreparedStatement stmt = conn.prepareStatement(UPDATE);
					PreparedStatement stmt_type = conn.prepareStatement(GET_TYPENAME);
					PreparedStatement stmt_style = conn.prepareStatement(GET_STYLENAME);) {

				stmt.setString(1, bean.getTypeCode());
				stmt.setString(2, bean.getStyleCode());
				stmt.setString(3, bean.getName());
				stmt.setInt(4, bean.getPrice());
				stmt.setString(5, bean.getIntro());
				stmt.setInt(6, bean.getStock());
				stmt.setInt(7, bean.getSafeStock());
				stmt.setBoolean(8, bean.isStatus());
	
				stmt.setBytes(9, bean.getProductImgByteArray());
	
				stmt.setInt(10, bean.getProductId());
				
				int i = stmt.executeUpdate();
				System.out.println(i);
				//
				stmt_type.setString(1, bean.getTypeCode());
				ResultSet typeName = stmt_type.executeQuery();
				stmt_style.setString(1, bean.getStyleCode());
				ResultSet styleName = stmt_style.executeQuery();
				if (i == 1) {
					result = this.selectByProdId(bean.getProductId());
					System.out.println(result);
				}
				while (typeName.next()) {
					result.setTypeName(typeName.getString("TYPE_NAME"));
				}
				while (styleName.next()) {
					result.setStyleName(styleName.getString("STYLE_NAME"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM PRODUCT WHERE PROD_ID=?";

	@Override
	public boolean delete(int productId) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {

			stmt.setInt(1, productId);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
