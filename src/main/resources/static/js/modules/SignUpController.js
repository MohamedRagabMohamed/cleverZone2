

// app.controller("SignUpController", ['$scope', '$http', function($scope,
// $http) {
// $scope.companies = [];
//	
// $scope.addRowAsyncAsJSON = function(){
// $scope.companies.push({ 'userName':$scope.username, 'firstName':
// $scope.firstname, 'lastName':$scope.lastname, 'password': $scope.password,
// 'roles': ['ROLE_TEACHER'] });
// // Writing it to the server
// //
// var dataObj = {
// 'userName':$scope.username,
// 'firstName': $scope.firstname,
// 'lastName':$scope.lastname,
// 'password': $scope.password,
// 'roles': ['ROLE_TEACHER']
// };
// var res = $http.post('/user/', dataObj);
//		
// console.log(res.status);
//		
// res.success(function(response) {
// $scope.message = data;
// });
// res.error(function(data, status, headers, config) {
// alert( "failure message: " + JSON.stringify({data: data}));
// });
//		
//		
// // res.then(function successCallback(response) {
// // console.log(response.data);
// // for(var i in response.data){
// // if(i == "ROLE_TEACHER")window.location =
// "/website-instructor-dashboard.html";
// // else window.location = "/website-student-dashboard.html";
// // }
// //
// // }, function errorCallback(response) {
// //
// // alert("a7a");
// // alert( "failure message: " + JSON.stringify({dataObj: data}));
// // });
//				
// // Making the fields empty
// //
// $scope.username='';
// $scope.firstname='';
// $scope.lastname='';
// $scope.password='';
// };
// }]);

console.log('SignUpController.js');
app.controller('SignUpController', [ "$scope", "$http",
		function($scope, $http, $location) {
			$scope.register = function() {
				
				console.log('zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz');
				console.log('Role == ' + $scope.type);

				$http({
					method : 'POST',
					url : 'http://localhost:8080/user/',
					data : {
						'userName' : $scope.username,
						'firstName' : $scope.firstname,
						'lastName' : $scope.lastname,
						'password' : $scope.password,
						'roles' : [$scope.type]
					}
				}).then(function successCallback(response) {
					console.log(response.status);
					console.log("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
					if ($scope.type == "ROLE_TEACHER")
						window.location = "/website-instructor-dashboard.html";
					else
						window.location = "/website-student-dashboard.html";

				}, function errorCallback(response) {

					alert("Error! Registeration Failed");
				});

			}

		} ]);
