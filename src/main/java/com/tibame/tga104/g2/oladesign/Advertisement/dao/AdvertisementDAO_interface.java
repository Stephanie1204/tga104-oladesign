package com.tibame.tga104.g2.oladesign.Advertisement.dao;

import java.util.List;

import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementVO;

public interface AdvertisementDAO_interface {

    public Boolean getIsInsertAble(AdvertisementVO advertisementVO);

    public void insert(AdvertisementVO advertisementVO);
//
//    public void update(AdvertisementVO advertisementVO);
//
//    public void delete(Integer advertisementno);

    public AdvertisementVO findByPrimaryKey(String advertisementno);

    public List<AdvertisementVO> getAll();
}