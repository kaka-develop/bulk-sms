
var groupId = document.getElementById("group-id").value;
var groupModule = angular.module('groupModule',[]);

groupModule.controller('GroupController',['$scope','$http', function($scope,$http){
	var getContactsByGroupId = function(id){
		$http.get('/contact/api/list/' + id).success(function(data) {
			$scope.selectedContacts = data;
		});
		
		$("#contact-loading").attr("hidden",false);
	};
	
	var findAllGroups = function(){
		$http.get('/contact/api/all').success(function(data) {
			$scope.contacts = data;
		});
	};
	
	$scope.deleteContactOfGroup = function(cId){
		$http.get('/group/api/del/' +cId + '&' + groupId ).success(function(data) {
			getContactsByGroupId(groupId);
		});
	};
	
	$scope.setGroupToContact = function(){
		var cId = document.getElementById("contact-options").value;
		$http.get('/contact/api/new/' + cId +'&' + groupId).success(function(data) {
			getContactsByGroupId(groupId);
		});
	};
	
	findAllGroups();
	getContactsByGroupId(groupId);
}]);