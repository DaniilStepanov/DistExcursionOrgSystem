function UserService($resource) {
    return $resource('rest/user/:login', { login: '@login' });
}

function OrgExcursionService($resource) {
    return $resource('rest/organizator/:login/excursion', { login: '@login' });
}

function AllFreeDriversService($resource) {
    return $resource('rest/organizator/:login/allDrivers', { login: '@login' }, {
        query: {method: 'get', isArray: true, cancellable: true}
    });
}

function StartExcursionService($resource) {
    return $resource('rest/organizator/:login/startExcursion', { login: '@login' });
}

function EndExcursionService($resource) {
    return $resource('rest/organizator/:login/endExcursion', { login: '@login' });
}

function OrganizatorController($scope, $routeParams, $http,
                          UserService, OrgExcursionService,
                           AllFreeDriversService,
                           StartExcursionService,
                           EndExcursionService) {

    var url = function () {
        return {login:$routeParams.login};
    };

    this.user = UserService.get(url());
    this.excursion = OrgExcursionService.get(url());
    this.drivers = AllFreeDriversService.query(url());

    this.sendOffer = function () {
        $http.post('rest/organizator/sendOffer?orgLogin='
            + this.user.login + '&driverId=' + $scope.role + '&money=' + $scope.money)
            .then(function () {}, function (error) { alert(error.data.message);});
    };
    
    this.startExcursion = function () {
        StartExcursionService.query(url(), function () {}, function (error) {alert(error.data.message)});
    };

    this.endExcursion = function () {
        EndExcursionService.query(url(), function () {}, function (error) {alert(error.data.message)});
    };


    this.addExcursionObject = function () {
        $http.post('rest/organizator/' + this.user.login + '/addExcursionObject?description=' +
        $scope.description);
    };

    this.newExcursion = function () {
        window.location.href = '#/organizator/' + this.user.login + '/addExcursion';
    };
}

angular
    .module('app')
    .factory('UserService', UserService)
    .factory('OrgExcursionService', OrgExcursionService)
    .factory('AllFreeDriversService', AllFreeDriversService)
    .factory('StartExcursionService', StartExcursionService)
    .factory('EndExcursionService', EndExcursionService)
    .controller('OrganizatorController', OrganizatorController);