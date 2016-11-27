/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
  
  // variable
  var kciRangeDegree = 240;
  var kciDegreeStart = 60;
  var percentValue = 100;
  var intervalTimePerVal = 5000; //in millisecond
  var arcArrColor = {
    "HIJAU":["rgba(180,255,200,0.8)","rgba(0,255,0,0.4)"],
    "KUNING":["rgba(255,200,180,0.8)","rgba(255,255,0,0.4)"],
    "MERAH":["rgba(255,180,180,0.8)","rgba(255,0,0,0.4)"]
  };
  var kciTextColor = "green";
  var currRotateArr = {"needle-fifgroup":0,"needle-area":0,"needle-branch":0};
  
  // change filter
  $("div#filter>div>ul").on("click","li",function() { 
    if(window["prev"+$(this).parent("ul").data("var")] !== $(this).data("code")) {
      execSpeedo();
    }
  });
  
  //
  $(".row>div>div:has(div#gauge)>div.title-dashboard>div.dropdown>ul>li").click(function() {
    execSpeedo();
  });
  
  //
  $("div#gauge>svg").on("click","g>g",function() {
    var thisElem = $(this);
    var thisId = this.id;
    var gaugeInfoArr = {
        "fifgroup":{"url":"../../apps/data/kciareas","param":{periode: periode,coyId: company,bussUnit: lob,deptId: dept}},
        "area":{"url":"../../apps/data/kcibranches","param":{periode: periode,coyId: company,bussUnit: lob,areaId: region,deptId: dept}},
        "branch":{"url":"../../apps/data/kcidepts","param":{periode: periode,coyId: company,bussUnit: lob,areaId: region,officeId: branch,deptId: dept}}
    };
    if(isZoom)
      $("div.div-cover").css("z-index",12);
    createGaugeDetail(thisElem,gaugeInfoArr[thisId].url,gaugeInfoArr[thisId].param);
  });
  
  // zoom section
  $("div:has(div#gauge)>div.title-dashboard>img:first-child").click(function() {
    var elem = $("div#gauge");
    zoomInObj(elem);
    elem.children("img#img-fifgroup").css("transform","scale(2) translate(50.5px,50px)");
    elem.children("img#img-area").css("transform","scale(2) translate(148.5px,32.5px)");
    elem.children("img#img-branch").css("transform","scale(2) translate(236px,33px)");
    elem.children("img#needle-fifgroup").css("transform","scale(2) translate(42px,30px) rotate("+currRotateArr["needle-fifgroup"]+"deg)");
    elem.children("img#needle-area").css("transform","scale(2) translate(149px,31px) rotate("+currRotateArr["needle-area"]+"deg)");
    elem.children("img#needle-branch").css("transform","scale(2) translate(235px,32px) rotate("+currRotateArr["needle-branch"]+"deg)");
    elem.children("svg").css("transform","scale(2) translate(154px,68px)");
  });
  
  //
  $("div:has(div#gauge):has(div.content-dashboard)").on("click","span.close-btn",function() {
    var elem = $("div#gauge");
    zoomOutObj(elem);
    elem.children("img").css("transform","");
    elem.children("img#needle-fifgroup").css("transform","rotate("+currRotateArr["needle-fifgroup"]+"deg)");
    elem.children("img#needle-area").css("transform","rotate("+currRotateArr["needle-area"]+"deg)");
    elem.children("img#needle-branch").css("transform","rotate("+currRotateArr["needle-branch"]+"deg)");
    elem.children("svg").css("transform","translate(13px,18px)");
  });
  
  //
  $("div#gauge-detail>span.close-btn").click(function() {
    if(isZoom) {
      showCover(true,false,"div#gauge");
      $("div.div-cover").css("z-index",5);
    } else {
      showCover(false);
    }
    $("div#gauge-detail").hide();
  });
   
  //
  execSpeedo();
  function execSpeedo() {
    showCover(true,true);
    $.get("../../apps/data/kcigauge",{
          periode: periode,
          coyId: company,
          bussUnit: lob,
          areaId: region,
          officeId: branch,
          deptId: dept},
      function(data,status) {
      if(status === "success") {
        d3.selectAll("div#gauge>svg>*").remove();
        showCover(false,false);
        if(data.kciFifgroup.gradeId)
          execCreateSpeedo(data.kciFifgroup,data.kciArea,data.kciBranch);
      } else {
        alert("Generate gauge value unsuccessfully: status = " + status);
        showCover(false,false);
      }
    });
  }
  
  function execCreateSpeedo(kciFifgroup,kciArea,kciBranch) {
    //  
    var svg = d3.select("div#gauge>svg");
    var svgGrp = svg.append("svg:g");
    var blurFilter = svgGrp.append("svg:defs")
            .append("svg:filter")
            .attr("id","blur");
    blurFilter
            .append("feGaussianBlur")
            .attr("in","SourceGraphic")
            .attr("stdDeviation","2");
    $.each({"54":"FIFGROUP","282":"AREA","447":"BRANCH"}, function(key, val) {
      svgGrp.append("svg:text")
            .attr("x",key)
            .attr("y","205")
            .attr("fill","#22aa99")
            .style("font-weight","bold")
            .style("font-size","16px")
            .text(val);
    });
    //gauge fifgroup  
    createSpeedo(svgGrp,"fifgroup","",kciFifgroup.nilaiKci,"div#gauge>svg>g>g#fifgroup>text#kci-text",
                  "div#gauge>img#needle-fifgroup",kciFifgroup.batasBawah,kciFifgroup.batasAtas);  
    //gauge area
    createSpeedo(svgGrp,"area","translate(225 19) scale(0.84)",kciArea.nilaiKci,"div#gauge>svg>g>g#area>text#kci-text",
                  "div#gauge>img#needle-area",kciArea.batasBawah,kciArea.batasAtas);
    //gauge branch
    createSpeedo(svgGrp,"branch","translate(415 33) scale(0.68)",kciBranch.nilaiKci,"div#gauge>svg>g>g#branch>text#kci-text",
                  "div#gauge>img#needle-branch",kciBranch.batasBawah,kciBranch.batasAtas);
  }
  
  function getGradeRatio(gradeValue) {
    return (gradeValue-kciGradeValueMin)/(kciGradeValueMax-kciGradeValueMin);
  }
  
  function createSpeedo(svgGrp,svgId,svgTransform,kciValue,elemKciText,elemNeedle,kciBatasBawah,kciBatasAtas) {
    var svgScopeGrp = svgGrp.append("svg:g")
            .attr("id",svgId)
            .attr("transform",svgTransform);
    createSpeedoValue(svgScopeGrp);  
    //createNeedle(svgScopeGrp.append("svg:g"));  
    createPointValue(svgScopeGrp.append("svg:g"));  
    roundingNeedle(svgScopeGrp,kciValue,elemKciText,elemNeedle,kciBatasBawah,kciBatasAtas);    
  }
  
  function createSpeedoValue(svgScopeGrp) {
    svgScopeGrp.append("svg:text")
            .attr("id","kci-text")
            .attr("x","77")
            .attr("y","143")
            .attr("fill","#22aa99")
            .style("font-weight","bold")
            .style("font-size","16px");    
  }
  
  function createPointValue(svgPointGroup) {
    var translatePointArr = [{x:"35",y:"115"},
                              {x:"35",y:"65"},
                              {x:"80",y:"35"},
                              {x:"125",y:"65"},
                              {x:"125",y:"115"}];
    svgPointGroup
            .style("transform","translate(0px,0px)");
    $.each([kciGradeValueMin,kciGradeValuePoint1,kciGradeValuePoint2,kciGradeValuePoint3,kciGradeValueMax], function(idx,val) {
      svgPointGroup
            .append("svg:text")
            .attr("x",translatePointArr[idx].x)
            .attr("y",translatePointArr[idx].y)
            .text(val.toFixed(1));
    });    
  }
  
  function roundingNeedle(svgScopeGrp,kciValue,elemKciText,elemNeedle,kciBatasBawah,kciBatasAtas) { 
    var kciGrade1Max = kciBatasBawah - kciUnitValue;
    var kciGrade2Min = kciBatasBawah;
    var kciGrade2Max = kciBatasAtas;
    var kciGrade3Min = kciBatasAtas + kciUnitValue;
    var arc = d3.svg.arc()
            .innerRadius(66)
            .outerRadius(69)
            .startAngle((kciDegreeStart + (getGradeRatio(kciGradeValueMin)) * kciRangeDegree) * (Math.PI/180)) //converting from degs to radians
            .endAngle((kciDegreeStart + (getGradeRatio(kciGrade1Max)) * kciRangeDegree) * (Math.PI/180));  
    var svgArcGrp = svgScopeGrp.append("svg:g")
            .attr("transform","translate(91 85) rotate(180)");
    var innerArc = svgArcGrp.append("svg:path")
            .attr("d", arc)
            .attr("fill",arcArrColor.HIJAU[0]);  
    arc
            .innerRadius(63)
            .outerRadius(72);
    var outerArc = svgArcGrp.append("svg:path")
            .attr("d", arc)
            .attr("fill",arcArrColor.HIJAU[1]);
    var kciCntInit = kciGradeValueMin * percentValue;
    var kciCnt = kciCntInit;
    var kciPercent = kciValue * percentValue;
    var degreePerValue = kciRangeDegree / ((kciGradeValueMax - kciGradeValueMin) * percentValue);
    var kciGrade2Percent = Math.round(kciGrade2Min * percentValue),
        kciGrade3Percent = Math.round(kciGrade3Min * percentValue);
    var kciText = $(elemKciText);
    var needle = $(elemNeedle);
    var thisId = needle.attr("id");
    var counter = setInterval(function() {
      kciText.text((kciCnt/percentValue).toFixed(2));
      if (kciCnt < kciPercent) {
        if(kciCnt === kciGrade2Percent) {
          arc 
                .startAngle((kciDegreeStart + (getGradeRatio(kciGrade2Min)) * kciRangeDegree) * (Math.PI/180)) //converting from degs to radians
                .endAngle((kciDegreeStart + (getGradeRatio(kciGrade2Max)) * kciRangeDegree) * (Math.PI/180));
          outerArc
                .attr("d", arc)
                .attr("fill",arcArrColor.KUNING[1]);
          arc
                .innerRadius(66)
                .outerRadius(69);
          innerArc
                .attr("d", arc)
                .attr("fill",arcArrColor.KUNING[0]);
          kciTextColor = "yellow";
        } else if(kciCnt === kciGrade3Percent) {
          arc 
                .startAngle((kciDegreeStart + (getGradeRatio(kciGrade3Min)) * kciRangeDegree) * (Math.PI/180)) //converting from degs to radians
                .endAngle((kciDegreeStart + (getGradeRatio(kciGradeValueMax)) * kciRangeDegree) * (Math.PI/180));
          innerArc
                .attr("d", arc)
                .attr("fill",arcArrColor.MERAH[0]);
          arc
                .innerRadius(63)
                .outerRadius(72);
          outerArc
                .attr("d", arc)
                .attr("fill",arcArrColor.MERAH[1]);
          kciTextColor = "red";
        }
        needle.css("transform","rotate(" + (kciDegreeStart + ((kciCnt-kciCntInit)*degreePerValue)) + "deg)");
        kciCnt++;
      }
      else {
        currRotateArr[thisId] = kciDegreeStart + ((kciCnt-kciCntInit)*degreePerValue);
        clearInterval(counter);
      }
    }, intervalTimePerVal/kciPercent);
  };
  
  function createGaugeDetail(thisElem,url,param) {
    var divGaugeDetail = $("div#gauge-detail");
    $("<span>"+thisElem.children("text#kci-text").text()+"</span>").css("color",kciTextColor).appendTo(divGaugeDetail.find("div>span").html(
        thisElem.attr("id").toUpperCase()+"&nbsp;VALUES&nbsp;&nbsp;&nbsp;"            
    )); 
    divGaugeDetail.scope().createGaugeDetail(divGaugeDetail,url,param);
  }
});
  
