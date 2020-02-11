/* 
 * HelpScout's Mailbox and Ticket related program.
 */

function hsRestUrl() {
    return "/hsreport/rest/HelpScoutApp";
}

function getMailboxes() {
    const url = hsRestUrl() + '/mailboxes';
    let dropdown = $('#mailboxes');

    $(".loader").show();

    dropdown.empty();

    $.get(url, function (data) {
        dropdown.append(data);
        $(".loader").fadeOut("slow");

    }).fail(function () {
        alert("Failed to connect to HelpScout. Please try again.");
    });
}

function generateCsvReport() {
    const url = hsRestUrl() + '/reports';
    var parmUrl = "";

    var mailboxId = document.getElementById("mailboxes").value;
    var dateRangeArr = document.getElementById("reservation").value.split(" - ");
    var fromDate = dateRangeArr[0];
    var toDate = dateRangeArr[1];
    var ticketStatus = document.getElementById("ticketStt").value;


    paramUrl = url + "/" + mailboxId + "/" + fromDate + "T00:00:00Z/" + toDate + "T00:00:00Z/" + ticketStatus;

    $(".loader").show();

    // Generate HS report (.csv)
    $.get(parmUrl, function () {
        // Download generated HS report
        window.location = paramUrl;
        $(".loader").fadeOut("slow");
    })
            .success(function (response, status, xhr) {

                console.log(xhr);
            })
            .fail(function (err) {
                window.alert("Failed to generate report. Please retry again.\n\nIf error persistent, please contact system admin.");
                $(".loader").fadeOut("slow");
                console.log(err);
            });

}