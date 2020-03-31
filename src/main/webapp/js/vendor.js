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

    $.getJSON(hsRestUrl() + "/vendor", function (data) {
        var tab_data = '';
        tabel = $.each(data, function (key, value) {

            tab_data += '<tr>';
            tab_data += '<td hidden >' + value.vendorid + '</td>';
            tab_data += '<td>' + value.vendorcode + '</td>';
            tab_data += '<td>' + value.vendorname + '</td>';
            tab_data += '<td>' + value.contact + '</td>';
            tab_data += '<td>' + value.email + '</td>';
            tab_data += '<td>' + value.address + '</td>';
            tab_data += '<td>' + value.note + '</td>';
            tab_data += '<td>' + value.rate + '</td>';
            tab_data += '<td class="text-center"><button data-toggle="modal" data-target="#edit-item" class="btn btn-xs btn-default edit-item"><i class="fa fa-pencil"></i></button><button class="btn btn-xs btn-danger remove-item"><i class="fa fa-times"></i></button></td>';
            tab_data += '</tr>';
        });

        $('#VendorDT').append(tab_data).DataTable({responsive: true});

           /* Create new Item */
        $(".crud-submit").click(function (e) {
            e.preventDefault();
            var act = $("#create-item").find("form").attr("action", );

            var vendorcode = $("#create-item").find("input[name='vendorcode']").val();
            var vendorname = $("#create-item").find("input[name='vendorname']").val();
            var contact = $("#create-item").find("input[name='contact']").val();
            var email = $("#create-item").find("input[name='email']").val();
            var address = $("#create-item").find("input[name='address']").val();
            var note = $("#create-item").find("input[name='note']").val();
            var rate = $("#create-item").find("input[name='rate']").val();
            var tada = {"vendorcode": vendorcode,
                "vendorname": vendorname,
                "contact": contact,
                "email": email,
                "address": address,
                "note": note,
                "rate": rate
            };
            $.ajax({
                dataType: 'json',
                type: 'POST',
                url: hsRestUrl() + "/vendor",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(tada)
            }).done(function (data) {
                window.parent.location = window.parent.location.href;

            });
        });
     
        
        /* Edit Item */
        $('body').on("click", ".edit-item", function (value) {
            var vendorid = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var vendorcode = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var vendorname = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var contact = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var email = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").text();
            var address = $(this).parent("td").prev("td").prev("td").prev("td").text();
            var note = $(this).parent("td").prev("td").prev("td").text();
            var rate = $(this).parent("td").prev("td").text();



            $("#edit-item").find("input[name='vendorcode']").val(vendorcode);
            $("#edit-item").find("input[name='vendorname']").val(vendorname);
            $("#edit-item").find("input[name='contact']").val(contact);
            $("#edit-item").find("input[name='email']").val(email);
            $("#edit-item").find("input[name='address']").val(address);
            $("#edit-item").find("input[name='note']").val(note);
            $("#edit-item").find("input[name='rate']").val(rate);



            $("#edit-item").find("form").attr("action", hsRestUrl() + "/vendor/" + vendorid);

            /* Updated new Item */
            $(".crud-submit-edit").click(function (e) {


                e.preventDefault();


                var form_action = $("#edit-item").find("form").attr("action");
                var vendorcode = $("#edit-item").find("input[name='vendorcode']").val();
                var vendorname = $("#edit-item").find("input[name='vendorname']").val();
                var contact = $("#edit-item").find("input[name='contact']").val();
                var email = $("#edit-item").find("input[name='email']").val();
                var address = $("#edit-item").find("input[name='address']").val();
                var note = $("#edit-item").find("input[name='note']").val();
                var rate = $("#edit-item").find("input[name='rate']").val();

                var tada = {"vendorcode": vendorcode,
                    "vendorname": vendorname,
                    "contact": contact,
                    "email": email,
                    "address": address,
                    "note": note,
                    "rate": rate
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
        $("body").on("click", ".remove-item", data, function (value) {


            var vendorid = $(this).parent("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text();
            var result = confirm("Are u sure to delete this?!");
            if (result) {
                $.ajax({
                    dataType: 'json',
                    type: 'delete',
                    url: hsRestUrl() + "/vendor/" + vendorid,
                    contentType: 'application/json; charset=utf-8'
                }).done(function (data) {

                    window.parent.location = window.parent.location.href;                    
                });
            }
        });       
    });
});
     
 function isNumber(evt) {
        var iKeyCode = (evt.which) ? evt.which : evt.keyCode;
        if (iKeyCode !== 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
            return false;

        return true;
    }  