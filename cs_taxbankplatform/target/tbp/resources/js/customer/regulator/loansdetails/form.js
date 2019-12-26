/*$(function(){
	setTimeout('search()', 500);
});*/
function search(){
	var data=$('#form').serialize();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/loansdetails/list.html",
		data:data,
		success:function(result){
			var list = eval("(" + result + ")");
			var data = list.loansdetailsstatistics;
			if (data.length != 0){
				$("#dataNo").hide();
				$("#dataAll").show();
				//清除原有数据，防止快速重复点击按钮造成数据重复
    			$("#table tbody").remove();
				$.each(data, function(i, n) {  
	                var tbBody = ""; 
	                tbBody += "<tr>" +
	                		"<td>" + "" + "</td>" + 
	                		"<td>" + n.qymc + "</td>" + 
	                		"<td>" + n.sdsj + "</td>" +
	                		"<td>" + n.sdyh + "</td>" +
	                		"<td>" + n.sdje + "</td>" +
	                		"<td>" + n.hpzt + "</td>" +
	                		"<td>" + n.spsj + "</td>"+
	                		"<td>" + n.sxje + "</td>"+
	                		"<td>" + n.sxll + "</td>"+	
	                		"</tr>";  
	                $("#table").append(tbBody); 
	            });  
	            var len = data.length;
	            var h1=0;
	            var h2=0;
	            for(var i = 0;i<len;i++){
	                $('#table tbody tr:eq('+i+') td:first').text(i+1);
	                $('#table tbody tr:eq('+i+')').each(function(){
	                	$(this).find('td:eq(4)').each(function() {  
	                		h1 += parseFloat($(this).text());   
	                    });  
	                	$(this).find('td:eq(7)').each(function() {
	                		h2 += parseFloat($(this).text());   
	                    }); 
	                });
	                
	            }   
	            $("#sdje,#tsdje").text(h1.toFixed(2));
	            $("#sxje,#tsxje").text(h2.toFixed(2));	
			}
			else {
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
	if($("#endtime").val()!=""&&$("#starttime").val()==""){
	   	 layer.msg("请输入正确的开始时间!", {icon : 0});
     	 return;
       
    }
	if($("#starttime").val()!=""&&$("#endtime").val()==""){
	   	 layer.msg("请输入正确的结束时间!", {icon : 0});
    	 return;
      
   }
    $("#table tbody").html("");
    //$("#sdje,#tsdje").text("0");
    //$("#sxje,#tsxje").text("0");
    userSearch();
});
/**
 * 清空按钮
 */
$("#btnReset").click(function(){
/*	document.getElementById("province")[0].selected=true;
	document.getElementById("city")[0].selected=true;
	$("#area").append("<option value='"+0+"'>"+'请选择'+"</option>");
	document.getElementById("area").lastChild.selected=true;
	document.getElementById("industries")[0].selected=true;
	document.getElementById("approve")[0].selected=true;
	for(i=0;i<$(".input").length;i++){  
        if($(".input")[i].value!=null&&$(".input")[i].value!=""){  
        	$(".input")[i].value="";  
        }  
    } */
	//setTimeout('search()', 500); 
	$("#province")[0].selectedIndex = -1;
	$("#city")[0].selectedIndex = -1;
	$("#area")[0].selectedIndex = -1;
	$("#approve")[0].selectedIndex = -1;
	$("#industries")[0].selectedIndex = -1;
	for(var i=0;i<$("input").length;i++){  
        $("input")[i].value="";  
    } 	
	 userSearch();
});
var applyAmt=0;
var authAmt=0;
var dtGridColumns = [{
	 id : 'indexNo',
	 title : '序号',
	 type : 'number',
	 columnClass : 'text-center',
	 hideType : 'xs',
	 headerClass : 'dlshouwen-grid-header'
}, {
  id : 'qymc',
  title : '企业名称',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
  id : 'sdsj',
  title : '申贷时间',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
  id : 'sdyh',
  title : '申贷银行',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
},{
  id : 'sdje',
  title : '申贷金额（万元）',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header',
  resolution:function(value, record, column, grid, dataNo, columnNo){
	applyAmt=parseFloat(parseFloat(applyAmt)+parseFloat(value));
	return (parseFloat(value)).toFixed(2);
  }
}, {
  id : 'hpzt',
  title : '获批状态',
  type : 'string',
  columnClass : 'text-center',
  headerClass : 'dlshouwen-grid-header'
}, {
	  id : 'spsj',
	  title : '授信时间',
	  type : 'string',
	  columnClass : 'text-center',
	  headerClass : 'dlshouwen-grid-header'
}, {
	  id : 'sxje',
	  title : '授信金额（万元）',
	  type : 'string',
	  columnClass : 'text-center',
	  headerClass : 'dlshouwen-grid-header',
	  resolution:function(value, record, column, grid, dataNo, columnNo){
		authAmt=parseFloat(parseFloat(authAmt)+parseFloat(value));
		return (parseFloat(value)).toFixed(2);
	}	  
}, {
	  id : 'sxll',
	  title : '授信利率',
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
  loadURL : sys.rootPath + "/loansdetails/list.html",
  columns : dtGridColumns,
  gridContainer : 'dtGridContainer',
  toolbarContainer : 'dtGridToolBarContainer',
  tools : '',
  pageSize : pageSize,
 // showHeader:false,
  pageSizeLimit : [20,50,100],
  onGridComplete:  function (data) {
	 if(grid.exhibitDatas.length>0){
		  var appendHtml = '<tr class="font-blod">'+
			'<td colspan="4" align="center"><strong>合计</strong></td>'+
			'<td id="tsdje" align="center"></td>'+
			'<td></td><td></td>'+
			'<td id="tsxje" align="center"></td><td></td></tr>';
		  $('#dtGridContainer').find('table').append(appendHtml);
		  for(var i = 0;i<grid.pager.pageSize;i++){
			  if(grid.exhibitDatas[i].applyTotalAmount!=null&&grid.exhibitDatas[i].applyTotalAmount!=''){
				  $("#sdje").text((parseFloat(grid.exhibitDatas[i].applyTotalAmount)).toFixed(2));
				  $("#sxje").text((parseFloat(grid.exhibitDatas[i].approvedTotalAmount)).toFixed(2));
				  break;
			  }
		  }
		  $("#tsdje").text((applyAmt/2).toFixed(2));
		  $("#tsxje").text((authAmt/2).toFixed(2));
	  }else{
		  $("#sdje,#tsdje").text("0.00");
		  $("#sxje,#tsxje").text("0.00");
	  }
	  applyAmt = 0;
	  authAmt =0;
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
  grid.parameters['approve'] = $("#approve").val();
  grid.parameters['industries'] = $("#industries").val();
  grid.refresh(true);
}