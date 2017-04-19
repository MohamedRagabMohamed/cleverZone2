app.controller('CourseController', ["$scope", "$http" ,function ($scope, $http, $location) {
    $scope.get = function () {
    	$http.defaults.headers.common['Authorization'] = 'Basic ' + btoa($scope.username + ':' + $scope.password);
        $http({
        	  method: 'GET',
        	  url: 'http://localhost:8080/userRole/'
        	}).then(function successCallback(response) {
        		console.log(response.data);
        		for(var i in response.data){ 
        			if(i == "ROLE_TEACHER")window.location = "/website-instructor-dashboard.html";
        			else window.location = "/website-student-dashboard.html";
        		}
        		
        	  }, function errorCallback(response) {
        	    
        		  alert("a7a");
        	  });
    }
    
    
    
    
    
    
    
}]);