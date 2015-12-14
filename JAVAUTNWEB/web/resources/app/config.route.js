(function () {
    'use strict';

    var app = angular.module('app');

    // Collect the routes
    app.constant('routes', getRoutes());
    
    // Configure the routes and route resolvers
    app.config(['$routeProvider', 'routes', routeConfigurator]);
    function routeConfigurator($routeProvider, routes) {

        routes.forEach(function (r) {
            $routeProvider.when(r.url, r.config);
        });
        $routeProvider.otherwise({ redirectTo: '/' });
    }

    // Define the routes 
    function getRoutes() {
        return [{
                url: '/',
                config: {
                    templateUrl: 'resources/app/dashboard/dashboard.html',
                    title: 'dashboard',
                    settings: {
                        nav: 1,
                        content: '<i class="fa fa-dashboard"></i> Dashboard'
                    }
                }
            },
            {
                url: '/admin',
                config: {
                    title: 'admin',
                    templateUrl: 'resources/app/admin/admin.html',
                    settings: {
                        nav: 2,
                        content: '<i class="fa fa-lock"></i> Admin'
                    }
                }
            },
            {
                url: '/estudios',
                config: {
                    title: 'estudios',
                    templateUrl: 'resources/app/estudios/estudios.html',
                    settings: {
                        nav: 3,
                        content: '<i class="fa fa-dashboard"></i> Estudios'
                    }
                }
            },
            {
                url: '/pacientes',
                config: {
                    title: 'pacientes',
                    templateUrl: 'resources/app/pacientes/pacientes.html',
                    settings: {
                        nav: 4,
                        content: '<i class="fa fa-dashboard"></i> Pacientes'
                    }
                }
            }
        ];
    }
})();