$(function () {
    var $signButton = $('#signInButton');

    $signButton.bind('click', function () {
        var login = $('#inputLogin').val();
        var password = $('#inputPassword').val();

        if (login.length === 0 || password.length === 0) {
            doLogin(false);
            return;
        }

        debugger;

        $.ajax({
            url: '/login',
            type: 'POST',
            data: JSON.stringify({
                login: login,
                password: password
            }),
            contentType: 'application/json',
            success: function(response) {
                // TODO
            }
        });
    });

    function doLogin(correctLogIn) {
        if (correctLogIn) {

        }
    }
});