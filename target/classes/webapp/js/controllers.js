(function () {
    'use strict';

    var controllers = angular.module('bid.controllers', []);

/*
    controllers.controller('VaxController', function ($scope, $http) {
        $http.get('../bid/vax')
            .success(function(response) {
                $scope.message = response;
            })
            .error(function(response) {
                $scope.message = 'request failed';
            });

    });
*/

    controllers.controller('VaxController', function ($scope, $http) {
        $scope.log = [];

        innerLayout({
            spacing_closed: 30,
            east__minSize: 200,
            east__maxSize: 350
        }, {
            show: true,
            button: '#patient-filters'
        });
    });




}());