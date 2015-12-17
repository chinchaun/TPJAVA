(function () {
    'use strict';
    var controllerId = 'dashboard';
    angular.module('app').controller(controllerId, ['common', 'datacontext', 'gameService', dashboard]);

    function dashboard(common, datacontext, gameService) {
        var getLogFn = common.logger.getLogFn;
        var log = getLogFn(controllerId);

        var vm = this;
        vm.news = {
            title: 'Hot Towel Angular',
            description: 'Hot Towel Angular is a SPA template for Angular developers.'
        };
        vm.messageCount = 0;
        vm.people = [];
        vm.title = 'Dashboard';
        vm.id1 = 0;
        vm.id2 = 0;
        
        vm.getGame = getGame;
        vm.saveGame = saveGame;
        activate();

        function activate() {
            var promises = [getMessageCount()];
            common.activateController(promises, controllerId)
                .then(function () { log('Activated Dashboard View'); });
        }

        function getMessageCount() {
            return datacontext.getMessageCount().then(function (data) {
                return vm.messageCount = data;
            });
        }

        function getGame() { 
                 return gameService.getAll(vm.id1, vm.id2).then(function (response) {
                   return vm.game = response.data;
                });
        }
        
         function saveGame() {       
                 return gameService.saveGame(vm.game).then(function (response) {
                   return vm.game = response.data;
                });
        }
    }
})();