"use strict";
angular.module("sportsClub").controller('managerBoardCtrl', function($scope,$http,$stateParams) {
    $scope.managers;
    $scope.players;
    $scope.teams;

    $scope.loggedManagerId = $stateParams;

    var getTeams= function(managerId){
        $http.get(restInterface + "/manager/"+managerId+"/teams").then(
            function(response){
                $scope.teams = response.data;
            },
            function(err){
                alert("error sending http request");
            });
    }

    $scope.getFreePlayersofClub= function(managerId){
        $http.get(restInterface + '/manager/' + managerId +'/freePlayers').then(
            function(response){
                $scope.freePlayers = response.data;
            },
            function(err){
                alert("error sending http request");
            });
    }

    var getPlayersOfTeam= function(teamId){
        $http.get(restInterface + "/team/"+ teamId +'/players').then(
        function(response){
                $scope.players = response.data;
        },
            function(err){
                alert("error sending http request");
            });
    }

    $scope.deletePlayer= function(playerId){
        $http.delete(restInterface + "/player/"+ playerId ).then(
            function(response){
                //?
            },
            function(err){
                alert("error sending http request");
            });
    }

    $scope.removePlayerFromRoster= function(teamId,playerId){
        $http.delete(restInterface + "/team"+ teamId +"/" + playerId).then(
            function(response){
                //?
            },
            function(err){
                alert("error sending http request");
            });
    }

    $scope.deleteTeam= function(teamId){
        $http.delete(restInterface + "/team"+ teamId).then(
            function(response){
                //?
            },
            function(err){
                alert("error sending http request");
            });
    }

    var init = function(){
        getTeams(10000);
        getPlayersOfTeam(10005);
    }

    init();

});