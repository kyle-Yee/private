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
	var param=$('#form').serialize();

	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/areastatistics/list.html",
		data: param,
		success:function(result){
			//清除原有数据，防止快速重复点击按钮造成数据重复
			 data1 = [];
			 data2 = [];
			 data3 = [];
			 data4 = [];
			 data5 = [];
			 data6 = [];
			var list = eval("(" + result + ")");
			var data = list.areastatistics;
			if (data.length != 0){ 
				$("#dataNo").hide();
				$("#dataALL").show();
				//清除原有数据，防止快速重复点击按钮造成数据重复
    			$("#table tbody").remove();
				$.each(data, function(i, n) {  
	                var tbBody = ""; 
	                tbBody += "<tr>" +
	                		"<td>" + "" + "</td>" + 
	                		"<td>" + n.areaName + "</td>" + 
	                		/*"<td>" + n.zcyh + "</td>" +*/
	                		"<td>" + n.rzyh + "</td>" +
	                		"<td>" + n.dksqbs + "</td>" +
	                		"<td>" + n.cgsxbs + "</td>" +
	                		"<td>" + n.cgsxed/10000 + "</td>"+
	                		"</tr>"; 
	                $("#table").append(tbBody);
	                if(n.zcyh != 0||n.rzyh != 0||n.dksqbs != 0||n.cgsxbs != 0||n.cgsxed != 0){
	                	
	                	
	                	data1.push([n.areaName,parseInt(n.zcyh)]);
	                	data2.push([n.areaName,parseInt(n.rzyh)]);
	                	data3.push([n.areaName,parseInt(n.dksqbs)]);
	                	data4.push([n.areaName,parseInt(n.cgsxbs)]);
	                	data5.push([n.areaName,parseFloat(n.cgsxed/10000)]);
	                	data6.push([n.areaName]);
	                }
	                
	                /*var flag = false;
	                if(n.zcyh != 0){
	                	data1.push([n.areaName,parseInt(n.zcyh)]);
//	                	data6.push([n.areaName]);
	                	flag = true;
	                }
	                if(n.rzyh != 0){
	                	data2.push([n.areaName,parseInt(n.rzyh)]);
//	                	data6.push([n.areaName]);
	                	flag = true;
	                }
	                if(n.dksqbs != 0){
	                	data3.push([n.areaName,parseInt(n.dksqbs)]);
//	                	data6.push([n.areaName]);
	                	flag = true;
	                }
	                if(n.cgsxbs != 0){
	                	data4.push([n.areaName,parseInt(n.cgsxbs)]);
//	                	data6.push([n.areaName]);
	                	flag = true;
	                }
	                if(n.cgsxed != 0){
	                	data5.push([n.areaName,parseFloat(n.cgsxed)]);
//	                	data6.push([n.areaName]);
	                	flag = true;
	                }
	                if(flag)
	                	data6.push([n.areaName]);*/
				});
				
				
	                var len = data.length;
	                var h1=0;
	                var h2=0;
	                var h3=0;
	                var h4=0;
	                var h5=0;
	                
	                for(var i = 0;i<len;i++){
	                    $('#table tbody tr:eq('+i+') td:first').text(i+1);
	                    $('#table tbody tr:eq('+i+')').each(function(){
	                    	/*$(this).find('td:eq(2)').each(function() {  
	                    		h1 += parseInt($(this).text());   
	                        });*/  
	                    	$(this).find('td:eq(2)').each(function() {  
	                    		h2 += parseInt($(this).text());   
	                        }); 
	                    	$(this).find('td:eq(3)').each(function() {  
	                    		h3 += parseFloat($(this).text());   
	                        }); 
	                    	$(this).find('td:eq(4)').each(function() {  
	                    		h4 += parseInt($(this).text());   
	                        }); 
	                    	$(this).find('td:eq(5)').each(function() {  
	                    		h5 += parseInt($(this).text());   
	                        });
	                    });
	                }
	               
	               $("#xzqylb,#txzqylb").text(len);
	              /* $("#zcyh,#tzcyh").text(h1);*/
	               $("#rzyh,#trzyh").text(h2);
	               $("#sqdk,#tsqdk").text(h3);
	               $("#sxbs,#tsxbs").text(h4);
	               $("#sxze,#tsxze").text(h5.toFixed(2));
				   
	             //chart data赋值
	               //chart1.series[0].setData(data1);
	               chart2.series[0].setData(data2);
	               chart3.series[0].setData(data3);       
            	   chart4.series[0].setData(data4);
	               chart5.series[0].setData(data5);
	               
	               
	               chart6.xAxis[0].setCategories(data6);
	               chart6.series[0].setData(data3); 
	               chart6.series[1].setData(data4); 
	               chart6.series[2].setData(data5); 
	               
	                
			}else {
				$("#dataALL").hide();
				$("#dataNo").show().text("查询无数据");
			}
			$("#loader").addClass('hide');
        },  
        error: function(json) { 
        	
            alert("加载失败"); 
            $("#loader").addClass('hide');
		}
	});
	

	/**
	 * 饼图
	 */
	/*chart1 = new Highcharts.Chart({  
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
            text: '注册用户'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y +' 户';
            }  
        },  
        plotOptions: {  
            pie: {  
                allowPointSelect: true,  
                cursor: 'pointer', 
                showInLegend: true,
                dataLabels: {  
                    enabled: true, 
                    useHTML: true,
                    color: '#000000',  
                    connectorColor: '#000000',  
                    formatter: function() {  
//                        return '<b>'+ this.point.name +'</b>: '+ this.point.percentage.toFixed(2) +' %';
                        return "<p style='width: 100px; display:inline-block; white-space:pre-wrap;'><b>"+this.point.name+"</b>: "+this.point.percentage.toFixed(2)+" %</p>";
                    }
                    
                }  
            }  
        },  
        series: [{  
            type: 'pie',  
            name: 'Browser share',  
            data: []  
        }]  
    });  */
    
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
            text: '通过认证用户'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y +' 户';
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
            text: '申请贷款（笔）'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y +' 笔';
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
            text: '授信笔数（笔）'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y+' 笔';
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
            text: '授信总额（万元）'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y+' 万元' ;
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
            renderTo: 'container6',//图表放置的容器，关联DIV#id 
            //因为是柱状图和曲线图共存在一个图表中，所以默认图表类型不在这里设置。 
            //showInLegend: true,
        },
        credits: { 
            enabled: false   //不显示LOGO 
        }, 
        title: {  
            text: '分地区展示'  
        },  
        xAxis: [{ //X轴标签 
        	categories: [],
            labels: {
            	formatter: function() {
            		var labelVal = this.value;	
            		if(!labelVal){ alert("labelVal is null");}
            		for (var i=0;i<labelVal.length;i++){
            			if (labelVal[i].length >20){	
                			labelVal[i] = labelVal[i].substr(0,10)+"<br/>"+labelVal[i].substring(10,labelVal[i].length);
                		}
            		}
            		return labelVal;
				},
				y:30,
                rotation: -20,  //逆时针旋转45°，标签名称太长。 
                align: 'right',  //设置右对齐 
            } 
        }], 
        yAxis: [ 
        { //设置Y轴-第一个
        	allowDecimals:false,
            title: {
            	useHTML:true,
            	rotation: 0,
            	text: '',
            	style:{
            		marginLeft:'-15px'
            	}
            },//Y轴标题设为空 
            labels: { 
                formatter: function() {//格式化标签名称 
                    return this.value+" 户"; 
                }, 
                style: { 
                    color: '#4572A7', //设置标签颜色 
                    marginLeft:'20px'
                } 
            },
            gridLineWidth: 1,
            gridLineColor: '#C0D0E0'
 
        },{ // Second yAxis
        	allowDecimals:false,
        	title: {
        		useHTML:true,
            	rotation: 0,
            	text: '贷<br/>款<br/>金<br/>额',
            	style:{
            		marginLeft:'25px'
            	}
            },
//            tickPositions: [0, 20, 50, 100],
            labels: {
            	x:0,//zzx改前-20
            	formatter: function() {//格式化标签名称 
                    return this.value+" 万元" ; 
                }, 
                style: { 
                    color: '#4572A7' //设置标签颜色 
                } 
            },
            opposite: true
        }], 
        tooltip: { //鼠标滑向数据区显示的提示框 
            formatter: function() {  //格式化提示框信息 
                var unit = { 
                    '注册用户': '户',
                    '通过认证用户': '户', 
                    '申请贷款笔数': '笔',
                    '授信笔数': '笔',
                    '授信总额': '万元',
                } [this.series.name]; 
                return this.series.name  + this.y + ' ' + unit; 
            } 
        }, 
        plotOptions: {
            column: {
                pointPadding: 0.05,
                borderWidth: 0,
                pointWidth: 6,
                borderColor: "",
                shadow: false,
                dataLabels:{  
                  enabled :true,//是否在点的旁边显示数据  
                  rotation:0  
               },
            }
        },
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
        series: [/*{  //数据列 
            name: '注册用户', 
            type: 'column',
            color: '#95CEFF',
            yAxis: 0, 
            data: [] 
        },{  //数据列 
            name: '通过认证用户', 
            type: 'column',
            color: '#5C5C61', 
            yAxis: 1, 
            data: [] 
        },*/{  //数据列 
            name: '申请贷款笔数', 
            type: 'column',
            color: '#A9FF96',
            yAxis: 1, 
            data: [] 
        },{  //数据列 
            name: '授信笔数', 
            type: 'column',
            color: '#FFBC75',
            yAxis: 1, 
            data: [] 
        },{  //数据列 
            name: '授信总额', 
            type: 'column',
            color: '#4682B4',
            yAxis: 1, 
            data: [] 
        }] 
    });
    $("#loader").addClass('hide');
}

