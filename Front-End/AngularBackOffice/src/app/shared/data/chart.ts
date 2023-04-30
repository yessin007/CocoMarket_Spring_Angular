// import * as Chartist from 'chartist';
// import { ChartEvent, ChartType } from 'ng-chartist';

// export interface Chart {
//   type: any;
//   data: Chartist.IChartistData;
//   options?: any;
//   responsiveOptions?: any;
//   events?: any;
// }

export let doughnutData = [
  {
    value: 100,
    name: 'Frontend'

  },
  {
    value: 150,
    name: 'Backend'
  },
  {
    value: 150,
    name: 'Api'
  },
  {
    value: 100,
    name: 'Issues'
  }
];

export let view: any[] = [409, 204];

export let pieData = [
  {
    value: 300,
    name: 'Frontend'

  },
  {
    value: 50,
    name: 'Backend'
  },
  {
    value: 100,
    name: 'Api'
  },
];

// Options
export let doughnutChartShowLabels = false;
export let doughnutChartTooltip = false;
export let doughnutChartGradient = false;
export let doughnutChartcolorScheme = {
  domain: ['#ff7f83', '#02cccd', '#a5a5a5', '#ffbc58'],
};

// Chart 5 Line chart with area
export let chart5: any = {
  type: 'Line',
  data: {
    labels: [1, 2, 3, 4, 5, 6, 7, 8],
    series: [
      [5, 9, 7, 8, 5, 3, 5, 4]
    ]
  },
  // options: {
  //   showArea: true,
  //   height: '450',
  //   low: 0,
  // }
  options: {
    height: 450,
    showArea: true,
    seriesBarDistance: 12,
    axisX: {
      showGrid: false,
      labelInterpolationFnc(value) {
        return value[0];
      }
    }
  },
};

// line chart
export let lineChartData: Array<any> = [
  { data: [20, 5, 80, 10, 100, 15] },
  { data: [0, 50, 20, 70, 30, 27] },
  { data: [0, 30, 40, 10, 86, 40] }
];
export let lineChartLabels: Array<any> = ['1 min.', '10 min.', '20 min.', '30 min.', '40 min.', '50 min.'];
export let lineChartOptions: any = {
  scaleShowGridLines: true,
  scaleGridLineWidth: 1,
  scaleShowHorizontalLines: true,
  scaleShowVerticalLines: true,
  bezierCurve: true,
  bezierCurveTension: 0.4,
  pointDot: true,
  pointDotRadius: 4,
  pointDotStrokeWidth: 1,
  pointHitDetectionRadius: 20,
  datasetStroke: true,
  datasetStrokeWidth: 2,
  datasetFill: true,
  responsive: true,
  maintainAspectRatio: false,
  elements: {
    line: {
      tension: 0.5
    }
  },
};
export let lineChartColors: Array<any> = [
  {
    backgroundColor: 'transparent',
    borderColor: '#01cccd',
    pointColor: '#01cccd',
    pointStrokeColor: '#fff',
    pointHighlightFill: '#fff',
    pointHighlightStroke: '#000'
  },
  {
    backgroundColor: 'transparent',
    borderColor: '#a5a5a5',
    pointColor: '#a5a5a5',
    pointStrokeColor: '#fff',
    pointHighlightFill: '#000',
    pointHighlightStroke: 'rgba(30, 166, 236, 1)',
  },
  {
    backgroundColor: 'transparent',
    borderColor: '#ff7f83',
    pointColor: '#ff7f83',
    pointStrokeColor: '#fff',
    pointHighlightFill: '#000',
    pointHighlightStroke: 'rgba(30, 166, 236, 1)',
  }
];
export let lineChartLegend = false;
export let lineChartType = 'line';


// line chart
export let smallLineChartData: Array<any> = [
  { data: [20, 5, 120, 10, 140, 15] },
];
export let smallLineChartLabels: Array<any> = ['', '', '', '', '', ''];
export let smallLineChartOptions: any = {
  scaleShowHorizontalLines: false,
  pointDotStrokeWidth: 0,
  scaleShowVerticalLines: false,
  responsive: true,
  backgroundColor: 'transparent',
  borderColor: '#ff8084',
  pointColor: '#ff8084',
  elements: {
    point: {
      radius: 0
    },
  },
  scales: {
    x: {
      grid: {
        drawBorder: false,
        display: false
      },
      ticks: {
        display: false
      }
    },
    y: {
      grid: {
        drawBorder: false,
        display: false
      },
      ticks: {
        display: false
      }
    },
  }
};
export let smallLineChartLegend = false;
export let smallLineChartType = 'line';

