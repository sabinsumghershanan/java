'use strict';
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var mancala = angular.module('mancala', [
    'ngRoute'
]);

mancala.config(['$routeProvider', '$sceProvider',
    function ($routeProvider, $sceProvider) {

        $routeProvider.
                when('/home', {
                    templateUrl: 'html/home.html',
                    controller: 'HomeCtrl'
                }).
                otherwise({
                    redirectTo: '/home'
                });
        $sceProvider.enabled(false);

    }]);


