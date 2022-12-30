package com.tibame.tga104.g2.oladesign.member.helper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class EncryptUtils {

	public static String encrypt(String text) {
		if (text == null || text.isBlank()) {
			return text;
		}

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// digest方法參數為byte[]
			byte[] hash = md.digest(text.getBytes(Charset.forName("UTF-8"))); // 將密碼字串以UTF-8的方式轉換為位元組陣列
			HexBinaryAdapter hba = new HexBinaryAdapter();
			text = hba.marshal(hash); // 將byte[] hash轉換為字串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return text;
	}

}
