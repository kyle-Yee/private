var chart;
$(function() {
	charts();
});
/**
 * 搜索按钮
 */
function search(){
	$.ajax({
		type : "post",
		url : sys.rootPath + '/pandect/search.html',
		dataType : "json",
		success : function(data){
			//chart data赋值
			chart.series[0].setData(data.data);
		}
	});
}
/**
 * 初始化点线图
 */
function charts(){
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'container',
			defaultSeriesType: 'line',
			margin: [50, 150, 60, 80]
		},
		title: {
			text: '',
			style: {
				margin: '10px 100px 0 0' // center it
			}
		},
		xAxis: {
			categories: ['1月', '2月', '3月', '4月', '5月', '6月', 
				'7月', '8月', '9月', '10月', '11月', '12月'],
			title: {
				text: 'Month'
			}
		},
		yAxis: {
			title: {
				text: '户'
			},
			plotLines: [{
				value: 0,
				width: 1,
			}]
		},
		legend: {
			layout: 'vertical',
			style: {
				left: 'auto',
				bottom: 'auto',
				right: '10px',
				top: '100px'
			}
		},
		series: [{
			name: '注册用户',
			data: []
		}, {
			name: '通过认证用户',
			data: []
		}, {
			name: '申请贷款用户',
			data: []
		}, {
			name: '授信用户',
			data: []
		}]
	});
}
