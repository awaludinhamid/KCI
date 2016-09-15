/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function startLoadingAnim() {
  document.getElementById("ball-stage").style.display = "inline";
  document.getElementById("ball-stage").style.WebkitAnimationPlayState = "running";
  document.getElementById("ball-stage").style.animationPlayState = "running";
  document.getElementById("text-stage").style.display = "inline";
  document.getElementById("text-stage").style.WebkitAnimationPlayState = "running";
  document.getElementById("text-stage").style.animationPlayState = "running";
}
function stopLoadingAnim() {
  document.getElementById("ball-stage").style.display = "none";
  document.getElementById("ball-stage").style.WebkitAnimationPlayState = "running";
  document.getElementById("ball-stage").style.animationPlayState = "running";
  document.getElementById("text-stage").style.display = "none";
  document.getElementById("text-stage").style.WebkitAnimationPlayState = "running";
  document.getElementById("text-stage").style.animationPlayState = "running";
}
