<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Sign up</title>
	<spring:url value="resources/cssfolder/signup.css" var="mainCss" />
   	<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
	<!-- signup box -->
	<div id = "container" class="container">
		<form action="handle-signup" method="POST" id="formsignup">
			<h1 align="center">SIGN UP TO PIGROUP</h1>
			<table id="table1">
				<tr>
					<td>
						Name			
					</td>
					<td>
						<input type="text" name="name"><br />			
					</td>
				</tr>
				<tr>
					<td>
						Username			
					</td>
					<td>
						<input type="text" name="username"><br />			
					</td>
				</tr>
				<tr>
					<td>
						Password			
					</td>
					<td>
						<input type="password" name="password"><br />			
					</td>
				</tr>
				<tr>
					<td>
						Re-password
					</td>
					<td>
						<input type="password" name="re-password"><br />
					</td>
				</tr>
			</table>
			<br />
			<input type="submit" name="submit" value="CREATE ACCOUNT">	
		</form>
	</div>
</body>
</html>
