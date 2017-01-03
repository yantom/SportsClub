"use strict";
angular.module('sportsClub', ['ui.router','ui.bootstrap','angular-jwt']);
angular.module('sportsClub').config(function($httpProvider,jwtInterceptorProvider) {
	jwtInterceptorProvider.tokenGetter = function(){return sessionStorage.getItem('jwt');};
    $httpProvider.interceptors.push('jwtInterceptor');
});
angular.module('sportsClub').run(function($rootScope,$state) {
	$rootScope.logout = function(){
		sessionStorage.removeItem("jwt");
		$state.go("home");
	}
	$rootScope.handleErrors = function(response){
		switch(response.status){
			case 403:
				$rootScope.logout();
				break;
			case 500:
				alert("Error occured on the server side.");
				break;
			default:
				alert("Internet connection problem.");
		}
	}
});