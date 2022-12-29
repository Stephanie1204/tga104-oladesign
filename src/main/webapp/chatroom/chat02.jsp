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

<div class="chat-nav" id="chat-nav" onclick="doShowChatContacts();">聊天訊息</div>
<!-- 一、歷史紀錄 / 對話 視窗 -->
<div class="chat-contacts" id="chat-contacts" style="display: none">
  <i
    class="fa-solid fa-circle-xmark fa-2x"
    id="closecontact"
    onclick="doCloseContact();"
  ></i>
  <h2>Contacts</h2>
  <div class="chat-contact-box" id="chat-contact-box"></div>
</div>

<!-- 二、聊天室窗 -->
<div class="chat-box" id="chat" style="display: none">
  <div class="chat" id="talkarea">
    <!-- 聊天對象的名字 -->
    <div class="chat-contact bar">
      <div class="pic stark"></div>
      <div class="name" id="memName"></div>
    </div>
    <!-- 聊天訊息 -->
    <div id="messagesArea" class="panel message-area">
      <ul id="area"></ul>
    </div>
    <!-- enter & submit send發送訊息 -->
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

  var messagesArea = document.getElementById("messagesArea");
  var webSocket;
  var chatRoomId = "";

  // create a websocket
  if (self !== "") {
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
          chatContact.className = "chat-contact";
          chatContact.memId = historyData.memId;
          chatContact.storeName = historyData.storeName;
          chatContact.onclick = function () {
            doShowChatroom(this.memId, this.storeName);
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
      success: function (data, status, xhr) {
        var jsonObj = {
          sender: self,
          receiver: friend,
          message: message,
          chatRoomId: chatRoomId,
        };

        webSocket.send(JSON.stringify(jsonObj));
      },
    });

    inputMessage.value = "";
    inputMessage.focus();
  }

  function doShowChatroom(memId, memName) {
    $("#chat").show();
    friend = memId;

    document.getElementById("area").innerHTML = "";
    document.getElementById("memName").innerHTML = memName;

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
  }

  function doShowChatContacts() {
    var chatNav = document.getElementById("chat-nav");
    var chatContacts = document.getElementById("chat-contacts");

    if (self === "") {
      alert("請先登入在使用此功能");
      return;
    }

    if (chatContacts.style.display === "none") {
      chatNav.style.display = "none";
      chatContacts.style.display = "";
    } else {
      chatNav.style.display = "";
      chatContacts.style.display = "none";
    }
  }
  
  function doCloseContact(){
	  var chatContacts = document.getElementById("chat-contacts");
	  var talkarea = document.getElementById("talkarea");
	  var chatNav = document.getElementById("chat-nav");
	  
	  chatContacts.style.display = "none";
	  talkarea.style.display = "none";
	  chatNav.style.display = "block";
  }

</script>
