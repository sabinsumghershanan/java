'use strict';
var mancala = angular.module('mancala');

//This controller will send the players response to the back end
//and receiving back the updated board of the game
mancala.controller('HomeCtrl', function ($scope, $http, $window) {
    //When the html is loaded ask the game to be initiated
    $http.get('/mancala/resources/service/mancala')
            .success(function (data) {
                $scope.board = data;
                $scope.msg = "First Player's Turn!!";
                $scope.pointsSecond = $scope.board[0];
                $scope.pointsFirst = $scope.board[7];
            });
    //Function that is called when a pit is clicked
    $scope.move = function (pit) {
        if ($scope.board[14] === 3)
        {
            return;
        }
        else
        {
            $http.post('/mancala/resources/service/play', pit)
                    .success(function (data) {
                        $scope.board = data;
                        if ($scope.board[14] === 1) {
                            $scope.msg = "First Player's Turn!!";
                        }
                        else if ($scope.board[14] === 2) {
                            $scope.msg = "Second Player's Turn!!!!";
                        }
                        else
                        {
                            $scope.board[0] = $scope.board[0] + $scope.board[8] + $scope.board[9] + $scope.board[10] + $scope.board[11] + $scope.board[12] + $scope.board[13];
                            $scope.board[7] = $scope.board[7] + $scope.board[1] + $scope.board[2] + $scope.board[3] + $scope.board[4] + $scope.board[5] + $scope.board[6];
                            $scope.board[8] = $scope.board[9] = $scope.board[10] = $scope.board[11] = $scope.board[12] = $scope.board[13] = 0;
                            $scope.board[1] = $scope.board[2] = $scope.board[3] = $scope.board[4] = $scope.board[5] = $scope.board[6] = 0;
                            $scope.pointsSecond = $scope.board[0];
                            $scope.pointsFirst = $scope.board[7];
							$scope.msg = "YOUR GAME IS OVER!!";
							if($scope.pointsFirst + $scope.pointsSecond == 72){
							if($scope.pointsFirst > $scope.pointsSecond) {
                            $scope.msg = "Player One Won the Game";
                            }else{
                            $scope.msg = "Player Two Won the Game";
                            }
						}
                        }

                        $scope.pointsSecond = $scope.board[0];
                        $scope.pointsFirst = $scope.board[7];
                    });
        }
      };
});