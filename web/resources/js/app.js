var app = angular.module('app', ['ngRoute','ngResource']);
app.config(['$routeProvider', function($routeProvider){
    $routeProvider
        .when('/', {
            redirectTo: '/login'
        })
        .when ('/login', {
            templateUrl: 'view/loginPage.html',
            controller: 'LoginController',
            controllerAs: 'loginCtrl'
        })
        .when('/driver/:login', {
            templateUrl: 'view/driverPage.html',
            controller: 'DriverController',
            controllerAs: 'driver'
        })
        .when('/organizator/:login', {
            templateUrl: 'view/organizatorPage.html',
            controller: 'OrganizatorController',
            controllerAs: 'organizator'
        })
        .when('/driver/:login/addVehicle', {
            templateUrl: 'view/addVehicle.html',
            controller: 'AddVehicleController',
            controllerAs: 'addVehicle'
        })
        .when('/organizator/:login/addExcursion', {
            templateUrl: 'view/addExcursion.html',
            controller: 'AddExcursionController',
            controllerAs: 'addExcursion'
        })
        .when('/user/:login', {
            templateUrl: 'view/userPage.html',
            controller: 'UserController',
            controllerAs: 'user'
        })
        .otherwise(
            { redirectTo: '/'}
        );
}]);