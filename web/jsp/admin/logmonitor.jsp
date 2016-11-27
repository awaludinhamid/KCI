<%-- 
    Document   : logmonitorpage
    Created on : Nov 22, 2016, 9:57:13 AM
    Author     : awal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="../../js/admin-log-monitor.js"></script>    
  </head>
  <body>
    <div ng-controller="kciLogMonCtrl">
    <div class="modal fade" id="mdl-log-monitor" tabindex="-1" role="dialog" aria-labelledby="log-monitor-title" aria-hidden="true">
      <div class="modal-dialog dialog-common">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="log-monitor-title">
              <img src="../../img/log-monitor.png" alt="Log Monitor"/><span>&nbsp;Log Monitor</span>
            </h3>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <div class="col-sm-2">
                <input class="form-control" placeholder="-- Job Name --" ng-model="jobNamePattern">
              </div>
              <div class="col-sm-2">
                <input class="form-control" placeholder="-- Package Name --" ng-model="packNamePattern">
              </div>
              <div class="col-sm-2">
                <select class="form-control" ng-model="dateProcess">
                  <option value="" selected>--Choose Date--</option>
                  <option ng-repeat="date in dateProcessList" value="{{date}}">{{date}}</option>
                </select>
              </div>
              <div class="col-sm-2">
                <select class="form-control" ng-model="status">
                  <option value="ALL" selected>--Choose Status--</option>
                  <option ng-repeat="stat in statusList" value="{{stat}}">{{stat}}</option>
                </select>
              </div>
              <div class="img-crud-header">
                <img id="img-find-record" class="img-record img-record-medium" src="../../img/icon/find-icon.png" alt="Find icon" 
                     title="Find Record"/>
              </div>
            </div>
            <div id="table-log-monitor">
              <table class="table table-bordered table-condensed table-hover">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>Job Name</th>
                    <th>Package Name</th>
                    <th>Date Process</th>
                    <th>Comment</th>
                    <th>Status</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <tr ng-repeat="log in logMonitorTable" data-id="{{log.jobName}}">
                    <td>{{$index + 1}}</td>
                    <td>{{log.jobName}}</td>
                    <td>{{log.packageName}}</td>
                    <td>{{log.dateProcess}}</td>
                    <td>{{log.comment}}</td>
                    <td>
                      <img id="img-edit-record" class="img-record-small" ng-src="../../img/icon/{{statusIcon(log.status)}}" alt="Status icon" 
                            ng-hide="isHide(log.jobName)"/>
                    </td>
                    <td>
                      <img id="img-run-record" class="img-record img-record-small" src="../../img/icon/play-icon.png" alt="Run icon" 
                           title="Run Job" ng-hide="isHide(log.jobName)" ng-click="showRunJob(log)"/>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div id="pagination" style="text-align: center">
              <ul class="pagination"></ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="mdl-run-job" tabindex="-1" role="dialog" aria-labelledby="run-job-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="run-job-title">
              <img class="img-record img-record-medium" src="../../img/icon/play-icon.png" alt="Play icon"/>&nbsp<span>Run Job</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <span>Do you want to run this job immediately ?</span>
              <table class="table table-bordered">
                <thead>
                  <tr><th>Job Name</th><th>Package Name</th></tr>
                </thead>
                <tbody>
                  <tr><td></td><td></td></tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="modal-footer">
            <button id="btn-run" class="btn btn-primary" data-dismiss="modal">Run</button>
          </div>
        </div>
      </div>
    </div>
    </div>
  </body>
</html>
