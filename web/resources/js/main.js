var myApp = angular.module('hello', [ 'ngRoute' ]);
myApp.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'view/home.jsp',
        controller : 'myController'
    }).when('/login', {
        templateUrl : 'resources/view/login.html',
        controller : 'aboutController'
    }).when('/contact', {
        templateUrl : 'view/contact.jsp',
        controller : 'contactController'
    });
});
myApp.controller('myController', function($scope) {
    $scope.message = 'Everyone come and see how good I look!';
});
myApp.controller('aboutController', function($scope) {
    $scope.message = 'Look! I am an about page.';
});
myApp.controller('contactController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});