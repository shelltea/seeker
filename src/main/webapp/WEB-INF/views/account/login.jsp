<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title><fmt:message key="login.title" /></title>
<%@ include file="../common/head.jsp"%>
<script src="${resources}/scripts/login.js" type="text/javascript"></script>
</head>
<body style="background-color: #eee;">
	<div class="container">
		<form id="loginForm" class="form-horizontal" method="POST" action="${contextPath}/account/login">
			<fieldset>
				<legend>
					<fmt:message key="login.title" />
				</legend>
				<c:if test="${loginMessage!=null}">
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<span class="glyphicon glyphicon-warning-sign"></span>
						${loginMessage}
					</div>
				</c:if>
				<div class="form-group">
					<label for="username" class="col-lg-2 control-label"> <fmt:message key="login.username" />
					</label>
					<div class="col-lg-4">
						<input id="username" name="username" type="text" class="form-control" placeholder="<fmt:message key="login.username" />" autofocus="autofocus">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-lg-2 control-label"> <fmt:message key="login.password" />
					</label>
					<div class="col-lg-4">
						<input id="password" name="password" type="password" class="form-control" placeholder="<fmt:message key="login.password" />">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<div class="checkbox">
							<label> <input name="rememberMe" type="checkbox"> <fmt:message key="login.rememberMe" />
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
	</div>
</body>
</html>