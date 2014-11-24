(function () {
    'use strict';

    var controllers = angular.module('bid.controllers', []);


    controllers.controller('VaxCtrl', function ($scope, $rootScope, $http) {
        innerLayout({
            spacing_closed: 30,
            east__minSize: 200,
            east__maxSize: 350
        }, {
            show: true,
            button: '#patient-filters'
        });

        $scope.count = 0;

        $scope.showSchedule = function() {
            //$scope.count = count + 1;
            $('#vaxScheduleModal').modal('show');
        };

    });


}());