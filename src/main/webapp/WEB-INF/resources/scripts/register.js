$(function () {
    $('#registerForm').validator({
        theme: 'yellow_right',
        dataFilter: function (data) {
            if (data.success) {
                return true;
            } else {
                var message = '';
                $.each(data.errors, function (key, value) {
                    message = value;
                });
                return message;
            }
        },
        fields: {
            'email': 'required;email;remote[get:' + contextPath + '/api/accounts/checking/email]',
            'username': 'required;username;remote[get:' + contextPath + '/api/accounts/checking/username]',
            'password': 'required;password'
        },
        valid: function (form) {
            var object = $(form).serializeObject();
            $.post(contextPath + '/api/accounts', JSON.stringify(object), function (data) {
                if (data.success === true) {
                    window.location = contextPath + '/account/register/success';
                }
            });
        }
    });
});