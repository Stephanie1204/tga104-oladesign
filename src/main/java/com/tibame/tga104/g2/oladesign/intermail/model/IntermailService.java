package com.tibame.tga104.g2.oladesign.intermail.model;

import java.util.List;



public class IntermailService {
private static final Boolean IsSend = null;
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
	
	public IntermailVO addmemIntermail(String interMailId, Integer memId,String adminId,String numQue,String conTent) {

		IntermailVO intermailVO = new IntermailVO();

		intermailVO.setInterMailId(interMailId);
		intermailVO.setMemId(memId);
		intermailVO.setAdminId(adminId);
		intermailVO.setNumQue(numQue);
		intermailVO.setConTent(conTent);
		dao.meminsert(intermailVO);

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
	
	public List<IntermailVO> getReceive() {
		return dao.getReceive();
	}
	
	public List<IntermailVO> getMemReceive() {
		return dao.getMemReceive();
	}

	public IntermailVO getCheck(String interMailId) {
		return dao.getCheck(interMailId);
	}
	
	public IntermailVO getMemCheck(String interMailId) {
		return dao.getMemCheck(interMailId);
	}
	
	public IntermailVO getReply(String interMailId, Boolean isSend,Integer memId, String adminId,Boolean isReply) {
		
		IntermailVO intermailVO = new IntermailVO();
		intermailVO.setIsSend(isSend);
		intermailVO.setInterMailId(interMailId);
		intermailVO.setMemId(memId);
		intermailVO.setAdminId(adminId);
		intermailVO.setIsReply(isReply);
		dao.getReply(intermailVO);
		return intermailVO;
	}
//	public IntermailVO getReply(String interMailId,Boolean isSend) {
//		return dao.getReply(interMailId, isSend);
//
//}
}
