$(document).ready(function () {
    initVue();
});

function initVue() {
    main = new Vue({
        el: '#vue',
        data: {
            username: null,
            password: null,
            authenticated: null
        },
        mounted: function () {
            this.session();
        },
        methods: {
            session: function () {
                const vm = this;
                axios.get('/umx/system/authentication/session')
                    .then(function (response) {
                        location.href = 'index';
                    }).catch(function (error) {
                        vm.authenticated = false;
                        console.log(error);
                    });
            },
            login: function () {
                const vm = this;
                axios.post(
                    '/umx/system/authentication/login?random=' + Math.random(), {
                    username: vm.username,
                    password: vm.password
                }).then(function (response) {
                    location.href = 'index';
                }).catch(function (error) {
                    notifyError('Failed login', 'Wrong username and/or password');
                    console.log(error);
                });

                vm.password = "";

                if (vm.$refs.username.value == "") {
                    vm.$refs.username.focus();
                } else {
                    vm.$refs.password.focus();
                }
            },
            logout: function () {
                const vm = this;
                axios.get('/umx/system/authentication/logout')
                    .then(function (response) {
                        vm.session();
                    }).catch(function (error) {
                        vm.session();
                        notifyError('Network Error', 'Logout failed');
                        console.log(error);
                    });
            }
        }
    });
}
