package com.tibame.tga104.g2.oladesign.member.helper;

import java.util.UUID;

import redis.clients.jedis.Jedis;

public class SendMail {
	
	public boolean sendAuthMail(Integer memId, String account, String memName) {
		Jedis jedis = null;
		try {
//			驗證碼產生
			jedis = new Jedis("localhost", 6379);	
			jedis.select(10);
			String uuId = UUID.randomUUID().toString();
			String emailToken = uuId.replace("-", "");				
			String strId = memId.toString();
			
			jedis.set(emailToken, strId);
			jedis.expire(emailToken, 2*60*60); //有效2小時
			
//			寄送驗證信
			String recipient = account;
			String subject = "OLA Design註冊驗證";
			String reci_name = memName;
			String url = "http://localhost:8080/oladesign/member/MemberMailServlet?emailToken=" + emailToken;
			String buttonLink = 
					  "<tr>"
					+ "  <td>"	
					+ "<a style='"
					+ "				 margin:0;"
					+ "              padding: 20px;"
					+ "				 background-color: #e4cd8d;"	
					+ "              border: none;"
					+ "              border-radius: 5px;"
					+ "              text-decoration: none;"
					+ "              color: #4f4e4d;"
					+ "              font-size: 1rem;"
					+ "              font-weight: bolder;"
					+ "            ' href=" + url + ">"		  
					+ "				驗證電子郵件</a>"
					+ "	 </td>"
					+ "	</tr>";
			String activeLink = 
					  "<tr>"
					+ "  <td>"
					+ "<a style='"
					+ "            text-decoration: none;"
					+ "            color: #92afbf;"
					+ "            font-size: 1rem;"
					+ "            font-weight: bolder;"
					+ "          ' href=" + url + ">" + url + "</a>"
					+ "	 </td>"
					+ "	</tr>";
			String messageText = 
			"<table style='background-color: #f3f1f2; width: 100%'>"
			+ "      <tbody>"
			+ "        <tr>"
			+ "          <td>"
			+ "            <table"
			+ "              style='"
			+ "				   width: 60%;"			
			+ "                background-color: white;"
			+ "                margin: 20%;"
			+ "                padding: 30px;"
			+ "                max-width: 540px;"
			+ "                margin-top: 50px;"
			+ " 			   margin-bottom: 50px;"
			+ "                border-radius: 12px;"
			+ "              '"
			+ "            >"
			+ "              <tbody style='text-align: center'>"
			+ "                <tr>"
			+ "                  <td>"			
			+ "						<h1 style='font-size: 2rem; color: #4f4e4d'>Olá! " + reci_name + ":</h1>"
			+ "					 </td>"
			+ "					</tr>"
			+ "                <tr>"
			+ "                  <td>"		
			+ "						<p style='font-size: 1rem; color: #4f4e4d'>僅需最後一步來啟用您的OLA Design帳號，請點選以下按鈕:</p><br> " + buttonLink + "<br><br>"
			+ "					 </td>"
			+ "					</tr>"
			+ "                <tr>"
			+ "                  <td>"
			+ "						<p style='text-align: center; font-size: 1rem; font-weight: bolder'>無法自動跳轉至網頁嗎?<br>請複製下列連結至您的瀏覽器網址列中:</p><br>" + activeLink + "<br>" 
			+ "					 </td>"
			+ "					</tr>"
			+ "                <tr>"
			+ "                  <td>"
			+ "						<p style='text-align: center; font-size: 1rem;'>祝您購物愉快!<br />OLA Design團隊 敬上</p></div>"
			+ "					 </td>"
			+ "					</tr>";

			MemberMailThread mailService = new MemberMailThread(recipient, subject, messageText);
			mailService.start();
			System.out.println("驗證信已發送");
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if(jedis != null) {
				jedis.close();
			}
			
		}		
		return true;
	}
}
