package com.tibame.tga104.g2.oladesign.admin.model;

import java.util.List;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;

public class AdminService {

	private AdminDAO_interface dao;

	public AdminService() {
		dao = new AdminJDBCDAO();
	}
	
	public AdminVO addAdmin(String adminId, String adminName, 
			String account, String password) {

		AdminVO adminVO = new AdminVO();

		adminVO.setAdminId(adminId);
		adminVO.setAdminName(adminName);
		adminVO.setAccount(account);
		adminVO.setPassword(password);
		dao.insert(adminVO);

		return adminVO;
	}

	public AdminVO updateAdmin(String adminId, String adminName, 
			String account, String password) {

		AdminVO adminVO = new AdminVO();

		adminVO.setAdminId(adminId);
		adminVO.setAdminName(adminName);
		adminVO.setAccount(account);
		adminVO.setPassword(password);
		dao.update(adminVO);

		return adminVO;
	}

	public void deleteAdmin(String adminId) {
		dao.delete(adminId);
	}

	public AdminVO getOneAdmin(String adminId) {
		return dao.findByPrimaryKey(adminId);
	}

	public List<AdminVO> getAll() {
		return dao.getAll();
	}
	
//	public AdminVO getAdmin (String account , String password) {
//		return dao.getAdmin(account,password);
//	}
//	public AdminVO getAdmin (String admin_acc , String admin_pwd) {
//		return dao.getAdmin(admin_acc,admin_pwd);
//	}
	
	public AdminVO adminLogin(String account, String password) {		
		return dao.login(account, password);
	}
}