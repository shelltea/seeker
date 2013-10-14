$(function() {
	// 计算Tab高度
	var tabsDivHeight = $(window).height() - ($('#navbar').height() + $('#localebar').height());
	$('#settings-tabs').height(tabsDivHeight - 25);

	// 启用页面中的Tooltip
	$('.container, .navbar').tooltip({
		selector : "[data-toggle=tooltip]",
		container : "body"
	});

	$('#update-password-form').validator({
		theme : 'yellow_right',
		fields : {
			'oldPassword' : '旧密码:required;password;remote[get:' + contextPath + '/api/accounts/checking/password]',
			'newPassword' : '新密码:required;password',
			'confirmPassword' : '确认密码:required;match(newPassword);password'
		},
		valid : function(form) {
			var object = $(form).serializeObject();

			$.put(contextPath + '/api/accounts', JSON.stringify(object), function(data) {
				if (data.success === true) {
					document.getElementById('update-password-form').reset();
					$('#update-password-success').show().fadeOut(1600);
					$('#update-password-failure').hide();
				} else {
					$('#update-password-failure').show().fadeOut(1600);
					$('#update-password-success').hide();
				}
			});
		}
	});

	$('#update-email-form').validator({
		theme : 'yellow_right',
		fields : {
			'email' : '邮箱:required;email;remote[get:' + contextPath + '/api/accounts/checking/email]'
		},
		valid : function(form) {
			var object = $(form).serializeObject();

			$.put(contextPath + '/api/accounts', JSON.stringify(object), function(data) {
				if (data.success === true) {
					$('#update-email-success').show().fadeOut(1600);
					$('#update-email-failure').hide();
				} else {
					$('#update-email-failure').show().fadeOut(1600);
					$('#update-email-success').hide();
				}
			});
		}
	});
});