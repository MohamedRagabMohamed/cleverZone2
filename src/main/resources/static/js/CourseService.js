 app.service('CourseService', ['$http', function ($http) {

        var urlBase = 'http://localhost:8080/';
        var selectedCourseToPlay ;
        var selectedCourseToEdit ;
        
        this.getRegistedCourses = function () {
            return $http.get(urlBase+'courseRegistedIn/');
        };

        this.getCreatedCourses = function () {
            return $http.get(urlBase + 'courseCreated/');
        };
        
        this.getAllCourse = function () {
            return $http.get(urlBase + 'course/');
        };
        
        this.getCourse = function (id) {
            return $http.get(urlBase + 'course/' + id);
        };
        
        this.setSelectedCourseToPlay = function(data){
        	return this.selectedCourseToPlay = data;
        }
        
        this.getSelectedCourseToPlay = function(){
        	return this.selectedCourseToPlay;
        }
        
        this.setSelectedCourseToEdit = function(data){
        	return this.selectedCourseToEdit = data;
        }
        
        this.getSelectedCourseToEdit = function(){
        	return this.selectedCourseToEdit;
        }
		
		this.insertCourse = function (userID ,data) {
            return $http.post(urlBase + 'course/' + userID, data);
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