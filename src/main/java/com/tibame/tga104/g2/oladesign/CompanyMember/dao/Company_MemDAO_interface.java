package com.tibame.tga104.g2.oladesign.CompanyMember.dao;

import java.util.List;

import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;

public interface Company_MemDAO_interface {

	public void insert(Company_MemVO company_memVO);
	public void update(Company_MemVO company_memVO);
	public void updateforshop(Company_MemVO company_memVO);
	public Company_MemVO findByMemId(Integer memId);
	public Company_MemVO findByPrimaryKey(String company_memno);
	public List<Company_MemVO> getAll();
	
	// public List<Company_MemVO> getAll(Map<String, String[]> map);
//	public void delete(String company_memno);

}