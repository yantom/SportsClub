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
				"club":{
					"id":null,
					"name":null
				}
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
		console.log("saving fn");
		if(!validClub(managerData)){
			console.log("not valid");
			return;
		}
		if($scope.manager.id == null){
			console.log("creating");
			createNewClub($scope.data);
		}
		else{
			console.log("updating");
			updateClub($scope.data);
		}
	}
	
	var createNewClub = function(managerData){
		$http.post(restInterface + '/club',managerData).then(
				function(response){
					alert("Club created");
					$scope.close(managerData);
				},
				function(){
					alert("error occured while creating club");
				}
			);
	}
	
	var updateClub = function(managerData){
		$http.put(restInterface + '/club',managerData).then(
				function(response){
					alert("Club updated");
					$scope.close(managerData);
				},
				function(){
					alert("error occured while updating club");
				}
			);
	}
	
	var validClub = function(managerData){
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