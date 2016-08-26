/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {

  // show user menu on page loading
  $("#side-menu>div.user-menu").each(function() {
    $(this).show();
  });
  
  // show home/welcome link
  $("div#user-menu>div.dropdown>ul>li#home-btn").show();
  
  // show unavailable message
  $("div#side-menu>div, div.title-dashboard>img, div#gauge>svg").click(function() {
    $("div#mdl-dashboard").modal("show");
  });
  
  // map object initialization
  var map, dialog;
  require([
    "esri/map",
    "esri/layers/ArcGISTiledMapServiceLayer",
    "esri/graphic",
    "esri/lang",
    "esri/Color",
    
    "esri/geometry/Point", 
    "esri/symbols/SimpleMarkerSymbol",
    "esri/symbols/PictureMarkerSymbol",
    "esri/symbols/TextSymbol",
    "esri/symbols/Font",
    
    "dijit/TooltipDialog",
    "dijit/popup",
    
    "dojo/_base/array",
    "dojo/dom-style",
    "dojo/domReady!"
  ], function(
    Map, ArcGISTiledMapServiceLayer, Graphic, Lang, Color, Point,
    SimpleMarkerSymbol, PictureMarkerSymbol, TextSymbol, Font,
    TooltipDialog, Popup,
    arrayUtils, domStyle
  ) {
  
    // load init data
    $.get("../../apps/data/mapbranch",{},
      function(data,status) {
      if(status === "success") {
        var points = data;
        
        // calculate center of map
        /*var pointsLength = points.length;
        var sumLong = 0;
        var sumLat = 0;
        arrayUtils.forEach(points, function(point) {
          sumLong += point.longitude;
          sumLat += point.latitude;      
        });*/
        
        // create map  
        map = new Map("map",{
          //basemap: "satellite",
          center: [117,-1.5],//[sumLong/pointsLength, sumLat/pointsLength],
          zoom: 6,
          logo: false,
          showAttribution: false,
          showLabels: true
        });
        
        // set map rest layer
        var myTiledMapServiceLayer = new ArcGISTiledMapServiceLayer("http://10.17.18.32:6080/arcgis/rest/services/gis/BASEMAP_HERE/MapServer");
        //var myTiledMapServiceLayer = new ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
        //ArcGISDynamicMapServiceLayer
        //ArcGISImageServiceLayer
        map.addLayer(myTiledMapServiceLayer);
        
        // load map
        map.on("load", mapLoaded);

        function mapLoaded(){
          // clear current symbol
          map.graphics.clear();
          
          // traverse each points
          arrayUtils.forEach(points, function(point) {
            
            // attribute for dialog info
            var attr = {
              "title":point.poiName,
              "lastAudit":point.lastAudit,
              "grade":point.grade,
              "p1":point.measurementP1,
              "p2":point.measurementP2,
              "poiId":point.poiId
            };
            
            // marker symbol base on picture 
            var picBaseUrl = "../../img/icon/"; //"https://static.arcgis.com/images/Symbols/Shapes/";
            var red = new PictureMarkerSymbol(picBaseUrl + point.warnaIcon, 60, 60).setOffset(0, 15);
            var graphMarker = new Graphic(new Point([point.longitude,point.latitude]), red);
            graphMarker.setAttributes(attr);
            map.graphics.add(graphMarker);
          });
          
          // create new dialog if not exist
          if(!dialog) {
            dialog = new TooltipDialog({
              id: "tooltipDialog",
              style: "position: absolute; width: 250px; z-index:10; text-align: center; opacity: 0.6"
            });
            dialog.startup();
          }
        
          // popup on hover
          map.graphics.on("mouse-over", function(evt) {
            var t = "<div style='color: #222244'>"+
                    "<b>${title}</b><br>"+
                    "<b>LAST AUDIT</b>: ${lastAudit}</br>"+
                    "<b>GRADE</b>: ${grade}</br>"+
                    "<b>P1</b>: ${p1:NumberFormat}<br>"+
                    "<b>P2</b>: ${p2:NumberFormat}<br>"+
                    "</div>";
            var content = Lang.substitute(evt.graphic.attributes,t);
            dialog.setContent(content);
            //domStyle.set(dialog.domNode, "opacity", 0.85);
            Popup.open({
              popup: dialog,
              x: evt.pageX,
              y: evt.pageY
            });
          });
          
          // redraw symbol on click
          map.graphics.on("click", function(evt) {
            alert(evt.graphic.attributes.title);
            $.get("../../apps/data/mappos/"+evt.graphic.attributes.poiId,{},
              function(data,status) {
              if(status === "success") {
                points = data;
                mapLoaded();
              } else {
                alert("Generate periode unsuccessfully: status = " + status);
              }
            });
          });
          
          // close dialog on mouse out
          map.graphics.on("mouse-out", function() {
            Popup.close(dialog);
          });
          
          // customize map attribute and events
          //map.disablePan();
          //map.hideZoomSlider();
          map.graphics.enableMouseEvents();
        }
      } else {
        alert("Generate periode unsuccessfully: status = " + status);
      }
    });
  });
});
