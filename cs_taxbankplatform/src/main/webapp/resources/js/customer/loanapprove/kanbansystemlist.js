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
    	//return '<a href="#" onclick="showDetail('+record.fp_id+')">'+value.fpName+'</a>';
    	return '<a onclick="showDetail('+record.fp_id+')">'+value.fpName+'</a>';
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
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(record.la_status == 1){
    		//return '<a href="#" onclick="showDetails('+value+')">立即初审</a>';
    		return '<a onclick="showDetails('+value+')">立即初审</a>';
    	}
    	if(record.la_status == 2){
    		//return '<a href="#" onclick="showDetails('+value+')">立即终审</a>';
    		return '<a onclick="showDetails('+value+')">立即终审</a>';
    	}
    }
}];


//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
var status = $("#status").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;
var dtGridOption = {
    lang : "zh-cn",
    ajaxLoad : true,
    check : true,
    checkWidth :"37px",
    extraWidth : "37px",
    loadURL : sys.rootPath + "/loanapprove/list.html?status="+status,
    columns : dtGridColumns,
    gridContainer : "dtGridContainer",
    toolbarContainer : "dtGridToolBarContainer",
    tools : "",
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
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
    grid.parameters['apply_search_fpname'] = $("#searchKey").val();
    grid.refresh(true);
}


/**
 * 跳转至贷款申请初审页
 * @param 申请id
 */
function showDetails(id){
	var nav = "/loanapprove/detailsUI.html";
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