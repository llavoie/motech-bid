(function () {
    'use strict';

    var controllers = angular.module('bid.controllers', []);

    controllers.controller('VaxController', function ($scope, $http) {
        $http.get('../bid/vax')
            .success(function(response) {
                $scope.message = response;
            })
            .error(function(response) {
                $scope.message = 'request failed';
            });

    });

}());