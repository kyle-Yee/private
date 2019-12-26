$(function(){
	$("#star, #end").datetimepicker({
		lang : "ch",
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
	});
});

var dtGridColumns = [{
    id : 'la_id',
    title : '编号',
    type : 'hidden',
    hide : true,
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'loanApproveQueryEntity',
    title : '创建人',
    type : '',
    hide : true,
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value.creatorid != null && value.creatorid != ""){
    		return value.creatorid;
    	} else {
    		return "--";
    	}
    	
    }
    	
},
{
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
}/*, {
    id : 'loanApproveRecList',
    title : '贷款开始日期',
    type:'date', 
    format:'yyyy-MM-dd',
    otype:'string',
    oformat:'yyyy-MM-dd',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return value.lar_begin;
    }
}*/
, {
    id : 'begin',
    title : '贷款开始日期',
    type:'string', 
   
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return record.begin;
    }
}
,  {
    id : 'loanApproveRecList',
    title : '贷款期限',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return value.lar_loan_deadline+"个月";
    }
}
, {
    id : 'loanApproveRecList',
    title : '贷款金额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return value.lar_credit_quota+"万元";
    }
},{
    id : 'loan_down_date',
    title : '最近一次报告下载日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	 if(record.loan_down_date==null||record.loan_down_date==""){
    	    	return "--";
    	    }else{
    	    	return record.loan_down_date;
    	    }     
    	
    }
}
,

{
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	 return "<a style='color: #00A2CA;' onclick='downloadPdf(" +
    	 '"'+record.la_id +'"'+
         ","+'"'+record.loanApproveQueryEntity.creatorid+'"'+
         ",&quot;"+record.nsryhxxEntity.qymc+"&quot;)'>下载当前报告</a> | <a style='color: #00A2CA;'  onclick='showCrediToTerminate(" +
         '"'+ record.id +'"'+
         ")'>授信终止</a>";
    	
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
    loadURL : sys.rootPath + "/loanapprove/postloanlist.html",
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
    grid.parameters['start'] = $("#star").val(); 
    grid.parameters['end'] = $("#end").val();
  
    if($("#area").val()!=0){
    	  grid.parameters['pcId'] = $("#area").val();
    }else if($("#area").val() ==0 && $("#city").val() !=0) {
    	grid.parameters['pcId'] =$("#city").val();
	}else {
		 grid.parameters['pcId'] = $("#province").val();
	}
   if(grid.parameters['start']!=""&& grid.parameters['end']==""){
      	 layer.msg("请输入正确的授信开始时间!", {icon : 0});
      	 return;
        
     }
    if(grid.parameters['start']==""&& grid.parameters['end']!=""){
     	 layer.msg("请输入正确的授信开始时间!", {icon : 0});
     	 return;
       
    }
  if(grid.parameters['start']!=""&& grid.parameters['end']!=""){
   if(grid.parameters['start'] > grid.parameters['end']){
   	 layer.msg("查询起始时间大于结束时间!", {icon : 0});
   	 return;
     }
  }
    grid.parameters['nsrmc'] = $("#nsrmc").val();


    grid.refresh(true);
}

/**
 * 弹出授信页
 * @param id
 */
