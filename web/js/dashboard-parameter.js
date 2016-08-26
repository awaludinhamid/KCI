/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
  
  $("div#parameter>ul").on("click","li>div",function() {
    $("div#mdl-dashboard").modal("show");
  });
  
  $.get("../../apps/data/parameter/all",{},
    function(data,status) {
    if(status === "success") {
      $.each(data, function(index,param) {
        var p1 = param.measurementP1;
        var p2 = param.measurementP2;
        var p3 = param.measurementP3;
        var total = p1 + p2 + p3;
        var persenP1 = p1/total*100;
        var persenP2 = p2/total*100;
        var lengthP1 = p1.toString().length * 2;
        var lengthP2 = p2.toString().length * 2;
        if (persenP1 > 0 && persenP1 < lengthP1) persenP1 = lengthP1;
        if (persenP2 > 0 && persenP2 < lengthP2) persenP2 = lengthP2;
        $("#parameter>ul").append(
          "<li style='margin-top: 10px'>"+param.parameterDesc+"</li>"+
          "<li>"+
            (total !== 0 ?
              "<div style='background-color: rgba(255,80,80,0.8); float: left; width: "+persenP1+"%; text-align: center'><span>"+(p1 === 0 ? "" : p1)+"</span></div>"+
              "<div style='background-color: rgba(220,220,40,0.8); float: left; width: "+persenP2+"%; text-align: center'><span>"+(p2 === 0 ? "" : p2)+"</span></div>"+
              "<div style='background-color: rgba(80,255,180,0.8); text-align: center'><span>"+(p3 === 0 ? "" : p3)+"</span></div>" :
              "<div style='background-color: #fafafa; color: red; width: 100%'>No available value !</div>")+
          "</li>"
        );
      });
    } else {
      alert("Generate periode unsuccessfully: status = " + status);
    }
  });
});