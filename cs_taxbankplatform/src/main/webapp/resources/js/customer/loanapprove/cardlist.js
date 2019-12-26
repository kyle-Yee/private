$(function(){
	$("#apply_star, #apply_end").datetimepicker({
		lang : "ch",
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
	});
});
var dtGridColumns = [{
    id : 'la_apply_time',
    title : '申请时间',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'nsryhxx_qymc',
    title : '企业名称',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value != null && value != ""){
    		return value;
    	} else {
    		return "--";
    	}
    }
}, {
    id : 'fp_name',
    title : '产品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
	},{
    id : 'la_status',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value=="DKZT01"){
    		return '<span>' + '待初审' + '</span>';
    	}
    	if(value=="DKZT02"){
    		return '<span>' + '初审已通过' + '</span>';
    	}
    	if(value=="DKZT03"){
    		return '<span>' + '终审已通过' + '</span>';
    	}
    	if(value=="DKZT04"){
    		return '<span>' + '终审未通过' + '</span>';
    	}
    	if(value=="DKZT05"){
    		return '<span>' + '未受理' + '</span>';
    	}
    	if(value=="DKZT06"){
    		return '<span>' + '初审未通过' + '</span>';
    	}
    	if(value=="DKZT07"){
    		return '<span>' + '授信完成' + '</span>';
    	}
    	if(value=="DKZT08"){
    		return '<span>' + '待批准撤销' + '</span>';
    	}
    	if(value=="DKZT09"){
    		return '<span>' + '已批准撤销' + '</span>';
    	}
    	if(value=="DKZT10"){
    		return '<span>' + '已退单' + '</span>';
    	}
    }
}, {
    id : 'la_id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	var status = record.la_status;
    	if(status != null && $.trim(status).length > 0){
    		status = parseInt(status);
    	}
    	/*1	审批中	待初审
    	2	审批中	初审已通过
    	3	已授信	终审已通过
    	4	未授信	终审未通过
    	5	未受理	未受理
    	6	未授信	初审未通过
    	7	已完成	授信完成
    	8	审批中	待批准撤销
    	9	已撤销/退单	已批准撤销
    	10	已撤销/退单	已退单*/
    	if(status == "DKZT02" || status == "DKZT06"){
    		//可看初审
    		return '<a  onclick="showDetails('+'"'+value+'"'+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    		'<span><img src="resources/images/u1602.png" title =""></span>'+
    		'<a onclick="showTailAfter('+'"'+value+'"'+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}else if(status == "DKZT03" || status == "DKZT04" || status == "DKZT07"){
    		//可看终审
    		return '<a  onclick="showDetails('+'"'+value+'"'+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    		'<a  onclick="showCreditExtension('+'"'+value+'"'+')"><img src="resources/images/u1610.png" title ="查看授信审批单"> </a>'+
    		'<a  onclick="showTailAfter('+'"'+value+'"'+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}else{//1,5,8,9,10
    		//不可查看
    		return '<span><img src="resources/images/u1602.png" title =""></span>'+
    		'<span><img src="resources/images/u1602.png" title =""></span>'+
    		'<a onclick="showTailAfter('+'"'+value+'"'+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}
    	
    }
}];


//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;
var dtGridOption = {
    lang : "zh-cn",
    ajaxLoad : true,
    checkWidth :"37px",
    extraWidth : "37px",
    loadURL : sys.rootPath + "/loancard/list.html",
    columns : dtGridColumns,
    gridContainer : "dtGridContainer",
    toolbarContainer : "dtGridToolBarContainer",
    tools : "",
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};
flag = "";

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    //$("#btnSearch").click(customSearch(""));
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch("");
        }
    };
});

/**
 * 清空查询条件
 */
function clearData(){
	$("#status").val('');
	$("#nsrmc").val('');
	$("#apply_end").val('');
	$("#apply_star").val('');
	  customSearch("");
}
 
/**
 * 自定义查询
 *
 */
