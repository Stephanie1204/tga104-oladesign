package com.tibame.tga104.g2.oladesign.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.tibame.tga104.g2.oladesign.order.model.Status.orderStatus;
import com.tibame.tga104.g2.oladesign.order.model.Status.shippingStatus;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class OrderDAOJdbc implements OrderDAO {

	private static DataSource dataSource = null;
	static {
		System.out.println("pass jdbc connect");
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/TGA104G2?serverTimezone=Asia/Taipei");
		config.setUsername("root");
		config.setPassword("password");
		dataSource = new HikariDataSource(config);

	}

//

	private static final String GET_ORDER_BYMEMID = "SELECT * FROM ORDERS WHERE MEM_ID=?";

	@Override
	public List<OrderBean> select_Mem(String memberId) {

		List<OrderBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ORDER_BYMEMID);
			stmt.setInt(1, Integer.parseInt(memberId));

			ResultSet rset = stmt.executeQuery();

			result = new ArrayList<OrderBean>();

			while (rset.next()) {
				OrderBean bean = new OrderBean();

				bean.setOrderId(rset.getString("ORDER_ID"));
				bean.setComTaxId(rset.getString("COM_TAXID"));
				bean.setMemId(rset.getString("MEM_ID"));
				bean.setOrderTime(rset.getTimestamp("ORDER_TIME"));
				bean.setAddress(rset.getString("ADDRESS"));
				bean.setAmount(rset.getInt("AMOUNT"));
				bean.setOrderStatus(rset.getInt("ORDER_STATUS"));
				bean.setShippingStatus(rset.getInt("SHIPPING_STATUS"));
				bean.setCoupon(rset.getString("COUPON"));
				bean.setPointUse(rset.getInt("POINT_USE"));
				bean.setPointGet(rset.getInt("POINT_GET"));
				bean.setReceiver(rset.getString("RECEIVER"));

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

	private static final String GET_ORDER_BYCOMTAXID = "SELECT * FROM ORDERS WHERE COM_TAXID=?";

	@Override
	public List<OrderBean> select_Com(String comTaxId) {

		List<OrderBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ORDER_BYCOMTAXID);
			stmt.setString(1, comTaxId);

			ResultSet rset = stmt.executeQuery();

			result = new ArrayList<OrderBean>();

			while (rset.next()) {
				OrderBean bean = new OrderBean();

				bean.setOrderId(rset.getString("ORDER_ID"));
				bean.setComTaxId(rset.getString("COM_TAXID"));
				bean.setMemId(rset.getString("MEM_ID"));
				bean.setOrderTime(rset.getTimestamp("ORDER_TIME"));
				bean.setAddress(rset.getString("ADDRESS"));
				bean.setAmount(rset.getInt("AMOUNT"));
				bean.setOrderStatus(rset.getInt("ORDER_STATUS"));
				bean.setShippingStatus(rset.getInt("SHIPPING_STATUS"));
				bean.setCoupon(rset.getString("COUPON"));
				bean.setPointUse(rset.getInt("POINT_USE"));
				bean.setPointGet(rset.getInt("POINT_GET"));
				bean.setReceiver(rset.getString("RECEIVER"));

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
	private static final String GET_ORDER_BYORDERID = "SELECT * FROM ORDERS WHERE ORDER_ID=?";

	@Override
	public OrderBean select(String orderId) {

		OrderBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ORDER_BYORDERID);
			stmt.setString(1, orderId);

			ResultSet rset = stmt.executeQuery();
			//
			result = new OrderBean();
			//
			while (rset.next()) {
				OrderBean bean = new OrderBean();

				bean.setOrderId(rset.getString("ORDER_ID"));
				bean.setComTaxId(rset.getString("COM_TAXID"));
				bean.setMemId(rset.getString("MEM_ID"));
				bean.setOrderTime(rset.getTimestamp("ORDER_TIME"));
				bean.setAddress(rset.getString("ADDRESS"));
				bean.setAmount(rset.getInt("AMOUNT"));
				bean.setOrderStatus(rset.getInt("ORDER_STATUS"));
				bean.setShippingStatus(rset.getInt("SHIPPING_STATUS"));
				bean.setCoupon(rset.getString("COUPON"));
				bean.setPointUse(rset.getInt("POINT_USE"));
				bean.setPointGet(rset.getInt("POINT_GET"));
				bean.setReceiver(rset.getString("RECEIVER"));

				result = bean;
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
	private static final String CREATE_ORDER = "INSERT INTO ORDERS(ORDER_ID, COM_TAXID, MEM_ID, ADDRESS, AMOUNT, ORDER_STATUS, SHIPPING_STATUS, COUPON, POINT_USE, POINT_GET, RECEIVER) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public void insert(OrderBean bean) {

		Connection conn = null;
		PreparedStatement stmt = null;
		//
		if (bean != null && bean.getComTaxId() != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(CREATE_ORDER);
//
				stmt.setString(1, bean.getOrderId());
				stmt.setString(2, bean.getComTaxId());
				stmt.setString(3, bean.getMemId());
				stmt.setString(4, bean.getAddress());
				stmt.setInt(5, bean.getAmount());// amount
				stmt.setInt(6, orderStatus.CHECKING.getCode());
				stmt.setInt(7, shippingStatus.CHECKING.getCode());
				stmt.setString(8, bean.getCoupon());
				stmt.setInt(9, bean.getPointUse());
				stmt.setInt(10, bean.getPointGet());// point get
				stmt.setString(11, bean.getReceiver());
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
	private static final String UPDATE_ORDERSTATUS = "UPDATE ORDERS SET ORDER_STATUS=? WHERE ORDER_ID=?";
	@Override
	public void updateOrderStatus(String orderId, int orderStatus) {
		Connection conn = null;
		PreparedStatement stmt = null;

		if (orderId != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(UPDATE_ORDERSTATUS);
//
				stmt.setInt(1, orderStatus);
				stmt.setString(2, orderId);
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
	}
//
	private static final String UPDATE_SHIPPINGSTATUS = "UPDATE ORDERS SET SHIPPING_STATUS=? WHERE ORDER_ID=?";
	@Override
	public void updateShippingStatus(String orderId, int shippingStatus) {
		Connection conn = null;
		PreparedStatement stmt = null;

		if (orderId != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(UPDATE_SHIPPINGSTATUS);
//
				stmt.setInt(1, shippingStatus);
				stmt.setString(2, orderId);
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
	}

	private static final String GET_MEMBERPOINT = "SELECT POINT FROM MEMBER WHERE MEM_ID=?";

	@Override
	public int getPoint(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int point = 0;

		if (memberId != null && memberId != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(GET_MEMBERPOINT);
//
				stmt.setString(1, memberId);
				ResultSet rset = stmt.executeQuery();
				//
				while (rset.next()) {
					point = rset.getInt("POINT");
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
		}
		return point;
	}
	//

	private static final String UPDATE_MEMBERPOINT = "UPDATE MEMBER SET POINT=? WHERE MEM_ID=?";

	@Override
	public void upDatePoint(String memberId, int point) {
		Connection conn = null;
		PreparedStatement stmt = null;

		if (memberId != null && memberId != null) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(UPDATE_MEMBERPOINT);
//
				stmt.setInt(1, point);
				stmt.setString(2, memberId);
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
	}

	//
	private static final String GET_DISCOUNTITEM = "SELECT PROD_ID, CODE, DISCOUNT FROM PROMOTION_ITEM WHERE PROMO_ID=( SELECT PROMO_ID FROM PROMOTION WHERE COUPON=?)";

	@Override
	public List<DiscountItem> getDiscountItem(String coupon) {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<DiscountItem> result = null;

		if (coupon != null && coupon.length() != 0) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(GET_DISCOUNTITEM);
				result = new ArrayList<DiscountItem>();
//
				stmt.setString(1, coupon);
				ResultSet rset = stmt.executeQuery();
//
				while (rset.next()) {
					DiscountItem bean = new DiscountItem();

					bean.setProductId(rset.getInt("PROD_ID"));
					bean.setDiscountCode(rset.getString("CODE"));
					bean.setDiscount(rset.getInt("DISCOUNT"));

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
		}
		return result;
	}

	// 追加時間條件
	private static final String GET_COUPON = "SELECT COUPON FROM PROMOTION WHERE COM_TAXID=? AND START_DATE <=? AND END_DATE >=?";

	@Override
	public String getCoupon(String comTaxId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sdate = dateFormat.format(date);

		String coupon = "";

		if (comTaxId != null && comTaxId.length() != 0) {
			try {
				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(GET_COUPON);
//
				stmt.setString(1, comTaxId);
				stmt.setString(2, sdate);
				stmt.setString(3, sdate);
				ResultSet rset = stmt.executeQuery();
//
				while (rset.next()) {
					coupon = rset.getString("COUPON");
				}
//				System.out.println(coupon);
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
		return coupon;
	}
}
