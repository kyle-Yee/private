
var dtGridColumns = [{
	   id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
    id : 'pdiName',
    title : '账户名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'htmlTag',
    title : '选项',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
        return value.htName;
}
}
, {
    id : 'productditvaluesList',
    title : '选项值',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}
, {
    id : '',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e) {
    	if("ZZLX002"== record.ot){
    		 return '<a onclick=editUI("'+record.id +'")>修改</a> | <a onclick=delDeadline("'+record.id +'")>删除</a>';
        }else{
			return "无权限";
		}
	}

}];
var dtGridColumnsReadOnlyShowOrgAndRegion = [{ //超级管理员进入,则需要带出区域和组织(只读不可编辑)
	 id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
    id : 'pdiName',
    title : '账户名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'htmlTag',
    title : '选项',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
        return value.htName;
}
}
, {
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
    id : 'productditvaluesList',
    title : '选项值',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}
, {
    id : '',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e) {
    	if("ZZLX002"== record.ot){
    		 return '<a onclick=editUI("'+record.id +'")>修改</a> | <a onclick=delDeadline("'+record.id +'")>删除</a>';
        }else{
			return "无权限";
		}
	}

}];
var dtGridColumnsReadOnly = [{	//非超级管理员, 属于地区非银行组织管理员登陆 --只需带出组织(只读不可编辑)
	 id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}, {
    id : 'pdiName',
    title : '账户名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'htmlTag',
    title : '选项',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
        return value.htName;
}
}
, {
	id : 'regionName',
	title : '所属区域',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
    id : 'productditvaluesList',
    title : '选项值',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}
, {
    id : '',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e) {
    	if("ZZLX002"== record.ot){
       return '<a onclick=editUI("'+record.id +'")>修改</a> | <a onclick=delDeadline("'+record.id +'")>删除</a>';
        }else{
			return "无权限";
		}
	}

}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;
var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/productdataitems/list.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : '',
	    pageSize : pageSize,
	    pageSizeLimit : [10, 20, 30]
	};

var dtGridOptionReadOnlyShowOrgAndRegion = {
    lang : 'zh-cn',
    ajaxLoad : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/productdataitems/list.html',
    columns : dtGridColumnsReadOnlyShowOrgAndRegion,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};
var dtGridOptionReadOnly = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/productdataitems/list.html',
	    columns : dtGridColumnsReadOnly,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : '',
	    pageSize : pageSize,
	    pageSizeLimit : [10, 20, 30]
	};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);	//银行组织管理员登陆的情况,既不显示区域列也不显示组织列
$(function() {
	var regionShow = $("#regionShow").val();
	var orgShow = $("#orgShow").val();
	if(null != $("#readOnly").val() && '' != $("#readOnly").val()){
		if($("#readOnly").val() == "true"){//只读,则看不到操作按钮  -- 非银行组织管理登陆
			if(regionShow == "true" && orgShow == "true"){		//超级管理员登陆 区域列和组织列都显示
				grid =  $.fn.dlshouwen.grid.init(dtGridOptionReadOnlyShowOrgAndRegion);
			}
			if(regionShow == "false" && orgShow == "true"){		//非银行组织也非超级管理员登陆——只显示组织列
				grid =  $.fn.dlshouwen.grid.init(dtGridOptionReadOnly);
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
    grid.parameters['pdiName'] = $("#searchKey").val();
    grid.refresh(true);
}


		
/**
 * 逻辑删除
 */
function delDeadline(id) {
	layer.confirm('确认删除吗？', {
        icon : 3,
        title : '删除提示'
    },function(index){
		$.ajax({
            type : "POST",
            url : sys.rootPath + '/productdataitems/deleteBatch.html',
            data : {
                "id" : id
            },
            dataType : "json",
            success : function(resultdata) {
				grid.refresh(true);
			},
            error : function(errorMsg) {
                layer.msg('服务器未响应,请稍后再试', {
                    icon : 3
                });
            }
        });
        layer.close(index);
	});
}

/**
 * 编辑信息
 * @param id
 */
function editUI(id){
	layer.open({
		type : 2,
		title : "修改数据项",
		area: ['500px', '450px'],
		skin: 'layui-layer-rim',
		content: sys.rootPath + '/productdataitems/editUI.html?id='+id,
		btn : ["保存","取消"],
    	yes : function(index, layero){
    		layer.closeAll();
			grid.refresh(true);
    	},
		cancel : function(index, layero){
			layer.closeAll();
			grid.refresh(true);
		}
	});
}
