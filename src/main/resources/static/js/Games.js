app.controller('GamesController', [ "$scope", "$location", "$http","GamesService","CourseService", function($scope, $location, $http,GamesService,CourseService) {

	$scope.setGamePlay = function(id,type) {
		alert(type);
		GamesService.getGame(id , type)
		.then(function successCallback(response) {
			GamesService.setSelectedGameToPlay(response.data);
			if(type == "MCQ"){
				$location.path('/MCQ');
			}else if(type == "TF"){
				$location.path('/TF');
			}
		}, function errorCallback(response) {
			alert("Game Failed");
		});
	}

	$scope.getGamePlay = function() {
		$scope.GamePlay = GamesService.getSelectedGameToPlay();
		console.log($scope.GamePlay);
	}
	
	$scope.getScore = function(data) {
		console.log(data);
	}
	
	$scope.create = function() {

		data = {
			"name" : $scope.name,
			"descption" : $scope.decription,
			"imageSrc" : $scope.imgsrc
		}
		GamesService.insertGame(CourseService.getSelectedCourseToEdit().id,data ,$scope.type )
		.then(function successCallback(response) {
			console.log(response.status);
			console.log("Game created successfully");
			$location.path('/teacher');

		}, function errorCallback(response) {

			alert("Error! Game Creation Failed");
		});

	}
	

} ]);