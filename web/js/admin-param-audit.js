/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  
  //
  var paramNamePattern = "";
  var packNamePattern = "";
  var deptId = allDataCode;
  var modalParamAudit = $("div#mdl-param-audit");
  var modalNewParam = $("div#mdl-new-param");
  var modalEditParam = $("div#mdl-edit-param");
  var mdlConfirm = $("div#mdl-confirm");
  var scope = modalParamAudit.scope();
  
  //
  $("div#side-menu>div#param-audit").click(function() {
    currPageNo = 1;
    scope.showParamAuditTable(modalParamAudit);
    scope.genDeptList();    
    scope.genPackageNameList();
    modalParamAudit.modal("show");
  });
  
  //
  modalParamAudit.find("div#pagination>ul").on("click","li",function() {
    scope.paramNamePattern = paramNamePattern;
    scope.packNamePattern = packNamePattern;
    scope.deptId = deptId;
    clickPageNo($(this),modalParamAudit,scope.showParamAuditTable);
  });
  
  //
  modalParamAudit.find("img#img-find-record").click(function() {
    currPageNo = 1;
    paramNamePattern = scope.paramNamePattern;
    packNamePattern = scope.packNamePattern;
    deptId = scope.deptId;
    scope.showParamAuditTable(modalParamAudit);
  });
  
  //
  modalParamAudit.find("img#img-new-record").click(function() {
    modalNewParam.find("table>tbody>tr>td:nth-child(2)").text("");
    modalNewParam.modal("show");
  });
  
  //
  modalNewParam.find("button#btn-add").click(function() {
    scope.saveParam(modalParamAudit,mdlConfirm);
  });
  
  //
  $("div#mdl-delete-param").find("button#btn-delete").click(function() {
    scope.deleteParam(modalParamAudit,mdlConfirm);
  });
  
  //
  modalEditParam.find("button#btn-update").click(function() {
    scope.editParam(modalParamAudit,mdlConfirm);
  });
});

//Setup parameter service
kciApp.service("padomserv", function() {
  //
  this.showDeleteParam = function(roleacc) {
    var modalDeleteParam = $("div#mdl-delete-param");
    modalDeleteParam.find("table>tbody>tr>td:first-child").text(roleacc.employee.namaEmployee);
    modalDeleteParam.find("table>tbody>tr>td:last-child").text(roleacc.role.roleName);
    modalDeleteParam.modal("show");  
  };
  //
  this.showEditParam = function(param,paramEdit) {
    var modalEditParam = $("div#mdl-edit-param");
    modalEditParam.find("table tr>td:nth-child(2)").each(function() {
      var currId = $(this).data("id");
      if(currId === "deptDesc") {
        paramEdit[currId] = param.dept[currId];
      } else {
        paramEdit[currId] = param[currId];
      }
      if(currId === "visible") {
        if(param[currId] === "Y") {
          $(this).children("img:first-child").css("opacity","1");
          $(this).children("img:last-child").css("opacity","0.2");
        } else {
          $(this).children("img:first-child").css("opacity","0.2");
          $(this).children("img:last-child").css("opacity","1");
        }
      } 
    });
    modalEditParam.modal("show");
  };
});

//Setup parameter detail controller
kciApp.controller("kciParamAuditCtrl", function($scope,$http,padomserv) { 
  //
  $scope.deptId = allDataCode;
  $scope.paramEdit = {
    parameterId: "",
    parameterDesc: "",
    deptDesc: "",
    packageName: "",
    visible: ""
  };
  //
  $scope.isHide = function(value) {
    if(value === null || value === undefined)
      return true;
    return false;
  };
  //
  $scope.statusIcon = function(visible) {
    if(visible === "Y")
      return "ok-icon.png";
    return "delete-icon.png";
  };
  //
  $scope.isChoose = function(event) {
    var thisElem = angular.element(event.currentTarget);
    thisElem.css("opacity","1");
    thisElem.siblings("img").css("opacity","0.2");
    $scope.paramEdit.visible = (thisElem.attr("alt").indexOf("Enable")) === -1 ? "N" : "Y";
  };
  //
  $scope.showParamAuditTable = function(modalParamAudit){ 
    modalParamAudit.css("opacity","0.1");
    showCover(true,true); 
    $http.get("../../apps/data/parameter/"+currPageNo,
      {params:{paramNamePattern: $scope.paramNamePattern, packNamePattern: $scope.packNamePattern, deptId: $scope.deptId}})
            .then(function(response) {
      $scope.pageStartNo = (currPageNo-1) * pagingRecords;
      var dataTmp = response.data.content;
      var dataTmpLength = dataTmp.length;
      if(dataTmpLength < pagingRecords) {
        for(var idx = 0; idx < pagingRecords - dataTmpLength; idx++)
          dataTmp.push({});
      }
      $scope.paramAuditTable = dataTmp; 
      execPaging(response.data.count);
      showCover(true,false); 
      modalParamAudit.css("opacity","1");
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
  //
  $scope.saveParam = function(modalParamAudit,mdlConfirm) {
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/new-icon.png").attr("alt","New icon");
    mdlConfirm.find(".modal-title>span").text("New User");
    var params = {
      parameterId: $scope.parameterId,
      parameterDesc: $scope.parameterDesc,
      deptId: $scope.deptId,
      packageName: $scope.packageName,
      visible: $scope.visible
    };
    $http.post("../../apps/save/parameter",null,{params:params}).then(function(response) {
      mdlConfirmSpan.text("New parameter has been added.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showParamAuditTable(modalParamAudit);
    },function(response) {
      mdlConfirmSpan.text("Failed when added parameter ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });
  };
  //
  $scope.showEditParam = function(param) {
    padomserv.showEditParam(param,$scope.paramEdit);
  };
  //
  $scope.showDeleteParam = function(roleacc) {
    $scope.parameterId = roleacc.parameterId;
    padomserv.showDeleteParam(roleacc);
  };
  //
  $scope.deleteParam = function(modalParamAudit,mdlConfirm) { 
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/delete-icon.png").attr("alt","Delete icon");
    mdlConfirm.find(".modal-title>span").text("Delete User");
    $http.delete("../../apps/delete/parameter",{params:{parameterId: $scope.parameterId}}).then(function(response) {
      mdlConfirmSpan.text("Parameter has been deleted.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showParamAuditTable(modalParamAudit);              
    },function(response) {    
      mdlConfirmSpan.text("Failed when deleted parameter ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });  
  };
  //
  $scope.editParam = function(modalParamAudit,mdlConfirm) { 
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/edit-icon.png").attr("alt","Edit icon");
    mdlConfirm.find(".modal-title>span").text("Edit User");
    $http.put("../../apps/edit/parameter",null,{params:$scope.paramEdit}).then(function(response) {
      mdlConfirmSpan.text("Parameter has been updated.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showParamAuditTable(modalParamAudit);              
    },function(response) {    
      mdlConfirmSpan.text("Failed when updated parameter ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });      
  };
  //
  $scope.genDeptList = function() {
    $http.get("../../apps/data/departments",{}).then(function(response) {
      $scope.deptList = response.data;
    });
  };
  //
  $scope.genPackageNameList = function() {
    $http.get("../../apps/data/packagenames",{}).then(function(response) {
      $scope.packageNameList = response.data;
    });
  };
});