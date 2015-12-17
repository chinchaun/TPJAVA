(function () {
    'use strict';

    var serviceId = 'gameService';
    angular.module('app').factory(serviceId,
            ['common', '$http', gameService]);

    function gameService(common, $http) {
        var $q = common.$q;

        var service = {
            getAll: getAll,
            saveGame: saveGame
        };

        return service;

        function getAll(id1, id2) {
                var data = $.param({
                id1: id1,
                id2: id2
            });
            var url = 'http://localhost:8080/JAVAUTNWEB/game?' + data;

            return $http.get(url)
                        .success(function (response) {
                            if (response) {
                                return response;
                            }
                        });

        };
        
        function saveGame(game) {
            var url = 'http://localhost:8080/JAVAUTNWEB/game';
            return $http.post(url, game)
                        .success(function (response) {
                            if (response) {
                                return response;
                            };
                        });
        };
    };

})();