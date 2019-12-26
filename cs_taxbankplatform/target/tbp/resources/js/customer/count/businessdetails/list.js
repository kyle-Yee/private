/*$(function(){
	setTimeout('search()', 500);
});*/

function search(){
	var data=$('#form').serialize();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/businessdetails/list.html",
		data:data,
		success:function(data){
			var list = eval("(" + data + ")");
			var typeData = list.details;  
            if (typeData.length != 0){
            	$("#nodata").hide();
            	$("#data").show();
            	//防止快速重复点击按钮，造成数据重复
                $("#table .contentTr").remove();
            	$.each(typeData, function(i, n) {  
                    var tbBody = ""  
                    var trColor;  
                    var td_report = n.report == "有"? 
                    				"<td class='border1'><span onclick='downloadFile(" +n.id +
                    				")' span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad'>" + n.report + "</span></td>":
                    				"<td class='border1'><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad'>" + n.report + "</span></td>";
                    tbBody += "<tr class='contentTr'>" +
                    		"<td class='border1'>" + "" + "</td>" + 
//                    		"<td class='border1' ><span id='"+n.id+"' onclick='toapprove("+n.id+");' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad' >" + n.nsrmc + "</span></td>" + 
                    		"<td class='border1' ><span id='"+n.id+"'   >" + n.nsrmc + "</span></td>" +
                    		"<td class='border1'>" + n.sdsj + "</td>" +
                    		"<td class='border1'>" + n.bankname + "</td>" +
                    		"<td class='border1'>" + n.laamount + "</td>" +
                    		"<td class='border1'>" + n.lastatus + "</td>" +
                    		"<td class='border1'>" + quNull(n.lastatus,n.sxsj).substring(0,10) + "</td>" +//n.sxsj
                    		"<td class='border1'>" + quNull(n.lastatus,n.creditquota) + "</td>" + //n.creditquota
                    		"<td class='border1'>" + quNull(n.lastatus,n.larrate)  + "</td>" + //n.larrate
//                    		td_report+
                    		"</tr>"; 
              
                    $("#table").append(tbBody); 
                });  
                var len = typeData.length;
                for(var i = 0;i<len;i++){
                    $('#table tbody tr:eq('+i+') td:first').text(i+1);
                }
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
    //$("#table tbody").html("");
    userSearch();
});
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	$("#province")[0].selectedIndex = -1;
	$("#city")[0].selectedIndex = -1;
	$("#area")[0].selectedIndex = -1;
	$("#loanproduct")[0].selectedIndex = -1;
	//document.getElementById("province")[0].selected=true;
	//document.getElementById("city")[0].selected=true;
	//$("#area").append("<option value='"+0+"'>"+'请选择'+"</option>");
	//document.getElementById("area").lastChild.selected=true;
	//document.getElementById("loanproduct")[0].selected=true;
	for(var i=0;i<$(".input").length;i++){  
        if($(".input")[i].value!=null&&$(".input")[i].value!=""){  
        	$(".input")[i].value="";  
        }  
    }
	//setTimeout('search()', 500);
});	


function toapprove(id){
	webside.common.loadPage('/loanapprove/listUI.html?status=1');

//	$.ajax({
//		type:"GET",
//		url:sys.rootPath + "/loanapprove/listUI.html?status=1",
//		dataType:"json",
//		success:function(data){
//			
//		},
//		error: function(json) {  
//            alert("加载失败");  
//		}
//	});	
	
}

function shubiaoover(o){
	
	o.style.textDecoration="underline";//text-decoration","underline");
}
function shubiaoout(o){
	o.style.textDecoration="none";;
	//$(".forwad"+id).css("text-decoration","none");
}
function downloadFile(id){
	$.ajax({
        type : "POST",
        url : sys.rootPath + "/businessdetails/downloadReport.html?id="+id,
        data : null,
        dataType : "json",
        async : false,
        success : function(resultdata) {
        	if (resultdata.success) {
        		if (resultdata.pdfFileName == ""){
        			
        			layer.msg(resultdata.message1, {
                        icon : 2
                    });
        		}else {
        			var a = $("<a></a>").attr("href", "/attached/pdf/"+resultdata.pdfFileName).attr("download", resultdata.pdfFileName).appendTo("body");
        			a[0].click();
        		
        			
        			return;
            		a.remove();
        		} 
        	}else{
        		layer.msg(resultdata.message1, {
                    icon : 2
                });
        	}
        }
    });
}

function quNull(status,data){
	if(status=='授信完成'||status=='终审已通过'){
		return data;
	}else{
		return '';
	}
}

var dtGridColumns = [{
	 id : 'indexNo',
	 title : '序号',
	 type : 'number',
	 columnClass : 'text-center',
	 hideType : 'xs',
	 headerClass : 'dlshouwen-grid-header'
}, {
    id : 'nsrmc',
    title : '企业名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'sdsj',
    title : '申贷时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'bankname',
    title : '办理行',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'laamount',
    title : '申贷金额_(万元)',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'lastatus',
    title : '审批状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'sxsj',
    title : '授信时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'creditquota',
    title : '授信金额_(万元)',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'larrate',
    title : '批贷利率',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}
];

//动态设置jqGrid的rowNum 增加分页 
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? 20 : pageSize;
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + "/businessdetails/list.html",
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
   // showHeader:false,
    pageSizeLimit : [20,50,100]
      
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
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
    grid.parameters['loanproduct'] = $("#loanproduct").val();
    grid.parameters['subbranch'] = $("#subbranch").val();
    grid.refresh(true);
}
