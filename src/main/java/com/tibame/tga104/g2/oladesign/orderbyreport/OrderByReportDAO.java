package com.tibame.tga104.g2.oladesign.orderbyreport;

import java.util.List;

public interface OrderByReportDAO {

	public List<OrderByReportVO> getsalesreport(String comTaxId, String reportYears);
	
	
}
