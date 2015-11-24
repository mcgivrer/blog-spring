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
	};

	PostFactory.$inject = [ '$resource' ];
	angular.module("myApp.services").factory("Post", PostFactory);
}(angular));