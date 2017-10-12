$(function () {
    var $inputLogin = $('#inputLogin');
    var $inputPassword = $('#inputPassword');
    var $inputFirstName = $('#inputFirstName');
    var $inputLastName = $('#inputLastName');
    var $inputAddress = $('#inputAddress');

    var $divLogin = $('#divLogin');
    var $page = $('.layer');
    $('#buttonRegister').prop('disabled', true);

    $page.bind("input", function () {
        var notFullInfo = $inputLogin.val().length === 0
            || $inputPassword.val().length === 0
            || $inputFirstName.val().length === 0
            || $inputLastName.val().length === 0
            || $inputAddress.val().length === 0;
        $('#buttonRegister').prop('disabled', notFullInfo);
        if ($inputLogin.val().length !== 0) {
            // $.ajax({
            //     url: "/rest/LoginRequest/isunique",
            //     type: "POST",
            //     data: $inputLogin.val(),
            //     contentType: "text/html;charset=utf-8",
            //     success: function (result) {
            //         var txt = result === 'true' ? 'This username is free to use :)' : 'This username is already in use :(';
            //         var clr = result === 'true' ? "color:darkolivegreen" : "color:orangered";
            //         textAndColor($divLogin, txt, clr);
            //     }
            // });
            debugger;
            // this.$http.post('/rest/LoginRequest/isunique', {username: this.loginField})
            //     .then(function (response) {
            //         var txt = result ? 'This username is free to use :)' : 'This username is already in use :(';
            //         var clr = result ? "color:darkolivegreen" : "color:orangered";
            //         textAndColor($divLogin, txt, clr);
            // });
            // var request = new XMLHttpRequest();
            // request.open('POST', '/rest/LoginRequest/isunique', true);
            // request.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
            // request.send("asd");
            //
            // request.onl = function () {
            //     var result = request.responseText;
            //     var txt = result ? 'This username is free to use :)' : 'This username is already in use :(';
            //     var clr = result ? "color:darkolivegreen" : "color:orangered";
            //     textAndColor($divLogin, txt, clr);
            // };


            axios.post(
                '/rest/LoginRequest/IsUnique', this.loginField,
                {
                    headers: {
                        'Content-type': 'text/html'
                    }
                })
                .then(response => {
                    let result = response.data;
                    let txt = result === 'true' ? 'This username is free to use :)' : 'This username is already in use :(';
                    let clr = result === 'true' ? "color:darkolivegreen" : "color:orangered";
                    textAndColor($divLogin, txt, clr);
                });
        } else {
            textAndColor($divLogin, "", "");
        }
    });

    function textAndColor(el, text, color) {
        el.attr("style", color);
        el.html(text + "<p/>");
    }

    // $('#buttonRegister').bind('click', function() {
    //     var data = {
    //         "login": $inputLogin.val(),
    //         "password": $inputPassword.val(),
    //         "firstName": $inputFirstName.val(),
    //         "lastName": $inputLastName.val(),
    //         "address": $inputAddress.val()
    //     };
    //     $.ajax({
    //         url: "/rest/register",s
    //         type: "POST",
    //         data: JSON.stringify(data),
    //         dataType: "json",
    //         async: false
    //     });
    // });

});