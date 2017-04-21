// declare modules

var app = angular.module("CleverZone", ['ngCookies' , 'ngRoute']);


app.config(['$routeProvider',function($routeProvider) {
	
	$routeProvider
		.when('/',{
			templateUrl :'/pages/login.html',
			controller : 'LoginController'
		})
		.when('/student',{
			templateUrl :'/pages/StudentDashboard.html',
			controller : 'CourseController'
		})
		.when('/login',{
			
			templateUrl :'/pages/login.html',
			controller : 'LoginController'
		})
		.otherwise({
			redirectto :'/pages/login.html'
			})
}]);


app.factory('CommonService', function () {
	var headInfo = [];
	return {
	    setData: function (key, data) {
	        headInfo[key] = data;
	    },
	    getData: function (key) {
	        return headInfo[key];
	    }
	}});



app.controller('CourseController', ["$scope","$location", "$http","$cookieStore","CommonService",function ($scope, $http, $cookieStore,$location,CommonService) {
    
	 
	CommonService.setData('Dataname',{name:"bla", price:25});
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
        		
        		console.log(response.data);
        		//$rootScope.aCourse = response.data;
        		 $scope.add = "555555555555555";
        		 $location.path(view);
        	  }, function errorCallback(response) {
        		  alert("Course data in fetching failed");
        	  });
    }
    
    
}]);




app.controller('Course2', [ "$scope", "$http", "$cookieStore","CommonService" ,function($scope, $http, $cookieStore,CommonService) {

	console.log(CommonService.getData('Dataname'));
	CommonService.getData('Dataname');

} ]);





app.controller('LoginController', ["$route","$scope", "$http","$location","$cookieStore" ,function ($route,$scope, $http,  $location,$cookieStore) {
    $scope.login = function () {
    	console.log($scope.Username);
    	$http.defaults.headers.common['Authorization'] = 'Basic ' + btoa($scope.Username + ':' + $scope.Password);
        $http({
        	  method: 'GET',
        	  url: 'http://localhost:8080/userRole/'
        	}).then(function successCallback(response) {
        		$cookieStore.put('username',$scope.Username);
        		$cookieStore.put('password',$scope.Password);
        		
        		for(var i in response.data){ 	
        			if(response.data[i] == "ROLE_TEACHER"){
        									$cookieStore.put('type','ROLE_TEACHER');
        									$route.current = "/student";
        									
        			}
        			else if(response.data[i] == "ROLE_STUDENT") {
        				$cookieStore.put('type','ROLE_STUDENT');
        				//$window.location = "#/student";
        				 $location.path('/student');
        			}
        		}
        	  }, function errorCallback(response) {
        		  alert("a7a");
        	  });
        
    
    }
    
}]);




