// declare modules
console.log('app.js') ;
var app = angular.module("CleverZone", []);
//  
//.config(['$routeProvider', function ($routeProvider) {
// 
//    $routeProvider
//        .when('http://localhost:8080/login', {
//            controller: 'LoginController',
//            templateUrl: 'modules/authentication/views/login.html'
//        })
//  
//        .when('/', {
//            controller: 'HomeController',
//            templateUrl: '/index.html'
//        })
//  
//        .otherwise({ redirectTo: '/login' });
//}])
//  
//.run(['$rootScope', '$location', '$cookieStore', '$http',
//    function ($rootScope, $location, $cookieStore, $http) {
//        // keep user logged in after page refresh
//        $rootScope.globals = $cookieStore.get('globals') || {};
//        if ($rootScope.globals.currentUser) {
//            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
//        }
//  
//        $rootScope.$on('$locationChangeStart', function (event, next, current) {
//            // redirect to login page if not logged in
//            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
//                $location.path('/login');
//            }
//        });
//    }]);