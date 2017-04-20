app.controller('CourseController', ["$scope", "$http","$cookieStore" ,function ($scope, $http, $cookieStore) {
    
	
	$scope.init = function () {
		
	    if($cookieStore.get('type') == "ROLE_TEACHER" ){
	    	$scope.getCourseCreated();
	    }else{
	    	$scope.getCourseRegisted();
	    }
	};
	
	$scope.getCourseRegisted = function () {
    	$http.defaults.headers.common['Authorization'] = 'Basic ' + btoa($cookieStore.get('username') + ':' + $cookieStore.get('password')  );
        $http({
        	  method: 'GET',
        	  url: 'http://localhost:8080/courseRegistedIn/'
        	}).then(function successCallback(response) {
        		console.log(response.data);
        		$scope.RegistedCourse = response.data; 
        	  }, function errorCallback(response) {
        	
        		  alert("Course Registed in fetching failed");
        	  });
    }
    $scope.getCourseCreated = function () {
    	$http.defaults.headers.common['Authorization'] = 'Basic ' + btoa($cookieStore.get('username') + ':' + $cookieStore.get('password')  );
        $http({
        	  method: 'GET',
        	  url: 'http://localhost:8080/courseCreated/'
        	}).then(function successCallback(response) {
        		console.log(response.data);
        		$scope.CreatedCourse = response.data; 
        	  }, function errorCallback(response) {
        		  alert("Course Created in fetching failed");
        	  });
    }
    
    
    
    $scope.getCourse = function(idd){
    	console.log(idd);
    	$http.defaults.headers.common['Authorization'] = 'Basic ' + btoa($cookieStore.get('username') + ':' + $cookieStore.get('password')  );
        $http({
        	  method: 'GET',
        	  url: 'http://localhost:8080/course/'+idd,
        	
        	}).then(function successCallback(response) {
        		$scope.Course = response.data;
        		console.log($scope.Course);
        		$scope.mazen = "55555555555555555555555";
        		console.log($scope.mazen);
        		window.location = "/website-take-course.html";
        	  }, function errorCallback(response) {
        		  alert("Course data in fetching failed");
        	  });
    }
    
    
}]);