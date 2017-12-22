function DriverService($resource) {
    return $resource('rest/driver/:login', { login: '@login'});
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function AddVehicleController($scope, $routeParams, $http,
                              DriverService) {
    var url = function () {
        return {login:$routeParams.login};
    };

    this.dr = DriverService.get(url());

    this.ok = function () {
        if (isEmpty($scope.capacity)) {
            alert("Enter the capacity");
        } else if (isEmpty($scope.milage)) {
            alert("Enter the milage");
        } else if (isEmpty($scope.model)) {
            alert("Enter the model");
        } else if (isEmpty($scope.numbers)) {
            alert("Enter the numbers");
        } else {
            $http.post('rest/driver/' + this.dr.user.login + '/addVehicle/add?model='
                + $scope.model + '&milage=' + $scope.milage
            + '&capacity=' + $scope.capacity + '&numbers=' + $scope.numbers);
            window.location.href = '#/driver/' + this.dr.user.login;
        }
    }
}

angular
    .module('app')
    .factory('DriverService', DriverService)
    .controller('AddVehicleController', AddVehicleController);