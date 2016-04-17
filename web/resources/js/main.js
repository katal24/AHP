var myApp = angular.module('hello', [ 'ngRoute' ]);
myApp.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'resources/view/home.html',
        controller : 'homeController'
    }).when('/login', {
        templateUrl : 'resources/view/login.html',
        controller : 'loginController'
    }).when('/createSurvey', {
        templateUrl : 'resources/view/createSurvey.html',
        controller : 'createSurveyController'
    }).when('/newAccount', {
        templateUrl : 'resources/view/newAccount.html',
        controller : 'newAccountController'
    }).when('/userPanel', {
        templateUrl : 'resources/view/userPanel.html',
        controller : 'userPanelController'
    });
});
myApp.controller('homeController', function($scope) {
    $scope.message = 'Sign up!';
});
myApp.controller('loginController', function($scope) {
    $scope.message = 'Login in';
});
myApp.controller('createSurveyController', function($scope) {
    $scope.message = 'Create survey';
});
myApp.controller('newAccountController', function($scope) {
    $scope.message = 'New Account';
});
myApp.controller('userPanelController', function($scope) {
    $scope.message = 'Your Panel';
});