package com.tibame.tga104.g2.oladesign.CompanyMember.service;

import java.util.HashMap;
import java.util.Map;

import com.tibame.tga104.g2.oladesign.CompanyMember.dao.Company_MemDAO_interface;
import com.tibame.tga104.g2.oladesign.CompanyMember.dao.Company_MemJDBCDAO;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAOImpl;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;

public class ComMemRegistService {
	private Company_MemDAO_interface comMemDAO;
	private MemberDAO memberDAO;
	public ComMemRegistService() {
		comMemDAO = new Company_MemJDBCDAO();
		memberDAO = new MemberDAOImpl();
	}
	
	public Object comMemRegist(Company_MemVO comMemVO) {
//		錯誤訊息提示
		Map<String, String> errorMsgs = new HashMap<String, String>(); 
		
//		接收請求參數		
		Integer	memId = Integer.valueOf(comMemVO.getMemId());
		MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.getOneMember(memId);		
		if(memberVO.getIsCom()) {
			errorMsgs.put("memName", "會員已具有賣家資格，請勿重複註冊");
		}
		
		if(memberVO.getIsRegCom()) {
			errorMsgs.put("memName", "已註冊申請賣家資格，請耐心等待審核");
		}
		
		String comTaxid = comMemVO.getComTaxId();
		String comTaxidReg = "^[0-9]{8}$";
		if (comTaxid == null || comTaxid.trim().length() == 0) {
			errorMsgs.put("comTaxid", "公司統編請勿空白");
		} else if (!comTaxid.trim().matches(comTaxidReg)) {
			errorMsgs.put("comTaxid", "需為數字,且長度為8碼");
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
		
		
		String comAddress = comMemVO.getComAddress();
		String zipcode = comAddress.substring(0, 3);
		System.out.println("zipcode"+zipcode);
		String zipcodeReg = "^\\d{3}$";
		if (comAddress == null || comAddress.trim().length() == 0) {
			errorMsgs.put("comAddress", "公司地址請勿空白");
		}else if (!zipcode.matches(zipcodeReg)) {
			errorMsgs.put("comAddress", "請選擇縣市與行政區");
		}

		if(!errorMsgs.isEmpty()) {
			return errorMsgs;
		}
		comMemDAO.insert(comMemVO);
		memberDAO.regComTag(memId, true); //設定已經註冊賣家的標記等待審核，審核不通過的話改回false
		return comMemVO;
	}
}
