<%-- 
    Document   : sidemenudash
    Created on : Aug 8, 2016, 8:38:33 PM
    Author     : awal
--%>
<%@include file="../dashboard/cpts.jsp" %>
<%@include file="../dashboard/help.jsp" %>
<%@include file="../dashboard/orgchart.jsp" %>
<%@include file="../dashboard/reportperiodik.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../../css/side-menu-dash.css"/>
    <script src="../../js/support/side-menu-dash.js"></script>  
  </head>
  <body>
    <div id="side-menu">
      <div id="report">
        <img src="../../img/report.png" alt="Report"/><br/>
        <span>REPORT</span>
      </div>
      <div id="org-chart">
        <img src="../../img/orgchart.png" alt="Org Chart"/><br/>
        <span>ORG CHART</span>
      </div>
      <div id="cpts">
        <img src="../../img/cpts.png" alt="CPTS"/><br/>
        <span>CPTS</span>
      </div>
      <div id="help">
        <img src="../../img/help.png" alt="Help"/><br/>
        <span>HELP</span>
      </div>
    </div>   
  </body>
</html>
