/**
 * Resize function without multiple trigger
 *
 * Usage: $(window).smartresize(function(){ // code here });
 */
(function ($, sr) {
    // debouncing function from John Hann
    // http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
    var debounce = function (func, threshold, execAsap) {
        var timeout;

        return function debounced() {
            var obj = this,
                args = arguments;

            function delayed() {
                if (!execAsap)
                    func.apply(obj, args);
                timeout = null;
            }

            if (timeout)
                clearTimeout(timeout);
            else if (execAsap)
                func.apply(obj, args);

            timeout = setTimeout(delayed, threshold || 100);
        };
    };

    // smartresize
    jQuery.fn[sr] = function (fn) {
        return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr);
    };

})(jQuery, 'smartresize');
/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */

var CURRENT_URL = window.location.href.split('#')[0].split('?')[0],
    $BODY = $('body'),
    $MENU_TOGGLE = $('#menu_toggle'),
    $SIDEBAR_MENU = $('#sidebar-menu'),
    $SIDEBAR_FOOTER = $('.sidebar-footer'),
    $LEFT_COL = $('.left_col'),
    $RIGHT_COL = $('.right_col'),
    $NAV_MENU = $('.nav_menu'),
    $FOOTER = $('footer');

// Sidebar
function init_sidebar() {
    // TODO: This is some kind of easy fix, maybe we can improve this
    var setContentHeight = function () {
        // reset height
        $RIGHT_COL.css('min-height', $(window).height());

        var bodyHeight = $BODY.outerHeight(),
            footerHeight = $BODY.hasClass('footer_fixed') ? -10 : $FOOTER.height(),
            leftColHeight = $LEFT_COL
            .eq(1).height() +
            $SIDEBAR_FOOTER.height(),
            contentHeight = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;

        // normalize content
        contentHeight -= $NAV_MENU.height() - 2;

        $RIGHT_COL.css('min-height', contentHeight);
    };

    $SIDEBAR_MENU.find('a').on('click', function (ev) {
        var $li = $(this).parent();

        if ($li.is('.active')) {
            $li.removeClass('active active-sm');
            $('ul:first', $li).slideUp(function () {
                setContentHeight();
            });
        } else {
            // prevent closing menu if we are on child menu
            if (!$li.parent().is('.child_menu')) {
                $SIDEBAR_MENU.find('li').removeClass('active active-sm');
                $SIDEBAR_MENU.find('li ul').slideUp();
            } else {
                if ($BODY.is(".nav-sm")) {
                    $SIDEBAR_MENU.find("li").removeClass("active active-sm");
                    $SIDEBAR_MENU.find("li ul").slideUp();
                }
            }
            $li.addClass('active');

            $('ul:first', $li).slideDown(function () {
                setContentHeight();
            });
        }
    });

    // toggle small or large menu
    $MENU_TOGGLE.on('click', function () {

        if ($BODY.hasClass('nav-md')) {
            $SIDEBAR_MENU.find('li.active ul').hide();
            $SIDEBAR_MENU.find('li.active').addClass('active-sm').removeClass('active');
        } else {
            $SIDEBAR_MENU.find('li.active-sm ul').show();
            $SIDEBAR_MENU.find('li.active-sm').addClass('active').removeClass('active-sm');
        }

        $BODY.toggleClass('nav-md nav-sm');

        setContentHeight();

        $('.dataTable').each(function () {
            $(this).dataTable().fnDraw();
        });
    });

    // check active menu
    $SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass('current-page');

    $SIDEBAR_MENU.find('a').filter(function () {
        return this.href == CURRENT_URL;
    }).parent('li').addClass('current-page').parents('ul').slideDown(function () {
        setContentHeight();
    }).parent().addClass('active');

    // recompute content when resizing
    $(window).smartresize(function () {
        setContentHeight();
    });

    setContentHeight();

    // fixed sidebar
    if ($.fn.mCustomScrollbar) {
        $('.menu_fixed').mCustomScrollbar({
            autoHideScrollbar: true,
            theme: 'minimal',
            mouseWheel: {
                preventDefault: true
            }
        });
    }
}
;
// /Sidebar

// Panel toolbox
$(document)
    .ready(
        function () {
            $('.collapse-link')
                .on(
                    'click',
                    function () {
                        var $BOX_PANEL = $(this).closest('.x_panel'),
                            $ICON = $(this).find('i'),
                            $BOX_CONTENT = $BOX_PANEL
                            .find('.x_content');

                        // fix for some div with hardcoded fix
                        // class
                        if ($BOX_PANEL.attr('style')) {
                            $BOX_CONTENT.slideToggle(200, function () {
                                $BOX_PANEL.removeAttr('style');
                            });
                        } else {
                            $BOX_CONTENT.slideToggle(200);
                            $BOX_PANEL.css('height', 'auto');
                        }

                        $ICON.toggleClass('fa-chevron-up fa-chevron-down');
                    });

            $('.close-link').click(function () {
                var $BOX_PANEL = $(this).closest('.x_panel');

                $BOX_PANEL.remove();
            });
        });
// /Panel toolbox

// Switchery
$(document).ready(function () {
    if ($(".js-switch")[0]) {
        var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
        elems.forEach(function (html) {
            var switchery = new Switchery(html, {
                color: '#26B99A'
            });
        });
    }
});
// /Switchery

