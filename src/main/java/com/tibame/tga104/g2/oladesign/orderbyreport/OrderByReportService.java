package com.tibame.tga104.g2.oladesign.orderbyreport;

import java.util.List;

public class OrderByReportService {

	private OrderByReportDAO dao;
	public OrderByReportService() {
		dao = new OrderByReportJDBCDAO();
	};
	

	public List<OrderByReportVO> getsalesreport(String comTaxId, String reportYears) {
		return dao.getsalesreport(comTaxId,reportYears);
	}
}
