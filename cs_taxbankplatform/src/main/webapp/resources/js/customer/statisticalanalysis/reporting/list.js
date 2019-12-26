//@ sourceURL=list.js
//$(function(){
//	setTimeout('search()', 500);
//});

//自定义加法
function accAdd(arg1,arg2){  
    var r1,r2,m;  
    try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;} ; 
    try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;} ; 
    m=Math.pow(10,Math.max(r1,r2));  
    return (arg1*m+arg2*m)/m;  
}
function search(){
	var data=$('#form').serialize();
//	console.log(data);
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/reporting/list.html",
		data:data,
		success:function(result){
			var list = eval("(" + result + ")");
			var data = list.reportingDetails;
//			console.log(data);
//			var unad = list.unauthorizedDetails;
			var tes = parseInt(list.totalEnterprises.zcyh);
			var total = 0;
			if (data.length != 0){
				$("#dataNo").hide();
				$("#dataALL").show();
				
				$.each(data, function(i, n) {
					total += parseInt(n.zcyh);
				});
				
				//清除原有数据，防止快速重复点击按钮造成数据重复
    			$("#table tbody").remove();
    			$.each(data, function(i, n) {  
	                var tbBody = ""; 
	                var td_zcyh = n.zcyh=="0" ? "" +
	                	    	"<td><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" + 
	                	    	n.zcyh + "</span></td>":
	                			"<td class='cur-pointer' id='"+
	                			n.pcid+"' onclick='searchDetails("+n.taxid+");' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);'><span class='font-blue' >" +
	                			n.zcyh + "</span></td>";
	              
	                var td_rztgs = n.rztgs=="0" ? "" +
                	    	"<td><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" + 
                	    	n.rztgs + "</span></td>":
                			"<td class='cur-pointer' id='"+
                			n.pcid+"' onclick='searchDetails2("+n.taxid+");' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);'><span class='font-blue' >" +
                			n.rztgs + "</span></td>";
	                
	                var td_sxbs = n.sxbs=="0" ? "" +
                	    	"<td><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" + 
                	    	n.sxbs + "</span></td>":
                			"<td class='cur-pointer' id='"+
                			n.pcid+"' onclick='searchDetails3("+n.taxid+");' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);'><span class='font-blue' >" +
                			n.sxbs + "</span></td>";
	                
	                var td_zqyzsbl = total != 0 ? (parseInt(n.zcyh)/total*100).toFixed(2) :"0.00";
	                tbBody += "<tr>" +
	                		"<td>" + n.swjg + "</td>" + 
	                		td_zcyh +
	                		"<td>" + td_zqyzsbl +"%"+ "</td>" +
	                		td_rztgs +
	                		td_sxbs +
	                		"<td>" + n.sxze + "</td>"+
	                		"</tr>";  
	                $("#table").append(tbBody); 
//	                $('#table tr:eq(1)').after(tbBody);
//	                $('#table  #title1').after(tbBody);
	            });  
				
//				var td_zcyh1 = unad.zcyh=="0" ? "" +
//            	    	"<td><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" + 
//            	    	unad.zcyh + "</span></td>":
//            			"<td><span id='' onclick='searchDetails();' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" +
//            			unad.zcyh + "</span></td>";
//          
//	            var td_rztgs1 = unad.rztgs=="0" ? "" +
//	        	    	"<td><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" + 
//	        	    	unad.rztgs + "</span></td>":
//	        			"<td><span id='' onclick='searchDetails();' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" +
//	        			unad.rztgs + "</span></td>";
//	            
//	            var td_sxbs1 = unad.zcyh=="sxbs" ? "" +
//	        	    	"<td><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" + 
//	        	    	unad.sxbs + "</span></td>":
//	        			"<td><span id='' onclick='searchDetails();' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='font-blue' >" +
//	        			unad.sxbs + "</span></td>";
//	            
//	            var td_zqyzsbl1 = tes != 0 ? (parseInt(unad.zcyh)/tes*100).toFixed(2) :"0.00";
//	            
//				var tbBody2 ="<tr id='data'>" +
//				    		"<td>" + unad.area + "</td>" + 
//				    		td_zcyh1 +
//				    		"<td>" + td_zqyzsbl1 +"%" + "</td>" +
//				    		td_rztgs1 +
//				    		td_sxbs1 +
//				    		"<td>" + unad.sxze + "</td>"+
//				    		"</tr>"; 
//				$('#table tr:last').before(tbBody2);
				
	            var len = data.length;
	            var h1=0;
	            var h2=0;
	            var h3=0;
	            var h4=0;
	            for(var i = 0;i<len;i++){
//	                $('#table tbody tr:eq('+i+') td:first').text(i+1);
	                $('#table tbody tr:eq('+i+')').each(function(){
	                	$(this).find('td:eq(1)').each(function() {  
	                		h1 += parseInt($(this).text()); 
	                    });  
	                	$(this).find('td:eq(3)').each(function() {
	                		h2 += parseInt($(this).text());   
	                    }); 
	                	$(this).find('td:eq(4)').each(function() {
	                		h3 += parseInt($(this).text());   
	                    });
	                	$(this).find('td:eq(5)').each(function() {
	                		h4 += parseFloat($(this).text());
	                		//h4 += parseInt($(this).text());
	                    });
	                });
	                
	            }
	            var td_zqyzsblttl = total != 0 ? (h1/total*100).toFixed(2) :"0.00";
	            //合计
	            $("#zcyh").text(h1);
	            $("#zqyzsbl").text(td_zqyzsblttl+"%");            
	            $("#rztgs").text(h2);
	            $("#sxbs").text(h3);
	            $("#sxze").text(h4.toFixed(2));
			}
			else {
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
	 $("#table #data").html("");
	 userSearch();
});
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	$("#province")[0].selectedIndex = -1;
	$("#city")[0].selectedIndex = -1;
	$("#area")[0].selectedIndex = -1;
	for(var i=0;i<$("input").length;i++){  
        $("input")[i].value="";  
    } 	
	userSearch();
});	

