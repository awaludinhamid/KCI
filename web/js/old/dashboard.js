/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  /*require(["esri/map", "dojo/domReady!"], function(Map) {
    var map = new Map("map", {
      center: [106.8451300, -6.2146200],
      zoom: 9,
      basemap: "topo"
    });
  });*/

  $("#side-menu>div.user-menu").each(function() {
    $(this).show();
  });
  $("div#user-menu>div.dropdown>ul>li#home-btn").show();
  //alert("ok");
  $("div#side-menu>div, div.title-dashboard>img, div#gauge>svg").click(function() {
    $("div#mdl-dashboard").modal("show");
  });
  
  
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
  
    $.get("../../apps/data/mapbranch",{},
      function(data,status) {
      if(status === "success") {
        var points = data;
                /*[
                  ["Jakarta",106.84513,-6.21462,20,14,2.58,"RedPin1LargeB.png"],
                  ["Bandung",107.6191,-6.9175,16,15,1.17,"GreenPin1LargeB.png"],
                  ["Semarang",110.4203,-6.9932,10,14,1.53,"YellowPin1LargeB.png"],
                  ["B. Lampung",105.25803,-5.42544,12,11,1.21,"GreenPin1LargeB.png"],
                  ["Yogyakarta",110.36083,-7.78278,18,14,1.61,"YellowPin1LargeB.png"],
                  ["Surabaya",112.75083,-7.24917,24,18,2.52,"RedPin1LargeB.png"],
                  ["Cirebon",108.557,-6.7063,12,10,1.43,"GreenPin1LargeB.png"],
                  ["Malang",112.6304,-7.9797,13,12,1.24,"GreenPin1LargeB.png"],
                  ["Tegal",109.1402,-6.8694,18,14,1.87,"YellowPin1LargeB.png"],
                  ["Tangerang",106.63,-6.17806,22,19,2.61,"RedPin1LargeB.png"],
                  ["Surakarta",110.83167,-7.55611,14,11,1.14,"GreenPin1LargeB.png"]
        ];*/
        var pointsLength = points.length;
        var sumLong = 0;
        var sumLat = 0;
        arrayUtils.forEach(points, function(point) {
          sumLong += point.longitude;
          sumLat += point.latitude;      
        });
        map = new Map("map",{
          //basemap: "satellite",
          center: [117,-1.5],//[sumLong/pointsLength, sumLat/pointsLength],
          zoom: 6,
          logo: false,
          showAttribution: false,
          showLabels: true
        });
        var myTiledMapServiceLayer = new ArcGISTiledMapServiceLayer("http://10.17.18.32:6080/arcgis/rest/services/gis/BASEMAP_HERE/MapServer");
        //var myTiledMapServiceLayer = new ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
        //ArcGISDynamicMapServiceLayer
        //ArcGISImageServiceLayer
        map.addLayer(myTiledMapServiceLayer);
        map.on("load", mapLoaded);

        function mapLoaded(){
          map.graphics.clear();
          // icon for manual marker symbol
          //var iconPath = "M24.0,2.199C11.9595,2.199,2.199,11.9595,2.199,24.0c0.0,12.0405,9.7605,21.801,21.801,21.801c12.0405,0.0,21.801-9.7605,21.801-21.801C45.801,11.9595,36.0405,2.199,24.0,2.199zM31.0935,11.0625c1.401,0.0,2.532,2.2245,2.532,4.968S32.4915,21.0,31.0935,21.0c-1.398,0.0-2.532-2.2245-2.532-4.968S29.697,11.0625,31.0935,11.0625zM16.656,11.0625c1.398,0.0,2.532,2.2245,2.532,4.968S18.0555,21.0,16.656,21.0s-2.532-2.2245-2.532-4.968S15.258,11.0625,16.656,11.0625zM24.0315,39.0c-4.3095,0.0-8.3445-2.6355-11.8185-7.2165c3.5955,2.346,7.5315,3.654,11.661,3.654c4.3845,0.0,8.5515-1.47,12.3225-4.101C32.649,36.198,28.485,39.0,24.0315,39.0z";
          //var iconPath = "M 20,-8 C 0,-35 50,-35 30,-8 L 25,0";
          arrayUtils.forEach(points, function(point) {
            //attribute
            var attr = {"title":point.poiName,"lastAudit":point.lastAudit,"grade":point.grade,"p1":point.measurementP1,"p2":point.measurementP2,"poiId":point.poiId};
            // marker symbol using esri object
            /*var symbol = new SimpleMarkerSymbol({
              "color": [255,255,255,64],
              "size": 12,
              "angle": -30,
              "xoffset": 0,
              "yoffset": 0,
              "type": "esriSMS",
              "style": "esriSMSCircle",
              "outline": {
                "color": [0,0,0,255],
                "width": 1,
                "type": "esriSLS",
                "style": "esriSLSSolid"
              }
            });*/
            // manual marker symbol
            /*var markerSymbol = new SimpleMarkerSymbol();
            markerSymbol.setPath(iconPath);
            markerSymbol.setColor(new Color("white"));
            markerSymbol.setOutline(null);
            markerSymbol.setSize(50);*/
            // marker symbol base on picture 
            var picBaseUrl = "../../img/icon/"; //"https://static.arcgis.com/images/Symbols/Shapes/";
            var red = new PictureMarkerSymbol(picBaseUrl + point.warnaIcon, 60, 60).setOffset(0, 15);
            var graphMarker = new Graphic(new Point([point.longitude,point.latitude]), red);
            graphMarker.setAttributes(attr);
            map.graphics.add(graphMarker);
            // text symbol
            /*var textSymbol = new TextSymbol(point[0]);
            var font = new Font();
            font.setSize("13pt");
            font.setWeight(Font.WEIGHT_BOLD);
            textSymbol.setFont(font);
            textSymbol.setColor(new Color("white"));
            textSymbol.setOffset(0,-15);
            var graphText = new Graphic(new Point([point[1],point[2]]), textSymbol);
            graphText.setAttributes(attr);
            map.graphics.add(graphText);*/
          });
          if(!dialog) {
            dialog = new TooltipDialog({
              id: "tooltipDialog",
              style: "position: absolute; width: 250px; z-index:10; text-align: center; opacity: 0.6"
            });
            dialog.startup();
          }
        
          map.graphics.on("mouse-over", function(evt) {
            //alert(JSON.stringify(evt.graphic.toJson()));
            //alert(JSON.stringify(evt.graphic.attributes));
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

          //map.disablePan();
          //map.hideZoomSlider();
          map.graphics.enableMouseEvents();
          map.graphics.on("mouse-out", closeDialog);

          function closeDialog() {
            //map.graphics.clear();
            Popup.close(dialog);
          }
        }
      } else {
        alert("Generate periode unsuccessfully: status = " + status);
      }
    });
  });
  // helpful for understanding dojoConfig.packages vs. dojoConfig.paths:
  // http://www.sitepen.com/blog/2013/06/20/dojo-faq-what-is-the-difference-packages-vs-paths-vs-aliases/
  /*var map;
  require([
    "dojo/parser", 
    "dojo/ready",
    "dojo/_base/array",
    "esri/Color",
    "dojo/dom-style",
    "dojo/query",

    "esri/map", 
    "esri/request",
    "esri/graphic",
    "esri/geometry/Extent",

    "esri/symbols/SimpleMarkerSymbol",
    "esri/symbols/SimpleFillSymbol",
    "esri/symbols/PictureMarkerSymbol",
    "esri/renderers/ClassBreaksRenderer",

    "esri/layers/GraphicsLayer",
    "esri/SpatialReference",
    "esri/dijit/PopupTemplate",
    "esri/geometry/Point",
    "esri/geometry/webMercatorUtils",

    "extras/ClusterLayer",

    "dijit/layout/BorderContainer", 
    "dijit/layout/ContentPane", 
    "dojo/domReady!"
  ], function(
    parser, ready, arrayUtils, Color, domStyle, query,
    Map, esriRequest, Graphic, Extent,
    SimpleMarkerSymbol, SimpleFillSymbol, PictureMarkerSymbol, ClassBreaksRenderer,
    GraphicsLayer, SpatialReference, PopupTemplate, Point, webMercatorUtils,
    ClusterLayer
  ) {
    ready(function() {
      parser.parse();

      var clusterLayer;
      var popupOptions = {
        "markerSymbol": new SimpleMarkerSymbol("circle", 20, null, new Color([0, 0, 0, 0.25])),
        "marginLeft": "20",
        "marginTop": "20"
      };
      map = new Map("map", {
        basemap: "oceans",
        center: [-117.789, 33.543],
        zoom: 12
      });

      map.on("load", function() {
        // hide the popup's ZoomTo link as it doesn't make sense for cluster features
        domStyle.set(query("a.action.zoomTo")[0], "display", "none");

        // get the latest 1000 photos from instagram/laguna beach
        var photos = esriRequest({
          url: "../../data/1000-photos.json",
          handleAs: "json"
        });
        photos.then(addClusters, error);
      });

      function addClusters(resp) {
        var photoInfo = {};
        var wgs = new SpatialReference({
          "wkid": 4326
        });
        photoInfo.data = arrayUtils.map(resp, function(p) {
          var latlng = new  Point(parseFloat(p.lng), parseFloat(p.lat), wgs);
          var webMercator = webMercatorUtils.geographicToWebMercator(latlng);
          var attributes = {
            "Caption": p.caption,
            "Name": p.full_name,
            "Image": p.image,
            "Link": p.link
          };
          return {
            "x": webMercator.x,
            "y": webMercator.y,
            "attributes": attributes
          };
        });

        // popupTemplate to work with attributes specific to this dataset
        var popupTemplate = new PopupTemplate({
          "title": "",
          "fieldInfos": [{
            "fieldName": "Caption",
            visible: true
          }, {
            "fieldName": "Name",
            "label": "By",
            visible: true
          }, {
            "fieldName": "Link",
            "label": "On Instagram",
            visible: true
          }],
          "mediaInfos": [{
            "title": "",
            "caption": "",
            "type": "image",
            "value": {
              "sourceURL": "{Image}",
              "linkURL": "{Link}"
            }
          }]
        });

        // cluster layer that uses OpenLayers style clustering
        clusterLayer = new ClusterLayer({
          "data": photoInfo.data,
          "distance": 100,
          "id": "clusters",
          "labelColor": "#fff",
          "labelOffset": 10,
          "resolution": map.extent.getWidth() / map.width,
          "singleColor": "#888",
          "singleTemplate": popupTemplate
        });
        var defaultSym = new SimpleMarkerSymbol().setSize(4);
        var renderer = new ClassBreaksRenderer(defaultSym, "clusterCount");

        var picBaseUrl = "https://static.arcgis.com/images/Symbols/Shapes/";
        var blue = new PictureMarkerSymbol(picBaseUrl + "BluePin1LargeB.png", 32, 32).setOffset(0, 15);
        var green = new PictureMarkerSymbol(picBaseUrl + "GreenPin1LargeB.png", 64, 64).setOffset(0, 15);
        var red = new PictureMarkerSymbol(picBaseUrl + "RedPin1LargeB.png", 72, 72).setOffset(0, 15);
        renderer.addBreak(0, 2, blue);
        renderer.addBreak(2, 200, green);
        renderer.addBreak(200, 1001, red);

        clusterLayer.setRenderer(renderer);
        map.addLayer(clusterLayer);

        // close the info window when the map is clicked
        map.on("click", cleanUp);
        // close the info window when esc is pressed
        map.on("key-down", function(e) {
          if (e.keyCode === 27) {
            cleanUp();
          }
        });
      }

      function cleanUp() {
        map.infoWindow.hide();
        clusterLayer.clearSingles();
      }

      function error(err) {
        console.log("something failed: ", err);
      }

      // show cluster extents...
      // never called directly but useful from the console 
      window.showExtents = function() {
        var extents = map.getLayer("clusterExtents");
        if ( extents ) {
          map.removeLayer(extents);
        }
        extents = new GraphicsLayer({ id: "clusterExtents" });
        var sym = new SimpleFillSymbol().setColor(new Color([205, 193, 197, 0.5]));

        arrayUtils.forEach(clusterLayer._clusters, function(c, idx) {
          var e = c.attributes.extent;
          extents.add(new Graphic(new Extent(e[0], e[1], e[2], e[3], map.spatialReference), sym));
        }, this);
        map.addLayer(extents, 0);
      };
    });
  });*/
});
