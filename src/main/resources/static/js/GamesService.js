app.service('GamesService', [ '$http', function($http) {

	var mcqBase = 'http://localhost:8080/mcqgame/';
	var tfBase = 'http://localhost:8080/tfgame/';
	var user = 'http://localhost:8080/user/';
	var selectedGameToPlay;
	var selectedGameToEdit;
	var gameScore;


	this.getGame = function(id, type) {
		if (type == "MCQ") return $http.get(mcqBase + id);
		else if (type == "TF") return $http.get(tfBase + id);
	};


	this.setSelectedGameToPlay = function(data) {
		return this.selectedGameToPlay = data;
	}

	this.getSelectedGameToPlay = function() {
		return this.selectedGameToPlay;
	}

	this.setSelectedGameToEdit = function(data) {
		return this.selectedGameToEdit = data;
	}

	this.getSelectedGameToEdit = function() {
		return this.selectedGameToEdit;
	}

	this.setGameScore = function(data, userId, gameId) {
		$http.get(user + userId + '/' + gameId + '/' + data.scoreFront);
		return this.gameScore = data;
	}

	this.getGameScore = function(data) {
		return this.gameScore;
	}

	this.insertGame = function(id, data, type) {
		if (type == "MCQ") return $http.post(mcqBase + id, data);
		else if (type == "TF") return $http.post(tfBase + id, data);
	};
	this.getAllGame = function() {
		var allGame = [];
		$http.get(mcqBase)
			.then(function successCallback(response) {
				allGame.push(response.data);
			}, function errorCallback(response) {
				alert("Game Failed");
			});
		$http.get(tfBase)
			.then(function successCallback(response) {
				allGame.push(response.data);
			}, function errorCallback(response) {
				alert("Game Failed");
			});
		return allGame;
	}

	this.copygame = function(courseId, gameId, type) {
		if (type == "MCQ") return $http.get(mcqBase + courseId + '/' + gameId);
		else if (type == "TF") return $http.get(tfBase + courseId + '/' + gameId);
	}



	this.updateGame = function(id, data, type) {
		if (type == "MCQ") return $http.put(mcqBase + id, data);
		else if (type == "TF") return $http.put(tfBase + id, data);
	};
//
//        this.deleteCustomer = function (id) {
//            return $http.delete(urlBase + '/' + id);
//        };
//
//        this.getOrders = function (id) {
//            return $http.get(urlBase + '/' + id + '/orders');
//        };
} ]);