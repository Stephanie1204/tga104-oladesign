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

    public Boolean getIsInsertAble(AdvertisementVO advertisementVO) {
	return dao.getIsInsertAble(advertisementVO);
    }
    
	// 單一查詢:確認該廠商是否有廣告
//	public AdvertisementVO doGetADByCom(String comtaxId) {
//		return dao.findByComtaxId(comtaxId);
//	}

 // 單一查詢:確認該廠商是否有廣告
    public List<AdvertisementVO> ADRecordByComtaxId(String comTaxId) {
	return dao.ADRecordByComtaxId(comTaxId);
    }
    
    // 新增
    public String addAdvertisement(AdvertisementVO advertisementVO) {
	String adId = seqDao.getOneSeq("AD");
	advertisementVO.setAdId(adId);

	dao.insert(advertisementVO);

	return adId;
    }

//
//    // 修改(不含開始&結束日期)
//    public AdvertisementVO updateAdvertisement(Integer adid, String comtaxid, byte[] adimages, String storelink,
//	    Boolean adstatus) {
//	AdvertisementVO advertisementVO = new AdvertisementVO();
//	advertisementVO.setAdId(adid);
//	advertisementVO.setComTaxId(comtaxid);
//	advertisementVO.setAdImages(adimages);
//	advertisementVO.setStoreLink(storelink);
//	advertisementVO.setAdStatus(adstatus);
//	dao.update(advertisementVO);
//	return advertisementVO;
//
//    }
//
//    // 刪除
//    public void deleteAdvertisement(Integer advertisementno) {
//	dao.delete(advertisementno);
//    }
//    
    // 單一查詢
    public AdvertisementVO getOneAdvertisement(String advertisementno) {
	return dao.findByPrimaryKey(advertisementno);
    }

    // 多筆查詢
    public List<AdvertisementVO> getAll() {
	return dao.getAll();
    }
}
