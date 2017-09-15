$(function () {
    $('#inputLogin').on('keyup', function () {
        var $divLogin = $('#divLogin');
        var username = $('#inputLogin').val();
        $.ajax({
            url: "/rest/LoginRequest/isunique",
            type: "POST",
            data: username,
            contentType: "text/html;charset=utf-8",
            success: function (result) {
                var txt = result === 'true' ? 'This username is free to use :)' : 'This username is already in use :(';
                var clr = result === 'true' ? "color:darkolivegreen" : "color:orangered";
                textandcolor($divLogin, txt, clr);
            }
        });
    });
});

function textandcolor(el, text, color) {
    el.attr("style", color);
    el.html(text + "<p/>");
}