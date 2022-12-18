package com.tibame.tga104.g2.oladesign.promotion.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CorsController {

	@RequestMapping("/person")
	public Map<String, String> get(@RequestBody(required = false) Map<String, String> reqBody, HttpSession session) {
		// 測試接收JSON的請求
		System.out.println("reqBody => " + reqBody);

		// 測試回傳JSON
		Map<String, String> map = new HashMap<>();
		map.put("name", "vic");
		map.put("gender", "male");

		// 測試session是否同一個
		// session要能keep住的前提是同樣使用tomcat拿到前端頁面
		// 意思是如果前端是透過例如VS Code的Live Server啟動的，Session會不斷變動
		map.put("session", session.getId());

		System.out.println(session.getAttribute("memId"));
		System.out.println(session.getAttribute("comTaxId"));

		return map;
	}
}