
angular.module('hello', ['ngRoute']).config(function ($routeProvider, $httpProvider) {

    //wstrzknelimsy ngroute do modulu

    //tu sie ustawia kontoler i szablon html w zaleznosci od mapowania
    $routeProvider.when('/', {
        //index to slash
        templateUrl: 'index.jsp',
        controller: 'home'
    });


    //<wylacza okno dialogowe popup przegladarki pytajace o haslo
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('home', function ($scope, $http, $rootScope) {

    if ($scope.authenticated) {
        $http.get('/getResources/').success(function (data) {

            $scope.greeting = data;


        });

    }
});
