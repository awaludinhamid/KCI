/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  
  //
  var jobNamePattern = "";
  var packNamePattern = "";
  var dateProcess = "";
  var status = allDataCode;
  var modalLogMonitor = $("div#mdl-log-monitor");
  var mdlConfirm = $("div#mdl-confirm");
  var scope = modalLogMonitor.scope();
  
  //
  $("div#side-menu>div#log-monitor").click(function() {
    currPageNo = 1;
    scope.showLogMonTable(modalLogMonitor);
    scope.genDateProcessList();
    modalLogMonitor.modal("show");
  });
  
  //
  modalLogMonitor.find("div#pagination>ul").on("click","li",function() {
    scope.jobNamePattern = jobNamePattern;
    scope.packNamePattern = packNamePattern;
    scope.dateProcess = dateProcess;
    scope.status = status;
    clickPageNo($(this), modalLogMonitor, scope.showLogMonTable);
  });
  
  //
  modalLogMonitor.find("img#img-find-record").click(function() {
    currPageNo = 1;
    jobNamePattern = scope.jobNamePattern;
    packNamePattern = scope.packNamePattern;
    dateProcess = scope.dateProcess;
    status = scope.status;
    scope.showLogMonTable(modalLogMonitor);
  });
  
  //
  $("div#mdl-run-job button#btn-run").click(function() {
    scope.runJob(modalLogMonitor,mdlConfirm);
  });
});

//Setup parameter service
kciApp.service("lmdomserv", function() {
  //
  this.showRunJob = function(log) {
    var modalRunJob = $("div#mdl-run-job");
    modalRunJob.find("table>tbody>tr>td:first-child").text(log.jobName);
    modalRunJob.find("table>tbody>tr>td:last-child").text(log.packageName);
    modalRunJob.modal("show");
  };
});

//Setup parameter detail controller
kciApp.controller("kciLogMonCtrl", function($scope,$http,lmdomserv) { 
  //
  $scope.status = allDataCode;
  $scope.statusList = ["Y","N"];
  //
  $scope.isHide = function(value) {
    if(value === null || value === undefined)
      return true;
    return false;
  };
  //
  $scope.statusIcon = function(status) {
    if(status === "Y")
      return "ok-icon.png";
    return "delete-icon.png";
  };
  //
  $scope.showLogMonTable = function(modalRoleAccess){ 
    modalRoleAccess.css("opacity","0.1");
    showCover(true,true); 
    var params = {
      jobNamePattern: $scope.jobNamePattern,
      packNamePattern: $scope.packNamePattern,
      dateProcess: $scope.dateProcess,
      status: $scope.status
    };
    $http.get("../../apps/data/logmonitor/"+currPageNo,{params:params})
            .then(function(response) {
      var dataTmp = response.data.content;
      var dataTmpLength = dataTmp.length;
      if(dataTmpLength < pagingRecords) {
        for(var idx = 0; idx < pagingRecords - dataTmpLength; idx++)
          dataTmp.push({});
      }
      $scope.logMonitorTable = dataTmp; 
      execPaging(response.data.count);
      showCover(true,false); 
      modalRoleAccess.css("opacity","1");
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
  //
  $scope.showRunJob = function(log) {
    $scope.jobName = log.jobName;
    lmdomserv.showRunJob(log);
  };
  //
  $scope.runJob = function(modalLogMonitor,mdlConfirm) { 
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/edit-icon.png").attr("alt","Edit icon");
    mdlConfirm.find(".modal-title>span").text("Edit User");
    $http.put("../../apps/edit/runjob/"+$scope.jobName,null,{}).then(function(response) {
      alert(response);
      mdlConfirmSpan.text("Your job is running now.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showLogMonTable(modalLogMonitor);              
    },function(response) {
      mdlConfirmSpan.text("Failed when executed job ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });   
  };
  //
  $scope.genDateProcessList = function() {
    $http.get("../../apps/data/dateprocess",{}).then(function(response) {
      $scope.dateProcessList = response.data;
    });
  };
});