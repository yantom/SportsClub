"use strict";
angular.module("sportsClub").controller('adminBoardCtrl',function($scope, $uibModal,$http) {
	$scope.clubs;
	
	$scope.clickOnClub = function(clubId){
		$http.get(restInterface + '/club/findall').then(
			function(response){
				openManagerModal(response.data);
			},
			function(){
				alert("error occured while retrieving manager");
			}
		);
	}
	
	$scope.createNewClub = function(){
		openManagerModal(null);
	}
	
	$scope.deleteClub = function(clubId){
		$http.delete(restInterface + '/club/'+clubId).then(
			function(response){
				for(i=0; i<clubs.size;i++){
					if($scope.clubs[i].id = clubId){
						$scope.clubs.splice(i,1)
						break;
					}
				}
				alert("Club deleted");
			},
			function(){
				alert("error occured while deleting club");
			}
		);
	}
	
	var openManagerModal = function(managerData){
		var modalInstance = $uibModal.open({	
			templateUrl : 'app/components/managerModal/managerModal.html',
			controller : 'managerModalCtrl',
			resolve : {
				data : function() {
					return managerData;
				}
			}
		});
		modalInstance.result.then(function(updatedData) {
				loadClubs();
			}, function () {
			});
	}
	
	var loadClubs = function(){
		$http.get(restInterface + '/club/findall').then(
			function(response) {
				$scope.clubs = response.data;
			},
			function(err) {
				alert("error occured while retrieving all clubs");
			}
		);
	}
	
	//
	var someData = {
		      "type": "dataFromCtrl",
		      "usedOften": true
		    }
	$scope.sendStaticJSON = function(dataFromUI){
		$state.go("testView",(dataFromUI));
	}
	$scope.sendDataStoredInController = function(){
		$state.go("testView",(someData));
	}
	
	var init = function(){
		loadClubs();
	}
	
	init();
});