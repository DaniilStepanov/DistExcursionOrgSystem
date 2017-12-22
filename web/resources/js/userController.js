function UserService($resource) {
    return $resource('rest/user/:login', { login: '@login' });
}

function AllUserExcursions($resource) {
    return $resource('rest/user/:login/excursions', { login: '@login' }, {
        query: {method: 'get', isArray: true, cancellable: true}
    });
}

function AllExcursions($resource) {
    return $resource('rest/user/allExcursions', {
        query: {method: 'get', isArray: true, cancellable: true}
    });
}

function ExcursionService($resource) {
    return $resource('rest/excursion/:id', {id: '@id' });
}


function UserController($routeParams, $http, $scope,
                        UserService, AllUserExcursions,
                        AllExcursions, ExcursionService) {

    var url = function () {
        return {login:$routeParams.login}
    };



    this.user = UserService.get(url());
    this.userExcursions = AllUserExcursions.query(url());
    this.allExcursions = AllExcursions.query(url());

    console.log($routeParams.login);
    this.selectExcursion = function (id) {
        $scope.desc = "Excursion Number 0";
    };

    this.addToExc = function () {
        $http.post('rest/user/addToExcursion?login='
            + this.user.login + '&id=' + $scope.role )
            .then(function (response) {
                    console.log(response.data.toString());
                }, function (error) {
                    alert(error.data.message);
                }
            );
    };

    this.delFromExc = function () {
        $http.post('rest/user/delFromExcursion?login='
            + this.user.login + '&id=' + $scope.role );
    };

    this.radioChanged = function () {
        $scope.desc = $scope.role;
    }

}

angular
    .module('app')
    .factory('UserService', UserService)
    .factory('AllUserExcursions', AllUserExcursions)
    .factory('AllExcursions', AllExcursions)
    .factory('ExcursionService', ExcursionService)
    .controller('UserController', UserController);