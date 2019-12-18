var main;

$(document).ready(function () {

    // Display Notification (called upon successful Add Campaign)
    let hash = String(window.location.hash);
    if (hash.localeCompare('') !== 0) {
        let message = '';
        if (hash.localeCompare('#success') === 0) {
            message = 'Successfully imported rules';
        }

        notifySuccess(message);
        removeHash();
    }

    initVue();

    // event listeners (clear modal item before show)
    $('#addRuleModal').on('hide.bs.modal', function () {
        main.newRule = {};
    });

    $('#importModal').on('hide.bs.modal', function () {
        main.data = '';
    });

    // event listeners (set focus on first modal item)
    $('#addRuleModal').on('shown.bs.modal', function () {
        $('#addRuleModal form .form-group:first-child input').focus();
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
                applicationId: localStorage.getItem('umx.application.id') || '',
                applicationName: '',
                applications: [],
                dataTable: null,
                newRule: {},
                data: '',
                fields: [],
                rules: []
            },
            mounted: function () {
                this.listApplication();
            },
            methods: {
                applicationChanged: function () {
                    localStorage.setItem('umx.application.id', this.applicationId);
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
                        applicationId: vm.applicationId,
                        roleName: vm.newRule['Role Name'],
                        attributes: attributeArr
                    };

                    axios.post('/umx/system/rules', json, {
                        headers: {
                            'Content-Type': 'application/json',
                        }
                    }).then(function (response) {
                        vm.newRule = {};
                        notifySuccess('Successfully added new rule');
                        $('#addRuleModal').modal('hide');
                        vm.list();
                    }).catch(function (error) {
                        notifyError('Network Error', 'Failed to add rule');
                        console.log(error);
                    });
                },
                remove: function (ruleId) {
                    const vm = this;
                    $('#delete-button-' + ruleId + ' .fa').removeClass('fa-trash');
                    $('#delete-button-' + ruleId + ' .fa').addClass('fa-spin fa-spinner');

                    axios.post('/umx/system/rules/delete', {
                        id: ruleId
                    }).then(function (response) {
                        vm.list();
                        notifySuccess('Successfully removed a rule');
                    }).catch(function (error) {
                        notifyError('Network Error', 'Failed to remove rule');
                        console.log(error);
                    }).finally(function resetDeleteButton() {
                        // should not run if jQuery is unable to find the class
                        $('#delete-button-' + ruleId + ' .fa').addClass('fa-trash');
                        $('#delete-button-' + ruleId + ' .fa').removeClass('fa-spin');
                        $('#delete-button-' + ruleId + ' .fa').removeClass('fa-spinner');
                    });
                },
                list: function () {
                    const vm = this;

                    // if no applications are selected
                    if (vm.applicationId == '') {
                        vm.rules = [];
                        vm.fields = [];
                        vm.applicationName = '';
                        vm.update();
                        return; // early return
                    }

                    axios.get('/umx/system/rules', {
                        params: {
                            applicationId: vm.applicationId
                        }
                    }).then(function (response) {
                        const localRules = JSON.parse(JSON.stringify(response.data.rules));
                        const localFields = JSON.parse(JSON.stringify(response.data.fields));

                        vm.rules = localRules;
                        vm.fields = localFields;
                        vm.applicationName = $('#application-selection option:selected').text();
                        vm.update();
                    }).catch(function (error) {
                        notifyError('Network Error', 'Failed to get rule data');
                        console.log(error);
                    });
                },
                doImport: function () {
                    const vm = this;
                    axios.post('/umx/system/rules/import?applicationId=' + vm.applicationId, {
                        data: vm.data,
                        applicationId: vm.applicationId
                    }).then(function (response) {
                        // redirects to same page with hash
                        window.location.href = window.location.href + '#success';
                        window.location.reload();
                    }).catch(function (error) {
                        notifyError('Network Error', 'Failed to get import rule data');
                        console.log(error);
                    });
                },
                doExport: function () {
                    if (this.applicationId != null) {
                        window.location.href = '/umx/system/rules/export?applicationId=' + this.applicationId;
                    }
                },
                update: function () {
                    const vm = this;
                    if ($.fn.DataTable.isDataTable('#ruleDT')) {
                        vm.dataTable.destroy();
                    }

                    // Generate table headers
                    var localFields = [];
                    for (let pos in vm.fields) {
                        localFields.push(vm.fields[pos]);
                    }
                    localFields.push('Role Name');
                    localFields.push('');

                    document.getElementById('ruleDT').innerHTML =
                        localFields.reduce(
                            function (htmlString, field, index, array) {
                                return htmlString +
                                    (index === 0 ? '<thead>' : '') +
                                    '<th>' + field + '</th>' +
                                    (index === array.length - 1 ? '<thead>' : '');
                            },
                            ''
                        );

                    // Init ID Column
                    var columns = [];

                    // Init Attribute Columns
                    for (let pos in vm.fields) {
                        let columnName = vm.fields[pos];
                        columns.push({
                            orderable: false,
                            visible: true,
                            defaultContent: '',
                            render: function (data, type, row, meta) {
                                return row.attributes[columnName];
                            }
                        });
                    }

                    // Role Name
                    columns.push({
                        orderable: false,
                        visible: true,
                        render: function (data, type, row, meta) {
                            return row.roleName;
                        }
                    });

                    // Delete Button
                    columns.push({
                        orderable: false,
                        visible: true,
                        render: function (data, type, row, meta) {
                            return '' +
                                '<span id="delete-button-' + row.id + '"' +
                                '    style="cursor:pointer;"' +
                                '    onclick="main.remove(' + row.id + ')" ' +
                                '    title="Click to delete">' +
                                '<i class = "fa fa-fw fa-trash"></i>' +
                                '</span>';
                        }
                    });

                    // (Re)Initialize Datatable
                    if (columns.length > 0) {
                        vm.dataTable = $('#ruleDT').DataTable({
                            dom: '<"myfilter"f><"mylength"l>tip',
                            data: vm.rules,
                            columns: columns
                        });
                    }

                    // Add Start, Stop and Delete Campaign Buttons
                    $('div.myfilter').append('' +
                        '<button id="addRuleButton" type="button" class="btn btn-primary btn-sm iconed-button" data-toggle="modal" data-target="#addRuleModal">' +
                        '    <i class="fa fa-fw fa-plus"></i>' +
                        '    <span>Add Rule</span>' +
                        '</button>' +
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
                    if ($('#addRuleModal').is(':visible') == false &&
                        $('#importModal').is(':visible') == false) {
                        $('div.dataTables_filter input').focus();
                    }

                },
                cancelAdd: function () {
                    $('#addRuleModal').modal('toggle');
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
