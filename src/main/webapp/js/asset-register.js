/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function hsRestUrl() {
    return "/asset/system";
}

var tableData = {};
$(document).ready(function () {

    $.getJSON(hsRestUrl() + "/assetregister", function (data) {
        var tab_data = '';
        tabel = $.each(data, function (key, value) {

            tab_data += '<tr>';
            tab_data += '<td hidden >' + value.assetid + '</td>';
            tab_data += '<td>' + value.locationname + '</td>';
            tab_data += '<td>' + value.assetcode + '</td>';            
            tab_data += '<td>' + value.buildingname + '</td>';
            tab_data += '<td>' + value.membercode + '</td>';
            tab_data += '<td>' + value.rateid + '</td>';
            tab_data += '<td>' + value.geolocation + '</td>';
            tab_data += '<td>' + value.photo + '</td>';
            tab_data += '<td>' + value.note + '</td>';
            tab_data += '<td class="text-center"><button data-toggle="modal" data-target="#edit-item" class="btn btn-xs btn-default edit-item"><i class="fa fa-pencil"></i></button><button class="btn btn-xs btn-danger remove-item"><i class="fa fa-times"></i></button></td>';
            tab_data += '</tr>';
        });

        $('#assetRegisterDT').append(tab_data).DataTable({responsive: true});
        
        /* Create new Item */
        $(".crud-submit").click(function (e) {
            e.preventDefault();
            var act = $("#create-item").find("form").attr("action", );

            var location = $("#create-item").find("input[name='location']").val();
            var assetcode = $("#create-item").find("input[name='assetcode']").val();
            var buildingname = $("#create-item").find("input[name='buildingname']").val();
            var membercode = $("#create-item").find("input[name='membercode']").val();
            var rateid = $("#create-item").find("input[name='rateid']").val();
            var geolocation = $("#create-item").find("input[name='geolocation']").val();
            var photo = $("#create-item").find("input[name='photo']").val();
            var note = $("#create-item").find("input[name='note']").val();
            var tada = {
                "location": location,
                "assetcode": assetcode,
                "buildingname": buildingname,
                "membercode": membercode,
                "rateid": rateid,
                "geolocation": geolocation,
                "photo": photo,
                "note": note};
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
            var userId = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var location = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var buildingname = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var geolocation = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").text();
            var photo = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").text();
            var note = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").text();


            $("#edit-item").find("input[name='membername']").val(location);
            $("#edit-item").find("input[name='username']").val(buildingname);
            $("#edit-item").find("input[name='email']").val(geolocation);
            $("#edit-item").find("input[name='levelid']").val(photo);
            $("#edit-item").find("input[name='departmentid']").val(note);

            $("#edit-item").find("form").attr("action", hsRestUrl() + "/user/" + userId);

            /* Updated new Item */
            $(".crud-submit-edit").click(function (e) {


                e.preventDefault();


                var form_action = $("#edit-item").find("form").attr("action");
                var location = $("#edit-item").find("input[name='location']").val();
                var buildingname = $("#edit-item").find("input[name='buildingname']").val();
                var geolocation = $("#edit-item").find("input[name='geolocation']").val();
                var photo = $("#edit-item").find("input[name='photo']").val();
                var note = $("#edit-item").find("input[name='note']").val();
                var tada = {"location": location};
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

            var userId = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();          

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
            

            var userid = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();

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