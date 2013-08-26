<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title><fmt:message key="index.title" /> - <shiro:principal property="username" /></title>
<%@ include file="common/head.jsp"%>
<script src="${resources}/scripts/index.js" type="text/javascript"></script>
</head>
<body style="background-color: #eee;">
	<div class="navbar navbar-default navbar-static-top">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand navbar-logo" href="${contextPath}/">
				<span class="glyphicon glyphicon-send"></span>
			</a>
		</div>
		<div class="navbar-collapse collapse navbar-links">
			<ul class="nav navbar-nav">
				<li>
					<a href="${contextPath}/explore">
						<fmt:message key="index.link.explore" />
					</a>
				</li>
				<li>
					<a href="${contextPath}/blog">
						<fmt:message key="index.link.blog" />
					</a>
				</li>
				<li>
					<a href="${contextPath}/help">
						<fmt:message key="index.link.help" />
					</a>
				</li>
			</ul>
			<p class="navbar-text navbar-right">
				<a href="${contextPath}/account/profile" class="navbar-link navbar-profile-link">
					<img src="<shiro:principal property="gravatarURL" />" width="20" height="20" class="img-rounded">
					<shiro:principal property="username" />
				</a>
				<a href="${contextPath}/account/settings" title="<fmt:message key="index.link.settings" />" data-toggle="tooltip" data-placement="bottom"
					class="navbar-link navbar-profile-link">
					<span class="glyphicon glyphicon-cog"></span>
				</a>
				<a href="${contextPath}/account/logout" title="<fmt:message key="index.link.logout" />" data-toggle="tooltip" data-placement="bottom"
					class="navbar-link navbar-profile-link">
					<span class="glyphicon glyphicon-log-out"></span>
				</a>
			</p>
		</div>
	</div>
	<%@ include file="common/locale.jsp"%>
</body>
</html>