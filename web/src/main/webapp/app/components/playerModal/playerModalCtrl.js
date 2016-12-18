"use strict";
angular.module("sportsClub").controller('playerBoardCtrl', function ($scope, $http, $uibModalInstance) {

    $scope.data = {
        "id": null,
        "firstName": null,
        "lastName": null,
        "email": null,
        "password": null,
        "mobile": null,
        "weight": null,
        "heigth": null
    }
    
    $scope.jersey = {
        "jersey":null
    }
    
    $scope.selected = [];
    
    $scope.teams;
    
    $scope.toggleSelection = function toggleSelection(selectedTeam) {
        var idx = $scope.selected.indexOf(selectedTeam);

        if (idx > -1) {
          $scope.selected.splice(idx, 1);
        }

        else {
          $scope.selected.push(selectedTeam);
        }
      };
    
    var getTeams= function(managerId){
        $http.get(restInterface + "/manager/"+managerId+"/teams").then(
            function(response){
                $scope.teams = response.data;
            },
            function(err){
                alert("error sending http request");
            });
    }
    
    $scope.close = function (updatedData) {
        $uibModalInstance.close(updatedData);
    }

    $scope.save = function () {
        if(!validEmail()){
			return;
		}
        if(!validMobile()){
			return;
		}
        if(!validFirstName()){
			return;
		}
        if(!validLastName()){
			return;
		}
        createNewPlayer($scope.data);
        asignPlayer();
    }
    
    //asings player to team
    var asignPlayer = function(){
        //TO DO
    }
    
    //checks if jersey is already used in a team
    $scope.checkNumber = function() {
    }
    
    var validFirstName = function(){
        if($scope.data.firstName.lenght < 2){
            alert("Name must be atleast 2 characters long");
            return false;
        }
        return true;
    }
    
    var validLastName = function(){
        if($scope.data.lastName.lenght < 2){
            alert("Last name must be atleast 2 characters long");
            return false;
        }
        return true;
    }
    
    var validEmail = function() {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(!re.test($scope.data.email)){
            alert("invalid email format");
            return false;
        }
        return true;
    }
    
    var validMobile = function(){
		if(!(/^(\+|00)?\d+$/.test($scope.data.mobile))){
			alert("invalid mobile phone format");
			return false;
		}
		return true;
	}
    
    var createNewPlayer = function (playerData) {
        $http.post(restInterface + '/player', playerData).then(
                function (response) {
                    alert("Player created");
                    $scope.close(playerData);
                },
                function () {
                    alert("error occured while creating player");
                }
        );
    }
    
    var init = function(){
        getTeams(10000);
    }

    init();
});


