package com.tibame.tga104.g2.oladesign.member.helper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class test {
	public static void main(String[] args){
		SendMail sendFirstMail = new SendMail();
		sendFirstMail.sendComMemMail("tga104g2@gmail.com", "測試");
	}
}
