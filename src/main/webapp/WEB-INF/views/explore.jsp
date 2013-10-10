<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title><fmt:message key="index.title" /> - <shiro:principal property="username" /></title>
<%@ include file="common/head.jsp"%>
<%@ include file="common/template.jsp"%>
<script src="${resources}/scripts/explore.js" type="text/javascript"></script>
</head>
<body style="background-color: #fff;">
	<%@ include file="common/navbar.jsp"%>
	<div id="channel-tabs" class="container"></div>
	<%@ include file="common/locale.jsp"%>
</body>
</html>