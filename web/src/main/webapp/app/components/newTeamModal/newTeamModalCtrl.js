"use strict";
angular.module("sportsClub").controller('newTeamModalCtrl',function($scope, $uibModalInstance, $http, notCreatedTeams) {
    $scope.notCreatedTeams = angular.copy(notCreatedTeams);

    $scope.close = function(updatedData){
        $uibModalInstance.close(updatedData);
    }

    $scope.createNewTeam = function(team){
        $http.post(restInterface + '/team/create',team).then(
            function(response){
                alert("Team created");
                $scope.close({"new":true,"newTeam":team});
            },
            function(response){
                //alert("error occured while creating team");
                alert(response.status);
                //console.log(response.status);
            }
        );
    }
});