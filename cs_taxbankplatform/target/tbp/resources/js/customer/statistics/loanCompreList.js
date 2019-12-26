
/**
 * 查询按钮
 */
$("#btnSearch").click(function(){
	if ($("#starttime").val()>$("#endtime").val()) {
		layer.msg("请选择正确的起止时间！", {icon : 0});
		return;	
	}
	
    userSearch();
});
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	$("#bankId")[0].selectedIndex = -1;
	$("#fpId")[0].selectedIndex = -1;
	$("#nsrzgswj")[0].selectedIndex = -1;
	for(var i=0;i< $("input").length;i++){
        $("input")[i].value="";  
    }
	 userSearch();
});	
var dtGridColumns = [{
	 id : 'tbpname',
	 title : '银行名称',
	 type : 'string',
	 columnClass : 'text-center',
	 headerClass : 'dlshouwen-grid-header',
}, {
  id : 'fpName',
  title : '金融产品名称',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
	  id : 'grantQueryCount',
	  title : '授权查询笔数',
	  type : 'string',
	  columnClass : 'text-center',
	  headerClass : 'dlshouwen-grid-header'
}, {
	id : 'applyEntCount',
	title : '申请企业数量',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
	id : 'applyLoanCount',
	title : '申请贷款笔数',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
  id : 'auditApproveCount',
  title : '审批通过笔数',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
  id : 'auditFailureCount',
  title : '审批不予通过笔数',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
  id : 'auditingCount',
  title : '在途审批数量',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header',

}, {
  id : 'loanAmountSum',
  title : '审批授信金额合计/万元',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header',
  resolution:function(value, record, column, grid, dataNo, columnNo){
		var i = 0.00;
		if(value!='0'){
			i = parseFloat(value)/10000;
		}
		return i.toFixed(2);
	}
}, {
	id : 'loanPutOutCount',
	title : '已放款客户数',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
  id : 'loanApplyAmountSum',
  title : '申请贷款总额/万元',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header',
  resolution:function(value, record, column, grid, dataNo, columnNo){
		var i = 0.00;
		if(value!='0'){
			i = parseFloat(value)/10000;
		}
		return i.toFixed(2);
	}
}, {
	id : 'loanBalance',
	title : '贷款总余额/万元',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	resolution:function(value, record, column, grid, dataNo, columnNo){
			var i = 0.00;
			if(value!='0'){
				i = parseFloat(value)/10000;
			}
			return i.toFixed(2);
	}
}, {
	id : 'loanBalanceCount',
	title : '贷款余额户数',
	type : 'string',
	columnClass : 'text-center',
	 headerClass : 'dlshouwen-grid-header',
     hideType:'lg|md|sm|xs',
	  fastQuery:true, 
	  fastQueryType:'range'
}, {
  id : 'loanApplyAmountAvg',
  title : '平均申贷金额/万元',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header',
  hideType:'lg|md|sm|xs',
  fastQuery:true, 
  fastQueryType:'range',
  resolution:function(value, record, column, grid, dataNo, columnNo){
		var i = 0.00;
		if(value!='0'){
			i = parseFloat(value)/10000;
		}
		return i.toFixed(2);
  }
},{
  id : 'loanAmountAvg',
  title : '平均授信金额/万元',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header',
  hideType:'lg|md|sm|xs',
  fastQuery:true, 
  fastQueryType:'range',
  resolution:function(value, record, column, grid, dataNo, columnNo){
		var i = 0.00;
		if(value!='0'){
			i = parseFloat(value)/10000;
		}
		return i.toFixed(2);
  }
}];


//动态设置jqGrid的rowNum 增加分页 
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? 20 : pageSize;
var dtGridOption = {
   lang : 'zh-cn',
   ajaxLoad : true,
   check : false,
   checkWidth :'37px',
   extraWidth : '37px',
   loadURL : sys.rootPath + "/statistics/loanCompreList.html",
   columns : dtGridColumns,
   gridContainer : 'dtGridContainer',
   toolbarContainer : 'dtGridToolBarContainer',
   tools : '',
   pageSize : pageSize,
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
//   $("#btnSearch").click(userSearch);
   
   //注册回车键事件
   document.onkeypress = function(e){
   var ev = document.all ? window.event : e;
       if(ev.keyCode==13) {
       	userSearch();
       }
   };
   
});
//选择银行时触发此事件
function bankChange(){
	//获取bankId
	var bankId = $('#bankId').val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/bankManager/findFPList.html",
		data:{"bankId":bankId},
		success:function(result){
			if(result.success){
				//清空select
				var selFPs = document.getElementById("fpId");//$("#fpId");//根据id获取select的jquery对象
//				alert("获取到select的jquery对象");
				var options = $(selFPs).find("option");
//				alert("获取到option的jquery对象");
				if(options.size()>1){
//					alert("options对象>1");
					$.each(options,function(index,value) { 
//						alert("循环options对象"+index);
						if(index>0){selFPs.options.remove(index);}
				  }); 
				}
				var fpList = result.data;
//				alert("获取到产品集合："+fpList);
				//遍历循环金融产品list
				$.each(fpList,function(index,value) { 
//					alert("循环产品集合："+index+value);
					//selFPs.append("<option value='"+value.fpId+"'>"+value.fpName+"</option>");//添加option
					selFPs.options.add(new Option(value.fpName,value.fpId));
			  }); 
//				fpChang();
			}else{
				layer.msg(result.message, {
		            icon : 0
		        });
			}
        }
	});
}
/**
* 自定义查询
* 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
*/
function userSearch() {
   var fpselect=document.getElementById("fpId");
   var index=fpselect.selectedIndex ; // selectedIndex代表的是你所选中项的index
   grid.parameters = new Object();
   grid.parameters['fpId'] = fpselect.options[index].value;
   grid.parameters['bankCode'] = $('#bankId').find("option:selected").attr("codeVal");
//   grid.parameters['fpId'] = $("#fpId").val();
   grid.parameters['starttime'] = $("#starttime").val();
   grid.parameters['endtime'] = $("#endtime").val();
   grid.parameters['nsrzgswj'] = $("#nsrzgswj").val();
   grid.refresh(true);
}

