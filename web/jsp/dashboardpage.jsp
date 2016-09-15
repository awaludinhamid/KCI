<%-- 
    Document   : homepage
    Created on : Jun 19, 2015, 11:58:08 AM
    Author     : awal
--%>

<%@include file="support/header.jsp" %>
<%@include file="support/sidemenu.jsp" %>
<!DOCTYPE html>

<html>
  <head>
    <link rel="stylesheet" href="../../d3/css/tree.css"/>
    <link rel="stylesheet" href="../../nvd3/css/nv.d3.css"/>
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/dijit/themes/claro/claro.css">
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/dijit/themes/tundra/tundra.css">
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/esri/css/esri.css">
    <!--link rel="stylesheet" href="http://10.17.18.123:8080/arcgis_js_api_v311/library/3.11/3.11/dijit/themes/claro/claro.css">
    <link rel="stylesheet" href="http://10.17.18.123:8080/arcgis_js_api_v311/library/3.11/3.11/dijit/themes/tundra/tundra.css">
    <link rel="stylesheet" href="http://10.17.18.123:8080/arcgis_js_api_v311/library/3.11/3.11/esri/css/esri.css"-->
    <link rel="stylesheet" href="../../css/dashboard.css"/>
    <link rel="stylesheet" href="../../css/loading.css"/>
    <script charset="utf-8" src="../../d3/js/d3.js"></script>
    <script src="../../d3/js/d3.layout.tree.js"></script>
    <script src="../../nvd3/js/nv.d3.min.js"></script>
    <script src="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/init.js"></script>
    <!--script src="http://10.17.18.123:8080/arcgis_js_api_v311/library/3.11/3.11/init.js"></script-->
    <script src="../../js/dashboard-filter.js"></script>
    <!--script src="../../js/dashboard-map.js"></script>
    <script src="../../js/dashboard-gauge.js"></script-->
    <script src="../../js/dashboard-parameter.js"></script>
    <!--script src="../../js/dashboard-graphic.js"></script-->
    <script src="../../js/dashboard-report.js"></script>
    <script src="../../js/dashboard.js"></script>
    <script src="../../js/loading.js"></script>
  </head>
<body class="claro">
  <div id="filter">
    <div id="filter-periode" class="dropdown">
      PERIODE&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu" data-var="periode"></ul>
    </div>
    <div id="filter-company" class="dropdown">
      COMP&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;
      </span>
      <ul class="dropdown-menu dropdown-menu-right" data-var="company"></ul>
    </div>
    <div id="filter-lob" class="dropdown">
      LOB&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;
      </span>
      <ul class="dropdown-menu dropdown-menu-right" data-var="lob"></ul>
    </div>
    <div id="filter-region" class="dropdown">
      AREA&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu" data-var="region"></ul>
    </div>
    <div id="filter-branch" class="dropdown">
      BRANCH&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu" data-var="branch"></ul>
    </div>
    <div id="filter-department" class="dropdown">
      DEPT&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu" data-var="dept"></ul>
    </div>
  </div>
  <div id="dashboard" class="container">
    <div class="row">
      <div class="col-md-1" hidden>
        <div>
          <div id="map-title" class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming" title="Zoom"/>
            <span>BRANCH MAPS</span>
            <div class="dropdown">
              <img src="../../img/gear.png" alt="Option" title="Option" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"/>
              <ul class="dropdown-menu dropdown-menu-right">
                <li><a href="#"><img src="../../img/icon/AllPin1LargeB.png" alt="Green"/><span>&nbsp;All Grade</span></a></li>
                <li><a href="#"><img src="../../img/icon/GreenPin1LargeB.png" alt="Green"/><span>&nbsp;Satisfactory</span></a></li>
                <li><a href="#"><img src="../../img/icon/YellowPin1LargeB.png" alt="Yellow"/><span>&nbsp;Need Improve.</span></a></li>
                <li><a href="#"><img src="../../img/icon/RedPin1LargeB.png" alt="Red"/><span>&nbsp;Unsatisfactory</span></a></li>
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
      <div class="col-md-offset-5 col-md-6" hidden>
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
              </ul>
            </div>
          </div>
          <div id="parameter" class="content-dashboard">
            <ul class="list-unstyled"></ul>
          </div>
        </div>
      </div>
      <div class="col-md-offset-5 col-md-6" hidden>
        <div>
          <div class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming" title="Zoom"/>
            <span>TREND</span>
            <img src="../../img/gear.png" alt="Option" title="Option"/>
          </div>
          <div id="graphic" class="content-dashboard">
            <div id="chart-trend"><svg></svg></div>            
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="mdl-dashboard" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="modal-title-label">
            <span><span class="glyphicon glyphicon-info-sign"></span>&nbsp;Information</span>
          </h4>
        </div>
        <div class="modal-body"></div>
        <!--div class="modal-footer">
          <button id="btn-ok" type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
        </div-->
      </div>
    </div>
  </div>
  <div class="modal fade" id="mdl-kci-report" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h3 class="modal-title">
            <img src="../../img/report.png" alt="Report"/><span>&nbsp;KCI REPORT</span>
          </h3>
        </div>
        <div class="modal-body">
          <table>
            <tbody>
              <tr>
                <td>Periode</td>
                <td id="report-periode">                  
                  <div class="dropdown">            
                    <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                    <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>Scope of Report</td>
                <td id="report-scope">                  
                  <div class="dropdown">            
                    <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      Function Parameter&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>
                    </span>
                    <ul class="dropdown-menu dropdown-menu-left scrollable-menu">
                      <li data-code="fp">Function Parameter</li>
                      <li data-code="ap">Area Parameter</li>
                      <li data-code="bp">Branch Parameter</li>
                    </ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>Area</td>
                <td id="report-area">                  
                  <div class="dropdown">            
                    <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                    <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>Branch</td>
                <td id="report-branch">                  
                  <div class="dropdown">            
                    <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                    <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>Company</td>
                <td id="report-company">                  
                  <div class="dropdown">            
                    <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
                    <ul class="dropdown-menu dropdown-menu-left scrollable-menu"></ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>Department</td>
                <td id="report-dept">                  
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
          <button id="btn-ok" type="button" class="btn btn-primary" data-dismiss="modal">Print</button>
        </div>
      </div>
    </div>
  </div>
  <div id="gauge-detail">
    <span class="glyphicon glyphicon-remove-circle close-btn" title="Close"></span>
    <div id="kci-detail">
      <table>
        <tbody></tbody>      
      </table>
    </div>
    <div id="kci-parent">
      <span></span>
    </div>
  </div>
  <div class="div-cover"></div>
  <div id="ball-stage">
    <div class="redball"></div>
    <div class="greenball"></div>
    <div class="yellowball"></div>
  </div>
  <div id="text-stage">
    Page loading, please wait...
  </div>
</body>
</html>
