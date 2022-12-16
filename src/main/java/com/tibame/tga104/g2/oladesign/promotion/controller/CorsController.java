package com.tibame.tga104.g2.oladesign.promotion.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CorsController {

	@RequestMapping("/person")
	public Map<String, String> get() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "vic");
		map.put("gender", "male");
		
		System.out.println("我怎麼跟某人一樣笨?");
		return map;
	}
}