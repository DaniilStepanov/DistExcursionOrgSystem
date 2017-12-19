/**
 * Created by kivi on 13.12.17.
 */

function UserService($resource) {
    return $resource('rest/:login', { login: '@login' });
}

function UserController($routeParams, UserService, MessageService) {
    var url = function () {
        return {login:$routeParams.login}
    }

    this.instance = new UserService(url());
    this.messages = MessageService.query(url());
}

angular
    .module('app')
    .factory('UserService', UserService)
    .controller('UserController', UserController);