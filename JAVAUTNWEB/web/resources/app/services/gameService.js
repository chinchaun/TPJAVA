(function () {
    'use strict';

    var serviceId = 'gameService';
    angular.module('app').factory(serviceId,
            ['common', '$http', gameService]);

    function gameService(common, $http) {
        var $q = common.$q;

        var service = {
            getAll: getAll,
            saveGame: saveGame,
            logIn: logIn,
            signUp: signUp,
            getById: getById,
        };

        return service;

        function getAll(id1, id2) {
                var data = $.param({
                id1: id1,
                id2: id2,
                isNewGame: true,
            });
            var url = 'http://localhost:8080/JAVAUTNWEB/game?' + data;

            return $http.get(url)
                        .success(function (response) {
                            if (response) {
                                return response;
                            }
                        });

        };
        
        function getById(isNewGame, gameId) {
                var data = $.param({
                isNewGame: isNewGame,
                gameId: gameId
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
            var board = {
                game: game
            };
            return $http.post(url, board,{
                    contentType: 'application/json'
                        })
                        .success(function (response) {
                            if (response) {
                                return response;
                            };
                        });
        };
        
        function logIn(user) {
            var url = 'http://localhost:8080/JAVAUTNWEB/user';
            var data = {
                method: 'login',
                user: user
            };
            return $http.post(url, data,{
                    contentType: 'application/json'
                        })
                        .success(function (response) {
                            if (response) {
                                return response;
                            };
                        });
        };
        
        function signUp(user) {
            var url = 'http://localhost:8080/JAVAUTNWEB/user';
            var data = {
                method: 'signUp',
                user: user
            };
            return $http.post(url, data,{
                    contentType: 'application/json'
                        })
                        .success(function (response) {
                            if (response) {
                                return response;
                            };
                        });
        };
    };

})();