/****** ANGULAR SECTION ******/

//Setup gauge controller
kciApp.controller("kciGaugeCtrl", function($scope,$http) {
  //load parameter
  $scope.createGaugeDetail = function(divGaugeDetail,url,param){
    showCover(true,true);
    $http.get(url,{params: param}).then(function(response) {
      var data = response.data;
      var dataLength = data.length;
      var halfLength = Math.floor(dataLength/2);
      var dataTemps = []; 
      for(var idx = 0; idx < halfLength; idx++) {     
        var dataTemp = {};
        dataTemp["name"] = data[idx].name;
        dataTemp["assignValue"] = data[idx].assignValue.toFixed(2);
        dataTemp["color"] = data[idx].descr;
        dataTemp["nameEven"] = data[idx+halfLength].name;
        dataTemp["assignValueEven"] = data[idx+halfLength].assignValue.toFixed(2);
        dataTemp["colorEven"] = data[idx+halfLength].descr;   
        dataTemps[idx] = dataTemp;
      }
      if(dataLength % 2 !== 0) {    
        var dataTemp = {};
        dataTemp["name"] = data[dataLength-1].name;
        dataTemp["assignValue"] = data[dataLength-1].assignValue.toFixed(2);
        dataTemp["color"] = data[dataLength-1].descr;
        dataTemp["nameEven"] = "";
        dataTemp["assignValueEven"] = "";
        dataTemp["colorEven"] = "initial";   
        dataTemps[idx] = dataTemp;        
      }
      $scope.kciDetailList = dataTemps;  
      divGaugeDetail.show();
      showCover(true,false);
    }, function(response) {
      alert("Something wrong in the server");
      showCover(false,false);
    });
  };
});