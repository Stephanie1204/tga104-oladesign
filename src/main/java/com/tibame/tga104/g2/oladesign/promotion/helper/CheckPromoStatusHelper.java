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

@Component
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
		}, new Date(0L), 24*60*60*1000);
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
		for (PromoVO vo : promoSvc.getAllPromo()) {
			if (!"PS002".equals(vo.getPromoStatus()) && isP002(vo.getStartDate(), vo.getEndDate())) {
				vo.setPromoStatus("PS002");
				System.out.println(vo.getPromoId() + " is PS002");
				promoSvc.update(vo);
			}
			if (!"PS003".equals(vo.getPromoStatus()) && isP003(vo.getStartDate(), vo.getEndDate())) {
				vo.setPromoStatus("PS003");
				System.out.println(vo.getPromoId() + " is PS003");
				promoSvc.update(vo);
			}
		}
	}

	private boolean isP002(Date startDate, Date endDate) {
		Date today = new Date();
		Date yesterday = getYesterday();
		System.out.println(startDate + " is before " + today);
		System.out.println(endDate + " is after " + yesterday);

		if (startDate.before(today) && endDate.after(today)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isP003(Date startDate, Date endDate) {
		Date yesterday = getYesterday();
		if (endDate.before(yesterday)) {
			return true;
		} else {
			return false;
		}
	}

	private static Date getYesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	private static Date getTomorrow() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		return cal.getTime();
	}

	private static Date getToday0000() {
		final Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

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

		CheckPromoStatusHelper helper = new CheckPromoStatusHelper();
		Date tmr = getTomorrow();
		Date today = new Date();
		Date yesterday = getYesterday();
		Date today0000 = getToday0000();

//		System.out.println(DEC16.before(tmr));
//		System.out.println(DEC21.before(tmr));
		System.out.println(DEC23.before(today));

//		System.out.println(DEC25.after(today));
		System.out.println(DEC25.after(yesterday));

//		// test isP002 should be true
		System.out.println("should be true1 " + helper.isP002(DEC16, DEC22));
		System.out.println("should be true2 " + helper.isP002(DEC21, DEC22));
		System.out.println("should be true3 " + helper.isP002(DEC22, DEC22));

		// test isP002 should be false
		System.out.println("should be false1 " + helper.isP002(DEC16, DEC17));
		System.out.println("should be false2 " + helper.isP002(DEC21, DEC21));
		System.out.println("should be false3 " + helper.isP002(DEC23, DEC25));
		System.out.println("should be false4 " + helper.isP002(DEC24, DEC25));

		System.out.println("should be true " + helper.isP002(DEC22, DEC23));
//		System.out.println("should be true " + helper.isP002(yesterday, today0000));

		// test isP003 should be true
		System.out.println("should be true " + helper.isP003(DEC16, DEC17));
		System.out.println("should be true " + helper.isP003(DEC20, DEC21));

		// test isP003 should be false
		System.out.println("should be false " + helper.isP003(DEC22, DEC23));
		System.out.println("should be false " + helper.isP003(DEC21, DEC22));
		System.out.println("should be false " + helper.isP003(DEC21, DEC22));
	}
}
