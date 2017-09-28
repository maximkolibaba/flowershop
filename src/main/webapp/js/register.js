$(function () {
    var $inputLogin = $('#inputLogin');
    var $divLogin = $('#divLogin');
    textAndColor($divLogin, "This field should not be empty", "color:black");

    $inputLogin.bind("input", function () {
        var username = $inputLogin.val();
        if (username.length === 0) {
            textAndColor($divLogin, "This field should not be empty", "color:black");
            return;
        }
        $.ajax({
            url: "/rest/LoginRequest/isunique",
            type: "POST",
            data: username,
            contentType: "text/html;charset=utf-8",
            success: function (result) {
                var txt = result === 'true' ? 'This username is free to use :)' : 'This username is already in use :(';
                var clr = result === 'true' ? "color:darkolivegreen" : "color:orangered";
                textAndColor($divLogin, txt, clr);
            }
        });
    });
});

function textAndColor(el, text, color) {
    el.attr("style", color);
    el.html(text + "<p/>");
}