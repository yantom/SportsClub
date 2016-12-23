"use strict";
angular.module("sportsClub").controller('newTeamModalCtrl',function($scope, $uibModalInstance, $http, notAlreadyCreatedTeams) {
    $scope.notAlreadyCreatedTeams = angular.copy(notAlreadyCreatedTeams)

    $scope.close = function(){
        $uibModalInstance.close();
    }

    $scope.createNewTeam = function(teamData){
        $http.post(restInterface + '/team',teamData).then(
            function(response){
                alert("Team created");
                $scope.close({"new":true,"data":response.data});
            },
            function(){
                alert("error occured while creating team");
            }
        );
    }


});