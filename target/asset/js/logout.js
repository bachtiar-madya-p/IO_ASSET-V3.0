/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//function hsRestUrl() {
//    return "/hsreport/rest";
//}

function logout() {

    var url = hsRestUrl() + "/hsreport/rest/authentication/logout";

    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);

    xhr.onload = function () {
        var data = JSON.parse(this.responseText);
        console.log(data);
        window.location.href = data;
    };

}