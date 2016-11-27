<%-- 
    Document   : orgchart
    Created on : Nov 20, 2016, 12:19:36 PM
    Author     : awal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../../jOrgChart/css/jquery.jOrgChart.css"/>
    <link rel="stylesheet" href="../../jOrgChart/css/prettify.css" />    
    <link rel="stylesheet" href="../../jOrgChart/css/custom.css"/>  
    <script src="../../jOrgChart/js/jquery.jOrgChart.js"></script>
    <script src="../../jOrgChart/js/prettify.js"></script>
    <script src="../../jOrgChart/js/custom.js"></script>  
  </head>
  <body>
    
    <div class="modal fade" id="mdl-org-chart" tabindex="-1" role="dialog" aria-labelledby="mdl-org-chart-title" aria-hidden="true">
      <div class="modal-dialog dialog-common">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="mdl-org-chart-title">
              <img src="../../img/orgchart.png" alt="Org Chart"/><span>&nbsp;Organization Chart</span>
            </h3>
          </div>
          <div class="modal-body">
            <div id="branch-info">
              <table>
                <tbody></tbody>
              </table>
            </div>
            <div class="topbar">
              <a class="brand" href="#">Struktur Organisasi:</a>            
            </div>
            <ul id="org-diag" style="display:none">
            </ul> 
            <div id="org-chart-diag" class="orgChart"></div>
          </div>
          <!--div class="modal-footer">
            <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
          </div-->
        </div>
      </div>
    </div> 
    
    <div class="modal fade" id="mdl-cpts-org" tabindex="-1" role="dialog" aria-labelledby="mdl-cpts-title" aria-hidden="true" ng-controller="kciCptsCtrl">
      <div class="modal-dialog dialog-common">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="mdl-cpts-title">
              <img src="../../img/cpts.png" alt="CPTS"/><span>&nbsp;Control Process Tracking System</span>
            </h3>
          </div>
          <div class="modal-body">
            <div id="table-cpts-org">
              <table class="table table-bordered table-condensed table-hover">
                <thead>
                  <tr>
                    <th>Periode</th>
                    <th>NPK</th>
                    <th>Employee Name</th>
                    <th>Branch</th>
                    <th>Position</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>KCI Value</th>
                    <th>ISR Value</th>
                    <th>Last Audit Grading</th>
                    <th>Last Audit Visit</th>
                    <th>Last Audit PIC</th>
                  </tr>
                </thead>
                <tbody>
                  <tr ng-repeat="cpts in cptsTable">
                    <td>{{cpts.periode}}</td>
                    <td>{{cpts.npk}}</td>
                    <td>{{cpts.employeeName}}</td>
                    <td>{{cpts.officeId}}</td>
                    <td>{{cpts.position}}</td>
                    <td>{{cpts.startDate}}</td>
                    <td>{{cpts.endDate}}</td>
                    <td>{{cpts.kciValue}}</td>
                    <td>{{cpts.isrValue}}</td>
                    <td>{{cpts.lastAuditGrading}}</td>
                    <td>{{cpts.lastAuditVisit}}</td>
                    <td>{{cpts.lastAuditPic}}</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div id="pagination" style="text-align: center">
              <ul class="pagination"></ul>
            </div>
          </div>
          <!--div class="modal-footer">
            <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
          </div-->
        </div>
      </div>
    </div>     
  </body>
</html>
