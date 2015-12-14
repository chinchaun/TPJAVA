(function () {
    'use strict';
    var controllerId = 'admin';
    angular.module('app').controller(controllerId, ['common', 'estudioService', admin]);

    function admin(common, estudioService) {
        var getLogFn = common.logger.getLogFn;
        var log = getLogFn(controllerId);

        var vm = this;
        vm.title = 'Vacunas';

        activate();

        function activate() {
            var promises = [getEstudios()];
            common.activateController([], controllerId)
                .then(function () { log('Activated Admin View'); });
        }

        function getEstudios() {
            estudioService.getAll().then(function (response) {
                vm.estudios = response.data;
            });
        };
    }
})();