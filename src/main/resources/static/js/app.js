// declare modules


var app = angular.module("CleverZone", [ 'ngCookies', 'ngRoute', 'ui.bootstrap' ]);


app.config([ '$routeProvider', function($routeProvider) {


	$routeProvider
		.when('/', {
			templateUrl : '/pages/login.html',
			controller : 'UserController'
		})
		.when('/studentHome', {
			templateUrl : '/pages/StudentHome.html',
			controller : 'CourseController'
		})
		.when('/teacherHome', {
			templateUrl : '/pages/StudentDashboard.html',
			controller : 'CourseController'
		})
		.when('/student', {
			templateUrl : '/pages/StudentDashboard.html',
			controller : 'CourseController'
		})
		.when('/teacher', {
			templateUrl : '/pages/TeacherDashboard.html',
			controller : 'CourseController',
//			resolve : {
//				"check" : function($location, CommonService) {
//					if (UserService.getUser. == "ROLE_TEACHER") {
//						// no need to Do something
//
//					} else {
//						$location.path('/login'); // redirect user to login.
//						alert("You don't have access here");
//					}
//				}
//			}
		})
		.when('/MCQ', {
			templateUrl : '/pages/MCQGame.html',
			controller : 'GamesController'
		})
		.when('/TF', {
			templateUrl : '/pages/TFGame.html',
			controller : 'GamesController'
		})
		.when('/addGames', {
			templateUrl : '/pages/addGames.html',
			controller : 'GamesController'
		})
		.when('/courseEdit', {
			templateUrl : '/pages/editCourse.html',
			controller : 'CourseController'
		})
		.when('/course', {
			templateUrl : '/pages/TakeCourse.html',
			controller : 'CourseController'
		})
		.when('/login', {
			templateUrl : '/pages/login.html',
			controller : 'UserController'
		})
		.when('/logout', {
			templateUrl : '/pages/login.html',
			resolve : {
				init : function() {
					return function() {
						$http.defaults.headers.common.Authorization = 'Basic ';
					}
				}
			}
		})
		.when('/sign-up', {
			templateUrl : '/pages/sign-up.html',
			controller : 'UserController'
		})
		.when('/create-course', {
			templateUrl : '/pages/create-course.html',
			controller : 'CreateCourseController'
		})
		.when('/create-game', {
		templateUrl : '/pages/create-game.html',
		controller : 'GamesController'
	}).otherwise({
		redirectto : '/pages/login.html'
	});

} ]);
//
//
//
//
//app.controller('Games', [ "$scope", "$http", "$location", "CommonService", function($scope, $http, $location, CommonService) {
//
//	$scope.getGame = function(id, type) {
//		alert(type);
//		if (type == "MCQ") {
//			$http({
//				method : 'GET',
//				url : 'http://localhost:8080/mcqgame/' + id
//			}).then(function successCallback(response) {
//				CommonService.setData('game', response.data);
//			}, function errorCallback(response) {
//				alert("Game MCQ Failed");
//			});
//			$location.path('/MCQ');
//		} else if (type == "TF") {
//			$http({
//				method : 'GET',
//				url : 'http://localhost:8080/tfgame/' + id
//			}).then(function successCallback(response) {
//				CommonService.setData('game', response.data);
//			}, function errorCallback(response) {
//				alert("Game TF Failed");
//			});
//			$location.path('/TF');
//		}
//
//
//	}
//} ]);
//
//
//app.controller('TFGames', [ "$scope", "$http", "$location", "CommonService", function($scope, $http, $location, CommonService) {
//	$scope.aGame = CommonService.getData('game');
//	$scope.getQuestion = function(id) {
//		$http({
//			method : 'GET',
//			url : 'http://localhost:8080/mcqgame/' + id
//		}).then(function successCallback(response) {
//			CommonService.setData('game', response.data);
//		}, function errorCallback(response) {
//			alert("Game MCQ Failed");
//		});
//
//
//	}
//} ]);
//
//
//
//
//
//
//
//app.controller('SignUpController', [ "$route", "$scope", "$http", "$location",
//	function($route, $scope, $http, $location) {
//
//		$scope.register = function() {
//
//
//
//			$http.defaults.headers.common.Authorization = 'Basic';
//			$http({
//				method : 'POST',
//				url : 'http://localhost:8080/user/',
//				data : {
//					"userName" : $scope.username,
//					"firstName" : $scope.firstname,
//					"lastName" : $scope.lastname,
//					"password" : $scope.password,
//					"roles" : [ $scope.type ]
//				}
//			}).then(function successCallback(response) {
//				console.log(response.status);
//				$location.path('/login');
//
//			}, function errorCallback(response) {
//
//				alert("Error! Registeration Failed");
//			});
//
//		}
//
//	} ]);
//
//app.controller('CreateCourseController', [ "$route", "$scope", "$http",
//	"$location", "CommonService",
//	function($route, $scope, $http, $location, CommonService) {
//
//		$scope.user = CommonService.getData('user');
//
//		$scope.create = function() {
//			$http({
//				method : 'POST',
//				url : 'http://localhost:8080/course/' + $scope.user.id,
//				data : {
//					"name" : $scope.name,
//					"descption" : $scope.decription,
//					"imageSrc" : $scope.imgsrc
//				}
//			}).then(function successCallback(response) {
//				console.log(response.status);
//				console.log("course created successfully");
//				$location.path('/teacher');
//
//			}, function errorCallback(response) {
//
//				alert("Error! Course Creation Failed");
//			});
//
//		}
//
//	} ]);
//
//app.controller('CreateGameController', [
//	"$route",
//	"$scope",
//	"$http",
//	"$location",
//	"CommonService",
//	function($route, $scope, $http, $location, CommonService) {
//
//		$scope.course = CommonService.getData('aCourse');
//		console.log($scope.course);
//		alert($scope.course.id);
//
//		$scope.create = function() {
//
//			$scope.url;
//			if ($scope.type = "MCQ_GAME")
//				$scope.url = 'http://localhost:8080/mcqgame/' + $scope.course.id;
//			else if ($scope.type = "TF_GAME")
//				$scope.url = 'http://localhost:8080/tfgame/' + $scope.course.id;else {
//				alert("ERROR!!!");
//				$location.path('/teacher');
//			}
//
//			$http({
//				method : 'POST',
//				url : $scope.url,
//				data : {
//					"name" : $scope.name,
//					"descption" : $scope.decription,
//					"imageSrc" : $scope.imgsrc
//				}
//			}).then(function successCallback(response) {
//				console.log(response.status);
//				console.log("Game created successfully");
//				$location.path('/teacher');
//
//			}, function errorCallback(response) {
//
//				alert("Error! Game Creation Failed");
//			});
//
//		}
//
//	} ]);