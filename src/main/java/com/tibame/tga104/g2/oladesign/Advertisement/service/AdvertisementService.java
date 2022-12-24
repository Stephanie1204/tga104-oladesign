package com.tibame.tga104.g2.oladesign.Advertisement.service;

import java.util.List;

import com.tibame.tga104.g2.oladesign.Advertisement.dao.AdvertisementDAO_interface;
import com.tibame.tga104.g2.oladesign.Advertisement.dao.AdvertisementJDBCDAO;
import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementVO;
import com.tibame.tga104.g2.oladesign.CompanyCommon.SeqDAO_interface;
import com.tibame.tga104.g2.oladesign.CompanyCommon.SeqJDBCDAO;

public class AdvertisementService {

	private AdvertisementDAO_interface dao;
	private SeqDAO_interface seqDao;

	public AdvertisementService() {
		dao = new AdvertisementJDBCDAO();
		seqDao = new SeqJDBCDAO();
	}

	// 確認廣告時段是否額滿
	public Boolean getIsInsertAble(AdvertisementVO advertisementVO) {
		return dao.getIsInsertAble(advertisementVO);
	}

	// 單一查詢:確認該廠商是否有廣告
	public List<AdvertisementVO> ADRecordByComtaxId(String comTaxId) {
		return dao.ADRecordByComtaxId(comTaxId);
	}

	// 單一查詢
	public AdvertisementVO getOneAdvertisement(String advertisementno) {
		return dao.findByPrimaryKey(advertisementno);
	}

	// 單一查詢該筆廣告資料的狀態
	public AdvertisementVO getAdStatus(String adId) {
		return dao.getAdStatus(adId);
	}
	
	// 抓取今日廣告項目
	public List<AdvertisementVO> getTodayAD() {
		return dao.getTodayAD();
	}
		
	// 新增
	public String addAdvertisement(AdvertisementVO advertisementVO) {
		String adId = seqDao.getOneSeq("AD");
		advertisementVO.setAdId(adId);

		dao.insert(advertisementVO);
		return adId;
	}

	// 修改-管理員審核
	public void updateAdStatus(String adId) {
		dao.updateAdStatus(adId);
	}

	// 多筆查詢
	public List<AdvertisementVO> getAll() {
		return dao.getAll();
	}
}
