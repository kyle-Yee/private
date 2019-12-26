 
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	$("#fpId")[0].selectedIndex = -1;
	$("#bankId")[0].selectedIndex = -1;
	 
	 $("#nsrsbh").val("");
	 $("#jhsj").val("");
	 $("#qymc").val("");
	 
	//setTimeout('search()', 500);
});	
 

function shubiaoover(o){
	
	o.style.textDecoration="underline";//text-decoration","underline");
}
function shubiaoout(o){
	o.style.textDecoration="none";;
	//$(".forwad"+id).css("text-decoration","none");
}
 
 

var dtGridColumns = [{
	 id : 'sqxh',
	 title : '序号',
	 type : 'string',
	 columnClass : 'text-center',
	 hideType : 'xs',
	 headerClass : 'dlshouwen-grid-header'
}, {
    id : 'at_qymc',
    title : '企业名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'nsrsbh',
    title : '纳税人识别号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'tbpName',
    title : '申贷银行',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'cp_name',
    title : '产品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'createtime',
    title : '交换日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'sjx',
    title : '数据项',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return '<span onclick="cxsjx(\''+record.sqxh+'\')" style="color:#00F;">'+value+'</span>';
    }
},
{
    id : 'sssqq',
    title : '所属期起',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'sssqz',
    title : '所属起止',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'exchangetype',
    title : '交换类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
	   hideType:'lg|md|sm|xs',
	   fastQuery:true, 
	   fastQueryType:'range' ,
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if ( value  == "01") {
            return '授信审批';
        } else {
            return "贷后管理";
        }
    }
},{
    id : 'grantCode',
    title : '授权书编号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	   hideType:'lg|md|sm|xs',
	   fastQuery:true, 
	   fastQueryType:'range' 
},{
    id : 'at_sqjssj',
    title : '授权书有效期止',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	   hideType:'lg|md|sm|xs',
	   fastQuery:true, 
	   fastQueryType:'range' 
}
];

//动态设置jqGrid的rowNum 增加分页 
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? 20 : pageSize;
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + "/swsjcxtj/list.html",
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
   // showHeader:false,
    pageSizeLimit : [20,50,100]
      
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    $("#btnSearch").click(userSearch);
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
        	userSearch();
        }
    };
    
});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function userSearch() {
	var fpselect=document.getElementById("fpId");
	var index=fpselect.selectedIndex ; // selectedIndex代表的是你所选中项的index
    grid.parameters = new Object();
    grid.parameters['fpId'] = fpselect.options[index].value;
//    grid.parameters['fpId'] = $("#fpId").val();
    grid.parameters['bankCode'] = $('#bankId').find("option:selected").attr("codeVal");
    grid.parameters['nsrsbh'] = $("#nsrsbh").val();
    grid.parameters['jhsj'] = $("#jhsj").val();
    grid.parameters['qymc'] = $("#qymc").val();
    grid.refresh(true);
}
 
//选择银行时触发此事件
function bankChange(){
	//获取bankId
	var bankId = $('#bankId').val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/bankManager/findFPList.html",
		data:{"bankId":bankId},
		success:function(result){
			if(result.success){
				//清空select
				var selFPs = document.getElementById("fpId");//$("#fpId");//根据id获取select的jquery对象
				var options = $(selFPs).find("option");
				if(options.size()>1){
					$.each(options,function(index,value) { 
						if(index>0){selFPs.options.remove(index);}
				  }); 
				}
//				selFPs.html("");
				var fpList = result.data;
//				selFPs.append("<option value=''>请选择产品...</option>");//添加option
				//遍历循环金融产品list
				$.each(fpList,function(index,value) { 
					selFPs.options.add(new Option(value.fpName,value.fpId));
			    }); 
//				fpChang();
			}else{
				layer.msg(result.message, {
		            icon : 0 
		        });
			}
        }
	});
}