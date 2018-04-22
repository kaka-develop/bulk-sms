
var conId = $("#con-id").val();

var contactModule = angular.module('contactModule',[]);

contactModule.controller('contactController',['$scope','$http', function($scope,$http){
	
	var getGroupsByContactId = function(id){
		$http.get('/group/api/list/' + id).success(function(data) {
			$scope.selectedGroups = data;
		});
	};
	
	var findAllGroups = function(){
		$http.get('/group/api/all').success(function(data) {
			$scope.groups = data;
		});
		
		$("#group-loading").attr("hidden",false);
	};
	
	$scope.deleteGroupOfContact = function(gId){
		$http.get('/group/api/del/' + conId +'&' + gId).success(function(data) {
			getGroupsByContactId(conId);
		});
	};
	
	$scope.addContactIntoGroup = function(){
		var gId = document.getElementById("group-options").value;
		$http.get('/contact/api/new/' + conId +'&' + gId).success(function(data) {
			getGroupsByContactId(conId);
		});
	};
	
	findAllGroups();
	getGroupsByContactId(conId);
}]);