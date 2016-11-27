<%-- 
    Document   : adminpage
    Created on : Jun 19, 2015, 11:58:08 AM
    Author     : awal
--%>

<%@include file="support/header.jsp" %>
<%@include file="support/sidemenuadmin.jsp" %>
<!DOCTYPE html>

<html>
  <head>
    <link rel="stylesheet" href="../../css/admin.css"/>
    <script src="../../js/admin.js"></script>
  </head>
  <body>
  <div class="container">
    <div class="row">
    </div>
  </div>
  
  <div>
    
    <div class="modal fade" id="mdl-confirm" tabindex="-1" role="dialog" aria-labelledby="confirm-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="confirm-title">
              <img class="img-record img-record-medium" src="" alt=""/>&nbsp<span></span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <span></span>
            </div>
          </div>
          <div class="modal-footer">
            <button class="btn btn-primary" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <div class="div-cover"></div>
  <div id="text-stage">
    Page loading, please wait...
  </div>
</body>
</html>
