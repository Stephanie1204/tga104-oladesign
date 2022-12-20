package com.tibame.tga104.g2.oladesign.Advertisement.dao;

import java.util.List;

import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementVO;

public interface AdvertisementDAO_interface {

    public Boolean getIsInsertAble(AdvertisementVO advertisementVO);
    public void insert(AdvertisementVO advertisementVO);
    public AdvertisementVO findByPrimaryKey(String advertisementno);   
    public List<AdvertisementVO> ADRecordByComtaxId(String comTaxId);
    public List<AdvertisementVO> getAll();
//  public void update(AdvertisementVO advertisementVO);
//  public void delete(Integer advertisementno);
}