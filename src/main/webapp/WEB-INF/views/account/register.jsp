<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title><fmt:message key="register.title" /></title>
<%@ include file="../common/head.jsp"%>
<script src="${resources}/scripts/register.js" type="text/javascript"></script>
</head>
<body style="background-color: #eee;">
	<div class="container">
		<form id="registerForm" class="form-horizontal">
			<fieldset>
				<legend>
					<fmt:message key="register.title" />
				</legend>
				<div class="form-group">
					<label for="email" class="col-lg-2 control-label"> <fmt:message key="register.email" />
					</label>
					<div class="col-lg-4">
						<input id="email" name="email" type="text" class="form-control" placeholder="<fmt:message key="register.email"/>" autofocus="autofocus">
					</div>
				</div>
				<div class="form-group">
					<label for="username" class="col-lg-2 control-label"> <fmt:message key="register.username" />
					</label>
					<div class="col-lg-4">
						<input id="username" name="username" type="text" class="form-control" placeholder="<fmt:message key="register.username"/>">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-lg-2 control-label"> <fmt:message key="register.password" />
					</label>
					<div class="col-lg-4">
						<input id="password" name="password" type="password" class="form-control" placeholder="<fmt:message key="register.password"/>">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="register.title" />
						</button>
						<a href="login" class="btn btn-default">
							<fmt:message key="register.gotoLogin" />
						</a>
					</div>
				</div>
			</fieldset>
		</form>
		<%@ include file="../common/locale.jsp"%>
	</div>
</body>
</html>