/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  
//
var dataGrade;
  
//
function execGraphic() {
  showCover(true,true);
  $.get("../../apps/data/kcigraphic",{
        periode: periode,
        coyId: company,
        bussUnit: lob,
        areaId: region,
        officeId: branch,
        deptId: dept},
    function(datagraphic,status) {
    if(status === "success") {
      if(isZoom) {
        showCover(true,false,"div#graphic");
        //$("div.div-cover").css("z-index",5);
      }
      else {
        showCover(false,false);
      }
      drawChart(datagraphic);
    } else {
      alert("Generate kci graphic value unsuccessfully: status = " + status);
      showCover(false,false);
    }
  });    
}

function drawChart(dataGraphic) {
  //
  d3.selectAll("div#chart-trend>svg>*").remove();  
  $(".nvtooltip").remove();
  /*These lines are all chart setup.  Pick and choose which chart features you want to utilize. */
  nv.addGraph(function() {
    var monthArr = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
    var chart = nv.models.lineChart()
                  .margin({left: 70, bottom: 70})  //Adjust chart margins to give the x-axis some breathing room.
                  .useInteractiveGuideline(true)  //We want nice looking tooltips and a guideline!
                  .duration(4000)  //how fast do you want the lines to transition?
                  .showLegend(true)       //Show the legend, allowing users to turn on/off line series.
                  .showYAxis(true)        //Show the y-axis
                  .showXAxis(true)        //Show the x-axis
                  .interpolate("linear")  //step, basis, linear
    ;
    //custom tooltip
    chart.interactiveLayer.tooltip.contentGenerator(function(d) {
      var html = "<b>"+d.value+"</b>" +
                  "<table><tbody>";
          var idx = 0;
          d.series.forEach(function(elem){
            if(!(idx === 0 || idx === 1))
              html += "<tr>" +
                        "<td style='color:"+elem.color+"'>&block;</td>" +
                        "<td>"+elem.key+"</td>" +
                        "<td><b>"+(elem.value === 0.99 ? "N/A" : elem.value) +"</b></td>" +
                      "</tr>";
            idx++;
          });
          html += "</tbody></table>";
          return html;
    });
    // legend margin
    chart.legend.margin({bottom: 20});

    /* Done setting the chart up? Time to render it!*/
    var myData = generateDataGraphic(dataGraphic);   //You need data...
    var tmp = myData.map(function(e) {
      return e.values.map(function(d) {
        return d.x;
      });
    });
    var xTickValues = [].concat.apply([],tmp);

    chart.xAxis     //Chart x-axis settings
        .axisLabel("Months")
        .tickFormat(function(d) {
          return monthArr[d];
        })
        .showMaxMin(false)
        .tickValues(xTickValues)
        .tickPadding(20)
        .axisLabelDistance(20)
;

    chart.yAxis     //Chart y-axis settings
        .axisLabel("Grade")
        .tickFormat(d3.format(".2f"))
        .showMaxMin(false)
        .tickValues([kciGradeValueMin,kciGradeValuePoint1,kciGradeValuePoint2,kciGradeValuePoint3,kciGradeValueMax])
    ;
    // background setup
    var svgTmp = d3.select("#chart-trend>svg")    //Select the <svg> element you want to render the chart in.   
        .datum(myData)         //Populate the <svg> element with chart data...
        .call(chart);          //Finally, render the chart!
    var rectBack = $(".nvd3").get(0).firstChild.firstChild;
    var greenObjMinPos = chart.yAxis.scale()(kciGradeValueMin);
    var greenObjMaxPos = chart.yAxis.scale()(dataGrade.batasBawah-kciUnitValue);
    var yellowObjMinPos = chart.yAxis.scale()(dataGrade.batasBawah);
    var yellowObjMaxPos = chart.yAxis.scale()(dataGrade.batasAtas);
    var redObjMinPos = chart.yAxis.scale()(dataGrade.batasAtas-kciUnitValue);
    var redObjMaxPos = chart.yAxis.scale()(kciGradeValueMax);
    var nvGroup = svgTmp.select(".nv-groups");
    var rectArr = [{id:"green-rect",fill:"rgba(100,255,100,0.8)",height:greenObjMinPos-greenObjMaxPos,posY:greenObjMaxPos},
                    {id:"yellow-rect",fill:"rgba(255,255,100,0.8)",height:yellowObjMinPos-yellowObjMaxPos,posY:yellowObjMaxPos},
                    {id:"red-rect",fill:"rgba(255,60,60,0.8)",height:redObjMinPos-redObjMaxPos,posY:redObjMaxPos}
    ];
    $.each(rectArr,function(idx,val) {
      nvGroup.append("svg:rect").attr("id",val.id);
      $("rect#"+val.id)
            .attr("width",rectBack.getAttribute("width"))
            .css("fill",val.fill)
            .attr("height",val.height)
            .attr("y",val.posY)
            .insertBefore(nvGroup);
    });

    //Update the chart when window resizes.
    nv.utils.windowResize(function() { 
      chart.update();
    });
    // disable legend click
    chart.legend.updateState(false);
    //
    return chart;
    
    function generateDataGraphic() {
      //
      var fifgroup = [],
          area = [],
          office = [],
          minValue = [],
          maxValue = [];
      var kciFifgroup = dataGraphic.kciFifgroup,
          kciArea = dataGraphic.kciArea,
          kciBranch = dataGraphic.kciBranch;
      var defKci = 0.99;
      if(typeof periode !== "string")
        periode = periode.toString();
      var lengthPeriode = Number(periode.substring(periode.length-2));
      //Data is represented as an array of {x,y} pairs.
      //
      for(var idx = 0; idx < lengthPeriode; idx++) {
        minValue.push({x: idx, y: kciGradeValueMin});
        maxValue.push({x: idx, y: kciGradeValueMax});
      }
      //      
      var fifgroupLength = kciFifgroup.length;
      if(fifgroupLength < lengthPeriode) {
        for(var idx = 0; idx < lengthPeriode; idx++) {
          fifgroup.push({x: idx, y: defKci});
        }
        for(var idx1 = 0; idx1 < fifgroupLength; idx1++) {
          fifgroup[kciFifgroup[idx1].periode].y = kciFifgroup[idx1].nKci;
        }
      } else {
        $.each(kciFifgroup,function(idxfg,valfg) {
          fifgroup.push({x: valfg.periode, y: valfg.nKci});
        });
      }
      //     
      var areaLength = kciArea.length;
      if(areaLength < lengthPeriode) {
        for(var idx = 0; idx < lengthPeriode; idx++) {
          area.push({x: idx, y: defKci});
        }
        for(var idx1 = 0; idx1 < areaLength; idx1++) {
          area[kciArea[idx1].periode].y = kciArea[idx1].nKci;
        }
      } else {
        $.each(kciArea,function(idxfg,valfg) {
          area.push({x: valfg.periode, y: valfg.nKci});
        });
      }
      //    
      var officeLength = kciBranch.length;
      if(officeLength < lengthPeriode) {
        for(var idx = 0; idx < lengthPeriode; idx++) {
          office.push({x: idx, y: defKci});
        }
        for(var idx1 = 0; idx1 < officeLength; idx1++) {
          office[kciBranch[idx1].periode].y = kciBranch[idx1].nKci;
        }
      } else {
        $.each(kciBranch,function(idxfg,valfg) {
          office.push({x: valfg.periode, y: valfg.nKci});
        });
      }
      //Line chart data should be sent as an array of series objects.
      return [
        {
          values: minValue,
          key: "Min Value",
          color: "green",
          interactive: 0
        },
        {
          values: maxValue,
          key: "Max Value",
          color: "red",
          interactive: 0
        },
        {
          values: fifgroup,      //values - represents the array of {x,y} data points
          key: "FIFGROUP", //key  - the name of the series.
          color: "#2f5fbf",  //color - optional: choose your own line color.
          interactive: 1
        },
        {
          values: area,
          key: "Area",
          color: "#9f1f9f",
          interactive: 1
        },
        {
          values: office,
          key: "Branch",
          color: "#000000",
          interactive: 1
          //area: true      //area - set to true if you want this line to turn into a filled area chart.
        }
      ];
    }
  });
}

$(document).ready(function() {
  //
  showCover(true,true);
  $.get("../../apps/data/grade/GR001",{},
    function(datagrade,status) {
    if(status === "success") {
      dataGrade = datagrade;
      execGraphic();
    } else {
      alert("Generate grade value unsuccessfully: status = " + status);
      showCover(false,false);
    }
  });
  
  // change filter
  $("div#filter>div>ul").on("click","li",function() { 
    if(window["prev"+$(this).parent("ul").data("var")] !== $(this).data("code")) {
      execGraphic();
    }
  });
  
  //
  $(".row>div>div:has(div#chart-trend)>div.title-dashboard>div.dropdown>ul>li").click(function() {
    execGraphic();
  });
  
  // zoom section
  $("div:has(div#graphic)>div.title-dashboard>img:first-child").click(function() {
    zoomInObj($("div#graphic"));
    execGraphic();
  });
  
  //
  $("div:has(div#graphic):has(div.content-dashboard)").on("click","span.close-btn",function() {
    zoomOutObj($("div#graphic"));
    execGraphic();
  });
});