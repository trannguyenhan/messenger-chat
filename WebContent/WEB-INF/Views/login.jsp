<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Login</title>
	<spring:url value="resources/login.css" var="mainCss" />
   	<style type="text/css">
   		body {
		  width: 1000px;
		  margin: 0 auto;
		  font-family: Helvetica, Arial,sans-serif;
		  font-size: 16px;
		}

		.container{
			background: white;
		}

		#formlogin{
			text-align: center;
		}

		#request-signup{
			text-align: center;
		}

		#table1{
			margin: 0 auto;
		}
   	</style>
</head>

<body>
	<!-- login box -->
	<div id = "container" class="container">
		<form action="login" method="POST" id="formlogin">
			<h1 align="center">LOGIN TO PIGROUP</h1>
			<table id="table1">
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
			</table>
			<br />
			<input type="submit" name="submit" value="Login">	
		</form>
		
		<br />
		<form action="signup" method="get" id = "request-signup">
			Donn't account : <input type="submit" name="request-signup" value="Sign up">
		</form>
	</div>
</body>
</html>
