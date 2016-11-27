/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  
  //variable
  var currParamId = "param";
  var currTableName = "";
  var measure1 = "P1";
  var measure2 = "P2";
  
  //
  loadParams();
  
  // change filter
  $("div#filter>div>ul").on("click","li",function() { 
    if(window["prev"+$(this).parent("ul").data("var")] !== $(this).data("code"))
      loadParams();
  });
  
  //
  $("div:has(div#parameter)>div.title-dashboard>div.dropdown>ul>li:first-child").click(function() {
    loadParams();
  });
  
  //
  $("div:has(div#parameter)>div.title-dashboard>div.dropdown>ul>li:nth-child(2)").click(function() {
    window.location.replace("../../apps/data/download/param");
  });
  
  //click parameter
  $("div#parameter>ul").on("click","li>*:not(.no-param-value,#param-p3)",function() {
    var measureArr = {"param-desc":["P1","P2"],"param-p1":["P1","P1"],"param-p2":["P2","P2"]};
    var thisId = this.id;
    measure1 = measureArr[thisId][0];
    measure2 = measureArr[thisId][1];
    if(isZoom) {
      $("div.div-cover").css("z-index",12);
      $("div#parameter-detail").css("z-index",15);
    }
    currParamId = $(this).parent().data("code");
    currTableName = $(this).parent().data("table-name");
    currPageNo = 1;
    $("div#parameter-detail").scope().showTable({tableName: currTableName, paramId: currParamId, measure1: measure1, measure2: measure2});
  });
  
  // zoom section
  $("div:has(div#parameter)>div.title-dashboard>img:first-child").click(function() {
    zoomInObj($("div#parameter"));
  });
  
  //
  $("div:has(div#parameter):has(div.content-dashboard)").on("click","span.close-btn",function() {
    zoomOutObj($("div#parameter"));
  });
  
  //
  $("div#parameter-detail>span.close-btn").click(function() {
    if(isZoom) {
      showCover(true,false,"div#parameter");
      $("div.div-cover").css("z-index",5);
      $("div#parameter-detail").css("z-index",10);
    } else {
      showCover(false);
    }
    $("div#parameter-detail").hide();
  });
  
  //
  $("div#parameter-detail>div#print-file>img").click(function() { 
    showCover(true,true,null,"div#parameter-detail");
    if(isZoom)
      $("div.div-cover").css("z-index",18);
    else
      $("div.div-cover").css("z-index",12);
    window.location.replace("../../apps/data/download/"+this.id);  
    var counter = setInterval(function() {  
      $.get("../../apps/data/downloadstatus",{},function(data,status) {
        if(status === "success") {
          if(data === "DONE") {
            clearInterval(counter);
            if(isZoom)
              $("div.div-cover").css("z-index",10);
            else
              $("div.div-cover").css("z-index",5);
            showCover(true,false,"div#parameter-detail");
          }      
        } else {
          alert("Generate download status unsuccessfully: status = " + status);
          showCover(true,false);
        }      
      });
    }, 2000);  
  });
  
  //
  $("div#kci-table>div#pagination>ul").on("click","li",function() {
    clickPageNo($(this),
      {tableName: currTableName, paramId: currParamId, measure1: measure1, measure2: measure2},
      $("div#parameter-detail").scope().showTable);
  });
  
  function loadParams() {
    $("div#parameter").scope().loadParams();
  }
  
});
  
/****** ANGULAR SECTION ******/

