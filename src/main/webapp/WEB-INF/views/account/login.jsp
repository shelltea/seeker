<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Login</title>
<link rel="shortcut icon" type="image/x-icon" href="${resources}/favicon.ico">
<%@ include file="../head.jsp"%>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" action="" method="POST">
			<fieldset>
				<legend>Login</legend>
				<div class="form-group">
					<label for="inputUsername" class="col-lg-2 control-label">Username</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" id="inputUsername" placeholder="Username">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="col-lg-2 control-label">Password</label>
					<div class="col-lg-10">
						<input type="password" class="form-control" id="inputPassword" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<div class="checkbox">
							<label> <input type="checkbox"> Remember me
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<button type="submit" class="btn btn-default">Sign in</button>
						<a href="register">Sign up</a>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>