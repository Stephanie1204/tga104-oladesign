<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
    />
    <link rel="stylesheet" href="../chatroom-css/style.css" />
    <link
      rel="stylesheet"
      href="../chatroom-css/friendchat.css"
      type="text/css"
    />
  </head>
  <body>
    <!-- partial:index.partial.html -->
    <div class="chat-box">
      <div class="chat" id="chat" style="display: none">
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
      var MyPoint = "/controller/FriendWS/220001"; // 這個是操作者
      var host = window.location.host;
      var path = window.location.pathname;
      var webCtx = path.substring(0, path.indexOf("/", 1));
      var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

      var statusOutput = document.getElementById("statusOutput");
      var messagesArea = document.getElementById("messagesArea");
      var self = "220001"; //檢查是自己還是他人
      var webSocket;
      var chatRoomId = "";
      // create a websocket
      webSocket = new WebSocket(endPointURL);
      webSocket.onopen = function (event) {
        console.log("Connect Success!");
        document.getElementById("sendMessage").disabled = false;

        // 確認有無chatroomid
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/oladesign/chatroom/chatstart.do?action=doSetChatRoom&mem_0=220001&mem_1=220002",
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

      // message start
      webSocket.onmessage = function (event) {
        var jsonObj = JSON.parse(event.data);
        var li = document.createElement("li");
        jsonObj.sender === self
          ? (li.className += "me")
          : (li.className += "friend");
        li.innerHTML = jsonObj.message;

        document.getElementById("area").appendChild(li);
        messagesArea.scrollTop = messagesArea.scrollHeight; // 要另外調整
      };

      function sendMessage() {
        var inputMessage = document.getElementById("message");
        var friend = "220002"; // TODO
        var message = inputMessage.value.trim();
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
      
      
      function openChatRoom(){
    	// 查詢歷史ChatRoom
          $.ajax({
            type: "POST",
            url:
              "http://localhost:8080/oladesign/chatroom/chatstart.do?action=doSetChatRoomLogs&cmem_0=220013&mem_1=220013",
            success: function (data, status, xhr) {
            	var dataJson = JSON.parse(data);
                for (var i = 0; i < dataJson.length; i++) {
                  var historyCR = dataJson[i];
                }
            },
          });
      }
    </script>
  </body>
</html>
