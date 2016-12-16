"use strict";
angular.module('sportsClub').factory(
		'AdminBoardService',
		function($http, $rootScope) {
			var clubs;
			var init = function() {
				$http.get(restInterface + '/club/findall').then(
						function(response) {
							clubs = response.data;
							$rootScope.$broadcast('clubsLoaded');
						}, function() {
							alert("error occured while retrieving all clubs");
						});
			};
			var getClubs = function() {
				return clubs;
			}
			init();
			return {
				getClubs : getClubs
			}
		});