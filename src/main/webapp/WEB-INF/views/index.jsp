<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title><fmt:message key="index.title" /> - <shiro:principal property="username" /></title>
<%@ include file="common/head.jsp"%>
</head>
<body>
	<div class="navbar navbar-static-top navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextPath}/">
				<%-- <fmt:message key="index.title" /> --%>
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li>
					<a href="${contextPath}/explore">Explore</a>
				</li>
				<li>
					<a href="${contextPath}/blog">Blog</a>
				</li>
				<li>
					<a href="${contextPath}/help">Help</a>
				</li>
			</ul>
			<p class="navbar-text navbar-right">
				<img src="<shiro:principal property="gravatarURL" />" width="20" height="20"> <b><shiro:principal property="username" /></b>
			</p>
			<form class="navbar-form navbar-right">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
			</form>
		</div>
	</div>
</body>
</html>