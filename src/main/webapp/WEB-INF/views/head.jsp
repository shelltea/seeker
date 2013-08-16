<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="resources" value="${pageContext.request.contextPath}/static-resources" />
<script src="${resources}/app.js" type="text/javascript"></script>
<script type="text/javascript">
	var contextPath = "${contextPath}";
</script>
<link rel="stylesheet" type="text/css" href="${resources}/app.css">
