package com.tibame.tga104.g2.oladesign.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.helper.EncryptUtils;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;
import com.tibame.tga104.g2.oladesign.order.model.OrderItemBean;

@RestController
@CrossOrigin
public class MemberController {

	static final String MEMBER_KEY = "memberVO";
	MemberService service = new MemberService();

//	@GetMapping("/member")
//	public MemberVO getVo(@SessionAttribute(name = MEMBER_KEY, required = true) MemberVO memberVO) {
//		return memberVO;
//	}

	@PutMapping("/member")
	public MemberVO putVO(@RequestBody MemberVO vo) {
		return service.updateMember(vo);
	}

	@GetMapping("/member")
	public MemberVO getVo(@RequestParam("memId") Integer memId) {
		return service.getOneMember(memId);
	}

	@PutMapping("/productReview")
	public Boolean addReview(@RequestBody OrderItemBean bean) {
		return service.addReview(bean);
	}

	@PutMapping("/member/password")
	public Boolean changePassword(@RequestParam("originPwd") String originPwd, @RequestParam("newPwd1") String newPwd1,
			@RequestParam("newPwd2") String newPwd2, HttpSession session) {
		MemberVO vo = (MemberVO) session.getAttribute("memberVO");

		// compare
		String pwd = vo.getPassword();
		String inputPWS = EncryptUtils.encrypt(originPwd);
		if (!pwd.equals(inputPWS) || !newPwd1.equals(newPwd2)) {
			return false;
		}

		// update
		vo.setPassword(EncryptUtils.encrypt(newPwd1));
		service.updateMember(vo);
		session.removeAttribute("memberVO");
		return true;
	}

}
