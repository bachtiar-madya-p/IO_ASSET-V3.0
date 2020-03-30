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
            tab_data += '<td></td>';
            tab_data += '<td>' + value.membername + '</td>';
            
            tab_data += '<td>' + value.email + '</td>';
            tab_data += '<td >' + value.imageaddress + '</td>';
            tab_data += '<td >' + value.description + '</td>';
            tab_data += '<td>' + value.levelid + '</td>';
            tab_data += '<td>' + value.departmentid + '</td>';
//                            tab_data += '<td><span class="label label-info">'+value.isadmin+'</span></td>';
            if (value.isactive !== true) {
                tab_data += '<td class="text-center"><span class="label label-default">Inactive</span></td>';
            } else {
                tab_data += '<td class="text-center"><span class="label label-success">Active</span></td>';
            }
            tab_data += '<td class="text-center"><button data-toggle="modal" data-target="#edit-item" class="btn btn-xs btn-default edit-item"><i class="fa fa-pencil"></i></button><button class="btn btn-xs btn-danger remove-item"><i class="fa fa-times"></i></button><button  class="btn btn-xs btn-info deactive-user"><i class="fa fa-power-off"></i></button></td>';
            tab_data += '</tr>';
        });

//                    $('#userManagerDT').append(tab_data).DataTable({ responsive: true});
        $('#userManagerDT').append(tab_data).DataTable({responsive: true});

        /* Create new Item */
        $(".crud-submit").click(function (e) {
            e.preventDefault();
            var act = $("#create-item").find("form").attr("action", );

            var username = $("#create-item").find("input[name='username']").val();
            var alias = $("#create-item").find("input[name='alias']").val();
            
            var membername = $("#create-item").find("input[name='membername']").val();
            var email = $("#create-item").find("input[name='email']").val();
            var imageaddress = $("#create-item").find("input[name='imageaddress']").val();
            var description = $("#create-item").find("input[name='description']").val();
            var levelid = $("#create-item").find("input[name='levelid']").val();
            var departmentid = $("#create-item").find("input[name='departmentid']").val();
            var tada = {
                "username": username,
                "alias": alias,
                "membercode": alias,
                "membername": membername,
                "email": email,
                "imageaddress": imageaddress,
                "description": description,
                "levelid": levelid,
                "departmentid": departmentid};
            $.ajax({
                dataType: 'json',
                type: 'POST',
                url: hsRestUrl() + "/user",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(tada)
            }).done(function (data) {
               window.parent.location = window.parent.location.href;

            });
        });

        $('body').on("click", ".edit-item", function () {
            var userId = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();    
            var membername = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();            
            var email = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();    
            var imageaddress = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var description = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").text();
            var levelid = $(this).parent("td").prev("td").prev("td").prev("td").text();
            var departmentid = $(this).parent("td").prev("td").prev("td").text();


            $("#edit-item").find("input[name='membername']").val(membername);            
            $("#edit-item").find("input[name='email']").val(email);
            $("#edit-item").find("input[name='imageaddress']").val(imageaddress);
            $("#edit-item").find("input[name='description']").val(description);
            $("#edit-item").find("input[name='levelid']").val(levelid);
            $("#edit-item").find("input[name='departmentid']").val(departmentid);

            $("#edit-item").find("form").attr("action", hsRestUrl() + "/user/" + userId);

            /* Updated new Item */
            $(".crud-submit-edit").click(function (e) {


                e.preventDefault();


                var form_action = $("#edit-item").find("form").attr("action");
                var membername = $("#edit-item").find("input[name='membername']").val();                
                var email = $("#edit-item").find("input[name='email']").val();
                var imageaddress = $("#edit-item").find("input[name='imageaddress']").val();
                var description = $("#edit-item").find("input[name='description']").val();
                var levelid = $("#edit-item").find("input[name='levelid']").val();
                var departmentid = $("#edit-item").find("input[name='departmentid']").val();
                var tada = {"membername": membername,
                            "email": email,
                            "imageaddress": imageaddress,
                            "description": description,
                            "levelid": levelid,
                            "departmentid": departmentid
                };
                $.ajax({

                    dataType: 'json',
                    type: 'put',
                    url: form_action,
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(tada)


                }).done(function (data) {

                    window.parent.location = window.parent.location.href;
                });
            });
        });
        /* Remove Item */
        $("body").on("click", ".remove-item", function () {

            var userId = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();          

            $.ajax({
                dataType: 'json',
                type: 'delete',
                url: hsRestUrl() + "/user/" + userId,
                contentType: 'application/json; charset=utf-8'
            }).done(function (data) {

              window.parent.location = window.parent.location.href;

            });
        });
        $('body').on('click', ".deactive-user", function () {

            if($(this).parent("td").prev("td").text()=== 'Inactive'){
                var status = true;
            }
            else{
                var status = false;
            }
            

            var userid =  $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();  

            var tada = {"isactive": status, "userid": userid};
            $.ajax({
                type: "post",
                dataType: "json",
                url: hsRestUrl() + "/user/activate/" + userid,

                data: JSON.stringify(tada),
                success: function (data) {
                    window.parent.location = window.parent.location.href;
                }
            });
        });
    });
});
          