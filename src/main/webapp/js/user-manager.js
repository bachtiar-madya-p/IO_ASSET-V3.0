function hsRestUrl() {
    return "/asset/system";
}

var tableData={};
 $(document).ready(function(){
                
                $.getJSON(hsRestUrl() + "/user",function(data){
                    var tab_data = '';                     
                   tabel = $.each(data, function(key,value){
                        
                        tab_data += '<tr>';
                            tab_data += '<td>'+value.userid+'</td>';
                            tab_data += '<td>'+value.membername+'</td>';
                            tab_data += '<td>'+value.username+'</td>';
                            tab_data += '<td>'+value.email+'</td>';
                            tab_data += '<td>'+value.levelid+'</td>';
                            tab_data += '<td>'+value.departmentid+'</td>';
//                            tab_data += '<td><span class="label label-info">'+value.isadmin+'</span></td>';
                             if (value.isadmin !== true) {
                             tab_data += '<td class="text-center"><span class="label label-default">Inactive</span></td>';}
                             else {
                             tab_data += '<td class="text-center"><span class="label label-success">Active</span></td>';}
                            tab_data += '<td class="text-center"><button data-toggle="modal" data-target="#edit-item" class="btn btn-xs btn-default edit-item"><i class="fa fa-pencil"></i></button><button class="btn btn-xs btn-danger remove-item"><i class="fa fa-times"></i></button></td>';                    
                        tab_data += '</tr>';
                    });
                    
//                    $('#userManagerDT').append(tab_data).DataTable({ responsive: true});
                    $('#userManagerDT').append(tab_data).DataTable({ responsive: true}).on("click",".edit-item",function(){
                    var userId = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
                    var membername = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
                    var username = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
                    var email = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").text();
                    var levelid = $(this).parent("td").prev("td").prev("td").prev("td").text();
                    var departmentid = $(this).parent("td").prev("td").prev("td").text();
                    

                    $("#edit-item").find("input[name='membername']").val(membername);
                    $("#edit-item").find("input[name='username']").val(username);
                    $("#edit-item").find("input[name='email']").val(email);
                    $("#edit-item").find("input[name='levelid']").val(levelid);
                    $("#edit-item").find("input[name='departmentid']").val(departmentid);
                    
                    $("#edit-item").find("form").attr("action",hsRestUrl() + "/user/" + userId);
                    
                    /* Updated new Item */
                        $(".crud-submit-edit").click(function(e){


                            e.preventDefault();


                            var form_action = $("#edit-item").find("form").attr("action");
                            var membername = $("#edit-item").find("input[name='membername']").val();
                            var username = $("#edit-item").find("input[name='username']").val();
                            var email = $("#edit-item").find("input[name='email']").val();
                            var levelid = $("#edit-item").find("input[name='levelid']").val();
                            var departmentid = $("#edit-item").find("input[name='departmentid']").val();

//                            $.getJSON(hsRestUrl() + "/user",function(data){
//                                tabel = $.each(data, function(key,value){
//                                tab_data += '<tr>';
//                                    tab_data += '<td>'+value.userid+'</td>';
//                                    tab_data += '<td>'+value.membername+'</td>';
//                                tab_data += '<td>'+value.levelid+'</td>';
//                                    tab_data += '<td>'+value.departmentid+'</td>';
//                                tab_data += '</tr>';
//
//                            });    
//                            });                    tab_data += '<td>'+value.username+'</td>';
//                                    tab_data += '<td>'+value.email+'</td>';
//                    
                            
                            $.ajax({
                                
                                dataType: 'json',                                
                                url: form_action,
                                type:'put',
                                contentType: 'application/json',
                                data:{membername:membername, username:username, email:email, levelid:levelid, departmentid:departmentid}
                            }).done(function(data){


                                getPageData(data);
                                $(".modal").modal('hide');
                                toastr.success('Item Updated Successfully.', 'Success Alert', {timeOut: 5000});


                            });


                        });

                    });
                });
            });
          