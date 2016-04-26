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
myApp.controller('loginController', function($rootScope, $scope, $http, $location, $route) {

    $scope.tab = function (route) {
        return $route.current && route === $route.current.controller;
    };

    var authenticate = function (credentials, callback) {

        var headers = credentials ? {
            authorization: "Basic "
            + btoa(credentials.username + ":"
                + credentials.password)
        } : {};

        $http.get('/user', {headers: headers}).success(function (data) {
            //mapowanie user zwraca Principal a on ma getName()
            if (data.name) {
                $rootScope.authenticated = true;
                $rootScope.loggedUser = credentials.username;

            } else {
                $rootScope.authenticated = false;
            }
            callback && callback($rootScope.authenticated);
        }).error(function () {
            $rootScope.authenticated = false;
            callback && callback(false);
        });

    }

    //proboje samo bez wysylania fomularza jesli juz mamy to ciastko a jedynie odswiezylismy
    authenticate();

    $scope.credentials = {};
    $scope.login = function () {
        authenticate($scope.credentials, function (authenticated) {
            if (authenticated) {
                console.log("Login succeeded")
                $location.path("/");
                $scope.error = false;
                $rootScope.authenticated = true;
            } else {
                console.log("Login failed")
                $location.path("/login");
                $scope.error = true;
                $rootScope.authenticated = false;
            }
        })
    };

    $scope.logout = function () {
        $http.post('logout', {}).success(function () {
            $rootScope.authenticated = false;
            $location.path("/");
        }).error(function (data) {"Logout failed"
            $rootScope.authenticated = false;
        });
    }

});
myApp.controller('createSurveyController', function($scope) {
    $scope.message = 'Create survey';
    $(function()
    {
        $(document).on('click', '.btn-add', function(e)
        {
            e.preventDefault();

            var controlForm = $('.controls form:first'),
                currentEntry = $(this).parents('.entry:first'),
                newEntry = $(currentEntry.clone()).appendTo(controlForm);

            newEntry.find('input').val('');
            controlForm.find('.entry:not(:last) .btn-add')
                .removeClass('btn-add').addClass('btn-remove')
                .removeClass('btn-success').addClass('btn-danger')
                .html('<span class="glyphicon glyphicon-minus"></span>');
        }).on('click', '.btn-remove', function(e)
        {
            $(this).parents('.entry:first').remove();

            e.preventDefault();
            return false;
        });
    });


});
myApp.controller('newAccountController', function($scope) {
    $scope.message = 'New Account';
});
myApp.controller('userPanelController', function($scope) {
    $scope.message = 'Your Panel';
});

