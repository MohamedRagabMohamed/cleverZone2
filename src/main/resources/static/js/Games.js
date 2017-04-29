app.controller('GamesController',
[ "$scope", "$location", "$http","GamesService","CourseService", "QuestionService","UserService", 
function($scope, $location, $http,GamesService,CourseService, QuestionService,UserService) {

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	$scope.setGamePlay = function(id,type) {
		
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
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	$scope.setGameToEdit = function(idd, type) {
		console.log(idd + " " + type);
		GamesService.getGame(idd, type)
		.then(function successCallback(response) {
			console.log(response.data.type);
			GamesService.setSelectedGameToEdit(response.data);
			if(type == "MCQ")
				$location.path('/add-mcq-question');
			else if(type == "TF")
				$location.path('/add-tf-question');
		}, function errorCallback(response) {
			alert("Game data in fetching failed");
		});
	}
	
	$scope.getGameToEdit = function() { //  may be not needed
		$scope.theGame = GamesService.getSelectedGameToEdit();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	$scope.getScore = function(data) {
		console.log(data);
		if(data.type == "MCQ"){
			var score=0;
			for(var i in data.questions){
				if(data.questions[i].UserInput == data.questions[i].answer){
					score+=100;
					data.questions[i].result = 1;
				}else{
					data.questions[i].result = 0;
				}
			}
			data.scoreFront = score;
			GamesService.setGameScore(data,UserService.getUser().id,GamesService.getSelectedGameToPlay().id);
			$location.path('/MCQScore');
		}else if(data.type == "TF"){
			var score=0;
			for(var i in data.questions){
				if(data.questions[i].UserInput == data.questions[i].answer){
					score+=100;
					data.questions[i].result = 1;
				}else{
					data.questions[i].result = 0;
				}
			}
			data.scoreFront = score;
			GamesService.setGameScore(data,UserService.getUser().id,GamesService.getSelectedGameToPlay().id);
			$location.path('/TFScore');
		}
		
		
	}
	
	$scope.getTheScore = function(data) {
		$scope.theScore = GamesService.getGameScore(data);
		console.log($scope.theScore);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	$scope.createGame = function() {

		data = {
			"name" : $scope.name,
			"descption" : $scope.decription,
			"imageSrc" : $scope.imgsrc,
			"totalTime" :$scope.time,
			"type" : $scope.type
		}
		GamesService.insertGame(CourseService.getSelectedCourseToEdit().id,data ,$scope.type)
		.then(function successCallback(response) {
			console.log(response.status);
			console.log("Game created successfully");
			$location.path('/teacher');

		}, function errorCallback(response) {

			alert("Error! Game Creation Failed");
		});

	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	$scope.addQuestion = function() {
		
		
		
		console.log(GamesService.getSelectedGameToEdit().type);
		$scope.senddata ;
		if(GamesService.getSelectedGameToEdit().type == "MCQ")
		{
			$scope.senddata = {
				    "choices": [
				      $scope.choice1,
				      $scope.choice2,
				      $scope.choice3,
				      $scope.choice4
				    ],
				    
				    "answer": $scope.answer, 
				    "question": $scope.question
				  }
		}
		else if(GamesService.getSelectedGameToEdit().type == "TF")
		{
			$scope.senddata = {
				    "answer": $scope.answer,
				    "question": $scope.question
		  	   		}
		}
		
		console.log($scope.senddata);
		
		QuestionService.insertQuestion(GamesService.getSelectedGameToEdit().id, $scope.senddata ,GamesService.getSelectedGameToEdit().type) // scope.type may be bug
		.then(function successCallback(response) {
			console.log(response.status);
			console.log("Question added successfully");
			if(GamesService.getSelectedGameToEdit().type == "MCQ"){
				  $scope.choice1 = angular.copy($scope.master);
			      $scope.choice2 = angular.copy($scope.master);
			      $scope.choice3 = angular.copy($scope.master);
			      $scope.choice4 = angular.copy($scope.master);
			      $scope.time = angular.copy($scope.master);
				  $scope.answer = angular.copy($scope.master);
				  $scope.question = angular.copy($scope.master);
			}
			else if(GamesService.getSelectedGameToEdit().type == "TF"){
				  $scope.time = angular.copy($scope.master);
				  $scope.answer = angular.copy($scope.master);
				  $scope.question = angular.copy($scope.master);
			}

		}, function errorCallback(response) {

			alert("Error! Question Insertion Failed");
		});

	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	$scope.changeRoute = function(path)
	{
		$location.path(path);
	}
	
//	$scope.resetForm = function() {
//		
//		if(GamesService.getSelectedGameToEdit().type == "MCQ"){
//			$scope.choice1 = angular.copy($scope.master);
//		      $scope.choice2 = angular.copy($scope.master);
//		      $scope.choice3 = angular.copy($scope.master);
//		      $scope.choice4 = angular.copy($scope.master);
//		      $scope.time = angular.copy($scope.master);
//			  $scope.answer = angular.copy($scope.master);
//			  $scope.question = angular.copy($scope.master);
//		}
//		else if(GamesService.getSelectedGameToEdit().type == "TF"){
//			  
//		      $scope.time = angular.copy($scope.master);
//			  $scope.answer = angular.copy($scope.master);
//			  $scope.question = angular.copy($scope.master);
//		}
//          
//      };
	
	
	

} ]);