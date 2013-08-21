<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><fmt:message key="login.title" /></title>
<link rel="shortcut icon" type="image/x-icon" href="${resources}/favicon.ico">
<%@ include file="../head.jsp"%>
<script src="${resources}/scripts/login.js" type="text/javascript"></script>
</head>
<body style="background-color: #eee;">
	<div class="container">
		<form id="loginForm" class="form-horizontal" method="POST">
			<fieldset>
				<legend>
					<fmt:message key="login.title" />
				</legend>
				<div class="form-group form-padding">
					<label for="inputUsername" class="col-lg-2 control-label"> <fmt:message key="login.username" />
					</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" id="inputUsername" placeholder="<fmt:message key="login.username" />">
					</div>
				</div>
				<div class="form-group form-padding">
					<label for="inputPassword" class="col-lg-2 control-label"> <fmt:message key="login.password" />
					</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" id="inputPassword" placeholder="<fmt:message key="login.password" />">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<div class="checkbox">
							<label> <input type="checkbox"> <fmt:message key="login.rememberMe" />
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="login.title" />
						</button>
						<a href="register" class="btn btn-default">
							<fmt:message key="register.title" />
						</a>
					</div>
				</div>
			</fieldset>
		</form>
		<%@ include file="../common/locale.jsp"%>
	</div>
</body>
</html>