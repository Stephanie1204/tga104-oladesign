package com.tibame.tga104.g2.oladesign.CompanyMember.service;

import java.util.List;

import com.tibame.tga104.g2.oladesign.CompanyMember.dao.Company_MemDAO_interface;
import com.tibame.tga104.g2.oladesign.CompanyMember.dao.Company_MemJDBCDAO;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;

public class Company_MemService {

	private Company_MemDAO_interface dao;

	public Company_MemService() {
		dao = new Company_MemJDBCDAO();
	}

	// 新增廠商資料
	public Company_MemVO addCompany_Mem(String comtaxid, Integer memid, String comname, String comaddress,
			String comphone, String comowner, String ownerphone, String comBankaccount) {
		Company_MemVO company_memVO = new Company_MemVO();
		company_memVO.setComTaxId(comtaxid);
		company_memVO.setMemId(memid);
		company_memVO.setComName(comname);
		company_memVO.setComAddress(comaddress);
		company_memVO.setComPhone(comphone);
		company_memVO.setComOwner(comowner);
		company_memVO.setOwnerPhone(ownerphone);
		company_memVO.setComBankaccount(comBankaccount);
		// company_memVO.setComRegdate(comregdate);
		dao.insert(company_memVO);
		return company_memVO;
	}

	// 單一查詢:確認該會員是否已開通廠商
	public Company_MemVO doGetCompanyMemByMemId(Integer memId) {
		return dao.findByMemId(memId);
	}

	// 修改廠商資料
	public Company_MemVO updateCompany_Mem(String comtaxid, Integer memid, String comname, String comaddress,
			String comphone, String comowner, String ownerphone, String comBankaccount) {
		Company_MemVO company_memVO = new Company_MemVO();
		company_memVO.setComTaxId(comtaxid);
		company_memVO.setMemId(memid);
		company_memVO.setComName(comname);
		company_memVO.setComAddress(comaddress);
		company_memVO.setComPhone(comphone);
		company_memVO.setComOwner(comowner);
		company_memVO.setOwnerPhone(ownerphone);
		company_memVO.setComBankaccount(comBankaccount);
		dao.update(company_memVO);
		return company_memVO;

	}

	// 修改賣家介紹
	public Company_MemVO updateforshop(String comtaxid, String storename, String storeintro, byte[] storelogo,
			byte[] storebanner) {
		Company_MemVO company_memVO = new Company_MemVO();
		company_memVO.setComTaxId(comtaxid);
		company_memVO.setStoreName(storename);
		company_memVO.setStoreIntro(storeintro);
		company_memVO.setStoreLogo(storelogo);
		company_memVO.setStoreBanner(storebanner);
		dao.updateforshop(company_memVO);
		return company_memVO;

	}

	// 單一查詢
	public Company_MemVO getOneCompany_Mem(String comtaxid) {
		return dao.findByPrimaryKey(comtaxid);
	}

	// 多筆查詢
	public List<Company_MemVO> getAll() {
		return dao.getAll();
	}

	public Integer doGetMemIdByComTaxId(String comTaxId) {
		return dao.doGetMemIdByComTaxId(comTaxId);
	}
}