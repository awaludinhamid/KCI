<%-- 
    Document   : paramauditpage
    Created on : Nov 18, 2016, 9:57:13 AM
    Author     : awal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="../../js/admin-param-audit.js"></script>   
    <script src="../../js/admin-diag-flow.js"></script>    
  </head>
  <body>
    <div ng-controller="kciParamAuditCtrl">
    <div class="modal fade" id="mdl-param-audit" tabindex="-1" role="dialog" aria-labelledby="param-audit-title" aria-hidden="true">
      <div class="modal-dialog dialog-common">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="param-audit-title">
              <img src="../../img/param-audit.png" alt="Par Audit"/><span>&nbsp;Parameter Audit</span>
            </h3>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <div class="col-sm-3">
                <input class="form-control" placeholder="-- Parameter Name --" ng-model="paramNamePattern">
              </div>
              <div class="col-sm-3">
                <input class="form-control" placeholder="-- Package Name --" ng-model="packNamePattern">
              </div>
              <div class="col-sm-3">
                <select class="form-control" ng-model="deptId">
                  <option value="ALL" selected>--Choose Dept--</option>
                  <option ng-repeat="dept in deptList" value="{{dept.code}}">{{dept.name}}</option>
                </select>
              </div>
              <div class="img-crud-header">
                <img id="img-find-record" class="img-record img-record-medium" src="../../img/icon/find-icon.png" alt="Find icon" 
                     title="Find Record"/>
                <!--img id="img-new-record" class="img-record img-record-medium" src="../../img/icon/new-icon.png" alt="New icon" title="New Record"/-->
              </div>
            </div>
            <div id="table-param-audit">
              <table class="table table-bordered table-condensed table-hover">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>Param Id</th>
                    <th>Param Name</th>
                    <th>Package Name</th>
                    <th>Dept</th>
                    <th>Hierarchy</th>
                    <th>Status</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <tr ng-repeat="param in paramAuditTable" data-id="{{param.parameterId}}" data-dept-id="{{param.dept.deptId}}">
                    <td>{{pageStartNo + $index + 1}}</td>
                    <td>{{param.parameterId}}</td>
                    <td>{{param.parameterDesc}}</td>
                    <td>{{param.packageName}}</td>
                    <td>{{param.dept.deptDesc}}</td>
                    <td>
                      <img id="img-show-hie" class="img-record img-record-small" src="../../img/icon/hierarchy.png" alt="Hie icon" 
                           title="Show hierarchy" ng-hide="isHide(param.parameterId)" ng-click="showDiagFlow(param)"/>
                    </td>
                    <td>
                      <img id="img-edit-record" class="img-record-small" ng-src="../../img/icon/{{statusIcon(param.visible)}}" alt="Status icon" 
                            ng-hide="isHide(param.parameterId)"/>
                    </td>
                    <td>
                      <img id="img-edit-record" class="img-record img-record-small" src="../../img/icon/edit-icon.png" alt="Edit icon" 
                           title="Edit Record" ng-hide="isHide(param.parameterId)" ng-click="showEditParam(param)"/>
                      <!--img id="img-delete-record" class="img-record img-record-small" src="../../img/icon/delete-icon.png" alt="Delete icon" 
                           title="Delete Record" ng-hide="isHide(param.parameterId)" ng-click="showDeleteParam(param)"/-->
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
    
    <div class="modal fade" id="mdl-new-param" tabindex="-1" role="dialog" aria-labelledby="new-param-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="new-param-title">
              <img class="img-record img-record-medium" src="../../img/icon/new-icon.png" alt="New icon"/>&nbsp<span>New Parameter</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <table class="table">
                <tbody>
                  <tr><td>Parameter ID</td><td data-id="parameterId"></td></tr>
                  <tr><td>Parameter Name</td><td data-id="parameterDesc"></td></tr>
                  <tr><td>Department</td><td data-id="deptId"></td></tr>
                  <tr><td>Package Name</td><td data-id="packageName"></td></tr>
                  <tr><td>Status</td><td data-id="visible"></td></tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="modal-footer">
            <button id="btn-add" class="btn btn-primary" data-dismiss="modal">Add</button>
          </div>
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="mdl-edit-param" tabindex="-1" role="dialog" aria-labelledby="edit-param-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud dialog-crud-medium">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="edit-param-title">
              <img class="img-record img-record-medium" src="../../img/icon/edit-icon.png" alt="Edit icon"/>&nbsp<span>Edit Parameter</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <table>
                <tbody>
                  <tr><td>Parameter ID</td><td data-id="parameterId">{{paramEdit.parameterId}}</td></tr>
                  <tr><td>Parameter Name</td><td data-id="parameterDesc">{{paramEdit.parameterDesc}}</td></tr>
                  <tr><td>Department</td><td data-id="deptDesc">{{paramEdit.deptDesc}}</td></tr>
                  <tr>
                    <td>Package Name <span class="text-mandatory">&ast;</span></td>
                    <td data-id="packageName">
                        <select class="form-control" ng-model="paramEdit.packageName">
                          <option value="">--Choose Package--</option>
                          <option ng-repeat="packname in packageNameList" value="{{packname}}">{{packname}}</option>
                        </select>
                    </td>
                  </tr>
                  <tr>
                    <td>Status <span class="text-mandatory">&ast;</span></td>
                    <td data-id="visible">                      
                      <img class="img-record img-record-medium" src="../../img/icon/ok-icon.png" alt="Enable icon" 
                            ng-click="isChoose($event)"/>                      
                      <img class="img-record img-record-medium" src="../../img/icon/delete-icon.png" alt="Disable icon" 
                            ng-click="isChoose($event)"/>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="modal-footer">
            <button id="btn-update" class="btn btn-primary" data-dismiss="modal">Update</button>
          </div>
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="mdl-delete-param" tabindex="-1" role="dialog" aria-labelledby="delete-param-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="delete-param-title">
              <img class="img-record img-record-medium" src="../../img/icon/delete-icon.png" alt="Delete icon"/>&nbsp<span>Delete Parameter</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <table class="table table-bordered">
                <tbody>
                  <tr><td>Parameter ID</td><td></td></tr>
                  <tr><td>Parameter Name</td><td></td></tr>
                  <tr><td>Department</td><td></td></tr>
                  <tr><td>Package Name</td><td></td></tr>
                  <tr><td>Status</td><td></td></tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="modal-footer">
            <button id="btn-delete" class="btn btn-primary" data-dismiss="modal">Delete</button>
          </div>
        </div>
      </div>
    </div>
      
    <div class="modal fade" id="mdl-diag-flow" tabindex="-1" role="dialog" aria-labelledby="diag-flow-title" aria-hidden="true">
      <div class="modal-dialog dialog-common">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="diag-flow-title">
              <img class="img-record img-record-medium" src="../../img/icon/hierarchy.png" alt="Hierarchy icon"/>&nbsp<span></span>
            </h3>
          </div>
          <div class="modal-body">
            <div id="flow-data"><span></span></div>
            <div id="flow-data-header">
              <table>
                <tbody>
                  <tr>
                    <td>PARAMETER</td>
                    <td>PACKAGE</td>
                    <td>PROCEDURE</td>
                    <td>TABLE</td>
                    <td>GROUP</td>
                    <td>SUMMARY</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div id="body"></div>
          </div>
          <div class="modal-footer">
          </div>
        </div>
      </div>
    </div>
    </div>
  </body>
</html>
