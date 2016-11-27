/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  //
  $("div#side-menu>div#help").click(function() {
    showCover(true);
    $("div#mdl-help").modal("show");
  });
  
  //
  $("div#mdl-help").on("hidden.bs.modal",function() {
    showCover(false);
  });
});