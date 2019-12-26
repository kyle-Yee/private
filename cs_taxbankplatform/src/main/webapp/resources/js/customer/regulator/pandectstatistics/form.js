var Pandectstatistics = (function ($, undefined) {
    var xAxis = null;
    var months = null;
    chart2 = null;
    chart3 = null;

    function init() {
        months = [];
        for (var i = 12; i > 0; i--) {
            var date1 = new Date();
            date1.setMonth(date1.getMonth() - i + 1);
            var year1 = date1.getFullYear();
            var month1 = date1.getMonth() + 1;
            month1 = (month1 < 10 ? "0" + month1 : month1);
            sDate = (year1.toString() + '年' + month1.toString() + '月');
            months[12 - i] = sDate;
        }

        xAxis = {
            categories: months,
            title: {
                text: '年月',
                style: {
                    color: '#4383B4',
                }
            },
            labels: {
                rotation: -30,
                y: 30,
                style: {
                    color: '#4383B4',
                    fontSize: '10px'
                }
            },
            lineWidth: 1//自定义x轴宽度
        };
        	
        chart2 = Highcharts.chart({
            chart: {
                renderTo: 'container2',
                type: 'spline',
                showInLegend: true,
            },
            title: {
                text: '近一年贷款信息分析（笔数）',
                style: {
                    color: '#4383B4',
                    margin: '10px 100px 0 0' // center it
                }
            },
            colors: ['#7CB5EC', '#434348'],
            xAxis: xAxis,
            yAxis: {
                allowDecimals: false,
//			tickPositions: [0, 10, 20, 30],
                title: {
                    useHTML: true,
                    rotation: 0,
                    text: '贷<br/>款<br/>笔<br/>数',
//				x : -25,
                    style: {
                        color: '#4383B4',
                    }
                },
                min: 0, // 定义最小值
                labels: {
                    formatter: function () {
                        return this.value + '  笔';
                    },
                    style: {
                        color: '#4383B4',
                        fontSize: '9px',
                        marginLeft: '5px'
                    }
                },
                gridLineColor: '#C0D0E0',
                gridLineWidth: 1
            },
            tooltip: {
                backgroundColor: '#FCFFC5',
                borderRadius: 10,
                borderWidth: 1,
                animation: true,               // 是否启用动画效果
                style: {                      // 文字内容相关样式
                    fontSize: "12px"
                },
                formatter: function () {
                    return '<b style="padding-right:20px;color:' + this.series.color + '">'
                        + this.series.name
                        + '</b><br/>'
                        + '<b style="color:#4383B4">' + this.y + ' 笔</b>';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right', //水平方向位置
                verticalAlign: 'middle', //垂直方向位置
            },
            series: [{
                name: '本月申请贷款笔数',
                data: []
            }, {
                name: '本月成功授信笔数',
                data: []
            }]
        });

        chart3 = Highcharts.chart({
            chart: {
                renderTo: 'container3',
                defaultSeriesType: 'line',
                showInLegend: true,
            },
            title: {
                text: '近一年贷款信息分析（金额）',
                style: {
                    color: '#4383B4',
                }
            },
            colors: ['#7CB5EC', '#434348'],
            xAxis: xAxis,
            yAxis: {
                title: {
                    useHTML: true,
                    rotation: 0,
                    text: '贷<br/>款<br/>金<br/>额',
                    style: {
                        color: '#4383B4',
                        marginLeft: '-35px'//标题字体与左侧距离
                    }
                },
//			tickPositions: [0, 20, 50, 100],
                min: 0, // 定义最小值
                labels: {
                    formatter: function () {
                        return this.value + '  万元';
                    },
                    style: {
                        color: '#4383B4',
                        fontSize: '9px',
                        marginLeft: '5px'//行标记与左侧距离
                    }
                },
                gridLineColor: '#C0D0E0',
                gridLineWidth: 1
            },
            tooltip: {
                backgroundColor: '#FCFFC5',
                borderRadius: 10,
                borderWidth: 1,
                animation: true,               // 是否启用动画效果
                style: {                      // 文字内容相关样式
                    fontSize: "12px"
                },
                formatter: function () {
                    return '<b style="padding-right:20px;color:' + this.series.color + '">'
                        + this.series.name
                        + '</b><br/>'
                        + '<b style="color:#4383B4">' + this.y + ' 万元</b>';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right', //水平方向位置
                verticalAlign: 'middle', //垂直方向位置
            },
            series: [{
                name: '本月申贷总额',
                data: []
            }, {
                name: '本月授信总额',
                data: []
            }]
        });
        
    }

    function search() {
        var data = $('#form').serialize();//序列化表单值
        $.ajax({
            type: "POST",
            dataType: "json",
            url: sys.rootPath + "/pandectstatistics/list_new.html",
            data: data,
            success: function (result) {
                $("#loader").addClass('hide');

                var statisticsinfo = result.statisticsinfo;
                var loancountstatisticsinfo = result.loancountstatisticsinfo;
                var loanamountstatisticsinfo = result.loanamountstatisticsinfo;

                //贷款信息统计start
                $("#loanUsers").text(toNum(statisticsinfo.sqdkyh));
                $("#creditUser").text(toNum(statisticsinfo.sxyh));
                $("#loanAmount").text(toNum(statisticsinfo.sqdkbs));//申请贷款笔数
                $("#creditNote").text(toNum(statisticsinfo.cgsxbs));//成功授信笔数

                $("#applyTotal").text(toNum(statisticsinfo.sqdkze));//申请贷款总额
                $("#averageApply").text(toNum(statisticsinfo.pjsdje));//平均申贷金额
                $("#creditTotal").text(toNum(statisticsinfo.sxze));//授信总额
                $("#averageCredit").text(toNum(statisticsinfo.pjsxze));//平均授信金额

                $("#coopBank").text(toNum(statisticsinfo.bqhzyh));//已合作银行
                $("#finaProducts").text(toNum(statisticsinfo.yshdjrcp));//银税互动金融产品

                if (loancountstatisticsinfo && loancountstatisticsinfo.length > 0) {
                    var sqdkbsArr = [];
                    var cgsxbsArr = [];

                    $.each(loancountstatisticsinfo, function (index, item) {
                        sqdkbsArr.push(item.sqdkbs);
                        cgsxbsArr.push(item.cgsxbs);
                    });

                    chart2.series[0].setData(sqdkbsArr);
                    chart2.series[1].setData(cgsxbsArr);
                } else {
                    $("#dataALL").hide();
                    $("#dataNo").show().text("查询无数据");
                }

                if (loanamountstatisticsinfo && loanamountstatisticsinfo.length > 0) {
                    var sdzeArr = [];
                    var sxzeArr = [];

                    $.each(loanamountstatisticsinfo, function (index, item) {
                        sdzeArr.push(item.sdze);
                        sxzeArr.push(item.sxze);
                    });

                    chart3.series[0].setData(sdzeArr);
                    chart3.series[1].setData(sxzeArr);
                } else {
                    $("#dataALL").hide();
                    $("#dataNo").show().text("查询无数据");
                }
                //贷款信息统计end

                //银行合作情况及金融产品
                var bankproduckstatisticsinfo = result.bankproduckstatisticsinfo;
                $("#intProduct").empty();
                if (bankproduckstatisticsinfo && bankproduckstatisticsinfo.length > 0) {
                    //产品
                    var liProduct = "";
                    liProduct += "<ol class='font-space ol-line'>";
                    $.each(bankproduckstatisticsinfo, function (i, n) {
                        liProduct += "<li>" + n.tbpname + "(" + n.tbpcode + ")--" + n.businesstype +
                            "</br>申贷笔数<span class='font-blue'>" + (n.sdbs==undefined ? 0 : n.sdbs) +
                            "</span>&nbsp;&nbsp;申贷金额<span class='font-blue'>" + (n.sdje==undefined ? 0 : n.sdje) +
                            "</span>&nbsp;&nbsp;授信笔数<span class='font-blue'>" + (n.sxbs==undefined ? 0 : n.sxbs) +
                            "</span>&nbsp;&nbsp;授信金额<span class='font-blue'>" + (n.sxje==undefined ? 0 : n.sxje) + "</span></li>";
                    });
                    liProduct += "</ol>";
                    $("#intProduct").append(liProduct);
                } else {
                    $("#intProduct").text("暂无相关数据").css({"color": "gray", "text-align": "center"});
                }
            },
            error: function (json) {
                layer.msg("系统发生异常，请联系管理员", {icon: 0});
                $("#loader").addClass('hide');
            }
        });
        
    }

    return {
        init: init,
        search: search
    }

})(window.jQuery);

/**
 * 查询按钮
 */
$("#btnSearch").click(function () {
    Pandectstatistics.search();
});

function toNum(num) {
    num = num / 1;
    if(num !== num) {
        return 0;
    }
    return num;
}

/**
 * 清空按钮
 */
$("#btnReset").click(function () {
    document.getElementById("province")[0].selected = true;
    document.getElementById("city")[0].selected = true;
    $("#area").append("<option value='" + 0 + "'>" + '请选择' + "</option>");

    document.getElementById("area").lastChild.selected = true;

    setTimeout('Pandectstatistics.search()', 500);
});

var resize = function() {
    var highcharts = $("#container3");
    var rtbz = !(highcharts.length > 0) || !highcharts.highcharts() || !(highcharts.highcharts().length > 0);
    if (rtbz) {
        return;
    }
    $("#container2").highcharts().reflow();
    $("#container3").highcharts().reflow();
}

//事件命名空间
$(window).on("resize.regulator", debounce(resize, 1500));

//图表宽度控制
$("#sidebar-collapse").on("click.regulator", debounce(resize, 1000));

$(function () {
    Pandectstatistics.init();

    $(".chart-box").children('div').height('356');

    setTimeout('Pandectstatistics.search()', 500);
});
