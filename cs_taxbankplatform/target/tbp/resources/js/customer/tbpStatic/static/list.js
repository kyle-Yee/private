var code = $("#code").val();
var dtGridColumns = [{
	   id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
    id : 'codeName',
    title : '标题',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	resolution : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e) {
    	return '<a onclick="details(' +
    	record.id +
    	')">' +
    	record.codeName +
    	'</a>';
     }
}, {
    id : 'cityName',
    title : '城市',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
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
    loadURL : sys.rootPath + '/static/list.html',
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
    if("" != code){
    	grid.parameters = new Object();
    	grid.parameters['code'] = code;
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
    grid.parameters['name'] = $("#searchKey").val();
    grid.parameters['code'] = code;
    grid.refresh(true);
}

/**
 * 详情信息
 */
function details(id){
	webside.common.loadPage('/static/details.html' + '?id=' + id);
}

/**
 * 静态信息添加画面
 * @param nav
 * @param code
 */
function addUI(nav,code){
	
	$.ajax({
		type : "POST",
		url : sys.rootPath + '/static/searchList.html',
		data : {
			code : code
		},
		async : false,
		dataType : "json",
		success : function(result){
			if(result.success){
				layer.msg("信息已经存在！",{
					icon : 2,
					offset : ["250px",""]
				});
				return;
			}else{
				webside.common.loadPage(nav + "?code=" + code);
			}
		}
	}); 
}