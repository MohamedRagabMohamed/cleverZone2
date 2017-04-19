
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
        		$cookieStore.put('id',$scope.password);
        		for(var i in response.data){ 
        			if(i == "ROLE_TEACHER")window.location = "/website-instructor-dashboard.html";
        			else window.location = "/website-student-dashboard.html";
        		}
        	  }, function errorCallback(response) {
        		  alert("a7a");
        	  });
        
        
    
    
    
    }
    
}]);