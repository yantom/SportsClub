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
			case 401:
				alert("Unauthorized call: you will be log out.");
				$rootScope.logout();
				break;
			case 404:
				alert("Requested resource was not found in our database.");
				break;
			case 409:
				alert("Data conflict. Maybe the email/phone number you have entered is already assigned to some other person.");
				break;
			case 500:
				alert("Oops. Error occured on the server side. Please try again later.");
				break;
			default:
				alert("Internet connection problem.");
		}
	}
});