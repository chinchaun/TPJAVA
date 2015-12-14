(function () {
    'use strict';

    var serviceId = 'pacienteService';
    angular.module('app').factory(serviceId,
            ['common', '$http', pacienteService]);

    function pacienteService(common, $http) {
        var $q = common.$q;

        var service = {
            getAll: getAll,
            getById: getById,
            update: update,
            save: save
        };

        return service;

        function getAll() {

            var url = 'api/Paciente';

            return $http.get(url)
                        .success(function (response) {
                            if (response) {
                                return response;
                            }
                        });

        };

        function getById(id) {
            var data = $.param({
                id: id
            });
            var url = 'api/Paciente/GetById?' + data;

            return $http.get(url)
                        .success(function (response) {
                            if (response) {
                                return response;
                            }
                        });

        };

        function update(paciente) {

            var url = 'api/Paciente/Update';
            return $http.put(url, paciente)
                        .success(function (response) {
                            if (response) {
                                return response;
                            };
                        });

        };

        function save(paciente) {

            var url = 'api/Paciente/Save';
            return $http.post(url, paciente)
                        .success(function (response) {
                            if (response) {
                                return response;
                            };
                        });

        };

    };

})();