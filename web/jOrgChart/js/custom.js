/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  var branchHeadObj = {};
  var branchDescendArr = [];
  var posDescendArr = [];

  /* Custom jQuery */
  $("#show-list").click(function(e){
      e.preventDefault();

      $("#list-html").toggle("fast", function(){
          if($(this).is(":visible")){
              $("#show-list").text("Hide underlying list.");
              $("#list-html").text($("#org-diag").html());
              $(".topbar").fadeTo("fast",0.9);
          }else{
              $("#show-list").text("Show underlying list.");
              $(".topbar").fadeTo("fast",1);                  
          }
      });
  });
  
  //
  $("#org-diag").bind("DOMSubtreeModified", function() {
      $("#list-html").text("");
      $("#list-html").text($("#org-diag").html());
      prettyPrint();                
  });
  
  //
  $("div#side-menu>div#org-chart").click(function() { 
    //
    if($("span#hasRoleOrgChart").text() !== "true") { 
      showMessage("You have no Organization Chart role ..!");
    } else if($("div#filter-branch>span").text().trim() === "ALL") {    
      showMessage("Please select branch first ..!");  
    } else {
      showCover(true,true); 
      //init
      $.get("../../apps/data/orgchart",{periode: periode,officeId: branch}, function(data) {
        $("div#branch-info>table>tbody").empty();
        $("ul#org-diag").empty();
        $("div#org-chart-diag").empty();

        //
        var branchInfoObj = JSON.parse(data.branchInfo);
        $("div#branch-info>table>tbody").append(
          "<tr><td>Office Code</td><td>: " + branchInfoObj.code + " - " + branchInfoObj.name + "</td>" +
            "<td style='width: 200px'></td><td>Last Audit Visit</td><td>: " + branchInfoObj.lastAuditVisit + "</td></tr>" +
          "<tr><td>Address</td><td>: " + branchInfoObj.address + "</td>" +
            "<td></td><td>Last Audit PIC</td><td>: " + branchInfoObj.lastAuditPic + "</td></tr>" +
          "<tr><td>Last KCI Value</td><td>: " + branchInfoObj.lastKciValue + "</td>" +
            "<td></td><td>ISR Value</td><td>: " + branchInfoObj.isrValue + "</td></tr>"
        );

        //
        branchHeadObj = data.branchHead;
        branchDescendArr = data.deptHead;
        posDescendArr = data.posHead;
        //
        var htmlText = "<li>";
        htmlText += getNodeText(branchHeadObj.employeeName,branchHeadObj.npk,branchHeadObj.position,
          branchHeadObj.startDate,branchHeadObj.masaJabatan,branchHeadObj.kciValue);
        //
        var centerOfDeptHead = Math.floor(branchDescendArr.length/2);
        htmlText += "<ul>";
        $.each(branchDescendArr,function(idxBranch,valBranch) {
          htmlText += 
                  "<li>" +
                    getNodeText(valBranch.employeeName,valBranch.npk,valBranch.position,
                      valBranch.startDate,valBranch.masaJabatan,valBranch.kciValue);
          if(idxBranch === (centerOfDeptHead - 1)) {
            htmlText += "<li><span style='font-size: 10px; font-weight: bold'>BMPOS</span><ul>";
            $.each(posDescendArr,function(idxPos,valPos) {
              htmlText += 
                      "<li>" +
                        getNodeText(valPos.employeeName,valPos.npk,valPos.position,
                          valPos.startDate,valPos.masaJabatan,valPos.kciValue,true) +
                      "</li>";
            });
            htmlText += "</ul>";
          }
          htmlText += "</li>";
        });
        htmlText += "</ul></li>";
        //
        $("ul#org-diag").append(htmlText);

        //
        $("#org-diag").jOrgChart({
            chartElement : "#org-chart-diag"//default 'body'
            //dragAndDrop  : true //default false
            //depth: 100 //default -1(unlimited)
            //chartClass: 'modify' //default 'jOrgChart'
        });
        $("div#mdl-org-chart").modal("show"); 
        showCover(true,false); 
      });
    }
  });
  
  //
  $("div#mdl-org-chart").on("hidden.bs.modal",function() {
    showCover(false,false);
  });
  
  $("div#mdl-org-chart ").on("mouseenter",".node",function() {
    $(this).css("transform",$(this).children("table").data("translate") + " scale(2)");
  });
  $("div#mdl-org-chart ").on("mouseleave",".node",function() {
      $(this).css("transform","");
  });
  
  //.org-table tr:first-child>>td>div
  $("div#org-chart-diag ").on("click",".org-table>tbody>tr:first-child>td>div",function(evt) {
    currPageNo = 1;
    var modalCptsOrg = $("div#mdl-cpts-org");
    var scope = modalCptsOrg.scope();
   // $("div#mdl-org-chart").modal("hide");
    scope.npk = $(this).children("span").text();
    scope.showCptsTable(modalCptsOrg);
    modalCptsOrg.modal("show");
  });
  
  //
  function getNodeText(name,npk,jobDesc,hiringDate,workingTime,nKci,flagPos) {
    var currNKci = (nKci === null) ? null : nKci.toFixed(2);
    var bgColorKci = (currNKci < 1.51 ? "lightgreen" : (currNKci > 2.5 ? "red" : "yellow"));
    var translate = function() {
      if(npk === branchDescendArr[branchDescendArr.length-1].npk)
        return "translate(-50px)";
      if(npk === branchDescendArr[0].npk)
        return "translate(50px)";
      if(npk === branchHeadObj.npk)
        return "translate(0px,50px)";
      if(flagPos)
        return "translate(0px,-50px)";
      return "";
    }
    return "<table class='table table-bordered table-condensed org-table' data-translate='"+translate()+"'>" +
            "<tbody>" +
              "<tr><td colspan='2'><div>" + name + " (<span>" + npk + "</span>)</div></td></tr>" +
              "<tr><td colspan='2'><div>" + jobDesc + "</div></td></tr>" +
              "<tr><td>" + hiringDate + "</td><td>" + workingTime + "</td></tr>" +
              "<tr><td colspan='2'" + (currNKci === null ? ">" : (" style='background-color: " + bgColorKci + "'>" + currNKci)) + "</td></tr>" +
            "</tbody>" +
          "</table>";
  }
  
});
