/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function hsRestUrl() {
    return "/asset/system";
}
function login() {

    var url = hsRestUrl() + "/authentication/login";

    var uname = document.getElementById("Username").value;
    var pwd = document.getElementById("Password").value;
    if (uname === "" && pwd === "")
    {
        window.alert("Empty username or password");
    } else
    {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            username: uname,
            password: pwd
        }));
        xhr.onload = function () {
            window.location.href = 'index.html';
            var data = JSON.parse(this.responseText);
            console.log(data);
        };
    }
}
