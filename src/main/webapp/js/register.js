$(function () {
    var $divLogin = $('#divLogin');
    var $page = $('.layer');
    var $btn = $('#buttonRegister');
    $btn.prop('disabled', true);

    var $login = $('#inputLogin');
    var $password = $('#inputPassword');
    var $firstName = $('#inputFirstName');
    var $lastName = $('#inputLastName');
    var $address = $('#inputAddress');

    $page.bind("input", function () {
        var notFullInfo = $login.val().length === 0
            || $password.val().length === 0
            || $firstName.val().length === 0
            || $lastName.val().length === 0
            || $address.val().length === 0;
        $btn.prop('disabled', notFullInfo);
        if ($login.val().length !== 0) {
            $.ajax({
                url: '/rest/LoginRequest/IsUnique',
                type: 'POST',
                data: $login.val(),
                contentType: 'text/html;charset=utf-8',
                success: function (result) {
                    var txt = result === 'true' ? 'This username is free to use :)' : 'This username is already in use :(';
                    var clr = result === 'true' ? "color:darkolivegreen" : "color:orangered";
                    textAndColor($divLogin, txt, clr);
                }
            });
        }
    });

    $btn.bind('click', function () {
        $.ajax({
            url: '/register',
            type: 'POST',
            data: JSON.stringify({
                login: $login.val(),
                password: $password.val(),
                firstName: $firstName.val(),
                lastName: $lastName.val(),
                address: $address.val()
            }),
            success: function (responseJson) {
                if (responseJson.redirect) {
                    window.location = responseJson.redirect;
                    return;
                }
            }
        });
    });

    function textAndColor(el, text, color) {
        el.attr("style", color);
        el.html(text + "<p/>");
    }
});