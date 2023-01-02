package com.tibame.tga104.g2.oladesign.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class OrderItemDAOJdbc implements OrderItemDAO {

	private static DataSource dataSource = null;
	static {
		System.out.println("pass jdbc connect");
		// HikariConfig config = new HikariConfig();
		// config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
		// config.setUsername("root");
		// config.setPassword("password");
		// dataSource = new HikariDataSource(config);

		dataSource = com.tibame.tga104.g2.oladesign.utils.DBConnectionUtils.getDataSource();

	}
	//
	private static final String GET_COMMENT_BYPRODUCTID = "SELECT COMM_STAR, COMM_TEXT FROM ORDER_ITEM WHERE PROD_ID=? AND COMM_STAR > 0";

	public List<OrderItemBean> select(int productId) {
		List<OrderItemBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_COMMENT_BYPRODUCTID);
			stmt.setInt(1, productId);

			ResultSet rset = stmt.executeQuery();

			result = new ArrayList<OrderItemBean>();

			while (rset.next()) {
				OrderItemBean bean = new OrderItemBean();

				bean.setCommentStar(rset.getInt("COMM_STAR"));
				bean.setComment(rset.getString("COMM_TEXT"));

				result.add(bean);
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

	private static final String GET_ORDERITEM_BYORDERID = "SELECT * FROM ORDER_ITEM WHERE ORDER_ID=?";

	public List<OrderItemBean> select(String orderId) {
		List<OrderItemBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ORDERITEM_BYORDERID);
			stmt.setString(1, orderId);

			ResultSet rset = stmt.executeQuery();

			result = new ArrayList<OrderItemBean>();

			while (rset.next()) {
				OrderItemBean bean = new OrderItemBean();

				bean.setOrderId(rset.getString("ORDER_ID"));
				bean.setProductId(rset.getInt("PROD_ID"));
				bean.setProductName(rset.getString("NAME"));
				bean.setQuantity(rset.getInt("QUANTITY"));
				bean.setPrice(rset.getInt("PRICE"));
				bean.setCommentStar(rset.getInt("COMM_STAR"));
				bean.setComment(rset.getString("COMM_TEXT"));

				result.add(bean);
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
	private static final String INSERT_ORDERITEM = "INSERT INTO ORDER_ITEM (ORDER_ID, PROD_ID, NAME, QUANTITY, PRICE) values (?, ?, ?, ?, ?)";

	public void insert(OrderItemBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		//
		if (bean != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(INSERT_ORDERITEM);
//
				stmt.setString(1, bean.getOrderId());
				stmt.setInt(2, bean.getProductId());
				stmt.setString(3, bean.getProductName());
				stmt.setInt(4, bean.getQuantity());
				stmt.setInt(5, bean.getPrice());
				//
				stmt.executeUpdate();

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
	}

	//
	private static final String UPDATE_ORDERITEM_COMMENT = "UPDATE ORDER_ITEM SET COMM_TEXT=?, COMM_STAR=? WHERE PROD_ID=? and ORDER_ID=?";

	public Integer update(OrderItemBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int i=0;

		if (bean != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(UPDATE_ORDERITEM_COMMENT);
//
				stmt.setString(1, bean.getComment());
				stmt.setInt(2, bean.getCommentStar());
				stmt.setInt(3, bean.getProductId());
				stmt.setString(4, bean.getOrderId());

				i = stmt.executeUpdate();
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
		}return i;
	}
}
