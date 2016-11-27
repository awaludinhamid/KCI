<%-- 
    Document   : gradingvaluepage
    Created on : Nov 15, 2016, 9:57:13 AM
    Author     : awal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="../../js/admin-grading-value.js"></script>    
  </head>
  <body>
    <div ng-controller="kciGradesCtrl">
    <div class="modal fade" id="mdl-grading-value" tabindex="-1" role="dialog" aria-labelledby="mdl-grading-value-title" aria-hidden="true">
      <div class="modal-dialog dialog-common">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h3 class="modal-title" id="mdl-grading-value-title">
              <img src="../../img/grading-value.png" alt="Grading"/><span>&nbsp;Grading Value</span>
            </h3>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <div class="col-sm-3">
                <input class="form-control" placeholder="[--Grade Id--]" ng-model="gradeIdPattern">
              </div>
              <div class="col-sm-3">
                <input class="form-control" placeholder="[--Grade Name--]" ng-model="gradeNamePattern">
              </div>
              <div class="img-crud-header">
                <img id="img-find-record" class="img-record img-record-medium" src="../../img/icon/find-icon.png" alt="Find icon" title="Find Record"/>
                <img id="img-new-record" class="img-record img-record-medium" src="../../img/icon/new-icon.png" alt="New icon" title="New Record"/>
              </div>
            </div>
            <div id="table-grading-value">
              <table class="table table-bordered table-condensed table-hover">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>Grade Id</th>
                    <th>Grade Name</th>
                    <th>Batas Bawah</th>
                    <th>Batas Atas</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <tr ng-repeat="gradval in gradingValueTable">
                    <td>{{$index + 1}}</td>
                    <td>{{gradval.gradeId}}</td>
                    <td>{{gradval.gradeName}}</td>
                    <td>{{gradval.batasBawah}}</td>
                    <td>{{gradval.batasAtas}}</td>
                    <td>
                      <img id="img-edit-record" class="img-record img-record-small" src="../../img/icon/edit-icon.png" alt="Edit icon" 
                           title="Edit Record" ng-hide="isHide(gradval.gradeId)" ng-click="showEditGrading(gradval)"/>
                      <img id="img-delete-record" class="img-record img-record-small" src="../../img/icon/delete-icon.png" alt="Delete icon" 
                           title="Delete Record" ng-hide="isHide(gradval.gradeId)" ng-click="showDeleteGrading(gradval)"/>
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
    
    <div class="modal fade" id="mdl-new-grading" tabindex="-1" role="dialog" aria-labelledby="new-grading-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="new-grading-title">
              <img class="img-record img-record-medium" src="../../img/icon/new-icon.png" alt="New icon"/>&nbsp<span>New Grade</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <table>
                <tbody>
                  <tr>
                    <td>Grade ID <span class="text-mandatory">&ast;</span></td>
                    <td>
                      <input class="form-control" ng-model="grade.gradeId" maxlength="5" required>
                    </td>
                  </tr>
                  <tr>
                    <td>Grade Name <span class="text-mandatory">&ast;</span></td>
                    <td>
                      <input class="form-control" ng-model="grade.gradeName" maxlength="50" required>
                    </td>
                  </tr>
                  <tr>
                    <td>Batas Bawah <span class="text-mandatory">&ast;</span></td>
                    <td>
                      <input type="number" step="0.01" id="batasBawah" class="form-control" ng-model="grade.batasBawah" required>
                    </td>
                  </tr>
                  <tr>
                    <td>Batas Atas <span class="text-mandatory">&ast;</span></td>
                    <td>
                      <input type="number" step="0.01" id="batasAtas" class="form-control" ng-model="grade.batasAtas" required>
                    </td>
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
    
    <div class="modal fade" id="mdl-edit-grading" tabindex="-1" role="dialog" aria-labelledby="edit-grading-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="edit-grading-title">
              <img class="img-record img-record-medium" src="../../img/icon/edit-icon.png" alt="Edit icon"/>&nbsp<span>Edit Grade</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <table>
                <tbody>
                  <tr>
                    <td>Grade ID</td>
                    <td>
                      <input id="gradeId" class="form-control" ng-model="gradeEdit.gradeId" readonly>
                    </td>
                  </tr>
                  <tr>
                    <td>Grade Name <span class="text-mandatory">&ast;</span></td>
                    <td>
                      <input id="gradeName" class="form-control" ng-model="gradeEdit.gradeName" maxlength="50" required>
                    </td>
                  </tr>
                  <tr>
                    <td>Batas Bawah <span class="text-mandatory">&ast;</span></td>
                    <td>
                      <input type="number" step="0.01" id="batasBawah" class="form-control" ng-model="gradeEdit.batasBawah" required>
                    </td>
                  </tr>
                  <tr>
                    <td>Batas Atas <span class="text-mandatory">&ast;</span></td>
                    <td>
                      <input type="number" step="0.01" id="batasAtas" class="form-control" ng-model="gradeEdit.batasAtas" required>
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
    
    <div class="modal fade" id="mdl-delete-grading" tabindex="-1" role="dialog" aria-labelledby="delete-grading-title" aria-hidden="true">
      <div class="modal-dialog dialog-crud">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="delete-grading-title">
              <img class="img-record img-record-medium" src="../../img/icon/delete-icon.png" alt="Delete icon"/>&nbsp<span>Delete Grade</span>
            </h4>
          </div>
          <div class="modal-body">
            <div>
              <span>Do you want to delete this grade ?</span>
              <table class="table table-bordered">
                <thead>
                  <tr><th>Grade Id</th><th>Grade Name</th></tr>
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
