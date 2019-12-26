var dtGridColumns = [{
    id : 'indexNo',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'accountName',
    title : '账户名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'userName',
    title : '姓名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}  , {
	id : 'ssswjDm',
	title : '所属税务局',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
    id : 'role',
    title : '所属角色',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (typeof(value) == "undefined") {
            return '未指定';
        } else {
            return value.name;
        }
    }
}, {
    id : 'deleteStatus',
    title : '是否删除',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 0) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">正常</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">已删除</span>';
        }
    }
}, {
    id : 'locked',
    title : '是否锁定',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 0) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">正常</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">已删除</span>';
        }
    }
}, {
    id : 'creatorName',
    title : '创建者',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs'
}, {
    id : 'createTime',
    title : '创建时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs'
}, {
    id : 'updateTime',
    title : '更新时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == null) {
            return '';
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
    loadURL : sys.rootPath + '/user/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
	debugger;
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
    grid.parameters['userName'] = $("#searchKey").val();
    grid.refresh(true);
}

/**
 *重置密码
 */
function resetPWDModel() {
    var rows = grid.getCheckedRecords();
    if (rows.length == 1) {
        var index;
        $.ajax({
            type : "POST",
            url : sys.rootPath + '/user/resetPassword.html',
            data : {
                "id" : rows[0].id,
                "accountName" : rows[0].accountName,
                "userName" : rows[0].userName
            },
            dataType : "json",
            beforeSend : function()
            {
                index = layer.load();
            },
            success : function(resultdata) {
                layer.close(index);
                /*layer.confirm('is not?', {icon: 3, title:'提示'}, function(index){
                	  //do something
                	  
                	  layer.close(index);
                });*/
                if (resultdata.success) {
                	layer.open({
                		  title: '消息提示：',
                		  content: resultdata.message,
                		  icon:1
                		});
                	/*layer.open(resultdata.message, {icon: 1, title:'消息提示：'}, function(index){
                  	  layer.close(index);
                  });*/
                   /* layer.msg(resultdata.message, {
                        icon : 1
                    });*/
                } else {
                	layer.open({
              		  title: '消息提示：',
              		  content: resultdata.message,
              		  icon:5
              		});
                    /*layer.msg(resultdata.message, {
                        icon : 5
                    });*/
                	/*layer.confirm(resultdata.message, {icon: 5, title:'消息提示：'}, function(index){
                    	  layer.close(index);
                    });*/
                }
            },
            error : function(data, errorMsg) {
                layer.close(index);
                layer.msg(data.responseText, {icon : 2});
            }
        });
    } else {
        layer.msg("你没有选择行或选择了多行数据", {
            icon : 0
        });
    }
}

/**
 * 当区域改变时，仅限超级管理员
 */
function regionchange(){
	var regionid =  $("#regionid").val();
	$.ajax({
        type : "POST",
        url : sys.rootPath + "/organization/regionidbyorg.html",
        data : {
            "regionid" : regionid
        },
        dataType : "json",
        async : false,
        success : function(resultdata) {
        	$("#orgid").empty();
            $("#orgid").append($("<option/>").text("请选择所属组织").attr("value",""));
        	for ( var i = 0; i < resultdata.length; i++) {
        		$("#orgid").append($("<option/>").text(resultdata[i].orgname).attr("value",resultdata[i].id));
			}
        }
    });
}

function print(){
	
	
}


/**
 * 当组织改变时，仅限区域管理员
 */
function organizationchange(){
	var regionid =  $("#regionid").val();
	var orgid =  $("#orgid").val();
	$.ajax({
        type : "POST",
        url : sys.rootPath + "/role/regorgbyrole.html",
        data : {
            "regionid" : regionid,
            "orgid":orgid
        },
        dataType : "json",
        async : false,
        success : function(resultdata) {
        	$("#roleId").empty();
            $("#roleId").append($("<option/>").text("请选择所属角色").attr("value",""));
        	for ( var i = 0; i < resultdata.length; i++) {
        		$("#roleId").append($("<option/>").text(resultdata[i].name).attr("value",resultdata[i].id));
			}
        }
    });
}

/**
 * 发送短信通知用户已创建账户
*/
function sendMessageToUser (){
	var data = $("#userForm").serialize();
	if ("添加" == $.trim($("#btnAdd").text())) {
		$.ajax({
	        type : "POST",
	        url : sys.rootPath + "/sendmessage/createUser.html",
	        data : data,
	        dataType : "json",
	        success : function(resultdata) {
	        	
	        },
	        error : function() {
	        }
		});
	}else {
		return;
	}
}