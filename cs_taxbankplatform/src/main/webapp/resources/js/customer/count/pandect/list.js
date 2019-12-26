$(function(){
	setTimeout('search()', 500);
});
var chart1;
var chart2;
var chart3;
var chart4;

var arrayObj1 = new Array();
var arrayObj2 = new Array();
var arrayObj3 = new Array();
var arrayObj4 = new Array();
var arrayObj5 = new Array();
var arrayObj6 = new Array();
var arrayObj7 = new Array();

function search(){
	var data=$('#form').serialize();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/pandect/userStatistics.html",
		data:data,
		success:function(data){
			//防止快速重复点击按钮，造成数据重复
			 arrayObj1 = [];
			 arrayObj2 = [];
			 arrayObj3 = [];
			 arrayObj4 = [];
			 arrayObj5 = [];
			 arrayObj6 = [];
			 arrayObj7 = [];
			
			var list = eval("(" + data + ")");
			var typeData = list.details;
//			console.log(list.details);
			//清除原有数据，防止快速重复点击按钮造成数据重复
			$("#table .border1").remove();
        	$.each(typeData, function(i, n) {
                var tbBody = ""; 
                tbBody += "<tr class='border1'>" +
                		"<td class='border1' >" + "" + "</td>" + 
                		"<td class='border1'>" + n.dataName + "</td>" + 
                		"<td class='border1'>" + n.dataCount + "</td>" +
                		"<td class='border1'>" + n.unit + "</td></tr>";  
                $("#table").append(tbBody); 
            });  
            var len = typeData.length;
            for(var i = 0;i<len;i++){
                $('#table tbody tr:eq('+i+') td:first').text(i+1);
            }
            
            if (typeData[0].dataCount != 0){
            	$("#nodata").hide();
            	$("#data").show();
                for ( var i = 0; i < 6; i++) {
                	if (list.sqyhs != null){
                		var sqyhs = Number(list.sqyhs[i].count);
                		arrayObj1[i] = sqyhs;
                	}
                	if (list.sxyhs != null){
                		var sxyhs = Number(list.sxyhs[i].count);
                		arrayObj2[i] = sxyhs;
                	}
                	if (list.sqbs != null){
                		var sqbs = Number(list.sqbs[i].count);
                		arrayObj3[i] = sqbs;
                	}
                	if (list.sqje != null){
                		var sqje = parseInt(list.sqje[i].count);
                		arrayObj4[i] = sqje;
                	}
                	if (list.sxbs != null){
                		var sxbs = Number(list.sxbs[i].count);
                		arrayObj5[i] = sxbs;
                	}
                	if (list.sxje != null){
                		var sxje = Number(list.sxje[i].count);
                		arrayObj6[i] = sxje;
                	}	
                	if (list.swbgxzs != null){
                		var swbgxzs = Number(list.swbgxzs[i].count);
                		arrayObj7[i] = swbgxzs;
                	}
  	
                }
//                arrayObj1=null;
//                arrayObj2=null;
    			chart1.series[0].setData(arrayObj1);
    			chart1.series[1].setData(arrayObj2);
    			chart2.series[0].setData(arrayObj3);
    			chart2.series[1].setData(arrayObj5);
    			chart3.series[0].setData(arrayObj4);
    			chart3.series[1].setData(arrayObj6);
    			chart4.series[0].setData(arrayObj7);
            }else {
            	$("#data").hide();
            	$("#nodata").show();
            }
        },  
        error: function(json) {  
            alert("加载失败");  
		}
	});
		var months = new Array();
		for (var i =6; i > 0; i--){
			var date1 = new Date();
			    date1.setMonth(date1.getMonth()-i+1);
			var year1=date1.getFullYear(); 
			var month1=date1.getMonth()+1;
			month1 =(month1<10 ? "0"+month1:month1); 
			sDate = (year1.toString()+'年'+month1.toString()+'月'); 
			months[6-i] = sDate;
		}
		
		   var xAxis = {
		       categories: months,
		       title : {
					text : 'Month'
				},
		       labels : {
				rotation : -30,
				y : 30,
				style: {
                	color :'#4383B4',
                	fontSize: '10px'
                }
		       },
		       lineWidth :1//自定义x轴宽度
		   };
	   
	chart1 = new Highcharts.Chart({
		chart : {
			renderTo : 'container1',
			type: 'spline',
			margin : [ 70, 150, 60, 80 ],
			showInLegend: true,
		},
		title : {
			text : '近6个月用户统计数的点线增量图',
			style: {
				color:'#4383B4',
				margin: '10px 100px 0 0'// center it
			}
		},
		colors: ['#7CB5EC', '#434348'],
		xAxis : xAxis,
		yAxis : {
			
//			tickPositions: [0, 10, 20, 30],
            min:0, // 定义最小值         
			title : {
				text : '用<br/>户<br/>统<br/>计<br/>数',
				x : -25,
				rotation: 0,
				margin:65,
				style: {
					color:'#4383B4'
				}
			},
			labels: {
                formatter: function() {
                		 return this.value  +'  户';
                },
                style: {
                	color :'#4383B4',
                	fontSize: '9px',
                	marginLeft:'5px'
                }
            },
            gridLineColor: '#C0D0E0',
            gridLineWidth: 1
			
//			tickInterval: 1
//			plotLines : [ {
//				value : 0,
//				width : 1,  
//				color : '#808080'
//			} ]
		},
		tooltip : {
			backgroundColor: '#FCFFC5',
		    borderRadius: 10,
		    borderWidth: 1,
		    animation: true,               // 是否启用动画效果
		    style: {                      // 文字内容相关样式
		        fontSize: "12px"
		    },
			formatter : function() {
				return '<b style="padding-right:20px;color:'+this.series.color+'">'
						+ this.series.name
						+ '</b><br/>'
						+'<b style="color:#4383B4">'+this.y+' 户</b>';
			}
		},
		legend: {
			layout: 'vertical',
			align: 'right', //水平方向位置
			verticalAlign: 'middle', //垂直方向位置
		},
		exporting : {
			enabled : false
		},
		series : 
			[ {
			name : '申请用户数',
			data : []
		}, {
			name : '授信用户数',
			data : []
		} ]
	});

	chart2 = new Highcharts.Chart({
		chart : {
			renderTo : 'container2',
			type: 'spline',
			margin : [ 70, 150, 60, 80 ],
			showInLegend: true,
		},
		title : {
			text : '近6个月申请笔数统计的点线增量图',
			style: {
				color:'#4383B4',
				margin: '10px 100px 0 0'// center it
			}
		},
		colors: ['#7CB5EC', '#434348'],
		xAxis :xAxis, 
		yAxis : {
			
			title : {
				text : '贷<br/>款<br/>笔<br/>数',
				rotation: 0,
				x : -25,
				margin:65,
				style: {
					color:'#4383B4'
				}
			},
			min:0, // 定义最小值  
			labels: {
                formatter: function() {
                		 return this.value  +'  笔';
                },
                style: {
                	color :'#4383B4',
                	fontSize: '9px',
                	marginLeft:'5px'
                }
            },
            gridLineColor: '#C0D0E0',
            gridLineWidth: 1
		},
		tooltip : {
			backgroundColor: '#FCFFC5',
		    borderRadius: 10,
		    borderWidth: 1,
		    animation: true,               // 是否启用动画效果
		    style: {                      // 文字内容相关样式
		        fontSize: "12px"
		    },
			formatter : function() {
				return '<b style="padding-right:20px;color:'+this.series.color+'">'
						+ this.series.name
						+ '</b><br/>'
						+'<b style="color:#4383B4">'+this.y+' 笔</b>';
			}
		},
		legend: {
			layout: 'vertical',
			align: 'right', //水平方向位置
			verticalAlign: 'middle', //垂直方向位置
		},
		exporting : {
			enabled : false
		},
		series : [ {
			name : '申请笔数',
			data : []
		}, {
			name : '授信笔数',
			data : []
		} ]
	});

	chart3 = new Highcharts.Chart({
		chart : {
			renderTo : 'container3',
			type: 'spline',
			margin : [ 70, 150, 60, 100 ],
			showInLegend: true,
		},
		title : {
			text : '近6个月授信笔数统计的点线增量图',
			style: {
				color:'#4383B4',
				margin: '10px 100px 0 0'// center it
			}
		},
		colors: ['#7CB5EC', '#434348'],
		xAxis : xAxis, 
		yAxis : {
			title : {
				text : '贷<br/>款<br/>金<br/>额',
				rotation: 0,
				x : -25,
				margin:65,
				style: {
					color:'#4383B4',
					marginLeft:'-20px'	//===			
				}
			},
			min:0, // 定义最小值  
			labels: {
                formatter: function() {
                		 return this.value  +'  万元';
                },
                style: {
                	color :'#4383B4',
                	fontSize: '9px',
                	marginLeft:'5px'
                }
            },
            gridLineColor: '#C0D0E0',
            gridLineWidth: 1
		},
		tooltip : {
			backgroundColor: '#FCFFC5',
		    borderRadius: 10,
		    borderWidth: 1,
		    animation: true,               // 是否启用动画效果
		    style: {                      // 文字内容相关样式
		        fontSize: "12px"
		    },
			formatter : function() {
				return '<b style="padding-right:20px;color:'+this.series.color+'">'
						+ this.series.name
						+ '</b><br/>'
						+'<b style="color:#4383B4">'+this.y+' 万元</b>';
			}
		},
		legend: {
			layout: 'vertical',
			align: 'right', //水平方向位置
			verticalAlign: 'middle', //垂直方向位置
		},
		exporting : {
			enabled : false
		},
		series : [ {
			name : '申请金额',
			data : []
		}, {
			name : '授信金额',
			data : []
		} ]
	});

	chart4 = new Highcharts.Chart({
		chart : {
			renderTo : 'container4',
			type: 'spline',
			margin : [ 70, 150, 60, 80 ],
			showInLegend: true,
			
		},
		title : {
			text : '近6个月报告下载份数的点线增量图',
			style: {
				color:'#4383B4',
				margin: '10px 100px 0 0'// center it
			}
		},
		colors: ['#7CB5EC', '#434348'],
		xAxis : xAxis, 
		yAxis : {
			title : {
				text : '报<br/>告<br/>下<br/>载<br/>数',
				rotation: 0,
				x : -25,
				margin:65,
				style: {
					color:'#4383B4'
				}
			},
			min:0, // 定义最小值  
			labels: {
                formatter: function() {
                		 return this.value  +'  次';
                },
                style: {
                	color :'#4383B4',
                	fontSize: '9px',
                	marginLeft:'5px'
                }
            },
            gridLineColor: '#C0D0E0',
            gridLineWidth: 1
		},
		tooltip : {
			backgroundColor: '#FCFFC5',
		    borderRadius: 10,
		    borderWidth: 1,
		    animation: true,               // 是否启用动画效果
		    style: {                      // 文字内容相关样式
		        fontSize: "12px"
		    },
			formatter : function() {
				return '<b style="padding-right:20px;color:'+this.series.color+'">'
						+ this.series.name
						+ '</b><br/>'
						+'<b style="color:#4383B4">'+this.y+' 次</b>';
			}
		},
		legend: {
			layout: 'vertical',
			align: 'right', //水平方向位置
			verticalAlign: 'middle', //垂直方向位置
		},
		exporting : {
			enabled : false
		},
		series : [ {
			name : '税务报告下载份数',
			data : []
		} ]
	});
}

$("#btnSearch").click(function(){
    $("#table tbody").html("");
    search();
});
	
