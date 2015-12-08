(function(angular) {
	angular.module("yaBlog.controllers", []);
	angular.module("yaBlog.services", []);
	angular.module("yaBlog", ["ngRoute", "ngResource",  "yaBlog.controllers", 
			"yaBlog.services" ]).config([function ($routeProvider) {
				$routeProvider
				.when('/game/:id',{
			 		templateUrl: 'views/game-view.html',
			        controller: 'AppController'
				})
				.when('/games',{
			 		templateUrl: 'views/game-list.html',
			        controller: 'AppController'
				})
				.when('/tag',{
					templateUrl: 'views/game-list.html',
					controller: 'AppController'
				})
				.when('/platform/:platform',{
					templateUrl: 'views/game-list.html',
					controller: 'AppController'
				})
				.when('/author/:author',{
					templateUrl: 'views/game-list.html',
					controller: 'AppController'
				})
				.when('/createdAt/:createdAt',{
					templateUrl: 'views/game-list.html',
					controller: 'AppController'
				})
				.otherwise({
					redirectTo: '/games'
				});
		}]);
}(angular));
