/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

    $.getJSON(hsRestUrl() + "/building", function (data) {
        var tab_data = '';
        tabel = $.each(data, function (key, value) {

            tab_data += '<tr>';
            tab_data += '<td hidden >' + value.buildingid + '</td>';
            tab_data += '<td>' + value.buildingname + '</td>';
            tab_data += '<td>' + value.description + '</td>';
            tab_data += '<td>' + value.cityid + '</td>';
            if (value.isactive !== true) {
                tab_data += '<td class="text-center"><span class="label label-default">Inactive</span></td>';
            } else {
                tab_data += '<td class="text-center"><span class="label label-success">Active</span></td>';
            }
            tab_data += '<td class="text-center"><button data-toggle="modal" data-target="#edit-item" class="btn btn-xs btn-default edit-item"><i class="fa fa-pencil"></i></button><button class="btn btn-xs btn-danger remove-item"><i class="fa fa-times"></i></button></td>';
            tab_data += '</tr>';
        });

        $('#assetMasterDT').append(tab_data).DataTable({responsive: true});
        
          /* Create new Item */
        $(".crud-submit").click(function (e) {
            e.preventDefault();
            var act = $("#create-item").find("form").attr("action", );

            var buildingname = $("#create-item").find("input[name='buildingname']").val();
            var description = $("#create-item").find("input[name='description']").val();
            var cityid = $("#create-item").find("input[name='cityid']").val();
            
            var tada = {"buildingname": buildingname,
                "description": description,
                "cityid": cityid

            };
            $.ajax({
                dataType: 'json',
                type: 'POST',
                url: hsRestUrl() + "/building",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(tada)
            }).done(function (data) {
                window.parent.location = window.parent.location.href;

            });
        });
                      
    });
});
     