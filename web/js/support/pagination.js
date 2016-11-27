/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// GLOBAL VARIABLE

var currPageNo = 1,
    pagingRecords = 10,
    currMaxPage;


// GLOBAL FUNCTION

// Click on main data page number
function clickPageNo(listObject,params,callback) {
  //
  var thisId = listObject.attr("id");
  //avoid execution on current page, disable page
  //page no must be less or equal then maximum page allowed
  if(!listObject.hasClass("active") && !listObject.hasClass("disabled") && currPageNo <= currMaxPage) {
    if(thisId === "firstPage")
      currPageNo = 1;
    else if(thisId === "nextPage")
      currPageNo += 1;
    else if(thisId === "prevPage")
      currPageNo -= 1;
    else if(thisId === "lastPage")
      currPageNo = currMaxPage;
    else
      currPageNo = parseInt(thisId.replace("page",""));
    callback(params);
  }  
}

function execPaging(dataLength) {
  
  setPagination();
  switchPageStatus();
  
  // Set pagination based on data length
  function setPagination() {
    //
    var pagePerLoad = 10;
    //recreated paging list
    $("div#pagination>ul>li").remove();
    if(dataLength === 0) return false;
    $("div#pagination>ul").append(
      "<li id='firstPage' title='First'><a href='#' aria-label='First'><span aria-hidden='true'>&laquo;</span></a></li>"+
      "<li id='prevPage' title='Previous'><a href='#' aria-label='Previous'><span aria-hidden='true'>&lt;</span></a></li>"
    );
   //set max page, start and end page no, and active page
    currMaxPage = Math.floor(dataLength/pagingRecords) + (dataLength%pagingRecords === 0 ? 0 : 1);
    var startPageNo = Math.floor(currPageNo/pagePerLoad) * pagePerLoad + (currPageNo%pagePerLoad === 0 ? 0 : 1); //start page no of serial page on page selected
    var endPageNo = currMaxPage < (startPageNo + pagePerLoad) ? currMaxPage : (startPageNo + pagePerLoad - 1); //end page no of serial page on page selected
    for(var idx = startPageNo; idx <= endPageNo; idx++) {
      $("div#pagination>ul").append(
        "<li id='page"+idx+"'"+(idx === currPageNo ? " class='active' " : "")+"><a href='#'>"+idx+"</a></li>"
      );
    }
    $("div#pagination>ul").append(
      "<li id='nextPage' title='Next'><a href='#' aria-label='Next'><span aria-hidden='true'>&gt;</span></a></li>"+
      "<li id='lastPage' title='Last'><a href='#' aria-label='Last'><span aria-hidden='true'>&raquo;</span></a></li>"
    );
  }

  // Switch page status
  function switchPageStatus() {
    if(currPageNo === 1)
      $("div#pagination>ul>li#firstPage").addClass("disabled");
    else
      $("div#pagination>ul>li#firstPage").removeClass("disabled");
    if(currPageNo > 1)
      $("div#pagination>ul>li#prevPage").removeClass("disabled");
    else
      $("div#pagination>ul>li#prevPage").addClass("disabled");
    if(currPageNo < currMaxPage)
      $("div#pagination>ul>li#nextPage").removeClass("disabled");
    else
      $("div#pagination>ul>li#nextPage").addClass("disabled");
    if(currPageNo === currMaxPage)
      $("div#pagination>ul>li#lastPage").addClass("disabled");
    else
      $("div#pagination>ul>li#lastPage").removeClass("disabled");
  }
}