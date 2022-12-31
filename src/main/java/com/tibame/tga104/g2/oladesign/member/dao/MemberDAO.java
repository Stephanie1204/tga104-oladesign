package com.tibame.tga104.g2.oladesign.member.dao;

import java.util.List;

import com.tibame.tga104.g2.oladesign.intermail.model.IntermailVO;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;



public interface MemberDAO {
	public Integer insert(MemberVO memberVO);
	
	public void update(MemberVO memberVO);
	
	public void delete(Integer memId);
	
	public MemberVO findByPrimaryKey(Integer memId);
	
	public MemberVO findByEmail(String account);
	
	public List<MemberVO>getAll();
	
	public MemberVO login(String inputAccount, String inputPassword);
	
	public Boolean isCheckMail();
	
	public void activeMember(Integer memId, Boolean isActive);
	
	public void resetPWD(String newPassword, Integer memId);
	
	public void regComTag(Integer memId, Boolean isRegCom);

	public MemberVO getCheckOne(Integer memId);
	
//	public void getBan(MemberVO memberVO);
	
	public void getBan(Integer memId,Boolean isBan);
	
	public void getUnBan(Integer memId,Boolean isBan);
}
