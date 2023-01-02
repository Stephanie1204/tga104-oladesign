package com.tibame.tga104.g2.oladesign.member.service;

import java.util.List;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAOImpl;
import com.tibame.tga104.g2.oladesign.order.model.OrderItemBean;
import com.tibame.tga104.g2.oladesign.order.model.OrderItemDAOJdbc;

public class MemberService {
	private MemberDAO dao;
	private boolean checkMail = true;

	public Boolean isCheckMail() { // 註冊時不能輸入已存在的mail
		return checkMail;
	}

	public MemberService() {
		dao = new MemberDAOImpl();
	}

	public MemberVO addMember(String memName, String account, String password, String memPhone, String memAddress,
			String sex, byte[] memPhoto) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemName(memName);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setMemPhone(memPhone);
		memberVO.setMemAddress(memAddress);
		memberVO.setSex(sex);
		memberVO.setMemPhoto(memPhoto);
//		memberVO.setMemPhotoBase64(memPhoto); // 不作為參數存進memberVO
		Integer memId = dao.insert(memberVO);
		memberVO.setMemId(memId);
		if (!dao.isCheckMail()) { // 若dao的isCheckMail為false，表示email已經存在
			this.checkMail = false; // 使MemberService的checkMail也為false，controller呼叫service時也可由isCheckMail()判斷錯誤
			System.out.println("mail已經存在");
		}

		return memberVO;
	}

	public MemberVO updateMember(MemberVO vo) {
		dao.update(vo);
		return vo;
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

	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	public MemberVO memberLogin(String inputAccount, String inputPassword) {
		return dao.login(inputAccount, inputPassword);
	}

	public void activeMember(Integer memId, Boolean isActive) {
		dao.activeMember(memId, isActive);
	}

	public void resetMemberPWD(String newPassword, Integer memId) {
		dao.resetPWD(newPassword, memId);
	}

//<<<<<<< HEAD
	public MemberVO getCheckOne(Integer memId) {
		return dao.getCheckOne(memId);
	}

//	public MemberVO getBan(Integer memId,Boolean isBan) {
//		
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMemId(memId);
//		memberVO.setBan(isBan);
//		dao.getBan(memberVO);
//		return memberVO;
//	}

	public void getBan(Integer memId, Boolean isBan) {
		dao.getBan(memId, isBan);
	}
//<<<<<<< HEAD
	
	public void getUnBan(Integer memId,Boolean isBan) {
		dao.getUnBan(memId, isBan);
	}
//=======
//
//>>>>>>> dev
//=======
	public Boolean addReview(OrderItemBean bean) {
		if (new OrderItemDAOJdbc().update(bean) == 1) {
			return true;
		} else {
			return false;
		}

//>>>>>>> dev
	}
}
