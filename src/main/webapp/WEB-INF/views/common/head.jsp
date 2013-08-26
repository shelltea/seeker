<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="resources" value="${pageContext.request.contextPath}/static-resources" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%-- <link rel="stylesheet" type="text/css" href="${resources}/app.css"> --%>
<link rel="stylesheet" type="text/css" href="${resources}/styles/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${resources}/styles/jquery.validator/jquery.validator.css">
<link rel="stylesheet" type="text/css" href="${resources}/styles/common.css">
<link rel="shortcut icon" type="image/x-icon" href="${resources}/favicon.ico">
<script src="${resources}/app.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${resources}/compatible.js" type="text/javascript"></script>
<![endif]-->
<script type="text/javascript">
	var contextPath = "${contextPath}";
</script>
