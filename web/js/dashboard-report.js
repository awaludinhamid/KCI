/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
  
  // variable
  var prevrepscope = "fp", prevrepperiode, prevreparea, prevrepbranch, prevrepcompany, prevrepdept;
  
  //
  $("div#side-menu>div#report").click(function() {
    prevrepperiode = dataperiode[0].code;
    prevreparea = dataarea[0].code;
    prevrepbranch = databranch[0].code;
    prevrepcompany = datacompany[0].code;
    prevrepdept = datadept[0].code;
    if($("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-periode>div>ul>li").length === 0) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-periode>div>span").html(
        dataperiode[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      $.each(dataperiode,function(idx,dp) {
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-periode>div>ul").append(
          "<li data-code='"+dp.code+"'>"+dp.name+"</li>"
        );        
      });
    }
    if($("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-company>div>ul>li").length === 0) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-company>div>span").html(
        datacompany[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      $.each(datacompany,function(idx,dc) {
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-company>div>ul").append(
          "<li data-code='"+dc.code+"'>"+dc.name+"</li>"
        );        
      });        
    }
    if($("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-dept>div>ul>li").length === 0) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-dept>div>span").html(
        datadept[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      $.each(datadept,function(idx,dd) {
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-dept>div>ul").append(
          "<li data-code='"+dd.code+"'>"+dd.name+"</li>"
        );        
      });        
    }
    if($("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-area>div>ul>li").length === 0) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-area>div>span").html(
        dataarea[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      $.each(dataarea,function(idx,dd) {
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-area>div>ul").append(
          "<li data-code='"+dd.code+"'>"+dd.name+"</li>"
        );        
      });        
    }
    if($("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-branch>div>ul>li").length === 0) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-branch>div>span").html(
        databranch[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      $.each(databranch,function(idx,dd) {
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-branch>div>ul").append(
          "<li data-code='"+dd.code+"'>"+dd.name+"</li>"
        );        
      });        
    }
    $("div#mdl-kci-report").modal("show");    
  });
  
  //
  $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-scope>div>ul>li").click(function() {
    var thisCode = $(this).data("code");
    if(thisCode !== prevrepscope) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-scope>div>span").html(
        $(this).text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      if($(this).data("code") === "ap") {
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr:has(td#report-area)").show();
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr:has(td#report-branch)").hide();
      } else if($(this).data("code") === "bp") {
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr:has(td#report-area)").hide();
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr:has(td#report-branch)").show();
      } else {
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr:has(td#report-area)").hide();
        $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr:has(td#report-branch)").hide();
      }
      prevrepscope = thisCode;
    }
  });
  
  //
  $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-periode>div>ul").on("click","li",function() {
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
  $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-branch>div>ul").on("click","li",function() {
    var thisCode = $(this).data("code");
    if(thisCode !== prevrepbranch) {
      $("div#mdl-kci-report>div>div>div.modal-body>table>tbody>tr>td#report-branch>div>span").html(
        $(this).text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      prevrepbranch = thisCode;
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
  });
});