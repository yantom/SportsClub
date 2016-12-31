"use strict";
angular.module("sportsClub").controller('suitablePlayersModalCtrl',function($scope, $uibModalInstance, $http, suitablePlayers) {
    $scope.suitablePlayers = angular.copy(suitablePlayers);
    $scope.jerseyNumber;

    $scope.close = function(updatedData){
        $uibModalInstance.close(updatedData);
    }

    $scope.addPlayerToRoster = function(teamId,playerId,jerseyNumber){
        $http.post(restInterface + '/' + teamId + '/' + playerId + '/' + $scope.jerseyNumber).then(
            function(response){
                alert("Player added in the roster");
                $scope.close({"new":true,"newPlayer":player});
            },
            function(response){
                alert(response.status);
            }
        );
    }

    $scope.createNewPlayer = function(player){

    }
});