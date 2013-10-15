<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title><fmt:message key="settings.title" /> - <shiro:principal property="username" /></title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/template.jsp"%>
<script src="${resources}/scripts/settings.js" type="text/javascript"></script>
</head>
<body style="background-color: #fff;">
	<%@ include file="../common/navbar.jsp"%>
	<div id="settings-tabs" class="container">
		<ul class="nav nav-tabs">
			<li class="active">
				<a href="#account" data-toggle="tab">
					<fmt:message key="settings.account.title" />
				</a>
			</li>
			<li>
				<a href="#email" data-toggle="tab">
					<fmt:message key="settings.email.title" />
				</a>
			</li>
			<li>
				<a href="#language" data-toggle="tab">
					<fmt:message key="settings.language.title" />
				</a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane tab-padding active" id="account">
				<form id="update-password-form">
					<fieldset>
						<div class="row">
							<div id="update-password-success" class="col-xs-4" style="display: none;">
								<div class="alert alert-success">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<span class="glyphicon glyphicon-ok"></span>
									<fmt:message key="settings.update-password-success.title" />
								</div>
							</div>
							<div id="update-password-failure" class="col-xs-4" style="display: none;">
								<div class="alert alert-danger">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<span class="glyphicon glyphicon-warning-sign"></span>
									<fmt:message key="settings.update-password-failure.title" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="old-password" class="control-label"> <fmt:message key="settings.old-password.title" /></label>
							<div class="row">
								<div class="col-xs-4">
									<input id="old-password" name="oldPassword" type="password" class="form-control">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="new-password" class="control-label"> <fmt:message key="settings.new-password.title" /></label>
							<div class="row">
								<div class="col-xs-4">
									<input id="new-password" name="newPassword" type="password" class="form-control">
									<span class="help-block">
										<fmt:message key="settings.new-password.help" />
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="confirm-password" class="control-label"> <fmt:message key="settings.confirm-password.title" /></label>
							<div class="row">
								<div class="col-xs-4">
									<input id="confirm-password" name="confirmPassword" type="password" class="form-control">
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">
							<fmt:message key="settings.update.title" />
						</button>
					</fieldset>
				</form>
			</div>
			<div class="tab-pane tab-padding" id="email">
				<form id="update-email-form">
					<fieldset>
						<div class="row">
							<div id="update-email-success" class="col-xs-4" style="display: none;">
								<div class="alert alert-success">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<span class="glyphicon glyphicon-ok"></span>
									<fmt:message key="settings.update-email-success.title" />
								</div>
							</div>
							<div id="update-email-failure" class="col-xs-4" style="display: none;">
								<div class="alert alert-danger">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<span class="glyphicon glyphicon-warning-sign"></span>
									<fmt:message key="settings.update-email-failure.title" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="control-label"> <fmt:message key="settings.email.title" /></label>
							<div class="row">
								<div class="col-xs-4">
									<input id="email" name="email" type="text" class="form-control" value="<shiro:principal property="email" />">
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">
							<fmt:message key="settings.update.title" />
						</button>
					</fieldset>
				</form>
			</div>
			<div class="tab-pane tab-padding" id="language">
				<%@ include file="../common/locale.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>