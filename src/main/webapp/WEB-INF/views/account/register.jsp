<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><fmt:message key="register.title" /></title>
<link rel="shortcut icon" type="image/x-icon" href="${resources}/favicon.ico">
<%@ include file="../head.jsp"%>
<script src="${resources}/scripts/register.js" type="text/javascript"></script>
</head>
<body>
	<div class="container">
		<form id="registerForm" class="form-horizontal" <%-- action="${contextPath}/account/register" --%> method="POST">
			<fieldset>
				<legend>
					<fmt:message key="register.title" />
				</legend>
				<div class="form-group" style="padding-bottom: 10px;">
					<label for="inputEmail" class="col-lg-2 control-label"><fmt:message key="register.email" /></label>
					<div class="col-lg-4">
						<input name="email" type="text" class="form-control" id="email" placeholder="<fmt:message key="register.email"/>">
					</div>
				</div>
				<div class="form-group" style="padding-bottom: 10px;">
					<label for="inputUsername" class="col-lg-2 control-label"><fmt:message key="register.username" /></label>
					<div class="col-lg-4">
						<input name="username" type="text" class="form-control" id="username" placeholder="<fmt:message key="register.username"/>">
					</div>
				</div>
				<div class="form-group" style="padding-bottom: 10px;">
					<label for="inputPassword" class="col-lg-2 control-label"><fmt:message key="register.password" /></label>
					<div class="col-lg-4">
						<input name="password" type="password" class="form-control" id="password" placeholder="<fmt:message key="register.password"/>">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<button id="registerButton" type="submit" class="btn btn-primary">
							<fmt:message key="register.title" />
						</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>