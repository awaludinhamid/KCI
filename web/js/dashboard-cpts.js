/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  
  //
  var param1 = "";
  var param2 = "";
  
  var modalCpts = $("div#mdl-cpts");
  var scope = modalCpts.scope();
  
  //
  $("div#side-menu>div#cpts").click(function() {
    currPageNo = 1;
    if($("span#hasRoleCpts").text() === "true") {      
      scope.showCptsTable(modalCpts);
      modalCpts.modal("show");
    } else {
      showMessage("You have no CPTS role ..!");
    }
  });
  
  //
  modalCpts.find("div#pagination>ul").on("click","li",function() {
    scope.periodePattern = param1;
    scope.otherPattern = param2;
    clickPageNo($(this), modalCpts, scope.showCptsTable);
  });
  
  //
  modalCpts.on("hidden.bs.modal",function() {
    showCover(false,false);
  });
  
  //
  modalCpts.find("img#img-find-record").click(function() {
    currPageNo = 1;
    param1 = scope.periodePattern;
    param2 = scope.otherPattern;
    scope.showCptsTable(modalCpts);
  });
});

//Setup parameter detail controller
kciApp.controller("kciCptsCtrl", function($scope,$http) { 
  //
  $scope.npk = "";
  //
  $scope.showCptsTable = function(modalCpts){ 
    modalCpts.css("opacity","0.1");
    showCover(true,true); 
    var params = {
      param1: $scope.periodePattern,
      param2: $scope.otherPattern,
      npk: $scope.npk
    }
    $http.get("../../apps/data/cpts/"+currPageNo,{params:params}).then(function(response) {
      $scope.cptsTable = response.data.content;
      execPaging(response.data.count);
      modalCpts.css("opacity","1");
      showCover(true,false); 
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
});