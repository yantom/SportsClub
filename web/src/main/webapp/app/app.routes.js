"use strict";
angular.module('sportsClub').config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise("/home");
	$stateProvider
    .state('home', {
        url: '/home',
        templateUrl: 'app/components/home/home.html',
        controller: "homeCtrl"
    });
});