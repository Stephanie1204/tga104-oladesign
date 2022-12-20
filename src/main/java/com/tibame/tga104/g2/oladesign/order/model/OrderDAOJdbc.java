package com.tibame.tga104.g2.oladesign.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public List<OrderBean> select_Mem(int memberId) {

		List<OrderBean> result = null;

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ORDER_BYMEMID);) {

			stmt.setInt(1, memberId);

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
		}
		return result;
	}
	
	private static final String GET_ORDER_BYCOMTAXID = "SELECT * FROM ORDERS WHERE COM_TAXID=?";
	
	@Override
	public List<OrderBean> select_Com(String comTaxId) {
		List<OrderBean> result = null;

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ORDER_BYCOMTAXID);) {

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
		}
		return result;
	}

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
				stmt.setInt(5, bean.getAmount());//amount
				stmt.setInt(6, orderStatus.CHECKING.getCode());
				stmt.setInt(7, shippingStatus.CHECKING.getCode());
				stmt.setString(8, bean.getCoupon());
				stmt.setInt(9, bean.getPointUse());
				stmt.setInt(10, bean.getPointGet());//point get
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

	@Override
	public OrderBean updateOrderStatus(OrderBean orderBean) {
		OrderBean result = null;
		
		return result;
	}

	@Override
	public OrderBean updateShippingStatus(OrderBean orderBean) {
		OrderBean result = null;
		return result;
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
				while(rset.next()) {
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
}