// iCheck
$(document).ready(function () {
    if ($("input.flat")[0]) {
        $(document).ready(function () {
            $('input.flat').iCheck({
                checkboxClass: 'icheckbox_flat-green',
                radioClass: 'iradio_flat-green'
            });
        });
    }
});
// /iCheck

// Accordion
$(document).ready(function () {
    $(".expand").on("click", function () {
        $(this).next().slideToggle(200);
        $expand = $(this).find(">:first-child");

        if ($expand.text() == "+") {
            $expand.text("-");
        } else {
            $expand.text("+");
        }
    });
});

// NProgress
if (typeof NProgress != 'undefined') {
    $(document).ready(function () {
        NProgress.start();
    });

    $(window).load(function () {
        NProgress.done();
    });
}

/* DATERANGEPICKER */
function init_daterangepicker() {

    if (typeof ($.fn.daterangepicker) === 'undefined') {
        return;
    }

    $('#reservation').daterangepicker({
        timePicker: false,
        startDate: "2018-01-01",
        endDate: "2050-01-01",
        locale: {
            format: 'YYYY-MM-DD'
        }
    },
        function (start, end, label) {
            // data range picker value
            $("#reservation").data('daterangepicker').setStartDate(start);
            $("#reservation").data('daterangepicker').setEndDate(end);
            $('#user-activity').DataTable().draw();
        });

    $('#reservation-time').daterangepicker({
        timePicker: true,
        timePickerIncrement: 30,
        locale: {
            format: 'YYYY-MM-DD'
        }
    });

}

/* VALIDATOR */
function init_validator() {

    if (typeof (validator) === 'undefined') {
        return;
    }

    // initialize the validator function
    validator.message.date = 'not a real date';

    // validate a field on "blur" event, a 'select' on 'change' event & a
    // '.reuired' classed multifield on 'keyup':
    $('form').on('blur', 'input[required], input.optional, select.required', validator.checkField).on('change',
        'select.required', validator.checkField).on('keypress', 'input[required][pattern]', validator.keypress);

    $('.multi.required').on('keyup blur', 'input', function () {
        validator.checkField.apply($(this).siblings().last()[0]);
    });

    $('form').submit(function (e) {
        e.preventDefault();
        var submit = true;

        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
            submit = false;
        }

        if (submit)
            this.submit();

        return false;
    });

}

/* DATA TABLES */
function init_DataTables() {

    if (typeof ($.fn.DataTable) === 'undefined') {
        return;
    }

    var handleDataTableButtons = function () {
        if ($("#datatable-buttons").length) {
            $("#datatable-buttons")
                .DataTable({
                    dom: "Blfrtip",
                    buttons: [{
                            extend: "copy",
                            className: "btn-primary"
                        },
                        {
                            extend: "csv",
                            className: "btn-info",
                            exportOptions: {
                                columns: [0, 1, 2, 3, 4]
                            },

                        },
                        {
                            title: function () {
                                // Set filename for report
                                return "Sample Report Title"
                            },
                            message: 'This is the report description.',
                            extend: "excelHtml5",
                            className: "btn-success",
                            exportOptions: {
                                columns: ':not(:last-child)',
                            },
                            customize: function (xlsx) {}
                        },
                        {
                            title: function () {
                                // Set filename for report
                                return "Sample Report Title"
                            },
                            message: 'This is the report description.',
                            extend: "pdfHtml5",
                            className: "btn-danger",
                            exportOptions: {
                                columns: ':not(:last-child)',
                            },
                            customize: function (doc) {

                                // Headers
                                var h_cols = [];
                                h_cols[0] = {
                                    image: 'data:image/png;base64,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',
                                    alignment: 'left',
                                    margin: [20],
                                    width: 50,
                                    height: 50,
                                };
                                h_cols[1] = [{
                                        text: 'Text 1: Blah Blah Blah \n Text 2: Blah Blah Blah',
                                        alignment: 'center',
                                        margin: [-80, 10, 20, 20],
                                        width: '400'
                                    }];
                                h_cols[2] = {
                                    text: 'A2A Module',
                                    alignment: 'right',
                                    margin: [0, 20, 20, 20]
                                };
                                var objHeader = {};
                                objHeader['alignment'] = 'center';
                                objHeader['columns'] = h_cols;
                                doc["header"] = objHeader;

                                // Footer
                                var f_cols = [];
                                f_cols[0] = {
                                    text: '',
                                    alignment: 'left',
                                    margin: [20]
                                };
                                f_cols[1] = {
                                    text: 'A2A Module',
                                    alignment: 'right',
                                    margin: [0, 0, 20]
                                };
                                var objFooter = {};
                                objFooter['alignment'] = 'left';
                                objFooter['columns'] = f_cols;
                                doc["footer"] = objFooter;

                                doc.content.forEach(function (content) {
                                    if (content.style == 'message') {
                                        content.text = 'Customize function changed message'
                                    }
                                    if (content.style == 'title') {
                                        content.text = 'Customize function changed title'
                                    }
                                })
                            }

                        }, {
                            extend: "print",
                            className: "btn-warning"
                        },
                    ],
                    responsive: true
                });
        }
    };

    TableManageButtons = function () {
        "use strict";
        return {
            init: function () {
                handleDataTableButtons();
            }
        };
    }();

    $('#datatable-responsive').DataTable();
    $('#asset-list-datatable').DataTable();
    $('#config-file-datatable').DataTable();

    TableManageButtons.init();

}

$(document).ready(function () {
    init_sidebar();
});
