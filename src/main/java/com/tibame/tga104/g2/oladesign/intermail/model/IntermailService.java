package com.tibame.tga104.g2.oladesign.intermail.model;

import java.util.List;



public class IntermailService {
private IntermailDAO_interface dao;
	
	
	public IntermailService() {
		dao = new IntermailJDBCDAO();
	}
	
	public IntermailVO addIntermail(String interMailId, Integer memId,String adminId,String numQue,String conTent) {

		IntermailVO intermailVO = new IntermailVO();

		intermailVO.setInterMailId(interMailId);
		intermailVO.setMemId(memId);
		intermailVO.setAdminId(adminId);
		intermailVO.setNumQue(numQue);
		intermailVO.setConTent(conTent);
		dao.insert(intermailVO);

		return intermailVO;
	}

	public IntermailVO updateIntermail(String interMailId, Integer memId,String adminId,String numQue,String conTent) {

		IntermailVO intermailVO = new IntermailVO();

		intermailVO.setInterMailId(interMailId);
		intermailVO.setMemId(memId);
		intermailVO.setAdminId(adminId);
		intermailVO.setNumQue(numQue);
		intermailVO.setConTent(conTent);
		dao.update(intermailVO);

		return intermailVO;
	}

	public void deleteIntermail(String interMailId) {
		dao.delete(interMailId);
	}

	public IntermailVO getOneIntermail(String interMailId) {
		return dao.findByPrimaryKey(interMailId);
	}

	public List<IntermailVO> getAll() {
		return dao.getAll();
	}
}
