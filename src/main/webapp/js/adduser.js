
function addUser(){
    var list = document.getElementById('users');
var username =document.getElementById('username').value;
var FullName =document.getElementById('FullName').value;
var Alternatename =document.getElementById('Alternatename').value;
var email = document.getElementById('email').value;
var department = document.getElementById('department').value;
var manager = document.getElementById('manager').value;
var entry = document.createElement('li');
entry.appendChild(document.createTextNode(username + ' ' + FullName+ ' '+ Alternatename +' '+ 
                                            email +' '+ department +' '+ manager+' '));
list.appendChild(entry);
    return false;
}
  $.ajax({
        url: hsRestUrl() + "/user",
        type: 'get',
        dataType: 'json',
        success: function (result) {
            let daftar = result;
            var html = '';

            $.each(daftar, function (i, data) {
                html +=
                        `<tr>           <td></td>
                                        <td>  + data.membername + </td>
                                        <td> + data.username + </td>
                                        <td> + data.email + </td>
                                        <td> + data.levelid + </td>
                                        <td>  + data.departmentid +  </td>
                                        <td>  + data.isactive +  </td>
                                        <td</td>
                                    </tr>`;

                //This is selector of my <tbody> in my table
                $("#list-list").html(html);

            });
            console.log(daftar);
        }
    });
