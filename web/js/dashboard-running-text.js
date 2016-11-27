/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  /*var runTextArr = [{name: "JATA", value: 1.26, devValue: 0.05, imgName: "triangle-up.png"},
                    {name: "JABAR", value: 1.06, devValue: 0.03, imgName: "triangle-down.png"},
                    {name: "RIAUNAD", value: 1.06, devValue: 0.03, imgName: "triangle-down.png"},
                    {name: "BALOM", value: 1.83, devValue: 0.43, imgName: "triangle-up.png"}];*/
  //
  $.get("../../apps/data/runningtext",{},function(data) {
    var runText = "[";
    $.each(data,function(idx,val) {
      runText += val + ",";
    });
    runText = runText.replace(/,$/,"") + "]";
    $.each(JSON.parse(runText),function(idx,val) {
      $(".running-text tr:nth-child(1)").append(
                "<td rowspan='2' class='run-txt-name'>"+val.name+"</td>"+
                "<td class='run-txt-val'>"+val.value+"</td>"+
                "<td rowspan='2'><img src='../../img/icon/"+val.imgName+"' alt='"+val.imgName.replace(".png","")+"'/>&bigcirc;</td>"
              );
      $(".running-text tr:nth-child(2)").append(
                "<td>"+val.devValue+"</td>"
              );
      //
      $(".running-text-2 tr:nth-child(1)").append(
                "<td rowspan='2' class='run-txt-name'>"+val.name+"</td>"+
                "<td class='run-txt-val'>"+val.value+"</td>"+
                "<td rowspan='2'><img src='../../img/icon/"+val.imgName+"' alt='"+val.imgName.replace(".png","")+"'/>&bigcirc;</td>"
              );
      $(".running-text-2 tr:nth-child(2)").append(
                "<td>"+val.devValue+"</td>"
              );
    });
  });
});
