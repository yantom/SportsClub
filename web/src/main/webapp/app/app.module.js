"use strict";
angular.module('sportsClub', ['ui.router','ui.bootstrap']).config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);