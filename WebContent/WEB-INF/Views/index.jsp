<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Messenger Chat</title>
	<spring:url value="resources/index.css" var="mainCss" />
	<spring:url value="resources/index.js" var="mainJs" />

   	<link href="${mainCss}" rel="stylesheet" />
   	<script type="text/javascript">
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
   	</script>
</head>
<body>
	<!-- header is table contain username and name of user-->
	<div class="header">
		<table align="center" id="tb-info">
			<tr>
				<td>Username</td>
				<td>: </td>
				<td id="td-username"><c:out value = "${user.getUsername()}"/></td>
			</tr>
			<tr>
				<td>Name</td>
				<td>: </td>
				<td><c:out value = "${user.getName()}"/></td>
			</tr>
		</table>
		<br />
		<br />
	</div>

	<!-- this area contain chat box-->
	<div class="container" align="center">
		Chat with : <c:out value = "${userTo.getUsername()}"/><br />
		<textarea class="textarea" id="textarea" rows="30" cols="100"><c:out value = "${chat_content}"/></textarea>
	</div>

	<div class="container" align="center">
		<input type="text" id="input-text" placeholder="enter here..." style="width: 740px; height: 20px;" />
		<input type="button" id="send-text" value="send" style="width: 70px; height: 25px" onclick="sendMessage();" />
	</div>
	
	<div class="container">
		<p>&#169; copyright in trannguyenhan</p>
	</div>

	<!-- loop over and print user-->
	<div class="list-users">
		<table>
			<tr><b>Danh bạ</b></tr>
			<c:forEach var="loop_user" items="${listUsers}">
				<c:if test = "${user.getUsername() != loop_user.getUsername()}">
					<tr>
						<td><a href="chat?usernameTo=${loop_user.getUsername()}"><c:out value = "${loop_user.getUsername()}"/></a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	
	<!-- sign out -->
	<div class="sign-out">
		<form action="signout" name="sign-out" method="POST">
			<input type="submit" name="sign-out" value="Sign Out">
		</form>
	</div>
</body>
</html>