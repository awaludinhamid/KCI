<%-- 
    Document   : homepage
    Created on : Jun 19, 2015, 11:58:08 AM
    Author     : awal
--%>

<%@include file="support/header.jsp" %>
<%@include file="support/sidemenudash.jsp" %>
<!DOCTYPE html>

<html>
  <head>
    <link rel="stylesheet" href="../../nvd3/css/nv.d3.css"/>
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/dijit/themes/claro/claro.css">
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/dijit/themes/tundra/tundra.css">
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/esri/css/esri.css">
    <!--link rel="stylesheet" href="http://10.17.18.123:8081/arcgis_js_api_v311/library/3.11/3.11/dijit/themes/claro/claro.css">
    <link rel="stylesheet" href="http://10.17.18.123:8081/arcgis_js_api_v311/library/3.11/3.11/dijit/themes/tundra/tundra.css">
    <link rel="stylesheet" href="http://10.17.18.123:8081/arcgis_js_api_v311/library/3.11/3.11/esri/css/esri.css"-->
    <link rel="stylesheet" href="../../css/dashboard.css"/>
    <link rel="stylesheet" href="../../css/parameter-ie.css"/>
    <script src="../../nvd3/js/nv.d3.min.js"></script>
    <script src="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/init.js"></script>
    <!--script src="http://10.17.18.123:8081/arcgis_js_api_v311/library/3.11/3.11/init.js"></script-->
    <script src="../../js/dashboard.js"></script>
    <script src="../../js/dashboard-filter.js"></script>
    <script src="../../js/dashboard-map.js"></script>
    <script src="../../js/dashboard-gauge.js"></script>
    <script src="../../js/dashboard-parameter.js"></script>
    <script src="../../js/dashboard-graphic.js"></script>
    <script src="../../js/dashboard-running-text.js"></script>
  </head>
