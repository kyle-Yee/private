var dtGridColumns = [{	//银行组织管理员登陆 --不需要带出组织和区域(可编辑)
	 id : 'indexNo',
	    title : '编号',
	    type : 'number',
	 //   hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	}
, {
	id : 'ptname',
    title : '类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'

}
, {
    id : 'fpName',
    title : '产品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    columnStyle:'color:#337ab7;cursor:pointer;'
}, {
    id : 'ratesEntity',
    title : '贷款利率',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
		return '<span>'+value.ratesName+'</span>';
    }
}, {
	id : 'amountEntity',
    title : '贷款额度',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
		return '<span>'+value.amountName+'</span>';
    }
}, {
	id : 'deadlineEntity',
    title : '贷款期限',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
		return '<span>'+value.deadlineName+'</span>';
    }
},{
    id : 'rwIds',
    title : '还款方式',
    type : 'string',
    width:'20%',
    columnClass : 'text-left',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return value;
    }
},{
    id : 'updatetime',
    title : '发布时间',
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
},
{
    id : 'ptDm',
    title : '产品类型',
    type : 'hidden',
    hide : true,
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'productStatusEntity',
    title : '状态',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value.id == 'CPZT01'){
            return '<span style="color: #EBCA6B;">未发布</span>';
        }if (value.id == 'CPZT02'){
            return '<span style="color: #34B244;">已发布</span>';
        }if (value.id == 'CPZT03'){
            return '<span style="color: #919191;">已下架</span>';
        }else{
        	 return '--';
        }
    }
}, {
    id : 'productStatusEntity',
    title : '操作',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value.id == 'CPZT01'){
            return '<shiro:hasPermission name="financialProduct:edit"><a onclick=editF("'+record.ptDm+'","'+record.id+'")>修改</a></shiro:hasPermission>  <shiro:hasPermission name="financialProduct:deleteBatch"><a onclick=delF("'+record.id+'")>删除</a></shiro:hasPermission>';
        }
       
        if (value.id == 'CPZT03'){
          /*  return '<a onclick=showFpOverlayPcIdsF('+record.id+') style="cursor:pointer;">扩大覆盖区域</a> <shiro:hasPermission name="financialProduct:edit"><a onclick=editF('+record.id+')>修改</a></shiro:hasPermission> <a onclick=fpRemoveF('+record.id+') style="cursor:pointer;">下架</a>';*/
            return '<a onclick=showFpOverlayPcIdsF("'+record.id+'") style="cursor:pointer;">扩大覆盖区域</a>  <a onclick=fpRemoveF("'+record.id+'") style="cursor:pointer;">下架</a>';
        }else{
        	 return '--';
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
	id : 'fpIssueTime',
	title : '发布时间',
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
	id : 'fpRemoveTime',
	title : '下架时间',
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
	title : '状态',
	type : 'String',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		  if (value.id == 'CPZT01'){
	            return '<span style="color: #EBCA6B;">未发布</span>';
	        }if (value.id == 'CPZT02'){
	            return '<span style="color: #34B244;">已发布</span>';
	        }if (value.id == 'CPZT03'){
	            return '<span style="color: #919191;">已下架</span>';
	        }else{
	        	 return '--';
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
	id : 'fpIssueTime',
	title : '发布时间',
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
	id : 'fpRemoveTime',
	title : '下架时间',
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
	title : '状态',
	type : 'String',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'xs|sm|md',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		  if (value.id == 'CPZT01'){
	            return '<span style="color: #EBCA6B;">未发布</span>';
	        }if (value.id == 'CPZT02'){
	            return '<span style="color: #34B244;">已发布</span>';
	        }if (value.id == 'CPZT03'){
	            return '<span style="color: #919191;">已下架</span>';
	        }else{
	        	 return '--';
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
    loadURL : sys.rootPath + '/financialProduct/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30],
    onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	if(column.id == "fpName"){
    		showDetail(record.id);
    	}
    }
};
var dtGridOptionReadOnly = {
		lang : 'zh-cn',
		ajaxLoad : true,
		check : true,
		checkWidth :'37px',
		extraWidth : '37px',
		loadURL : sys.rootPath + '/financialProduct/list.html',
		columns : dtGridColumnsReadOnly,
		gridContainer : 'dtGridContainer',
		toolbarContainer : 'dtGridToolBarContainer',
		tools : '',
		pageSize : pageSize,
		pageSizeLimit : [10, 20, 30],
		onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
			if(column.id == "fpName"){
				showDetail(record.id);
			}
		}
};
var dtGridOptionReadOnlyShowOrgAndRegion = {
		lang : 'zh-cn',
		ajaxLoad : true,
		check : true,
		checkWidth :'37px',
		extraWidth : '37px',
		loadURL : sys.rootPath + '/financialProduct/list.html',
		columns : dtGridColumnsReadOnlyShowOrgAndRegion,
		gridContainer : 'dtGridContainer',
		toolbarContainer : 'dtGridToolBarContainer',
		tools : '',
		pageSize : pageSize,
		pageSizeLimit : [10, 20, 30],
		onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
			if(column.id == "fpName"){
				showDetail(record.id);
			}
		}
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
    grid.parameters['fpName'] = $("#searchKey").val();
    grid.refresh(true);
}

