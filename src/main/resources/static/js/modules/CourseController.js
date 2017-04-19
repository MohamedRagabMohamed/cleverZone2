app.controller('CourseController', ["$scope", "$http","$cookieStore" ,function ($scope, $http, $cookieStore) {
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
        	
        		  alert("Course Registed in fetching failed");
        	  });
    }
    
    
    
    
    
    
}]);