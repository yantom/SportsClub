"use strict";
angular.module("sportsClub").controller('adminBoardCtrl',function($scope, $uibModal,$http) {
	$scope.managers;
	
	$scope.deleteManager = function(managerId){
		$http.remove(restInterface + '/manager/'+managerId).then(
			function(){
				console.log(managers.length);
				for(var i=0; i < managers.length; i++){
					if($scope.managers[i].id == managerId){
						$scope.managers.splice(i,1);
						break;
					}
				}
				alert("Club deleted");
			},
			function(){
				alert("error occured while deleting manager");
			}
		);
	}
	
	$scope.openManagerModal = function(managerData){
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
				loadManagers();
			}, function () {
			});
	}
	
	var loadManagers = function(){
		$http.get(restInterface + '/manager/findall').then(
			function(response) {
				$scope.managers = response.data;
			},
			function(err) {
				alert("error occured while retrieving all managers");
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
		loadManagers();
	}
	
	init();
});