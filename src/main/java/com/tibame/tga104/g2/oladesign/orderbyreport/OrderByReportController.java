package com.tibame.tga104.g2.oladesign.orderbyreport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/CompanyBackEnd/report.do")
public class OrderByReportController extends HttpServlet {
	private static final long serialVersionUID = 2L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("doGetCOMReport".equals(action)) {
			String comTaxId = req.getParameter("comTaxId");
			String reportYears = req.getParameter("reportYears");	
			OrderByReportService orderByReportSVC = new OrderByReportService();
			List<OrderByReportVO> orderByReportVO = orderByReportSVC.getsalesreport(comTaxId, reportYears);
			List<OrderByReportVO> result = new ArrayList<OrderByReportVO>();
			
			for(int i = 0 ; i< orderByReportVO.size(); i++) {
				OrderByReportVO orderByReport = orderByReportVO.get(i);
				orderByReport.setComTaxId(orderByReport.getComTaxId());
				orderByReport.setMonth(orderByReport.getMonth());
				orderByReport.setDayPrice(orderByReport.getDayPrice());
				result.add(orderByReport);
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(result);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
	}
}