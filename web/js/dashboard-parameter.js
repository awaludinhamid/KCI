/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
  
  $("div#parameter>ul").on("click","li>div",function() {
    $("div#mdl-dashboard").modal("show");
  });
  
  // change filter
  $("div#filter>div>ul").on("click","li",function() { 
    if(window["prev"+$(this).parent("ul").data("var")] !== $(this).data("code")) {
      loadParams();
    }
  });
  
  //
  $(".row>div>div:has(div#parameter)>div.title-dashboard>div.dropdown>ul>li").click(function() {
    loadParams();
  });
  
  //
  loadParams();
  
  function loadParams() {
    showCover(true,true);
    $.get("../../apps/data/parameters",{
          periode: periode,
          coyId: company,
          areaId: region,
          officeId: branch,
          bussUnit: lob,
          deptId: dept},
      function(data,status) {
      if(status === "success") {
        $("#parameter>ul>*").remove();
        $.each(data, function(index,param) {
          var p1 = param.measurementP1;
          var p2 = param.measurementP2;
          var p3 = param.measurementP3;
          var total = param.countTotal;
          var persenP1 = param.persenMeasurementP1;
          var persenP2 = param.persenMeasurementP2;
          var persenP3 = param.persenMeasurementP3;
          var lengthP1 = p1.toString().length * 2;
          var lengthP2 = p2.toString().length * 2;
          var lengthP3 = p3.toString().length * 2;
          if (persenP1 > 0 && persenP1 < lengthP1) persenP1 = lengthP1;
          if (persenP2 > 0 && persenP2 < lengthP2) persenP2 = lengthP2;
          if (persenP3 > 0 && persenP3 < lengthP3) {
            persenP3 = lengthP3;
            if(persenP1 > persenP2)
              persenP1 = persenP1 - persenP3;
            else
              persenP2 = persenP2 - persenP3;
          }
          $("#parameter>ul").append(
            "<li><span>"+param.parameterDesc+"</span></li>"+
            "<li>"+
              (total !== 0 ?
                "<div style='background-color: rgba(255,80,80,0.8); float: left; width: "+persenP1+"%; text-align: center'><span>"+(p1 === 0 ? "" : p1)+"</span></div>"+
                "<div style='background-color: rgba(220,220,40,0.8); float: left; width: "+persenP2+"%; text-align: center'><span>"+(p2 === 0 ? "" : p2)+"</span></div>"+
                "<div style='background-color: rgba(80,255,180,0.8); text-align: center'><span>"+(p3 === 0 ? "" : p3)+"</span></div>" :
                "<div style='background-color: #fafafa; color: red; width: 100%'>No available value !</div>")+
            "</li>"
          );
        });
        showCover(false,false);
      } else {
        alert("Generate parameter unsuccessfully: status = " + status);
        showCover(false,false);
      }
    });
  }
});