function customSearch(flag) {
    grid.parameters = new Object();
    grid.parameters[''] = 1;
    grid.parameters['apply_star'] = $("#apply_star").val(); 
    grid.parameters['apply_end'] = $("#apply_end").val();
    
    if(grid.parameters['apply_star']=='' && grid.parameters['apply_end'] != ''){
     	 layer.msg("请同时输入起始时间、结束时间!", {icon : 0});
     	 return;
     } 
    if(grid.parameters['apply_star']!='' && grid.parameters['apply_end'] == ''){
    	 layer.msg("请同时输入起始时间、结束时间!", {icon : 0});
    	 return;
    }
    
    if(grid.parameters['apply_star'] > grid.parameters['apply_end']){
   	 layer.msg("申请起始时间大于结束时间!", {icon : 0});
   	 return;
   }
    grid.parameters['nsrmc'] = $("#nsrmc").val();
    if(flag ==""){
    	var status = $("#status").val();
    	if(status==0){
    		grid.parameters['status'] = "";
    	}else{
    		grid.parameters['status'] = status;
    	}
    }else{
    	if(flag==0){
    		grid.parameters['status'] = "";
    	}else{
    		grid.parameters['status'] = flag;
    	}
    
    }
    grid.pager.nowPage = 1;//默认第一页
    grid.pager.startRecord = 0;//从0开始获取
    
    //导出excel条件
    /*$("#A_apply_star").val(grid.parameters['apply_star']);
	$("#A_apply_end").val(grid.parameters['apply_end']);
	$("#A_nsrmc").val(grid.parameters['nsrmc']);
	$("#A_status").val(grid.parameters['status']);*/
    grid.refresh(true);
}


/**
 * 跳转至贷款申请初审页
 * @param 申请id
 */
function showDetails(id){
	var nav = "/loancard/toChuShenDetail.html";
//	webside.common.loadPage(nav + '?id=' + id+"\&gridPager="+getPager());
	var data = {
			"id":id,
			"gridPager":getPager()
	};
	$(".page-content").load(sys.rootPath + nav,data);
}

/**
 * 跳转到贷款授信页
 * @param 申请id
 */
function showCreditExtension(id){
	var nav = "/loancard/toZhongShenDetail.html";
//	webside.common.loadPage(nav + '?id=' + id+"\&gridPager="+getPager());
	var data = {
			"id":id,
			"gridPager":getPager()
	};
	$(".page-content").load(sys.rootPath + nav,data);
}


/**
 * 封装页面参数【分页信息/查询参数】
 */
function getPager(){
	
	var a = grid;
	c=new Object;
	c.isExport=!1;
	c.pageSize=a.pager.pageSize;
	c.startRecord=a.pager.startRecord;
	c.nowPage=a.pager.nowPage;
	c.recordCount=a.pager.recordCount?a.pager.recordCount:-1;
	c.pageCount=a.pager.pageCount?a.pager.pageCount:-1;
	c.parameters=a.parameters?a.parameters:new Object;
	c.fastQueryParameters=a.fastQueryParameters?a.fastQueryParameters:new Object;
	c.advanceQueryConditions=a.advanceQueryParameter&&a.advanceQueryParameter.advanceQueryConditions?a.advanceQueryParameter.advanceQueryConditions:new Array;
	c.advanceQuerySorts=a.advanceQueryParameter&&a.advanceQueryParameter.advanceQuerySorts?a.advanceQueryParameter.advanceQuerySorts:new Array;
	//console.log(JSON.stringify(c));
	return JSON.stringify(c);
	
}

/**
 * 弹出跟踪页面
 * @param id
 */
function showTailAfter(id){
	layer.open({
			title: "跟踪列表",
			type: 2, 
			//scrollbar : false,
			area: ['900px', '500px'],
			maxmin: true,
		 	content: [sys.rootPath + '/loanapprove/tailafterUI.html?id='+id ,'yes'],
		 	min:function(layero){
		 		layero.css({
		 			left:0,top:$(window).height()-45//缩小在页面左下角
		 			});
		 	  }
		});    
}


/** 导出excel表格 */
/*function exportData(){
	$("#exportForm").submit();
}*/