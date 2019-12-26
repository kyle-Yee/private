var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'hidden',
    hide : true,
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'createtime',
    title : '处理时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	//return value.substr(0,10);
    	return value;
    }
}, {
    id : 'userName',
    title : '处理人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'las_id',
    title : '贷款申请状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	/*if(value!=2 || value!=3 || value !=4 || value !=5 || value !=6 ||value !=7){
    		return '<span>' + '待受理' + '</span>';
    	}*/
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
    		return '<span>' + '退单' + '</span>';
    	}
    	if(value=="DKZT11"){
    		return '<span>' + '贷后管理中' + '</span>';
    	}
    }
}, {
    id : 'lar_opinion',
    title : '处理信息',
    type : 'string',
    advanceQuery:false,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;
var id = $("#findid").val();
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/loanapprove/tailafterlist.html?id='+id,
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
//    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : 5,
    pageSizeLimit : [5, 10, 15]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
   /* $("#btnSearch").click(customSearch);
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };*/
    
});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
/*function customSearch() {
    grid.parameters = new Object();
    grid.parameters['taxName'] = $("#searchKey").val();
    grid.refresh(true);
}*/
/**
 * 自定义时间格式转换
 * 
 */
Date.prototype.Format = function (fmt) {  
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}