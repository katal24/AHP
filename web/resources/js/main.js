var myApp = angular.module('hello', [ 'ngRoute', 'angularRangeSlider', 'ng-fusioncharts']);
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
    });
});



myApp.controller('AppController',['$scope', function($s) {

    $s.items = [{
        name  : 'First Item',
        value : 5
    }];


}]);


myApp.controller('homeController', function($scope, $http, $window) {

    $scope.goToPublicSurveys  = function() {
        $window.location.href = '#/surveysList';
    }

    $scope.goToHome  = function() {
        $window.location.href = '#/';
    }

    $scope.goToCreateSurvey = function() {
        $window.location.href = '#/createSurvey';
    }


    $scope.goToOwnerSurveys  = function() {
        $window.location.href = '#/ownerSurveyList';
    }


    if ($scope.authenticated) {
        $http.get('/getResources/').success(function (data) {

            $scope.greeting = data;


        })} ;



});




myApp.controller('resultSurveyController', function($scope, $http, $window) {


    $scope.goToPublicSurveys  = function() {
        $window.location.href = '#/surveysList';
    }

    $scope.goToHome  = function() {
        $window.location.href = '#/';
    }

    $scope.goToCreateSurvey = function() {
        $window.location.href = '#/createSurvey';
    }


    $scope.goToOwnerSurveys  = function() {
        $window.location.href = '#/ownerSurveyList';
    }


    $scope.myDataSource = {
        chart: {
            caption: "",
            numberSuffix: "%",
            theme: "ocean"
        },
        data:
            []
    };


    $http.get('getResult2/').success(function (data) {

            $scope.model = data;

        $scope.myDataSource.data = $scope.model.resultList;


    }).error(function (data) {

    });

});



// pobiera nazwy publicznych ankiet
myApp.controller('surveysListController', function($scope, $http, $window, $rootScope) {

    $http.get('getPublicNamesFromBase/').success(function (data) {

        $scope.model = data;

    }).error(function (data) {

    });

    $scope.getPublicSurvey = function (a, b) {

        $rootScope.validationOn = b;

        $http.post('setCompletedDataFromBase/', a).success(function() {

            $window.location.href = '#/completeData';

        }).error(function () {

        });
    }

    $scope.goToPublicSurveys  = function() {
        $window.location.href = '#/surveysList';
    }

    $scope.goToHome  = function() {
        $window.location.href = '#/';
    }

    $scope.goToCreateSurvey = function() {
        $window.location.href = '#/createSurvey';
    }


    $scope.goToOwnerSurveys  = function() {
        $window.location.href = '#/ownerSurveyList';
    }

});


// pobiera nazwy ANKIET ZALOGOWANEGO USERA
myApp.controller('ownerSurveysListController', function($scope, $http, $window, $rootScope) {


    $scope.goToPublicSurveys  = function() {
        $window.location.href = '#/surveysList';
    }

    $scope.goToHome  = function() {
        $window.location.href = '#/';
    }

    $scope.goToCreateSurvey = function() {
        $window.location.href = '#/createSurvey';
    }


    $scope.goToOwnerSurveys  = function() {
        $window.location.href = '#/ownerSurveyList';
    }

    $http.get('getOwnerNamesFromBase/').success(function (data) {

        $scope.model = data;

    }).error(function (data) {

    });

    $scope.getOwnerSurvey = function (a, b) {

        $rootScope.validationOn = b;

        $http.post('setCompletedDataFromBase/', a).success(function() {

            $window.location.href = '#/completeData';

        }).error(function () {

        });

    }

});


myApp.controller('newAccountController', function($scope,$window,$http) {

    $scope.setNewAcc = function () {
        var newAcc = {
            username: $scope.credentials.username,
            password: $scope.credentials.password
        };


        $http.post('addNewAccount', newAcc).success(function (data){

            $window.location.href = '#/login';

        }).error(function (data){

            $window.location.reload();
        });

    }
    $scope.message = 'New Account';
});

