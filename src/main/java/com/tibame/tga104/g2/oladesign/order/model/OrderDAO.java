package com.tibame.tga104.g2.oladesign.order.model;

import java.util.Date;
import java.util.List;

import com.tibame.tga104.g2.oladesign.intermail.model.IntermailVO;


public interface OrderDAO {
	
	public abstract List<OrderBean> select_Mem(String memberId);
	
	public abstract List<OrderBean> select_Com(String comTaxId);
	
	public abstract List<OrderBean> select_Com(String comTaxId, int orderStatus);
	
	public abstract OrderBean select(String orderId);
	
	public abstract void insert(OrderBean orderBean);
	
	public abstract void updateOrderStatus(String orderId, int orderStatus);
	
	public abstract void updateShippingStatus(String orderId, int shippingStatus);

	public abstract int getPoint(String memberId);
	
	public abstract List<DiscountItem> getDiscountItem(String coupon);
	
	public abstract String getCoupon(String comTaxId);
	
	public abstract void upDatePoint(String memberId, int point);
	
    public abstract List<OrderBean> getAll();	 //管理員查詢所有訂單
    public OrderBean getCheckOne(String orderId); //管理員查看選擇單筆訂單明細
    public abstract List<OrderBean> getSearch(String orderId,String comTaxId,String memId, String receiver ,Integer orderStatus,Integer shippingStatus,String startTime,String overTime);
//    public abstract List<OrderBean> getSearch(String orderId,String comTaxId,String memId, String receiver ,Integer orderStatus,Integer shippingStatus);
//    public abstract List<OrderBean> getSearch(String orderId,String comTaxId,String memId, String receiver ,String orderStatus,String shippingStatus);
//    public abstract List<OrderBean> getSearch(String orderId,String comTaxId);
    
}