<body class="claro">
  <div id="div-running-text">
    <div class="running-text">
      <table>
        <tbody>
          <tr>
          </tr>
          <tr class="run-txt-dev-val">
          </tr>
        </tbody>
      </table>
    </div>
    <div class="running-text-2">
      <table>
        <tbody>
          <tr>
          </tr>
          <tr class="run-txt-dev-val">
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div id="filter" ng-controller="kciFilterCtrl">
    <div id="filter-periode" class="dropdown">
      PERIODE&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu" data-var="periode">
        <li ng-repeat="periode in filterPeriodeList" data-code="{{periode.code}}">{{periode.name}}</li>
      </ul>
    </div>
    <div id="filter-company" class="dropdown">
      COMP&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      </span>
      <ul class="dropdown-menu dropdown-menu-right" data-var="company">
        <li ng-repeat="company in filterCompanyList" data-code="{{company.code}}">{{company.name}}</li>
      </ul>
    </div>
    <div id="filter-lob" class="dropdown">
      LOB&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      </span>
      <ul class="dropdown-menu dropdown-menu-right" data-var="lob">
        <li ng-repeat="lob in filterLobList" data-code="{{lob.code}}">{{lob.name}}</li>
      </ul>
    </div>
    <div id="filter-region" class="dropdown">
      AREA&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu" data-var="region">
        <li ng-repeat="region in filterRegionList" data-code="{{region.code}}">{{region.name}}</li>
      </ul>
    </div>
    <div id="filter-branch" class="dropdown">
      BRANCH&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu" data-var="branch">
        <li ng-repeat="branch in filterBranchList" data-code="{{branch.code}}">{{branch.name}}</li>
      </ul>
    </div>
    <div id="filter-department" class="dropdown">
      DEPT&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu" data-var="dept">
        <li ng-repeat="department in filterDepartmentList" data-code="{{department.code}}">{{department.name}}</li>
      </ul>
    </div>
  </div>
  <div id="dashboard" class="container">
    <div class="row">
      <div class="col-md-1">
        <div>
          <div id="map-title" class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming" title="Zoom"/>
            <span>BRANCH MAPS</span>
            <div class="dropdown">
              <img src="../../img/gear.png" alt="Option" title="Option" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"/>
              <ul class="dropdown-menu dropdown-menu-right">
                <li><a href="#"><img src="../../img/icon/AllPin1LargeB.png" alt="All"/><span>&nbsp;All Grade</span></a></li>
                <li><a href="#"><img src="../../img/icon/GreenPin1LargeB.png" alt="Green"/><span>&nbsp;Satisfactory</span></a></li>
                <li><a href="#"><img src="../../img/icon/YellowPin1LargeB.png" alt="Yellow"/><span>&nbsp;Need Improve.</span></a></li>
                <li><a href="#"><img src="../../img/icon/RedPin1LargeB.png" alt="Red"/><span>&nbsp;Unsatisfactory</span></a></li>
                <li><a href="#"><img src="../../img/icon/recycle-icon.png" alt="Rest"/><span>&nbsp;To Local Rest</span></a></li>
              </ul>
            </div>
          </div>
          <div id="map" class="content-dashboard">
            <div id="custom-zoom-btn" class="btn-toolbar">
              <div class="btn-group">
                <button id="custom-zoom-in-btn" class="btn btn-info btn-sm" title="Zoom In">
                  <span class="glyphicon glyphicon-plus"></span>
                </button>
                <button id="custom-zoom-out-btn" class="btn btn-default btn-sm" title="Zoom Out">
                  <span class="glyphicon glyphicon-minus"></span>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-offset-5 col-md-6">
        <div>
          <div class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming" title="Zoom"/>
            <span>KCI VALUES</span>
            <div class="dropdown">
              <img src="../../img/gear.png" alt="Option" title="Option" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"/>
              <ul class="dropdown-menu dropdown-menu-right">
                <li><a href="#"><span class="glyphicon glyphicon-refresh refresh-btn"></span><span class="refresh-btn">&nbsp;Refresh</span></a></li>
              </ul>
            </div>
          </div>
          <div id="gauge" class="content-dashboard">
            <img id="img-fifgroup" src="../../img/gauge.png" alt="Gauge FIFGROUP"/>
            <img id="img-area" src="../../img/gauge.png" alt="Gauge Area"/>
            <img id="img-branch" src="../../img/gauge.png" alt="Gauge Branch"/>
            <img id="needle-fifgroup" src="../../img/needle2.png" alt="Speedo Needle"/>
            <img id="needle-area" src="../../img/needle2.png" alt="Speedo Needle"/>
            <img id="needle-branch" src="../../img/needle2.png" alt="Speedo Needle"/>
            <svg></svg>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-1">
        <div>
          <div class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming" title="Zoom"/>
            <span>PARAMETER</span>
            <div class="dropdown">
              <img src="../../img/gear.png" alt="Option" title="Option" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"/>
              <ul class="dropdown-menu dropdown-menu-right">
                <li><a href="#"><span class="glyphicon glyphicon-refresh refresh-btn"></span><span class="refresh-btn">&nbsp;Refresh</span></a></li>
                <li><a href="#"><span class="glyphicon glyphicon-download-alt download-btn"></span><span class="download-btn">&nbsp;Download</span></a></li>
              </ul>
            </div>
          </div>
          <div id="parameter" class="content-dashboard" ng-controller="kciParamCtrl">
            <ul class="list-unstyled">
              <li ng-repeat="param in paramList" data-code="{{param.code}}" data-table-name="{{param.tableName}}">
                <span id="param-desc" class="{{param.classNoParamValue}}">{{param.desc}}</span><br/>
                <div id="param-p1" style="background-color: rgba(255,80,80,0.8); width: {{param.persenP1}};
                     float: left; text-align: center; display: {{param.display}}" class="{{param.classP1}}">
                  <span>{{param.p1}}</span>
                </div>
                <div id="param-p2" style="background-color: rgba(220,220,40,0.8); width: {{param.persenP2}};
                     float: left; text-align: center; display: {{param.display}}" class="{{param.classP2}}">
                  <span>{{param.p2}}</span>
                </div>
                <div id="param-p3" style="background-color: rgba(80,255,180,0.8); text-align: center; display: {{param.display}}">
                  <span>{{param.p3}}</span>
                </div>
                <div class="no-param-value {{param.classDisplayNoParamValue}}" style="background-color: #fafafa; color: red; width: 100%; cursor: default;
                     text-decoration: none">
                  <span>No available value !</span>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-md-offset-5 col-md-6">
        <div>
          <div class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming" title="Zoom"/>
            <span>TREND</span>
            <div class="dropdown">
              <img src="../../img/gear.png" alt="Option" title="Option" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"/>
              <ul class="dropdown-menu dropdown-menu-right">
                <li><a href="#"><span class="glyphicon glyphicon-refresh refresh-btn"></span><span class="refresh-btn">&nbsp;Refresh</span></a></li>
              </ul>
            </div>
          </div>
          <div id="graphic" class="content-dashboard">
            <div id="chart-trend"><svg></svg></div>            
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="gauge-detail" ng-controller="kciGaugeCtrl">
    <span class="glyphicon glyphicon-remove-circle close-btn" title="Close"></span>
    <div id="kci-detail">
      <table>
        <tbody>
          <tr ng-repeat="kciDetail in kciDetailList">
            <td>{{kciDetail.name}}</td><td style="color: {{kciDetail.color}}">{{kciDetail.assignValue}}</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>{{kciDetail.nameEven}}</td><td style="color: {{kciDetail.colorEven}}">{{kciDetail.assignValueEven}}</td>
          </tr>
        </tbody>      
      </table>
    </div>
    <div id="kci-parent">
      <span></span>
    </div>
  </div>
  <div id="parameter-detail" ng-controller="kciParamDetCtrl">
    <span class="glyphicon glyphicon-remove-circle close-btn" title="Close"></span>
    <div id="print-file">
      <img id="txt" src="../../img/icon/text_icon.jpg" alt="Text Icon" title="Download (Text)"/>
      <img id="xls" src="../../img/icon/excel_icon.jpg" alt="Excel Icon" title="Download (Excel)"/>
    </div>
    <div id="kci-table">
      <div>
        <table class="table table-bordered table-condensed">
          <thead>
            <tr>
              <th ng-repeat="paramDetHdr in paramDetailHeaderList">{{paramDetHdr}}</th>
            </tr>
          </thead>
          <tbody>
            <tr ng-repeat="paramDetContent in paramDetailContentList">
              <td ng-repeat="paramDetHdr in paramDetailHeaderList">{{paramDetContent[paramDetHdr]}}</td>
            </tr>
          </tbody>      
        </table>
      </div>
      <div id="pagination" style="text-align: center">
        <ul class="pagination"></ul>
      </div>
    </div>
  </div>
  <div class="div-cover"></div>
  <div id="text-stage">
    Page loading, please wait...
  </div>
  
  <div hidden>
    <span id="hasRoleCpts">${sessionScope.hasCpts}</span>
    <span id="hasRoleOrgChart">${sessionScope.hasOrgChart}</span>
    <span id="hasRoleReport">${sessionScope.hasReport}</span>
  </div>
</body>
</html>
