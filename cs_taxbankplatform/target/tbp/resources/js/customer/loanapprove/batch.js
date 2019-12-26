//var POIFSFileSystem = null;//导入的excel输入流
var dataList = null;//excel数据列表
var listSuccess = null;//审批成功行号
var listFailed = null;//审批失败行号

$(function(){
	//点击导入数据
	$("#dataNameATag,#dataNameATag2").click(function(){
		$("#excelFile").trigger('click');
	});		
	
	//点击导出审核成功数据
	$("#successBtn").click(function(){
		console.log(listSuccess);
		if(listSuccess.length == 0){
			layer.msg("没有审批成功的记录!");
			return;
		}
		$("#dataList").val(JSON.stringify(listSuccess));
		$("#fileName").val("审批成功列表.xls");
		$("#export").submit();
//		$.ajax({
//			type:"post",
//			url:sys.rootPath+"/loanapprovebatch/downloadExport.html",
//			data:{"list":JSON.stringify(listSuccess),"fileName":"审批成功列表"},
//			dataType:"json",
//			success:function(rdata){
//				alert("sss");
//				if(rdata.code == 200){
//					layer.msg(rdata.message);
//				}else if(rdata.code == 202){
//					laeyer.msg("下载完成");
//					
//				}
//			},
//			error:function(){
//				layer.msg("系统异常，请联系管理员");
//			}
//			
//		});
	});
	
	//点击导出审核失败数据
	$("#failBtn").click(function(){
		console.log(listFailed);
		if(listFailed.length == 0){
			layer.msg("没有审批失败的记录!");
			return;
		}
		$("#dataList").val(JSON.stringify(listFailed));
		$("#fileName").val("审批失败列表.xls");
		$("#export").submit();
	});
	
});

/* 导入数据 */
function importData(){
	
	var types = ['xls'];
	$.ajaxFileUpload({
		valid_extensions : types,
		type:"post",
		url : sys.rootPath+"/loanapprovebatch/uploadExcel.html",  //上传文件的服务端
		secureuri : false, //是否启用安全提交
		dataType : "json", //数据类型 
		data:{},
		fileElementId : 'excelFile', //表示文件域ID
		//提交成功后处理函数      data为返回值，status为执行的状态
		success : function(data, status) {
			if(data.code == 200){
				layer.msg(data.message);
				$("#excelFile").val("");
			}else if(data.code == 202){
//				alert("success");
				var list = data.list;
				var listHtml = "";
				for ( var i = 0; i < list.length; i++) {
					var ele = list[i];
					listHtml += "<tr>";
					listHtml += "<td>"+ele.nsrsbh+"</td>";
					listHtml += "<td>"+ele.yhsqxh+"</td>";
					if(ele.result == '002'){
						listHtml += "<td>通过</td>";
					}else if(ele.result == '003'){
						listHtml += "<td>不通过</td>";
					}else{
						listHtml += "<td></td>";
					}
					listHtml += "<td>"+ele.sxje+"</td>";
					listHtml += "<td>"+ele.sxzq+"</td>";
					listHtml += "<td>"+ele.sprq.substr(0,10)+"</td>";
					listHtml += "<td>"+ele.dkqxStart.substr(0,10)+"</td>";
					listHtml += "<td>"+ele.dkqxEnd.substr(0,10)+"</td>";
					listHtml += "<td>"+ele.sxll+"</td>";
					for(var s = 0;s < data.repayWay.length;s++){//匹配还款方式汉字
						if(data.repayWay[s].rw_id == ele.hkfs){
							listHtml += "<td>"+data.repayWay[s].rw_name+"</td>";
							break;
						}else if(s == data.repayWay.length-1){
							if(ele.hkfs == "-1"){//有填写，未匹配
								listHtml += "<td style='color:#ff0000;'>系统未匹配还款方式</td>";
							}else{//未填写
								listHtml += "<td></td>";
							}
						}
					}
					listHtml += "</tr>";
				}
				//1,清除原有的数据
				$(".thisTable").find("tr").not(".trFirst,.trSecond").html("");
				$("#headTr").after(listHtml); 
				$("#noData").hide();//隐藏原来的无数据提示
				$("#hasData").show();//显示列表
				//2,隐藏域赋值，保存表格数据和list
//				POIFSFileSystem = data.POIFSFileSystem;
				dataList = data.list;
				//3,处理按钮组
				$("#dataNameATag").text("重新导入");
				$("#check").show();
				$("#successBtn").hide();
				$("#failBtn").hide();
				//4,清空input内文件，使onChange可激发
				$("#excelFile").val("");
				
			}
		},
		error:function(){
			layer.msg("导入异常,请确保导入文件格式是xls");
			$("#excelFile").val("");
		}
	});
	
}
/** 结束 */

/** 审批  */
function checkApply(){
	if(dataList)
		var jsonList = JSON.stringify(dataList);
	else{//无数据
		layer.msg("请先导入数据");
		return;
	}
	$.ajax({
		type:"POST",
		data:{"list":jsonList},
		url:sys.rootPath+"/loanapprovebatch/checkBatch.html",
		dataType:"json",
		beforeSend: function(){//遮罩防止重复点击
			layerOpenIndex = layer.load(0, {shade: 0.1, offset : ["350px",""]}); //0代表加载的风格，支持0-2
		},
		complete: function(){
			layer.close(layerOpenIndex);//关闭加载层. 
		},
		success:function(rdata){
//			alert("success");
			if(rdata.code ==202){
				//1,处理成功/失败的结果存储
				listSuccess = rdata.listSuccess;//审批成功行号
				listFailed = rdata.listFailed;//审批失败行号
				
				//2,提示弹窗
				layer.open({
					  area: ['525px', '180px'],
					  title: ['消息','background-color:#00A2CA;color:#FFFFFF;height:35px;'],
					  content: '导入成功'+listSuccess.length+'条<br/>导入失败'+listFailed.length+'条',
					  btn:['确定'],
					  success:function(layero){
						  var btn = layero.find('.layui-layer-btn');
					      btn.css('text-align', 'center');
					      
					      var btn0 = layero.find('.layui-layer-btn0');
					      btn0.css('color', 'white');
					      btn0.css('background-color', '#00A2CA');
					      btn0.css('border', 'none');
					      
					      var contentCss = layero.find('.layui-layer-content');
					      contentCss.css('text-align','center');
					      contentCss.css('padding-top','30px');
					      contentCss.css('padding-button','0px');
					      contentCss.css('font-family','微软雅黑');
					      contentCss.css('font-size','14px');
					      contentCss.css('font-weight','400');
					      contentCss.css('height','105px');
					    
					      //alert(title1.css('color'));
					  }
					});
			}
			
			//3,处理页面的按钮组
			$("#dataNameATag").html("继续导入");
			$("#check").hide();
			$("#successBtn").show();
			$("#failBtn").show();
			
		},
		error:function(){
	    	layer.close(layerOpenIndex);//关闭加载层. 
			layer.msg("系统异常，请联系相关人员！");
		}
		
	});
}
/** 结束 */