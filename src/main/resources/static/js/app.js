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
			templateUrl : '/pages/TeacherHome.html',
			controller : 'CourseController'
		})
		.when('/teacherPlay', {
			templateUrl : '/pages/TeacherPlay.html',
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
		.when('/allGames', {
			templateUrl : '/pages/allGames.html',
			controller : 'GamesController'
		})
		.when('/TF', {
			templateUrl : '/pages/TFGame.html',
			controller : 'GamesController'
		})
		.when('/TFScore', {
			templateUrl : '/pages/TFGameScore.html',
			controller : 'GamesController'
		})
		.when('/MCQScore', {
			templateUrl : '/pages/MCQGameScore.html',
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
		.when('/gameEdit', {
			templateUrl : '/pages/editGame.html',
			controller : 'GamesController'
		})
		.when('/gameEditForm', {
			templateUrl : '/pages/edit-game-form.html',
			controller : 'GamesController'
		})
		.when('/edit-mcq-question', {
			templateUrl : '/pages/edit-mcq-question-form.html',
			controller : 'GamesController'
		})
		.when('/edit-tf-question', {
			templateUrl : '/pages/edit-tf-question-form.html',
			controller : 'GamesController'
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
			controller : 'UserController',
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
		.when('/add-mcq-question', {
			templateUrl : '/pages/add-mcq-question.html',
			controller : 'GamesController'
		})
		.when('/add-tf-question', {
			templateUrl : '/pages/add-tf-question.html',
			controller : 'GamesController'
		})
		.when('/create-course', {
			templateUrl : '/pages/create-course.html',
			controller : 'CourseController'
		})
		.when('/addcollaborator', {
			templateUrl : '/pages/AddCollaborator.html',
			controller : 'UserController'
		})
		.when('/notify', {
			templateUrl : '/pages/notify.html',
			controller : 'UserController'
		})
		.when('/create-game', {
			templateUrl : '/pages/create-game.html',
			controller : 'GamesController'
	}).otherwise({
		redirectto : '/pages/login.html'
	});

} ]);

