/*$(function(){
	setTimeout('search()', 300);
});*/
function search(){
	var data=$('#form').serialize();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/enterprisestatistics/list.html",
		data:data,
		success:function(result){
			var list = eval("(" + result + ")");
			var data = list.enterprisestatistics;
            if (data.length != 0){
            	$("#dataNo").hide();
				$("#dataALL").show();
				//清除原有数据，防止快速重复点击按钮造成数据重复
    			$("#table tbody").remove();
            	$.each(data, function(i, n) {  
                    var tbBody = ""; 
                    tbBody += "<tr>" +
                    		"<td>" + "" + "</td>" + 
                    		"<td>" + n.zcsj + "</td>" + 
                    		"<td>" + n.qymc + "</td>" +
                    		"<td>" + n.frxm + "</td>" +
                    		"<td>" + n.frsjh + "</td>" +
                    		"<td>" + n.sfrz + "</td>" +
                    		"<td>" + n.yy + "</td>"+
                    		"</tr>"; 
                    $("#table").append(tbBody); 
                });  
                var len = data.length;
                for(var i = 0;i<len;i++){
                    $('#table tbody tr:eq('+i+') td:first').text(i+1);
                } 
            } else {

				$("#dataALL").hide();
				$("#dataNo").show().text("查询无数据");
            }  
            $("#loader").addClass('hide');
        },  
        error: function(json) {  
            alert("加载失败"); 
            $("#loader").addClass('hide');
		}
	});
}

/**
 * 查询按钮
 */
$("#btnSearch").click(function(){
	if ($("#starttime").val()>$("#endtime").val()) {
		layer.msg("请选择正确的起止时间！", {icon : 0});
		return;	
	}
	
    //$("#table tbody").html("");
    userSearch();
});
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
	$("#province")[0].selectedIndex = -1;
	$("#city")[0].selectedIndex = -1;
	$("#area")[0].selectedIndex = -1;
	for(var i=0;i< $("input").length;i++){
        $("input")[i].value="";  
    }
	 userSearch();
});	
var dtGridColumns = [{
	 id : 'indexNo',
	 title : '序号',
	 type : 'number',
	 columnClass : 'text-center',
	 hideType : 'xs',
	 headerClass : 'dlshouwen-grid-header'
}, {
   id : 'zcsj',
   title : '注册时间',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
}, {
	id : 'regPhone',
	title : '注册手机号',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
	}, {
	id : 'userName',
	title : '用户名',
	type : 'string',
	columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
   id : 'qymc',
   title : '企业名称',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
}, {
   id : 'frxm',
   title : '法人姓名',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
}, {
   id : 'frsjh',
   title : '法人手机号',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
},{
	   id : 'sfrz',
	   title : '身份认证',
	   type : 'string',
	   columnClass : 'text-center',
	   headerClass : 'dlshouwen-grid-header',
	   width:'8%',
	   resolution:function(value, record, column, grid, dataNo, columnNo){
		   
		   	        var content = '';
		 
		   	        if(value=='rzjg001'){
		   
		   	            content += '<span style="background:green;padding:2px 10px;color:white; border-radius:30px;">通过</span>';
		  
		   	        }else if(value=='rzjg002'){
		  
		   	            content += '<span style="background:red;padding:2px 10px;color:white; border-radius:30px;">不通过</span>';
		  
		   	        }
		   	          else if(value=='rzjg000'){
		   		  
		   	            content += '<span style="background:red;padding:2px 10px;color:white; border-radius:30px;">认证初始化</span>';
		  
		   	        }else{
		   	        	content +=value;
		   	        }
		
		   	        return content;
		   
		   	    }
		
}, {
   id : 'yy',
   title : '原因',
   type : 'string',
   columnClass : 'text-center',
   headerClass : 'dlshouwen-grid-header'
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
   loadURL : sys.rootPath + "/enterprisestatistics/list.html",
   columns : dtGridColumns,
   gridContainer : 'dtGridContainer',
   toolbarContainer : 'dtGridToolBarContainer',
   tools : '',
   pageSize : pageSize,
  // showHeader:false,
   pageSizeLimit : [20,50,100],
   onGridComplete:  function (data) {
	   $("#loader").hide();
 }
     
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

/**
* 自定义查询
* 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
*/
function userSearch() {
   grid.parameters = new Object();
   grid.parameters['province'] = $("#province").val();
   grid.parameters['city'] = $("#city").val();
   grid.parameters['area'] = $("#area").val();
   grid.parameters['starttime'] = $("#starttime").val();
   grid.parameters['endtime'] = $("#endtime").val();
   grid.parameters['enterprisename'] = $("#enterprisename").val();
   grid.parameters['corporate'] = $("#corporate").val();
   grid.parameters['phonenumber'] = $("#phonenumber").val();
   grid.refresh(true);
}
