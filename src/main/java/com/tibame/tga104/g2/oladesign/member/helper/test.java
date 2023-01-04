package com.tibame.tga104.g2.oladesign.member.helper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class test {
	public static void main(String[] args){
//		SendMail sendFirstMail = new SendMail();
//		sendFirstMail.sendComMemMail("tga104g2@gmail.com", "測試");
		MessageDigest md;
		HexBinaryAdapter hba;
		String password = "1234501";
		String passwordKey = null;
		try {
			
			md = MessageDigest.getInstance("SHA-256");
			// digest方法參數為byte[]
			byte[] hash = md.digest(password.getBytes(Charset.forName("UTF-8"))); // 將密碼字串以UTF-8的方式轉換為位元組陣列
			hba = new HexBinaryAdapter();
			passwordKey = hba.marshal(hash); // 將byte[] hash轉換為字串
			System.out.println(passwordKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
