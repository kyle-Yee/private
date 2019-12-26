var dtGridColumns = [{
	 id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
    id : 'laName',
    title : '协议名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    columnStyle:'color:#337ab7;cursor:pointer;'
}, {
    id : 'createtime',
    title : '添加时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md'
}, {
    id : 'updatetime',
    title : '修改时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md'
}];
var dtGridColumnsShowOrgOnly = [{
	id : 'id',
	title : '序号',
	type : 'number',
	columnClass : 'text-center',
	hideType : 'xs',
	headerClass : 'dlshouwen-grid-header'
}, {
	id : 'laName',
	title : '协议名称',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	columnStyle:'color:#337ab7;cursor:pointer;'
}, {
	id : 'orgName',
	title : '所属组织',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
	id : 'createtime',
	title : '添加时间',
	type : 'date',
	format : 'yyyy-MM-dd hh:mm:ss',
	otype : 'string',
	oformat : 'yyyy-MM-dd hh:mm:ss',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md'
}, {
	id : 'updatetime',
	title : '修改时间',
	type : 'date',
	format : 'yyyy-MM-dd hh:mm:ss',
	otype : 'string',
	oformat : 'yyyy-MM-dd hh:mm:ss',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md'
}];
var dtGridColumnsShowOrgAndRegion = [{
	 id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
	id : 'laName',
	title : '协议名称',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	columnStyle:'color:#337ab7;cursor:pointer;'
}, {
	id : 'regionName',
	title : '所属区域',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
	id : 'orgName',
	title : '所属组织',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
	id : 'createtime',
	title : '添加时间',
	type : 'date',
	format : 'yyyy-MM-dd hh:mm:ss',
	otype : 'string',
	oformat : 'yyyy-MM-dd hh:mm:ss',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md'
}, {
	id : 'updatetime',
	title : '修改时间',
	type : 'date',
	format : 'yyyy-MM-dd hh:mm:ss',
	otype : 'string',
	oformat : 'yyyy-MM-dd hh:mm:ss',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md'
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
    loadURL : sys.rootPath + '/loanagreement/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30],
    onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	if(column.id == "laName"){
    		showDetail(record.id);
    	}
    }
};
var dtGridOptionShowOrgOnly = {
		lang : 'zh-cn',
		ajaxLoad : true,
		check : true,
		checkWidth :'37px',
		extraWidth : '37px',
		loadURL : sys.rootPath + '/loanagreement/list.html',
		columns : dtGridColumnsShowOrgOnly,
		gridContainer : 'dtGridContainer',
		toolbarContainer : 'dtGridToolBarContainer',
		tools : '',
		pageSize : pageSize,
		pageSizeLimit : [10, 20, 30],
		onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
			if(column.id == "laName"){
				showDetail(record.id);
			}
		}
};
var dtGridOptionShowOrgAndRegion = {
		lang : 'zh-cn',
		ajaxLoad : true,
		check : true,
		checkWidth :'37px',
		extraWidth : '37px',
		loadURL : sys.rootPath + '/loanagreement/list.html',
		columns : dtGridColumnsShowOrgAndRegion,
		gridContainer : 'dtGridContainer',
		toolbarContainer : 'dtGridToolBarContainer',
		tools : '',
		pageSize : pageSize,
		pageSizeLimit : [10, 20, 30],
		onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
			if(column.id == "laName"){
				showDetail(record.id);
			}
		}
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);	//银行组织管理员登陆的情况,既不显示区域列也不显示组织列
$(function() {
	var regionShow = $("#regionShow").val();
	var orgShow = $("#orgShow").val();
	if(regionShow == "true" && orgShow == "true"){		//超级管理员登陆 区域列和组织列都显示
		grid =  $.fn.dlshouwen.grid.init(dtGridOptionShowOrgAndRegion);
	}
	if(regionShow == "false" && orgShow == "true"){		//非银行组织也非超级管理员登陆——只显示组织列
		grid =  $.fn.dlshouwen.grid.init(dtGridOptionShowOrgOnly);
	}
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
    grid.parameters['laName'] = $("#searchKey").val();
    grid.refresh(true);
}

//弹出详细信息
function showDetail(id){
	layer.open({
		  title: "",
		  type: 2,
		  area: ['900px', '600px'],
		  fix: false, //不固定
		  maxmin: true,
		  content: sys.rootPath + '/loanagreement/detail.html?id='+id
		});
}

//产品编辑
function editF(id){
	webside.common.loadPage("/loanagreement/editUI.html?id="+id);
}
//产品删除
function delF(id){
	var url = "/loanagreement/deleteBatch.html";
	var res=confirm("您确定要删除吗？");
	if(res == true){
		$.ajax({
			type : "POST",
			url : sys.rootPath + url,
			data : {
				"ids" : id
			},
			dataType : "json",
			success : function(resultdata) {
				alert(resultdata.message);
				grid.refresh(true);
			}
		});
	}
}