package com.tibame.tga104.g2.oladesign.CompanyMember.service;

import java.util.HashMap;
import java.util.Map;

import com.tibame.tga104.g2.oladesign.CompanyMember.dao.Company_MemDAO_interface;
import com.tibame.tga104.g2.oladesign.CompanyMember.dao.Company_MemJDBCDAO;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;

public class ComMemRegistService {
	private Company_MemDAO_interface comMemDAO;
	public ComMemRegistService() {
		comMemDAO = new Company_MemJDBCDAO();
	}
	
	public Object comMemRegist(Company_MemVO comMemVO) {
//		錯誤訊息提示
		Map<String, String> errorMsgs = new HashMap<String, String>(); 
		
//		接收請求參數		
		Integer	memId = Integer.valueOf(comMemVO.getMemId());
		MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.getOneMember(memId);		
		if(memberVO.isCom()) {
			errorMsgs.put("memName", "會員已具有賣家資格，請勿重複註冊");
		}
		
		String comTaxid = comMemVO.getComTaxId();
		String comTaxidReg = "^[0-9]{8}$";
		if (comTaxid == null || comTaxid.trim().length() == 0) {
			errorMsgs.put("comTaxid", "公司統編請勿空白");
		} else if (!comTaxid.trim().matches(comTaxidReg)) {
			errorMsgs.put("comTaxid", "需為數字,且長度為8碼");
		}
		
		if(!memberVO.isCom() && comTaxid != null) {
			errorMsgs.put("comTaxid", "已經註冊賣家，請耐心等待審核結果");
		}
		
		String comName = comMemVO.getComName();
		String comNameReg = "^[(\u4e00-\\u9fa5)(a-zA-Z)]{2,50}$";
		if (comName == null || comName.trim().length() == 0) {
			errorMsgs.put("comName", "公司名稱請勿空白");
		} else if (!comName.trim().matches(comNameReg)) {
			errorMsgs.put("comName", "只能是中、英文,且長度需在2-50之間");
		}
		
		String comPhone = comMemVO.getComPhone();
		String comPhoneReg = "^(\\d{2,3}-?|\\d{2,3})\\d{3,4}-?\\d{4}$";
		if (comPhone == null || comPhone.trim().length() == 0) {
			errorMsgs.put("comPhone", "公司電話請勿空白");
		} else if (!comPhone.trim().matches(comPhoneReg)) {
			errorMsgs.put("comPhone", "公司電話：需有區碼-");
		}
		
		String comOwner = comMemVO.getComOwner();
		String comOwnerReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
		if (comOwner == null || comOwner.trim().length() == 0) {
			errorMsgs.put("comOwner", "負責人姓名請勿空白");
		} else if (!comOwner.trim().matches(comOwnerReg)) {
			errorMsgs.put("comOwner", "只能是中、英文,且長度需在2-10之間");
		}
		
		String ownerPhone = comMemVO.getOwnerPhone();
		String ownerPhoneReg = "^09\\d{8}$";
		if (ownerPhone == null || ownerPhone.trim().length() == 0) {
			errorMsgs.put("ownerPhone", "負責人手機號碼請勿空白");
		} else if (!ownerPhone.trim().matches(ownerPhoneReg)) {
			errorMsgs.put("ownerPhone", "請填正確手機格式");
		}
		
		//待新增VO的zipcode, city, town
		String comAddress = comMemVO.getComAddress();
		if (comAddress == null || comAddress.trim().length() == 0) {
			errorMsgs.put("comAddress", "公司地址請勿空白");
		}
		
		if(!errorMsgs.isEmpty()) {
			return errorMsgs;
		}
		comMemDAO.insert(comMemVO);
		return comMemVO;
	}
}
