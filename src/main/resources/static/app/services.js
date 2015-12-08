(function(angular) {
	var GameFactory = function($resource) {
		return $resource('/game/:id', {
			id : '@id'
		}, {
			update : {
				method : "PUT"
			},
			remove : {
				method : "DELETE"
			}
		});
	},
	UserFactory = function($resource) {
		return $resource('/users/:username', {
			id : '@username'
		}, {
			update : {
				method : "PUT"
			},
			remove : {
				method : "DELETE"
			}
		});
	};

	GameFactory.$inject = [ '$resource' ];
	angular.module("yaBlog.services").factory("Game", GameFactory);

	UserFactory.$inject = [ '$resource' ];
	angular.module("yaBlog.services").factory("User", UserFactory);

}(angular));