var myApp = angular.module('hello', [ 'ngRoute', 'angularRangeSlider']);
myApp.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'resources/view/home.html',
        controller : 'homeController'
    }).when('/login', {
        templateUrl : 'resources/view/login.html',
        controller : 'loginController'
    }).when('/resultSurvey', {
        templateUrl : 'resources/view/resultSurvey.html',
        controller : 'resultSurveyController'
    }).when('/createSurvey', {
        templateUrl : 'resources/view/createSurvey.html',
        controller : 'createSurveyController'
    }).when('/surveysList', {
        templateUrl : 'resources/view/surveysList.html',
        controller : 'surveysListController'
    }).when('/ownerSurveyList', {
        templateUrl : 'resources/view/ownerSurveyList.html',
        controller : 'ownerSurveysListController'
    }).when('/newAccount', {
        templateUrl : 'resources/view/newAccount.html',
        controller : 'newAccountController'
    }).when('/completeData', {
        templateUrl : 'resources/view/completeData.html',
        controller : 'completeDataController'
    }).when('/userPanel', {
        templateUrl : 'resources/view/userPanel.html',
        controller : 'userPanelController'
    });
});



myApp.controller('AppController',['$scope', function($s) {

    $s.items = [{
        name  : 'First Item',
        value : 5
    }];


}]);


myApp.controller('homeController', function($scope) {
    $scope.message = 'Sign up!';
});




myApp.controller('resultSurveyController', function($scope, $http, $window) {

    $http.get('getResult/').success(function (data) {
        //if(data.error==0){
        //    alert("Niewlasciwe dane");
        //} else {
            $scope.model = data;
        //}

    }).error(function (data) {
      //  location.reload();
      //  alert('Wprowadzone dane są niespójne. \n Określ prioryetery jeszcze raz!');
     //   $window.location.href = '#/completeData';
     //   console.log("Setting up account failed");
        // $rootScope.errorEditProfile = true;

    });






});



// pobiera nazwy publicznych ankiet
myApp.controller('surveysListController', function($scope, $http, $window) {

    $http.get('getPublicNamesFromBase/').success(function (data) {

        $scope.model = data;

    }).error(function (data) {

    });

    $scope.getPublicSurvey = function (a) {

        $http.post('setCompletedDataFromBase/', a).success(function() {

            console.log("post opublicznej");
           // $window.location.href = '#/completeData';
            //  $window.location.href = '#/setSurveysData';

        }).error(function () {
            console.log("Setting up account failed dhfdjkpublicznej");
            // $rootScope.errorEditProfile = true;


        });

        $window.location.href = '#/completeData';
    }


 

});


// pobiera nazwy ANKIET ZALOGOWANEGO USERA
myApp.controller('ownerSurveysListController', function($scope, $http, $window) {

    console.log('jestem w ownersyerveycontroler');
    $http.get('getOwnerNamesFromBase/').success(function (data) {

        $scope.model = data;

    }).error(function (data) {

    });
    
    $scope.getOwnerSurvey = function (a) {

        $http.post('setCompletedDataFromBase/', a).success(function() {

            console.log("post opublicznej");
            // $window.location.href = '#/completeData';
            //  $window.location.href = '#/setSurveysData';

        }).error(function () {
            console.log("Setting up account failed dhfdjkpublicznej");
            // $rootScope.errorEditProfile = true;


        });

        $window.location.href = '#/completeData';
    }



});


myApp.controller('newAccountController', function($scope,$window,$http) {

    console.log('jestem w newaccountcontroler');
    $scope.setNewAcc = function () {
        var newAcc = {
            username: $scope.credentials.username,
            password: $scope.credentials.password
        };

        console.log('zrobilem var');

        $http.post('addNewAccount', newAcc).success(function (data){
            console.log('jestem w sukcesie');

            $window.location.href = '#/login';
        }).error(function (data){
            console.log('jestem w errorze');

            $window.location.reload();
        });
    }

    $scope.message = 'New Account';
});

