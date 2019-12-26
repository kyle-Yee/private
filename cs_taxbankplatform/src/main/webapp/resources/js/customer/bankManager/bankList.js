var dtGridColumns = [{
    id : 'indexNo',
    title : '序号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'status',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function(value, record, column, grid, dataNo, columnNo){
		if(value=="0")
			return "待开通";
		else if(value=="1")
			return "已开通";
		else if(value=="2")
			return "已停用";
	}	
},{
    id : 'bankName',
    title : '银行名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'bankCode',
    title : '银行代码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'fpId',
    title : '产品ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'fpName',
    title : '产品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'contactName',
    title : '联系人名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs'
}
,{
    id : 'contactphone',
    title : '联系电话',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'dataCounter',
    title : '数据项',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function(value, record, column, grid, dataNo, columnNo){
    	var str="";
    	if(record.fpId!=null)
    		str = "<a onclick='openConfigDtl("+'"'+record.fpId+'"'+")'>"+value+"项</a>";
    	else 
    		str = value+"项";
		return str;
	}	
}, {
    id : 'createTime',
    title : '创建时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'updateTime',
    title : '更新时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
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
    loadURL : sys.rootPath + '/bankManager/list.html',
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
function openConfigDtl(fpId){
	var height = Math.round(window.screen.height/2);
	var width = Math.round(window.screen.width/2);
    var top=Math.round((window.screen.height-height)/2);  
    var left=Math.round((window.screen.width-width)/2);  
    window.open(sys.rootPath +'/bankManager/showBankCfgDtl.html'+"?fpId="+fpId,"newwindow",   
    "height="+height+",width="+width+",top="+top+",left="+left +",toolbar=no,menubar=no,menubar=yes,scrollbars=yes,resizable=yes,location=no,status=no");
}
//点击修改触发该事件
function editBankCfg(){
	var rows = grid.getCheckedRecords();
	if (rows.length == 1) {
		var fpId = rows[0].fpId;
		var bankCode = rows[0].bankCode;
		webside.common.loadPage('/bankManager/editBankConf.html?fpId='+fpId+'&bankCode='+bankCode);
	} else {
        layer.msg("你没有选择行或选择了多行数据", {
            icon : 0
        });
    }
}
function updateStatus(status){
	var rows = grid.getCheckedRecords();
	if (rows.length >= 1) {
		var fpIdBankCodeArr = new Array();
		for(var i =0;i<rows.length;i++){
			var perstatus = rows[i].status;
			if(perstatus==status){
				if(status==1){
					layer.msg("第"+rows[i].indexNo+"行的银行已经是开通状态", {
			            icon : 0
			        });
					return false;
				}else if(status==2){
					layer.msg("第"+rows[i].indexNo+"行的银行已经是停用状态", {
				           icon : 0
				       });
					return false;
				}
			}else if(perstatus=="0"&&status=="2"){
				layer.msg("第"+rows[i].indexNo+"行的银行是未开通状态不能停用", {
		            icon : 0
		        });
				return false;
			}
			
			var fpIdBankCodeStr = rows[i].fpId+':'+rows[i].bankCode;
			fpIdBankCodeArr.push(fpIdBankCodeStr);
		}
		webside.common.loadPage('/bankManager/updatsBankStatus.html?fpIdBankCodeStr='+fpIdBankCodeArr.join(",")+"&status="+status);
	} else {
        layer.msg("你没有选择行或选择了多行数据", {
            icon : 0
        });
    }
}