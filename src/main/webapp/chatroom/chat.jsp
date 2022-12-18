<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../chatroom-css/friendchat.css" type="text/css" />
<style type="text/css">
</style>
<title>最大私人聊天室</title>
</head>
<body>
	<h3 id="statusOutput" class="statusOutput"></h3>
	<div id="row"></div>
	<div id="messagesArea" class="panel message-area">
		<ul id="area">
		</ul>
	</div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text"
			placeholder="Message"
			onkeydown="if (event.keyCode == 13) sendMessage();" /> 
			<input type="submit" id="sendMessage" class="button" value="Send"
			onclick="sendMessage();" />
	</div>
</body>
<script src="jquery-3.4.1.min.js"></script>
<script>
	var MyPoint = "/controller/FriendWS/${mem_0}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${mem_0}'; //檢查是自己還是他人
	var webSocket;
	var chatRoomId = '123456789';

	// create a websocket
	webSocket = new WebSocket(endPointURL);

	webSocket.onopen = function(event) {
		console.log("Connect Success!");
		document.getElementById("sendMessage").disabled = false;
		// 確認有無chatroomid
		$
				.ajax({
					type : "POST",
					url : "http://localhost:8080/oladesign/chatroom/chatstart.do?action=doSetChatRoom&mem_0=${mem_0}&mem_1=${mem_1}",
					success : function(data, status, xhr) {
						var dataJson = JSON.parse(data);
						chatRoomId = dataJson.chatRoomId;
					},
					complete : function() {
						// 確認有無歷史對話紀錄
						$
								.ajax({
									type : "POST",
									url : 'http://localhost:8080/oladesign/chatroom/chatstart.do?action=doGetChatLogs&chatRoomId='
											+ chatRoomId,
									success : function(data, status, xhr) {
										var dataJson = JSON.parse(data);
										for (var i = 0; i < dataJson.length; i++) {
											var historyData = dataJson[i];
											var showMsg = historyData.message;
											var li = document
													.createElement("li");
											historyData.sender === self ? (li.className += "me")
													: (li.className += "friend");
											li.innerHTML = showMsg;
											document.getElementById("area")
													.appendChild(li);
										}
									},
								});
					}
				});
	}
	webSocket.onmessage = function(event) {
		var jsonObj = JSON.parse(event.data);
		var li = document.createElement("li");
		jsonObj.sender === self ? (li.className += "me")
				: (li.className += "friend");
		li.innerHTML = jsonObj.message;

		document.getElementById("area").appendChild(li);
		messagesArea.scrollTop = messagesArea.scrollHeight;
	};

	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = '${mem_1}';
		var message = inputMessage.value.trim();

		if (message === "") {
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"sender" : self,
				"receiver" : friend,
				"message" : message,
				"chatRoomId" : chatRoomId
			};
			// 儲存對話內容
			$
					.ajax({
						type : 'POST',
						url : 'http://localhost:8080/oladesign/chatroom/chatstart.do?action=doSetChatMessage&chatRoomId='
								+ chatRoomId
								+ '&message='
								+ message
								+ '&sender=' + self,
						success : function(data, status, xhr) {
							var dataJson = JSON.parse(data);
							chatRoomId = dataJson.chatRoomId;
							message = dataJson.message;
							sender = dataJson.sender;
						}
					});

			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
</script>
</html>