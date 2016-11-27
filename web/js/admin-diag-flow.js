/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  //
  var root, tree, vis, diagonal, tooltip, linGradRed, linGradGreen, linGradYellow, linGradBlue, linGradGrey; //tree var holder
  var squareWidth = 160, //square side width
      squareHeight = 20, //square side height
      lLine = 190; //hierarchy line length
  var m = [20, 16, 20, 16], //svg padding to tree(up,left,down,right)
        minW = 600, //min tree diagram width
        minH = 300, //min tree diagram height
        w = 0, //tree diagram width
        h = 0, //tree diagram height
        cnt = 0; //node count
  //
  var modalDiagFlow = "div#mdl-diag-flow";
        
  //
  $("div#mdl-param-audit ").on("click","img#img-show-hie", function() {
    var parentTr = $(this).parent("td").parent("tr");
    showJson(parentTr.data("dept-id"),parentTr.data("id"));
  });
  
  //show data tree through json
  function showJson(deptId,parameterId) {
    showCover(true,true);
    //generate json, if current json different from previous one and GET status is success then reload data
    var execFunc = function() {
      $.get("../../apps/data/diagramflow/"+deptId,{},function(data,status) {
        if(status === "success") {
          updateTree(data,deptId); 
          $(modalDiagFlow).find(".modal-title>span").text("Diagram Flow Process " + parameterId);
          $(modalDiagFlow).modal("show");
        } else {
          alert("Loading diagram flow unsuccessfully: status = " + status);
        }
      }).fail(function(d) {
        alert("Failed to load diagram flow: ");
      }).error(function(d) {
        alert("Error loading diagram flow: ");
      }).always(function(d) {
        showCover(true,false);
      });
    };
    checkCurrSessAndExec({},execFunc);
  }

  //update tree object and data view
  function updateTree(treeLinked,deptId) {
    //clean tree contanier and create a new one
    d3.selectAll(modalDiagFlow+ " div#body").remove();
    $(modalDiagFlow + " .modal-body").append("<div id='body'></div>");
    $(modalDiagFlow+ " .modal-body>div#flow-data>span").text("FLOW DATA " + deptId + "");
    //save json data to temporer var, init variable needed (including window size)
    //show first children only, update view, and restore scroll position
    root = treeLinked;
    initTreeVariable(modalDiagFlow);
    update(root);
  }

  //update tree object
  function update(source) {
    var duration = d3.event && d3.event.altKey ? 5000 : 500;  //node transition duration
    //
    //Compute the new tree layout
    var nodes = tree
      .nodes(root)
      .reverse();
      
    //Normalize for fixed-depth
    nodes.forEach(function(d) {d.y = w - ((d.depth + 1) * lLine);}); //reverse from nodes.forEach(function(d) {d.y = d.depth * lLine;});
    
    // Update the nodes?
    var node = vis
      .selectAll("g.node")
      .data(nodes, function(d) {return d.id || (d.id = ++cnt);});
      
    // Enter any new nodes at the parent's previous position.
    var nodeEnter = node
      .enter()
      .append("svg:g")
      .attr("class", "node")
      .attr("transform", function(d) {return "translate(" + source.y0 + "," + source.x0 + ")";});

    //rect nodes and its properties
    nodeEnter
      .append("svg:rect")
      .attr("width", squareWidth)
      .attr("height", squareHeight)
      .attr("rx","2");
    
    //text nodes
    nodeEnter
      .append("svg:text")
      .text(function(d) {return d.name;})
      .attr("x",squareWidth/2)
      .attr("y",squareHeight/2 + 2)
      .attr("alignment-baseline","middle")
      .attr("text-anchor","middle");
      
    //arrow
    nodeEnter
      .append("svg:polygon")
      .attr("points","0,0 10,10 0,20")
      .style("opacity",function(d) {return (d.lvl === 5 || d.lvl === 6) ? "0" : "1";})
      .attr("transform","translate(" + (lLine - 10) + ")");

    // Transition nodes to their new position.
    node.transition()
      .duration(duration)
      .attr("transform", function(d) {return "translate(" + d.y + "," + d.x + ")";});

    // Transition exiting nodes to the parent's new position.
    var nodeExit = node.exit().transition()
      .duration(duration)
      .attr("transform", function(d) {return "translate(" + source.y + "," + source.x + ")";})
      .remove();
      
    //exit rect properties
    nodeExit.select("rect")
      .attr("width", 1e-6)
      .attr("height", 1e-6);

    // Update the links?
    var link = vis.selectAll("path.link")
      .data(tree.links(nodes), function(d) {return d.target.id;});
    // Enter any new links at the parent's previous position.
    link.enter().insert("svg:path", "g")
      .attr("class", "link")
      .attr("d", function(d) {
              var o = {x: source.x0, y: source.y0};
              return diagonal({source: o, target: o});
      })
      .transition()
      .duration(duration)
      .attr("d", diagonal);
    // Transition links to their new position.
    link.transition()
      .duration(duration)
      .attr("d", diagonal);
    // Transition exiting nodes to the parent's new position.
    link.exit().transition()
      .duration(duration)
      .attr("d", function(d) {
        var o = {x: source.x, y: source.y};
        return diagonal({source: o, target: o});
      })
      .remove();

    // Stash the old positions for transition.
    nodes.forEach(function(d) {
      d.x0 = d.x;
      d.y0 = d.y;
      });
  }

  //toggle children between expand or collapse.
  function toggle(d) {
    if (d.children) {
      d._children = d.children;
      d.children = null;
    } else {
      d.children = d._children;
      d._children = null;
    }
  }

  //init tree variable
  function initTreeVariable(modalDiagFlow) {
      //init line
      diagonal = d3
        .svg
        .diagonal()
        .projection(function(d) {return [d.y, d.x];});
      //init tooltip
      tooltip = d3
        .select(modalDiagFlow+" div#body")
        .append("div") //declare the tooltip div
        .attr("class", "tooltip") //apply the 'tooltip' class
        .style("opacity", 0);
      //sizing tree
      var depth = 0;
      var depthTemp = 0;
      setDepth(root);
      var stack = root.children.length;
      var tempW = lLine * depth; //(squareWidth+lLine)*depth;
      var tempH = stack*(m[0]+m[2]);
      w =  tempW > minW ? tempW : minW;
      h = tempH > minH ? tempH : minH;
      //x,y node start position
      root.x0 = h / 2;
      root.y0 = 0;
      //created tree object
      tree = d3
        .layout
        .tree()
        .size([h, w]);
      //created svg object on tree (container)
      vis = d3
        .select(modalDiagFlow+" div#body")
        .append("svg:svg")
        .attr("width", w + m[1] + m[3])
        .attr("height", h + m[0] + m[2])
        .append("svg:g")
        .attr("transform", "translate(" + m[3] + "," + m[0] + ")");
      //gradient color
      linGradRed = vis
        .append("svg:defs")
        .append("svg:linearGradient")
        .attr("id","red")
        .attr("x1","70%")
        .attr("y1","80%")
        .attr("x2","10%")
        .attr("y2","0%")
        .attr("spreadMethod", "pad");
      linGradRed
        .append("svg:stop")
        .attr("offset","0%")
        .attr("style","stop-color:#c00;stop-opacity:1");
      linGradRed
        .append("svg:stop")
        .attr("offset","100%")
        .attr("style","stop-color:#fdd;stop-opacity:1");
      linGradGreen = vis
        .append("svg:defs")
        .append("svg:linearGradient")
        .attr("id","green")
        .attr("x1","70%")
        .attr("y1","80%")
        .attr("x2","10%")
        .attr("y2","0%")
        .attr("spreadMethod", "pad");
      linGradGreen
        .append("svg:stop")
        .attr("offset","0%")
        .attr("style","stop-color:#090;stop-opacity:1");
      linGradGreen
        .append("svg:stop")
        .attr("offset","100%")
        .attr("style","stop-color:#dfd;stop-opacity:1");
      linGradYellow = vis
        .append("svg:defs")
        .append("svg:linearGradient")
        .attr("id","yellow")
        .attr("x1","70%")
        .attr("y1","80%")
        .attr("x2","10%")
        .attr("y2","0%")
        .attr("spreadMethod", "pad");
      linGradYellow
        .append("svg:stop")
        .attr("offset","0%")
        .attr("style","stop-color:#ee0;stop-opacity:1");
      linGradYellow
        .append("svg:stop")
        .attr("offset","100%")
        .attr("style","stop-color:#ffd;stop-opacity:1");
      linGradBlue = vis
        .append("svg:defs")
        .append("svg:linearGradient")
        .attr("id","blue")
        .attr("x1","70%")
        .attr("y1","80%")
        .attr("x2","10%")
        .attr("y2","0%")
        .attr("spreadMethod", "pad");
      linGradBlue
        .append("svg:stop")
        .attr("offset","0%")
        .attr("style","stop-color:#009;stop-opacity:1");
      linGradBlue
        .append("svg:stop")
        .attr("offset","100%")
        .attr("style","stop-color:#ddf;stop-opacity:1");
      linGradGrey = vis
        .append("svg:defs")
        .append("svg:linearGradient")
        .attr("id","grey")
        .attr("x1","70%")
        .attr("y1","80%")
        .attr("x2","10%")
        .attr("y2","0%")
        .attr("spreadMethod", "pad");
      linGradGrey
        .append("svg:stop")
        .attr("offset","0%")
        .attr("style","stop-color:#444;stop-opacity:1");
      linGradGrey
        .append("svg:stop")
        .attr("offset","100%")
        .attr("style","stop-color:#ddd;stop-opacity:1");
      //grab the most length of nodes
      function setDepth(obj) {
        if (obj.children) {
            obj.children.forEach(function (d) {
                depthTemp++;
                setDepth(d);
                if (depthTemp > depth) {
                    depth = depthTemp;
                }
                depthTemp = 0;
            });
        }
        depthTemp++;
      }
  }

});
