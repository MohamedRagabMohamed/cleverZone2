 app.service('CourseService', ['$http', function ($http) {

        var urlBase = 'http://localhost:8080/';
        var selectedCourse ;
        
        
        this.getRegistedCourses = function () {
            return $http.get(urlBase+'courseRegistedIn/');
        };

        this.getCreatedCourses = function () {
            return $http.get(urlBase + 'courseCreated/');
        };
        
        this.getCourse = function (id) {
            return $http.get(urlBase + 'course/' + id);
        };
        
        this.setSelectedCourse = function(data){
        	return this.selectedCourse = data;
        }
        
        this.getSelectedCourse = function(){
        	return this.selectedCourse;
        }

//        this.insertCustomer = function (cust) {
//            return $http.post(urlBase, cust);
//        };
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