// line chart
export let smallLine2ChartData: Array<any> = [
  { data: [85, 83, 90, 70, 85, 60, 65, 63, 68, 68, 65, 40, 60, 75, 70, 90] },
];
export let smallLine2ChartLabels: Array<any> = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17'];
export let smallLine2ChartOptions: any = {
  scaleShowHorizontalLines: false,
  pointDotStrokeWidth: 0,
  scaleShowVerticalLines: false,
  responsive: true,
  elements: {
    point: {
      radius: 0
    },
    line: {
      tension: 0
    }
  },
  scales: {
    x: {
      grid: {
        drawBorder: false,
        display: false
      },
      ticks: {
        display: false
      }
    },
    y: {
      grid: {
        drawBorder: false,
        display: false
      },
      ticks: {
        display: false
      }
    },
  }
};
export let smallLine2ChartLegend = false;
export let smallLine2ChartType = 'line';


// line chart
export let smallLine3ChartData: Array<any> = [
  { data: [85, 83, 90, 70, 85, 60, 65, 63, 68, 68, 65, 40, 60, 75, 70, 90] },
];
export let smallLine3ChartLabels: Array<any> = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17'];
export let smallLine3ChartOptions: any = {
  scaleShowHorizontalLines: false,
  pointDotStrokeWidth: 0,
  scaleShowVerticalLines: false,
  responsive: true,
  backgroundColor: 'transparent',
  borderColor: '#f0b54d',
  pointColor: '#f0b54d',
  elements: {
    point: {
      radius: 0
    },
    line: {
      tension: 0
    }
  },
  scales: {
    x: {
      grid: {
        drawBorder: false,
        display: false
      },
      ticks: {
        display: false
      }
    },
    y: {
      grid: {
        drawBorder: false,
        display: false
      },
      ticks: {
        display: false
      }
    },
  }
};
export let smallLine3ChartLegend = false;
export let smallLine3ChartType = 'line';

// line chart
export let smallLine4ChartData: Array<any> = [
  { data: [85, 83, 90, 70, 85, 60, 65, 63, 68, 68, 65, 40, 60, 68, 75, 70, 90] },
];
export let smallLine4ChartLabels: Array<any> = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17'];
export let smallLine4ChartOptions: any = {
  scaleShowHorizontalLines: false,
  pointDotStrokeWidth: 0,
  scaleShowVerticalLines: false,
  responsive: true,
  backgroundColor: 'transparent',
  borderColor: '#a5a5a5',
  pointColor: '#a5a5a5',
  pointStrokeColor: '#fff',
  pointHighlightFill: '#fff',
  pointHighlightStroke: '#000',
  elements: {
    point: {
      radius: 0
    },
    line: {
      tension: 0
    }
  },
  scales: {
    x: {
      grid: {
        drawBorder: false,
        display: false
      },
      ticks: {
        display: false
      }
    },
    y: {
      grid: {
        drawBorder: false,
        display: false
      },
      ticks: {
        display: false
      }
    },
  }
};
export let smallLine4ChartColors: Array<any> = [
  {
    backgroundColor: 'transparent',
    borderColor: '#a5a5a5',
    pointColor: '#a5a5a5',
    pointStrokeColor: '#fff',
    pointHighlightFill: '#fff',
    pointHighlightStroke: '#000',
  },

];
export let smallLine4ChartLegend = false;
export let smallLine4ChartType = 'line';


// Chart 3
export let chart3: any = {
  type: 'Bar',
  data: {
    labels: ['100', '200', '300', '400', '500', '600', '700', '800'],
    series: [
      [2.5, 3, 3, 0.9, 1.3, 1.8, 3.8, 1.5],
      [3.8, 1.8, 4.3, 2.3, 3.6, 2.8, 2.8, 2.8]
    ]
  },
  options: {
    height: 303,
    seriesBarDistance: 12,
    axisX: {
      showGrid: false,
      labelInterpolationFnc(value) {
        return value[0];
      }
    }
  },
  events: {
    created: (data) => {

    }
  }
};


// report component

