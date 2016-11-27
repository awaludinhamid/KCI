/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    
  //
  $(".modal:not(#mdl-diag-flow)").on("hidden.bs.modal",function() {
    showCover(false);
  });
});
