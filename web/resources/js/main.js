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


myApp.controller('homeController', function($scope, $http) {
    $scope.message = 'Sign up!';

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
            //subCaption: "Top 5 stores in last month by revenue",
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
        //  location.reload();
        //  alert('Wprowadzone dane są niespójne. \n Określ prioryetery jeszcze raz!');
        //   $window.location.href = '#/completeData';
        //   console.log("Setting up account failed");
        // $rootScope.errorEditProfile = true;

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



            console.log("post opublicznej");

            $window.location.href = '#/completeData';
            // $window.location.href = '#/completeData';
            //  $window.location.href = '#/setSurveysData';

        }).error(function () {
            console.log("Setting up account failed dhfdjkpublicznej");
            // $rootScope.errorEditProfile = true;


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


    console.log('jestem w ownersyerveycontroler');
    $http.get('getOwnerNamesFromBase/').success(function (data) {

        $scope.model = data;

    }).error(function (data) {

    });

    $scope.getOwnerSurvey = function (a, b) {

        $rootScope.validationOn = b;

        $http.post('setCompletedDataFromBase/', a).success(function() {

            console.log("post opublicznej");
            $window.location.href = '#/completeData';
            //  $window.location.href = '#/setSurveysData';

        }).error(function () {
            console.log("Setting up account failed dhfdjkpublicznej");
            // $rootScope.errorEditProfile = true;


        });

    }



});


myApp.controller('newAccountController', function($scope,$window,$http) {

    console.log('jestem w newaccountcontroler');
    $scope.setNewAcc = function () {
        var newAcc = {
            username: $scope.credentials.username,
            password: $scope.credentials.password
        };

        console.log('register');

        $http.post('addNewAccount', newAcc).success(function (data){
            console.log('jestem w sukcesie');

            $window.location.href = '#/login';
        }).error(function (data){
            console.log('jestem w errorze');

            $window.location.reload();
        });

        //$window.location.href = '#/';
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
        $rootScope.userek =  $scope.credentials.username;

        console.log('zrobilem var');

        $http.post('loginUser', user).success(function (){
            console.log('jestem w sukcesie przy logowaniu');


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
            console.log('jestem w errorze przy logowaniu');
            //  alert('Niewlasciwy login lub haslo');
            //$window.location.reload();
        });
        //$window.location.href = '#/createSurvey';
    }

    $scope.logoutFoo  = function() {
        $rootScope.logged = false;
        $window.location.href = '#/';
    }


});


myApp.controller('createSurveyController', function($scope, $window, $http, $rootScope) {
    $scope.message = 'Create survey';

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

    $scope.completeData = function (b) {
        var cs = {
            categoriesInput: $scope.variants
        };

        $rootScope.validationOn = b;

        console.log("Jestesmy w main.js compete");

        $http.post('setCompletedData/', cs).success(function (data) {

            console.log("udalo sie w complee.post");
            $window.location.href = '#/completeData';


        }).error(function (data) {
            console.log("Setting up account failed");
            // $rootScope.errorEditProfile = true;


        });



    }



});

myApp.controller('userPanelController', function($scope) {
    $scope.message = 'Your Panel';
});

myApp.controller('completeDataController', function($scope, $http, $window, $rootScope) {

    $scope.message = 'Your Panel';

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
                console.log("przesylasz suwakiiiiiiiid");

                $http.post('setAllData/', items).success(function (data) {

                    console.log("udalo sie w suwakach");
                    $http.get('getResult/').success(function (data) {

                        $scope.datka = data.error;


                        if(data.error==0 && $rootScope.validationOn=="yes"){

                            alert("Sorry! You filled it incorrect! Please, try again :)");

                        } else {
                            $window.location.href = '#/resultSurvey';
                        }

                    }).error(function (data) {
                        //  location.reload();
                        //  alert('Wprowadzone dane są niespójne. \n Określ prioryetery jeszcze raz!');
                        //   $window.location.href = '#/completeData';
                        //   console.log("Setting up account failed");
                        // $rootScope.errorEditProfile = true;

                    });

                }).error(function (data) {
                    console.log("Setting up account failed");
                });



        }
        //koniec

        $scope.getNext = function (){

            if(($scope.model.listToScroll[$scope.index2].name != $scope.model.listToScroll[$scope.index2+1].name) && ($rootScope.validationOn == "yes") ){

                //var items = {
                //    items: $scope.model.listToScroll
                //};

                console.log("przesylasz suwakiiiiiiiid");

                $http.post('setAllData/', items).success(function (data) {

                    console.log(items);

                    console.log("udalo sie w suwakach");
                    $http.get('getResult/').success(function (data) {

                        //$scope.modele = data;


                        if(data.error==0){

                            alert("Sorry! You filled it incorrect! Please, try again :)");
                        } else {
                            $scope.index2 = $scope.index2 + 1;
                            $scope.complaints = $scope.model.listToScroll[$scope.index2];
                        }

                    }).error(function (data) {
                        //  location.reload();
                        //  alert('Wprowadzone dane są niespójne. \n Określ prioryetery jeszcze raz!');
                        //   $window.location.href = '#/completeData';
                        //   console.log("Setting up account failed");
                        // $rootScope.errorEditProfile = true;

                    });

                }).error(function (data) {
                    console.log("Setting up account failed");
                });

               }
            else{
                $scope.index2 = $scope.index2 + 1;
               $scope.complaints = $scope.model.listToScroll[$scope.index2];
            }
            //if(funckja spradzajaca niepewnosc > 0.2){
            //    do nic; alert
            //} else


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