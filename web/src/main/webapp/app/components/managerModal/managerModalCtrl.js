"use strict";
angular.module("sportsClub").controller('managerModalCtrl',function($scope, $uibModalInstance, $http, data) {
	if(data == null){
		$scope.data = {
				"id":null,
				"firstName":null,
				"lastName":null,
				"email":null,
				"password":null,
				"mobile":null,
				"clubName":null
		}
	}
	else{
		$scope.data = angular.copy(data);
	}
	
	$scope.passwordCheck = null;
	
	$scope.close = function(updatedData){
		$uibModalInstance.close(updatedData);
	}
	
	$scope.save = function(){
		if(!validManager(managerData)){
			return;
		}
		if($scope.manager.id == null){
			createNewManager($scope.data);
		}
		else{
			updateManager($scope.data);
		}
	}
	
	var createNewManager = function(managerData){
		$http.post(restInterface + '/manager',managerData).then(
				function(response){
					alert("Manager created");
					$scope.close(managerData);
				},
				function(){
					alert("error occured while creating manager");
				}
			);
	}
	
	var updateManager = function(managerData){
		$http.put(restInterface + '/manager',managerData).then(
				function(response){
					alert("Manager updated");
					$scope.close(managerData);
				},
				function(){
					alert("error occured while updating manager");
				}
			);
	}
	
	var validManager = function(managerData){
		if(managerData.password.length < 6){
			alert("password must contain at least 6 characters");
			return false;
		}
		if(managerData.password != $scope.passwordCheck){
			alert("passwords doesn't match");
			return false;
		}
		if(!(/(\+|00)?\d+/.test(managerData.mobile))){
			alert("invalid mobile phone format");
			return false;
		}
		return true;
	}
	
});