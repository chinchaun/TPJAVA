(function () {
    'use strict';

    var serviceId = 'estudioService';
    angular.module('app').factory(serviceId,
            ['common', '$http', estudioService]);

    function estudioService(common, $http) {
        var $q = common.$q;

        var service = {
            getAll: getAll,
            getById: getById,
            update: update,
            save: save
        };

        return service;

        function getAll() {

            var url = 'api/Estudio';

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
            var url = 'api/Estudio/GetById?' + data;

            return $http.get(url)
                        .success(function (response) {
                            if (response) {
                                return response;
                            }
                        });

        };

        function update(estudio) {

            var url = 'api/Estudio/Update';
            return $http.put(url, estudio)
                        .success(function (response) {
                            if (response) {
                                return response;
                            };
                        });

        };

        function save(estudio) {

            var url = 'api/Estudio/Save';
            return $http.post(url, estudio)
                        .success(function (response) {
                            if (response) {
                                return response;
                            };
                        });

        };



    };

})();