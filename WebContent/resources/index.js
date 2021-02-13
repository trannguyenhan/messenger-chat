var usernameFrom = "${user.getUsername()}";
var usernameTo = "${userTo.getUsername()}";
var webSocket = new WebSocket("ws://localhost:8080/MessengerChat/chatServerEndpoint/" + usernameFrom);

webSocket.onmessage = function processMessage(message) {
	let jsonData = JSON.parse(message.data);
	let tmpUsernameFrom = jsonData["usernameFrom"];
	let tmpUsernameTo = jsonData["usernameTo"];
	let contentChat = jsonData["content"];

	if((tmpUsernameFrom === usernameTo && tmpUsernameTo === usernameFrom) || 
		(tmpUsernameTo === usernameTo && tmpUsernameFrom === usernameFrom)){
		document.getElementById("textarea").append(contentChat + "\n");
	}
}

function sendMessage() {
	let inputText = document.getElementById("input-text").value;

	let contentSend = "{ \"message\" : \"" + inputText + "\", " + 
	 					 "\"usernameFrom\" : \"" + usernameFrom + "\", " + 
	 					 "\"usernameTo\" : \"" + usernameTo + "\"}";	
	webSocket.send(contentSend);
	document.getElementById("input-text").value = "";
}