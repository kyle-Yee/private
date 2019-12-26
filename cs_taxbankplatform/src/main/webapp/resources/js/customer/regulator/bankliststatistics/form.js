$(function(){
	setTimeout('search()', 500);
	
});
 
var chart1;  
var chart2; 
var chart3;
var chart4;
var chart5;
var data1 = [];
var data2 = [];
var data3 = [];
var data4 = [];
var data5 = [];


function search(){
	var data=$('#form').serialize();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/bankliststatistics/list.html",
		data:data,
		success:function(result){
			// 清除原有数据，防止快速重复点击按钮造成数据重复
			 data1 = [];
			 data2 = [];
			 data3 = [];
			 data4 = [];
			 data5 = [];
			
			var list = eval("(" + result + ")");
			var data = list.bankliststatistics;
			
            if (data.length != 0){
				$("#dataNo").hide();
				$("#dataALL").show();
				//清除原有数据，防止快速重复点击按钮造成数据重复
    			$("#table tbody").remove();
            	$.each(data, function(i, n) { 
            		//银行列表
                    var tbBody = ""; 
                    tbBody += "<tr>" +
                    		"<td>" + "" + "</td>" + 
                    		"<td>" + n.bankName + "</td>" + 
                    		"<td>" + n.dksqbs + "</td>" +
                    		"<td>" + n.dksqze + "</td>" +
                    		"<td>" + n.pjsdje + "</td>" +
                    		"<td>" + n.cgsxbs + "</td>" +
                    		"<td>" + n.cgsxed + "</td>"+
                    		"<td>" + n.pjsxed + "</td>"+
                    		"</tr>"; 
                    $("#table").append(tbBody);
                    	//var flag = false;
                    if(n.dksqbs != "0"||parseInt(n.dksqze) != 0||n.cgsxbs != "0"||parseInt(n.cgsxed) != 0){
                    	data1.push([n.bankName,parseInt(n.dksqbs)]);
                    	data2.push([n.bankName,parseFloat(n.dksqze)]);
                    	data3.push([n.bankName,parseInt(n.cgsxbs)]);
                    	data4.push([n.bankName,parseFloat(n.cgsxed)]);
                    	data5.push([n.bankName]);
                    }
                    /*if(n.dksqbs != "0"){
                    	data1.push([n.bankName,parseInt(n.dksqbs)]);
//                    	data5.push([n.bankName]);
                    	flag = true;
                    }
                    if(parseInt(n.dksqze) != 0){
                    	data2.push([n.bankName,parseFloat(n.dksqze)]);
//                    	data5.push([n.bankName]);
                    	flag = true;
                    }
                    if(n.cgsxbs != "0"){
                    	data3.push([n.bankName,parseInt(n.cgsxbs)]);
//                    	data5.push([n.bankName]);
                    	flag = true;
                    }
                    if(parseInt(n.cgsxed) != 0){
                    	data4.push([n.bankName,parseFloat(n.cgsxed)]);
//                    	data5.push([n.bankName]);
                    	flag = true;
                    }  
                    if(flag){
                    	data5.push([n.bankName]);
                    }*/
                    
                });  
                
                var len = data.length;
                var h1=0;
                var h2=0;
                var h3=0;
                var h4=0;
                var h5=0;
                var h6=0;
                for(var i = 0;i<len;i++){
                    $('#table tbody tr:eq('+i+') td:first').text(i+1);
                    $('#table tbody tr:eq('+i+')').each(function(){
                    	$(this).find('td:eq(2)').each(function() {  
                    		h1 += parseInt($(this).text().replace(/,/g,""));   //贷款申请笔数
                        });  
                    	$(this).find('td:eq(3)').each(function() {  
                    		h2 += parseFloat($(this).text().replace(/,/g,""));   //贷款申请总额
                        }); 
                    	$(this).find('td:eq(4)').each(function() { 
                    		h3 += parseFloat($(this).text().replace(/,/g,""));   //平均申贷金额
                        }); 
                    	$(this).find('td:eq(5)').each(function() {  
                    		h4 += parseInt($(this).text().replace(/,/g,""));   //成功授信笔数
                        }); 
                    	$(this).find('td:eq(6)').each(function() {  
                    		h5 += parseFloat($(this).text().replace(/,/g,""));   //成功授信额度
                        });
                    	$(this).find('td:eq(7)').each(function() {  
                    		h6 += parseFloat($(this).text().replace(/,/g,""));  //平均授信额度 
                        });
                    });
                }
                //总和计算
                $("#loanTotal,#tloanTotal").text(h2.toFixed(2));//贷款申请总额
                $("#aveAmount,#taveAmount").text((h2.toFixed(2)/h1.toFixed(2)).toFixed(2));//平均申贷金额
                $("#creditQuota,#tcreditQuota").text(h5.toFixed(2));//成功授信额度
                if(h5.toFixed(2)==0)
                	$("#aveQuota,#taveQuota").text("0.00");
                else
                	$("#aveQuota,#taveQuota").text((h5.toFixed(2)/h4.toFixed(2)).toFixed(2));//平均授信额度
                $("#amountNum,#tamountNum").text(h1);//贷款申请笔数
                $("#quotaNum,#tquotaNum").text(h4);//成功授信笔数
               
             //chart data赋值
//               data1 = null;
	    	   chart1.series[0].setData(data1);
	    	   chart2.series[0].setData(data2);
	    	   chart3.series[0].setData(data3);
	    	   chart4.series[0].setData(data4);
	    	   chart5.xAxis[0].setCategories(data5);
	           chart5.series[0].setData(data1); 
	           chart5.series[1].setData(data2); 
	           chart5.series[2].setData(data3); 
	           chart5.series[3].setData(data4);
             
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
	chart1 = new Highcharts.Chart({  
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
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y+" 笔" ;
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
        title: {  
            text: '银行贷款申请总额（万元）'  
        },  
        legend: {
			layout: 'vertical',
			align: 'right', //水平方向位置
			verticalAlign: 'middle', //垂直方向位置
		},
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y+" 万元" ;
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
        title: {  
            text: '银行成功授信笔数（笔）'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y+" 笔" ;
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
        title: {  
            text: '银行成功授信额度（万元）'  
        },  
        legend: {
        	layout: 'vertical',
        	align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
		tooltip: {  
            formatter: function() { 
            	//鼠标放在上面显示的
                return '<b>'+ this.point.name +'</b>: ' +this.point.y+" 万元" ;
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
    chart5 = new Highcharts.Chart({ 
        chart: { 
            renderTo: 'container5', //图表放置的容器，关联DIV#id 
            //因为是柱状图和曲线图共存在一个图表中，所以默认图表类型不在这里设置。
            //margin : [ 70, 200, 60, 100 ]
        },
        credits: { 
            enabled: false   //不显示LOGO 
        }, 
        title: {  
            text: '分银行展示'  
        },  
        xAxis: [{ //X轴标签 
            categories: [], 
            labels: { 
            	formatter: function() {
            		var labelVal = this.value;	//换行
            		for (var i=0;i<labelVal.length;i++){
            			if (labelVal[i].length >10){	
                			labelVal[i] = labelVal[i].substr(0,10)+"<br/>"+labelVal[i].substring(10,labelVal[i].length);
                		}
            		}
            		return labelVal;
				},
				y : 30,
            	rotation: -20,
                align: 'right' //设置右对齐 
				
            } 
        }], 
        yAxis: [{ //设置Y轴-第一个
        	allowDecimals:false,
            title: {
            	useHTML:true,
            	rotation: 0,
            	text: '贷<br/>款<br/>笔<br/>数'
            },//Y轴标题设为空 
//            tickPositions: [0, 20, 50, 100],
            labels: { 
                formatter: function() {//格式化标签名称 
                    return this.value+" 笔" ; 
                }, 
                style: { 
                    color: '#4572A7', //设置标签颜色 
                    marginLeft:'20px'
                } 
            },
            gridLineWidth: 1,
            gridLineColor: '#C0D0E0'
 
        }, { // Secondary yAxis
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
            	x:0,//zzx改前原始:-20
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
                    '贷款申请笔数': '笔', 
                    '贷款申请总额': '万元',
                    '成功授信笔数': '笔',
                    '成功授信额度': '万元',
                } [this.series.name]; 
                return this.series.name  + this.y + ' ' + unit; 
            } 
        },
        plotOptions: {
            column: {
                pointPadding: 0.05,
                borderWidth: 0,
                pointWidth: 20,
                borderColor: "",//去阴影
                shadow: false	
            }
        },
        legend: { //设置图例 
            layout: 'vertical', //水平排列图例 
            shadow: false,  //不设置阴影 
            align: 'right', //水平方向位置
        	verticalAlign: 'middle', //垂直方向位置
        },
        series: [{  //数据列 
            name: '贷款申请笔数', 
            type: 'column',
            color: '#95CEFF',
            yAxis: 0, 
            data: [] 
        },{  //数据列 
            name: '贷款申请总额', 
            type: 'column',
            color: '#5C5C61',
            yAxis: 1,  
            data: [] 
        },{  //数据列 
            name: '成功授信笔数', 
            type: 'column',
            color: '#A9FF96',
            yAxis: 0, 
            data: [] //金额数据 
        },{  //数据列 
            name: '成功授信额度', 
            type: 'column',
            color: '#FFBC75',
            yAxis: 1, 
            data: [] 
        }] 
    });
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
    $("#table tbody").html("");
    $("#loanTotal,#tloanTotal").text("0");//贷款申请总额
    $("#aveAmount,#taveAmount").text("0");//平均申贷金额
    $("#creditQuota,#tcreditQuota").text("0");//成功授信额度
    $("#aveQuota,#taveQuota").text("0");//平均授信额度
    $("#amountNum,#tamountNum").text("0");//贷款申请笔数
    $("#quotaNum,#tquotaNum").text("0");//成功授信笔数
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
    data1 = [];
    data2 = [];
    data3 = [];
    data4 = [];
    data5 = [];

    setTimeout('search()', 500);
});
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	/*document.getElementById("province")[0].selected=true;
	document.getElementById("city")[0].selected=true;
	$("#area").append("<option value='"+0+"'>"+'请选择'+"</option>");
	document.getElementById("area").lastChild.selected=true;
	document.getElementById("bank")[0].selected=true;
	for(i=0;i<$(".input").length;i++){  
        if($(".input")[i].value!=null&&$(".input")[i].value!=""){  
        	$(".input")[i].value="";  
        }  
    } */
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
	var n=$(".chart-box").length;
	var minWidth=winWidth-100;
	if(winWidth>991){
		var widthOne=(winWidth-370)/2;
		var widthTwo=(winWidth-223)/2;
		for(var i=1;i<=n;i++){
			$("#container"+i).width(widthOne);
		  	$("#container"+i).highcharts().reflow();
		}
	}else{
		for(var i=1;i<=n;i++){
			$("#container"+i).width(minWidth);
		  	$("#container"+i).highcharts().reflow();
		}
	}
}

function chartWidthTwo(){
	var winWidth=$(window).width();
	var n=$(".chart-box").length;
	var minWidth=winWidth-100;
	if(winWidth>991){
		var widthOne=(winWidth-370)/2;
		var widthTwo=(winWidth-223)/2;
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