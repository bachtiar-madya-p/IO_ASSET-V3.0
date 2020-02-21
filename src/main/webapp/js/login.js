/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function hsRestUrl() {
    return "/asset/system/";
}
$('#login-form').submit(function (event) {
    event.preventDefault();

    const loginUrl = hsRestUrl() + 'authentication/login';
    const loginData = {
        username: $('#login-form input[name="username"]').val(),
        password: $('#login-form input[name="password"]').val()
    };

    $.ajax({
        method: 'POST',
        url: loginUrl,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(loginData),
        success: function (response) {

                window.location.replace('index-admin');
        },
        error: function (error) {
            // clear fields
            $('#login-form input[name="password"]').val('');

            // show error message
            notify('alert-danger', "login failed");
            console.warn(error);
        }
    });
});

