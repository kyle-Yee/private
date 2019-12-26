
/**
 * 查询按钮
 */
$("#btnSearch").click(function(){
	if ($("#starttime").val()>$("#endtime").val()) {
		layer.msg("请选择正确的起止时间！", {icon : 0});
		return;	
	}
	
    userSearch();
});
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	$("#bankId")[0].selectedIndex = -1;
	$("#fpId")[0].selectedIndex = -1;
	for(var i=0;i< $("input").length;i++){
        $("input")[i].value="";  
    }
	 userSearch();
});	
var dtGridColumns = [{
	 id : 'bankname',
	 title : '银行名称',
	 type : 'string',
	 columnClass : 'text-center',
	 headerClass : 'dlshouwen-grid-header',
}, {
  id : 'fpName',
  title : '金融产品名称',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
	id : 'opentime',
	title : '开通日期',
	type:'date', 
    format:'yyyy-MM-dd', 
    otype:'string', 
    oformat:'yyyy-MM-dd hh:mm:ss', 
    columnClass:'text-center',
	   hideType:'lg|md|sm|xs',
	   fastQuery:true, 
	   fastQueryType:'range' 
}, {
	id : 'datasum',
	title : '数据项',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	resolution:function(value, record, column, grid, dataNo, columnNo){
    	var dataItems=record.dataItems;
    	if(value!=null&&value!='0')
    		str = "<a onclick='openConfigDtl("+'"'+dataItems+'"'+")'>"+value+"项</a>";
    	else 
    		str = value+"项";
		return str;
	}	
}, {
  id : 'starttime',
  title : '有效期起',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
  id : 'endtime',
  title : '有效期止',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
	id : 'createtime',
	title : '设置日期',
	type:'date', 
    format:'yyyy-MM-dd', 
    otype:'string', 
    oformat:'yyyy-MM-dd hh:mm:ss', 
    columnClass:'text-center',
	   hideType:'lg|md|sm|xs',
	   fastQuery:true, 
	   fastQueryType:'range' 
}, {
  id : 'status',
  title : '状态',
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
   loadURL : sys.rootPath + "/statistics/bankManagerList.html",
   columns : dtGridColumns,
   gridContainer : 'dtGridContainer',
   toolbarContainer : 'dtGridToolBarContainer',
   tools : '',
   pageSize : pageSize,
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
   //$("#btnSearch").click(userSearch);
   
   //注册回车键事件
   document.onkeypress = function(e){
   var ev = document.all ? window.event : e;
       if(ev.keyCode==13) {
       	userSearch();
       }
   };
   
});
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
//				fpChang();
			}else{
				layer.msg(result.message, {
		            icon : 0
		        });
			}
        }
	});
}
/**
* 自定义查询
* 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
*/
function userSearch() {
	var fpselect=document.getElementById("fpId");
	var index=fpselect.selectedIndex ; // selectedIndex代表的是你所选中项的index
    grid.parameters = new Object();
    grid.parameters['fpId'] = fpselect.options[index].value;
   grid.parameters['bankid'] = $('#bankId').val();
//   grid.parameters['fpId'] = $("#fpId").val();
   grid.refresh(true);
}
function openConfigDtl(v){
	var height = Math.round(window.screen.height/2);
	var width = Math.round(window.screen.width/2);
    var top=Math.round((window.screen.height-height)/2);  
    var left=Math.round((window.screen.width-width)/2);  
    window.open(sys.rootPath +'/statistics/showBankCfgDtl.html'+"?dataStr="+v,"newwindow",   
    "height="+height+",width="+width+",top="+top+",left="+left +",toolbar=no,menubar=no,menubar=yes,scrollbars=yes,resizable=yes,location=no,status=no");
}

