<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><fmt:message key="register-success.title"/></title>
    <%@ include file="../common/head.jsp" %>
    <script src="${resources}/scripts/register-success.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <fmt:message key="register-success.message"/>
</div>
</body>
</html>