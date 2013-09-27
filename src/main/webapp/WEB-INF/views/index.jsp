<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title><fmt:message key="index.title" /> - <shiro:principal property="username" /></title>
<%@ include file="common/head.jsp"%>
<%@ include file="common/template.jsp"%>
<script src="${resources}/scripts/index.js" type="text/javascript"></script>
</head>
<body style="background-color: #eee;">
	<%@ include file="common/navbar.jsp"%>
	<div class="container">
		<div id="toolbar" class="row row-padding">
			<div class="col-sm-4 col-md-3 hidden-xs">
				<div class="input-group input-group-sm">
					<input type="text" class="form-control" placeholder="<fmt:message key="index.search.placeholder" />">
					<span class="input-group-btn">
						<button type="button" class="btn btn-success">
							<fmt:message key="index.search.title" />
						</button>
					</span>
				</div>
			</div>
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="row">
					<div class="col-xs-8 col-md-8">
						<button type="button" class="btn btn-sm btn-default" title="<fmt:message key="index.refresh.tooltip" />" data-toggle="tooltip" data-placement="top">
							<span id="refresh" class="glyphicon glyphicon-refresh"></span>
						</button>
						<div class="btn-group">
							<button type="button" class="btn btn-sm btn-default dropdown-toggle" data-toggle="dropdown">
								<fmt:message key="index.all-items.title" />
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li>
									<a href="#">
										<fmt:message key="index.all-items.title" />
									</a>
								</li>
								<li>
									<a href="#">
										<fmt:message key="index.unread-items.title" />
									</a>
								</li>
							</ul>
						</div>
						<button type="button" class="btn btn-sm btn-default" title="<fmt:message key="index.mask-as-read.tooltip" />" data-toggle="tooltip" data-placement="top">
							<span class="glyphicon glyphicon-ok"></span>
						</button>
					</div>
					<div class="col-xs-4 col-md-4">
						<div class="btn-group pull-right">
							<button id="prev-page" type="button" class="btn btn-sm btn-default" title="<fmt:message key="index.left.tooltip" />" data-toggle="tooltip"
								data-placement="top">
								<span class="glyphicon glyphicon-chevron-left"></span>
							</button>
							<button id="next-page" type="button" class="btn btn-sm btn-default" title="<fmt:message key="index.right.tooltip" />" data-toggle="tooltip"
								data-placement="top">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4 col-md-3 hidden-xs">
				<div class="list-group">
					<a href="${contextPath}/inbox" class="list-group-item active">
						<span class="glyphicon glyphicon-inbox"></span>
						<b><fmt:message key="index.inbox.title" /></b>
						<span id="entries-count" class="badge"></span>
					</a>
					<a href="${contextPath}/star" class="list-group-item">
						<span class="glyphicon glyphicon-star"></span>
						<fmt:message key="index.star.title" />
					</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<fmt:message key="index.subscriptions.title" />
					</div>
					<div id="feeds" class="list-group"></div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div id="entries" class="list-group"></div>
			</div>
		</div>
	</div>
	<%@ include file="common/locale.jsp"%>
	<!-- Entry -->
	<div class="modal" id="entry-modal" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 id="entry-title" class="modal-title"></h4>
				</div>
				<div id="entry-content" class="modal-body"></div>
			</div>
		</div>
	</div>
</body>
</html>