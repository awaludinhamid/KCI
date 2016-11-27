/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// global variable
var isZoom = false;
var currDate = new Date();
var currMonth = currDate.getMonth() + "";
var periode = currDate.getFullYear()+"00".substr(0,2-currMonth.length)+currMonth;
var prevperiode = periode;
var company = lob = region = branch = dept = allDataCode;
var prevcompany = prevlob = prevregion = prevbranch = prevdept = allDataCode;
var dataperiode, datacompany, datadept, dataarea, databranch;
var kciUnitValue = 0.01;
var kciGradeValueMin = 1,
    kciGradeValueMax = 3;
var kciGradeValuePoint1 = getValuePoint(1),
    kciGradeValuePoint2 = getValuePoint(2),
    kciGradeValuePoint3 = getValuePoint(3);

//global function
  
// process on zoom in
function zoomInObj(elem) {
  elem.css("position","fixed")
      .css("left","100px")
      .css("top","102px")
      .width("1140px")
      .css("height","460")
      .css("border","#badafa solid 2px")
      .css("opacity","0.7")
      .css("z-index","10");
  var closeBtnParent = elem.parent("div");
  closeBtnParent.append(
    "<span class='glyphicon glyphicon-remove-circle close-btn' title='Close'></span>"
  );
  closeBtnParent.children("span.close-btn").css("position","fixed")
                .css("top","77px")
                .css("left","1247px")
                .css("transform","scale(2)");
  $("body").css("overflow","hidden");
  isZoom = true;  
  showCover(true,false,"div#"+elem.attr("id")); 
}

// process on zoom out
function zoomOutObj(elem) {
  elem.css("position","static")
      .css("width","570px")
      .css("height","230px")
      .css("border","none")
      .css("opacity","1")
      .css("z-index","0");
  elem.parent("div").children("span.close-btn").remove();
  $("body").css("overflow","auto");
  isZoom = false;   
  showCover(false); 
}
  
function getValuePoint(pointSeq) {
  return kciGradeValueMin + (pointSeq/4 * (kciGradeValueMax - kciGradeValueMin));
}


$(document).ready(function() {
    
  //
  $(".modal:not(#mdl-cpts-org)").on("hidden.bs.modal",function() {
    showCover(false);
  });
  
});
