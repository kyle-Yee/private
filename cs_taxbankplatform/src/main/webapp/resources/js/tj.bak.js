var Dashboard = {};
Dashboard.chart1 = null;
Dashboard.chart2 = null;
Dashboard.chart3 = null;
Dashboard.chart4 = null;
Dashboard.chart5 = null;
Dashboard.chart6 = null;
Dashboard.chart7 = null;

Dashboard.divqh = false;

Dashboard.chartInit = function () {
    Dashboard.chart1 = Highcharts.chart("tj_1", {
        chart: {
            borderColor: '#EBBA95',
            borderWidth: 2,
            type: 'area'
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
        color: ['#ff7575', '#FFFF93', '#BBFFBB'],
        xAxis: {
            categories: ['201902', '201903', '201904']
        },
        yAxis: {
            title: {
                text: '金额'
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

    Dashboard.chart2 = Highcharts.chart("tj_2", {
        chart: {
            borderColor: '#EBBA95',
            borderWidth: 2,
            type: 'area'
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

    Dashboard.chart3 = Highcharts.chart("tj_3", {
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
                    {name: '以获批户数', y: 335},
                    {name: '未获批户数', y: 310}
                ]
            }
        ]
    });

    Dashboard.chart4 = Highcharts.chart("tj_4", {
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

    Dashboard.chart5 = Highcharts.chart("tj_5", {
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
            categories: ['第一季度', '第二季度', '第三季度', '第四季度']
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

    Dashboard.chart6 = Highcharts.chart("tj_6", {
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
            categories: ['第一季度', '第二季度', '第三季度', '第四季度']
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

    Dashboard.chart7 = Highcharts.chart("tj_7", {
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
            categories: ['第一季度', '第二季度', '第三季度', '第四季度']
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

Dashboard.tj1 = function() {
    $.ajax({
        type: "POST",
        url: "/tbp/tj/welcome.html",
        dataType: 'json',
        success: function (data) {
            var sqjeArray = [];//更新的值
            var sxjeArray = [];//更新的值
            var dkyeArray = [];//更新的值
            var hpslArray = [];//更新的值
            var whpslArray = [];//更新的值

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
                    sqjesum = dataObj[i].sqje / 1;
                }
                sxjeArray[i] = dataObj[i].sxje / 1;
                if (dataObj[i].sxje != null && dataObj[i].sxje != "") {
                    sxjesum = dataObj[i].sxje / 1;
                }
                dkyeArray[i] = dataObj[i].dkye / 1;
                if (dataObj[i].dkye != null && dataObj[i].dkye != "") {
                    dkyesum = dataObj[i].dkye / 1;
                }
                hpslArray[i] = dataObj[i].hpsl / 1;
                if (dataObj[i].hpsl != null && dataObj[i].hpsl != "") {
                    hpslsum = dataObj[i].hpsl / 1;
                }
                whpslArray[i] = dataObj[i].whpsl / 1;
                if (dataObj[i].whpsl != null && dataObj[i].whpsl != "") {
                    whpslsum = dataObj[i].whpsl / 1;
                }
                dkrq[i] = dataObj[i].dkrq;
            }
            sqzssum = whpslsum / 1 + hpslsum / 1;

            Dashboard.chart1.series[0].setData(dkyeArray);
            Dashboard.chart1.series[1].setData(sxjeArray);
            Dashboard.chart1.series[2].setData(sqjeArray);

            Dashboard.chart2.series[0].setData(hpslArray);
            Dashboard.chart2.series[1].setData(whpslArray);

            Dashboard.chart3.series[0].setData([
                {name: '以获批户数', y: 335},
                {name: '未获批户数', y: 310}
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

Dashboard.tj2 = function() {
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
            for (var i in dataObj) {
                jd[i] = dataObj[i].jd;
                tj4value[i] = dataObj[i].sqje / 1;
                tj5value[i] = dataObj[i].sxje / 1;
                tj6value[i] = dataObj[i].dkye / 1;
                tj7value[i] = dataObj[i].whpsl / 1 + dataObj[i].hpsl / 1;
            }

            tj4value[2] = 0;
            tj4value[3] = 0;
            tj5value[2] = 0;
            tj5value[3] = 0;
            tj6value[2] = 0;
            tj6value[3] = 0;
            tj7value[2] = 0;
            tj7value[3] = 0;

            Dashboard.chart4.series[0].setData(tj4value);

            Dashboard.chart5.series[0].setData(tj5value);

            Dashboard.chart6.series[0].setData(tj6value);

            Dashboard.chart7.series[0].setData(tj7value);
        }
    });
}

$(function () {
    Dashboard.chartInit();

    Dashboard.tj1();
    Dashboard.tj2();

    var isResize = true;
    window.setInterval(function () {
        if (Dashboard.divqh) {
            Dashboard.tj1();
            $("#tj_one").show();
            $("#tj_two").hide();
            Dashboard.divqh = false;
        } else {
            Dashboard.tj2();
            $("#tj_one").hide();
            $("#tj_two").show();
            Dashboard.divqh = true;
        }
        if(isResize) {
            resize();
            isResize = false;
        }
    }, 15000);

});

function resize() {
    $("#tj_1").highcharts().reflow();
    $("#tj_2").highcharts().reflow();
    $("#tj_3").highcharts().reflow();

    $("#tj_4").highcharts().reflow();
    $("#tj_5").highcharts().reflow();
    $("#tj_6").highcharts().reflow();
    $("#tj_7").highcharts().reflow();
}

$(window).resize(function () {
    resize();
});

//图表宽度控制
$("#sidebar-collapse").click(function () {
    setTimeout('resize()', 100);
});