import data from '../../data/steps-pan.js'
const request = require('../../utils/request.js')
let chart = null;
let ringChart = null;
let pieChart = null;

function formatNumber(n) {
  return String(Math.floor(n * 100) / 100).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function initChart(canvas, width, height, F2) {
  console.log(canvas)
  const originDates = [];
  data.forEach(obj => {
    if (obj.date >= '2018-05-01') {
      originDates.push(obj.date);
    }
  });

  chart = new F2.Chart({
    el: canvas,
    width,
    height,
    animate: false
  });

  chart.source(data, {
    date: {
      type: 'timeCat',
      tickCount: 5,
      values: originDates,
      mask: 'MM-DD'
    },
    steps: {
      tickCount: 5
    }
  });

  chart.axis('date', {
    tickLine: {
      length: 4,
      stroke: '#cacaca'
    },
    label: {
      fill: '#cacaca'
    },
    line: {
      top: true
    }
  });
  chart.axis('steps', {
    position: 'right',
    label(text) {
      return {
        text: formatNumber(text * 1),
        fill: '#cacaca'
      };
    },
    grid: {
      stroke: '#d1d1d1'
    }
  });
  chart.tooltip({
    showItemMarker: false,
    background: {
      radius: 2,
      padding: [3, 5]
    },
    onShow(ev) {
      const items = ev.items;
      items[0].name = '';
      items[0].value = items[0].value + ' 步';
    }
  });
  chart.interval().position('date*steps').style({
    radius: [2, 2, 0, 0]
  });

  // 定义进度条
  chart.scrollBar({
    mode: 'x',
    xStyle: {
      backgroundColor: '#e8e8e8',
      fillerColor: '#808080',
      offsetY: -2
    }
  });
  chart.interaction('pan');
  chart.render();
  return chart;
}


function initRingChart(canvas, width, height, F2) {
  const { Util, G } = F2;
  const { Group } = G;
  function drawLabel(shape, coord, canvas) {
    const { center } = coord;
    const origin = shape.get('origin');
    const points = origin.points;
    const x1 = (points[2].x - points[1].x) * 0.75 + points[1].x;
    const x2 = (points[2].x - points[1].x) * 1.8 + points[1].x;
    const y = (points[0].y + points[1].y) / 2;
    const point1 = coord.convertPoint({ x: x1, y });
    const point2 = coord.convertPoint({ x: x2, y });

    // const group = new Group();
    const group = canvas.addGroup();
    group.addShape('Line', {
      attrs: {
        x1: point1.x,
        y1: point1.y,
        x2: point2.x,
        y2: point2.y,
        lineDash: [0, 2, 2],
        stroke: '#808080'
      }
    });
    const text = group.addShape('Text', {
      attrs: {
        x: point2.x,
        y: point2.y,
        text: origin._origin.type + '  ' + origin._origin.cost + ' 元',
        fill: '#808080',
        textAlign: 'left',
        textBaseline: 'bottom'
      }
    });
    const textWidth = text.getBBox().width;
    const baseLine = group.addShape('Line', {
      attrs: {
        x1: point2.x,
        y1: point2.y,
        x2: point2.x,
        y2: point2.y,
        stroke: '#808080'
      }
    });
    if (point2.x > center.x) {
      baseLine.attr('x2', point2.x + textWidth);
    } else if (point2.x < center.x) {
      text.attr('textAlign', 'right');
      baseLine.attr('x2', point2.x - textWidth);
    } else {
      text.attr('textAlign', 'center');
      text.attr('textBaseline', 'top');
    }
    //canvas.add(group);
    shape.label = group;
  }

  const data = [
    { type: '饮食', cost: 669.47, a: '1' },
    { type: '服饰美容', cost: 338, a: '1' },
    { type: '健康', cost: 118.5, a: '1' },
    { type: '生活用品', cost: 78.64, a: '1' },
    { type: '其他', cost: 14.9, a: '1' },
    { type: '交通出行', cost: 8.7, a: '1' }
  ];

  let sum = 0;
  data.map(obj => {
    sum += obj.cost;
  });
  ringChart = new F2.Chart({
    el: canvas,
    width,
    height
  });
  ringChart.source(data);
  let lastClickedShape;
  ringChart.legend({
    position: 'bottom',
    offsetY: -5,
    marker: 'square',
    align: 'center',
    itemMarginBottom: 20,
    onClick(ev) {
      const { clickedItem } = ev;
      const dataValue = clickedItem.get('dataValue');
      const canvas = ringChart.get('canvas');
      const coord = ringChart.get('coord');
      const geom = ringChart.get('geoms')[0];
      const container = geom.get('container');
      const shapes = geom.get('shapes'); // 只有带精细动画的 geom 才有 shapes 这个属性

      let clickedShape;
      // 找到被点击的 shape
      Util.each(shapes, shape => {
        const origin = shape.get('origin');
        if (origin && origin._origin.type === dataValue) {
          clickedShape = shape;
          return false;
        }
      });

      if (lastClickedShape) {
        lastClickedShape.animate().to({
          attrs: {
            lineWidth: 0
          },
          duration: 200
        }).onStart(() => {
          if (lastClickedShape.label) {
            lastClickedShape.label.hide();
          }
        }).onEnd(() => {
          lastClickedShape.set('selected', false);
        });
      }

      if (clickedShape.get('selected')) {
        clickedShape.animate().to({
          attrs: {
            lineWidth: 0
          },
          duration: 200
        }).onStart(() => {
          if (clickedShape.label) {
            clickedShape.label.hide();
          }
        }).onEnd(() => {
          clickedShape.set('selected', false);
        });
      } else {
        const color = clickedShape.attr('fill');
        clickedShape.animate().to({
          attrs: {
            lineWidth: 5
          },
          duration: 350,
          easing: 'bounceOut'
        }).onStart(() => {
          clickedShape.attr('stroke', color);
          clickedShape.set('zIndex', 1);
          container.sort();
        }).onEnd(() => {
          clickedShape.set('selected', true);
          clickedShape.set('zIndex', 0);
          container.sort();
          lastClickedShape = clickedShape;
          if (clickedShape.label) {
            clickedShape.label.show();
          } else {
            drawLabel(clickedShape, coord, canvas);
          }
          canvas.draw();
        });
      }
    }
  });
  ringChart.coord('polar', {
    transposed: true,
    innerRadius: 0.7,
    radius: 0.5
  });
  ringChart.axis(false);
  ringChart.tooltip(false);
  ringChart.interval()
    .position('a*cost')
    .color('type', ['#1890FF', '#13C2C2', '#2FC25B', '#FACC14', '#F04864', '#8543E0'])
    .adjust('stack');

  ringChart.guide().text({
    position: ['50%', '50%'],
    content: sum.toFixed(2),
    style: {
      fontSize: 24
    }
  });
  ringChart.render();
  return ringChart;
}

// 饼状图
function initPieChart(canvas, width, height, F2) {
  const map = {
    '芳华': '40%',
    '妖猫传': '20%',
    '机器之血': '18%',
    '心理罪': '15%',
    '寻梦环游记': '5%',
    '其他': '2%',
  };
  const data = [
    { name: '芳华', percent: 0.4, a: '1' },
    { name: '妖猫传', percent: 0.2, a: '1' },
    { name: '机器之血', percent: 0.18, a: '1' },
    { name: '心理罪', percent: 0.15, a: '1' },
    { name: '寻梦环游记', percent: 0.05, a: '1' },
    { name: '其他', percent: 0.02, a: '1' }
  ];
  pieChart = new F2.Chart({
    el: canvas,
    width,
    height
  });
  pieChart.source(data, {
    percent: {
      formatter(val) {
        return val * 100 + '%';
      }
    }
  });
  pieChart.legend({
    position: 'right',
    itemFormatter(val) {
      return val + '  ' + map[val];
    }
  });
  pieChart.tooltip(false);
  pieChart.coord('polar', {
    transposed: true,
    radius: 0.85
  });
  pieChart.axis(false);
  pieChart.interval()
    .position('a*percent')
    .color('name', ['#1890FF', '#13C2C2', '#2FC25B', '#FACC14', '#F04864', '#8543E0'])
    .adjust('stack')
    .style({
      lineWidth: 1,
      stroke: '#fff',
      lineJoin: 'round',
      lineCap: 'round'
    })
    .animate({
      appear: {
        duration: 1200,
        easing: 'bounceOut'
      }
    });

  pieChart.render();
  return pieChart;
}

Page({
  data: {
    opts: {
      onInit: initChart
    },
    ringOpts: {
      onInit: initRingChart
    },
    pieOpts: {
      onInit: initPieChart
    },
    exampleList: [],
    loadMoreBool: true,
    bannerList: []
  },

  onReady() {
  },
  loadMoreExample(){
    let _this = this;
    request._get('/cases/getAll.do').then( res => {
      _this.setData({
        exampleList: res,
        loadMoreBool: false
      })
    })
  },
  onLoad(){
    let _this = this;

    request._get('/cases/getAhead.do').then(res => {
      _this.setData({
        exampleList: res,
      })
    })
  }
});
