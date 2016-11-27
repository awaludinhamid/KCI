/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  
  var datascope = [{code:"fp", name:"Function Parameter"},
                   {code:"ap", name:"Area Parameter"},
                   {code:"mp", name:"Monthly Priority"}
                 ];

$(document).ready(function() {
  //
  $("div#side-menu>div#report").click(function() {
    if($("span#hasRoleReport").text() === "true") {
      showCover(true,true);
      window["prevrepscope"] = datascope[0].code;
      window["prevrepperiode"] = dataperiode[0].code;
      window["prevreparea"] = dataarea[0].code;
      window["prevrepcompany"] = datacompany[0].code;
      window["prevrepdept"] = datadept[0].code;

      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr").each(function() {
        var tdObj = $(this).children("td:nth-child(2)");
        var tdId = tdObj.attr("id");
        var arrName = tdId.replace("rep","data");
        if(tdObj.find("div>ul>li").length === 0) {
          $.each(window[arrName],function(idx,dp) {
            tdObj.find("div>ul").append(
              "<li data-code='"+dp.code+"'>"+dp.name+"</li>"
            );        
          });
        }
        tdObj.find("div>span").html(
          window[arrName][0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
        );
      });
      $("div#mdl-kci-report").modal("show");    
      showCover(true,false);
    } else {
      showMessage("You have no Report role ..!");
    }
  });
  
  //
  $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td>div>ul").on("click","li",function() {
    var parentsTdId = $(this).parents("td").attr("id");
    var thisCode = $(this).data("code");
    if(thisCode !== window["prev"+parentsTdId]) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#"+parentsTdId+">div>span").html(
        $(this).text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      if(parentsTdId === "repscope") {
        if($(this).data("code") === "ap") {
          $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr:has(td#reparea)").show();
        } else {
          $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr:has(td#reparea)").hide();
        }
      }
      window["prev"+parentsTdId] = thisCode;
    }
  });
  
  //
  /*$("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-periode>div>ul").on("click","li",function() {
    var thisCode = $(this).data("code");
    if(thisCode !== prevrepperiode) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-periode>div>span").html(
        $(this).text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      prevrepperiode = thisCode;
    }
  });
  
  //
  $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-area>div>ul").on("click","li",function() {
    var thisCode = $(this).data("code");
    if(thisCode !== prevreparea) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-area>div>span").html(
        $(this).text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      prevreparea = thisCode;
    }
  });
  
  //
  $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-company>div>ul").on("click","li",function() {
    var thisCode = $(this).data("code");
    if(thisCode !== prevrepcompany) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-company>div>span").html(
        $(this).text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      prevrepcompany = thisCode;
    }
  });
  
  //
  $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-dept>div>ul").on("click","li",function() {
    var thisCode = $(this).data("code");
    if(thisCode !== prevrepdept) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-dept>div>span").html(
        $(this).text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      prevrepdept = thisCode;
    }
  });*/
  
  //
  $("div#mdl-kci-report>div>div>div.modal-footer>button#btn-print").click(function() {
    /*$.get("../../apps/data/reportperiodikparam",{
      repPeriode: repPeriode,
      scopeOfReport: scopeOfReport,
      repCoyId: repCoyId,
      repDeptId: repDeptId},
      function(data) {
        if(data === "ok") {
          alert("ok");
          //window.location.replace("../../apps/data/downloadreport");
        }
    });*/
    window.location.replace("../../apps/data/downloadreport?"+
            "repPeriode="+window["prevrepperiode"]+"&"+
            "scopeOfReport="+window["prevrepscope"]+"&"+
            "repCoyId="+window["prevrepcompany"]+"&"+
            "repAreaId="+window["prevreparea"]+"&"+
            "repDeptId="+window["prevrepdept"]); 
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
  $("div#mdl-kci-report").on("hidden.bs.modal",function() {
    showCover(false,false);
  });
});