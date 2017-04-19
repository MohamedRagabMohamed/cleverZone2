
console.log('LoginController.js') ;
app.controller('LoginController', ["$scope", "$http", function ($scope, $http, $location) {
    $scope.submit = function () {
    	console.log('a7a');

    	var string = $scope.username+':'+$scope.password;
    	
    	// Encode the String
    	var encodedString = btoa(string);
    	console.log(encodedString);
    	var url  = "http://"+encodedString+"@localhost:8080/course/";
    	
    	var xmlhttp = new XMLHttpRequest();
    	

    	xmlhttp.onreadystatechange = function() {
    	    if (this.readyState == 4 && this.status == 200) {
    	        var myArr = JSON.parse(this.responseText);
    	        console.log(myArr); 
    	    }
    	};
    	xmlhttp.open("GET", url, true);
    	xmlhttp.send();
    	
    	console.log($scope.username);
    	    	
            }
    
}]);