//function  getExplorer() {
//    var explorer = window.navigator.userAgent ;
//    //ie 
//    if (explorer.indexOf("MSIE") >= 0) {
//        return 'ie';
//    }
//    //firefox 
//    else if (explorer.indexOf("Firefox") >= 0) {
//        return 'Firefox';
//    }
//    //Chrome
//    else if(explorer.indexOf("Chrome") >= 0){
//        return 'Chrome';
//    }
//    //Opera
//    else if(explorer.indexOf("Opera") >= 0){
//        return 'Opera';
//    }
//    //Safari
//    else if(explorer.indexOf("Safari") >= 0){
//        return 'Safari';
//    }
//}
//var idTmr;
//$("#btnExport").click(function (){
//	
//});
//var tableToExcel = (function() {
//    var uri = 'data:application/vnd.ms-excel;base64,',
//    template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
//      base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
//      format = function(s, c) {
//          return s.replace(/{(\w+)}/g,
//          function(m, p) { return c[p]; }) }
//      return function(table, name) {
//      if (!table.nodeType) table = document.getElementById(table)
//      var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
//      window.location.href = uri + base64(format(template, ctx))
//    }
//  })
function shubiaoover(o){
	
	o.style.textDecoration="underline";//text-decoration","underline");
}
function shubiaoout(o){
	o.style.textDecoration="none";;
	//$(".font-blue"+id).css("text-decoration","none");
}

