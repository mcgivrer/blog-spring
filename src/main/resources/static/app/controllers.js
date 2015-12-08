(function(angular) {
	var AppController = function($scope, Game, $routeParams) {
		Game.query(function(response) {
			$scope.games = response ? response : [];
		});
		
		$scope.game=Game.get('/game/:id',{id:$routeParams.id});

		/*
 		$scope.addItem = function(description) {
			new Item({
				description : description,
				checked : false
			}).$save(function(item) {
				$scope.items.push(item);
			});
			$scope.newItem = "";
		};

		$scope.updateItem = function(item) {
			item.$update();
		};

		$scope.deleteItem = function(item) {
			item.$remove(function() {
				$scope.items.splice($scope.items.indexOf(item), 1);
			});
		};
 		*/
	};

	AppController.$inject = [ '$scope', 'Item' ];
	angular.module("yaBlog.controllers").controller("AppController",
			AppController);
}(angular));