package com.tibame.tga104.g2.oladesign.favorite.dao;

import java.util.List;
import java.util.Map;

import com.tibame.tga104.g2.oladesign.favorite.bean.FavorVO;

public interface FavorDAO {
	public Map<Integer, Integer> insert(FavorVO favorVO);
	
	public void delete(Integer memId, Integer prodId);
	
	public List<FavorVO> getOneMemAll(Integer memId);
}