/**
 * 查询按钮
 */
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
    $("#xzqylb,#txzqylb").text("0");
    $("#zcyh,#tzcyh").text("0");
    $("#rzyh,#trzyh").text("0");
    $("#sqdk,#tsqdk").text("0");
    $("#sxbs,#tsxbs").text("0");
    $("#sxze,#tsxze").text("0");
    /*var length1 = chart1.series.length;
    while (length1 > 0) {
    	chart1.series[length1-1].remove();
    	length1--;
    }*/
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
//图表宽度控制
$("#sidebar-collapse").click(function(){
	var hasMenu=$("#sidebar").hasClass('menu-min');
	if(hasMenu==false){
		chartWidthTwo();
	}else{
		chartWidthOne();
	}
})
$(window).resize(function(){
	var hasMenu=$("#sidebar").hasClass('menu-min');
	if(hasMenu==false){
		chartWidthOne();
	}else{
		chartWidthTwo();
	}
})
function chartWidthOne(){
	var winWidth=$(window).width();
	
	var chartContainer = $(".chart-box");
	var n=$(".chart-box").length;
	var minWidth=$(window).width()-100;
	if(winWidth>991){
		var widthOne=winWidth-290;
		var widthTwo=winWidth-143;
		chartContainer.each(function(idx,container){
			container.width(widthOne);
			container.highcharts().reflow()
		});
		
		/*for(var i=1;i<=n;i++){
			$("#container"+i).width(widthOne);
		  	$("#container"+i).highcharts().reflow();
		}*/
	}else{
		/*for(var i=1;i<=n;i++){
			$("#container"+i).width(minWidth);
		  	$("#container"+i).highcharts().reflow();
		}*/
		chartContainer.each(function(idx,container){
			container.width(minWidth);
			container.highcharts().reflow()
		});
	}
}

function chartWidthTwo(){
	var winWidth=$(window).width();
	var n=$(".chart-box").length;
	var minWidth=$(window).width()-100;
	if(winWidth>991){
		var widthOne=winWidth-290;
		var widthTwo=winWidth-143;
		for(var i=1;i<=n;i++){
			$("#container"+i).width(widthTwo);
		  	$("#container"+i).highcharts().reflow();
		}
	}else{
		for(var i=1;i<=n;i++){
			$("#container"+i).width(minWidth);
		  	$("#container"+i).highcharts().reflow();
		}
	}
}