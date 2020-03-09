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

    $.getJSON(hsRestUrl() + "/asset", function (data) {
        var tab_data = '';
        tabel = $.each(data, function (key, value) {

            tab_data += '<tr>';
            tab_data += '<td>' + value.assetid + '</td>';
            tab_data += '<td>' + value.assetcode + '</td>';
            tab_data += '<td>' + value.assetname + '</td>';
            tab_data += '<td>' + value.typeid + '</td>';
            tab_data += '<td>' + value.manufacture + '</td>';
            tab_data += '<td>' + value.model + '</td>';
            tab_data += '<td>' + value.vendorid + '</td>';
            tab_data += '<td>' + value.note + '</td>';
            tab_data += '<td class="text-center"><button data-toggle="modal" data-target="#edit-item" class="btn btn-xs btn-default edit-item"><i class="fa fa-pencil"></i></button><button class="btn btn-xs btn-danger remove-item"><i class="fa fa-times"></i></button></td>';
            tab_data += '</tr>';
        });


        $('#assetMasterDT').append(tab_data).DataTable({responsive: true});

        $('body').on("click", ".edit-item", function () {
            var userId = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var assetcode = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var assetname = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var typeid = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var manufacture = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").text();
            var model = $(this).parent("td").prev("td").prev("td").prev("td").text();
            var vendor = $(this).parent("td").prev("td").prev("td").text();
            var note = $(this).parent("td").prev("td").text();



            $("#edit-item").find("input[name='assetcode']").val(assetcode);
            $("#edit-item").find("input[name='assetname']").val(assetname);
            $("#edit-item").find("input[name='typeid']").val(typeid);
            $("#edit-item").find("input[name='manufacture']").val(manufacture);
            $("#edit-item").find("input[name='model']").val(model);
            $("#edit-item").find("input[name='vendor']").val(vendor);
            $("#edit-item").find("input[name='note']").val(note);
            


            $("#edit-item").find("form").attr("action", hsRestUrl() + "/asset/" + userId);

            /* Updated new Item */
            $(".crud-submit-edit").click(function (e) {


                e.preventDefault();


                var form_action = $("#edit-item").find("form").attr("action");
                var assetcode = $("#edit-item").find("input[name='assetcode']").val();
                var assetname = $("#edit-item").find("input[name='assetname']").val();
                var typeid = $("#edit-item").find("input[name='typeid']").val();
                var manufacture = $("#edit-item").find("input[name='manufacture']").val();
                var model = $("#edit-item").find("input[name='model']").val();
                var vendorid = $("#edit-item").find("input[name='vendor']").val();
                var note = $("#edit-item").find("input[name='note']").val();

                var tada = {"assetcode": assetcode,
                    "assetname": assetname,
                    "typeid": typeid,
                    "manufacture": manufacture,
                    "model": model,
                    "vendorid": vendorid,
                    "note": note
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


    });
});
     