function searchDetails(id){
	var starttime = $("#starttime").val();
	var endtime = $("#endtime").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var area = $("#area").val();
//	var data = {
//			"starttime":starttime,
//			"endtime":endtime,
//			"province":province,
//			"city":city,
//			"area":area,
//			"taxid":id,
//	};
//	webside.common.loadPage('/reporting/detailsUI.html?data='+JSON.stringify(data));
	webside.common.loadPage('/reporting/detailsUI.html?taxid='+id+'&province='+province+'&city='+city+'&area='+area+'&starttime='+starttime+'&endtime='+endtime);
}
function searchDetails2(id){
//	if(typeof(id)=="undefined"){ 
//		 id = -1;
//	} 
	var starttime = $("#starttime").val();
	var endtime = $("#endtime").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var area = $("#area").val();
//	var data = {
//			"starttime":starttime,
//			"endtime":endtime,
//			"province":province,
//			"city":city,
//			"area":area,
//			"taxid":id,
//	};
//	webside.common.loadPage('/reporting/detailsUIrztg.html?data='+JSON.stringify(data));
	webside.common.loadPage('/reporting/detailsUIrztg.html?taxid='+id+'&province='+province+'&city='+city+'&area='+area+'&starttime='+starttime+'&endtime='+endtime);
}
function searchDetails3(id){
	var starttime = $("#starttime").val();
	var endtime = $("#endtime").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var area = $("#area").val();
//	var data = {
//			"starttime":starttime,
//			"endtime":endtime,
//			"province":province,
//			"city":city,
//			"area":area,
//			"taxid":id,
//	};
//	webside.common.loadPage('/reporting/detailsUIsxbs.html?data='+JSON.stringify(data));
	webside.common.loadPage('/reporting/detailsUIsxbs.html?taxid='+id+'&province='+province+'&city='+city+'&area='+area+'&starttime='+starttime+'&endtime='+endtime);
}
//增加分页
var dtGridColumns = [{
    id : 'swjg',
    title : '税务机关名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'zcyh',
    title : '贷款用户',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function(value, record, column, grid, dataNo, columnNo){
    	if(value>0&&record.taxid>0){
    		var content='';
    		content='<a onmouseover="shubiaoover(this);" onmouseout="shubiaoout(this);" onclick="searchDetails('+record.taxid+')" class="font-blue" href="#">' + 
    		value+'</a>';
    		return content;
    	}
    	return value;
    }
}, {
    id : 'zqyzsbl',
    title : '占企业总数比例',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'rztgs',
    title : '申请笔数',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function(value, record, column, grid, dataNo, columnNo){
    	if(value>0&&record.taxid>0){
    		var content='';
        	content='<a onmouseover="shubiaoover(this);" onmouseout="shubiaoout(this);" onclick="searchDetails2('+record.taxid+')" class="font-blue" href="#">' + 
    		value+'</a>';
        	return content;
        }
    	return value;
    }
}, {
    id : 'sxbs',
    title : '授信笔数',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function(value, record, column, grid, dataNo, columnNo){
    	if(value>0&&record.taxid>0){
    		var content='';
    		content='<a onmouseover="shubiaoover(this);" onmouseout="shubiaoout(this);" onclick="searchDetails3('+record.taxid+')" class="font-blue" href="#">' + 
    		value+'</a>';
    		return content;
    	}
    	return value;
    }
},{
    id : 'sxze',
    title : '授信总额（万元）',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}];

//动态设置jqGrid的rowNum 
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? 20 : pageSize;
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + "/reporting/list.html",
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
   // showHeader:false,
    pageSizeLimit : [20, 50,100],
	onGridComplete:  function (data) {
		   $("#loader").hide();
	}
      
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    setTimeout(function(){
        grid.parameters = {};
        grid.parameters['province'] = $("#province").val();
        grid.parameters['city'] = $("#city").val();
        grid.parameters['area'] = $("#area").val();
        grid.parameters['starttime'] = $("#starttime").val();
        grid.parameters['endtime'] = $("#endtime").val();
        grid.load();
    }, 500);
    //$("#btnSearch").click(userSearch);
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
        	userSearch();
        }
    };
    
});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function userSearch() {
    grid.parameters = new Object();
    grid.parameters['province'] = $("#province").val();
    grid.parameters['city'] = $("#city").val();
    grid.parameters['area'] = $("#area").val();
    grid.parameters['starttime'] = $("#starttime").val();
    grid.parameters['endtime'] = $("#endtime").val();
    grid.refresh(true);
}
/** 导出excel表格 */
function exportData(){
	var exportData = JSON.stringify(grid.exhibitDatas);
	/*$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/reporting/downloadExport.html",
		data:{exportData:JSON.stringify(grid.exhibitDatas)},
		success:function(result){
			var list = eval("(" + result + ")");
			var data = list.reportingDetails;
		}
	});*/
	$("#exportData").val(exportData);
	if(grid.exhibitDatas.length>0)
	{
		$("#exportForm").submit();
	}
	else
		layer.msg("没有可导出数据!");
}