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
    <link rel="stylesheet" href="../../css/header.css"/>
    <script src="../../jQuery/js/jquery.min.js"></script>
    <script src="../../jQuery/js/jquery-ui.min.js"></script>
    <script src="../../bootstrap/js/bootstrap.min.js"></script>
    <script src="../../js/header.js"></script>
    <title>FIFGROUP KCI</title>
  </head>
  <body>
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
              <li><a href="#"><span class="glyphicon glyphicon-edit"></span>&nbsp;Profile</a></li>
              <li id="home-btn"><a href="../../apps/main/welcome"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a></li>
            </ul>
          </div>
        </div>
        <!--sec:authentication property="principal"/-->
      </div>
    </div>
    <!--div id="backImage">
      <img src="../../img/logo-jempol.png" alt="background-image"/>
    </div-->
  </body>
</html>
