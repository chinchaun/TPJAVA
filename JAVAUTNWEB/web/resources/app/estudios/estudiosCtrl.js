(function () {
    'use strict';
    var controllerId = 'estudiosCtrl';
    angular.module('app').controller(controllerId, ['common', 'estudioService', estudiosCtrl]);

    function estudiosCtrl(common, estudioService) {
        var getLogFn = common.logger.getLogFn;
        var log = getLogFn(controllerId);

        var vm = this;
        //Propoerties
        vm.title = 'Estudio';
        vm.mostrarABM = false;
        vm.isModification = false;
        vm.currentEstudio = {
            cod_estudio: '',
            desc_estudio: ''
        };

        //Behaviour
        vm.crearEstudio = crearEstudio;
        vm.modificarEstudio = modificarEstudio;
        vm.guardarEstudio = guardarEstudio;

        activate();

        function activate() {
            var promises = [getEstudios()];
            common.activateController([], controllerId)
                .then(function () { log('Activated Estudio View'); });
        }

        function getEstudios() {
            estudioService.getAll().then(function (response) {
                vm.estudios = response.data;
            });
        };

        function crearEstudio() {
            vm.currentEstudio.cod_estudio = '';
            vm.currentEstudio.desc_estudio = '';
            vm.isModification = false;
            vm.mostrarABM = true;
        };

        function modificarEstudio() {
            vm.currentEstudio.cod_estudio = '';
            vm.currentEstudio.desc_estudio = '';
            vm.isModification = true;
            vm.mostrarABM = true;
        };

        function guardarEstudio() {
            if (vm.isModification) {
                return estudioService.update(vm.currentEstudio)
                .then(function (data) {
                    console.log(data);
                });
            }
            else {
                return estudioService.save(vm.currentEstudio)
                .then(function (data) {
                    console.log(data);
                });
            };  
        };


    }
})();