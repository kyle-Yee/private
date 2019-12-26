var dtGridColumns = [{
    id : 'indexNo',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
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
    id : 'cpmc',
    title : '产品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'cpdm',
    title : '产品代码',
    type : 'string',
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
    loadURL : sys.rootPath + '/financialProducts/list.html',
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
			webside.common.loadPage('/financialProducts/update.html?bankCode='+bankCode+"&xlzt="+xlzt);
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
        url: sys.rootPath +'/financialProducts/testPort.html',  
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
		
		webside.common.addModel('/financialProducts/addUI.html');
	}
}
