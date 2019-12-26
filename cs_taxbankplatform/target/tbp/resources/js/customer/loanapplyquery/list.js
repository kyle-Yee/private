var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'financialProduct',
    title : '产品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	//return '<a href="#" onclick="showDetail('+record.fp_id+')"><span>'+value.fpName+'</span></a>';
    	return '<a onclick="showDetail('+record.fp_id+')"><span>'+value.fpName+'</span></a>';
    }
}, {
	id : 'nsryhxxEntity',
    title : '申请人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value.nsrmc != null && value.nsrmc != ""){
    		return value.nsrmc;
    	} else {
    		return "--";
    	}
    }
}, {
    id : 'la_apply_time',
    title : '申请时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}, {
    id : 'la_first_time',
    title : '初审时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == "") {
            return '--';
        } else {
        	return value;
        }
    }
}, {
    id : 'la_end_time',
    title : '终审时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == "") {
            return '--';
        } else {
        	return value;
        }
    }
}, {
    id : 'loanStatusEntity',
    title : '审批状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value.las_name == "待初审") {
            return '<span class="orange">'+value.las_name+'</span>';
        } else if (value.las_name == "待终审") {
            return '<span class="green">'+value.las_name+'</span>';
        } else if (value.las_name == "审批完成"){
        	return '<span class="green">'+value.las_name+'</span>';
        }
    }
}, {
    id : 'loanApplyFinalEntity',
    title : '终审结果',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(record.la_status == "3"){
    		if (value.lac_id == "1") {
                return '<span class="green">同意</span>';
            } else if (value.lac_id == "2") {
                return '<span class="orange">不同意</span>';
            } else if(value.lac_id == "3"){
            	 return '<span class="orange">退单</span>';
            }
    	} else {
    		 return '<span>--</span>';
    	}
    }
}, {
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	//return '<a href="#" onclick="showDetails('+value+')"><span>查看详情</span></a>';
    	return '<a onclick="showDetails('+value+')"><span>查看详情</span></a>';
    }
}];


//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/loanapplyquery/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()){
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    $("#btnSearch").click(customSearch);
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
});


/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['apply_search_fpname'] = $("#apply_search_fpname").val();
    grid.parameters['apply_search_applicant'] = $("#apply_search_applicant").val();
    grid.parameters['apply_search_applytime1'] = $("#apply_search_applytime1").val(); 
    grid.parameters['apply_search_applytime2'] = $("#apply_search_applytime2").val();
    if(grid.parameters['apply_search_applytime1'] > grid.parameters['apply_search_applytime2']){
   	 layer.msg("申请起始时间大于结束时间!", {icon : 0});
   	 return;
   }
    grid.parameters['apply_search_firsttime1'] = $("#apply_search_firsttime1").val();
    grid.parameters['apply_search_firsttime2'] = $("#apply_search_firsttime2").val();
    if(grid.parameters['apply_search_firsttime1'] > grid.parameters['apply_search_firsttime2']){
      	 layer.msg("初审起始时间大于结束时间!", {icon : 0});
      	 return;
      }
    grid.parameters['apply_search_endtime1'] = $("#apply_search_endtime1").val();
    grid.parameters['apply_search_endtime2'] = $("#apply_search_endtime2").val();
    if(grid.parameters['apply_search_endtime1'] > grid.parameters['apply_search_endtime2']){
      	 layer.msg("终审起始时间大于结束时间!", {icon : 0});
      	 return;
      }
    grid.refresh(true);
}


/**
 * 跳转至贷款申请详细信息页
 */
function showDetails(id){
	var nav = "/loanapplyquery/applydetails.html";
	 //当前页码
    var nowPage = grid.pager.nowPage;
    //获取每页显示的记录数(即: select框中的10,20,30)
    var pageSize = grid.pager.pageSize;
    //获取排序字段
    var columnId = grid.sortParameter.columnId;
    //获取排序方式 [0-不排序，1-正序，2-倒序]
    var sortType = grid.sortParameter.sortType;
    webside.common.loadPage(nav + '?id=' + id + "&page=" + nowPage + "&rows=" + pageSize + "&sidx=" + columnId + "&sord=" + sortType);
}


/**
 * 加入时间插件
 */
$(function(){
	//(申请/初审/终审)时间
	$("#apply_search_applytime1, #apply_search_applytime2, #apply_search_firsttime1, #apply_search_firsttime2, #apply_search_endtime1, #apply_search_endtime2").datetimepicker({
		lang : "ch",
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
	});
});


/**
 * 弹出产品详细信息
 * @param id
 */
function showDetail(id){
	layer.open({
		title: "",
		type: 2,
		area: ['900px', '600px'],
		fix: false, //不固定
		maxmin: true,
		content: sys.rootPath + '/financialProduct/detail.html?id='+id
	});
}


//查看附件信息
function showAttachment(title, path){
	layer.open({
		type : 2,
        scrollbar : false,
		title : title,
		maxmin: true,
		area: ["600px", "400px"],
		content : sys.rootPath + "/loanapplyquery/showAttachment.html?path="+path
	});
}