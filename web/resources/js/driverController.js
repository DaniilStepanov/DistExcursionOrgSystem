function UserService($resource) {
    return $resource('rest/user/:login', { login: '@login' });
}

function DriverService($resource) {
    return $resource('rest/driver/:login', { login: '@login'});
}

function AgreeService($resource) {
    return $resource('rest/driver/:login/agree', { login: '@login'});
}

function DisagreeService($resource) {
    return $resource('rest/driver/:login/disagree', { login: '@login'});
}

function AllExcursions($resource) {
    return $resource('rest/user/allExcursions', {
        query: {method: 'get', isArray: true, cancellable: true}
    });
}

function DriverController($scope, $routeParams,
                          UserService,
                          DriverService,
                          AgreeService,
                          DisagreeService,
                          AllExcursions) {

    var url = function () {
        return {login:$routeParams.login};
    };

    this.user = UserService.get(url());
    this.dr = DriverService.get(url());
    this.allExcursions = AllExcursions.query(url());

    $scope.description = "LOH";

    this.addVehicle = function () {
        console.log('#/driver/' + this.user.login + '/addVehicle')
        window.location.href = '#/driver/' + this.user.login + '/addVehicle';
    };

    this.agree = function () {
        AgreeService.query(url());
    };

    this.disagree = function () {
        DisagreeService.query(url());
    };

    this.radioChanged = function () {
        $scope.desc = $scope.role;
    };

}

angular
    .module('app')
    .factory('UserService', UserService)
    .factory('DriverService', DriverService)
    .factory('AgreeService', AgreeService)
    .factory('DisagreeService', DisagreeService)
    .factory('AllExcursions', AllExcursions)
    .controller('DriverController', DriverController);