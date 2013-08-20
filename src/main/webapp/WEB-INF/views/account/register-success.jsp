<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><fmt:message key="register-success.title" /></title>
<link rel="shortcut icon" type="image/x-icon" href="${resources}/favicon.ico">
<%@ include file="../head.jsp"%>
<script src="${resources}/scripts/register-success.js" type="text/javascript"></script>
</head>
<body>
	<div class="container">
		<fmt:message key="register-success.message" />
	</div>
</body>
</html>