//Setup parameter controller
kciApp.controller("kciParamCtrl", function($scope,$http) {
  //load parameter
  $scope.loadParams = function(){
    showCover(true,true);
    var paramData = {periode: periode,
                      coyId: company,
                      areaId: region,
                      officeId: branch,
                      bussUnit: lob,
                      deptId: dept};
    var isIE = false;
    if((navigator.userAgent.indexOf("MSIE") != -1 ) || (!!document.documentMode == true ))
      isIE = true;
    $http.get("../../apps/data/parameters",{params: paramData}).then(function(response) {
      var dataTemps = [];
      //var ulWidth = Number(($("div#parameter>ul").css("width")).replace("px",""));
      $.each(response.data, function(index,param) {
        var p1 = param.measurementP1 || 0;
        var p2 = param.measurementP2 || 0;
        var p3 = param.measurementP3 || 0;
        var total = param.countTotal || 0;
        var persenP1 = param.persenMeasurementP1 || 0;
        var persenP2 = param.persenMeasurementP2 || 0;
        var persenP3 = param.persenMeasurementP3 || 0;
        if(persenP1 > 0 && persenP1 < 5) persenP1 = 5;
        if(persenP2 > 0 && persenP2 < 5) persenP2 = 5;
        if(persenP3 > 0 && persenP3 < 5) persenP3 = 5;
        var persenTot = persenP1 + persenP2 + persenP3;
        if(persenTot > 100) {
          var persenDev = persenTot - 100;
          var maxPersen = Math.max(persenP1,persenP2,persenP3);
          if(persenP1 === maxPersen)
            persenP1 = persenP1 - persenDev;
          else if(persenP2 === maxPersen)
            persenP2 = persenP2 - persenDev;
          else
            persenP3 = persenP3 - persenDev;
        }
        var persenDevP1P2 = persenP1 + persenP2 - 95;
        if(persenDevP1P2 > 0 && persenP3 > 0) {
          if(persenP1 > persenP2) {
            persenP1 = persenP1 - persenDevP1P2;
          } else {
            persenP2 = persenP2 - persenDevP1P2;
          }
        }
        var dataTemp = {};
        dataTemp["code"] = param.parameterId;
        dataTemp["desc"] = param.parameterDesc;
        dataTemp["tableName"] = param.tableKci;
        dataTemp["p1"] = p1 <= 0 ? "" : p1;
        dataTemp["p2"] = p2 <= 0 ? "" : p2;
        dataTemp["p3"] = p3 <= 0 ? "" : p3;
        dataTemp["persenP1"] = persenP1 + "%";
        dataTemp["persenP2"] = persenP2 + "%";
        dataTemp["classP1"] = isIE ? "div-perc-" + Math.ceil(persenP1/5) : "";
        dataTemp["classP2"] = isIE ? "div-perc-" + Math.ceil(persenP2/5) : "";
        dataTemp["display"] = total === 0 ? "none" : "block";
        dataTemp["displayNoValue"] = total === 0 ? "block" : "none";
        dataTemp["classDisplayNoParamValue"] = total === 0 ? "display-no-param-value" : "hidden-no-param-value";
        dataTemp["classNoParamValue"] = total === 0 ? "no-param-value" : "";
        dataTemps[index] = dataTemp;
      });
      $scope.paramList = dataTemps;
      showCover(false,false);
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
});

//Setup parameter detail controller
kciApp.controller("kciParamDetCtrl", function($scope,$http) {
  //load parameter
  $scope.showTable = function(param){    
    $("div#parameter-detail").hide();
    showCover(true,true);
    $http.get("../../apps/data/tablecontents/"+param.tableName+"/"+currPageNo,
    {params: {
        periode: periode,
        coyId: company,
        areaId: region,
        officeId: branch,
        bussUnit: lob,
        paramId: param.paramId,
        measure1: param.measure1,
        measure2: param.measure2}
    }).then(function(response) {
      var headers = response.data.headers;
      var contents = response.data.contents;
      var contentsTemp = [];
      $.each(contents,function(idx,data) {
        var content = data.stringValue.split("|");
        var row = {};
        $.each(content,function(idx1,data1) {
          row[headers[idx1]] = data1;
        });
        contentsTemp[idx] = row;
      });
      $scope.paramDetailHeaderList = headers;
      $scope.paramDetailContentList = contentsTemp;
      execPaging(response.data.count);
      $("div#parameter-detail").show();
      showCover(true,false);
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
});