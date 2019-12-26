 
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	$("#fpId")[0].selectedIndex = -1;
	$("#bankId")[0].selectedIndex = -1;
	 $("#nsrsbh").val("");
	 $("#djclxDm")[0].selectedIndex = -1;
	 $("#hydm")[0].selectedIndex = -1;
	 $("#qymc").val("");
	 $("#endtime").val("");
	 $("#starttime").val("");
	 
	  
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
	 id : 'indexNo',
	 title : '序号',
	 type : 'string',
	 columnClass : 'text-center',
	 hideType : 'xs',
	 headerClass : 'dlshouwen-grid-header'
}, {
    id : 'ENTNAME',
    title : '企业名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'nsrsbh',
    title : '社会信用代码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'TBPNAME',
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
    id : 'LOANTIME',
    title : '放款日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	   hideType:'lg|md|sm|xs',
	   fastQuery:true, 
	   fastQueryType:'range' 
}, {
    id : 'LOANACCOUNT',
    title : '账号性质',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	  if ( value.length > 1) {
              return '对公';
          } else {
              return "对私";
          }
    }
},
{
    id : 'LOANACCOUNT',
    title : '对公账号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}];

//动态设置jqGrid的rowNum 增加分页 
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? 20 : pageSize;
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + "/syptzhbgqk/list.html",
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
    grid.parameters['bankId'] = $('#bankId').find("option:selected").attr("codeVal");
    grid.parameters['fpId'] = fpselect.options[index].value;
//    grid.parameters['fpId'] = $("#fpId").val();
    grid.parameters['djclxDm'] = $("#djclxDm").val();
    grid.parameters['starttime'] = $("#starttime").val();
    grid.parameters['endtime'] = $("#endtime").val();
    grid.parameters['hydm'] = $("#hydm").val();
    grid.parameters['qymc'] = $("#qymc").val();
    grid.parameters['nsrsbh'] = $("#nsrsbh").val();
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
				var fpList = result.data;
				//遍历循环金融产品list
				$.each(fpList,function(index,value) { 
					selFPs.options.add(new Option(value.fpName,value.fpId));
			  }); 
				//fpChang();
			}else{
				layer.msg(result.message, {
		            icon : 0
		        });
			}
        }
	});
}
