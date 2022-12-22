package com.tibame.tga104.g2.oladesign.member.service;

import com.tibame.tga104.g2.oladesign.member.dao.MemberIsComDAO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberIsComJDBCDAO;

public class MemberIsComService {

	private MemberIsComDAO dao;
	public MemberIsComService() {
		dao = new MemberIsComJDBCDAO();
	}
	
	
	// 修改-管理員審核
	public void updateIsCom(Integer memId) {
		dao.updateIsCom(memId);
	}
	
	
}
