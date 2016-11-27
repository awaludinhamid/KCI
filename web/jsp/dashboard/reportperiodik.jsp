<%-- 
    Document   : reportperiodik
    Created on : Nov 20, 2016, 12:17:08 PM
    Author     : awal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="../../js/dashboard-report.js"></script>
  </head>
  <body>
    
    <div class="modal fade" id="mdl-kci-report" tabindex="-1" role="dialog" aria-labelledby="mdl-kci-report-title" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="mdl-kci-report-title">
              <img src="../../img/report.png" alt="Report"/><span>&nbsp;KCI REPORT</span>
            </h3>
          </div>
          <div class="modal-body">
            <table>
              <tbody>
                <tr>
                  <td>Periode</td>
                  <td id="repperiode">                  
                    <div class="dropdown">            
                      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                      <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>Scope of Report</td>
                  <td id="repscope">                  
                    <div class="dropdown">            
                      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                      <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>Area</td>
                  <td id="reparea">                  
                    <div class="dropdown">            
                      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                      <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>Company</td>
                  <td id="repcompany">                  
                    <div class="dropdown">            
                      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                      <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>Department</td>
                  <td id="repdept">                  
                    <div class="dropdown">            
                      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                      <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="modal-footer">
            <button id="btn-print" type="button" class="btn btn-primary" data-dismiss="modal">Print</button>
          </div>
        </div>
      </div>
    </div>  
  </body>
</html>
