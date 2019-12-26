var Industriesstatistics = (function ($, undefined) {
    var chart1 = null;
    var chart2 = null;
    var chart3 = null;
    var chart4 = null;
    var chart5 = null;
    
 
    
    
    function init() {
        /**
         * 饼图
         */
        chart1 = Highcharts.chart({
            chart: {
                renderTo: 'container1',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
            },
            title: {
                text: '银行贷款申请笔数（笔 ）'
            },
            legend: {
                layout: 'vertical',
                align: 'right', //水平方向位置
                verticalAlign: 'middle', //垂直方向位置
            },
            tooltip: {
                formatter: function () {
                    //鼠标放在上面显示的
                    return '<b>' + this.point.name + '</b>: ' + this.point.y + " 笔";
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    showInLegend: true,
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function () {
                            return '<b>' + this.point.name + '</b>: ' + this.point.percentage.toFixed(2) + ' %';
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: []
            }]
        });

        chart2 = Highcharts.chart({
            chart: {
                renderTo: 'container2',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
            },
            title: {
                text: '银行贷款申请总额（万元）'
            },
            legend: {
                layout: 'vertical',
                align: 'right', //水平方向位置
                verticalAlign: 'middle', //垂直方向位置
            },
            tooltip: {
                formatter: function () {
                    //鼠标放在上面显示的
                    return '<b>' + this.point.name + '</b>: ' + this.point.y + " 万元";
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    showInLegend: true,
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function () {
                            return '<b>' + this.point.name + '</b>: ' + this.point.percentage.toFixed(2) + ' %';
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: []
            }]
        });

        chart3 = Highcharts.chart({
            chart: {
                renderTo: 'container3',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
            },
            title: {
                text: '银行成功授信笔数（笔）'
            },
            legend: {
                layout: 'vertical',
                align: 'right', //水平方向位置
                verticalAlign: 'middle', //垂直方向位置
            },
            tooltip: {
                formatter: function () {
                    //鼠标放在上面显示的
                    return '<b>' + this.point.name + '</b>: ' + this.point.y + " 笔";
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    showInLegend: true,
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function () {
                        		 return '<b>' + this.point.name + '</b>: ' + this.point.percentage.toFixed(2) + ' %';
                           
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: []
            }]
        });

        chart4 = Highcharts.chart({
            chart: {
                renderTo: 'container4',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
            },
            title: {
                text: '银行成功授信额度（万元）'
            },
            legend: {
                layout: 'vertical',
                align: 'right', //水平方向位置
                verticalAlign: 'middle', //垂直方向位置
            },
            tooltip: {
                formatter: function () {
                    //鼠标放在上面显示的
                    return '<b>' + this.point.name + '</b>: ' + this.point.y + " 万元";
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    showInLegend: true,
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function () {
                            return '<b>' + this.point.name + '</b>: ' + this.point.percentage.toFixed(2) + ' %';
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: []
            }]
        });

        /**
         * 柱状图
         */
        chart5 = Highcharts.chart({
            chart: {
                renderTo: 'container5', //图表放置的容器，关联DIV#id
                //因为是柱状图和曲线图共存在一个图表中，所以默认图表类型不在这里设置。
                margin: [70, 180, 100, 70],
                showInLegend: true,
            },
            credits: {
                enabled: false   //不显示LOGO
            },
            title: {
                text: '分行业展示'
            },
            xAxis: [{ //X轴标签
                categories: [],
                labels: {
                    formatter: function () {
                        var labelVal = this.value;
                        for (var i = 0; i < labelVal.length; i++) {
                            if (labelVal[i].length > 10) {
                                labelVal[i] = labelVal[i].substr(0, 10) + "<br/>" + labelVal[i].substring(10, labelVal[i].length);
                            }
                        }
                        return labelVal;
                    },
                    y: 30,
                    rotation: -20,
                    align: 'right'  //设置右对齐

                }
            }],
            yAxis: [{ //设置Y轴-第一个
                allowDecimals: false,
                title: {
                    useHTML: true,
                    rotation: 0,
                    text: '贷<br/>款<br/>笔<br/>数'
                },//Y轴标题设为空
                labels: {
                    formatter: function () {//格式化标签名称
                        return this.value + " 笔";
                    },
                    style: {
                        color: '#4572A7', //设置标签颜色
                        marginLeft: '20px'
                    }
                },
                gridLineWidth: 1,
                gridLineColor: '#C0D0E0'


            }, { // Secondary yAxis
                allowDecimals: false,
                title: {
                    useHTML: true,
                    rotation: 0,
                    text: '贷<br/>款<br/>金<br/>额',
                    style: {
                        marginLeft: '30px'//添加样式，使字体叠加分开
                    }
                },
                labels: {
                    x: 0,//zzx改前-20
                    formatter: function () {//格式化标签名称
                        return this.value + " 万元";
                    },
                    style: {
                        color: '#4572A7' //设置标签颜色
                    }
                },
                opposite: true
            }],
            tooltip: { //鼠标滑向数据区显示的提示框
                formatter: function () {  //格式化提示框信息
                    var unit = {
                        '贷款申请笔数': '笔',
                        '贷款申请总额': '万元',
                        '成功授信笔数': '笔',
                        '成功授信额度': '万元',
                    } [this.series.name];
                    return this.series.name + this.y + ' ' + unit;
                }
            },
            plotOptions: {
                column: {
                    pointPadding: 0.05,
                    borderWidth: 0,
                    pointWidth: 20,
                    borderColor: "",
                    shadow: false
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right', //水平方向位置
                verticalAlign: 'middle', //垂直方向位置
            },
            scrollbar: {
                enabled: true
            },
            series: [{
                name: '贷款申请笔数',
                type: 'column',
                color: '#95CEFF',
                yAxis: 0, //数据列关联到Y轴，默认是0，设置为1表示关联上述第二个Y轴即金额
                data: [] //金额数据
            }, {
                name: '贷款申请总额',
                type: 'column',
                color: '#5C5C61',
                yAxis: 1, //数据列关联到Y轴，默认是0，设置为1表示关联上述第二个Y轴即金额
                data: [] //金额数据
            }, {
                name: '成功授信笔数',
                type: 'column',
                color: '#A9FF96',
                yAxis: 0, //数据列关联到Y轴，默认是0，设置为1表示关联上述第二个Y轴即金额
                data: [] //金额数据
            }, {
                name: '成功授信额度',
                type: 'column',
                color: '#FFBC75',
                yAxis: 1, //数据列关联到Y轴，默认是0，设置为1表示关联上述第二个Y轴即金额
                data: [] //金额数据
            }]
        });
    }

    function search() {
        var data = $('#form').serialize();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: sys.rootPath + "/industriesstatistics/list_new.html",
            data: data,
            success: function (result) {
                //清除原有数据，防止快速重复点击按钮造成数据重复
                var data1 = [],
                    data2 = [],
                    data3 = [],
                    data4 = [],
                    data5 = [];

                var details = result.industriesstatistics;
                if (details && details.length != 0) {
                    $("#dataNo").hide();
                    $("#dataALL").show();
                    $("#caltr").show();
                    //清除原有数据，防止快速重复点击按钮造成数据重复
                    $("#table tbody").remove();
                    $.each(details, function (i, n) {
                        var tbBody = "";
                        tbBody += "<tr>" +
                            "<td>" + (i + 1) + "</td>" +
                            "<td>" + n.hymc + "</td>" +
                            "<td>" + n.dksqbs + "</td>" +
                            "<td>" + n.dksqze + "</td>" +
                            "<td>" + parseFloat(n.pjsdje).toFixed(2) + "</td>" +
                            "<td>" + n.cgsxbs + "</td>" +
                            "<td>" + n.cgsxed + "</td>" +
                            "<td>" + parseFloat(n.pjsxed).toFixed(2) + "</td>" +
                            "</tr>";
                        $("#table").append(tbBody);
                    });

                    $.each(details, function (i, n) {
                    	if(parseInt(n.dksqbs) != 0){
                    		data1.push([n.hymc, parseInt(n.dksqbs)]);
                    		 data5.push([n.hymc]);
                    	}
                    	if(n.dksqze !=0){
                    		data2.push([n.hymc, parseFloat(n.dksqze)]);
                    		data5.push([n.hymc]);
                    	}
                    	if(n.cgsxbs != 0){
                    		data3.push([n.hymc, parseInt(n.cgsxbs)]);
                    		 data5.push([n.hymc]);
                    	}
                    	if(n.cgsxed !=0){
                    		data4.push([n.hymc, parseFloat(n.cgsxed)]);
                    		 data5.push([n.hymc]);
                    	}
//                        if (parseInt(n.dksqbs) != 0 || n.dksqze != "0.00" || n.cgsxbs != 0 || n.cgsxed != "0.00") {
//                            data1.push([n.hymc, parseInt(n.dksqbs)]);
//                            data2.push([n.hymc, parseFloat(n.dksqze)]);
//                            data3.push([n.hymc, parseInt(n.cgsxbs)]);
//                            data4.push([n.hymc, parseFloat(n.cgsxed)]);
//                            data5.push([n.hymc]);
//                        }
                    });

                    var len = details.length;
                    var h1 = 0;
                    var h2 = 0;
                    var h3 = 0;
                    var h4 = 0;
                    var h5 = 0;
                    var h6 = 0;
                    for (var i = 0; i < len; i++) {
                        $('#table tbody tr:eq(' + i + ')').each(function () {
                            $(this).find('td:eq(2)').each(function () {
                                h1 += parseInt($(this).text());
                            });
                            $(this).find('td:eq(3)').each(function () {
                                h2 += parseFloat($(this).text());
                            });
                            $(this).find('td:eq(4)').each(function () {
                                h3 += parseFloat($(this).text());
                            });
                            $(this).find('td:eq(5)').each(function () {
                                h4 += parseInt($(this).text());
                            });
                            $(this).find('td:eq(6)').each(function () {
                                h5 += parseFloat($(this).text());
                            });
                            $(this).find('td:eq(7)').each(function () {
                                h6 += parseFloat($(this).text());
                            });
                        });
                    }
                    $("#dksqbs, #tdksqbs").text(h1);
                    $("#dksqze, #tdksqze").text(h2.toFixed(2));
                    if (h1 == 0) {
                        $("#pjsdje, #tpjsdje").text("0.00");
                    } else {
                        $("#pjsdje, #tpjsdje").text((h2 / h1).toFixed(2));
                    }
                    $("#cgsxbs, #tcgsxbs").text(h4);
                    $("#cgsxed, #tcgsxed").text(h5.toFixed(2));
                    if (h4 == 0) {
                        $("#pjsxed, #tpjsxed").text("0.00");
                    } else {
                        $("#pjsxed, #tpjsxed").text((h5 / h4).toFixed(2));
                    }
                } else {
                    $("#caltr").hide();
                    $("#dataNo").show();
                }

                //chart data赋值
                chart1.series[0].setData(data1);
                chart2.series[0].setData(data2);
                chart3.series[0].setData(data3);
                chart4.series[0].setData(data4);
                chart5.xAxis[0].setCategories(data5);
                chart5.series[0].setData(data1);
                chart5.series[1].setData(data2);
                chart5.series[2].setData(data3);
                chart5.series[3].setData(data4);

                $("#loader").addClass('hide');
            },
            error: function (json) {
                $("#caltr").hide();
                $("#loader").addClass('hide');
                layer.msg("系统发生异常，请联系管理员", {icon: 0});
            }
        });
        
    }

    return {
        init: init,
        search: search
    }
})(window.jQuery);


var mlindustries=[];
var dlindustries=[];
var zlindustries=[];
//行业下拉框初始化
function initindustries(){
	$.ajax({
		type : "post",
		url : sys.rootPath + '/industriesstatistics/listIndustries.html',
		dataType : "json",
		success : function(data) {
		
			for(var i in data){
				if(data[i].hy_mlbz=='Y'){
					mlindustries.push(data[i]);
					$("#mlindustries").append("<option value="+data[i].hy_dm+">"+data[i].hy_mc+"</option>");
				}
				if(data[i].hy_dlbz=='Y'){
					dlindustries.push(data[i]);
					$("#dlindustries").append("<option value="+data[i].hy_dm+">"+data[i].hy_mc+"</option>");
				}
				if(data[i].hy_zlbz=='Y'){
					zlindustries.push(data[i]);
					$("#zlindustries").append("<option value="+data[i].hy_dm+">"+data[i].hy_mc+"</option>");
				}
				
			}
			
			//门类、大类、中类三级联动
			$("#mlindustries").on("change",function(){
				debugger;
				var mlHydm=$("#mlindustries").val();
				$("#dlindustries").empty();
				$("#zlindustries").empty();
				$("#dlindustries").append("<option disabled selected>" + '大类行业：' + "</option>");
				for(var j in dlindustries){
					if(mlHydm==dlindustries[j].hy_mldm){
						$("#dlindustries").append("<option value="+dlindustries[j].hy_dm+">"+dlindustries[j].hy_mc+"</option>");
					}
				}
			})
			$("#dlindustries").on("change",function(){
				var mlHydm=$("#dlindustries").val();
				$("#zlindustries").empty();
				$("#zlindustries").append("<option disabled selected>" + '中类行业：' + "</option>");
				for(var j in zlindustries){
					if(mlHydm==zlindustries[j].hy_dldm){
						$("#zlindustries").append("<option value="+zlindustries[j].hy_dm+">"+zlindustries[j].hy_mc+"</option>");
					}
				}
			})
		}

	})

}


/**
 * 查询按钮
 */
$("#btnSearch").click(function () {
    var starttime = $("#starttime").val();
    var endtime = $("#endtime").val();

    if (starttime > endtime) {
        layer.msg("请选择正确的起止时间！", {icon: 0});
        return false;
    }
    if (endtime != "" && starttime == "") {
        layer.msg("请输入正确的开始时间!", {icon: 0});
        return false;
    }
    if (starttime != "" && endtime == "") {
        layer.msg("请输入正确的结束时间!", {icon: 0});
        return false;
    }

    $("#table tbody").html("");
    $("#dksqbs, #tdksqbs").text("0");
    $("#dksqze, #tdksqze").text("0");
    $("#pjsdje, #tpjsdje").text("0");
    $("#cgsxbs, #tcgsxbs").text("0");
    $("#cgsxed, #tcgsxed").text("0");
    $("#pjsxed, #tpjsxed").text("0");

    setTimeout('Industriesstatistics.search()', 500);
});

/**
 * 清空按钮
 */
$("#btnReset").click(function () {
    document.getElementById("province")[0].selected = true;
    document.getElementById("city")[0].selected = true;
    $("#area").append("<option value='" + 0 + "'>" + '请选择' + "</option>");
    document.getElementById("area").lastChild.selected = true;
    document.getElementById("mlindustries")[0].selected = true;
    document.getElementById("dlindustries")[0].selected = true;
    document.getElementById("zlindustries")[0].selected = true;
    $("#dlindustries").append("<option disabled selected>" + '大类行业：' + "</option>");
    $("#zlindustries").append("<option disabled selected>" + '中类行业：' + "</option>");
    for (i = 0; i < $(".input").length; i++) {
        if ($(".input")[i].value != null && $(".input")[i].value != "") {
            $(".input")[i].value = "";
        }
    }

    setTimeout('Industriesstatistics.search()', 500);
});

var resize = function() {
    var highcharts = $("#container5");
    var rtbz = !(highcharts.length > 0) || !highcharts.highcharts() || !(highcharts.highcharts().length > 0);
    if (rtbz) {
        return;
    }
    $("#container1").highcharts().reflow();
    $("#container2").highcharts().reflow();
    $("#container3").highcharts().reflow();
    $("#container4").highcharts().reflow();
    $("#container5").highcharts().reflow();
}

//事件命名空间
$(window).on("resize.regulator", debounce(resize, 1500));

//图表宽度控制
$("#sidebar-collapse").on("click.regulator", debounce(resize, 1000));

$(function () {
	
    Industriesstatistics.init();
    initindustries();
    setTimeout('Industriesstatistics.search()', 500);
});
