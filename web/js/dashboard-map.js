/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {

  // variable
  var currIdxMap = 0;
  var prevBranchId = "00",
      prevDataBranch;
  
  showCover(true,true);
  
  // map object initialization
  var map, dialog, isPos = false;
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
    $.get("../../apps/data/mapbranch/"+currIdxMap,{
        periode: periode,
        coyId: company,
        bussUnit: lob,
        areaId: region,
        officeId: branch,
        deptId: dept},
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

        // set map rest layer
        //var myTiledMapServiceLayer = new ArcGISTiledMapServiceLayer("http://10.17.18.32:6080/arcgis/rest/services/gis/BASEMAP_HERE/MapServer");
        var myTiledMapServiceLayer = /*null;//*/new ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
        //ArcGISDynamicMapServiceLayer
        //ArcGISImageServiceLayer

        // create map  
        initMap();

        // load map
        map.on("load", mapLoaded);

        function mapLoaded(){

          // initialize map data
          mapInitData();

          // create new dialog    
          if(!dialog)
            dialog = new TooltipDialog({
              id: "tooltipDialog",
              style: "position: absolute; z-index:10; text-align: center; opacity: 0.6; font-size: 11px"
            });
          dialog.startup();          

          // popup on hover
          map.graphics.on("mouse-over", function(evt) {
            var t = "";
            if(isPos) {
              t = "<div style='color: red'>" +
                    "<b>${title}</b><br>"+
                    "Currently unavailable"+
                  "</div>";
            } else {
              t = "<div>"+
                    "<b>${title}</b><br>"+
                    "<b>LAST AUDIT</b>: ${lastAudit}</br>"+
                    "<b>GRADE</b>: ${grade}</br>"+
                    "<b>P1</b>: ${p1:NumberFormat}<br>"+
                    "<b>P2</b>: ${p2:NumberFormat}<br>"+
                    "</div>";
            }
            var content = Lang.substitute(evt.graphic.attributes,t);
            dialog.setContent(content);
            //domStyle.set(dialog.domNode, "opacity", 0.85);
            Popup.open({
              popup: dialog,
              x: evt.pageX,
              y: evt.pageY
            });
          });

          // close dialog on mouse out
          map.graphics.on("mouse-out", function() {
            Popup.close(dialog);
          }); 

          // redraw symbol on click
          map.graphics.on("click", function(evt) {
            if(isPos) {
              points = data;
              isPos = false;
              mapInitData();
            } else {  
              showCover(true,true);
              isPos = true;
              var currBranchId = evt.graphic.attributes.poiId;
              if(currBranchId === prevBranchId) {
                points = prevDataBranch;
                mapInitData();
              } else {
                $.get("../../apps/data/mappos/"+currBranchId,{},
                  function(datapos,status) {
                  if(status === "success") {
                    prevDataBranch = points = datapos;
                    prevBranchId = currBranchId;
                    mapInitData();
                  } else {
                    alert("Generate pos map unsuccessfully: status = " + status);
                    showCover(false,false);
                  }
                });
              }
            }
          }); 

          // custome zoom in button
          $("button#custom-zoom-in-btn").click(function() {
            map.setLevel(map.getLevel()+1);
          });

          // custome zoom out button
          $("button#custom-zoom-out-btn").click(function() {
            map.setLevel(map.getLevel()-1);
          });

          // customize map attribute and events
          //map.disablePan();
          map.hideZoomSlider();
          map.graphics.enableMouseEvents();
        }

        function mapInitData() {
          // clear current symbol
          if(map.graphics)
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
            var red = new PictureMarkerSymbol(picBaseUrl + point.warnaIcon, 30, 30).setOffset(0, 0);
            var graphMarker = new Graphic(new Point([point.longitude,point.latitude]), red);
            graphMarker.setAttributes(attr);
            map.graphics.add(graphMarker);
          });   
          if(!isZoom)
            showCover(false,false); 
        }

        // grade level click
        $(".row>div>div:has(div#map)>div.title-dashboard>div.dropdown>ul>li").click(function() {
          showCover(true,true);
          if($(this).index() === 4) {
            var currFindRest = $(this).find("a>span");
            if(currFindRest.text().trim() === "To Local Rest") {
              currFindRest.text(" To Online Rest");
              map.removeLayer(myTiledMapServiceLayer);
              myTiledMapServiceLayer = new ArcGISTiledMapServiceLayer("http://10.17.18.32:6080/arcgis/rest/services/gis/BASEMAP_HERE/MapServer");
              map.addLayer(myTiledMapServiceLayer);
            } else {
              currFindRest.text(" To Local Rest");
              map.removeLayer(myTiledMapServiceLayer);
              myTiledMapServiceLayer = new ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
              map.addLayer(myTiledMapServiceLayer);
            }            
          } else {
            currIdxMap = $(this).index();
          }
          $.get("../../apps/data/mapbranch/"+currIdxMap,{
            periode: periode,
            coyId: company,
            bussUnit: lob,
            areaId: region,
            officeId: branch,
            deptId: dept},
            function(databranch,status) {
            if(status === "success") {
              data = points = databranch;
              isPos = false;
              mapInitData();
            } else {
              alert("Generate branch map unsuccessfully: status = " + status);
              showCover(false,false);
            }
          });
        });

        // change filter
        $("div#filter>div>ul").on("click","li",function() {   
          if(window["prev"+$(this).parent("ul").data("var")] !== $(this).data("code")) {
            showCover(true,true);
            $.get("../../apps/data/mapbranch/"+currIdxMap,{
              periode: periode,
              coyId: company,
              bussUnit: lob,
              areaId: region,
              officeId: branch,
              deptId: dept},
              function(datafilter,status) {
              if(status === "success") {
                data = points = datafilter;
                isPos = false;
                mapInitData();
              } else {
                alert("Generate branch map unsuccessfully: status = " + status);
                showCover(false,false);
              }
            });
          }
        });

        // zoom section
        $("div:has(div#map)>div.title-dashboard>img:first-child").click(function() {
          zoomInObj($(this).parent("div").siblings("div"));

          // recreate map  
          map.destroy(); 
          initMap();
          mapLoaded();
        });

        //
        $("div:has(div#map)").on("click","span.close-btn",function() {
          zoomOutObj($(this).siblings("div.content-dashboard"));

          // recreate map  
          map.destroy();
          initMap();
          mapLoaded();
        });

        function initMap() {
          // create map  
          map = new Map("map",{
            //basemap: "satellite",
            center: [117,-2.2],//[sumLong/pointsLength, sumLat/pointsLength],
            zoom: 4,
            logo: false,
            showAttribution: false,
            showLabels: true
          });

          // set map rest layer
          map.addLayer(myTiledMapServiceLayer);
        }

      } else {
        alert("Generate branch map unsuccessfully: status = " + status);
        showCover(false,false);
      }
    });
  });
});
