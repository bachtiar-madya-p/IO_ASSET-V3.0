var main;
var storedApplicationIdName = 'umx.application.id';
var storedApplicationId = '';

$(document).ready(function () {

    // Display Notification (called upon successful Add Campaign)
    let hash = String(window.location.hash);
    if (hash.localeCompare('') !== 0) {
        let message = '';
        if (hash.localeCompare('#success') === 0) {
            message = 'Successfully imported whitelist';
        }

        notifySuccess(message);
        removeHash();
    }

    storedApplicationId = localStorage.getItem(storedApplicationIdName) || '';
    initVue();

    // event listeners (clear modal item before show)
    $('#whitelistAddModal').on('hide.bs.modal', function () {
        main.newWhitelistAccountName = '';
    });

    $('#importModal').on('hide.bs.modal', function () {
        main.data = '';
    });

    // event listeners (set focus on first modal item)
    $('#whitelistAddModal').on('shown.bs.modal', function () {
        $('#whitelistAddModal form .form-group:first-child input').focus();
    });

    $('#importModal').on('shown.bs.modal', function () {
        $('#importModal textarea').focus();
    });
});

function initVue() {
    main = new Vue(
        {
            el: '#vue',
            data: {
                applicationId: storedApplicationId,
                applicationName: '',
                applications: [],
                dataTable: null,
                newRule: {},
                data: '',
                fields: [],
                dispensations: [],
                newWhitelistAccountName: ''
            },
            mounted: function () {
                this.listApplication();
            },
            methods: {
                applicationChanged: function () {
                    localStorage.setItem(storedApplicationIdName, this.applicationId);
                    this.applicationName = $('#application-selection option:selected').text();
                    this.list();
                },
                listApplication: function () {
                    const vm = this;

                    axios.get('/umx/system/applications')
                        .then(function (response) {
                            const localApplications = JSON.parse(JSON.stringify(response.data.applications));
                            localApplications.forEach(function (application, index) {
                                vm.$set(vm.applications, index, application);
                            });
                            vm.list();
                        }).catch(function (error) {
                            notifyError('Network Error', 'Failed to get application data');
                            console.log(error);
                        });
                },
                add: function () {
                    const vm = this;

                    var attributeArr = {};

                    for (var key in vm.newRule) {
                        if (key != 'Role Name') {
                            attributeArr[key] = vm.newRule[key];
                        }
                    }

                    const json = {
                        attributes: attributeArr,
                        roleName: vm.newRule['Role Name'],
                        applicationId: vm.applicationId
                    };

                    axios.post('/umx/system/dispensations/add', json)
                        .then(function (response) {
                            vm.newRule = {};
                            notifySuccess('Successfully added account');
                            $('#whitelistAddModal').modal('hide');

                            vm.list();
                        }).catch(function (error) {
                            notifyError('Network Error', 'Failed to add account');
                            console.log(error);
                        });
                },
                remove: function (accountId) {
                    const vm = this;
                    $('#delete-button-' + accountId + ' .fa').removeClass('fa-trash');
                    $('#delete-button-' + accountId + ' .fa').addClass('fa-spin fa-spinner');

                    axios
                        .delete('/umx/system/dispensations/' + accountId)
                        .then(function (response) {
                            vm.list();
                            notifySuccess('Successfully removed account');
                        }).catch(function (error) {
                            notifyError('Network Error', 'Failed to remove account');
                            console.log(error);
                        }).finally(function resetDeleteButton() {
                            // should not run if jQuery is unable to find the class
                            $('#delete-button-' + accountId + ' .fa').addClass('fa-trash');
                            $('#delete-button-' + accountId + ' .fa').removeClass('fa-spin');
                            $('#delete-button-' + accountId + ' .fa').removeClass('fa-spinner');
                        });
                },
                list: function () {
                    const vm = this;

                    axios.get('/umx/system/dispensations', {
                        params: {
                            applicationId: vm.applicationId
                        }
                    }).then(function (response) {
                        const localWhitelists = JSON.parse(JSON.stringify(response.data.dispensations));

                        vm.whitelist = localWhitelists;
                        vm.applicationName = $('#application-selection option:selected').text();
                        vm.update();

                    }).catch(function (error) {
                        notifyError('Network Error', 'Failed to get whitelist data');
                        console.log(error);
                    });
                },
                doImport: function () {
                    const vm = this;
                    axios.post('/umx/system/dispensations/import?applicationId=' + vm.applicationId, {
                        data: vm.data,
                        applicationId: vm.applicationId
                    }).then(function (response) {
                        // redirects to same page with hash
                        window.location.href = window.location.href + '#success';
                        window.location.reload();
                    }).catch(function (error) {
                        notifyError('Network Error', 'Failed to get import whitelist data');
                        console.log(error);
                    });
                },
                doExport: function () {
                    if (this.applicationId != null) {
                        window.location.href = '/umx/system/dispensations/export?applicationId=' + this.applicationId;
                    }
                },
                update: function () {
                    const vm = this;
                    if ($.fn.DataTable.isDataTable('#whitelistDT')) {
                        vm.dataTable.destroy();
                    }


                    // Init ID Column
                    var columns = [];

                    // Whitelist Name
                    columns.push({
                        orderable: false,
                        visible: true,
                        render: function (data, type, row, meta) {
                            return row.name;
                        }
                    });

                    // Delete Button
                    columns.push({
                        orderable: false,
                        visible: true,
                        render: function (data, type, row, meta) {
                            return '' +
                                '<span id="delete-button-' + row.id + '"' +
                                '    class="btn btn-link"' +
                                '    onclick="main.remove(' + row.id + ', this)"' +
                                '    title="Click to delete">' +
                                '    <i class = "fa fa-fw fa-trash"></i>' +
                                '</span>';
                        }
                    });

                    // (Re)Initialize Datatable
                    if (columns.length > 0) {
                        vm.dataTable = $('#whitelistDT').DataTable({
                            dom: '<"myfilter"f><"mylength"l>tip',
                            data: vm.whitelist,
                            columns: columns
                        });
                    }

                    // Add Start, Stop and Delete Campaign Buttons
                    $('div.myfilter').append('' +
                        '<button id="importButton" type="button" ' +
                        '    class="btn btn-default btn-sm iconed-button" ' +
                        '    data-toggle="modal" data-target="#importModal">' +
                        '    <i class="fa fa-fw fa-upload"></i>' +
                        '    <span> Import (Upload) </span>' +
                        '</button>' +
                        '<button id="exportButton" type="button" ' +
                        '    class="btn btn-default btn-sm iconed-button" ' +
                        '    data-toggle="modal" onclick="main.doExport()">' +
                        '    <i class="fa fa-fw fa-download"></i>' +
                        '    <span> Export (Download) </span>' +
                        '</button>' +
                        '');

                    // Set focus on search when page loaded
                    if ($('#whitelistAddModal').is(':visible') == false &&
                        $('#importModal').is(':visible') == false) {
                        $('div.dataTables_filter input').focus();
                    }

                },
                cancelAdd: function () {
                    $('#whitelistAddModal').modal('toggle');
                },
                cancelImport: function () {
                    $('#importModal').modal('toggle');
                }
            },
            updated: function () {
                const vm = this;
                vm.$nextTick(function () {
                    vm.update();
                });
            }
        });
}

function removeHash() {
    // removes hash from URL
    let scrollV, scrollH, loc = window.location;
    if ('pushState' in history)
        history.pushState('', document.title, loc.pathname + loc.search);
    else {
        // Prevent scrolling by storing the page's current scroll offset
        scrollV = document.body.scrollTop;
        scrollH = document.body.scrollLeft;

        loc.hash = '';

        // Restore the scroll offset, should be flicker free
        document.body.scrollTop = scrollV;
        document.body.scrollLeft = scrollH;
    }
}
