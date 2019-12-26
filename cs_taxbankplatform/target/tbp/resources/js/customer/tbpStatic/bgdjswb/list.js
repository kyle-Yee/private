var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'fileName',
    title : '文件名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}, {
    id : 'enabled',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	resolution : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e) {
    	if(value == 'Y')
        {
            return '<span class="label label-sm label-success arrowed arrowed-righ">有效</span>';
        }else
        {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">无效</span>';
        }
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
    loadURL : sys.rootPath + '/bgdjswb/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
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
    grid.parameters['fileName'] = $("#searchKey").val();
    grid.refresh(true);
}

/**
 * 添加按钮(判断数据库中是否存在)
 */
function addBt(){
	$.ajax({
		type : "post",
		url : sys.rootPath + "/bgdjswb/ckByRegionId.html",
        dataType : "json",
        success : function(resultdata) {
        	if (resultdata.success) {
        		//数据库中不存在
        		webside.common.addModel('/bgdjswb/addUI.html');
            } else {
            	//数据库中存在
                layer.msg(resultdata.message, {
                    icon : 5
                });
                return;
            }
        }
	});
}