/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//global variable

$(document).ready(function() {
  
  // on click filter
  $("div#filter>div>ul").on("click","li",function() { 
    var thisElem = $(this);
    var thisUl = $(this).parent("ul");
    window["prev"+thisUl.data("var")] = window[thisUl.data("var")];
    window[thisUl.data("var")] = thisElem.data("code");
    thisUl.siblings("span").html(thisElem.text()+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>");
    //region change propagate branches list change
    var thisUlParent = thisUl.parent("div");
    if(thisUlParent.attr("id") === "filter-region" && prevregion !== region) {
      var thisUlSiblingSpan = thisUlParent.siblings("div#filter-branch").children("span");
      if(thisUlSiblingSpan.text() !== allDataCode) {
        thisUlSiblingSpan.html(allDataCode+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;");
        prevbranch = branch;
        branch = allDataCode;
      }
      $.get("../../apps/data/branches/"+thisElem.data("code"),{},
        function(data,status) {
        if(status === "success") {
          $("div#filter>div#filter-branch>ul>li").remove();
          $("div#filter>div#filter-branch>ul").append(
                "<li data-code='"+allDataCode+"'>ALL</li>"
          );
          for(var idx = 0; idx < data.length; idx++)
            $("div#filter>div#filter-branch>ul").append(
                  "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
            );
        } else {
          alert("Generate branch unsuccessfully: status = " + status);
        }
      });      
    }    
  });
  
  //filter periode
  $.get("../../apps/data/allperiodes",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-periode>span").append(
        data[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>&nbsp;"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-periode>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
      dataperiode = data;
    } else {
      alert("Generate periode unsuccessfully: status = " + status);
    }
  });
    
  //filter company
  $.get("../../apps/data/allcompanies",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-company>ul").append(
            "<li data-code='"+allDataCode+"'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-company>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
      datacompany = data;
    } else {
      alert("Generate company unsuccessfully: status = " + status);
    }
  });
    
  //filter lob
  $.get("../../apps/data/allbussunits",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-lob>ul").append(
            "<li data-code='"+allDataCode+"'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-lob>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
    } else {
      alert("Generate business unit unsuccessfully: status = " + status);
    }
  });
    
  //filter region
  $.get("../../apps/data/allregions",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-region>ul").append(
            "<li data-code='"+allDataCode+"'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-region>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
      dataarea = data;
    } else {
      alert("Generate region unsuccessfully: status = " + status);
    }
  });
  
  //filter branch
  $.get("../../apps/data/branches/"+allDataCode,{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-branch>ul").append(
            "<li data-code='"+allDataCode+"'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-branch>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
      databranch = data;
    } else {
      alert("Generate branch unsuccessfully: status = " + status);
    }
  });
  
  //filter department
  $.get("../../apps/data/alldepartments",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-department>ul").append(
            "<li data-code='"+allDataCode+"'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-department>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
      datadept = data;
    } else {
      alert("Generate department unsuccessfully: status = " + status);
    }
  });  
});