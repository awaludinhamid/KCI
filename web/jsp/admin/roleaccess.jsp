<%-- 
    Document   : roleaccesspage
    Created on : Nov 15, 2016, 9:57:13 AM
    Author     : awal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="../../js/admin-role-access.js"></script>    
  </head>
  <body>
    <div ng-controller="kciRoleAccCtrl">
    <div class="modal fade" id="mdl-role-access" tabindex="-1" role="dialog" aria-labelledby="mdl-role-access-title" aria-hidden="true">
      <div class="modal-dialog dialog-common">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="mdl-role-access-title">
              <img src="../../img/user.png" alt="Role Acc"/><span>&nbsp;Role Access</span>
            </h3>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <div class="col-sm-3">
                <input class="form-control" placeholder="-- Employee Name --" ng-model="emplNamePattern">
              </div>
              <div class="col-sm-3">
                <select class="form-control" ng-model="roleId">
                  <option value="0" selected>--Choose Role--</option>
                  <option ng-repeat="role in roleList" value="{{role.roleId}}">{{role.roleName}}</option>
                </select>
              </div>
              <div class="img-crud-header">
                <img id="img-find-record" class="img-record img-record-medium" src="../../img/icon/find-icon.png" alt="Find icon" 
                     title="Find Record"/>
                <img id="img-new-record" class="img-record img-record-medium" src="../../img/icon/new-icon.png" alt="New icon" title="New Record"/>
              </div>
            </div>
            <div id="table-role-access">
              <table class="table table-bordered table-condensed table-hover">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>NPK</th>
                    <th>Employee Name</th>
                    <th>Role</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <tr ng-repeat="roleacc in roleAccessTable" data-id="{{roleacc.userId}}">
                    <td>{{$index + 1}}</td>
                    <td>{{roleacc.employee.npk}}</td>
                    <td>{{roleacc.employee.namaEmployee}}</td>
                    <td>{{roleacc.role.roleName}}</td>
                    <td>
                      <img id="img-edit-record" class="img-record img-record-small" src="../../img/icon/edit-icon.png" alt="Edit icon" 
                           title="Edit Record" ng-hide="isHide(roleacc.userId)" ng-click="showEditUser(roleacc)"/>
                      <img id="img-delete-record" class="img-record img-record-small" src="../../img/icon/delete-icon.png" alt="Delete icon" 
                           title="Delete Record" ng-hide="isHide(roleacc.userId)" ng-click="showDeleteUser(roleacc)"/>
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
    
    <div class="modal fade" id="mdl-new-user" tabindex="-1" role="dialog" aria-labelledby="new-user-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="new-user-title">
              <img class="img-record img-record-medium" src="../../img/icon/new-icon.png" alt="New icon"/>&nbsp<span>New User</span>
            </h4>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <div class="col-sm-6">
                <input id="npk" class="form-control" placeholder="-- Employee NPK --" ng-model="npk">
              </div>
              <div class="img-crud-header">
                <img id="img-find-empl" class="img-record img-record-medium" src="../../img/icon/find-icon.png" alt="Find icon" title="Find Record"/>
              </div>
            </div>
            <div>
              <table class="table table-bordered">
                <thead>
                  <tr><th>Employee Name</th><th>Role</th></tr>
                </thead>
                <tbody>
                  <tr><td></td><td>Regular User</td></tr>
                </tbody>
              </table>
              <table id="table-role-dtl" class="table table-bordered">
                <tbody>
                  <tr>
                    <td>
                      <img class="img-record-large" src="../../img/cpts.png" alt="cpts"/><br/>
                      <span>CPTS</span><br/>
                      <input type="checkbox" ng-model="hasCpts" ng-true-value="'true'" ng-false-value="'false'">
                    </td>
                    <td>
                      <img class="img-record-large" src="../../img/orgchart.png" alt="org chart"/><br/>
                      <span>ORG CHART</span><br/>
                      <input type="checkbox" ng-model="hasOrgChart" ng-true-value="'true'" ng-false-value="'false'">
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <img class="img-record-large" src="../../img/report.png" alt="report"/><br/>
                      <span>REPORT</span><br/>
                      <input type="checkbox" ng-model="hasReport" ng-true-value="'true'" ng-false-value="'false'">
                    </td>
                    <td></td>
                  </tr>
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
    
    <div class="modal fade" id="mdl-edit-user" tabindex="-1" role="dialog" aria-labelledby="edit-user-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="edit-user-title">
              <img class="img-record img-record-medium" src="../../img/icon/edit-icon.png" alt="Edit icon"/>&nbsp<span>Edit User</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <table class="table table-bordered">
                <thead>
                  <tr><th>Employee Name</th><th>Role</th></tr>
                </thead>
                <tbody>
                  <tr><td></td><td></td></tr>
                </tbody>
              </table>
              <table class="table table-bordered">
                <tbody>
                  <tr>
                    <td>
                      <img class="img-record-large" src="../../img/cpts.png" alt="cpts"/><br/>
                      <span>CPTS</span><br/>
                      <input id="hasCpts" type="checkbox">
                    </td>
                    <td>
                      <img class="img-record-large" src="../../img/orgchart.png" alt="org chart"/><br/>
                      <span>ORG CHART</span><br/>
                      <input id="hasOrgChart" type="checkbox">
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <img class="img-record-large" src="../../img/report.png" alt="report"/><br/>
                      <span>REPORT</span><br/>
                      <input id="hasReport" type="checkbox">
                    </td>
                    <td></td>
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
    
    <div class="modal fade" id="mdl-delete-user" tabindex="-1" role="dialog" aria-labelledby="delete-user-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="delete-user-title">
              <img class="img-record img-record-medium" src="../../img/icon/delete-icon.png" alt="Delete icon"/>&nbsp<span>Delete User</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <span>Do you want to delete this user ?</span>
              <table class="table table-bordered">
                <thead>
                  <tr><th>Employee Name</th><th>Role</th></tr>
                </thead>
                <tbody>
                  <tr><td></td><td></td></tr>
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
    </div>
  </body>
</html>
