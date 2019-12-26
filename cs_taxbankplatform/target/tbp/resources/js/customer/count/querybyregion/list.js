$(function(){
	setTimeout('search()', 500);
});
var chart1;  
var chart2; 
var chart3;
var chart4;
var chart5;
var chart6;
var data1 = [];
var data2 = [];
var data3 = [];
var data4 = [];
var data5 = [];
var data6 = [];
function search(){
	var data=$('#form').serialize();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/querybyregion/list.html",
		data:data,
		success:function(data){
			//防止快速重复点击按钮，造成数据重复
			 data1 = [];
			 data2 = [];
			 data3 = [];
			 data4 = [];
			 data5 = [];
			 data6 = [];

			var list = eval("(" + data + ")");
			var typeData = list.details;
//			console.log(list.details);
			if (typeData.length != 0){
				$("#nodata").hide();
				$("#data").show();
				//防止快速重复点击按钮，造成数据重复
				$("#table .contentTr").remove();
				$.each(typeData, function(i, n) {  
	                var tbBody = "";  
	                var trColor;  
	                if (i % 2 == 0) {  
	                    trColor = "even";  
	                }  
	                else {  
	                    trColor = "odd";  
	                }  
	                tbBody += "<tr class='contentTr'>" +
	                		"<td class='border1' >" + "" + "</td>" + 
	                		"<td class='border1' >" + n.banklist + "</td>" + 
	                		"<td class='border1' >" + n.creditnumber + "</td>" +
	                		"<td class='border1' >" + n.creditline + "</td>" +
	                		"<td class='border1' >" + n.bgxzcs + "</td>" +
	                		"<td class='border1' >" + n.dqxzcs + "</td>" +
	                		"<td class='border1' >" + n.dhxzcs + "</td>"+
	                		"</tr>";  
	                $("#table").append(tbBody); 
	                var flag = false;
	                if(parseInt(n.creditnumber) != 0){
	                	data1.push([n.banklist,parseInt(n.creditnumber)]);
	                	flag = true;
	                }
	                if(parseInt(n.creditline) != 0){
	                	data2.push([n.banklist,parseInt(n.creditline)]);
	                	flag = true;
	                }
	                if(parseInt(n.bgxzcs) != 0){
	                	data3.push([n.banklist,parseInt(n.bgxzcs)]);
	                	flag = true;
	                }
	                if(parseInt(n.dqxzcs) != 0){
	                	data4.push([n.banklist,parseInt(n.dqxzcs)]);
	                	flag = true;
	                }
	                if(parseInt(n.dhxzcs) != 0){
	                	data5.push([n.banklist,parseFloat(n.dhxzcs)]);
	                	flag = true;
	                }
	                if(flag)
	                	data6.push([n.banklist]);
	            });  
	            var len = typeData.length;
	            var h1=0;
	            var h2=0;
	            var h3=0;
	            var h4=0;
	            var h5=0;
	            for(var i = 0;i<len;i++){
	                $('#table tbody tr:eq('+i+') td:first').text(i+1);
	                $('#table tbody tr:eq('+i+')').each(function(){
	                	$(this).find('td:eq(2)').each(function() {  
	                		h1 += parseInt($(this).text());   
	                    });  
	                	$(this).find('td:eq(3)').each(function() {  
	                		h2 += parseInt($(this).text());   
	                    }); 
	                	$(this).find('td:eq(4)').each(function() {  
	                		h3 += parseInt($(this).text());   
	                    }); 
	                	$(this).find('td:eq(5)').each(function() {  
	                		h4 += parseInt($(this).text());   
	                    }); 
	                	$(this).find('td:eq(6)').each(function() {  
	                		h5 += parseInt($(this).text());   
	                    });
	                });
	            }
	           $("#sxbs").text(h1);
	           $("#sxed").text(h2);
	           $("#bgxz").text(h3);
	           $("#dqbgxz").text(h4);
	           $("#dhbgxz").text(h5);
	           
	         //chart1 data赋值
	         //chart data赋值
	           chart1.series[0].setData(data1); 
	           chart2.series[0].setData(data2);
	           chart3.series[0].setData(data3);
	           chart4.series[0].setData(data4);
	           chart5.series[0].setData(data5);
	           

	           chart6.xAxis[0].setCategories(data6);
	           console.log(data6);
	           chart6.series[0].setData(data1); 
	           console.log(data1);
	           chart6.series[1].setData(data2); 
	           chart6.series[2].setData(data3); 
	           chart6.series[3].setData(data4); 
	           chart6.series[4].setData(data5); 
				
			}else { 
				$("#data").hide();
				$("#nodata").show().html("<div style='width:1100px;'>" +
						"<span style='margin-left:440px;width:100px;color:gray;font-size:16px;'>" +
						"<img  src='resources/images/uimg01.png' " +
						"style='outline: none;width:70px;height:50px; '/>查询无数据</span></div>");
			}
        },  
        error: function(json) {  
            alert("加载失败");  
		}
	});
	
	/**
	 * 饼图
	 */
	
	chart1 = new Highcharts.Chart({  
		chart: {  
            renderTo: 'container1',  
            plotBackgroundColor: null,  
            plotBorderWidth: null,  
            plotShadow: false, 
        },  
        colors:[
                '#FFAA33',
                '#EE82EE', 
                '#66CDAA', 
                '#F4A460', 
                '#4682B4'
        ],
        title: {  
            text: '成功授信笔数（笔）'   
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y ;
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
                    formatter: function() {  
                        return '<b>'+ this.point.name +'</b>: '+ this.point.percentage.toFixed(2) +' %';  
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
    
    chart2 = new Highcharts.Chart({  
        chart: {  
            renderTo: 'container2',  
            plotBackgroundColor: null,  
            plotBorderWidth: null,  
            plotShadow: false, 
        },  
        colors:[
                '#FFAA33',
                '#EE82EE', 
                '#66CDAA', 
                '#F4A460', 
                '#4682B4'
        ],
        title: {  
            text: '成功授信额度（万元）'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y ;
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
                    formatter: function() {  
                        return '<b>'+ this.point.name +'</b>: '+ this.point.percentage.toFixed(2) +' %';  
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
    
    chart3 = new Highcharts.Chart({  
        chart: {  
            renderTo: 'container3',  
            plotBackgroundColor: null,  
            plotBorderWidth: null,  
            plotShadow: false,
        }, 
        colors:[
                '#FFAA33',
                '#EE82EE', 
                '#66CDAA', 
                '#F4A460', 
                '#4682B4'
        ],
        title: {  
            text: '报告下载数'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y ;
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
                    formatter: function() {  
                        return '<b>'+ this.point.name +'</b>: '+ this.point.percentage.toFixed(2) +' %';  
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
    
    chart4 = new Highcharts.Chart({  
        chart: {  
            renderTo: 'container4',  
            plotBackgroundColor: null,  
            plotBorderWidth: null,  
            plotShadow: false,
        },  
        colors:[
                '#FFAA33',
                '#EE82EE', 
                '#66CDAA', 
                '#F4A460', 
                '#4682B4'
        ],
        title: {  
            text: '贷前报告下载数'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y ;
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
                    formatter: function() {  
                        return '<b>'+ this.point.name +'</b>: '+ this.point.percentage.toFixed(2) +' %';  
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
    
    chart5 = new Highcharts.Chart({  
        chart: {  
            renderTo: 'container5',  
            plotBackgroundColor: null,  
            plotBorderWidth: null,  
            plotShadow: false, 
        },  
        colors:[
                '#FFAA33',
                '#EE82EE', 
                '#66CDAA', 
                '#F4A460', 
                '#4682B4'
        ],
        title: {  
            text: '贷后报告下载数'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y ;
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
                    formatter: function() {  
                        return '<b>'+ this.point.name +'</b>: '+ this.point.percentage.toFixed(2) +' %';  
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
    chart6 = new Highcharts.Chart({ 
        chart: { 
            renderTo: 'container6', //图表放置的容器，关联DIV#id 
            //因为是柱状图和曲线图共存在一个图表中，所以默认图表类型不在这里设置。
            margin : [ 70, 200, 60, 100 ],
            showInLegend: true,
        },
        credits: { 
            enabled: false   //不显示LOGO 
        }, 
        title: {  
            text: '分地区展示' ,
        },  
        xAxis: [{ //X轴标签 
            categories: [], 
            labels: { 
            	formatter: function() {
            		var labelVal = this.value;	
            		for (var i=0;i<labelVal.length;i++){
            			if (labelVal[i].length >10){	
                			labelVal[i] = labelVal[i].substr(0,10)+"<br/>"+labelVal[i].substring(10,labelVal[i].length);
                		}
            		}
            		return labelVal;
				},
				y : 30,
            	rotation: -20,
                align: 'right'  //设置右对齐 
				
            } 
        }], 
        yAxis: [{ //设置Y轴-第一个（金额） 
            labels: { 
                formatter: function() { //格式化标签名称 
                    return this.value + '万元'; 
                }, 
                style: { 
                    color: '#4572A7', //设置标签颜色 
                    marginLeft:'0px'
                } 
            }, 
            title: {
            	useHTML:true,
            	rotation: 0,
            	text: '金<br/>额',
            	style:{
            		marginLeft:'-30px'
            	}
            }, //Y轴标题设为空 
//            opposite: false  //显示在Y轴右侧，通常为false时，左边显示Y轴，下边显示X轴 
 
        },
        { //设置Y轴-第二个（笔数） 
        	allowDecimals:false,
            labels: {
                formatter: function() { //格式化标签名称 
                    return this.value + '次/笔'; 
                }, 
                style: { 
                    color: '#4572A7' //设置标签颜色 
                } 
            }, 
            title: {
            	useHTML:true,
            	rotation: 0,
            	text: '次<br/>/</br>笔<br/>数',
            	style:{
            		marginLeft:'40px'
            	}
            }, //Y轴标题设为空 
            opposite: true  //显示在Y轴右侧，通常为false时，左边显示Y轴，下边显示X轴 
 
        }
        
        ], 
        tooltip: { //鼠标滑向数据区显示的提示框 
            formatter: function() {  //格式化提示框信息 
                var unit = { 
                    '成功授信笔数': '笔',
                    '成功授信额度': '万元', 
                    '报告下载数': '次',
                    '贷前报告下载数': '次',
                    '贷后报告下载数': '次',
                } [this.series.name]; 
                return this.series.name  + this.y + ' ' + unit; 
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
        series: [{  //数据列 
            name: '成功授信笔数', 
            type: 'column',
            color: '#FF8C00', 
            yAxis: 1,
            data: [] 
        },{  //数据列 
            name: '成功授信额度', 
            type: 'column',
            color: '#EE82EE', 
            yAxis: 0,
            data: [] 
        },{  //数据列 
            name: '报告下载数', 
            type: 'column',
            color: '#66CDAA',
            yAxis: 1,
            data: [] 
        },{  //数据列 
            name: '贷前报告下载数', 
            type: 'column',
            color: '#F4A460',
            yAxis: 1,
            data: [] 
        },{  //数据列 
            name: '贷后报告下载数', 
            type: 'column',
            color: '#4682B4',
            yAxis: 1,
            data: [] 
        }] ,
    });
	
}

$("#btnSearch").click(function(){
	if ($("#starttime").val()>$("#endtime").val()) {
		layer.msg("请选择正确的起止时间！", {icon : 0});
		return;
		
	}
	if($("#endtime").val()!=""&&$("#starttime").val()==""){
	   	 layer.msg("请输入正确的开始时间!", {icon : 0});
     	 return;
       
    }
	if($("#starttime").val()!=""&&$("#endtime").val()==""){
	   	 layer.msg("请输入正确的结束时间!", {icon : 0});
    	 return;
      
   }
	
    $("#table tbody").html("");
    $("#sxbs").html("");
    $("#sxed").html("");
    $("#bgxz").html("");
    $("#dqbgxz").html("");
    $("#dhbgxz").html("");
    var length1 = chart1.series.length;
    while (length1 > 0) {
    	chart1.series[length1-1].remove();
    	length1--;
    }
    var length2 = chart2.series.length;
    while (length2 > 0) {
    	chart2.series[length2-1].remove();
    	length2--;
    }
    var length3 = chart3.series.length;
    while (length3 > 0) {
    	chart3.series[length3-1].remove();
    	length3--;
    }
    var length4 = chart4.series.length;
    while (length4 > 0) {
    	chart4.series[length4-1].remove();
    	length4--;
    }
    var length5 = chart5.series.length;
    while (length5 > 0) {
    	chart5.series[length5-1].remove();
    	length5--;
    }
    var length6 = chart6.series.length;
    while (length6 > 0) {
    	chart6.series[length6-1].remove();
    	length6--;
    }
    data1 = [];
    data2 = [];
    data3 = [];
    data4 = [];
    data5 = [];
    data6 = [];
    setTimeout('search()', 500);
});
	
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	document.getElementById("province")[0].selected=true;
	document.getElementById("city")[0].selected=true;
	$("#area").append("<option value='"+0+"'>"+'请选择'+"</option>");
	document.getElementById("area").lastChild.selected=true;
	for(i=0;i<$(".input").length;i++){  
        if($(".input")[i].value!=null&&$(".input")[i].value!=""){  
        	$(".input")[i].value="";  
        }  
    } 
	setTimeout('search()', 500);
});

/*	$(function(){
		setTimeout('chart()', 500);
	});
	var chart1;
	function chart(){
		chart1=new Highcharts.Chart({
			chart:{
				renderTo: 'container1', 
				type:'pie',
				options3d:{
					enabled:true,	
					alpha:45
				}
			},
			title:{
				text:'AAAA'
			},
			plotOptions:{
				pie:{
					allowPointSelect: true,
			        cursor: 'pointer',
//			        dataLabels: {
//			             enabled: true,
//			             formatter: function() {  
//		                        return '<b>'+ this.point.name +'</b>: '+ this.point.percentage.toFixed(2) +' %';  
//		                 },
//			             connectorColor:'silver'
			        dataLabels: {  
			        	<span style="white-space:pre">    </span>enabled: true,  
			        	        useHTML: true, // 一定要加上  
			        	        formatter : function() {  
			        	        <span style="">    </span>return "<p style='width: 100px; display:inline-block; white-space:pre-wrap;'><b>"+this.point.name+"</b>: "+this.percentage.toFixed(2)+" %</p>"; // 重点在white-space:pre-wrap  
			        	        }  
			        	}   
			        }
				}
			},
			series:[{
				type:'pie',
				name:'aaa',
				data:[
				      ['香蕉',8],
				      ['苹果',5],
				      ['梨子',7],
				      ['草莓',10],
				      ['芒果',3]	      
				]
			}]
		});
	}*/
