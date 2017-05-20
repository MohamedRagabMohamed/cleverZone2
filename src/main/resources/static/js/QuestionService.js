 app.service('QuestionService', ['$http', function ($http) {

        var mcqBase = 'https://cleverzone.herokuapp.com/MCQQuestion/';
        var tfBase = 'https://cleverzone.herokuapp.com/tfquestion/';

        var selectedQuestionToEdit ;
     
        this.getQuestion = function (questionID , type) {
        	if(type == "MCQ")return $http.get(mcqBase + questionID);
        	else if(type == "TF" ) return $http.get(tfBase + questionID);
        };
 
        this.setSelectedQuestionToEdit = function(data){
        	return this.selectedQuestionToEdit = data;
        }
        
        this.getSelectedQuestionToEdit = function(){
        	return this.selectedQuestionToEdit;
        }

        this.insertQuestion = function (gameID, data ,type) {
        	if(type == "MCQ")return $http.post(mcqBase + gameID, data);
        	else if(type == "TF" ) return $http.post(tfBase + gameID, data);
        };

        this.updateQuestion = function (questionID, data ,type) {
        	if(type == "MCQ")return $http.put(mcqBase + questionID, data);
        	else if(type == "TF" ) return $http.put(tfBase + questionID, data);
        };

        this.deleteQuestion = function (questionID, type) {
        	if(type == "MCQ")return $http.delete(mcqBase + questionID);
        	else if(type == "TF" ) return $http.delete(tfBase + questionID);
        };
//
//        this.getOrders = function (id) {
//            return $http.get(urlBase + '/' + id + '/orders');
//        };
    }]);