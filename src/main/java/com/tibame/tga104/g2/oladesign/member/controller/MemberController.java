package com.tibame.tga104.g2.oladesign.member.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;
import javax.sound.midi.VoiceStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;

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
	public MemberVO getVo(Integer memId) {
		return service.getOneMember(220003);
	}

//	@PostMapping("/member/mock")
//	public void fakeVo(HttpSession session) {
//		MemberVO vo = new MemberVO(); // TODO
//		vo.setMemId(220001);
//		vo.setMemName("王承翰");
//		vo.setAccount("andy54@gmail.com");
//		vo.setPassword("password");
//		vo.setMemPhone("0995266655");
//		vo.setMemAddress("高雄市三民區鼎祥街115號");
//		vo.setMemRegdate(new Date(2022-12-19));
//		vo.setSex("M");
//		vo.setPoint(50);
//		vo.setBan(false);
//		vo.setCom(true);
//		vo.setActive(false);
////		vo.setMemPhoto(byte[] memPhoto);
//		session.setAttribute(MEMBER_KEY, vo);
//	}

}