// line chart
export let salesChartData: Array<any> = [
  { data: [10, 50, 0, 80, 10, 70] },
  { data: [20, 40, 15, 70, 30, 27] },
  { data: [5, 30, 20, 40, 50, 20] }
];
export let salesChartLabels: Array<any> = ['1 min.', '10 min.', '20 min.', '30 min.', '40 min.', '50 min.'];
export let salesChartOptions: any = {
  scaleShowGridLines: true,
  scaleGridLineWidth: 1,
  scaleShowHorizontalLines: true,
  scaleShowVerticalLines: true,
  bezierCurve: true,
  bezierCurveTension: 0.4,
  pointDot: true,
  pointDotRadius: 4,
  pointDotStrokeWidth: 1,
  pointHitDetectionRadius: 20,
  datasetStroke: true,
  datasetStrokeWidth: 2,
  datasetFill: true,
  responsive: true,
  maintainAspectRatio: false,
  elements: {
    line: {
      tension: 0.5
    }
  },
};
export let salesChartColors: Array<any> = [
  {
    backgroundColor: 'transparent',
    borderColor: '#01cccd',
    pointColor: '#01cccd',
    pointStrokeColor: '#fff',
    pointHighlightFill: '#fff',
    pointHighlightStroke: '#000'
  },
  {
    backgroundColor: 'transparent',
    borderColor: '#a5a5a5',
    pointColor: '#a5a5a5',
    pointStrokeColor: '#fff',
    pointHighlightFill: '#000',
    pointHighlightStroke: 'rgba(30, 166, 236, 1)',
  },
  {
    backgroundColor: 'transparent',
    borderColor: '#ff7f83',
    pointColor: '#ff7f83',
    pointStrokeColor: '#fff',
    pointHighlightFill: '#000',
    pointHighlightStroke: 'rgba(30, 166, 236, 1)',
  }
];
export let salesChartLegend = false;
export let salesChartType = 'line';

export let areaChart1: any = {
  chartType: 'AreaChart',
  dataTable: [
    ['Year', 'Sales', 'Expenses'],
    ['2013', 1000, 400],
    ['2014', 1170, 460],
    ['2015', 660, 1120],
    ['2016', 1030, 540]
  ],
  options: {
    title: 'Company Performance',
    hAxis: { title: 'Year', titleTextStyle: { color: '#333' } },
    vAxis: { minValue: 0 },
    width: '100%',
    height: 340,
    colors: ['#ff7f83', '#a5a5a5'],
    backgroundColor: 'transparent'
  },
};

export let columnChart1: any = {
  chartType: 'ColumnChart',
  dataTable: [
    ['Year', 'Sales', 'Expenses'],
    ['100', 2.5, 3.8],
    ['200', 3, 1.8],
    ['300', 3, 4.3],
    ['400', 0.9, 2.3],
    ['500', 1.3, 3.6],
    ['600', 1.8, 2.8],
    ['700', 3.8, 2.8],
    ['800', 1.5, 2.8]
  ],
  options: {
    legend: { position: 'none' },
    bars: 'vertical',
    vAxis: {
      format: 'decimal'
    },
    height: 340,
    width: '100%',
    colors: ['#ff7f83', '#a5a5a5'],
    backgroundColor: 'transparent'
  },
};

export let lineChart: any = {
  chartType: 'LineChart',
  dataTable: [
    ['Month', 'Guardians of the Galaxy', 'The Avengers'],
    [10, 20, 60],
    [20, 40, 10],
    [30, 20, 40],
    [40, 50, 30],
    [50, 20, 80],
    [60, 60, 30],
    [70, 10, 20],
    [80, 40, 90],
    [90, 20, 0]
  ],
  options: {
    colors: ['#ff8084', '#a5a5a5'],
    legend: { position: 'none' },
    height: 500,
    width: '100%',
    backgroundColor: 'transparent'
  },
};

export let chart6: any = {
  type: 'Line',
  data: {
    labels: [],
    series: [
      [3, 4, 3, 5, 4, 3, 5]
    ]
  },
  options: {
    showScale: false,
    fullWidth: !0,
    showArea: !0,
    label: false,
    width: '600',
    height: '358',
    low: 0,
    offset: 0,
    axisX: {
      showLabel: false,
      showGrid: false
    },
    axisY: {
      showLabel: false,
      showGrid: false,
      low: 0,
      offset: -10,
    },
  }
};
