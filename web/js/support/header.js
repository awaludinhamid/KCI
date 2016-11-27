/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// VARIABLE
var allDataCode = "ALL";

//cover and loading animation
function showCover(coverState,animState,exclElem,inclElem) {  
  var arrToMod = ["div#side-menu","div#filter",".title-dashboard",".content-dashboard"];
  if(coverState) {
    $("div.div-cover").show();
    $.each(arrToMod,function(idx,val) {
      $(val).css("filter","blur(5px)");//.css("webkit-filter","blur(5px)").css("ms-filter","blur(5px)").css("moz-filter","blur(5px)");
    });
    if(exclElem)
      $(exclElem).css("filter","none");//.css("webkit-filter","none").css("ms-filter","none").css("moz-filter","none");
    if(inclElem)
      $(inclElem).css("filter","blur(5px)");//.css("webkit-filter","blur(5px)").css("ms-filter","blur(5px)").css("moz-filter","blur(5px)");
  } else {
    $("div.div-cover").hide();
    $.each(arrToMod,function(idx,val) {
      $(val).css("filter","none");//.css("webkit-filter","none").css("ms-filter","none").css("moz-filter","none");
    });
    if(inclElem)
      $(inclElem).css("filter","none");//.css("webkit-filter","none").css("ms-filter","none").css("moz-filter","none");
  }
  if(animState)
    startLoadingAnim();
  else
    stopLoadingAnim();
}

//create json file manual
function inputToJsonObjectFormat(inputElemArr,childObj) {
  var userJson = "{";
  inputElemArr.each(function() {
    if($(this).is(":checkbox")) {
      if($(this).prop("checked") === "true")
        userJson = userJson + $(this).attr("id") + ': "true",';
      else
        userJson = userJson + $(this).attr("id") + ': "false",';
    } else {
      userJson = userJson + $(this).attr("id") + ': "' + $(this).val() + '",';
    }
  });
  $.each(childObj,function(idx,val) {    
    userJson = userJson + val.name + ": {";
    $.each(val.fields,function(idxField,valField) {
      userJson = userJson + valField.name + ': "' + valField.value + '",';
    }); 
    userJson = userJson.replace(/,$/,"}") + ",";
  });
  return userJson.replace(/,$/,"}");
}

//show message
function showMessage(message) {  
  var modalMessage = $("div#mdl-message");
  modalMessage.find(".modal-body>span").text(message);
  modalMessage.modal("show"); 
}
  
//check requested current session(active/inactive) before execution
function checkCurrSessAndExec(params,callback) {    
  $.get("../../apps/auth/currentsession",function(data,status) {
    if(status === "success") {
      if(data === $("div#mdl-profile input#prof-session").val()) {
        callback(params);
      } else {
        showMessage("Your session has been expired");
        window.location.reload();
      }
    } else {
      alert("Get current session unsuccessfully: status = " + status);
    }
  }).fail(function(d) {
    alert("Failed to get current session: ");
  }).error(function(d) {
    alert("Error getting current session: ");
  });
}

$(document).ready(function() {

  //controlling user visibility
  if($("#uid").text().length > 0)
    $("#user-menu").show();
  else
    $("#user-menu").hide();
  
  //
  $("li#profile-btn").click(function() {
    showCover(true);
    $("div#mdl-profile").modal("show");
  });
  
  //
  $("div#mdl-profile").on("hidden.bs.modal",function() {
    showCover(false);
  });
});
  
/****** ANGULAR SECTION ******/

//Setup angular modul
var kciApp = angular.module("kciApp",[]);