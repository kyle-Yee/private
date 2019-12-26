var dtGridColumns = [{
	 id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
    id : 'fpName',
    title : '产品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    columnStyle:'color:#337ab7;cursor:pointer;'
}, {
    id : 'amountEntity',
    title : '最高贷款额度',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value.id != null){
    		return value.amountName;
    	}else{
    		return '--';
    	}
    }
}, {
    id : 'ratesEntity',
    title : '贷款利率',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
        hideType : 'xs|sm|md',
        resolution : function(value, record, column, grid, dataNo, columnNo) {
        	if(value.id != null){
        		return value.ratesName;
        	}else{
        		return '--';
        	}
        }
}, {
    id : 'deadlineEntity',
    title : '最长贷款期限',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value.id != null){
    		return value.deadlineName;
    	}else{
    		return '--';
    	}
    }
}, {
    id : 'rwIds',
    title : '还款方式',
    type : 'string',
    columnClass : 'text-left',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return value;
    }
},{
    id : 'createtime',
    title : '添加时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if (value == null || value =="") {
            return "--";
        } else {
            return value;
        }
    }
}, {
    id : 'productStatusEntity',
    title : '常见问题',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return '<a onclick=editFAQ('+record.id+') style="cursor:pointer;text-decoration:none;">编辑</a>';
    }
}];
var dtGridColumnsFaqReadOnly = [{
	 id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
	id : 'fpName',
	title : '产品名称',
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
	id : 'amountEntity',
	title : '最高贷款额度',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if(value.id != null){
			return value.amountName;
		}else{
			return '--';
		}
	}
}, {
	id : 'ratesEntity',
	title : '贷款利率',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if(value.id != null){
			return value.ratesName;
		}else{
			return '--';
		}
	}
}, {
	id : 'deadlineEntity',
	title : '最长贷款期限',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if(value.id != null){
			return value.deadlineName;
		}else{
			return '--';
		}
	}
}, {
	id : 'rwIds',
	title : '还款方式',
	type : 'string',
	columnClass : 'text-left',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		return value;
	}
},{
	id : 'createtime',
	title : '添加时间',
	type : 'date',
	format : 'yyyy-MM-dd hh:mm:ss',
	otype : 'string',
	oformat : 'yyyy-MM-dd hh:mm:ss',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if (value == null || value =="") {
			return "--";
		} else {
			return value;
		}
	}
}];
var dtGridColumnsFaqReadOnlyShowOrgAndRegion = [{
	 id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
	id : 'fpName',
	title : '产品名称',
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
},{
	id : 'amountEntity',
	title : '最高贷款额度',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if(value.id != null){
			return value.amountName;
		}else{
			return '--';
		}
	}
}, {
	id : 'ratesEntity',
	title : '贷款利率',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if(value.id != null){
			return value.ratesName;
		}else{
			return '--';
		}
	}
}, {
	id : 'deadlineEntity',
	title : '最长贷款期限',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if(value.id != null){
			return value.deadlineName;
		}else{
			return '--';
		}
	}
}, {
	id : 'rwIds',
	title : '还款方式',
	type : 'string',
	columnClass : 'text-left',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		return value;
	}
}, {
	id : 'createtime',
	title : '添加时间',
	type : 'date',
	format : 'yyyy-MM-dd hh:mm:ss',
	otype : 'string',
	oformat : 'yyyy-MM-dd hh:mm:ss',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if (value == null || value =="") {
			return "--";
		} else {
			return value;
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
    loadURL : sys.rootPath + '/financialProduct/listPublish.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30],
    onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	if(column.id == "fpName"){
    		showsDetail(record.id);
    	}
    }
};
var dtGridOptionFaqReadOnly = {
		lang : 'zh-cn',
		ajaxLoad : true,
		check : true,
		checkWidth :'37px',
		extraWidth : '37px',
		loadURL : sys.rootPath + '/financialProduct/listPublish.html',
		columns : dtGridColumnsFaqReadOnly,
		gridContainer : 'dtGridContainer',
		toolbarContainer : 'dtGridToolBarContainer',
		tools : '',
		pageSize : pageSize,
		pageSizeLimit : [10, 20, 30],
		onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
			if(column.id == "fpName"){
				showsDetail(record.id);
			}
		}
};
var dtGridOptionFaqReadOnlyShowOrgAndRegion = {
		lang : 'zh-cn',
		ajaxLoad : true,
		check : true,
		checkWidth :'37px',
		extraWidth : '37px',
		loadURL : sys.rootPath + '/financialProduct/listPublish.html',
		columns : dtGridColumnsFaqReadOnlyShowOrgAndRegion,
		gridContainer : 'dtGridContainer',
		toolbarContainer : 'dtGridToolBarContainer',
		tools : '',
		pageSize : pageSize,
		pageSizeLimit : [10, 20, 30],
		onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
			if(column.id == "fpName"){
				showsDetail(record.id);
			}
		}
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
	var regionShow = $("#regionShow").val();
	var orgShow = $("#orgShow").val();
	if(null != $("#faqReadOnly").val() && '' != $("#faqReadOnly").val()){
		if($("#faqReadOnly").val() == "true"){//只读,则看不到操作按钮
			if(regionShow == "true" && orgShow == "true"){		//超级管理员登陆 区域列和组织列都显示
				grid =  $.fn.dlshouwen.grid.init(dtGridOptionFaqReadOnlyShowOrgAndRegion);
			}
			if(regionShow == "false" && orgShow == "true"){		//非银行组织也非超级管理员登陆——只显示组织列
				grid =  $.fn.dlshouwen.grid.init(dtGridOptionFaqReadOnly);
			}
		}
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
    grid.parameters['fpName'] = $("#searchKey").val();
    grid.refresh(true);
}

//产品编辑
function editFAQ(id){
	webside.common.loadPage("/financialProduct/editFAQUI.html?id="+id);
}

//弹出详细信息
function showsDetail(id){
	layer.open({
		  title: "",
		  type: 2,
		  area: ['900px', '400px'],
		  fix: false, //不固定
		  content: sys.rootPath + '/financialProduct/detail.html?id='+id
		});
}

//产品发布
function updateStatusF(){
	var url = "/financialProduct/updateStatus.html";
	var rows = grid.getCheckedRecords();
    if (rows.length > 0) {
    	var ids = "";
    	for ( var i = 0; i < rows.length; i++) {
    	  ids += rows[i].id+",";
		}
    	$.ajax({
    		type : "POST",
    		url : sys.rootPath + url,
    		data : {
    			"ids" : ids
    		},
    		dataType : "json",
    		success : function(resultdata) {
    			 layer.msg("发布成功", {
    		            icon : 1
    		        });
    			grid.refresh(true);
    		}
    	});
    } else {
        layer.msg("你没有选择行", {
            icon : 0
        });
    }
}
