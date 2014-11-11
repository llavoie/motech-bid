(function () {
    'use strict';

    /* App Module */

    angular.module('bid', ['motech-dashboard', 'bid.controllers', 'bid.directives', 'ngCookies', 'ui.bootstrap', 'ngSanitize']).config(
    ['$routeProvider',
        function ($routeProvider) {
            $routeProvider.
                when('/bid/vax', {templateUrl: '../bid/resources/partials/vax.html', controller: 'VaxController'});
    }]);
}());
