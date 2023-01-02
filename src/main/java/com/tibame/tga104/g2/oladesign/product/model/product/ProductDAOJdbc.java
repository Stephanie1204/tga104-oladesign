package com.tibame.tga104.g2.oladesign.product.model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ProductDAOJdbc implements ProductDAO {

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

	//
	private static final String GET_ALL_PRODUCT = "SELECT pt.TYPE_NAME, ps.STYLE_NAME, p.PROD_ID, p.COM_TAXID, p.TYPE_CODE, p.STYLE_CODE, p.NAME, p.PRICE, p.INTRO, p.AMOUNT_SOLD, p.STOCK, p.SAFE_STOCK, p.AMOUNT_FAVOR, p.STATUS, p.IMG FROM PRODUCT p\n"
			+ "LEFT JOIN PRODUCT_TYPE pt\n" + "ON p.TYPE_CODE = pt.TYPE_CODE\n" + "LEFT JOIN PRODUCT_STYLE ps\n"
			+ "ON p.STYLE_CODE = ps.STYLE_CODE";

	@Override
	public List<ProductBean> select() {

		List<ProductBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
//		System.out.println("jdbc selectAll");
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ALL_PRODUCT);
			ResultSet rset = stmt.executeQuery();

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
				if (buffer != null) {
					bean.setProductImgBase64(buffer);
				}
				bean.setTypeName(rset.getString("TYPE_NAME"));
				bean.setStyleName(rset.getString("STYLE_NAME"));
				result.add(bean);
//				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		Connection conn = null;
		PreparedStatement stmt = null;
//		System.out.println("jdbc selectAll_byName");
		if (name != null) {

			ResultSet rset = null;
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(GET_ALL_PRODUCT_BYNAME);
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
					if (buffer != null) {
						bean.setProductImgBase64(buffer);
					}

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
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
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
		Connection conn = null;
		PreparedStatement stmt = null;
//		System.out.println("jdbc selectAll_byCondition");
		if (name != null) {

			ResultSet rset = null;
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(GET_ALL_PRODUCT_BYCONDITION);
				String tempName = "%" + name.trim() + "%";
				stmt.setString(1, tempName);

				if (price <= 0) {
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
					if (buffer != null) {
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
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
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
		Connection conn = null;
		PreparedStatement stmt = null;
//		System.out.println("jdbc selectAll_bycomTaxId");
		if (comTaxId != null) {

			ResultSet rset = null;
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(GET_ALL_PRODUCT_BY_COMTAXID);
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
					if (buffer != null) {
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
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
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
		Connection conn = null;
		PreparedStatement stmt = null;
//		System.out.println("jdbc selectAll byprodId");
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_PRODUCT_BY_PRODID);
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
				if (buffer != null) {
					result.setProductImgBase64(buffer);
				}
				//
				result.setTypeName(rset.getString("TYPE_NAME"));
				result.setStyleName(rset.getString("STYLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	//
	private static final String INSERT = "INSERT INTO PRODUCT (COM_TAXID, TYPE_CODE, STYLE_CODE, NAME, PRICE, INTRO, STOCK, SAFE_STOCK, STATUS, IMG) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";
	private static final String GET_MAX_PRODID = "SELECT MAX(PROD_ID) FROM PRODUCT";
	private static final String GET_TYPENAME = "SELECT TYPE_NAME FROM PRODUCT_TYPE WHERE TYPE_CODE = ?";
	private static final String GET_STYLENAME = "SELECT STYLE_NAME FROM PRODUCT_STYLE WHERE STYLE_CODE = ?";

	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_maxId = null;
		PreparedStatement stmt_type = null;
		PreparedStatement stmt_style = null;
		PreparedStatement stmt_img = null;
//		System.out.println("jdbc insert");
		//
		if (bean != null && bean.getComTaxId() != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(INSERT);
				stmt_maxId = conn.prepareStatement(GET_MAX_PRODID);
				stmt_type = conn.prepareStatement(GET_TYPENAME);
				stmt_style = conn.prepareStatement(GET_STYLENAME);
				stmt.setString(1, bean.getComTaxId());
				stmt.setString(2, bean.getTypeCode());
				stmt.setString(3, bean.getStyleCode());
				stmt.setString(4, bean.getName());
				stmt.setInt(5, bean.getPrice());
				stmt.setString(6, bean.getIntro());
				stmt.setInt(7, bean.getStock());
				stmt.setInt(8, bean.getSafeStock());
				stmt.setBoolean(9, bean.isStatus());
				stmt.setBytes(10, bean.getProductImgByteArray());

				//
				int i = stmt.executeUpdate();
				stmt_type.setString(1, bean.getTypeCode());
				ResultSet typeName = stmt_type.executeQuery();
				stmt_style.setString(1, bean.getStyleCode());
				ResultSet styleName = stmt_style.executeQuery();

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
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt_maxId != null) {
					try {
						stmt_maxId.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt_type != null) {
					try {
						stmt_type.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt_style != null) {
					try {
						stmt_style.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt_img != null) {
					try {
						stmt_img.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	private static final String UPDATE = "UPDATE PRODUCT SET TYPE_CODE =?, STYLE_CODE=?, NAME=?, PRICE=?, INTRO=?, STOCK=?, SAFE_STOCK=?, STATUS=?, IMG=ifnull(?,IMG) where PROD_ID=?";

	@Override
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt_type = null;
		PreparedStatement stmt_style = null;
//		System.out.println("jdbc update");
		if (bean != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(UPDATE);
				stmt_type = conn.prepareStatement(GET_TYPENAME);
				stmt_style = conn.prepareStatement(GET_STYLENAME);
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
//				System.out.println(bean.getProductImgByteArray());
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
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt_type != null) {
					try {
						stmt_type.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt_style != null) {
					try {
						stmt_style.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return result;
	}

	private static final String DELETE = "DELETE FROM PRODUCT WHERE PROD_ID=?";

	@Override
	public boolean delete(int productId) {
//		System.out.println("jdbc delete");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, productId);
			int i = stmt.executeUpdate();
			if (i == 1) {
				if (conn != null) {
					conn.close();
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//

	private static final String GET_PRODUCTPRICE = "SELECT PRICE FROM PRODUCT WHERE PROD_ID=?";

	@Override
	public int getPrice(int productId) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int productPrice = 0;
		ResultSet rset = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_PRODUCTPRICE);
			stmt.setInt(1, productId);
			rset = stmt.executeQuery();

			while (rset.next()) {
				productPrice = rset.getInt("PRICE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return productPrice;
	}

//
	private static final String SELECT_IMG = "SELECT IMG_NUM, PROD_ID, IMG FROM PRODUCT_IMG WHERE PROD_ID=?";

	@Override
	public List<ProductImageBean> selectImg(int productId) {
		List<ProductImageBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECT_IMG);
			stmt.setInt(1, productId);
			
			ResultSet rset = stmt.executeQuery();

			result = new ArrayList<ProductImageBean>();
			int maxLength = 3;
			int imageOrder = 1;
			while (rset.next()) {
				ProductImageBean bean = new ProductImageBean();
				bean.setImageId(Integer.toString(rset.getInt("IMG_NUM")));
				bean.setProductId(rset.getInt("PROD_ID"));
//
				byte[] buffer = rset.getBytes("IMG");
				if (buffer != null) {
					bean.setProductImgBase64(buffer);
				}
				result.add(bean);
				imageOrder++;
				if (imageOrder > maxLength) {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String INSERT_IMG = "INSERT INTO PRODUCT_IMG (PROD_ID, IMG) values (?, ?)";

	@Override
	public void insertImg(ProductImageBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(INSERT_IMG);
			stmt.setInt(1, bean.getProductId());
			stmt.setBytes(2, bean.getProductImgByteArray());
			//
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static final String DELETE_IMG = "DELETE FROM PRODUCT_IMG WHERE IMG_NUM=?";

	@Override
	public void deleteImg(int imageId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(DELETE_IMG);
			stmt.setInt(1, imageId);
			//
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//
	private static final String GET_IMG_AMOUNT = "SELECT COUNT(PROD_ID) FROM PRODUCT_IMG WHERE PROD_ID=?";
	@Override
	public int getImgAmount(int productId) {
		Connection conn = null;
		PreparedStatement stmt = null;
        int amount = 0;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_IMG_AMOUNT);
			
			stmt.setInt(1, productId);
			ResultSet rset = stmt.executeQuery();

			while (rset.next()) {
				amount = rset.getInt("COUNT(PROD_ID)");
//				System.out.println(amount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return amount;
	}
}
