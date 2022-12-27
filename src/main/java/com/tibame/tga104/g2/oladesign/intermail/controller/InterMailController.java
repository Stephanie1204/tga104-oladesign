package com.tibame.tga104.g2.oladesign.intermail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.intermail.model.IntermailService;
import com.tibame.tga104.g2.oladesign.intermail.model.IntermailVO;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;

@RestController
@CrossOrigin
public class InterMailController {
	
	IntermailService service = new IntermailService();
	
	@GetMapping("/interMail")
	public List<IntermailVO> getAll(@RequestParam("memId") Integer memId) {
		return service.getMemReceive(memId);
	}
	
	@GetMapping("/interMail/:id")
	public IntermailVO getOneInterMail(@RequestParam("interMailId")Integer interMailId) {
		return service.getMemCheck(interMailId);
	}
	
	@PostMapping("/interMail")
	public Boolean addInterMail(@RequestParam Integer memId,String numQue,String conTent) {
		return service.addmemIntermail(memId, "A001", numQue, conTent);
		
	}
	

}
