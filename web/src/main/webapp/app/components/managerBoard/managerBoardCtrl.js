"use strict";
angular.module("sportsClub").controller('managerBoardCtrl', function($scope,$http,$uibModal,$stateParams) {
    $scope.managers;
    $scope.playerInfos;
    $scope.teams;

    $scope.displayingFree=false;
    //$scope.loggedManagerId = $stateParams;
    //temporary
  $scope.loggedManagerId =10000;

    var getTeams= function(managerId){
        $http.get(restInterface + "/manager/"+managerId+"/teams").then(
            function(response){
                $scope.teams = response.data;
            },
            function(err){
                alert("error sending http request");
            });
    }
    $scope.getFreePlayersOfClub= function(){
        $http.get(restInterface + '/manager/' + $scope.loggedManagerId +'/freePlayers').then(
            function(response){
            	 $scope.displayingFree=true;
                $scope.playerInfos = response.data;
            },
            function(err){
                alert("error sending http request");
            });
    }

    $scope.getPlayersOfTeam= function(teamId){
        $http.get(restInterface + "/team/"+ teamId +'/players').then(
        function(response){
        	 $scope.displayingFree=false;
                $scope.playerInfos = response.data;
        },
            function(err){
                alert("error sending http request");
            });
    }

    $scope.deletePlayer= function(playerId){
        $http.delete(restInterface + "/player/"+ playerId ).then(
            function(){
            	alert("player deleted");
                for(var i=0;i<$scope.playerInfos.length;i++){
                	if($scope.playerInfos[i].id == playerId){
                		$scope.playerInfos.splice(i,1);
                		break;
                	}
                }
            },
            function(){
                alert("error sending http request");
            });
    }

    $scope.removePlayerFromRoster= function(playerInfoId){
        $http.delete(restInterface + "/playerInfo/"+playerInfoId).then(
            function(response){
                alert("player removed from team");
                for(var i=0;i<$scope.playerInfos.length;i++){
                	if($scope.playerInfos[i].playerInfoId == playerInfoId){
                		$scope.playerInfos.splice(i,1);
                		break;
                	}
                }         
            },
            function(err){
                alert("error sending http request");
            });
    }

    $scope.deleteTeam= function(teamId){
        $http.delete(restInterface + "/team/"+ teamId).then(
            function(response){
                alert("team deleted");
                for(var i=0;i<$scope.teams.length;i++){
                	if($scope.teams[i].id == teamId){
                		$scope.teams.splice(i,1);
                		break;
                	}
                	$scope.playerInfos=[];
                } 
            },
            function(err){
                alert("error sending http request");
            });
    }
    
    $scope.addNewPlayer = function(){
    	alert("add");
    }
    
    $scope.editPlayer = function(){
    	alert("edit");
    }

    var getNotAlreadyCreatedTeams = function(managerId){
        $http.get(restInterface + "/manager/"+managerId+"/freeTeams").then(
            function(response){
                $scope.notAlreadyCreatedTeams = response.data;
            },
            function(err){
                alert("error sending http request");
            });
    }

    $scope.openNewTeamModal = function(){
        var notAlreadyCreatedTeams = getNotAlreadyCreatedTeams(loggedManagerId)
        var modalInstance = $uibModal.open({
            templateUrl : 'app/components/newTeamModal/newTeamModal.html',
            controller : 'newTeamModalCtrl',
            resolve : {
                // data : function() {
                //     return notAlreadyCreatedTeams;
                // }
            }
        });
        modalInstance.result.then(function(updatedData) {
            $scope.teams.push(updatedData.data);
        }, function () {
        });
    }
    
    

    var init = function(){
        getTeams($scope.loggedManagerId);
    }

    init();



});