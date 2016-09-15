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
var allDataCode = "ALL";
var company = lob = region = branch = dept = allDataCode;
var prevcompany = prevlob = prevregion = prevbranch = prevdept = allDataCode;
var dataperiode, datacompany, datadept, dataarea, databranch;

//global function

//cover and loading animation
function showCover(coverState,animState,exclElem) {  
  var arrToMod = ["div#side-menu","div#filter",".title-dashboard",".content-dashboard"];
  if(coverState) {
    $("div.div-cover").show();
    $.each(arrToMod,function(idx,val) {
      $(val).css("filter","blur(5px)").css("-webkit-filter","blur(5px)");
    });
    if(exclElem)
      $(exclElem).css("filter","none").css("-webkit-filter","none");
  } else {
    $("div.div-cover").hide();
    $.each(arrToMod,function(idx,val) {
      $(val).css("filter","none").css("-webkit-filter","none");
    });
  }
  if(animState)
    startLoadingAnim();
  else
    stopLoadingAnim();
}
  
// process on zoom in
function zoomInObj(elem) {
  elem.css("position","fixed")
      .css("left","100px")
      .css("top","102px")
      .width("1140px")
      .css("height","460")
      .css("border","#11aa77 solid 2px")
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


$(document).ready(function() {
  
  //$("div.row>div>div:not(:has(div#gauge))").hide();
  
  // show user menu on page loading
  $("#side-menu>div.user-menu").each(function() {
    $(this).show();
  });
  
  // show home/welcome link
  $("div#user-menu>div.dropdown>ul>li#home-btn").show();
  
  // zoom section
  $("div:not(:has(div#map))>div.title-dashboard>img:first-child").click(function() {
    var elem = $(this).parent("div").siblings("div");
    var currId = elem.attr("id");
    zoomInObj(elem);
    if(currId === "graphic") {
      d3.selectAll('#chart-trend>svg>*').remove();
      drawChart();
    } else if(currId === "gauge") {
      elem.children("img#img-fifgroup").css("transform","scale(2) translate(50.5px,50px)");
      elem.children("img#img-area").css("transform","scale(2) translate(148.5px,32.5px)");
      elem.children("img#img-branch").css("transform","scale(2) translate(235px,33px)");
      elem.children("svg").css("transform","scale(2) translate(154px,68px)");
    }
  });
  
  //
  $("div:not(:has(div#map)):has(div.content-dashboard)").on("click","span.close-btn",function() {
    var elem = $(this).siblings("div.content-dashboard");
    var currId = elem.attr("id");
    zoomOutObj(elem);
    if(currId === "graphic") {
      d3.selectAll('#chart-trend>svg>*').remove();
      drawChart();
    } else if(currId === "gauge") {
      elem.children("img").css("transform","");
      elem.children("svg").css("transform","translate(13px,18px)");
    }
  });
  
  // show unavailable message
  $("div#side-menu>div:not(#report), div.title-dashboard>img:last-child").click(function() {
      $("div#mdl-dashboard div.modal-body").empty();
      $("div#mdl-dashboard div.modal-body").append("<span>Currently unavailable..</span>");
      $("div#mdl-dashboard").modal("show");
  });
});
