 app.service('UserService', ['$http', function ($http) {

        var urlBase = 'http://localhost:8080/';
        var user ; 
        this.login = function () {
            return $http.get(urlBase+'getuser/');
        };

        this.insertUser = function (user) {
        	$http.defaults.headers.common.Authorization = 'Basic';
            return $http.post(urlBase+'user/', user);
        };
        
        this.setUser = function(data){
        	return this.user = data;
        }
        
        this.getUser = function(){
        	return this.user;
        }
        
        ///////////////////////////////////////////////////////////////
        
        this.alluser = function(){
        	return $http.get(urlBase+'user/');
        }
        this.addcollaborator = function(userid,gameid, data){
        	return $http.post(urlBase+'user/'+userid+'/'+gameid, data);
        }
        
        
        
    }]);