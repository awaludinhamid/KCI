  /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  /*These lines are all chart setup.  Pick and choose which chart features you want to utilize. */
  nv.addGraph(function() {
    var chart = nv.models.lineChart()
                  .margin({left: 100})  //Adjust chart margins to give the x-axis some breathing room.
                  .useInteractiveGuideline(true)  //We want nice looking tooltips and a guideline!
                  .duration(4000)  //how fast do you want the lines to transition?
                  .showLegend(true)       //Show the legend, allowing users to turn on/off line series.
                  .showYAxis(true)        //Show the y-axis
                  .showXAxis(true)        //Show the x-axis
    ;

    chart.xAxis     //Chart x-axis settings
        .axisLabel('Months')
        .tickFormat(d3.format(',r'))
;

    chart.yAxis     //Chart y-axis settings
        .axisLabel('Grade')
        .tickFormat(d3.format('.02f'))
    ;

    /* Done setting the chart up? Time to render it!*/
    var myData = sinAndCos();   //You need data...

    d3.select('#chart-trend>svg')    //Select the <svg> element you want to render the chart in.   
        .datum(myData)         //Populate the <svg> element with chart data...
        .call(chart);          //Finally, render the chart!

    //Update the chart when window resizes.
    nv.utils.windowResize(function() { chart.update() });
    return chart;
  });
  /**************************************
   * Simple test data generator
   */
  function sinAndCos() {
    var sin = [],sin2 = [],
        cos = [];

    //Data is represented as an array of {x,y} pairs.
    for (var i = 0; i <= 12; i++) {
      sin.push({x: i, y: Math.sin(i*5/10)+2});
      sin2.push({x: i, y: Math.sin(i*5/10) *0.25 + 2.5});
      cos.push({x: i, y: .5 * Math.cos(i*5/10)+2});
    }

    //Line chart data should be sent as an array of series objects.
    return [
      {
        values: sin,      //values - represents the array of {x,y} data points
        key: 'FIFGROUP', //key  - the name of the series.
        color: '#ff7f0e'  //color - optional: choose your own line color.
      },
      {
        values: cos,
        key: 'Area',
        color: '#2ca02c'
      },
      {
        values: sin2,
        key: 'Branch',
        color: '#7777ff',
        //area: true      //area - set to true if you want this line to turn into a filled area chart.
      }
    ];
  } 
});
