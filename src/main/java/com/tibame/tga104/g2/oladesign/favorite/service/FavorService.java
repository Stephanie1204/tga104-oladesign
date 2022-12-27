package com.tibame.tga104.g2.oladesign.favorite.service;

import java.util.List;

import com.tibame.tga104.g2.oladesign.favorite.bean.FavorVO;
import com.tibame.tga104.g2.oladesign.favorite.dao.FavorDAO;
import com.tibame.tga104.g2.oladesign.favorite.dao.FavorDAOImpl;

public class FavorService {
	private FavorDAO favorDAO;
	public FavorService() {
		favorDAO = new FavorDAOImpl();
	}
	
	public boolean addFavor(FavorVO favorVO) {
//		接收請求參數
		Integer memId = null;
		if(favorVO.getMemId() != null) {
			memId = Integer.valueOf(favorVO.getMemId());
		}else {
			return false;
		}
		
		Integer prodId = null;
		if(favorVO.getProductId() != null) {
			prodId = Integer.valueOf(favorVO.getProductId());
		}else {
			return false;
		}
//		加入收藏
		favorDAO.insert(favorVO);
		return true;
	}
	
	public boolean delFavor(FavorVO favorVO) {
//		接收請求參數
		Integer memId = null;
		if(favorVO.getMemId() != null) {
			memId = Integer.valueOf(favorVO.getMemId());
		}else {
			return false;
		}
		
		Integer prodId = null;
		if(favorVO.getProductId() != null) {
			prodId = Integer.valueOf(favorVO.getProductId());
		}else {
			return false;
		}
//		移除收藏
		favorDAO.delete(memId, prodId);
		return true;
	}
	
	public List<FavorVO> getOneAllFavor(FavorVO favorVO) {
		List<FavorVO> favorList = null;
		if(favorVO != null) {
			favorList = favorDAO.getOneMemAll(favorVO.getMemId());
		}
		return favorList;
	}
}
