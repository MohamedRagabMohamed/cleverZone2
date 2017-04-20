
console.log('LoginController.js') ;
app.controller('LoginController', ["$scope", "$http","$cookieStore" ,function ($scope, $http, $cookieStore) {
    $scope.login = function () {
    	
    	$http.defaults.headers.common['Authorization'] = 'Basic ' + btoa($scope.username + ':' + $scope.password);
        $http({
        	  method: 'GET',
        	  url: 'http://localhost:8080/userRole/'
        	}).then(function successCallback(response) {
        		$cookieStore.put('username',$scope.username);
        		$cookieStore.put('password',$scope.password);
        		
        		for(var i in response.data){ 	
        			if(response.data[i] == "ROLE_TEACHER"){$cookieStore.put('type','ROLE_TEACHER');
        									window.location = "/website-instructor-dashboard.html";}
        			else if(response.data[i] == "ROLE_STUDENT") {
        				$cookieStore.put('type','ROLE_STUDENT');
        					window.location = "/website-student-dashboard.html";}
        		}
        	  }, function errorCallback(response) {
        		  alert("a7a");
        	  });
        
    
    }
    
}]);