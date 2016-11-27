<%-- 
    Document   : header
    Created on : Jun 25, 2015, 2:30:49 PM
    Author     : awal
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" type="image/png" href="../../fificon.png" />
    <link rel="stylesheet" href="../../jQuery/css/jquery-ui.min.css"/>
    <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../d3/css/tree.css"/>
    <link rel="stylesheet" href="../../css/header.css"/>
    <link rel="stylesheet" href="../../css/loading.css"/>
    <script src="../../jQuery/js/jquery.min.js"></script>
    <script src="../../jQuery/js/jquery-ui.min.js"></script>
    <script src="../../jQuery-Keyframes/js/jquery.keyframes.min.js"></script>
    <script src="../../angular/angular.min.js"></script>
    <script src="../../bootstrap/js/bootstrap.min.js"></script>
    <script charset="utf-8" src="../../d3/js/d3.js"></script>
    <script src="../../d3/js/d3.layout.tree.js"></script>
    <script src="../../js/support/header.js"></script>
    <script src="../../js/support/loading.js"></script>   
    <script src="../../js/support/pagination.js"></script>  
    <title>FIFGROUP KCI</title>
  </head>
  <body ng-app="kciApp">
    <div class="title">
      <!--span>FIFGROUP KCI</span-->
      <img src="../../img/kci-logo.png" alt="KCI Logo"/>
      <div class="right-info">
        <a href="http://www.fifgroup.co.id">
          <img src="../../img/fifgroup-logo.png" alt="FIFGROUP Logo"/>
        </a>
        <span id="uid">${sessionScope.uid}</span>
        <div id="user-menu">
          <div class="dropdown">
            <button class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="glyphicon glyphicon-user"></span>&nbsp;${sessionScope.cnname}&nbsp;<span class="caret"></span>
            </button>
            <ul class="dropdown-menu dropdown-menu-right">
              <li id="btn-logout"><a href="../../apps/auth/logout"><span class="glyphicon glyphicon-off"></span>&nbsp;Logout</a></li>
              <li role="separator" class="divider"></li>
              <li id="profile-btn"><a href="#"><span class="glyphicon glyphicon-edit"></span>&nbsp;Profile</a></li>
              <li id="home-btn"><a href="../../apps/main/welcome"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!--div id="backImage">
      <img src="../../img/logo-jempol.png" alt="background-image"/>
    </div-->  
    
    <div class="modal fade" id="mdl-profile" tabindex="-1" role="dialog" aria-labelledby="mdl-profile-title" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="mdl-profile-title">
              <span class="glyphicon glyphicon-edit">&nbsp;</span><span>User Profile</span>
            </h3>
          </div>
          <div class="modal-body">
            <div>
              <form class="form-horizontal">
                <div class="form-group">
                  <label for="prof-username" class="col-sm-3 control-label">User Name</label>
                  <div class="col-sm-9">
                    <input id="prof-username" class="form-control" value="${sessionScope.cnname}" disabled>
                  </div>
                </div>
                <div class="form-group">
                  <label for="prof-name" class="col-sm-3 control-label">User Id</label>
                  <div class="col-sm-9">
                    <input id="prof-name" class="form-control" value="${sessionScope.uid}" disabled>
                  </div>
                </div>
                <div class="form-group">
                  <label for="prof-session" class="col-sm-3 control-label">Session Id</label>
                  <div class="col-sm-9">
                    <input id="prof-session" class="form-control" value="${sessionScope.sessionid}" disabled>
                  </div>
                </div>
              </form>
            </div>
          </div>
          <div class="modal-footer">
            <button id="btn-print" type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>  
    
    <div class="modal fade" id="mdl-message" tabindex="-1" role="dialog" aria-labelledby="mdl-message-title" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="mdl-message-title">
              <span><span class="glyphicon glyphicon-info-sign"></span>&nbsp;Information</span>
            </h4>
          </div>
          <div class="modal-body">
            <span></span>
          </div>
          <div class="modal-footer">
          </div>
        </div>
      </div>
    </div> 
                  
    <div id="ball-stage">
      <div class="redball"></div>
      <div class="greenball"></div>
      <div class="yellowball"></div>
    </div>
  </body>
</html>
