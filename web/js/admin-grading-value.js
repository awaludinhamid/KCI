/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  
  //
  var gradeIdPattern = "";
  var gradeNamePattern = "";
  var modalGradingValue = $("div#mdl-grading-value");
  var modalNewGrading = $("div#mdl-new-grading");
  var modalEditGrading = $("div#mdl-edit-grading");
  var mdlConfirm = $("div#mdl-confirm");
  var scope = modalGradingValue.scope();
  
  //
  $("div#side-menu>div#grading-value").click(function() {
    currPageNo = 1;
    scope.showGradingValueTable(modalGradingValue);
    modalGradingValue.modal("show");
  });
  
  //
  modalGradingValue.find("div#pagination>ul").on("click","li",function() {
    scope.gradeIdPattern = gradeIdPattern;
    scope.gradeNamePattern = gradeNamePattern;
    clickPageNo($(this), modalGradingValue, scope.showGradingValueTable);
  });
  
  //
  modalGradingValue.find("img#img-find-record").click(function() {
    currPageNo = 1;
    gradeIdPattern = scope.gradeIdPattern;
    gradeNamePattern = scope.gradeNamePattern;
    scope.showGradingValueTable(modalGradingValue);
  });
  
  //
  modalGradingValue.find("img#img-new-record").click(function() {
    modalNewGrading.find("input").val("");
    modalNewGrading.modal("show");
  });
  
  //
  modalNewGrading.find("button#btn-add").click(function() {
    scope.saveGrading(modalGradingValue,mdlConfirm);
  });
  
  //
  $("div#mdl-delete-grading").find("button#btn-delete").click(function() {
    scope.deleteGrading(modalGradingValue,mdlConfirm);
  });
  
  //
  modalEditGrading.find("button#btn-update").click(function() {
    scope.editGrading(modalGradingValue,mdlConfirm);
  });
});

//Setup parameter service
kciApp.service("gvdomserv", function() {
  //
  this.showDeleteGrading = function(gradval) {
    var modalDeleteGrading = $("div#mdl-delete-grading");
    modalDeleteGrading.find("table>tbody>tr>td:first-child").text(gradval.gradeId);
    modalDeleteGrading.find("table>tbody>tr>td:last-child").text(gradval.gradeName);
    modalDeleteGrading.modal("show");  
  };
  //
  this.showEditGrading = function(gradval) {
    var modalEditGrading = $("div#mdl-edit-grading");
    modalEditGrading.find("table td>input").each(function() {
      var thisModel = $(this).attr("ng-model").replace("gradeEdit.","");
      $(this).val(gradval[thisModel]);
      $(this).trigger("input");
    });
    modalEditGrading.modal("show");
  };
});

//Setup parameter detail controller
kciApp.controller("kciGradesCtrl", function($scope,$http,gvdomserv) { 
  //
  $scope.roleId = 0;
  //
  $scope.isHide = function(value) {
    if(value === null || value === undefined)
      return true;
    return false;
  };
  //
  $scope.showGradingValueTable = function(modalGradingValue){ 
    modalGradingValue.css("opacity","0.1");
    showCover(true,true); 
    $http.get("../../apps/data/grades/"+currPageNo,{params:{gradeIdPattern: $scope.gradeIdPattern, gradeNamePattern: $scope.gradeNamePattern}})
            .then(function(response) {
      var dataTmp = response.data.content;
      var dataTmpLength = dataTmp.length;
      if(dataTmpLength < pagingRecords) {
        for(var idx = 0; idx < pagingRecords - dataTmpLength; idx++)
          dataTmp.push({});
      }
      $scope.gradingValueTable = dataTmp; 
      execPaging(response.data.count);
      showCover(true,false); 
      modalGradingValue.css("opacity","1");
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
  //
  $scope.saveGrading = function(modalGradingValue,mdlConfirm) {
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/new-icon.png").attr("alt","New icon");
    mdlConfirm.find(".modal-title>span").text("New Grade");
    var params = {
      gradeId: $scope.grade.gradeId,
      gradeName: $scope.grade.gradeName,
      batasBawah: $scope.grade.batasBawah,
      batasAtas: $scope.grade.batasAtas
    };
    $http.post("../../apps/save/grade",null,{params:params}).then(function(response) {
      mdlConfirmSpan.text("New grade has been added.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showGradingValueTable(modalGradingValue);
    },function(response) {
      mdlConfirmSpan.text("Failed when added grade ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });
  };
  //
  $scope.showEditGrading = function(gradval) {
    gvdomserv.showEditGrading(gradval);
  };
  //
  $scope.showDeleteGrading = function(gradval) {
    $scope.gradeId = gradval.gradeId;
    gvdomserv.showDeleteGrading(gradval);
  };
  //
  $scope.deleteGrading = function(modalGradingValue,mdlConfirm) { 
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/delete-icon.png").attr("alt","Delete icon");
    mdlConfirm.find(".modal-title>span").text("Delete Grade");
    $http.delete("../../apps/delete/grade",{params:{gradeId: $scope.gradeId}}).then(function(response) {
      mdlConfirmSpan.text("Grade has been deleted.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showGradingValueTable(modalGradingValue);              
    },function(response) {    
      mdlConfirmSpan.text("Failed when deleted grade ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });  
  };
  //
  $scope.editGrading = function(modalGradingValue,mdlConfirm) { 
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/edit-icon.png").attr("alt","Edit icon");
    mdlConfirm.find(".modal-title>span").text("Edit Grade");
    var params = {
      gradeId: $scope.gradeEdit.gradeId,
      gradeName: $scope.gradeEdit.gradeName,
      batasBawah: $scope.gradeEdit.batasBawah,
      batasAtas: $scope.gradeEdit.batasAtas
    };
    $http.put("../../apps/edit/grade",null,{params:params}).then(function(response) {
      mdlConfirmSpan.text("Grade has been updated.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showGradingValueTable(modalGradingValue);              
    },function(response) {    
      mdlConfirmSpan.text("Failed when updated grade ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });      
  };
});