//弹出详细信息
function showDetail(id){
	layer.open({
		  title: "",
		  type: 2,
		  area: ['900px', '400px'],
		  fix: false, //不固定
		  content: sys.rootPath + '/financialProduct/detail.html?id='+id
		});
}

//产品编辑
function editF(ptdm,id){
	webside.common.loadPage("/financialProduct/editUI.html?ptdm="+ptdm+"&id="+id);
}
//产品删除
function delF(id){
	/*var url = "/financialProduct/deleteBatch.html";
	
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
	}*/
	layer.confirm('您确定要删除吗？', {
        icon : 3,
        title : '删除提示'
    },function(index){
		$.ajax({
            type : "POST",
            url : sys.rootPath + '/financialProduct/deleteBatch.html',
            data : {
                "ids" : id
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
//产品下架
function fpRemoveF(id){
	/*var url = "/financialProduct/fpRemove.html";
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
	});*/
	layer.confirm('您确定要下架该产品吗？', {
        icon : 3,
        title : '下架提示'
    },function(index){
		$.ajax({
            type : "POST",
            url : sys.rootPath + '/financialProduct/fpRemove.html',
            data : {
                "ids" : id
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
//显示扩大覆盖区域的界面
function showFpOverlayPcIdsF(id){
	layer.open({
	      title:"扩大覆盖区域",
		  type: 2,
		  skin: 'demo-class',
		  area: ['900px', '600px'],
		  fix: false, //不固定
		  btn:'确定',
		  content: sys.rootPath + '/financialProduct/showFpOverlayPcIds.html?id='+id,
		  yes: function(i){
			  var body = layer.getChildFrame('body', i);
			  var id = body.find("input[name=ids]").val();
			  var fp_overlay_pc_ids = "";
			  	body.find("input[name='city.id']:checked").each(function(){ 
					fp_overlay_pc_ids += $(this).val()+"#"; 
				});
				var url = "/financialProduct/updateFpOverlayPcIds.html";
				$.ajax({
					async:false,
					type : "POST",
					url : sys.rootPath + url,
					data : {
						"ids" : id,
						"fp_overlay_pc_ids" : fp_overlay_pc_ids
					},
					dataType : "json",
					 success : function(resultdata) {
						 layer.msg("更新覆盖区域成功", {
			                 icon : 1
			             });
							grid.refresh(true);
						},
			            error : function(errorMsg) {
			                layer.msg('服务器未响应,请稍后再试', {
			                    icon : 3
			                });
			            }
				});
			  parent.layer.close(i);
		  }
		});
}
//扩大覆盖区域更新
function updateFpOverlayPcIdsF(id){
	var fp_overlay_pc_ids = "";
	$("input[name='provinceCitiesEntity.id']:checked").each(function(){
		fp_overlay_pc_ids += $(this).val()+"#"; 
	});
	var url = "/financialProduct/updateFpOverlayPcIds.html";
	$.ajax({
		type : "POST",
		url : sys.rootPath + url,
		data : {
			"ids" : id,
			"fp_overlay_pc_ids" : fp_overlay_pc_ids
		},
		dataType : "json",
		success : function(resultdata) {
			grid.refresh(true);
		}
	});
}