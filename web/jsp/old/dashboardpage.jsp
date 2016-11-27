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
    <!--link rel="stylesheet" href="https://js.arcgis.com/3.16/dojox/widget/ColorPicker/ColorPicker.css"-->
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/esri/css/esri.css">
    <link rel="stylesheet" href="../../css/dashboard.css"/>
    <script charset="utf-8" src="../../d3/js/d3.js"></script>
    <script src="../../d3/js/d3.layout.tree.js"></script>
    <script src="../../nvd3/js/nv.d3.min.js"></script>
    <script src="http://localhost:8080/arcgis_js_api_v311/library/3.11/3.11/init.js"></script>
    <script src="../../js/dashboard-filter.js"></script>
    <script src="../../js/dashboard-gauge.js"></script>
    <script src="../../js/dashboard-parameter.js"></script>
    <script src="../../js/dashboard-graphic.js"></script>
    <script src="../../js/dashboard.js"></script>
    <!--script>
      // helpful for understanding dojoConfig.packages vs. dojoConfig.paths:
      // http://www.sitepen.com/blog/2013/06/20/dojo-faq-what-is-the-difference-packages-vs-paths-vs-aliases/
      // this script must be executed before arcgis library loaded
      var path = location.pathname.split("/");
      var dojoConfig = { 
        paths: {
          extras: path[0] + "/" + path[1] + "/extras"
        }
      };
    </script-->
    <!--script type="text/javascript">
      dojo.require("esri.map");
      function init() {
        var myMap = new esri.Map("map");
        //note that if you do not have public Internet access then you will need to point this url to your own locally accesible cached service.
        var myTiledMapServiceLayer = new esri.layers.ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/ArcGIS/rest/services/NGS_Topo_US_2D/MapServer");
        myMap.addLayer(myTiledMapServiceLayer);
      }
      dojo.addOnLoad(init);
    </script-->
  </head>
<body class="claro">
      <!--div class="container" data-dojo-type="dijit/layout/BorderContainer" 
           data-dojo-props="design:'headline',gutters:false" 
           style="height: 50%; width: 50%">        
        <div class="row">
          <div class="col-md-6"></div>
          <div class="col-md-6" id="map" 
               data-dojo-type="dijit/layout/ContentPane" 
               data-dojo-props="region:'center'"> 
          </div>
        </div>
      </div-->
  <div id="filter">
    <div id="filter-periode" class="dropdown">
      PERIODE&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu"></ul>
    </div>
    <div id="filter-company" class="dropdown">
      COMP&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>
      </span>
      <ul class="dropdown-menu dropdown-menu-right"></ul>
    </div>
    <div id="filter-lob" class="dropdown">
      LOB&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>
      </span>
      <ul class="dropdown-menu dropdown-menu-right"></ul>
    </div>
    <div id="filter-region" class="dropdown">
      AREA&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu"></ul>
    </div>
    <div id="filter-branch" class="dropdown">
      BRANCH&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu"></ul>
    </div>
    <div id="filter-department" class="dropdown">
      DEPT&nbsp;&nbsp;
      <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ALL&nbsp;<span class="glyphicon glyphicon-triangle-bottom"></span>
      </span>
      <ul class="dropdown-menu dropdown-menu-right scrollable-menu"></ul>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-1">
        <div>
          <div class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming"/>
            <span>BRANCH MAPS</span>
            <img src="../../img/gear.png" alt="Option"/>
          </div>
          <div id="map" class="content-dashboard" style="background-color: rgb(182,199,214); opacity: 0.8"></div>
        </div>
      </div>
      <div class="col-md-offset-5 col-md-6">
        <div>
          <div class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming"/>
            <span>KCI VALUES</span>
            <img src="../../img/gear.png" alt="Option"/>
          </div>
          <div id="gauge" class="content-dashboard">
            <img src="../../img/gauge.png" alt="Gauge" style="width: 190px; height: 190px"/>
            <img src="../../img/gauge.png" alt="Gauge" style="width: 160px; height: 160px"/>
            <img src="../../img/gauge.png" alt="Gauge" style="width: 130px; height: 130px"/>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-1">
        <div>
          <div class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming"/>
            <span>PARAMETER</span>
            <img src="../../img/gear.png" alt="Option"/>
          </div>
          <div id="parameter" class="content-dashboard" style="overflow-y: scroll; font-size: 16px; color: #fafafa">
            <ul class="list-unstyled"></ul>
          </div>
        </div>
      </div>
      <div class="col-md-offset-5 col-md-6">
        <div>
          <div class="title-dashboard">
            <img src="../../img/zooming.png" alt="Zooming"/>
            <span>TREND</span>
            <img src="../../img/gear.png" alt="Option"/>
          </div>
          <div id="graphic" class="content-dashboard">
            <div id="chart-trend"><svg></svg></div>            
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="mdl-message" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">
            <span><span class="glyphicon glyphicon-info-sign"></span>&nbsp;Information</span>
          </h4>
        </div>
        <div class="modal-body">
          <span>Currently unavailable..</span>
        </div>
        <div class="modal-footer">
          <button id="btn-ok" type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
