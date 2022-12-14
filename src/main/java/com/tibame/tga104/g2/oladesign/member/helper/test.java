package com.tibame.tga104.g2.oladesign.member.helper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String password = "123456";
		//將密碼經SHA256運算
		MessageDigest md;
		HexBinaryAdapter hba;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes("UTF-8"));
			hba = new HexBinaryAdapter();
			String playerHash = hba.marshal(hash);
			System.out.println(playerHash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
