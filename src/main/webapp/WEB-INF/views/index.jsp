<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="resources" value="${pageContext.request.contextPath}/static-resources" />
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link rel="shortcut icon" type="image/x-icon" href="${resources}/favicon.ico">
<%@ include file="head.jsp"%>
</head>
<body>
	<shiro:user>
    	Welcome back <shiro:principal property="username" />!
    	<a href="${contextPath}/account/logout">
			<fmt:message key="logout.title" />
		</a>
	</shiro:user>
</body>
</html>