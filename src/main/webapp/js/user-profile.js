function hsRestUrl() {
    return "/asset/system";
}

var tableData = {};
$(document).ready(function () {

    $.getJSON(hsRestUrl() + "/user", function (data) {
        var tab_data = '';
        tabel = $.each(data, function (key, value) {

            tab_data += '<tr>';
            tab_data += '<td hidden>' + value.userid + '</td>';

            tab_data += '<td>' + value.membername + '</td>';
            tab_data += '<td hidden>' + value.username + '</td>';
            tab_data += '<td hidden>' + value.alias + '</td>';
            tab_data += '<td hidden>' + value.memberid + '</td>';
            tab_data += '<td hidden>' + value.membercode + '</td>';
            tab_data += '<td hidden>' + value.email + '</td>';
            tab_data += '<td hidden>' + value.imageaddress + '</td>';
            tab_data += '<td hidden>' + value.levelid + '</td>';
            tab_data += '<td hidden>' + value.departmentid + '</td>';
//                            tab_data += '<td><span class="label label-info">'+value.isadmin+'</span></td>';
            if (value.isactive !== true) {
                tab_data += '<td hidden class="text-center"><span class="label label-default">Inactive</span></td>';
            } else {
                tab_data += '<td hidden class="text-center"><span class="label label-success">Active</span></td>';
            }
            
            tab_data += '<td class="text-center"><button data-toggle="modal" data-target="#edit-item" class="btn btn-xs btn-info edit-item"><i class="fa fa-eye"></i> Details</button></td>';
            tab_data += '</tr>';
        });

//                    $('#userManagerDT').append(tab_data).DataTable({ responsive: true});
        $('#userManagerDT').append(tab_data).DataTable({responsive: true});



        $('body').on("click", ".edit-item", function () {

            var mod = '';
            var userId = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var membername = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var username = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var alias = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var memberid = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var membercode = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var email = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var imageaddress = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").text();
            var levelid = $(this).parent("td").prev("td").prev("td").prev("td").text();
            var departmentid = $(this).parent("td").prev("td").prev("td").text();
            var status = $(this).parent("td").prev("td").text();
            
            
            $("#edit-item").find("input[name='membername']").val(membername);
            $("#edit-item").find("input[name='username']").val(username);
            $("#edit-item").find("input[name='alias']").val(alias);
            $("#edit-item").find("input[name='memberid']").val(memberid);
            $("#edit-item").find("input[name='membercode']").val(membercode);
            $("#edit-item").find("input[name='email']").val(email);
            $("#edit-item").find("input[name='imageaddress']").val(imageaddress);
            $("#edit-item").find("input[name='levelid']").val(levelid);
            $("#edit-item").find("input[name='departmentid']").val(departmentid);
            $("#edit-item").find("input[name='status']").val(status);


            $("#edit-item").find("form").attr("action", hsRestUrl() + "/user/" + userId);



           

        });
      

    });
});
          