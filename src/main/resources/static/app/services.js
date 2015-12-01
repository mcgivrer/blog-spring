(function(angular) {
	var PostFactory = function($resource) {
		return $resource('/posts/:id', {
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

	UserFactory.$inject = [ '$resource' ];
	angular.module("myApp.services").factory("User", UserFactory);
}(angular));