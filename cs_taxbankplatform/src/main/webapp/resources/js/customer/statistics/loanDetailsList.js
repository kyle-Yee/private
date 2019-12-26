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
	$("#lxdm")[0].selectedIndex = -1;
	$("#hydm")[0].selectedIndex = -1;
	for(var i=0;i< $("input").length;i++){
        $("input")[i].value="";  
    }
	 userSearch();
});	
var dtGridColumns = [{
	 id : 'businessid',
	 title : '申请单ID',
	 type : 'string',
	 columnClass : 'text-center',
	 headerClass : 'dlshouwen-grid-header',
}, {
   id : 'entname',
   title : '企业名称',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
}, {
	id : 'entcreditid',
	title : '社会信用代码',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
	id : 'tbpname',
	title : '申贷银行',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
   id : 'fpName',
   title : '产品名称',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
}, {
   id : 'atId',
   title : '授权书编号',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header',
   hideType:'lg|md|sm|xs',
   fastQuery:true, 
   fastQueryType:'range' 
}, {
   id : 'atSqsj',
   title : '授权日期',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
}, {
   id : 'atSqsyxq',
   title : '授权书有效期',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
}, {
   id : 'atSqqzsj',
   title : '授权查询期起止',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header',
   hideType:'lg|md|sm|xs',
   fastQuery:true, 
   fastQueryType:'range' 
}, {
	id : 'loanterm',
	title : '贷款期限',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	 hideType:'lg|md|sm|xs',
	   fastQuery:true, 
	   fastQueryType:'range' 
}, {
	id : 'applytimestart',
	title : '贷款申请日期',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header' 
}, {
   id : 'loanGrantTime',
   title : '贷款授信日期',
   type:'string', 
   columnClass:'text-center',
	   hideType:'lg|md|sm|xs',
	   fastQuery:true, 
	   fastQueryType:'range' 
}, {
   id : 'atSjmc',
   title : '税局名称',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header',
   hideType:'lg|md|sm|xs',
   fastQuery:true, 
   fastQueryType:'range' 
},{
   id : 'djzclxmc',
   title : '登记注册类型',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header',
   hideType:'lg|md|sm|xs',
   fastQuery:true, 
   fastQueryType:'range' 
}, {
   id : 'hyMc',
   title : '行业',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header',
   hideType:'lg|md|sm|xs',
   fastQuery:true, 
   fastQueryType:'range' 
}, {
   id : 'loanamount',
   title : '授信金额',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
},{
   id : 'isPutLoan',
   title : '是否放款',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
}, {
   id : 'loantime',
   title : '放款日期',
   type:'string',  
   columnClass:'text-center',
   hideType:'lg|md|sm|xs',
   fastQuery:true, 
   fastQueryType:'range' 
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
   loadURL : sys.rootPath + "/statistics/loanDetailsList.html",
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
   //$("#btnSearch").click(userSearch);
   
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
				var options = $(selFPs).find("option");
				if(options.size()>1){
					$.each(options,function(index,value) { 
						if(index>0){selFPs.options.remove(index);}
				  }); 
				}
				var fpList = result.data;
				//遍历循环金融产品list
				$.each(fpList,function(index,value) { 
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
   grid.parameters['lxdm'] = $("#djclxDm").val();
   grid.parameters['starttime'] = $("#starttime").val();
   grid.parameters['endtime'] = $("#endtime").val();
   grid.parameters['hydm'] = $("#hydm").val();
   grid.parameters['entname'] = $("#entname").val();
   grid.parameters['entcreditid'] = $("#entcreditid").val();
   grid.parameters['isfk'] = $("#isfk").val();
   grid.refresh(true);
}

