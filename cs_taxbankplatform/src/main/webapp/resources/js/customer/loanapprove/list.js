$(function(){
	$("#apply_star, #apply_end").datetimepicker({
		lang : "ch",
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
	});
});
var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'hidden',
    hide : true,
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'la_apply_time',
    title : '申请时间',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(record.la_status == 1){
    		var total = record.totaltime;
    		total=total*24*60;
    		var value = record.alreadytime;
    		if(value-total>0){
    			var test = value-total;
    			return record.la_apply_time;
    		}else{
    			total = total-value;
    			var min = total%60;
    			var hour = Math.floor(total/60)%24;
    			var date = Math.floor(total/60/24);
    			if(date<=2){
    				return  '<span>'+'<img src="resources/images/u1576.png" title ="'+date+'天'+hour+'时'+min+'分'+'">'+record.la_apply_time+'</span>';
    			}else{
    				return record.la_apply_time;
    			}
    		}
    	}else{
    		return record.la_apply_time;
    	}
    }
},{
    id : 'nsryhxxEntity',
    title : '企业名称',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value.qymc != null && value.qymc != ""){
    		return value.qymc;
    	} else {
    		return "--";
    	}
    }
}, {
    id : 'financialProduct',
    title : '产品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	//return '<a  href="#"  style="color:#ae8535;" onclick="showDetail('+record.fp_id+')">'+value.fpName+'</a>';
    	/*return '<a style="color:#ae8535;" onclick="showDetail('+record.fp_id+')">'+value.fpName+'</a>';*/
    	return value.fpName;
    }
	},{
	    id : 'creatorid',
	    title : '创建人id',
	    type : 'String',
	    hide : true,
	    columnClass : 'text-center',
	    hideType : 'xs',
	    headerClass : 'dlshouwen-grid-header'
	},{
    id : 'la_status',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value=="DKZT01"){
    		return '<span>' + '待初审' + '</span>';
    	}
    	if(value=="DKZT02"){
    		return '<span>' + '初审已通过' + '</span>';
    	}
    	if(value=="DKZT03"){
    		return '<span>' + '终审已通过' + '</span>';
    	}
    	if(value=="DKZT04"){
    		return '<span>' + '终审未通过' + '</span>';
    	}
    	if(value=="DKZT05"){
    		return '<span>' + '未受理' + '</span>';
    	}
    	if(value=="DKZT06"){
    		return '<span>' + '初审未通过' + '</span>';
    	}
    	if(value=="DKZT07"){
    		return '<span>' + '授信完成' + '</span>';
    	}
    	/*if(value=="DKZT08"){
    		return '<span>' + '待批准撤销' + '</span>';
    	}*/
    	if(value=="DKZT09"){
    		return '<span>' + '已撤销' + '</span>';
    	}
    	if(value=="DKZT10"){
    		return '<span>' + '已退单' + '</span>';
    	}
    }
}/*, {
    id : 'nsryhxxEntity',
    title : '申请人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value.nsrmc != null && value.nsrmc != ""){
    		return value.nsrmc;
    	} else {
    		return "--";
    	}
    }
}*//*, {
    id : 'la_apply_time',
    title : '申请时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}*/, {
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	var flag = "";
    	//待受理按钮处理
    	if(record.la_status == "DKZT01"){
    		flag = 1;//flag = 1待受理状态 待受理页面可以操作
    		return '<span id='+value+' onclick="downloadPdf('+"'"+value+"'"+','+"'"+record.creatorid+"'"+','+"'"+record.la_status+"'"+',&quot;'+record.nsryhxxEntity.qymc+'&quot;)"><img src="resources/images/u1582.png" title ="下载"></span>'+
    		'<a  onclick="showDetails('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1640.png" title ="受理"></a>'+
    		'<span><img src="resources/images/u1602.png" title =""></span>'+
    		/*'<span><img src="resources/images/u1597.png" title =""></span>';*/
    		'<span><img src="resources/images/u12525.png" title =""></span>'+
    		'<a onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}
    	//待授信按钮处理
    	if(record.la_status == "DKZT02"){
    		flag = 3;//flag=3表示受理页面可以查看但是不能操作
    		return '<span id='+value+' onclick="downloadPdf('+"'"+value+"'"+','+"'"+record.creatorid+"'"+','+"'"+record.la_status+"'"+',&quot;'+record.nsryhxxEntity.qymc+'&quot;)"><img src="resources/images/u1582.png" title ="下载"></span>'+
    		'<a  onclick="showDetails('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    		'<a  onclick="showCreditExtension('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1588.png" title ="授信审批"> </a>'+
    		/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    		'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}
    	//已授信按钮处理
    	if(record.la_status == "DKZT03"){
    		flag = 4;//flag=4表示授信结束 受理和授信页面可以查看但是不能操作
    		return '<span><img src="resources/images/u1608.png" title =""></span>'+
    		'<a  onclick="showDetails('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    		'<a  onclick="showCreditExtension('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看授信审批单"> </a>'+
    		/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    		'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}
    	//未获得受信按钮处理(授信不通过)
    	if(record.la_status == "DKZT04"){
    		flag = 2;
    		return '<span><img src="resources/images/u1608.png" title ="下载"></span>'+
    		/*'<span id='+value+' onclick="downloadPdf('+value+','+record.creatorid+','+record.la_status+','+record.nsryhxxEntity.qymc+')"><img src="resources/images/u1582.png" title ="下载"></span>'+*/
    		'<a  onclick="showDetails('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    		'<a  onclick="showCreditExtension('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看授信审批单"> </a>'+
    		/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    		'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}
    	//未受理按钮处理()
    	if(record.la_status == "DKZT05"){
    		flag = 2;
    		return '<span><img src="resources/images/u1608.png" title ="下载"></span>'+
    		'<span><img src="resources/images/u1610.png" title ="查看受理审批单"></span>'+
    		'<span><img src="resources/images/u1602.png" title =""></span>'+
    		/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    		'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}
    	//受理不通过
    	if(record.la_status == "DKZT06"){
    		flag = 2;
    		return '<span><img src="resources/images/u1608.png" title ="下载"></span>'+
    		'<a  onclick="showDetails('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    		'<span><img src="resources/images/u1602.png" title =""></span>'+
    		/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    		'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}
    	//授信完成
    	if(record.la_status == "DKZT07"){
    		flag = 2;
    		return '<span><img src="resources/images/u1608.png" title ="下载"></span>'+
    		'<a  onclick="showDetails('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    		'<a  onclick="showCreditExtension('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看授信审批单"> </a>'+
    		/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    		'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    	}
    	//待批准撤销1.如果没有进行初审 ：下载按钮 ，撤销按钮，跟踪按钮可用；2.如果已经初审过了下载按钮，查询初审按钮，撤销按钮，跟踪按钮可用
    	if(record.la_status == "DKZT08"){
    		//1.如果没有进行初审 ：下载按钮 ，撤销按钮，跟踪按钮可用；
    		if(record.las_status == 'CTZT1'){
    			flag = 1;
    			return '<span id='+value+' onclick="downloadPdf('+"'"+value+"'"+','+"'"+record.creatorid+"'"+','+"'"+record.la_status+"'"+',&quot;'+record.nsryhxxEntity.qymc+'&quot;)"><img src="resources/images/u1582.png" title ="下载"></span>'+
        		'<span><img src="resources/images/u1602.png" title =""></span>'+
        		'<span><img src="resources/images/u1602.png" title =""></span>'+
    			/*'<a onclick="showDetails('+value+','+flag+')"><img src="resources/images/u12504.png" title ="撤销"></a>'+*/
    			'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    		}
    		//2.如果已经初审过了下载按钮，查询初审按钮，撤销按钮，跟踪按钮可用
    		if(record.las_status == 'CTZT2'){
    			flag = 3;
    			return '<span id='+value+' onclick="downloadPdf('+"'"+value+"'"+','+"'"+record.creatorid+"'"+','+"'"+record.la_status+"'"+',&quot;'+record.nsryhxxEntity.qymc+'&quot;)"><img src="resources/images/u1582.png" title ="下载"></span>'+
    			'<a onclick="showDetails('+"'"+"'"+value+"'"+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    			'<span><img src="resources/images/u1602.png" title =""></span>'+
    				/*'<span onclick="showCreditExtension('+value+','+flag+')"><img src="resources/images/u12504.png" title =""></span>'+*/
    			'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    		}
    	}
    	
    	//已批准撤销1.如果没有进行初审 ：跟踪按钮可用；2.如果已经初审过了，查询初审按钮，跟踪按钮可用
    	if(record.la_status == "DKZT09"){
    		//1.如果没有进行初审 ：跟踪按钮可用；
    		if(record.las_status == 'CTZT1'){
    			return '<span><img src="resources/images/u1608.png" title ="下载"></span>'+
    			'<span><img src="resources/images/u1602.png" title =""></span>'+
    			'<span><img src="resources/images/u1602.png" title =""></span>'+
    			/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    			'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    		}
    		//2.如果已经初审过了，查询初审按钮，跟踪按钮可用
    		if(record.las_status == 'CTZT2'){
    			flag = 2;
    			return '<span><img src="resources/images/u1608.png" title ="下载"></span>'+
    			'<a onclick="showDetails('+"'"+"'"+value+"'"+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    			'<span><img src="resources/images/u1602.png" title =""></span>'+
    			/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    			'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    		}
    	}
    	//已退单1.在退单状态下 如果已经初审过了，初审查看按钮可用，跟踪按钮可用；2.在退单状态下 如果是在终审页面退单，初审，终审，跟踪按钮可用
    	if(record.la_status == "DKZT10"){
    		flag = 2;
    		//1.在退单状态下 如果已经初审过了，初审查看按钮可用，跟踪按钮可用；
    		if(record.las_status == 'CTZT1'){
    			return '<span><img src="resources/images/u1608.png" title ="下载"></span>'+
    			'<a  onclick="showDetails('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    			'<span><img src="resources/images/u1602.png" title =""></span>'+
    			/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    			'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    		}
    		//2.在退单状态下 如果是在终审页面退单，初审，终审，跟踪按钮可用
    		if(record.las_status == 'CTZT2'){
    			return '<span><img src="resources/images/u1608.png" title ="下载"></span>'+
    			'<a  onclick="showDetails('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看受理审批单"></a>'+
    			'<a  onclick="showCreditExtension('+"'"+value+"'"+','+flag+')"><img src="resources/images/u1610.png" title ="查看授信审批单"> </a>'+
    			/*'<span><img src="resources/images/u12525.png" title =""></span>'+*/
    			'<a  onclick="showTailAfter('+"'"+value+"'"+')"><img src="resources/images/u1596.png" title ="跟踪"></a>';
    		}
    	}
    }
}];


//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
var status = $("#status").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;
var dtGridOption = {
    lang : "zh-cn",
    ajaxLoad : true,
    checkWidth :"37px",
    extraWidth : "37px",
    loadURL : sys.rootPath + "/loanapprove/list.html?status="+status,
    columns : dtGridColumns,
    gridContainer : "dtGridContainer",
    toolbarContainer : "dtGridToolBarContainer",
    tools : "",
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    //$("#btnSearch").click(customSearch(""));
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch("");
        }
    };
});
/**
 * 清空查询条件
 */
function clearData(){
	$("#status").val('');
	$("#nsrmc").val('');
	$("#apply_end").val('');
	$("#apply_star").val('');
	  customSearch("");
}
 
/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch(flag) {
    grid.parameters = new Object();
    grid.parameters['apply_star'] = $("#apply_star").val(); 
    grid.parameters['apply_end'] = $("#apply_end").val();
    
    if(grid.parameters['apply_star']=='' && grid.parameters['apply_end'] != ''){
     	 layer.msg("请同时输入起始时间、结束时间!", {icon : 0});
     	 return;
     } 
    if(grid.parameters['apply_star']!='' && grid.parameters['apply_end'] == ''){
    	 layer.msg("请同时输入起始时间、结束时间!", {icon : 0});
    	 return;
    }
    
    if(grid.parameters['apply_star'] > grid.parameters['apply_end']){
   	 layer.msg("申请起始时间大于结束时间!", {icon : 0});
   	 return;
   }
    grid.parameters['nsrmc'] = $("#nsrmc").val();
    if(flag ==""){
    	var status = $("#status").val();
    	if(status==0){
    		grid.parameters['status'] = "";
    	}else{
    		grid.parameters['status'] = status;
    	}
    }else{
    	if(flag==0){
    		grid.parameters['status'] = "";
    	}else{
    		grid.parameters['status'] = flag;
    	}
    
    }
    $("#A_apply_star").val(grid.parameters['apply_star']);
	$("#A_apply_end").val(grid.parameters['apply_end']);
	$("#A_nsrmc").val(grid.parameters['nsrmc']);
	$("#A_status").val(grid.parameters['status']);
    grid.refresh(true);
}


/**
 * 跳转至贷款申请初审页
 * @param 申请id
 */
function showDetails(id,flag){
	var nav = "/loanapprove/detailsUI.html";
	 //当前页码
    var nowPage = grid.pager.nowPage;
    //获取每页显示的记录数(即: select框中的10,20,30)
    var pageSize = grid.pager.pageSize;
    //获取排序字段
    var columnId = grid.sortParameter.columnId;
    //获取排序方式 [0-不排序，1-正序，2-倒序]
    var sortType = grid.sortParameter.sortType;
    webside.common.loadPage(nav + '?id=' + id + "&flag="+ flag + "&page=" + nowPage + "&rows=" + pageSize + "&sidx=" + columnId + "&sord=" + sortType);
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
			area: ['900px', '500px'],
			maxmin: true,
		 	content: [sys.rootPath + '/loanapprove/tailafterUI.html?id='+id ,'yes'],
		 	min:function(layero){
		 		layero.css({
		 			left:0,top:$(window).height()-45//缩小在页面左下角
		 			});
		 	  }
		});    
}

/**
 * 弹出产品详细信息
 * @param id
 */
function showDetail(id){
	layer.open({
		title: "",
		type: 2,
		area: ['900px', '500px'],
		fix: false, //不固定
//		maxmin: true,
		content: sys.rootPath + '/financialProduct/detail.html?id='+id
	});
}
/**
 * 跳转到贷款受理页
 * @param 申请id
 */
function showReceive(id){
	var nav = "/loanapprove/receiveUI.html";
    webside.common.loadPage(nav + '?id=' + id );
}
/**
 * 跳转到贷款授信页
 * @param 申请id
 */
function showCreditExtension(id,flag){
	var nav = "/loanapprove/creditextensionUI.html";
	//当前页码
    var nowPage = grid.pager.nowPage;
    //获取每页显示的记录数(即: select框中的10,20,30)
    var pageSize = grid.pager.pageSize;
    //获取排序字段
    var columnId = grid.sortParameter.columnId;
    //获取排序方式 [0-不排序，1-正序，2-倒序]
    var sortType = grid.sortParameter.sortType;
    webside.common.loadPage(nav + '?id=' + id + "&flag="+ flag +"&page=" + nowPage + "&rows=" + pageSize + "&sidx=" + columnId + "&sord=" + sortType);
}

/*function test(id,creatorid){
	$.ajax({
		type : "POST",
		url : sys.rootPath + "/loanapprove/downloadsReport.html?id="+id+ "&statue=1"+"&creatorid="+creatorid,
		data : null,
		dataType : "json",
		async : true,//zzx1
		success : function(resultdata) {
			if(resultdata.isExistPdf){
//				alert("该表单后台正在准备数据，请耐心等待... ...");
				layer.alert('该表单后台正在准备数据，请耐心等待... ...', {
					  icon: 1,
					  skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
					});
				return;
			}
			if (resultdata.success) {
				layer.closeAll();
				var a = $("<a></a>").attr("href", "/attached/pdf/"+resultdata.pdfFileName).attr("download", resultdata.pdfFileName).appendTo("body");
				a[0].click();
				a.remove();
			}else{
				layer.msg(resultdata.message, {
					icon : 2
				});
			}
		}
	});
}*/

/*
 * 列表下载贷前报告
 */
function downloadPdf(id,creatorid,lasid,qymc){
	var _html = "";  
    _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + '温馨提示' + '</span>';  
    _html += '<div id="mb_msg" style="text-align:center;">' + '您确定要下载吗?' + '</div><div id="mb_btnbox">';  
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
    			url : sys.rootPath + "/loanapprove/downloadsReport.html?id="+id+ "&statue=1"+"&creatorid="+creatorid+"&lasid="+lasid+"&qymc="+qymc,
    			data : null,
    			dataType : "json",
    			async : true,//zzx2
    			beforeSend:function(){
    				layerOpenIndex = layer.load(0, {shade: 0.1, offset : ["350px",""]});
    			/*	layerOpenIndex = layer.load(0, {shade: 0.1, offset : ["350px",""]
    				,content:'正在下载，请稍等... ...',
    				success: function(layero){
    					layero.find('.layui-layer-content').css('padding-top', '-40px').css('width','150px').css('color','#000');
    					}
    				});*/ //0代表加载的风格，支持0-2
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
    				//下载按钮还原可点击状态
    				$("#"+id).html('<img src="resources/images/u1582.png" title ="下载">');
    				$("#"+id).attr("onclick","downloadPdf("+id+","+creatorid+","+lasid+",'"+qymc+"');");
    				if (resultdata.success) {
    					var a = $("<a></a>").attr("href", "/attached/pdf/"+resultdata.pdfFileName).attr("download", resultdata.pdfFileName).appendTo("body");
    					a[0].click();
    					a.remove();
//    					$("body").append(_html);
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
function GenerateCss() {  
	   
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
   
    $("#mb_msg").css({ padding: '20px', lineHeight: '20px',  
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


/*
 * 详情内下载报告
 */
function downloadFile(){  
	var id = $("#id").val();
	var creatorid = $("#creatorid").val();
	var lasid = $("#lasid").val();
	var qymc = $("#qymc").val();
	/*layer.msg("开始下载", {
        icon : 0
    });*/
	var layerOpenIndex = null;
	$.ajax({
        type : "POST",
        url : sys.rootPath + "/loanapprove/downloadsReport.html?id="+id+ "&statue=1"+"&creatorid="+creatorid+"&lasid="+lasid+"&qymc="+qymc,
        data : null,
        dataType : "json",
        async : true,
        beforeSend:function(){
        	
        	layerOpenIndex = layer.load(0, {shade: 0.1, offset : ["350px",""]});
			/*layerOpenIndex = layer.load(0, {shade: 0.1, offset : ["350px",""]
			,content:'正在下载，请稍等... ...',
			success: function(layero){
				layero.find('.layui-layer-content').css('padding-top', '-40px').css('width','150px').css('color','#000');
				}
			}); *///0代表加载的风格，支持0-2
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
//				alert("该表单后台正在准备数据，请耐心等待... ...");
				layer.alert('该表单后台正在准备数据，请耐心等待... ...', {
					  icon: 1,
					  skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
					});
				return;
			}
        	
        	if (resultdata.success) {
//        		layer.closeAll();
        		var a = $("<a></a>").attr("href", "/attached/pdf/"+resultdata.pdfFileName).attr("download", resultdata.pdfFileName).appendTo("body");
        		a[0].click();
        		a.remove();
        	}else{
        		layer.msg(resultdata.message, {
                    icon : 2
                });
        	}
        }
    });
}
function search(){
	var status = 1;
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/loanapprove/list.html?status="+status,
		data:data,
		success:function(data){
			var list = eval("(" + data + ")");
			var typeData = list.exhibitDatas;  
            if (typeData.length != 0){
            	$("#nodata").hide();
            	$("#data").show();
            	$.each(typeData, function(i, dataList) {  
                    var tbBody = ""  
                    var trColor;  

                    /*var td_report = n.report == "有"? 
                    				"<td class='border1'><span onclick='downloadFile(" +n.id +
                    				")' span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad'>" + n.report + "</span></td>":
                    				"<td class='border1'><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad'>" + n.report + "</span></td>";
                    tbBody += "<tr >" +
                    		"<td class='border1'>" + "" + "</td>" + 
                    		"<td class='border1' ><span id='"+n.id+"' onclick='toapprove("+n.id+");' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad' >" + n.nsrmc + "</span></td>" + 
                    		"<td class='border1'>" + n.sdsj + "</td>" +
                    		"<td class='border1'>" + n.bankname + "</td>" +
                    		"<td class='border1'>" + n.laamount + "</td>" +
                    		"<td class='border1'>" + n.lastatus + "</td>" +
                    		"<td class='border1'>" + n.sxsj + "</td>" +
                    		"<td class='border1'>" + n.creditquota + "</td>" +
                    		"<td class='border1'>" + n.larrate + "</td>" +
                    		td_report+"</tr>"; */
              
                    $("#table").append("<tr><td>"+ dataList.la_status +"</td></tr>"); 
                });  
                var len = exhibitDatas.length;
                for(var i = 0;i<len;i++){
                    $('#table tbody tr:eq('+i+') td:first').text(i+1);
                }
            }else {
            	$("#data").hide();
            	$("#nodata").show().html("<div style='width:1100px;'>" +
						"<span style='margin-left:440px;width:100px;color:gray;font-size:16px;'>" +
						"<img  src='resources/images/uimg01.png' " +
						"style='outline: none;width:70px;height:50px; '/>查询无数据</span></div>");
            }
            
        },  
        error: function(json) {  
       	 layer.msg("加载失败", {
	            icon : 3
	        });
		}
	});
}

/** 导出excel表格 */
function exportData(){
	console.log(grid);
	$("#exportForm").submit();
}