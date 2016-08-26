/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {

  //controlling user visibility
  if($("#uid").text().length > 0)
    $("#user-menu").show();
  else
    $("#user-menu").hide();
});
