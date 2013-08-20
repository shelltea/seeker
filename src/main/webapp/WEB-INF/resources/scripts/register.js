$(function() {
	$('#registerForm').validator({
		// theme : 'simple_right',
		stopOnError : false,
		fields : {
			'email' : 'required;email;remote[get:' + contextPath + '/api/accounts/checking/email]',
			'username' : 'required;username;remote[get:' + contextPath + '/api/accounts/checking/username]',
			'password' : 'required;password'
		},
		valid : function(form) {
			var object = $(form).serializeObject();
			$.post(contextPath + '/api/accounts', JSON.stringify(object), function(data) {
				if (data.success === true) {
					window.location = contextPath + '/account/register/success';
				}
			});
		}
	});
});