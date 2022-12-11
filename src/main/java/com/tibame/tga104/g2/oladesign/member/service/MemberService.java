package com.tibame.tga104.g2.oladesign.member.service;

import java.sql.Date;
import java.util.List;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAOImpl;



public class MemberService {
	private MemberDAO dao;
	private boolean checkMail = true;
	
	public boolean isCheckMail() {
		return checkMail;
	}
	
	public MemberService() {
		dao = new MemberDAOImpl();
	}
	
	public MemberVO addMember(String memName, String account, String password, String memPhone, 
			String memAddress, String sex, byte[] memPhoto) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemName(memName);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setMemPhone(memPhone);
		memberVO.setMemAddress(memAddress);
		memberVO.setSex(sex);
		memberVO.setMemPhoto(memPhoto);
		memberVO.setMemPhotoBase64(memPhoto); //不作為參數存進memberVO
		dao.insert(memberVO);
		if(!dao.isCheckMail()) {
			this.checkMail = false;
			System.out.println("mail已經存在");
		}

		return memberVO;
	}
	
	public MemberVO updateMember(Integer memId, String memName, String account, String password, String memPhone, 
			String memAddress, Date memRegdate, String sex, Integer point, boolean isBan, boolean isCom, byte[] memPhoto) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemId(memId);
		memberVO.setMemName(memName);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setMemPhone(memPhone);
		memberVO.setMemAddress(memAddress);
		memberVO.setMemRegdate(memRegdate);
		memberVO.setSex(sex);
		memberVO.setPoint(point);
		memberVO.setBan(isBan);
		memberVO.setCom(isCom);
		memberVO.setMemPhoto(memPhoto);
		memberVO.setMemPhotoBase64(memPhoto); //不作為參數存進memberVO
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public void delMember(Integer memId) {
		dao.delete(memId);
	}
	
	public MemberVO getOneMember(Integer memId) {
		return dao.findByPrimaryKey(memId);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
	
	public MemberVO memberLogin(String inputAccount, String inputPassword) {		
		return dao.login(inputAccount, inputPassword);
	}
	
	public void activeMember(String account) {
		dao.activeMember(account);
	}
}
