// $(function () {
//     var $divLogin = $('#divLogin');
//     var $page = $('.layer');
//     var $btn = $('#buttonRegister');
//     $btn.prop('disabled', true);

//     var $login = $('#inputLogin');
//     var $password = $('#inputPassword');
//     var $firstName = $('#inputFirstName');
//     var $lastName = $('#inputLastName');
//     var $address = $('#inputAddress');

//     $page.bind("input", function () {
//         var notFullInfo = $login.val().length === 0 ||
//             $password.val().length === 0 ||
//             $firstName.val().length === 0 ||
//             $lastName.val().length === 0 ||
//             $address.val().length === 0;
//         $btn.prop('disabled', notFullInfo);
//         if ($login.val().length !== 0) {
//             $.ajax({
//                 url: '/rest/LoginRequest/IsUnique',
//                 type: 'POST',
//                 data: $login.val(),
//                 contentType: 'text/html;charset=utf-8',
//                 success: function (result) {
//                     var txt = result === 'true' ? 'This username is free to use :)' : 'This username is already in use :(';
//                     var clr = result === 'true' ? "color:darkolivegreen" : "color:orangered";
//                     textAndColor($divLogin, txt, clr);
//                 }
//             });
//         }
//     });

//     $btn.bind('click', function () {
//         $.ajax({
//             url: '/register',
//             type: 'POST',
//             data: JSON.stringify({
//                 login: $login.val(),
//                 password: $password.val(),
//                 firstName: $firstName.val(),
//                 lastName: $lastName.val(),
//                 address: $address.val()
//             }),
//             success: function (responseJson) {
//                 if (responseJson.redirect) {
//                     window.location = responseJson.redirect;
//                     return;
//                 }
//             }
//         });
//     });

//     function textAndColor(el, text, color) {
//         el.attr("style", color);
//         el.html(text + "<p/>");
//     }
// });

var UNIQUE_LOGIN = 'This username is free to use :)';
var NOT_UNIQUE_LOGIN = 'This username is already in use :(';

new Vue({
    el: '.layer',
    data: {
        login: '',
        password: '',
        firstName: '',
        lastName: '',
        address: '',
        uniqueMessage: '',
        uniqueColor: '#ffffff'
        // buttonDisabled: true
    },
    methods: {
        checkLogin: function () {
            if (!this.login) {
                return;
            }

            this.$http.post('/rest/login/is_unique', this.login, {
                    headers: {
                        'Content-type': 'text/html'
                    }
                })
                .then(response => {
                    var unique = response.body.unique;
                    this.uniqueMessage = (unique ? UNIQUE_LOGIN : NOT_UNIQUE_LOGIN);
                    this.uniqueColor = unique ? 'darkolivegreen' : 'orangered';
                });
        },
        register: function () {
            this.$http.post('/rest/login/register', {afield:'1',bfield:'2'}, {
                headers: {
                    'Content-type': 'application/json'
                }}).then(response => {
                if (responseJson.redirect) {
                    window.location = responseJson.redirect;
                    return;
                }
            });
        }
    },
    computed: {
        buttonDisabled: function () {
            return !((this.uniqueMessage === UNIQUE_LOGIN) &&
                this.login &&
                this.password &&
                this.firstName &&
                this.lastName &&
                this.address);
        }
    }
});