var dtGridColumns = [{
    id : 'indexNo',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
//    id : 'xlzt',
//    title : '线路状态',
//    type : 'string',
//    columnClass : 'text-center',
//    headerClass : 'dlshouwen-grid-header'
//}, {
    id : 'yhmc',
    title : '银行名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'yhdm',
    title : '银行代码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'zxyys',
    title : '专线运营商',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'zxbh',
    title : '专线编号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'cjr',
    title : '创建者',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'createtime',
    title : '创建时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'updatetime',
    title : '更新时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
//    id : 'yhdkh',
//    title : '操作',
//    type : 'string',
//    columnClass : 'text-center',
//    headerClass : 'dlshouwen-grid-header',
//    resolution : function(value, record, column, grid, dataNo, columnNo) {
//    	if(record.xlzt!='待开通'){
//    		return '<a  onclick="testPort('+"'"+record.id+"'"+')">端口测试</a>';
//    	}else{
//    		return '<span></span>';
//    	}
//    }
//}, {
    id : 'yhdkh',
    title : '银行端口号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
}, {
    id : 'yhzxlldz',
    title : '银行专线链路地址',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
}, {
    id : 'yhfwqipdz',
    title : '银行服务器IP地址',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
}, {
    id : 'sjdkh',
    title : '税局端口号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
}, {
    id : 'sjzxlldz',
    title : '税局专线链路地址',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
}, {
    id : 'sjfwqipdz',
    title : '税局服务器IP地址',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
}, {
    id : 'bz',
    title : '备注',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
}, {
    id : 'bankaccount',
    title : '银行账号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
}, {
    id : 'password',
    title : '银行密码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg|md|sm|xs'
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
    loadURL : sys.rootPath + '/portManager/list.html',
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
    grid.parameters['searchKey'] = $("#searchKey").val();
    grid.refresh(true);
}

/**
 * 开通 或是 禁用端口
 * 当传入过来的参数是 xlzt02是 提示开通端口
 * 当传入过来的参数是 xlzt03是 提示禁用端口
 * 
 * @param xlzt
 */
function updateStatus(xlzt){
	var message = '';
	var resultMessage = '';
	var rows = grid.getCheckedRecords();
	if (rows.length >= 1) {
		
		if(xlzt=="xlzt02"){
			message = "您确定要开通端口吗？";
			resultMessage = "端口开通成功";
		}else if (xlzt=="xlzt03"){
			message = "您确定要禁用端口吗？";
			resultMessage = "端口禁用成功";
		}
		layer.confirm(message, {
			 skin: 'layui-layer-molv' ,
			btn: ['确定','取消'] //按钮
		}, function(){
			var bankCode="";
			for(var i =0;i<rows.length;i++){
				bankCode=rows[i].id+","+bankCode;
			}
			//var bankCode = rows[0].bankCode;
			webside.common.loadPage('/portManager/update.html?bankCode='+bankCode+"&xlzt="+xlzt);
			layer.msg(resultMessage, {icon: 1});
		});
	} else {
        layer.msg("你没有选择任何行", {
            icon : 0
        });
    }
}


/**
 * 
 * @param id
 */
function testPort(id){	
   $.ajax({   
        type: 'POST', 
        dataType : "json", 
        data : {
        	id : id
        },
        url: sys.rootPath +'/portManager/testPort.html',  
        success:function(result){  
        	var message = result.message;
        	layer.alert(message, {
        		  skin: 'layui-layer-molv' ,//样式类名
        		  btnAlign: 'c',
        		  closeBtn: 0
        		});
        }  
    });
}
/**
 * 添加专线管理的方法
 */
function add(){
	var regionId = $("#regionId").val();
	alert(regionId);
	if(regionId=="KTQY0002017010100000000000"){
		
		layer.alert('您没有操作权限', {
			
			  icon: 1,
			  
			  skin: 'layer-ext-moon'
				  
			});
		
	}else{
		
		webside.common.addModel('/portManager/addUI.html');
	}
}
