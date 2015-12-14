(function () {
    'use strict';
    var controllerId = 'pacientesCtrl';
    angular.module('app').controller(controllerId, ['common', 'pacienteService', pacientesCtrl]);

    function pacientesCtrl(common, pacienteService) {
        var getLogFn = common.logger.getLogFn;
        var log = getLogFn(controllerId);

        var vm = this;
        //Propoerties
        vm.title = 'Paciente';
        vm.mostrarABM = false;
        vm.isModification = false;
        vm.currentPaciente = {
            cod_paciente: '',
            apellido: 'aaaaa',
            nombre: '',
            fechaNacimiento: '',
            nroObraSocial: ''
        };

        //Behaviour
        vm.crearPaciente = crearPaciente;
        vm.modificarPaciente = modificarPaciente;
        vm.guardarPaciente = guardarPaciente;
        vm.selectChange = selectChange;

        activate();

        function activate() {
            var promises = [getPacientes()];
            common.activateController([], controllerId)
                .then(function () { log('Activated Paciente View'); });
        }

        function getPacientes() {
            pacienteService.getAll().then(function (response) {
                vm.pacientes = response.data;
            });
        };

        function crearPaciente() {
            vm.currentPaciente.cod_paciente = '';
            vm.currentPaciente.apellido = '';
            vm.currentPaciente.nombre = '';
            vm.currentPaciente.fechaNacimiento = '';
            vm.currentPaciente.nroObraSocial = '';
            vm.isModification = false;
            vm.mostrarABM = true;
        };

        function modificarPaciente() {
            vm.currentPaciente.cod_paciente = '';
            vm.currentPaciente.apellido = 'aaaaaa';
            vm.currentPaciente.nombre = '';
            vm.currentPaciente.fechaNacimiento = '';
            vm.currentPaciente.nroObraSocial = '';
            vm.isModification = true;
            vm.mostrarABM = true;
        };

        function guardarPaciente() {
           // console.log(vm.currentPaciente);
            if (vm.isModification) {
                return pacienteService.update(vm.currentPaciente)
                .then(function (data) {
                    console.log(data);
                });
            }
            else {
                return pacienteService.save(vm.currentPaciente)
                .then(function (data) {
                    console.log(data);
                });
            };
        };

        function selectChange(paciente) {
            vm.currentPaciente = JSON.parse(paciente);
        };


    }
})();