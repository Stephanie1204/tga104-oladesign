package com.tibame.tga104.g2.oladesign.member.service;

import java.sql.Date;
import java.util.List;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAOImpl;



public class MemberService {
	private MemberDAO dao;
	private boolean checkMail = true;
	
	public Boolean isCheckMail() { //註冊時不能輸入已存在的mail
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
		Integer memId = dao.insert(memberVO);
		memberVO.setMemId(memId);
		if(!dao.isCheckMail()) { //若dao的isCheckMail為false，表示email已經存在
			this.checkMail = false; //使MemberService的checkMail也為false，controller呼叫service時也可由isCheckMail()判斷錯誤
			System.out.println("mail已經存在");
		}

		return memberVO;
	}
	
//	public MemberVO updateMember(Integer memId, String memName, String account, String password, String memPhone, 
//			String memAddress, Date memRegdate, String sex, Integer point, boolean isBan, boolean isCom, byte[] memPhoto) {
//		
//		MemberVO memberVO = new MemberVO();
//		
//		memberVO.setMemId(memId);
//		memberVO.setMemName(memName);
//		memberVO.setAccount(account);
//		memberVO.setPassword(password);
//		memberVO.setMemPhone(memPhone);
//		memberVO.setMemAddress(memAddress);
//		memberVO.setMemRegdate(memRegdate);
//		memberVO.setSex(sex);
//		memberVO.setPoint(point);
//		memberVO.setBan(isBan);
//		memberVO.setCom(isCom);
//		memberVO.setMemPhoto(memPhoto);
//		memberVO.setMemPhotoBase64(memPhoto); //不作為參數存進memberVO
//		dao.update(memberVO);
//		
//		return memberVO;
//	}
	
	public MemberVO updateMember(MemberVO vo) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemId(vo.getMemId());
		memberVO.setMemName(vo.getMemName());
		memberVO.setAccount(vo.getAccount());
		memberVO.setPassword(vo.getPassword());
		memberVO.setMemPhone(vo.getMemPhone());
		memberVO.setMemAddress(vo.getMemAddress());
		memberVO.setMemRegdate(vo.getMemRegdate());
		memberVO.setSex(vo.getSex());
		memberVO.setPoint(vo.getPoint());
		memberVO.setBan(vo.isBan());
		memberVO.setCom(vo.isCom());
		memberVO.setIsRegCom(vo.getIsRegCom());
		memberVO.setMemPhoto(vo.getMemPhoto());
		memberVO.setMemPhotoBase64(vo.getMemPhoto()); //不作為參數存進memberVO
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public void delMember(Integer memId) {
		dao.delete(memId);
	}
	
	public MemberVO getOneMember(Integer memId) {
		return dao.findByPrimaryKey(memId);
	}
	
	public MemberVO getOneMemberByEmail(String account) {
		return dao.findByEmail(account);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
	
	public MemberVO memberLogin(String inputAccount, String inputPassword) {		
		return dao.login(inputAccount, inputPassword);
	}
	
	public void activeMember(Integer memId, Boolean isActive) {
		dao.activeMember(memId, isActive);
	}
	
	public void resetMemberPWD(String newPassword,Integer memId) {
		dao.resetPWD(newPassword, memId);
	}
}
