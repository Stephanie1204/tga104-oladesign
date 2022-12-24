package com.tibame.tga104.g2.oladesign.intermail.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.intermail.model.IntermailService;

@RestController
@CrossOrigin
public class InterMailController {
	
	IntermailService service = new IntermailService();
	
	@GetMapping("/interMail")
	public void getAll() {
		service.getMemReceive();
	}

}
