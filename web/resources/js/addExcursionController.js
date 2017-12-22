function UserService($resource) {
    return $resource('rest/user/:login', { login: '@login' });
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function AddExcursionController($scope, $routeParams, $http,
                                UserService) {
    var url = function () {
        return {login:$routeParams.login};
    };

    this.user = UserService.get(url());

    this.ok = function () {
        $http.post('rest/organizator/' + this.user.login + '/addNewExcursion?name='
            + $scope.name + '&minTourists=' + $scope.minT
            + '&maxTourists=' + $scope.maxT + '&equipment=' + $scope.equip)
            .then(function () {}, function (error) { alert(error.data.message);});
        window.location.href = '#/organizator/' + this.user.login;
    }
}

angular
    .module('app')
    .factory('UserService', UserService)
    .controller('AddExcursionController', AddExcursionController);