myApp.controller('loginController', function($rootScope, $scope, $http, $window) {

    console.log('jestem w login controller');
    $scope.loginUser = function () {
        var user = {
            username: $scope.credentials.username,
            password: $scope.credentials.password
        };

        console.log('zrobilem var');

        $http.post('loginUser', user).success(function (){
            console.log('jestem w sukcesie przy logowaniu');


            $http.get('getloggedUser').success(function (data) {
                $scope.loged = data;


            if($scope.loged.zalogowany === true) {

                $window.location.href = '#';
            } else {
                alert('Niewlasciwy login lub haslo');
            }
            });
        }).error(function (data){
            console.log('jestem w errorze przy logowaniu');
          //  alert('Niewlasciwy login lub haslo');
            //$window.location.reload();
        });
    }

});


myApp.controller('createSurveyController', function($scope, $window, $http) {
    $scope.message = 'Create survey';

    $scope.categories = [{id: 'category1'}, {id: 'category2'}];

    $scope.addNewCategory = function() {
        var newItemNo = $scope.categories.length+1;
        $scope.categories.push({'id':'category'+newItemNo});
    };

    $scope.removeCategory = function() {
        var lastItem = $scope.categories.length-1;
        $scope.categories.splice(lastItem);
    };


    $scope.variants = [{id: 'variant1'}, {id: 'variant2'}];

    $scope.addNewVariant = function() {
        var newItemNo = $scope.variants.length+1;
        $scope.variants.push({'id':'variant'+newItemNo});
    };

    $scope.removeVariant = function() {
        var lastItem = $scope.variants.length-1;
        $scope.variants.splice(lastItem);
    };


    $scope.getSurveydata = function () {

        var cs = {
            surveyName: $scope.s.surveyName,
            access: $scope.s.access,
          //  surveyName2: $scope.s.surveyName
            categories: $scope.categories,
            variants: $scope.variants
        };

        console.log("GetSurvaysData sdsd");

        $http.post('setSurveysData/', cs).success(function (data) {

            console.log(cs.surveyName);
            // $rootScope.errorEditProfile = false;
            // $rootScope.EditProfileOK = true;
            console.log("udalo sie w http.post");
          //   $window.location.href = '#/completeData';
            //  $window.location.href = '#/setSurveysData';
            $http.get('getSurveyData/').success(function (data) {
                console.log("get daty w MAINNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
                $scope.surveyData = data;
            });


        }).error(function (data) {
            console.log("Setting up account failed");
            // $rootScope.errorEditProfile = true;

        });

    };

    $scope.completeData = function () {
        var cs = {
            categoriesInput: $scope.variants
        };


        console.log("Jestesmy w main.js compete");

        $http.post('setCompletedData/', cs).success(function (data) {

          //  console.log(cs.surveyName);
            // $rootScope.errorEditProfile = false;
            // $rootScope.EditProfileOK = true;
           //$window.location.href = '#/completeData';
            console.log("udalo sie w complee.post");
            //   $window.location.href = '#/completeData';
            //  $window.location.href = '#/setSurveysData';

        }).error(function (data) {
            console.log("Setting up account failed");
            // $rootScope.errorEditProfile = true;


        });


    }



});

myApp.controller('userPanelController', function($scope) {
    $scope.message = 'Your Panel';
});

myApp.controller('completeDataController', function($scope, $http) {


    $scope.message = 'Your Panel';

    $http.get('getDataToScroll').success(function (data) {
       $scope.model = data;
    });

    $scope.go = function(targetId){
        var destination = $(targetId).offset().top;
        $('html, body').animate({scrollTop: destination}, 300);
    };
    
    $scope.items=[];

    $scope.setAllData = function () {

        var items = {
            items: $scope.model.listToScroll
        };

        console.log("przesylasz suwakiiiiiiiid");

        $http.post('setAllData/', items).success(function (data) {

            console.log(items);

            console.log("udalo sie w suwakach");

        }).error(function (data) {
            console.log("Setting up account failed");
        });
    };

});


















myApp.controller('loginController1', function($rootScope, $scope, $http, $location, $route) {

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

    };

    //proboje samo bez wysylania fomularza jesli juz mamy to ciastko a jedynie odswiezylismy
    authenticate();

    $scope.credentials = {};
    $scope.login = function () {
        authenticate($scope.credentials, function (authenticated) {
            if (authenticated) {
                console.log("Login succeeded");
                $location.path("/");
                $scope.error = false;
                $rootScope.authenticated = true;
            } else {
                console.log("Login failed");
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
        }).error(function (data) {"Logout failed";
            $rootScope.authenticated = false;
        });
    }

});