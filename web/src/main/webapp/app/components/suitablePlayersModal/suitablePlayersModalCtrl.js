"use strict";
angular.module("sportsClub").controller('suitablePlayersModalCtrl',function($scope, $uibModal, $uibModalInstance, $http, suitablePlayers) {
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

    $scope.playerModal = function(playerData){
        var modalInstance = $uibModal.open({
            templateUrl: 'app/components/playerModal/playerModal.html',
            controller: 'playerModalCtrl',
            resolve: {
                data: function () {
                    return playerData;
                }
            }
        });
        modalInstance.result.then(function (updatedData) {
            if (updatedData.new == true) {
                $scope.playerInfos.player.push(updatedData.data);
            } else {
                for (var i = 0; i < $scope.playerInfos.player.length; i++) {
                    if ($scope.playerInfos.player[i].id == updatedData.data.id) {
                        $scope.playerInfos.player[i] = updatedData.data;
                    }
                }
            }
        }, function () {
        });
    }
});