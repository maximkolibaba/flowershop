$(function () {
    const EMPTY_STRING = '';
    const INCORRECT_LOGIN = 'Incorrect login/password. Please try again or register.</p>';

    var $signButton = $('#signInButton');
    var $divLogin = $('#incorrect-login');
    $divLogin.html(EMPTY_STRING);

    $signButton.bind('click', function () {
        var login = $('#inputLogin').val();
        var password = $('#inputPassword').val();

        if (login.length === 0 || password.length === 0) {
            doLogin(false);
            return;
        }

        $.ajax({
            url: '/login',
            type: 'POST',
            data: JSON.stringify({
                login: login,
                password: password
            }),
            success: function (responseJson) {
                $('#inputPassword').val(EMPTY_STRING);
                if (responseJson.redirect) {
                    window.location = responseJson.redirect;
                    $divLogin.html(EMPTY_STRING);
                    return;
                }
                $divLogin.html(INCORRECT_LOGIN);
            }
        });
    });
});