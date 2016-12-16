"use strict";
angular.module("sportsClub").controller('managerBoardCtrl', function($scope,$http) {
    $scope.getTeams= function(){
        $http.get("http://localhost:8080/pa165/rest/club{" + managerId +"}/teams").then(
            function(response){
                $scope.clubs = response.data;
            },
            function(response){
                alert("error sending http request");
            });
    }

    $scope.getFreePlayersofClub= function(){
        $http.get("http://localhost:8080/pa165/rest/club{" + managerId +"}/freePlayers").then(
            function(response){
                $scope.freePlayers = response.data;
            },
            function(response){
                alert("error sending http request");
            });
    }

    $scope.getPlayersOfTeam= function(){
        $http.get("http://localhost:8080/pa165/rest/team{"+ teamId +"}/players").then(
            function(response){
                $scope.freePlayers = response.data;
            },
            function(response){
                alert("error sending http request");
            });
    }

    $scope.deletePlayer= function(){
        $http.delete("http://localhost:8080/pa165/rest/player{"+ playerId +"}").then(
            function(response){
                //?
            },
            function(response){
                alert("error sending http request");
            });
    }

    $scope.deleteTeam= function(){
        $http.delete("http://localhost:8080/pa165/rest/team{"+ teamId +"}").then(
            function(response){
                //?
            },
            function(response){
                alert("error sending http request");
            });
    }

});