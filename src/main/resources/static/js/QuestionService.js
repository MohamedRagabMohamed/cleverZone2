 app.service('QuestionService', ['$http', function ($http) {

        var mcqBase = 'http://localhost:8080/MCQQuestion/';
        var tfBase = 'http://localhost:8080/tfquestion/';
//        var selectedGameToPlay ;
//        var selectedGameToEdit ;
//        
//      
//        this.getQuestion = function (id , type) {
//        	if(type == "MCQ")return $http.get(mcqBase + id);
//        	else if(type == "TF" ) return $http.get(tfBase + id);
//        };
//        
//        
//        this.setSelectedQuestion = function(data){
//        	return this.selectedGameToPlay = data;
//        }
//        
//        this.getSelectedQuestion = function(){
//        	return this.selectedGameToPlay;
//        }
//        
//        this.setSelectedGameToEdit = function(data){
//        	return this.selectedGameToEdit = data;
//        }
//        
//        this.getSelectedGameToEdit = function(){
//        	return this.selectedGameToEdit;
//        }

        this.insertQuestion = function (gameID, data ,type) {
        	if(type == "MCQ")return $http.post(mcqBase + gameID, data);
        	else if(type == "TF" ) return $http.post(tfBase + gameID, data);
        };
//
//        this.updateCustomer = function (cust) {
//            return $http.put(urlBase + '/' + cust.ID, cust)
//        };
//
//        this.deleteCustomer = function (id) {
//            return $http.delete(urlBase + '/' + id);
//        };
//
//        this.getOrders = function (id) {
//            return $http.get(urlBase + '/' + id + '/orders');
//        };
    }]);