function showCrediToTerminate(id){
	layer.open({
		title: "授信终止",
		type: 2,
		area: ['600px', '430px'],
		fix: false, //不固定
		maxmin: true,
		btn : ["保存","返回"],
		content: sys.rootPath + '/loanapprove/crediToTerminateUI.html?id='+id,
		yes : function(i){
			
			var body = layer.getChildFrame('body', i);
		
			
			var bankloan_type = body.find("#SEL").select().val();
			var tthNsrmc = body.find("#tthNsrmc").val();
			var tthNsrsbh = body.find("#tthNsrsbh").val();
			var time = body.find("#time").val();
			var lae_credit_quota = body.find("#lae_credit_quota").val();
			var lae_overdue_count = body.find("#lae_overdue_count").val();
			var la_id = body.find("#la_id").val();
			var laf_id = body.find("#laf_id").val();
			var lac_id = body.find("#lac_id").val();
			var laname = body.find("#laname").val();
			if(lae_credit_quota == null ||lae_credit_quota == ""){
				$("#lae_credit_quota").focus();
				 layer.msg("请输入授信完成额度!", {icon : 0});
				 return;
			}
			if(lae_credit_quota <=0||isNaN(lae_credit_quota)){
				$("#lae_credit_quota").focus();
				 layer.msg("请输入正确的授信完成额度!", {icon : 0});
				 return;
			}
			if(lae_overdue_count <0||isNaN(lae_overdue_count)){
				$("#lae_overdue_count").focus();
				 layer.msg("请输入准确的逾越次数!", {icon : 0});
				 return;
			}
			 var r=/^([1-9]\d*|[0]{1,1})$/;
		
			 if(! (r.test(lae_overdue_count))){
				 $("#lae_overdue_count").focus();
				 layer.msg("请输入准确的逾越次数!", {icon : 0});
				 return;
			 }
			 if(! (r.test(lae_credit_quota))){
				 $("#lae_credit_quota").focus();
				 layer.msg("请输入准确的授信完成额度!", {icon : 0});
				 return;
			 }
			if(parseFloat(lae_credit_quota)>parseFloat(laname)){
            		
        			isNaN(lae_credit_quota);
        			$("#lae_credit_quota").focus();

        		 layer.msg("该产品最高授信额度为"+laname+"万，请修改!", {icon : 0});
        			 return;
        	}
	
			if("0"== bankloan_type){
			layer.msg("请选择银行五级分类",{
						icon : 2,
					});
					return;
				}
			
			webside.common.commit('addEndForm',
					'/loanapprove/addEnd.html'+'?bankloan_type=' + bankloan_type + "&tthNsrmc="+ tthNsrmc 
					+"&tthNsrsbh="+ tthNsrsbh  +"&lae_credit_quota="+ lae_credit_quota +"&lae_overdue_count="+ lae_overdue_count 
					+"&laId="+ la_id +"&la_id="+ la_id +"&lacId="+ lac_id +"&laf_id="+ laf_id ,
					
					'/loanapprove/postloanUI.html',addSuccess());
		}
	});
}


function addSuccess(){
	layer.closeAll();
}

function btnCLear(){
	document.getElementById("province")[0].selected=true;
	document.getElementById("city")[0].selected=true;
	$("#area").append("<option value='"+0+"'>"+'请选择'+"</option>");
	document.getElementById("area").lastChild.selected=true;
	document.getElementById("star").value="";
	document.getElementById("end").value="";
	document.getElementById("nsrmc").value="";
	customSearch();
	
} 

