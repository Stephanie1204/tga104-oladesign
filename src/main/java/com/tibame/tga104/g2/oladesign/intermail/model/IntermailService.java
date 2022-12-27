package com.tibame.tga104.g2.oladesign.intermail.model;

import java.util.List;



public class IntermailService {
private static final Boolean IsSend = null;
private IntermailDAO_interface dao;
	
	
	public IntermailService() {
		dao = new IntermailJDBCDAO();
	}
	
//	public IntermailVO addIntermail(Integer interMailId, Integer memId,String adminId,String numQue,String conTent) {
	public IntermailVO addIntermail( Integer memId,String adminId,String numQue,String conTent) {
		IntermailVO intermailVO = new IntermailVO();

//		intermailVO.setInterMailId(interMailId);
		intermailVO.setMemId(memId);
		intermailVO.setAdminId(adminId);
		intermailVO.setNumQue(numQue);
		intermailVO.setConTent(conTent);
		dao.insert(intermailVO);

		return intermailVO;
	}
	
//	public IntermailVO addmemIntermail(Integer interMailId, Integer memId,String adminId,String numQue,String conTent) {
	public Boolean addmemIntermail( Integer memId,String adminId,String numQue,String conTent) {

		IntermailVO intermailVO = new IntermailVO();

//		intermailVO.setInterMailId(interMailId);
		intermailVO.setMemId(memId);
		intermailVO.setAdminId(adminId);
		intermailVO.setNumQue(numQue);
		intermailVO.setConTent(conTent);
		
		if((dao.meminsert(intermailVO))==1) {
			return true;
		}else {
			return false;
		}
		
	}

//	public IntermailVO updateIntermail(Integer interMailId, Integer memId,String adminId,String numQue,String conTent) {
	public IntermailVO updateIntermail( Integer memId,String adminId,String numQue,String conTent) {

		IntermailVO intermailVO = new IntermailVO();

//		intermailVO.setInterMailId(interMailId);
		intermailVO.setMemId(memId);
		intermailVO.setAdminId(adminId);
		intermailVO.setNumQue(numQue);
		intermailVO.setConTent(conTent);
		dao.update(intermailVO);

		return intermailVO;
	}

	public void deleteIntermail(Integer interMailId) {
		dao.delete(interMailId);
	}
	public void deleteMemIntermail(Integer interMailId) {
		dao.Memdelete(interMailId);
	}

	public IntermailVO getOneIntermail(Integer interMailId) {
		return dao.findByPrimaryKey(interMailId);
	}

	public List<IntermailVO> getAll() {
		return dao.getAll();
	}
	
	public List<IntermailVO> getReceive() {
		return dao.getReceive();
	}
	
	public List<IntermailVO> getMemReceive(Integer memId) {
		return dao.getMemReceive(memId);
	}

	public IntermailVO getCheck(Integer interMailId) {
		return dao.getCheck(interMailId);
	}
	
	public IntermailVO getCheckAll(Integer interMailId) {
		return dao.getCheckAll(interMailId);
	}
	
	public IntermailVO getMemCheck(Integer interMailId) {
		return dao.getMemCheck(interMailId);
	}
	
	public IntermailVO getReply(Integer interMailId, Boolean isSend,Integer memId, String adminId,Boolean isReply) {
		
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
