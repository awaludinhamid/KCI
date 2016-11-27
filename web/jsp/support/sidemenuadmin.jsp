<%-- 
    Document   : sidemenuadmin
    Created on : Aug 8, 2016, 8:38:33 PM
    Author     : awal
--%>
<%@include file="../admin/roleaccess.jsp" %>
<%@include file="../admin/paramaudit.jsp" %>
<%@include file="../admin/logmonitor.jsp" %>
<%@include file="../admin/gradingvalue.jsp" %>

<!DOCTYPE html>
<html>
  <head>
  </head>
  <body>
    <div id="side-menu">
      <div id="role-access">
        <img src="../../img/user.png" alt="User"/><br/>
        <span>Role Access</span>
      </div>
      <div id="grading-value">
        <img src="../../img/grading-value.png" alt="Grading"/><br/>
        <span>Grading Value</span>
      </div>
      <div id="log-monitor">
        <img src="../../img/log-monitor.png" alt="Monitor"/><br/>
        <span>Log Monitor</span>
      </div>
      <div id="param-audit">
        <img src="../../img/param-audit.png" alt="Parameter"/><br/>
        <span>Parameter Audit</span>
      </div>
    </div>   
  </body>
</html>
