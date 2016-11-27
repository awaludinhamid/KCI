/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  
  //
  var emplNamePattern = "";
  var roleId = 0;
  var modalRoleAccess = $("div#mdl-role-access");
  var modalNewUser = $("div#mdl-new-user");
  var modalEditUser = $("div#mdl-edit-user");
  var mdlConfirm = $("div#mdl-confirm");
  var scope = modalRoleAccess.scope();
  
  //
  $("div#side-menu>div#role-access").click(function() {
    currPageNo = 1;
    scope.showRoleAccTable(modalRoleAccess);
    scope.genRoleList();
    modalRoleAccess.modal("show");
  });
  
  //
  modalRoleAccess.find("div#pagination>ul").on("click","li",function() {
    scope.emplNamePattern = emplNamePattern;
    scope.roleId = roleId;
    clickPageNo($(this), modalRoleAccess, scope.showRoleAccTable);
  });
  
  //
  modalRoleAccess.find("img#img-find-record").click(function() {
    currPageNo = 1;
    emplNamePattern = scope.emplNamePattern;
    roleId = scope.roleId;
    scope.showRoleAccTable(modalRoleAccess);
  });
  
  //
  modalRoleAccess.find("img#img-new-record").click(function() {
    modalNewUser.find("input#npk").val("");
    modalNewUser.find("input:checkbox").prop("checked",false);
    modalNewUser.find("table:first-child>tbody>tr>td:first-child").text("");
    modalNewUser.modal("show");
  });
  
  //
  modalNewUser.find("img#img-find-empl").click(function() {
    scope.showListEmployee(modalNewUser,"table:first-child>tbody>tr>td:first-child");
  });
  
  //
  modalNewUser.find("button#btn-add").click(function() {
    scope.saveUser(modalRoleAccess,modalNewUser,mdlConfirm);
  });
  
  //
  $("div#mdl-delete-user").find("button#btn-delete").click(function() {
    scope.deleteUser(modalRoleAccess,mdlConfirm);
  });
  
  //
  modalEditUser.find("button#btn-update").click(function() {
    scope.editUser(modalRoleAccess,modalEditUser,mdlConfirm);
  });
});

//Setup parameter service
kciApp.service("radomserv", function() {
  //
  this.showDeleteUser = function(roleacc) {
    var modalDeleteUser = $("div#mdl-delete-user");
    modalDeleteUser.find("table>tbody>tr>td:first-child").text(roleacc.employee.namaEmployee);
    modalDeleteUser.find("table>tbody>tr>td:last-child").text(roleacc.role.roleName);
    modalDeleteUser.modal("show");  
  };
  //
  this.showEditUser = function(roleacc) {
    var modalEditUser = $("div#mdl-edit-user");
    modalEditUser.find("table:first-child>tbody>tr>td:first-child").text(roleacc.employee.namaEmployee);
    modalEditUser.find("table:first-child>tbody>tr>td:last-child").text(roleacc.role.roleName);
    modalEditUser.find("table:last-child input:checkbox").each(function() {
      $(this).prop("checked",roleacc[$(this).attr("id")] === "true");
    });
    modalEditUser.modal("show");
  };
});

//Setup parameter detail controller
kciApp.controller("kciRoleAccCtrl", function($scope,$http,radomserv) { 
  //
  $scope.roleId = 0;
  //
  $scope.isHide = function(value) {
    if(value === null || value === undefined)
      return true;
    return false;
  };
  //
  $scope.showRoleAccTable = function(modalRoleAccess){ 
    modalRoleAccess.css("opacity","0.1");
    showCover(true,true); 
    $http.get("../../apps/data/roleacc/"+currPageNo,{params:{emplNamePattern: $scope.emplNamePattern, roleId: $scope.roleId}})
            .then(function(response) {
      var dataTmp = response.data.content;
      var dataTmpLength = dataTmp.length;
      if(dataTmpLength < pagingRecords) {
        for(var idx = 0; idx < pagingRecords - dataTmpLength; idx++)
          dataTmp.push({});
      }
      $scope.roleAccessTable = dataTmp; 
      execPaging(response.data.count);
      showCover(true,false); 
      modalRoleAccess.css("opacity","1");
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
  //
  $scope.showListEmployee = function(modalNewUser,element) {
    var elemToFill = modalNewUser.find(element);
    modalNewUser.css("opacity","0.1");
    showCover(true,true); 
    $http.get("../../apps/data/employee/"+$scope.npk,{}).then(function(response) {
      elemToFill.text(response.data.namaEmployee || "NOT FOUND!");
      if(elemToFill.text() === "NOT FOUND!")
        elemToFill.css("text-shadow","red 0px 0px 4px");
      else
        elemToFill.css("text-shadow","");
      modalNewUser.css("opacity","1");        
      showCover(true,false); 
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
  //
  $scope.saveUser = function(modalRoleAccess,modalNewUser,mdlConfirm) {
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/new-icon.png").attr("alt","New icon");
    mdlConfirm.find(".modal-title>span").text("New User");
    var params = {
      npk: $scope.npk
    };
    modalNewUser.find("input:checkbox").each(function() {
      params[$(this).attr("id")] = $(this).prop("checked")+"";
    });
    $http.post("../../apps/save/user",null,{params:params}).then(function(response) {
      mdlConfirmSpan.text("New user has been added.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showRoleAccTable(modalRoleAccess);
    },function(response) {
      mdlConfirmSpan.text("Failed when added user ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });
  };
  //
  $scope.showEditUser = function(roleacc) {
    $scope.userId = roleacc.userId;
    radomserv.showEditUser(roleacc);
  };
  //
  $scope.showDeleteUser = function(roleacc) {
    $scope.userId = roleacc.userId;
    radomserv.showDeleteUser(roleacc);
  };
  //
  $scope.deleteUser = function(modalRoleAccess,mdlConfirm) { 
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/delete-icon.png").attr("alt","Delete icon");
    mdlConfirm.find(".modal-title>span").text("Delete User");
    $http.delete("../../apps/delete/user",{params:{userId: $scope.userId}}).then(function(response) {
      mdlConfirmSpan.text("User has been deleted.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showRoleAccTable(modalRoleAccess);              
    },function(response) {    
      mdlConfirmSpan.text("Failed when deleted user ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });  
  };
  //
  $scope.editUser = function(modalRoleAccess,modalEditUser,mdlConfirm) { 
    var mdlConfirmSpan = mdlConfirm.find(".modal-body>div>span"); 
    mdlConfirm.find(".modal-title>img").attr("src","../../img/icon/edit-icon.png").attr("alt","Edit icon");
    mdlConfirm.find(".modal-title>span").text("Edit User");
    var params = {
      userId: $scope.userId
    };
    modalEditUser.find("input:checkbox").each(function() {
      params[$(this).attr("id")] = $(this).prop("checked")+"";
    });
    $http.put("../../apps/edit/user",null,{params:params}).then(function(response) {
      mdlConfirmSpan.text("User has been updated.").css("text-shadow","");
      mdlConfirm.modal("show");
      $scope.showRoleAccTable(modalRoleAccess);              
    },function(response) {    
      mdlConfirmSpan.text("Failed when updated user ..!").css("text-shadow","red 0px 0px 4px");
      mdlConfirm.modal("show");
    });      
  };
  //
  $scope.genRoleList = function() {
    $http.get("../../apps/data/roles",{}).then(function(response) {
      $scope.roleList = response.data;
    });
  };
});