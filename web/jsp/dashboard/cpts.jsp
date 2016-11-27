<%-- 
    Document   : cpts
    Created on : Nov 20, 2016, 12:20:33 PM
    Author     : awal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="../../js/dashboard-cpts.js"></script>    
  </head>
  <body>
    <div ng-controller="kciCptsCtrl">
    <div class="modal fade" id="mdl-cpts" tabindex="-1" role="dialog" aria-labelledby="mdl-cpts-title" aria-hidden="true">
      <div class="modal-dialog dialog-common">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="mdl-cpts-title">
              <img src="../../img/cpts.png" alt="CPTS"/><span>&nbsp;Control Process Tracking System</span>
            </h3>
          </div>
          <div class="modal-body">
            <div class="form-group crud-header">
              <div class="col-sm-3">
                <input class="form-control" placeholder="[Periode]" ng-model="periodePattern">
              </div>
              <div class="col-sm-3">
                <input class="form-control" placeholder="[NPK | Name | Branch]" ng-model="otherPattern">
              </div>
              <div class="img-crud-header">
                <img id="img-find-record" class="img-record img-record-medium" src="../../img/icon/find-icon.png" alt="Find icon" title="Find Record"/>
              </div>
            </div>
            <div id="table-cpts">
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
                    <td>{{cpts.nilaiKci}}</td>
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
    </div>
  </body>
</html>
