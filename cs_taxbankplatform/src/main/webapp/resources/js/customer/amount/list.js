var dtGridColumns = [{
    id : 'indexNo',
    title : '编号',
    type : 'number',
 //   hide : true,
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'amountName',
    title : '授信额度名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : '',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e) {
    		//权限控制，只有微银总部才有删除权限
    		if("ZZ0002017010100000000000" == record.orgId){
    			return "<a onclick='delAmount("+'"' +
        		record.id + '"' +
        		 ")'>删除</a>";
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
    loadURL : sys.rootPath + '/amount/list.html',
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
    grid.parameters['amountName'] = $("#searchKey").val();
    grid.refresh(true);
}

function delAmount(id) {
	layer.confirm('确认删除吗？', {
        icon : 3,
        title : '删除提示'
    },function(index){
    	$.ajax({
            type : "POST",
            url : sys.rootPath + '/amount/delAmount.html',
            data : {
                "id" : id
            },
            dataType : "json",
            success : function(resultdata) {
            	if (resultdata.success) {
                    layer.msg(resultdata.message, {
                        icon : 1
                    });
                    grid.refresh(true);
                } else {
                    layer.msg(resultdata.message, {
                        icon : 5
                    });
                }
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
