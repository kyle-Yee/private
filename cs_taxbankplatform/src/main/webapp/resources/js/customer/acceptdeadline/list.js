var dtGridColumns = [{
    id : 'indexNo',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'regionName',
    title : '区域',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'orgName',
    title : '组织',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, /*{
    id : 'laId',
    title : '贷款申请id',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},*/ {
    id : 'tlTotalDays',
    title : '贷款受理期限',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
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
    loadURL : sys.rootPath + '/acceptdeadline/list.html',
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
    grid.parameters['orgId'] = $("#searchKey").val();
    grid.refresh(true);
}

/**
 * 增加贷款期限
 */
function addDeadLine(){
	debugger;
	$.post(sys.rootPath + "/acceptdeadline/deadLineCount.html",null,function(sdata){
		if(sdata.count >= 1){
			layer.msg("已存在一个贷款受理期限",{
				icon : 2,
			});
		}else{
			webside.common.addModel('/acceptdeadline/addUI.html');
		}
	},"json");
	
}

/**
 * 删除贷款期限
 */
function deleteDeadLine(){
	var rows = grid.getCheckedRecords();
	if (rows.length != 1) {
		layer.msg("你没有选择行或选择了多行数据", {
			icon : 0
		});
	}else{
		
		$.post(sys.rootPath + "/acceptdeadline/deadLineCount.html",null,function(sdata){
			if(sdata.count <= 1){
				layer.msg("贷款受理期限数量限制为1,如要更换期限请选择编辑",{
					icon : 2,
				});
			}else{
				webside.common.delModel('/acceptdeadline/deleteUI.html', customSearch);
			}
		},"json");
		
	}
	
	
	
}