package com.tibame.tga104.g2.oladesign.promotion.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoService;
import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoVO;

//@Component
public class CheckPromoStatusHelper {

	@Autowired
	PromoService promoSvc;

	Timer timer = new Timer();

	@PostConstruct
	public void init() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updatePromoStatus();
				System.out.println("hello" + new Date());
			}
		}, new Date(), 1000 * 10);
	}

	@PreDestroy
	public void destroy() {
		timer.cancel();
	}

	private void updatePromoStatus() {
		Date yesterday = getYesterday();
		Date today = new Date();
		System.out.println("yesterday is " + yesterday);
		System.out.println("today is " + today);

		// 開始日期是今天
		for (PromoVO data : promoSvc.getAllPromo()) {
//			System.out.println("startDate is " + data.getStartDate());
//			System.out.println("EndDate is " + data.getEndDate());

			if (!(data.getPromoStatus().equals("PS002") && isP002(data.getStartDate(), data.getEndDate()))) {

			}

//			if (data.getStartDate().after(yesterday) && data.getEndDate().after(today)
//					&& !(data.getPromoStatus().equals("PS002"))) {
//				data.setPromoStatus("PS002");
//				System.out.println(data.getPromoId() + " is PS002");
//				promoSvc.update(data);
//			}
			if (data.getEndDate().before(today) && !(data.getPromoStatus().equals("PS003"))) {
				data.setPromoStatus("PS003");
				System.out.println(data.getPromoId() + " is PS003");
				promoSvc.update(data);
			}
		}
	}

	private static boolean isP002(Date startDate, Date endDate) {
//		data.getStartDate().after(yesterday) && data.getEndDate().after(today)
		return true;
	}

	private static boolean isP003(Date startDate, Date endDate) {
//		data.getStartDate().after(yesterday) && data.getEndDate().after(today)
		return true;
	}

	private Date getYesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

//	private Date getYesterday() {
//	    final Calendar cal = Calendar.getInstance();
//	    cal.add(Calendar.DATE, -1);
//	    return cal.getTime();
//	}

	public static void main(String[] args) {
		Date DEC16 = new Date(122, 11, 16);
		Date DEC17 = new Date(122, 11, 17);
		Date DEC18 = new Date(122, 11, 18);
		Date DEC19 = new Date(122, 11, 19);
		Date DEC20 = new Date(122, 11, 20);
		Date DEC21 = new Date(122, 11, 21);
		Date DEC22 = new Date(122, 11, 22);
		Date DEC23 = new Date(122, 11, 23);
		Date DEC24 = new Date(122, 11, 24);
		Date DEC25 = new Date(122, 11, 25);

		// test isP002 should be true
		System.out.println("should be true " + isP002(DEC16, DEC17));
		System.out.println("should be true " + isP002(DEC21, DEC22));
		System.out.println("should be true " + isP002(DEC21, DEC22));

		// test isP002 should be false
		System.out.println("should be false " + isP002(DEC21, DEC22));
		System.out.println("should be false " + isP002(DEC21, DEC22));
		System.out.println("should be false " + isP002(DEC21, DEC22));

		// test isP003 should be true
		System.out.println("should be true " + isP003(DEC16, DEC17));
		System.out.println("should be true " + isP003(DEC21, DEC22));
		System.out.println("should be true " + isP003(DEC21, DEC22));

		// test isP003 should be false
		System.out.println("should be false " + isP003(DEC16, DEC17));
		System.out.println("should be false " + isP003(DEC21, DEC22));
		System.out.println("should be false " + isP003(DEC21, DEC22));
	}
}
