/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//global variable

$(document).ready(function() {
  
  // on click filter
  $("div#filter>div>ul").on("click","li",function() { 
    var thisElem = $(this);
    var thisUl = $(this).parent("ul");
    window["prev"+thisUl.data("var")] = window[thisUl.data("var")];
    window[thisUl.data("var")] = thisElem.data("code");
    thisUl.siblings("span").html(thisElem.text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>");
    //region change propagate branches list change
    var thisUlParent = thisUl.parent("div");
    if(thisUlParent.attr("id") === "filter-region" && prevregion !== region) {
      var filterBranch = $("div#filter-branch");
      //var thisUlSiblingSpan = thisUlParent.siblings("div#filter-branch").children("span");
      var thisUlSiblingSpan = filterBranch.children("span");
      if(thisUlSiblingSpan.text() !== allDataCode) {
        thisUlSiblingSpan.html(allDataCode+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;");
        prevbranch = branch;
        branch = allDataCode;
      }
      var scope = filterBranch.scope();
      scope.getBranches(thisElem.data("code"));  
    }    
  });
});
  
/****** Angular Section ******/

//Setup filter controller
kciApp.controller("kciFilterCtrl", function($scope,$http) {
  //filter branch
  $scope.getBranches = function(areaCode){    
    $http.get("../../apps/data/branches/"+areaCode).then(function(response) {
      $scope.filterBranchList = response.data;
      var filterBranchSpan = $("div#filter>div#filter-branch>span");
      if(filterBranchSpan.text().trim() === "")
        filterBranchSpan.append(
            $scope.filterBranchList[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;"
          );
    });
  };
  $scope.getBranches(allDataCode);
  //filter periode
  $http.get("../../apps/data/allperiodes").then(function(response) {
    $scope.filterPeriodeList = dataperiode = response.data;
    $("div#filter>div#filter-periode>span").append(
        $scope.filterPeriodeList[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;"
      );
  });
  //filter company
  $http.get("../../apps/data/allcompanies").then(function(response) {
    $scope.filterCompanyList = datacompany = response.data;
    $("div#filter>div#filter-company>span").append(
        $scope.filterCompanyList[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;"
      );
  });
  //filter lob
  $http.get("../../apps/data/allbussunits").then(function(response) {
    $scope.filterLobList = response.data;
    $("div#filter>div#filter-lob>span").append(
        $scope.filterLobList[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;"
      );
  });
  //filter region
  $http.get("../../apps/data/allregions").then(function(response) {
    $scope.filterRegionList = dataarea = response.data;
    $("div#filter>div#filter-region>span").append(
        $scope.filterRegionList[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;"
      );
  });
  //filter department
  $http.get("../../apps/data/alldepartments").then(function(response) {
    $scope.filterDepartmentList = datadept = response.data;
    $("div#filter>div#filter-department>span").append(
        $scope.filterDepartmentList[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;"
      );
  });
});