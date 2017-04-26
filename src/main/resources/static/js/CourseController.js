app.controller('CourseController', [ "$scope", "$location", "$http", "CourseService", "UserService", function($scope, $location, $http, CourseService, UserService) {

	$scope.getAllCourses = function() {

		CourseService.getAllCourse()
			.then(function successCallback(response) {
				console.log(response.data);
				$scope.allCourse = response.data;
			}, function errorCallback(response) {
				alert("Course Registed in fetching failed");
			});
	}


	$scope.getCourseRegisted = function() {

		CourseService.getRegistedCourses()
			.then(function successCallback(response) {
				console.log(response.data);
				$scope.RegistedCourse = response.data;
			}, function errorCallback(response) {
				alert("Course Registed in fetching failed");
			});
	}
	$scope.getCourseCreated = function() {
		CourseService.getCreatedCourses()
			.then(function successCallback(response) {
				console.log(response.data);
				$scope.CreatedCourse = response.data;

			}, function errorCallback(response) {
				alert("Course Created in fetching failed");
			});
	}


	$scope.setCourseToPlay = function(idd) {
		CourseService.getCourse(idd)
			.then(function successCallback(response) {
				CourseService.setSelectedCourseToPlay(response.data);
				$location.path('/course');
			}, function errorCallback(response) {
				alert("Course data in fetching failed");
			});
	}

	$scope.getCourseToPlay = function() {
		$scope.theCourse = CourseService.getSelectedCourseToPlay();
	}

	$scope.setCourseToEdit = function(idd) {
		CourseService.getCourse(idd)
			.then(function successCallback(response) {
				CourseService.setSelectedCourseToEdit(response.data);
				$location.path('/courseEdit');
			}, function errorCallback(response) {
				alert("Course data in fetching failed");
			});
	}

	$scope.getCourseToEdit = function() {
		$scope.theCourse = CourseService.getSelectedCourseToEdit();
	}

} ]);