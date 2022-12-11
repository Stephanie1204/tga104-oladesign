package com.tibame.tga104.g2.oladesign.CompanyCommon;

public class MemberCheckService {
	private MemberCheckDAO_interface dao;

	public MemberCheckService() {
		dao = new MemberCheckJDBCDAO();
	}

	public Boolean doCheckMemberHasCom(String memId) {
		return dao.doCheckMemberHasCom(memId);
	}
}