var jsonDataSbb={
		  "ROOT": {
			    "GGXX": {
			      "JHWYM": "4000657051 20150123125917",
			      "GTSBBH": "4000657051",
			      "FHM": "0000",
			      "FHSM": "税费计算成功返回!",
			      "SFZS": "Y"
			    },
			    "JYXX": {
			      "ZRFXX": [{
			          "NSRMC": "张乐",
			          "NSRLB": "01",
			          "ZJLX": "11",
			          "ZJHM": "440301197510073469",
			          "SFJTWYZF": "N",
			          "SFGMWN": "Y",
			          "YGFSJ": "2008-04-30",
			          "ZRFE": "1.0000",
			          "GFEDYYGZJ": "6824700.00"
			        }
			      ],
			      "SRFXX": [ {
			          "NSRMC": "黄犇",
			          "NSRLB": "01",
			          "ZJLX": "11",
			          "ZJHM": "230804197111050013",
			          "SFJTWYZF": "N",
			          "SRFE": "1.0000"
			        }
			      ],
			      "FDCJYXX": {
			        "TDZDH": "T406-0009",
			        "XZQH": "3",
			        "JDXZ": "南山(沙河街道)",
			        "TDZL": "南山区侨城北路",
			        "LJDH": "香年广场[南区]主楼（A座）",
			        "FH": "401",
			        "FWID": "21353837",
			        "FWYT": "05",
			        "JZMJ": "337.31",
			        "RJLBZ": "Y",
			        "XZQYZFZJBZ": "0.00",
			        "TNMJ": "0.00",
			        "SFPTZF": "N",
			        "YDJJG": "6824700.00",
			        "PGZJ": "7586203.09",
			        "CJZJ": "6824700.00",
			        "CJDJ": "20232.72",
			        "PGDJ": "22490.30",
			        "BCSFHTHM": "4000657051",
			        "HTQDSJ": "2015-01-22",
			        "GHSQSLSJ": "2015-01-22",
			        "GSZSFS": "02",
			        "TDZZSZSFS": "02",
			        "BCQSZYFS": "21",
			        "YFDCQDFS": "06",
			        "YFDCZHM": "4000399310"
			      },
			      "DJLX": "03"
			    },
			    "SFXX": {
			      "ZRFSFXX":  [
			          {
			            "ZJLX": "11",
			            "ZJHM": "440301197510073469",
			            "NSRMC": "张乐",
			            "ZSXM": "营业税",
			            "ZSPM": "销售商品房",
			            "JSJG": "7586203.09",
			            "KCJE": "6824700.00",
			            "SYSL": "0.05",
			            "JMSE": "0.00",
			            "YNSE": "38075.15",
			            "SJSE": "38075.15"
			          },
			          {
			            "ZJLX": "11",
			            "ZJHM": "440301197510073469",
			            "NSRMC": "张乐",
			            "ZSXM": "城市维护建设税",
			            "ZSPM": "营业税附征(市区)",
			            "JSJG": "38075.15",
			            "KCJE": "0.00",
			            "SYSL": "0.07",
			            "JMSE": "0.00",
			            "YNSE": "2665.26",
			            "SJSE": "2665.26"
			          },
			          {
			            "ZJLX": "11",
			            "ZJHM": "440301197510073469",
			            "NSRMC": "张乐",
			            "ZSXM": "教育费附加",
			            "ZSPM": "营业税附征",
			            "JSJG": "38075.15",
			            "KCJE": "0.00",
			            "SYSL": "0.03",
			            "JMSE": "0.00",
			            "YNSE": "1142.25",
			            "SJSE": "1142.25"
			          },
			          {
			            "ZJLX": "11",
			            "ZJHM": "440301197510073469",
			            "NSRMC": "张乐",
			            "ZSXM": "地方教育附加",
			            "ZSPM": "营业税附征",
			            "JSJG": "38075.15",
			            "KCJE": "0.00",
			            "SYSL": "0.02",
			            "JMSE": "0.00",
			            "YNSE": "761.50",
			            "SJSE": "761.50"
			          },
			          {
			            "ZJLX": "11",
			            "ZJHM": "440301197510073469",
			            "NSRMC": "张乐",
			            "ZSXM": "印花税",
			            "ZSPM": "产权转移书据",
			            "JSJG": "7586203.09",
			            "KCJE": "0.00",
			            "SYSL": "0.0005",
			            "JMSE": "0.00",
			            "YNSE": "3793.10",
			            "SJSE": "3793.10"
			          },
			          {
			            "ZJLX": "11",
			            "ZJHM": "440301197510073469",
			            "NSRMC": "张乐",
			            "ZSXM": "土地增值税",
			            "ZSPM": "其他类房产",
			            "JSJG": "7586203.09",
			            "KCJE": "0.00",
			            "SYSL": "0.05",
			            "JMSE": "0.00",
			            "YNSE": "379310.15",
			            "SJSE": "379310.15"
			          },
			          {
			            "ZJLX": "11",
			            "ZJHM": "440301197510073469",
			            "NSRMC": "张乐",
			            "ZSXM": "个人所得税",
			            "ZSPM": "房屋转让所得",
			            "JSJG": "7586203.09",
			            "KCJE": "0.00",
			            "SYSL": "0.015",
			            "JMSE": "0.00",
			            "YNSE": "113793.05",
			            "SJSE": "113793.05"
			          }
			        ]
			      ,
			      "SRFSFXX": [
			          {
			            "ZJLX": "11",
			            "ZJHM": "230804197111050013",
			            "NSRMC": "黄犇",
			            "ZSXM": "契税",
			            "JSJG": "7586203.09",
			            "KCJE": "0.00",
			            "SYSL": "0.00",
			            "JMSE": "0.00",
			            "YNSE": "0.00",
			            "SJSE": "0.00"
			          },
			          {
			            "ZJLX": "11",
			            "ZJHM": "230804197111050013",
			            "NSRMC": "黄犇",
			            "ZSXM": "印花税",
			            "ZSPM": "产权转移书据",
			            "JSJG": "7586203.09",
			            "KCJE": "0.00",
			            "SYSL": "0.0005",
			            "JMSE": "0.00",
			            "YNSE": "3793.10",
			            "SJSE": "3793.10"
			          }
			        ]
			      ,
			      "HJ": {
			        "HJ_YNSE": " StringEx.sNull(hjYnse) ",
			        "HJ_JMSE": " StringEx.sNull(hjJmse) ",
			        "HJ_SJSE": " StringEx.sNull(hjSjse) "
			      }
			    },
			    "IMAG":{"IMAGPATH":"E:/54328a477a44fcc12421f0a7adb1d935.jpg"}
			  }
			};
	/*	function downloadPdf(id,creatorid,qymc){
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
//		    		layer.msg("开始下载", {
//		    			icon : 1
//		    		});
		    		$("#"+id).removeAttr("onclick");
		    		$("#"+id).html('<img src="resources/images/u1608.png" title ="下载">');
		    		$.ajax({
		    			type : "POST",
		    			url : sys.rootPath + "/loanapprove/downloadsReport.html?id="+id+ "&statue=2"+"&creatorid="+creatorid+"&lasid=3"+"&qymc="+qymc,
		    			data : null,
		    			dataType : "json",
		    			async : true,//zzx2
		    			success : function(resultdata) {
		    				if(resultdata.isExistPdf){
//		    					alert("该表单后台正在准备数据，请耐心等待... ...");
		    					layer.alert('该表单后台正在准备数据，请耐心等待... ...', {
		    						  icon: 1,
		    						  skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
		    						});
		    					return;
		    				}
		    				
		    				if (resultdata.success) {
		    					
		    					var a = $("<a></a>").attr("href", "/attached/pdf/"+resultdata.pdfFileName).attr("download", resultdata.pdfFileName).appendTo("body");
		    					a[0].click();
		    					a.remove();
		    					$("body").append(_html);
		    					$("#"+id).html('<img src="resources/images/u1582.png" title ="下载">');
		    					$("#"+id).attr("onclick","downloadPdf("+id+","+creatorid+");");
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
		  }*/
function downloadPdf(id,creatorid,qymc){
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
    			url : sys.rootPath + "/loanapprove/downloadsReport.html?id="+id+ "&statue=2"+"&creatorid="+creatorid+"&lasid=3"+"&qymc="+qymc,
    			data : null,
    			dataType : "json",
    			async : true,//zzx2
    			beforeSend:function(){
    				layerOpenIndex = layer.load(0, {shade: 0.1, offset : ["350px",""]
    				,content:'正在下载，请稍等... ...',
    				success: function(layero){
    					layero.find('.layui-layer-content').css('padding-top', '-40px').css('width','150px').css('color','#000');
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
    				//下载按钮还原可点击状态
    				$("#"+id).html('<img src="resources/images/u1582.png" title ="下载">');
					$("#"+id).attr("onclick","downloadPdf("+id+","+creatorid+","+qymc+");");
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