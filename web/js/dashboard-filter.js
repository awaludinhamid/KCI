/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
  
  //filter periode
  $.get("../../apps/data/allperiodes",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-periode>span").append(
        data[0].name+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-periode>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
    } else {
      alert("Generate periode unsuccessfully: status = " + status);
    }
  });
    
  //filter company
  $.get("../../apps/data/allcompanies",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-company>ul").append(
            "<li data-code='all'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-company>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
    } else {
      alert("Generate company unsuccessfully: status = " + status);
    }
  });
    
  //filter lob
  $.get("../../apps/data/allbussunits",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-lob>ul").append(
            "<li data-code='all'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-lob>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
    } else {
      alert("Generate company unsuccessfully: status = " + status);
    }
  });
    
  //filter region
  $.get("../../apps/data/allregions",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-region>ul").append(
            "<li data-code='all'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-region>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
    } else {
      alert("Generate region unsuccessfully: status = " + status);
    }
  });
  
  //filter branch
  $.get("../../apps/data/branches/all",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-branch>ul").append(
            "<li data-code='all'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-branch>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
    } else {
      alert("Generate branch unsuccessfully: status = " + status);
    }
  });
  
  //filter department
  $.get("../../apps/data/alldepartments",{},
    function(data,status) {
    if(status === "success") {
      $("div#filter>div#filter-department>ul").append(
            "<li data-code='all'>ALL</li>"
      );
      for(var idx = 0; idx < data.length; idx++)
        $("div#filter>div#filter-department>ul").append(
              "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
        );
    } else {
      alert("Generate page no unsuccessfully: status = " + status);
    }
  });
  
  //list click
  $("div#filter>div>ul").on("click","li",function() {
    $(this).parent("ul").siblings("span").html(($(this).text())+"&nbsp;<span class='glyphicon glyphicon-triangle-bottom'></span>");
    //region change propagate branches list change
    if($(this).parent("ul").parent("div").attr("id") === "filter-region") {
      $.get("../../apps/data/branches/"+$(this).data("code"),{},
        function(data,status) {
        if(status === "success") {
          $("div#filter>div#filter-branch>ul>li").remove();
          $("div#filter>div#filter-branch>ul").append(
                "<li data-code='all'>ALL</li>"
          );
          for(var idx = 0; idx < data.length; idx++)
            $("div#filter>div#filter-branch>ul").append(
                  "<li data-code='"+data[idx].code+"'>"+data[idx].name+"</li>"
            );
        } else {
          alert("Generate page no unsuccessfully: status = " + status);
        }
      });      
    }
  });
  
});