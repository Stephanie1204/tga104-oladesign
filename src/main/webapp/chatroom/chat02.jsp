<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<link
  rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
/>
<link
  rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
/>
<link rel="stylesheet" href="../chatroom-css/style.css" />
<link rel="stylesheet" href="../chatroom-css/friendchat.css" type="text/css" />
<div class="chat-contacts" style="display: none">
  <i class="fas fa-bars fa-2x"></i>
  <h2>Contacts</h2>
  <div class="chat-contact-box" id="chat-contact-box"></div>
</div>
<!-- partial:index.partial.html -->
<div class="chat-box" id="chat" style="display: none">
  <div class="chat">
    <div class="chat-contact bar">
      <div class="pic stark"></div>
      <div class="name" id="statusOutput"></div>
    </div>

    <div id="messagesArea" class="panel message-area">
      <ul id="area"></ul>
    </div>
    <div class="input">
      <input
        id="message"
        type="text"
        placeholder="Message"
        onkeydown="if (event.keyCode == 13) sendMessage();"
      />
      <input
        id="sendMessage"
        type="submit"
        class="fa-solid fa-paper-plane"
        value="Send"
        onclick="sendMessage();"
      />
    </div>
  </div>
</div>
<!-- partial -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
  var self = "${memId}"; //檢查是自己還是他人
  var friend = "";

  var MyPoint = "/controller/FriendWS/" + self; // 這個是操作者
  var host = window.location.host;
  var path = window.location.pathname;
  var webCtx = path.substring(0, path.indexOf("/", 1));
  var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

  var statusOutput = document.getElementById("statusOutput");
  var messagesArea = document.getElementById("messagesArea");
  var webSocket;
  var chatRoomId = "";
  // create a websocket
  if(self !== ""){
	  webSocket = new WebSocket(endPointURL);
	  webSocket.onopen = function (event) {
	    // 取得歷史聊天室
	    doGetAllChatroom();
	
	    document.getElementById("sendMessage").disabled = false;
	  };
	
	  // 接收到訊息
	  webSocket.onmessage = function (event) {
	    // 重新取得歷史聊天室
	    doGetAllChatroom();
	
	    var jsonObj = JSON.parse(event.data);
	    if (jsonObj.chatRoomId === chatRoomId) {
	      var li = document.createElement("li");
	      jsonObj.sender === self
	        ? (li.className += "me")
	        : (li.className += "friend");
	      li.innerHTML = jsonObj.message;
	
	      document.getElementById("area").appendChild(li);
	      messagesArea.scrollTop = messagesArea.scrollHeight; // 要另外調整
	    }
	  };
  }
  // 取得歷史聊天室
  function doGetAllChatroom() {
    document.getElementById("chat-contact-box").innerHTML = "";

    $.ajax({
      type: "POST",
      url: "http://localhost:8080/oladesign/chatroom/chatstart.do?action=doGetAllChatroom&mem_0=${memId}",
      success: function (data, status, xhr) {
        var dataJson = JSON.parse(data);
        for (var i = 0; i < dataJson.length; i++) {
          var historyData = dataJson[i];
          var showMsg = historyData.message;

          var chatContact = document.createElement("div");
          chatContact.classclassName = "chat-contact";
          chatContact.onclick = function () {
        	  $("#chat").show();
        	  
            friend = historyData.memId;

            document.getElementById("area").innerHTML = "";

            // 確認有無chatroomid
            $.ajax({
              type: "POST",
              url:
                "http://localhost:8080/oladesign/chatroom/chatstart.do?action=doSetChatRoom&mem_0=" +
                self +
                "&mem_1=" +
                friend,
              success: function (data, status, xhr) {
                var dataJson = JSON.parse(data);
                chatRoomId = dataJson.chatRoomId;
              },
              complete: function () {
                // 確認有無歷史對話紀錄
                $.ajax({
                  type: "POST",
                  url:
                    "http://localhost:8080/oladesign/chatroom/chatstart.do?action=doGetChatLogs&chatRoomId=" +
                    chatRoomId,
                  success: function (data, status, xhr) {
                    var dataJson = JSON.parse(data);
                    for (var i = 0; i < dataJson.length; i++) {
                      var historyData = dataJson[i];
                      var showMsg = historyData.message;
                      var li = document.createElement("li");
                      historyData.sender === self
                        ? (li.className += "me")
                        : (li.className += "friend");
                      li.innerHTML = showMsg;
                      document.getElementById("area").appendChild(li);
                    }
                  },
                });
              },
            });
          };

          var pic = document.createElement("div");
          pic.className = "pic";
          pic.style.backgroundImage = historyData.storeLogo;

          var name = document.createElement("div");
          name.className = "name";
          name.innerHTML = historyData.storeName;

          var message = document.createElement("div");
          message.className = "message";
          message.innerHTML = historyData.message;

          chatContact.appendChild(pic);
          chatContact.appendChild(name);
          chatContact.appendChild(message);

          document.getElementById("chat-contact-box").appendChild(chatContact);
        }
      },
    });
  }

  function sendMessage() {
    var inputMessage = document.getElementById("message");
    var message = inputMessage.value.trim();

    if (message === "" || message === null) {
      return;
    }

    var jsonObj = {
      sender: self,
      receiver: friend,
      message: message,
      chatRoomId: chatRoomId,
    };
    // 儲存對話內容
    $.ajax({
      type: "POST",
      url:
        "http://localhost:8080/oladesign/chatroom/chatstart.do?action=doSetChatMessage&chatRoomId=" +
        chatRoomId +
        "&message=" +
        message +
        "&sender=" +
        self,
      success: function (data, status, xhr) {},
    });

    webSocket.send(JSON.stringify(jsonObj));
    inputMessage.value = "";
    inputMessage.focus();
  }
</script>
