package com.tibame.tga104.g2.oladesign.intermail.model;
import java.util.List;


public class Intermail_qnService {
	private Intermail_qnDAO_interface dao;
	
	
	public Intermail_qnService() {
		dao = new Intermail_qnJDBCDAO();
	}
	
	public Intermail_qnVO addIntermail_qn(String numQue, String type) {

		Intermail_qnVO intermail_qnVO = new Intermail_qnVO();

		intermail_qnVO.setNumQue(numQue);
		intermail_qnVO.setType(type);
		dao.insert(intermail_qnVO);

		return intermail_qnVO;
	}

	public Intermail_qnVO updateIntermail_qn(String numQue, String type) {

		Intermail_qnVO intermail_qnVO = new Intermail_qnVO();

		intermail_qnVO.setNumQue(numQue);
		intermail_qnVO.setType(type);
		dao.update(intermail_qnVO);

		return intermail_qnVO;
	}

	public void deleteIntermail_qn(String numQue) {
		dao.delete(numQue);
	}

	public Intermail_qnVO getOneIntermail_qn(String numQue) {
		return dao.findByPrimaryKey(numQue);
	}

	public List<Intermail_qnVO> getAll() {
		return dao.getAll();
	}
	public List<Intermail_qnVO> getType() {
		return dao.getType();
	}
}
