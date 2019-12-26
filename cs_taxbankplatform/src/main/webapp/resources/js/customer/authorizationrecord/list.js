var dtGridColumns = [{
    id : 'indexNo',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'at_id',
    title : '授权编号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'at_sqsj',
    title : '申请时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'  
},{
    id : 'at_qymc',
    title : '企业名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'  
},{
    id : 'at_nsrsbh',
    title : '纳税人识别号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'  
},{
    id : 'at_sqsjqx',
    title : '授权时间期限',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'  
},{
    id : 'sqzt',
    title : '授权状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 'sqjg001') {
            return '<span class="label label-sm label-success arrowed arrowed-righ">已授权</span>';
        } else  if(value == 'sqjg002'){
            return '<span class="label label-sm label-warning arrowed arrowed-righ">授权失败</span>';
        }
        else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">授权初始化</span>';
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
    loadURL : sys.rootPath + '/authorizationrecord/list.html',
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
    grid.parameters['at_qymc'] = $("#searchKey").val();
    grid.refresh(true);
}

function downloadPdf(id){
	//var params={templateIds :"fdcjysbb_temp",pdfJsonData:JSON.stringify(jsonDataSbb)};
	//var data = jQueryAjax("pdfGenerator_json2pdf", params);
	var _html = "";  
    _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + '温馨提示' + '</span>';  
    _html += '<div id="mb_msg">' + '您确定要下载吗?' + '</div><div id="mb_btnbox">';  
    _html += '<input id="mb_btn_ok" type="button" value="确定" />';  
    _html += '<input id="mb_btn_no" type="button" value="取消" />';  
    _html += '</div></div>';  
   $("body").append(_html);
    GenerateCss();
    $("#mb_btn_ok").click( function() {  
    	var a =  $("#mb_box,#mb_con").remove();
    	if(a != null){
//    		layer.msg("开始下载", {
//    			icon : 1
//    		});
    		$("#"+id).removeAttr("onclick");
    		$("#"+id).html('<img src="resources/images/u1608.png" title ="下载">');
    		var layerOpenIndex = null;
    		$.ajax({
    			type : "POST",
    			url : sys.rootPath + "/authorizationrecord/downloadsReport.html?id="+id,
    			data : null,
    			dataType : "json",
    			async : true,//zzx2
    			beforeSend:function(){
//    				layerOpenIndex = layer.load(0, {shade: 0.1, offset : ["350px",""]});
    				layerOpenIndex = layer.load(0, {shade: 0.1, offset : ["350px",""]
    				,success: function(layero){
    					layero.find('.layui-layer-content').css('padding-top', '-40px');
    					}
    				}); //0代表加载的风格，支持0-2
    			},
    			complete: function () {
    				layer.close(layerOpenIndex);
    		    },
    		    error:function(){
    		    	layer.close(layerOpenIndex);
    		    	layer.alert('下载出现异常，请联系管理员', {
						  icon: 2,
						  skin: 'layer-ext-moon'
						});
    		    },
    			success : function(resultdata) {
    				
    				if(resultdata.isExistPdf){
//    					alert("该表单后台正在准备数据，请耐心等待... ...");
    					layer.alert('该表单后台正在准备数据，请耐心等待... ...', {
    						  icon: 1,
    						  skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
    						});
    					return;
    				}
    				
    				if (resultdata.success) {
    					layer.close(layerOpenIndex);
    					var a = $("<a></a>").attr("href", "/attached/pdf/"+resultdata.pdfFileName).attr("download", resultdata.pdfFileName).appendTo("body");
    					a[0].click();
    					a.remove();
    					$("body").append(_html);
    				/*	$("#"+id).html('<img src="resources/images/u1582.png" title ="下载">');*/
    					$("#"+id).attr("onclick","downloadPdf("+'"'+id+'"'+","+'"'+creatorid+'"'+");");
    					
    				}else{
    					layer.msg(resultdata.message, {
    						icon : 2
    					});
    				}
    			}
    		});
    	}else{
    		return false;
    	}
    });  
    $("#mb_btn_no").click( function() {  
        $("#mb_box,#mb_con").remove();
    });    
}
var GenerateCss = function () {  
	   
    $("#mb_box").css({ width: '100%', height: '100%', zIndex: '99999', position: 'fixed',  
      filter: 'Alpha(opacity=60)', backgroundColor: 'black', top: '0', left: '0', opacity: '0.6'  
    });  
   
    $("#mb_con").css({ zIndex: '999999', width: '50%', position: 'fixed',  
      backgroundColor: 'White', borderRadius: '15px'  
    });  
   
    $("#mb_tit").css({ display: 'block', fontSize: '14px', color: '#444', padding: '10px 15px',  
      backgroundColor: '#DDD', borderRadius: '15px 15px 0 0',  
      borderBottom: '3px solid #009BFE', fontWeight: 'bold'  
    });  
   
    $("#mb_msg").css({ padding: '20px', lineHeight: '20px',  textAlign:'center',
      borderBottom: '1px dashed #DDD', fontSize: '13px'  
    });  
   
    $("#mb_ico").css({ display: 'block', position: 'absolute', right: '10px', top: '9px',  
      border: '1px solid Gray', width: '18px', height: '18px', textAlign: 'center',  
      lineHeight: '16px', cursor: 'pointer', borderRadius: '12px', fontFamily: '微软雅黑'  
    });  
   
    $("#mb_btnbox").css({ margin: '15px 0 10px 0', textAlign: 'center' });  
    $("#mb_btn_ok,#mb_btn_no").css({ width: '85px', height: '30px', color: 'white', border: 'none' });  
    $("#mb_btn_ok").css({ backgroundColor: '#168bbb' });  
    $("#mb_btn_no").css({ backgroundColor: 'gray', marginLeft: '20px' });  
   
   
    //右上角关闭按钮hover样式  
    $("#mb_ico").hover(function () {  
      $(this).css({ backgroundColor: 'Red', color: 'White' });  
    }, function () {  
      $(this).css({ backgroundColor: '#DDD', color: 'black' });  
    });  
   
    var _widht = document.documentElement.clientWidth; //屏幕宽  
    var _height = document.documentElement.clientHeight; //屏幕高  
   
    var boxWidth = $("#mb_con").width();  
    var boxHeight = $("#mb_con").height();  
   
    //让提示框居中  
    $("#mb_con").css({ top: (_height - boxHeight) / 2 + "px", left: (_widht - boxWidth) / 2 + "px" });  
  }

/**
 * 弹出跟踪页面
 * @param id
 */
function showTailAfter(id){
	layer.open({
			title: "跟踪列表",
			type: 2, 
			//scrollbar : false,
			area: ['900px', '200px'],
			maxmin: true,
		 	content: [sys.rootPath + '/authorizationrecord/tailafterUI.html?id='+id ,'yes'],
		 	min:function(layero){
		 		layero.css({
		 			left:0,top:$(window).height()-45//缩小在页面左下角
		 			});
		 	  }
		});    
}