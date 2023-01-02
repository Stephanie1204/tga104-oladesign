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

import com.tibame.tga104.g2.oladesign.intermail.model.IntermailVO;
import com.tibame.tga104.g2.oladesign.order.model.Status.orderStatus;
import com.tibame.tga104.g2.oladesign.order.model.Status.shippingStatus;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import antlr.Utils;

public class OrderDAOJdbc implements OrderDAO {

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
	private static final String GET_ORDER_BYCOMTAXID_ORDERSTATUS = "SELECT * FROM ORDERS WHERE COM_TAXID=? AND ORDER_STATUS=?";

	@Override
	public List<OrderBean> select_Com(String comTaxId, int orderStatus) {

		List<OrderBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ORDER_BYCOMTAXID_ORDERSTATUS);
			stmt.setString(1, comTaxId);
			stmt.setInt(2, orderStatus);

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
	private static final String GET_ALL_STMT =
			"SELECT * FROM ORDERS ORDER BY ORDER_ID  ";
	@Override
	public List<OrderBean> getAll() {
		List<OrderBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ALL_STMT);

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
	private static final String CHECKONE =
			"SELECT * FROM ORDERS where ORDER_ID = ? ";
	@Override
	public OrderBean getCheckOne(String orderId) {
		OrderBean orderBean = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(CHECKONE);
			
			stmt.setString(1, orderId);

			ResultSet rset = stmt.executeQuery();


			while (rset.next()) {
				orderBean = new OrderBean();

				orderBean.setOrderId(rset.getString("ORDER_ID"));
				orderBean.setComTaxId(rset.getString("COM_TAXID"));
				orderBean.setMemId(rset.getString("MEM_ID"));
				orderBean.setOrderTime(rset.getTimestamp("ORDER_TIME"));
				orderBean.setAddress(rset.getString("ADDRESS"));
				orderBean.setAmount(rset.getInt("AMOUNT"));
				orderBean.setOrderStatus(rset.getInt("ORDER_STATUS"));
				orderBean.setShippingStatus(rset.getInt("SHIPPING_STATUS"));
				orderBean.setCoupon(rset.getString("COUPON"));
				orderBean.setPointUse(rset.getInt("POINT_USE"));
				orderBean.setPointGet(rset.getInt("POINT_GET"));
				orderBean.setReceiver(rset.getString("RECEIVER"));

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
		return orderBean;
	}
	@Override
//	public List<OrderBean>getSearch(String orderId,String comTaxId,String memId, String receiver ,String orderStatus,String shippingStatus) {
//	public List<OrderBean>getSearch(String orderId,String comTaxId,String memId, String receiver ,Integer orderStatus,Integer shippingStatus) {
//	List<OrderBean> list = new ArrayList<OrderBean>();
	public List<OrderBean>getSearch(String orderId,String comTaxId,String memId, String receiver ,Integer orderStatus,Integer shippingStatus,String startTime,String overTime) {


	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	

	try {
		conn = dataSource.getConnection();			
		String sql = "SELECT * FROM ORDERS WHERE 1=1 " ;
		StringBuffer sb = new StringBuffer(sql);			
		List l = new ArrayList<>();
		if(orderId != null && orderId != "") {
			sb.append(" and ORDER_ID like  '%' ? '%' ");
			l.add(orderId);
		}
		
		if(comTaxId != null && comTaxId != "") {
			sb.append(" and COM_TAXID like '%' ? '%' ");
			l.add(comTaxId);
		}
		if(memId != null && memId != "") {
		sb.append("and MEM_ID like '%' ? '%' ");
		l.add(memId);
	}
	if(receiver != null && receiver != "") {
		sb.append("and RECEIVER like '%' ? '%' ");
		l.add(receiver);
	}
//	String orderStatus1 = String.valueOf(orderStatus);
//	int orderStatus1  = 0;
//	if(orderStatus != null && orderStatus != "") {
	if(orderStatus != null ) {
		sb.append("and ORDER_STATUS = ? ");
//		orderStatus1 =Integer.parseInt(orderStatus);
		l.add(orderStatus);
	}

//	String shippingStatus1 = String.valueOf(shippingStatus);
//	if(shippingStatus1 != null && shippingStatus1 != "") {
//		sb.append("and shippingStatus = ? ");
//		shippingStatus =Integer.parseInt(shippingStatus1);
//		l.add(shippingStatus);
//	}
//	int shippingStatus1 = 0;
//	if(shippingStatus != null && shippingStatus != "") {
	if(shippingStatus != null ) {
//		orderStatus1 = Integer.parseInt(shippingStatus);
		sb.append("and SHIPPING_STATUS = ? ");
		l.add(shippingStatus);
	}
	
	if(startTime != null ) {
//		orderStatus1 = Integer.parseInt(shippingStatus);
		sb.append("and ORDER_TIME >= ? ");
		l.add(startTime);
	}
	
	if(overTime != null ) {
//		orderStatus1 = Integer.parseInt(shippingStatus);
		sb.append("and ORDER_TIME <= ? ");
		l.add(overTime);
	}
		
		
		stmt = conn.prepareStatement(sb.toString());						
		for(int i = 1 ; i<=l.size(); i++) {
			stmt.setObject(i, l.get(i-1));
		}			
		
		ResultSet rset = stmt.executeQuery();
		OrderBean orderBean = null;
		List<OrderBean> list = new ArrayList();
		while (rset.next()) {
			orderBean = new OrderBean();

			orderBean.setOrderId(rset.getString("ORDER_ID"));
			orderBean.setComTaxId(rset.getString("COM_TAXID"));
			orderBean.setMemId(rset.getString("MEM_ID"));
			orderBean.setOrderTime(rset.getTimestamp("ORDER_TIME"));
			orderBean.setAddress(rset.getString("ADDRESS"));
			orderBean.setAmount(rset.getInt("AMOUNT"));
			orderBean.setOrderStatus(rset.getInt("ORDER_STATUS"));
			orderBean.setShippingStatus(rset.getInt("SHIPPING_STATUS"));
			orderBean.setCoupon(rset.getString("COUPON"));
			orderBean.setPointUse(rset.getInt("POINT_USE"));
			orderBean.setPointGet(rset.getInt("POINT_GET"));
			orderBean.setReceiver(rset.getString("RECEIVER"));

			list.add(orderBean);
		}
		return list;
		
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
	return null ;
	}
}