myApp.controller('loginController', function($rootScope, $scope, $http, $window) {

    $scope.loginUser = function () {
        var user = {
            username: $scope.credentials.username,
            password: $scope.credentials.password
        };
        $rootScope.userek =  $scope.credentials.username;



        $http.post('loginUser', user).success(function (){

            $http.get('getloggedUser').success(function (data) {
                $scope.loged = data;
                $rootScope.logged = data.zalogowany;

                if($scope.loged.zalogowany === true) {

                    $window.location.href = '#/createSurvey';
                } else {
                    alert('Unknown user name or wrong password');
                }
            });
        }).error(function (data){

        });

    }

    $scope.logoutFoo  = function() {
        $rootScope.logged = false;
        $window.location.href = '#/';
    }


});


myApp.controller('createSurveyController', function($scope, $window, $http, $rootScope) {

    $scope.goToPublicSurveys  = function() {
        $window.location.href = '#/surveysList';
    }

    $scope.goToHome  = function() {
        $window.location.href = '#/';
    }

    $scope.goToCreateSurvey = function() {
        $window.location.href = '#/createSurvey';
    }


    $scope.goToOwnerSurveys  = function() {
        $window.location.href = '#/ownerSurveyList';
    }

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
            check1: $scope.s.check1,
            categories: $scope.categories,
            variants: $scope.variants
        };


        $http.post('setSurveysData/', cs).success(function (data) {

            $http.get('getSurveyData/').success(function (data) {
                $scope.surveyData = data;
            });


        }).error(function (data) {


        });

    };

    $scope.completeData = function (b) {
        var cs = {
            categoriesInput: $scope.variants
        };

        $rootScope.validationOn = b;

        $http.post('setCompletedData/', cs).success(function (data) {

            $window.location.href = '#/completeData';


        }).error(function (data) {

        });

    }

});


myApp.controller('completeDataController', function($scope, $http, $window, $rootScope) {

    $scope.goToPublicSurveys  = function() {
        $window.location.href = '#/surveysList';
    }

    $scope.goToHome  = function() {
        $window.location.href = '#/';
    }

    $scope.goToCreateSurvey = function() {
        $window.location.href = '#/createSurvey';
    }


    $scope.goToOwnerSurveys  = function() {
        $window.location.href = '#/ownerSurveyList';
    }


    $http.get('getDataToScroll').success(function (data) {

        $scope.model = data;
        $scope.index2 = 0;
        $scope.complaints = $scope.model.listToScroll[0];
        var items = {
            items: $scope.model.listToScroll
        };

        $scope.getNext2 = function (){

                $http.post('setAllData/', items).success(function (data) {

                    $http.get('getResult/').success(function (data) {

                        $scope.datka = data.error;


                        if(data.error==0 && $rootScope.validationOn=="yes"){

                            alert("Sorry! You filled it incorrect! Please, try again :)");

                        } else {
                            $window.location.href = '#/resultSurvey';
                        }

                    }).error(function (data) {

                    });

                }).error(function (data) {

                });

        }

        $scope.getNext = function (){

            if(($scope.model.listToScroll[$scope.index2].name != $scope.model.listToScroll[$scope.index2+1].name) && ($rootScope.validationOn == "yes") ){

                $http.post('setAllData/', items).success(function (data) {

                    $http.get('getResult/').success(function (data) {

                        if(data.error==0){

                            alert("Sorry! You filled it incorrect! Please, try again :)");
                        } else {
                            $scope.index2 = $scope.index2 + 1;
                            $scope.complaints = $scope.model.listToScroll[$scope.index2];
                        }

                    }).error(function (data) {

                    });

                }).error(function (data) {
                    console.log("Setting up account failed");
                });

               }
            else{
                $scope.index2 = $scope.index2 + 1;
               $scope.complaints = $scope.model.listToScroll[$scope.index2];
            }

        }

        $scope.getPrevious = function (){
            if($scope.model.listToScroll[$scope.index2].name == $scope.model.listToScroll[$scope.index2-1].name) {
                $scope.index2 = $scope.index2 - 1;
                $scope.complaints = $scope.model.listToScroll[$scope.index2];
            }

        }
    });
    
    $scope.items=[];

    $scope.setAllData = function () {

        var items = {
            items: $scope.model.listToScroll
        };


        $http.post('setAllData/', items).success(function (data) {


        }).error(function (data) {

        });
    };

});














