initVue();

function initVue() {
    main = new Vue({
        el: "#vue",
        data: {
            loaded: false,
            executionId: '',
            applicationId: 0,
            status: '',
            startDt: '',
            completedDt: '',
            accountsProcessed: 0,
            rulesProcessed: 0,
            whitelistProcessed: 0,
            whitelistcompliant: 0,
            normalProcessed: 0,
            whitelistCompliant: 0,
            whitelistNonCompliant: 0,
            normalCompliant: 0,
            normalNonCompliant: 0,
            chart: null
        },
        computed: {
            startDate: function () {
                const vm = this;
                return vm.formatDate(vm.startDt);
            },
            endDate: function () {
                const vm = this;
                return vm.formatDate(vm.completedDt);
            },
            compliant: function () {
                const vm = this;
                return vm.whitelistCompliant +
                    vm.whitelistNonCompliant +
                    vm.normalCompliant;
            },
            nonCompliant: function () {
                const vm = this;
                return vm.accountsProcessed - vm.compliant;
            },
            violationsHref: function () {
                const vm = this;
                return '/umx/system/executions/' + vm.executionId + '/violations/export';
            },
            completed: function () {
                const vm = this;
                return vm.status.localeCompare('COMPLETED') === 0;
            }
        },
        mounted: function () {
            const vm = this;
            const executionId = (new URLSearchParams(window.location.search)).get('executionId');
            console.log(`executionId: ${executionId}`);
            axios.get('/umx/system/executions/' + executionId).then(
                function success(response) {
                    const executionData = response.data.execution;
                    vm.executionId = executionData.id || vm.executionId;
                    vm.applicationId = executionData.applicationId || vm.applicationId;
                    vm.status = executionData.status || vm.status;
                    vm.startDt = executionData.startDt || vm.startDt;
                    vm.completedDt = executionData.completedDt || vm.completedDt;

                    if (executionData.hasOwnProperty('statistic')) {
                        vm.loaded = true;
                        vm.accountsProcessed = executionData.statistic.accountsProcessed || vm.accountsProcessed;
                        vm.rulesProcessed = executionData.statistic.rulesProcessed || vm.rulesProcessed;
                        vm.whitelistProcessed = executionData.statistic.whitelistProcessed || vm.whitelistProcessed;
                        vm.whitelistcompliant = executionData.statistic.whitelistcompliant || vm.whitelistcompliant;
                        vm.normalProcessed = executionData.statistic.normalProcessed || vm.normalProcessed;
                        vm.whitelistCompliant = executionData.statistic.whitelistCompliant || vm.whitelistCompliant;
                        vm.whitelistNonCompliant = executionData.statistic.whitelistNonCompliant || vm.whitelistNonCompliant;
                        vm.normalCompliant = executionData.statistic.normalCompliant || vm.normalCompliant;
                        vm.normalNonCompliant = executionData.statistic.normalNonCompliant || vm.normalNonCompliant;
                    }

                    vm.initChart();
                }, function error(response) {
                    // replace page with 404
                    if (response.response.status == 404) {
                        axios.get('/404').catch(
                            function (error) {
                                const notFoundHtml = error.response.data;
                                document.write(notFoundHtml);
                            }
                        );
                    }
                }
            );
        },
        methods: {
            initChart: function () {
                const vm = this;
                var ctxWhitelist = document.getElementById('dispensationChart').getContext('2d');
                var dispensationChart = new Chart(ctxWhitelist, {
                    type: 'pie',
                    data: {
                        labels: ['Whitelisted', 'Not Whitelisted'],
                        datasets: [{
                            label: '# of Users',
                            data: [
                                vm.whitelistProcessed,
                                vm.normalProcessed
                            ],
                            backgroundColor: [
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 99, 132, 0.2)'
                            ],
                            borderColor: [
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 99, 132, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        events: false,
                        animation: {
                            duration: 500,
                            easing: "easeOutQuart",
                            onComplete: showNumberAndPercentage
                        }
                    }
                });

                var ctxCompliant = document.getElementById('compliantChart').getContext('2d');
                var compliantChart = new Chart(ctxCompliant, {
                    type: 'pie',
                    data: {
                        labels: ['Compliant', 'Not Compliant'],
                        datasets: [{
                            label: '# of Users',
                            data: [
                                vm.compliant,
                                vm.nonCompliant
                            ],
                            backgroundColor: [
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 99, 132, 0.2)'
                            ],
                            borderColor: [
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 99, 132, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        events: false,
                        animation: {
                            duration: 500,
                            easing: "easeOutQuart",
                            onComplete: showNumberAndPercentage
                        }
                    }
                });

                var ctxWhitelistRule = document.getElementById('dispensationRuleChart').getContext('2d');
                var dispensationRuleChart = new Chart(ctxWhitelistRule, {
                    type: 'horizontalBar',
                    data: {
                        labels: [
                            'Whitelisted, compliant',
                            'Whitelisted, non-compliant',
                            'Normal, compliant',
                            'Normal, non-compliant'
                        ],
                        datasets: [{
                            data: [
                                vm.whitelistCompliant,
                                vm.whitelistNonCompliant,
                                vm.normalCompliant,
                                vm.normalNonCompliant
                            ],
                            backgroundColor: [
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(94, 180, 237, 0.2)',
                                'rgba(157, 208, 242, 0.2)',
                                'rgba(255, 179, 193, 0.2)'
                            ],
                            borderColor: [
                                'rgba(54, 162, 235, 1)',
                                'rgba(94, 180, 237, 1)',
                                'rgba(157, 208, 242, 1)',
                                'rgba(255, 179, 193, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        events: false,
                        legend: {
                            display: false
                        },
                        tooltips: {
                            enabled: false
                        },
                        hover: {
                            animationDuration: 0
                        },
                        animation: {
                            duration: 1,
                            onComplete: function () {
                                var chartInstance = this.chart,
                                    ctx = chartInstance.ctx;
                                ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                                ctx.textAlign = 'center';
                                ctx.textBaseline = 'bottom';

                                this.data.datasets.forEach(function (dataset, i) {
                                    var meta = chartInstance.controller.getDatasetMeta(i);
                                    meta.data.forEach(function (bar, index) {
                                        var data = dataset.data[index];
                                        ctx.fillText(data, bar._model.x, bar._model.y + 8);
                                    });
                                });
                            }
                        }
                    }
                });
            },
            formatDate: function (dateText) {
                return moment(dateText, 'YYYY-MM-DD HH:mm:ss')
                    .format('dddd, Do MMMM YYYY, HH:mm:ss')
                    .toString();
            }
        }
    });
}

function showNumberAndPercentage() {
    const vm = this;
    var ctx = vm.chart.ctx;
    ctx.textAlign = 'center';
    ctx.textBaseline = 'bottom';

    this.data.datasets.forEach(function (dataset) {

        for (var i = 0; i < dataset.data.length; i++) {
            var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model,
                total = dataset._meta[Object.keys(dataset._meta)[0]].total,
                mid_radius = model.innerRadius + (model.outerRadius - model.innerRadius) / 2,
                start_angle = model.startAngle,
                end_angle = model.endAngle,
                mid_angle = start_angle + (end_angle - start_angle) / 2;

            var x = mid_radius * Math.cos(mid_angle);
            var y = mid_radius * Math.sin(mid_angle);

            var val = dataset.data[i];
            var percent = String(Math.round(val / total * 100)) + "%";

            if (val != 0) {
                ctx.fillText(dataset.data[i], model.x + x, model.y + y);
                // Display percent in another line, line break doesn't work for fillText
                ctx.fillText(percent, model.x + x, model.y + y + 15);
            }
        }
    });
}
