/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
  
  // variable
  var kciRangeDegree = 240;
  var kciDegreeStart = 60;
  var kciUnitValue = 0.01;
  var percentValue = 100;
  var speedoValIndPosX = 92,
      speedoValIndPosY = 138;
  var transformOriginPoint = "16.1% 41%";
  var intervalTimePerVal = 5000; //in millisecond
  var kciGradeValueMin = 1,
      kciGradeValueMax = 3;
  var kciGradeValuePoint1 = getValuePoint(1),
      kciGradeValuePoint2 = getValuePoint(2),
      kciGradeValuePoint3 = getValuePoint(3);
  var arcArrColor = {
    "HIJAU":["rgba(180,255,200,0.8)","rgba(0,255,0,0.4)"],
    "KUNING":["rgba(255,200,180,0.8)","rgba(255,255,0,0.4)"],
    "MERAH":["rgba(255,180,180,0.8)","rgba(255,0,0,0.4)"]
  };
  var kciTextColor = "green";
  
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
    if(isZoom)
      $("div.div-cover").css("z-index",12);
    showCover(true,true);
    if(thisId === "fifgroup")
      $.get("../../apps/data/kciareas",{
            periode: periode,
            coyId: company,
            bussUnit: lob,
            deptId: dept},
        function(data,status) {
        if(status === "success") {
          createGaugeDetail(thisElem,data);
        } else {
          alert("Generate gauge kci area value unsuccessfully: status = " + status);
          showCover(false,false);
        }
      });
    else if(thisId === "area")
      $.get("../../apps/data/kcibranches",{
            periode: periode,
            coyId: company,
            areaId: region,
            bussUnit: lob,
            deptId: dept},
        function(data,status) {
        if(status === "success") {
          createGaugeDetail(thisElem,data);
        } else {
          alert("Generate gauge kci branch value unsuccessfully: status = " + status);
          showCover(false,false);
        }
      });
    else if(thisId === "branch")
      $.get("../../apps/data/kcidepts",{
            periode: periode,
            coyId: company,
            areaId: region,
            officeId: branch,
            bussUnit: lob,
            deptId: dept},
        function(data,status) {
        if(status === "success") {
          createGaugeDetail(thisElem,data);
        } else {
          alert("Generate gauge kci dept value unsuccessfully: status = " + status);
          showCover(false,false);
        }
      });
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
        execCreateSpeedo(data.kciFifgroup,data.kciArea,data.kciBranch);
      } else {
        alert("Generate gauge value unsuccessfully: status = " + status);
        showCover(false,true);
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
                  "div#gauge>svg>g>g#fifgroup>g#needle",kciFifgroup.batasBawah,kciFifgroup.batasAtas);  
    //gauge area
    createSpeedo(svgGrp,"area","translate(225 19) scale(0.84)",kciArea.nilaiKci,"div#gauge>svg>g>g#area>text#kci-text",
                  "div#gauge>svg>g>g#area>g#needle",kciArea.batasBawah,kciArea.batasAtas);
    //gauge branch
    createSpeedo(svgGrp,"branch","translate(415 33) scale(0.68)",kciBranch.nilaiKci,"div#gauge>svg>g>g#branch>text#kci-text",
                  "div#gauge>svg>g>g#branch>g#needle",kciBranch.batasBawah,kciBranch.batasAtas);
  }
  
  function getGradeRatio(gradeValue) {
    return (gradeValue-kciGradeValueMin)/(kciGradeValueMax-kciGradeValueMin);
  }
  
  function createSpeedo(svgGrp,svgId,svgTransform,kciValue,elemKciText,elemNeedle,kciBatasBawah,kciBatasAtas) {
    var svgScopeGrp = svgGrp.append("svg:g")
            .attr("id",svgId)
            .attr("transform",svgTransform);
    createSpeedoValue(svgScopeGrp);  
    createNeedle(svgScopeGrp.append("svg:g"));  
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
  
  function createNeedle(svgNeedleGrp) {
    svgNeedleGrp
            .style("transform-origin",transformOriginPoint)
            .attr("id","needle");
    $.each(["url(#blur)",""], function(idx,val) {
      svgNeedleGrp.append("svg:path")
            .attr("d","M 92,114 L 88,77 H 96 Z")
            .attr("stroke","#aaaaaa")
            .attr("stroke-width","2")
            .attr("fill","white")
            .attr("filter",val);    
    });    
  }
  
  function createPointValue(svgPointGroup) {
    svgPointGroup
            .style("transform","translate(-11px)");
    $.each([kciGradeValueMin,kciGradeValuePoint1,kciGradeValuePoint2,kciGradeValuePoint3,kciGradeValueMax], function(idx,val) {
      var svgEachPointGrp = svgPointGroup.append("svg:g");
      var rotateDegree = kciDegreeStart+(getGradeRatio(val)*kciRangeDegree);
      svgEachPointGrp
            .style("transform-origin",transformOriginPoint)
            .style("transform","rotate("+rotateDegree+"deg)");
      svgEachPointGrp
            .append("svg:text")
            .attr("x",speedoValIndPosX)
            .attr("y",speedoValIndPosY)
            .style("transform-origin",speedoValIndPosX+"px "+speedoValIndPosY+"px")
            .style("transform","rotate(-"+rotateDegree+"deg)")
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
        needle.attr("transform","rotate("+(kciDegreeStart + ((kciCnt-kciCntInit)*degreePerValue))+")");
        kciCnt++;
      }
      else {
        clearInterval(counter);
      }
    }, intervalTimePerVal/kciPercent);
  };
  
  function createGaugeDetail(thisElem,data) {
    var dataLength = data.length;
    var halfLength = Math.floor(dataLength/2);
    var tableBody = $("div#gauge-detail>div>table>tbody");
    tableBody.find("*").remove();
    for(var idx = 0; idx < halfLength; idx++) {
      var tRow = $("<tr></tr>");
      $("<td>"+data[idx].name+"</td>").appendTo(tRow);
      $("<td>"+data[idx].assignValue.toFixed(2)+"</td>").css("color",data[idx].descr).appendTo(tRow);
      $("<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>").appendTo(tRow);
      $("<td>"+data[idx+halfLength].name+"</td>").appendTo(tRow);
      $("<td>"+data[idx+halfLength].assignValue.toFixed(2)+"</td>").css("color",data[idx+halfLength].descr).appendTo(tRow);
      tRow.appendTo(tableBody);
    }
    if(dataLength % 2 !== 0) {
      var addTRow = $("<tr></tr>");
      $("<td>"+data[dataLength-1].name+"</td>").appendTo(addTRow);
      $("<td>"+data[dataLength-1].assignValue.toFixed(2)+"</td>").css("color",data[dataLength-1].descr).appendTo(addTRow);
      addTRow.appendTo(tableBody);
    }
    $("<span>"+thisElem.children("text#kci-text").text()+"</span>").css("color",kciTextColor).appendTo($("div#gauge-detail>div>span").html(
        thisElem.attr("id").toUpperCase()+"&nbsp;VALUES&nbsp;&nbsp;&nbsp;"            
    ));   
    $("div#gauge-detail").show();
    showCover(true,false);
  }
  
  function getValuePoint(pointSeq) {
    return kciGradeValueMin + (pointSeq/4 * (kciGradeValueMax - kciGradeValueMin));
  }
});