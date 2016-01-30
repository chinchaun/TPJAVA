(function () {
    'use strict';
    var controllerId = 'dashboard';
    angular.module('app').controller(controllerId, ['common', 'datacontext', 'gameService', dashboard]);

    function dashboard(common, datacontext, gameService) {
        var getLogFn = common.logger.getLogFn;
        var log = getLogFn(controllerId);

        var vm = this;

        vm.title = 'Tablero';
        vm.playerLogin = {};
        vm.playersignUp = {};
        vm.player = undefined;
        vm.currentGame = 0;
        
        vm.getGame = getGame;
        vm.saveGame = saveGame;
        vm.logIn = logIn;
        vm.signUp = signUp;
        vm.getStoreGame = getStoreGame;
        activate();

        function activate() {
            var promises = [];
            common.activateController(promises, controllerId)
                .then(function () { log('Activated Dashboard View'); });
        }

        function getGame() { 
                 return gameService.getAll(vm.player.id, vm.id2).then(function (response) {
                   return vm.game = response.data;
                });
        }
        
        function getStoreGame() { 
                 return gameService.getById(false, vm.currentGame[0]).then(function (response) {
                   return vm.game = response.data;
                });
        }
        
         function saveGame() {       
                vm.game.white.id = vm.player.id;
                 return gameService.saveGame(vm.game).then(function (response) {
                   if(response.status === 200){
                       log('Partida Guardada con Exito!');
                   };
                });
        }
        
        function logIn(){
            return gameService.logIn(vm.playerLogin).then(function (response) {
                   if(response.status === 200){
                       vm.player = {};
                       vm.player.id = response.data.id;
                       vm.player.dni = response.data.dni;
                       vm.player.storedGames = response.data.storedGames;
                       log('Bienvenido: ' + vm.player.dni);
                   };
                   if(response.status === 401){
                       log('contraseñ o usuario incorrecto');
                   };
                });
        }
        
        function signUp(){
            return gameService.signUp(vm.playersignUp).then(function (response) {
                   if(response.status === 201){
                       vm.player = {};
                       vm.player.id = response.data;
                       vm.player.dni = vm.playersignUp.dni;
                       log('Te registraste con exito: ' + vm.player.dni);
                   }
                   else{
                       log('Hubo un problema creando el usuario');
                   };
                });
        }
    }
})();