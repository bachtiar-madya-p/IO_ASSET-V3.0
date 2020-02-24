function hsRestUrl() {
    return "/asset/system";
}

var tableData={};

$(document).ready(function () {
    $("#userManagerDT").dataTable();
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
                                        <td> ` + data.membername + `</td>
                                        <td>` + data.username + `</td>
                                        <td>` + data.email + `</td>
                                        <td>` + data.levelid + `</td>
                                        <td> ` + data.departmentid + ` </td>
                                        <td> ` + data.isactive + ` </td>
                                        <td></td>
                                    </tr>`;

                //This is selector of my <tbody> in my table
                $("#list-list").html(html);

            });
            console.log(daftar);
        }
    });
})

