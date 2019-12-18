var storedApplicationIdName = 'umx.application.id';
var storedApplicationId = '';
var dateStart = moment().subtract(1, 'months').toISOString();
var dateEnd = moment().toISOString();
var API_POLLING_RATE = 30000;

$(document).ready(function () {
    storedApplicationId = localStorage.getItem(storedApplicationIdName) || '';
    initVue();

    $('#dateRange').daterangepicker({
        endDate: new Date(),
        startDate: new Date((new Date()).setMonth((new Date()).getMonth() - 1)),
        locale: {
            format: "DD/MM/YYYY"
        }
    }, function (start, end, label) {
        dateStart = start.toISOString();
        dateEnd = end.toISOString();
    });
});

function initVue() {
    main = new Vue({
        el: '#vue',
        data: function () {
            return {
                applicationId: storedApplicationId,
                applications: [],
                activeApplications: [],
                table: null,
                pollingExecutionStatus: null
            };
        },
        mounted: function () {
            const vm = this;
            vm.listApplication();
        },
        created: function () {
            const vm = this;

            vm.updateRunButton();
            vm.pollingExecutionStatus = setInterval(
                vm.updateRunButton,
                API_POLLING_RATE
            );
        },
        methods: {
            updateRunButton: function () {
                const vm = this;
                vm.fetchActiveApplications()
                    .then(vm.toggleRunButton)
                    .catch();
            },
            fetchActiveApplications: function () {
                const vm = this;

                return new Promise(function (resolve, reject) {

                    if (!vm.applicationId) {
                        reject('No application selected');
                        return;
                    }

                    axios.get(
                        '/umx/system/executions/applications/' + vm.applicationId + '/active'
                    ).then(function (response) {
                        vm.activeApplications = response.data.executions;
                        resolve(vm.activeApplications);
                    }).catch(function (error) {
                        console.log('Failed to get active applications');
                        console.warn(error);
                        reject(error);
                    });
                });
            },
            applicationChanged: function () {
                const vm = this;
                vm.list();
                localStorage.setItem(storedApplicationIdName, vm.applicationId);
                vm.activeApplications = [];
                vm.disableRunButton();
                vm.updateRunButton();
            },
            listApplication: function () {
                const vm = this;
                axios.get('/umx/system/applications')
                    .then(function (response) {
                        const applications = JSON.parse(JSON.stringify(response.data.applications));
                        vm.applications = applications;
                        vm.list();
                    }).catch(function (error) {
                        notifyError('Network Error', 'Failed to get application data');
                        console.log(error);
                    });
            },
            list: function () {
                const vm = this;

                if ($.fn.DataTable.isDataTable('#executionHistoryTable')) {
                    vm.table.destroy();
                }

                vm.table = $("#executionHistoryTable").DataTable({
                    dom: '<"myfilter"f><"mylength"l>tip',
                    processing: true,
                    ajax: {
                        url: '/umx/system/executions',
                        type: 'POST',
                        contentType: 'application/json',
                        data: function () {
                            return JSON.stringify({
                                startDate: dateStart,
                                endDate: dateEnd,
                                applicationId: vm.applicationId
                            });
                        },
                        dataSrc: 'executions'
                    },
                    order: [
                        [0, 'desc']
                    ],
                    language: {
                        searchPlaceholder: 'Search listed history ...'
                    },
                    initComplete: function () {
                        if (!document.getElementById('executionRunButton')) {
                            $('.myfilter').append('' +
                                `<button id="executionRunButton"` +
                                `    class="btn btn-sm btn-success"` +
                                `    onclick="main.run()"` +
                                `    disabled>` +
                                `    <i class="fa fa-spin fa-spinner"></i>` +
                                `    <span>Run</span>` +
                                `</button>`
                            );
                            vm.toggleRunButton();
                        }
                    },
                    columnDefs: [{
                        targets: [0],
                        defaultContent: '',
                        render: (data, type, row) => {
                            const execution = row;

                            if (execution.status.localeCompare('COMPLETED') === 0 &&
                                type === 'display') {
                                const reportUrl = `/umx/executions/report?executionId=` + execution.id;
                                return `<a href="${reportUrl}">${row.name}</a>`;
                            }

                            return row.name;
                        }
                    },
                    {
                        targets: [1],
                        defaultContent: '',
                        render: function (data, type, row) {
                            return row.status;
                        }
                    }, {
                        targets: [2],
                        defaultContent: '',
                        render: (data, type, row) => {
                            return row.startDt;
                        }
                    }, {
                        targets: [3],
                        defaultContent: '',
                        render: (data, type, row) => {
                            return row.completedDt;
                        }
                    },
                    {
                        targets: [4],
                        defaultContent: '',
                        sortable: false,
                        className: 'text-center ',
                        width: "120px",
                        render: function (data, type, row) {
                            const execution = row;
                            if (row.status.localeCompare('COMPLETED') === 0) {
                                return `
                                <a href="/umx/system/executions/${row.id}/violations/export"
                                    title="Click to download">
                                    <span class="fa fa-fw fa-download"></span>
                                </a>`;
                            }
                            return '';
                        }
                    },
                    {
                        targets: [5],
                        defaultContent: '',
                        sortable: false,
                        className: 'text-center ',
                        width: "120px",
                        render: function (data, type, row) {
                            const execution = row;
                            if (execution.status.localeCompare('COMPLETED') === 0) {
                                return '' +
                                    '<span onclick="main.remove(' + execution.id + ')"' +
                                    '     class="pointerCursor" title="Click to delete">' +
                                    '    <span class="fa fa-fw fa-trash"></span>' +
                                    '</span>';
                            }
                            return '';
                        }
                    },
                    ]
                });
            },
            remove: function (activityId) {
                const vm = this;
                axios.delete('/umx/system/executions/' + activityId)
                    .then(function (response) {
                        vm.list();
                        notifySuccess('Successfully removed executed activity');
                    }).catch(function (error) {
                        notifyError('Network Error', 'Failed to remove activity');
                        console.log(error);
                    });
            },
            run: function () {
                const vm = this;
                vm.disableRunButton();

                axios.post('/umx/system/executions/execute/' + vm.applicationId)
                    .then(function () {
                        vm.updateRunButton();
                        vm.list();
                    }).catch(function (error) {
                        console.log(error);
                        notifyError(error);
                    });
            },
            toggleRunButton: function () {
                const vm = this;
                if (vm.activeApplications.length === 0) {
                    vm.enableRunButton();
                } else {
                    vm.disableRunButton();
                }
            },
            enableRunButton: function () {
                $('#executionRunButton').prop('disabled', false);
                $('#executionRunButton i.fa').addClass('fa-play');
                $('#executionRunButton i.fa').removeClass('fa-spin');
                $('#executionRunButton i.fa').removeClass('fa-spinner');
            },
            disableRunButton: function () {
                $('#executionRunButton').prop('disabled', true);
                $('#executionRunButton i.fa').removeClass('fa-play');
                $('#executionRunButton i.fa').addClass('fa-spin fa-spinner');
            }
        },
        beforeDestroy: function () {
            clearInterval(this.pollingExecutionStatus);
        }
    });
}