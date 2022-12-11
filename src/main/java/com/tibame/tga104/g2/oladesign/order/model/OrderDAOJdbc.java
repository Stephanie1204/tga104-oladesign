package com.tibame.tga104.g2.oladesign.order.model;

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

import order.model.OrderBean;
import order.model.OrderDAO;

public class OrderDAOJdbc implements OrderDAO {

	private DataSource dataSource;

	public OrderDAOJdbc() {
		System.out.println("pass jdbc connect");
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ORDER_BYMEMID = "SELECT * FROM ORDERS WHERE MEM_ID=?";

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

	public OrderBean insert(OrderBean orderBean) {
		OrderBean result = null;
		return result;
	}

	public List<OrderBean> insert(List<OrderBean> orderBeanList) {
		List<OrderBean> result = null;
		return result;
	}

	public OrderBean updateOrderStatus(OrderBean orderBean) {
		OrderBean result = null;
		return result;
	}

	public OrderBean updateShippingStatus(OrderBean orderBean) {
		OrderBean result = null;
		return result;
	}
}
