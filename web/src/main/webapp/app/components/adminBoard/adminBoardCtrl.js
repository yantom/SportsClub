"use strict";
angular.module("sportsClub").controller('adminBoardCtrl',
		function($scope, $http, AdminBoardService) {
			$scope.$on('clubsLoaded', function() {
				$scope.clubs = AdminBoardService.getClubs();
			});
		});