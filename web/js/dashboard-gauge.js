/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
  
  //gauge fifgroup
  $("#gauge").append(
        "<svg style='height: 210px; width: 210px; font-size: 12px; transform: translate(13px,-193px)'>"+
          "<g>"+
            //"<circle cx='92' cy='87' r='10'/>"+
            "<path d='M 33,49 A 70,70 0 0,1 150,48' stroke='rgba(255,200,180,0.8)' stroke-width='3' fill='transparent'/>"+
            "<path d='M 33,49 A 70,70 0 0,1 150,48' stroke='rgba(255,255,0,0.4)' stroke-width='10' fill='transparent'/>"+
            "<g style='transform-origin: 44% 41%; transform: rotateZ(-57.6deg)'>"+
              "<path d='M 92,57 L 96,94 H 88 Z' stroke='#aaaaaa' stroke-width='2' fill='white' filter='url(#blur)'/>"+
              "<path d='M 92,57 L 96,94 H 88 Z' stroke='#aaaaaa' stroke-width='2' fill='white'/>"+
            "</g>"+
            "<line x1='59' x2='64' y1='26' y2='36' style='stroke: rgba(255,200,180,0.8); stroke-width: 2'/>"+
            "<line x1='59' x2='64' y1='26' y2='36' style='stroke: rgba(255,200,180,0.2); stroke-width: 10'/>"+
            "<line x1='92' x2='92' y1='18' y2='29' style='stroke: rgba(255,200,180,0.8); stroke-width: 2'/>"+
            "<line x1='92' x2='92' y1='18' y2='29' style='stroke: rgba(255,200,180,0.2); stroke-width: 10'/>"+
            "<line x1='125' x2='120' y1='26' y2='36' style='stroke: rgba(255,200,180,0.8); stroke-width: 2'/>"+
            "<line x1='125' x2='120' y1='26' y2='36' style='stroke: rgba(255,200,180,0.2); stroke-width: 10'/>"+
            "<text x='38' y='110' fill='white'>1.0</text>"+
            "<text x='122' y='110' fill='white'>3.0</text>"+
            "<text x='38' y='65' fill='white'>1.5</text>"+
            "<text x='122' y='65' fill='white'>2.5</text>"+
            "<text x='84' y='42' fill='white'>2.0</text>"+
            "<text x='77' y='143' fill='#22aa99' style='font-weight: bold; font-size: 16px'>1.52</text>"+
            "<text x='54' y='205' fill='#22aa99' style='font-weight: bold; font-size: 16px'>FIFGROUP</text>"+
          "</g>"+
          "<defs>"+
            "<filter id='blur'>"+
              "<feGaussianBlur in='SourceGraphic' stdDeviation='2'/>"+
            "</filter>"+
          "</defs>"+
        "</svg>");
  
  //gauge area
  $("#gauge").append(
        "<svg style='height: 210px; width: 210px; font-size: 11px; transform: translate(17px,-178px)'>"+
          "<g style='transform: scale(0.84)'>"+
            //"<circle cx='92' cy='87' r='10'/>"+
            "<path d='M 148,48 A 70,70 0 0,1 155,111' stroke='rgba(255,180,180,0.8)' stroke-width='3' fill='transparent'/>"+
            "<path d='M 148,48 A 70,70 0 0,1 155,111' stroke='rgba(255,0,0,0.4)' stroke-width='10' fill='transparent'/>"+
            "<g style='transform-origin: 44% 41%; transform: rotateZ(85.2deg)'>"+
              "<path d='M 92,57 L 96,94 H 88 Z' stroke='#aaaaaa' stroke-width='2' fill='white' filter='url(#blur)'/>"+
              "<path d='M 92,57 L 96,94 H 88 Z' stroke='#aaaaaa' stroke-width='2' fill='white'/>"+
            "</g>"+
            /*"<line x1='59' x2='64' y1='26' y2='36' stroke='rgba(255,200,180,0.8)' stroke-width='2'/>"+
            "<line x1='59' x2='64' y1='26' y2='36' stroke='rgba(255,200,180,0.2)' stroke-width='10'/>"+
            "<line x1='92' x2='92' y1='18' y2='29' stroke='rgba(255,200,180,0.8)' stroke-width='2'/>"+
            "<line x1='92' x2='92' y1='18' y2='29' stroke='rgba(255,200,180,0.2)' stroke-width='10'/>"+
            "<line x1='125' x2='120' y1='26' y2='36' stroke='rgba(255,200,180,0.8)' stroke-width='2'/>"+
            "<line x1='125' x2='120' y1='26' y2='36' stroke='rgba(255,200,180,0.2)' stroke-width='10'/>"+*/
            "<text x='38' y='110' fill='white'>1.0</text>"+
            "<text x='122' y='110' fill='white'>3.0</text>"+
            "<text x='38' y='65' fill='white'>1.5</text>"+
            "<text x='122' y='65' fill='white'>2.5</text>"+
            "<text x='84' y='42' fill='white'>2.0</text>"+
            "<text x='77' y='143' fill='#22aa99' style='font-weight: bold; font-size: 16px'>2.71</text>"+
          "</g>"+
          "<text x='54' y='191' fill='#22aa99' style='font-weight: bold; font-size: 16px'>AREA</text>"+
          "<defs>"+
            "<filter id='blur'>"+
              "<feGaussianBlur in='SourceGraphic' stdDeviation='2'/>"+
            "</filter>"+
          "</defs>"+
        "</svg>");

  //gauge branch
  $("#gauge").append(
        "<svg style='height: 210px; width: 210px; font-size: 10px; transform: translate(410px,-380px)'>"+
          "<g style='transform: scale(0.68)'>"+
            //"<circle cx='92' cy='87' r='10'/>"+
            "<path d='M 36,48 A 70,70 0 0,0 29,111' stroke='rgba(180,255,200,0.8)' stroke-width='3' fill='transparent'/>"+
            "<path d='M 36,48 A 70,70 0 0,0 29,111' stroke='rgba(0,255,0,0.4)' stroke-width='10' fill='transparent'/>"+
            "<g style='transform-origin: 44% 41%; transform: rotateZ(-104.4deg)'>"+
              "<path d='M 92,57 L 96,94 H 88 Z' stroke='#aaaaaa' stroke-width='2' fill='white' filter='url(#blur)'/>"+
              "<path d='M 92,57 L 96,94 H 88 Z' stroke='#aaaaaa' stroke-width='2' fill='white'/>"+
            "</g>"+
            /*"<line x1='59' x2='64' y1='26' y2='36' stroke='rgba(255,200,180,0.8)' stroke-width='2'/>"+
            "<line x1='59' x2='64' y1='26' y2='36' stroke='rgba(255,200,180,0.2)' stroke-width='10'/>"+
            "<line x1='92' x2='92' y1='18' y2='29' stroke='rgba(255,200,180,0.8)' stroke-width='2'/>"+
            "<line x1='92' x2='92' y1='18' y2='29' stroke='rgba(255,200,180,0.2)' stroke-width='10'/>"+
            "<line x1='125' x2='120' y1='26' y2='36' stroke='rgba(255,200,180,0.8)' stroke-width='2'/>"+
            "<line x1='125' x2='120' y1='26' y2='36' stroke='rgba(255,200,180,0.2)' stroke-width='10'/>"+*/
            "<text x='38' y='110' fill='white'>1.0</text>"+
            "<text x='122' y='110' fill='white'>3.0</text>"+
            "<text x='38' y='65' fill='white'>1.5</text>"+
            "<text x='122' y='65' fill='white'>2.5</text>"+
            "<text x='84' y='42' fill='white'>2.0</text>"+
            "<text x='77' y='143' fill='#22aa99' style='font-weight: bold; font-size: 16px'>1.13</text>"+
          "</g>"+
          "<text x='30' y='178' fill='#22aa99' style='font-weight: bold; font-size: 16px'>BRANCH</text>"+
          "<defs>"+
            "<filter id='blur'>"+
              "<feGaussianBlur in='SourceGraphic' stdDeviation='2'/>"+
            "</filter>"+
          "</defs>"+
        "</svg>");
  
});