app.controller('UserController', [	"$scope","$http","$location","UserService",	function($scope, $http, $location, UserService) {
		$scope.login = function() {
			console.log($scope.Username);
			$http.defaults.headers.common['Authorization'] = 'Basic '+ btoa($scope.Username + ':' + $scope.Password);
			
			UserService.login()
			.then(function successCallback(response) {
				UserService.setUser(response.data);
				for (var i in response.data.roles) {
					if (response.data.roles[i] == "ROLE_TEACHER") {
						$location.path('/teacher');
					} else if (response.data.roles[i] == "ROLE_STUDENT") {
						$location.path('/student');
					}
				}
				console.log(UserService.getUser());
			}, function errorCallback(response) {
				alert("failure");
			});

		}

	















} ]);