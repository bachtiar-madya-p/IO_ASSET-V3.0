function hsRestUrl() {
    return "/asset/system";
}

var tableData={};
 $(document).ready(function(){
                
                $.getJSON(hsRestUrl() + "/user",function(data){
                    var tab_data = '';                     
                    $.each(data, function(key,value){
                        
                        tab_data += '<tr>';
                            tab_data += '<td></td>';
                            tab_data += '<td>'+value.membername+'</td>';
                            tab_data += '<td>'+value.username+'</td>';
                            tab_data += '<td>'+value.email+'</td>';
                            tab_data += '<td>'+value.levelid+'</td>';
                            tab_data += '<td>'+value.departmentid+'</td>';
//                            tab_data += '<td><span class="label label-info">'+value.isadmin+'</span></td>';
                             if (value.isadmin !== true) {
                             tab_data += '<td><span class="label label-default">Inactive</span></td>';}
                         else {
                             tab_data += '<td><span class="label label-success">Active</span></td>';}
                            tab_data += '<td ><div class="btn-group"><a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-xs btn-default"><i class="fa fa-pencil"></i></a><a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a></div></td>';                    
                        tab_data += '</tr>';
                    });
                    $('#userManagerDT').append(tab_data).DataTable({ responsive: true});
                });
            });
//$(document).ready(function () {
//
//    const  columns = daftar;
//
//     $('#userManagerDT').DataTable({
//                 "ajax" : { url: hsRestUrl() + "/user",
//                          type: 'get',
//                          dataType: 'json',
//                          success: function (result) {
//                               daftar = result;      
//                               console.log(daftar);
//                              }
//    },
//                 columns : [
//                  { "data" : "membername"},
//                  { "data" : "username"},
//                  { "data" : "email"},
//                  { "data" : "levelid"},
//                  { "data" : "departmentid"},
//                  { "data" : "departmentid"},
//                  { "data" : "isadmin"},
//                  { "data" : "isactive"}                   
//                 ]
//            });    
//});
//    $("#userManagerDT").dataTable({lengthChange: false});
//    $("#userManagerDT").DataTable({ responsive: true});
//    $.ajax({
//        url: hsRestUrl() + "/user",
//        type: 'get',
//        dataType: 'json',
//        success: function (result) {
//             daftar = result;      
//            console.log(daftar);
//        }
//    });