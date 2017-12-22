function InfoShareService() {
    var user = {};
    return {
        setUser: function (value) {
            user = value;
        },
        getUser: function () {
            return user;
        }
    };
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function LoginController($scope, $http) {
    this.isRegister = false;
    this.signIn = function () {
        if (!$scope.login) {
            alert("Enter login");
        } else if (!$scope.passwd) {
            alert("Enter password");
        } else {
            $http.get('rest/user/' + $scope.login + '/authenticate?passwd=' + $scope.passwd)
                .then(function (response) {
                    if (response.data.toString() == "0"){
                        alert("Incorrect login or password");
                        $scope.login = "";
                        $scope.passwd = "";
                    }
                    else if (response.data.toString() == "1")
                        window.location.href = '#/driver/' + $scope.login;
                    else if (response.data.toString() == "2")
                        window.location.href = '#/organizator/' + $scope.login;
                    else if (response.data.toString() == "3")
                        window.location.href = '#/user/' + $scope.login;
                }, function (error) {
                    alert("ERROR :(");
                })
        }
    };
    this.registerUser = function () {
        if (isEmpty($scope.login)) {
            alert("Enter the login");
        } else if (isEmpty($scope.passwd)) {
            alert("Enter the passwd");
        } else {
           if ($scope.role == "user") {
               $http.post('rest/user/addUser?login='
                   + $scope.login + '&name=' + $scope.name + '&passwd=' + $scope.passwd);
               window.location.href = '#/user/' + $scope.login;
           }
           else if ($scope.role == "org") {
               $http.post('rest/organizator/addOrganizator?login='
                   + $scope.login + '&name=' + $scope.name + '&passwd=' + $scope.passwd);
               window.location.href = '#/organizator/' + $scope.login;
           }
           else if ($scope.role == "driver") {
               $http.post('rest/driver/addDriver?login='
                   + $scope.login + '&name=' + $scope.name + '&passwd=' + $scope.passwd);
               window.location.href = '#/driver/' + $scope.login;
           }
        }
    }

}

app
    .service('InfoShareService', InfoShareService)
    .controller('LoginController', LoginController);