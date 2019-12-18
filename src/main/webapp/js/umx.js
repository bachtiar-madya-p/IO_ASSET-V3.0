// consider using es6's import / export functions
function notifySuccess(title, message = '') {

    $.notify({
        "icon": 'glyphicon glyphicon-ok',
        "title": title,
        "message": message
    }, {
        "type": 'success',
        "allow_dismiss": true,
        "newest_on_top": true,
        "placement": {
            "from": 'top',
            "align": 'right'
        },
        "template": '<div data-notify="container" class="col-xs-12 col-sm-3 alert alert-{0}" role="alert">' +
            '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
            '<h4 data-notify="title" style="margin: 0; padding-top: 0.2em;">{1}</h4>' +
            (message.length == 0 ? '' : '<p style="padding-top: 0.125em;">{2}</p>') +
            '</div>'
    });
}

function notifyInfo(title, messages = '') {
    message = "";
    $.notify({
        "icon": 'glyphicon glyphicon-ok',
        "title": title,
        "message": message
    }, {
        "type": 'info',
        "allow_dismiss": true,
        "newest_on_top": true,
        "placement": {
            "from": 'top',
            "align": 'right'
        },
        "template": '<div data-notify="container" class="col-xs-12 col-sm-3 alert alert-{0}" role="alert">' +
            '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
            '<h4 data-notify="title" style="margin: 0; padding-top: 0.2em;">{1}</h4>' +
            (message.length == 0 ? '' : '<p style="padding-top: 0.125em;">{2}</p>') +
            '</div>'
    });
}

function notifyError(title, message = '') {
    $.notify({
        "icon": 'glyphicon glyphicon-ok',
        "title": title,
        "message": message
    }, {
        "type": 'error',
        "allow_dismiss": true,
        "newest_on_top": true,
        "placement": {
            "from": 'top',
            "align": 'right'
        },
        "template": '<div data-notify="container" class="col-xs-12 col-sm-3 alert alert-{0}" role="alert">' +
            '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
            '<h4 data-notify="title" style="margin: 0; padding-top: 0.2em;">{1}</h4>' +
            (message.length == 0 ? '' : '<p style="padding-top: 0.125em;">{2}</p>') +
            '</div>'
    });
}

function unescapeUnicode(str) {
    return str.replace(/\\u([a-fA-F0-9]{4})/g, function (g, m1) {
        return String.fromCharCode(parseInt(m1, 16));
    });
}
