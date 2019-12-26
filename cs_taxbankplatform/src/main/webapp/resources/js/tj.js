var Dashboard = (function ($, undefined) {
    var chart1 = null;
    var chart2 = null;
    var chart3 = null;
    var chart4 = null;
    var chart5 = null;
    var chart6 = null;
    var chart7 = null;

    function chartInit() {
        chart1 = Highcharts.chart("tj_1", {
            chart: {
                borderColor: '#EBBA95',
                borderWidth: 2,
                type: 'spline'
            },
            title: {
                text: '金额'
            },
            legend: {
                align: 'right',
                verticalAlign: 'middle',
                layout: 'vertical',
                itemMarginTop: 5,
                floating: true
            },
            xAxis: {
                categories: []
            },
            yAxis: {
                title: {
                    text: '金额(万元)'
                }
            },
            series: [
                {
                    name: '贷款余额',
                    data: []
                },
                {
                    name: '授信金额',
                    data: []
                },
                {
                    name: '申请金额',
                    data: []
                }
            ]
        });

        chart2 = Highcharts.chart("tj_2", {
            chart: {
                borderColor: '#EBBA95',
                borderWidth: 2,
                type: 'spline'
            },
            title: {
                text: '户数'
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                verticalAlign: 'top',
                floating: true,
                itemMarginTop: 5,
                x: 70,
                y: 15
            },
            color: ['#4F9D9D', '#FF8040'],
            yAxis: {
                title: {
                    text: '户数'
                }
            },
            series: [
                {
                    name: '获批户数',
                    data: []
                },
                {
                    name: '未获批户数',
                    data: []
                }
            ]
        });

        chart3 = Highcharts.chart("tj_3", {
            chart: {
                borderColor: '#EBBA95',
                borderWidth: 2,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '占比'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                itemMarginTop: 5
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            color: ['#FF5151', '#4F9D9D'],
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [
                {
                    name: '访问来源',
                    colorByPoint: true,
                    data: [
                        {name: '以获批户数', y: 0},
                        {name: '未获批户数', y: 0}
                    ]
                }
            ]
        });

        chart4 = Highcharts.chart("tj_5", {
            chart: {
                borderColor: '#EBBA95',
                borderWidth: 2,
                type: 'column'
            },
            title: {
                text: '申请金额'
            },
            tooltip: {},
            plotOptions: {
                series: {
                    color: '#ff7575'
                }
            },
            xAxis: {
                categories: ['第一季度', '第二季度', '第三季度', '第四季度'],
                crosshair: true
            },
            yAxis: {
                title: {
                    text: '金额（万元）'
                }
            },
            series: [
                {
                    name: '申请金额',
                    data: []
                }
            ]
        });

        chart5 = Highcharts.chart("tj_6", {
            chart: {
                borderColor: '#EBBA95',
                borderWidth: 2,
                type: 'column'
            },
            title: {
                text: '授信金额'
            },
            tooltip: {},
            plotOptions: {
                series: {
                    color: '#FFFF93'
                }
            },
            xAxis: {
                categories: ['第一季度', '第二季度', '第三季度', '第四季度'],
                crosshair: true
            },
            yAxis: {
                title: {
                    text: '金额 (万元)'
                }
            },
            series: [
                {
                    name: '授信金额',
                    data: []
                }
            ]
        });

        chart6 = Highcharts.chart("tj_7", {
            chart: {
                borderColor: '#EBBA95',
                borderWidth: 2,
                type: 'column'
            },
            title: {
                text: '贷款余额'
            },
            tooltip: {},
            plotOptions: {
                series: {
                    color: '#BBFFBB'
                }
            },
            xAxis: {
                categories: ['第一季度', '第二季度', '第三季度', '第四季度'],
                crosshair: true
            },
            yAxis: {
                title: {
                    text: '金额 (万元)'
                }
            },
            series: [
                {
                    name: '贷款余额',
                    data: []
                }
            ]
        });

        chart7 = Highcharts.chart("tj_4", {
            chart: {
                borderColor: '#EBBA95',
                borderWidth: 2,
                type: 'column'
            },
            title: {
                text: '申请数量'
            },
            tooltip: {},
            plotOptions: {
                series: {
                    color: '#FF8040'
                }
            },
            xAxis: {
                categories: ['第一季度', '第二季度', '第三季度', '第四季度'],
                crosshair: true
            },
            yAxis: {
                title: {
                    text: ''
                }
            },
            series: [
                {
                    name: '申请数量',
                    data: []
                }
            ]
        });
    }

    function tj1() {
        $.ajax({
            type: "POST",
            url: "/tbp/tj/welcome.html",
            dataType: 'json',
            success: function (data) {
                var sqjeArray = [];//申请金额
                var sxjeArray = [];//授信金额
                var dkyeArray = [];//贷款余额
                
                var hpslArray = [];//获批数量
                var whpslArray = [];//未获批数量

                var dkrq = [];

                var hpslsum = 0;
                var whpslsum = 0;

                var dataObj = data;
                var sqjesum = 0;
                var sxjesum = 0;
                var dkyesum = 0;
                var sqzssum = 0;
                
                for (var i in dataObj) {
                    sqjeArray[i] = dataObj[i].sqje / 1;
                    if (dataObj[i].sqje != null && dataObj[i].sqje != "") {
                        sqjesum += dataObj[i].sqje / 1;
                    }
                    sxjeArray[i] = dataObj[i].sxje / 1;
                    if (dataObj[i].sxje != null && dataObj[i].sxje != "") {
                        sxjesum += dataObj[i].sxje / 1;
                    }
                    dkyeArray[i] = dataObj[i].dkye / 1;
                    if (dataObj[i].dkye != null && dataObj[i].dkye != "") {
                        dkyesum += dataObj[i].dkye / 1;
                    }
                    
                    hpslArray[i] = dataObj[i].hpsl / 1;
                    if (dataObj[i].hpsl != null && dataObj[i].hpsl != "") {
                        hpslsum += dataObj[i].hpsl / 1;
                    }
                    whpslArray[i] = dataObj[i].whpsl / 1;
                    if (dataObj[i].whpsl != null && dataObj[i].whpsl != "") {
                        whpslsum += dataObj[i].whpsl / 1;
                    }
                    
                    dkrq[i] = dataObj[i].dkrq;
                }
                sqzssum = whpslsum / 1 + hpslsum / 1;
                
                chart1.xAxis[0].setCategories(dkrq);
                chart1.series[0].setData(dkyeArray);
                chart1.series[1].setData(sxjeArray);
                chart1.series[2].setData(sqjeArray);

                chart2.xAxis[0].setCategories(dkrq);
                chart2.series[0].setData(hpslArray);
                chart2.series[1].setData(whpslArray);
                
                chart3.series[0].setData([
                    {name: '以获批户数', y: hpslsum},
                    {name: '未获批户数', y: whpslsum}
                ]);

                $("#sqje").text(sqjesum + '（万）');
                $("#sqzs").text(sqzssum);
                $("#sxje").text(sxjesum + '（万）');
                $("#hpsl").text(hpslsum);
                $("#dkye").text(dkyesum + '（万）');
                $("#whpsl").text(whpslsum);
            }
        });
    }

    function tj2() {
        $.ajax({
            type: "POST",
            url: "/tbp/tj/welcomemx.html",
            dataType: 'json',
            success: function (data) {
                var jd = [];//更新的值
                var tj4value = [];//更新的值
                var tj5value = [];//更新的值
                var tj6value = [];//更新的值
                var tj7value = [];//更新的值

                var dataObj = data;
                for (var i = 0; i < 4; i++) {
                    if (dataObj[i]) {
                        jd[i] = dataObj[i].jd;
                        tj4value[i] = dataObj[i].sqje / 1;
                        tj5value[i] = dataObj[i].sxje / 1;
                        tj6value[i] = dataObj[i].dkye / 1;
                        tj7value[i] = dataObj[i].whpsl / 1 + dataObj[i].hpsl / 1;
                    } else {
                        jd[i] = i + 1;
                        tj4value[i] = 0;
                        tj5value[i] = 0;
                        tj6value[i] = 0;
                        tj7value[i] = 0;
                    }
                }

                chart4.series[0].setData(tj4value);

                chart5.series[0].setData(tj5value);

                chart6.series[0].setData(tj6value);

                chart7.series[0].setData(tj7value);
            }
        });
    }

    return {
        chartInit: chartInit,
        tj1: tj1,
        tj2: tj2
    };
})(window.jQuery);

$(function () {
    Dashboard.chartInit();

    Dashboard.tj1();
    Dashboard.tj2();

//    var divqh = false;
//
//    if (!dashboardTimeoutSeed) {
//        //清楚当前计数器，重新开始计时
//        clearTimeout(dashboardTimeoutSeed);
//        //重复定时器
//        dashboardTimeoutSeed = setTimeout(function () {
//            if (divqh) {
//                Dashboard.tj1();
//                $("#tj_one").show();
//                $("#tj_two").hide();
//                divqh = false;
//            } else {
//                Dashboard.tj2();
//                $("#tj_one").hide();
//                $("#tj_two").show();
//                divqh = true;
//            }
//            resize();
//            dashboardTimeoutSeed = setTimeout(arguments.callee, 15000);
//        }, 20000);
//    }
});

var resize = function () {
    var highcharts = $("#tj_7");
    var rtbz = !(highcharts.length > 0) || !highcharts.highcharts() || !(highcharts.highcharts().length > 0);
    if (rtbz) {
        return;
    }
    $("#tj_1").highcharts().reflow();
    $("#tj_2").highcharts().reflow();
    $("#tj_3").highcharts().reflow();

    $("#tj_4").highcharts().reflow();
    $("#tj_5").highcharts().reflow();
    $("#tj_6").highcharts().reflow();
    $("#tj_7").highcharts().reflow();
}

//事件命名空间
$(window).on("resize.dashboard", debounce(resize, 1500));

//图表宽度控制
$("#sidebar-collapse").on("click.dashboard", debounce(resize, 1000));
