var ue;
$(function() {
	//实例化编辑器 
	ue = UE.getEditor('container');
	//自定义参数
	ue.ready(function() {
	    ue.execCommand('serverparam', {
	        'fkTable': 'tb_financial_product',
	        'fkIdName': 'fp_id',
	        'fkId': !$("#financialProductId").val()?"":$("#financialProductId").val()
	    });
	});
	//自动生成摘要
	$("#summary").on("focus",function(){
		if(!$(this).val()){
			var txt = $.trim(ue.getContentTxt());
			if(txt){
				$(this).val(txt.length>290?(txt.substr(0,290)+"..."):txt);//数据库字段长度300
			}
		}
	});
    validateFinancialProductForm();
    
    $("#radioAll").click(function(){
    	if($(this).attr("checked")){
    		$(".checkbox-inline").find("input[name=fpOverlayPcId]").prop("checked",false);
    		$(this).attr("checked",false);
    	}else{
    		$(".checkbox-inline").find("input[name=fpOverlayPcId]").prop("checked",true);
    		$(this).attr("checked",true);
    	}
    });
    
    //判断按钮是否被点击,取消timeout事件并执行执行跳转
	$("#btn1").click(function(){
 		webside.common.loadPage('/financialProduct/listPublishUI.html');
	});
	$("#btn2").click(function(){
		webside.common.loadPage('/financialProduct/addUI.html?ptdm=1');
	});
	if($("#stopCount").val() == "1" ){
		stopCount();
	}
});

/**
 * 表单验证
 */
function validateFinancialProductForm()
{    
    $('#financialProductForm').validate({
        errorElement : 'label',
        errorClass : 'help-block',
        focusInvalid : true,
        ignore : "",
        rules : {
        	fpName : {
                required : true,
                maxlength:50
            },
            rwId : {
            	required:true
            },
            gsId : {
                required : true
            }
        },
        messages : {
        	fpName : {
                required : "请填写产品名称",
                maxlength:"产品名称不能大于100个字符"
            },
            rwId : {
            	required : "请选择还款方式"
            },
            gsId : {
                required : "请选择担保方式"
            }
        },
        highlight : function(e) {
            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
        },
        success : function(e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
            $(e).remove();
        },
        errorPlacement : function(error, element) {
           if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
                else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            }
            else if(element.is('.select2')) {
                error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
            }
            else if(element.is('.chosen-select')) {
                error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
            }
            else error.insertAfter(element.parent());
        },
        submitHandler : function(form) {
            var financialProductId = $("#financialProductId").val();
            var url = "";
            getAttachmenturl();//设置文件参数
            if(financialProductId != undefined)
            {
                url = '/financialProduct/edit.html';
            }else
            {
                url = '/financialProduct/add.html';
            }
            webside.common.commit('financialProductForm', url, '/financialProduct/listUI.html');
        }
    });
}

function getAttachmenturl(){
	//获取文章编辑内容，获取上传附件
	var attachmenturls = "";
	$("#htmlTemp").html(ue.getContent());
	$("#htmlTemp").find("img").each(function(){
		attachmenturls += $(this).attr("src")+",";
	});
	$("#htmlTemp").find("a").each(function(){
		attachmenturls += $(this).attr("href")+",";
	});
	$("#htmlTemp").find("video").each(function(){
		attachmenturls += $(this).attr("src")+",";
	});
	$("#attachmenturls").val(attachmenturls);
}

function submitF(){
	//还款方式拼接插入
	var rw_ids="";
	$("#financialProduct input[name='rwId']:checked").each(function(){ 
		rw_ids += $(this).val()+"#"; 
	});
	$("#financialProduct input[name='rwIds']").val(rw_ids);
	//担保方式拼接插入
	var gs_ids="";
	$("#financialProduct input[name='gsId']:checked").each(function(){ 
		gs_ids += $(this).val()+"#"; 
	});
	$("#financialProduct input[name='gsIds']").val(gs_ids);
	//覆盖城市拼接插入
	var fp_overlay_pc_ids="";
	$("#financialProduct input[name='fpOverlayPcId']:checked").each(function(){ 
		fp_overlay_pc_ids += $(this).val()+"#"; 
	});
	$("#financialProduct input[name='fpOverlayPcIds']").val(fp_overlay_pc_ids);
	
	$('#financialProductForm').submit();
}
 function nextF(fpId){
	 //fpId 为0时 为添加产品界面的下一步, 不为0时则为修改产品界面的下一步
	var rw_ids="";
	var hkfsHTML ="";
	var hkfsName ="";
	$("#financialProduct input[name='rwId']:checked").each(function(){ 
		hkfsName +=$(this).parent().find($(".rwname")).html()+",";
	});
	$("#financialProduct input[name='rwId']:checked").each(function(){ 
		rw_ids += $(this).val()+"#"; 
		hkfsHTML +=$(this).parent().html();
	});
	$("#financialProduct input[name='rwIds']").val(rw_ids);
	
	var gs_ids="";
	$("#financialProduct input[name='gsId']:checked").each(function(){ 
		gs_ids += $(this).val()+"#"; 
	});
	$("#financialProduct input[name='gsIds']").val(gs_ids);
	
	var fp_overlay_pc_ids="";
	$("#financialProduct input[name='fpOverlayPcId']:checked").each(function(){ 
		fp_overlay_pc_ids += $(this).val()+"#"; 
	});
	$("#financialProduct input[name='fpOverlayPcIds']").val(fp_overlay_pc_ids);
	
	//下一步
	 var validate= validateNext(fpId);
	 if(validate){ //校验成功之后才进数据项添加页面
		 $("#financialProduct").css("display","none");
		 $("#loanForms").css("display","inline-block");
		 $(".page-header .headerTop").css("background","url('./resources/images/bg_addpf2.png') no-repeat center center");
		 
		 var dkedAtrr= $("#financialProduct #laId").find("option:selected").text(); //贷款额度属性
		 var dkqxAtrr= $("#financialProduct #ldId").find("option:selected").text(); //贷款期限属性
		 $("#loanForms #lf_dked").attr("placeholder","最高"+dkedAtrr);
		 $("#loanForms #lf_dkqx").attr("placeholder","最长"+dkqxAtrr);
		
		 
		 $("#loanForms #dked_label").attr("pdi_id","0");
		 $("#loanForms #dked_label").attr("pdi_code","dked");
		 $("#loanForms #dked_label").attr("pdi_name","贷款额度");
		 $("#loanForms #dked_label").attr("ht_type","text");
		 $("#loanForms #dked_label").attr("productditvalueslist",dkedAtrr);//最高贷款额度
		
		 $("#loanForms #dkqx_label").attr("pdi_id","0");
		 $("#loanForms #dkqx_label").attr("pdi_code","dkqx");
		 $("#loanForms #dkqx_label").attr("pdi_name","贷款期限");
		 $("#loanForms #dkqx_label").attr("ht_type","text");
		 $("#loanForms #dkqx_label").attr("productditvalueslist",dkqxAtrr);//最长贷款期限
			 
		 $("#loanForms #hkfs_label").attr("pdi_id","0");
		 $("#loanForms #hkfs_label").attr("pdi_code","hkfs");
		 $("#loanForms #hkfs_label").attr("pdi_name","还款方式");
		 $("#loanForms #hkfs_label").attr("ht_type","checkbox");
		 $("#loanForms #hkfs_label").attr("productditvalueslist",hkfsName);
		 $("#loanForms #lf_hkfs").html(hkfsHTML); //还款方式名称
		 $("#loanForms #lf_hkfs input").attr("readOnly",true);
		 $("#loanForms #lf_hkfs input").attr("disabled",true);
		 
		 if(fpId !=0){
			$("#loanForms #lf_hkfs input").removeAttr("checked");
		 }
		 
		 $("#loanForms #sqxyss_label").attr("pdi_id","0");
		 $("#loanForms #sqxyss_label").attr("pdi_code","sqxyss");
		 $("#loanForms #sqxyss_label").attr("pdi_name","授权协议");
		 $("#loanForms #sqxyss_label").attr("ht_type","checkbox");
		 $("#loanForms #sqxyss_label").attr("productditvalueslist",$("#sqxyss").val()); //贷款协议ID
	 }
}
function showCheckDkxy(){//显示选择贷款协议
	layer.open({
	      title:"选择贷款协议",
		  type: 2,
		  async: true,
		  skin: 'demo-class',
		  area: ['900px', '600px'],
		  fix: true, //不固定
		  btn:'确定',
		  content: sys.rootPath + '/financialProduct/showCheckDkxy.html',
		  yes: function(i){
			  var body = layer.getChildFrame('body', i);
			  var laId = body.find("input[name='laId']:checked").val();
			  if(undefined == laId || "undefined" == laId ){
				   layer.msg('请选择', {
	                    icon : 3
	                });
				   parent.layer.close(i);
			  }else{
				  var laName = body.find("#laName"+laId).html();
				  var contentHtml = body.find("#loanAgreementContent"+laId).html();
				  var appendHtml='<label>我已阅读并同意<a id="xy" onclick="showContent('+laId+')">'+laName+'</a>的内容 </label>'+
				  '<input readOnly="true" disabled="true" type="hidden" name="dkxyId" id="dkxyId" value="'+laId+'">'+
				  '<div id="loanAgreementContent'+laId+'" style="display:none">'+contentHtml+
				  '</div>';
				  $("#dkxy_old").html("");
				  $("#appendHtmlId").after(appendHtml);
				  parent.layer.close(i);
			  }
		  }
		});
 }

function validateNext(fpId){
	 //校验产品名称
	var checkfpName =true;
	if(fpId ==0){//添加的时候要验证产品名称唯一，修改的时候不用
		var fpNameval = $("#fpName").val();
		var orgIdval = $("#orgId").val();
		var regionIdval = $("#regionId").val();
		checkfpName =false;
		if(fpNameval!=null && fpNameval!="" && fpNameval.length > 0){
			var notExist = true;
			$.ajax({
				async:false,
				type : "POST",
				url : sys.rootPath +'/financialProduct/checkFpName.html',
				data : {
					"regionId" : regionIdval,
					"orgId" : orgIdval,
					"fpName" : fpNameval
				},
				dataType : "json",
				success : function(resultdata) {
					notExist = resultdata;
					if(notExist == false){
						layer.msg('该产品名称已存在', {
		                    icon : 3
		                });
					   
						$("#fpName").focus();
					}
				}
			});
			checkfpName = notExist;
			if(checkfpName == false){
				return;
			}
		}else{
			layer.msg('请输入产品名称', {
                icon : 3
            });
		  
			$("#fpName").focus();
			checkfpName =  false;
			return;
		}
	}
	
	var loanAgreementSize = $("#loanAgreementSize").val();
	var checkDkxy =false;
	if(loanAgreementSize == '0'){
		layer.msg('授信协议不能为空,请到贷款协议管理里面添加授信协议!', {
            icon : 3
        });
		checkDkxy = false;
		return;
	}else{
		//showCheckDkxy();
		checkDkxy = true;
	}
	
	//贷款额度
	 var laIdval = $("#laId").find("option:selected").val();
	 var checklaId =false;
	 if(laIdval!=null && laIdval!="" && laIdval.length > 0){
		 checklaId = true;
	 }else{
		 layer.msg('请选择贷款额度!', {
	            icon : 3
	        });

		$("#laId").focus();
		checklaId =  false;
		return;
	 }
	 
	 //贷款期限
	 var ldIdval = $("#ldId").find("option:selected").val(); 
	 var checkldId =false;
	 if(ldIdval!=null && ldIdval!="" && ldIdval.length > 0){
		 checkldId = true;
	 }else{
		 layer.msg('请选择贷款期限!', {
	            icon : 3
	        });
		 $("#ldId").focus();
		 checkldId =  false;
		 return;
	 }
	 
	 //贷款利率
	 var lrIdval = $("#lrId").find("option:selected").val(); 
	 var checklrId =false;
	 if(lrIdval!=null && lrIdval!="" && lrIdval.length > 0){
		 checklrId = true;
	 }else{
		 layer.msg('请选择贷款利率!', {
	            icon : 3
	        });
		 $("#lrId").focus();
		 checklrId =  false;
		 return;
	 }
	 
	 //还款方式
	 var rwIdval = $("#financialProduct input[name='rwId']:checked").val();
	 var checkrwId =false;
	 if(rwIdval!=null && rwIdval!="" && rwIdval.length > 0){
		 checkrwId = true;
	 }else{
		 layer.msg('请选择还款方式!', {
	            icon : 3
	        });
		 checkrwId =  false;
		 return;
	 }
	 //担保方式
	 var gsIdval = $("#financialProduct input[name='gsId']:checked").val();
	 var checkgsId =false;
	 if(gsIdval!=null && gsIdval!="" && gsIdval.length > 0){
		 checkgsId = true;
	 }else{
		 layer.msg('请选择担保方式!', {
	            icon : 3
	        });
		 checkgsId =  false;
		 return;
	 }
	 //覆盖区域
	 var fpOverlayPcIdval = $("#financialProduct input[name='fpOverlayPcId']:checked").val();
	 var checkfpOverlayPcId =false;
	 if(fpOverlayPcIdval!=null && fpOverlayPcIdval!="" && fpOverlayPcIdval.length > 0){
		 checkfpOverlayPcId = true;
	 }else{
		 layer.msg('请选择覆盖区域!', {
	            icon : 3
	        });
		 checkfpOverlayPcId =  false;
		 return;
	 }

	 if(checkDkxy ==true && checkfpName ==true && checklaId ==true && checkldId ==true && checklrId ==true 
			 && checkrwId ==true && checkgsId ==true && checkfpOverlayPcId ==true){
		return true;
	}
	return false;
}
 function showAddSjx(){//弹出添加数据项界面
	layer.open({
		  type: 2,
		  title:"添加数据项",
		  skin: 'demo-class',
		  area: ['900px', '600px'],
		  fix: false, //不固定
		  maxmin: true,
		  btn:'添加数据项',
		  content: sys.rootPath + '/financialProduct/listDataItemsUI.html',
		  yes: function(i){
			  var body = layer.getChildFrame('body', i);
			  var htmlAppend="";
			  var pdi_id_child ="";
			  	body.find("#addSjxForm .playList").each(function(j){
			  		var notExsitBoolean = true;
					if($(this).find(".leftI input[type='checkbox']").prop("checked")){
						pdi_id_child = $(this).find('.rightI label').attr("pdi_id");
						$(".itemList").each(function(z){
							var pdi_id_parent = $(this).find('label.control-label').attr("pdi_id");
							if(pdi_id_parent == pdi_id_child){
								 layer.msg($(this).find('label.control-label').attr("pdi_name")+"已存在!", {
							            icon : 3
							        });
								//alert($(this).find('label.control-label').attr("pdi_name")+"已存在!");
								notExsitBoolean = false;
							}
						});
						
						if(notExsitBoolean){
							htmlAppend +='<div class="row itemList item'+j+'" style="display:inline-block;width: 830px;">'+$(this).find('.rightI').html()+'<button style="float:right;" onclick="removeF('+j+')">删除</button></div>';
						}
					}
				  });
				$("#afterFlag").after(htmlAppend);
				parent.layer.close(i);
		  }
		});
 }
 function submitAll(){//添加界面的提交按钮事件
	 var sxxyss =$('#lf_sqxyss option:selected') .val(); //授权协议ID
	 $("#loanForms #sqxyss_label").attr("productditvalueslist",sxxyss); //授权协议ID
	 if(undefined == sxxyss || "undefined" == sxxyss || null == sxxyss || "" == sxxyss){
		 layer.msg("没有选择授权协议", {
	            icon : 3
	        });
		 return;
	 }else{
		 //1.产品保存、申请单基表保存
		 var submitBoolean = false;
		 var newlfId =0;
		 var ajax_option={
				 async:false,
				 url: sys.rootPath + "/financialProduct/add.html?ptdm=1&sxxyss="+sxxyss,
				 dataType : "json",
				 success : function(resultdata) {
					 submitBoolean = resultdata.success;
					 newlfId = resultdata.newlfId;

				 }
		 };
		 $('#financialProductForm').ajaxSubmit(ajax_option);
		 
		 //申请副表插入
		 
		 $.each($("#loanFormsForm .row"), function(index){
			 var pdi_id= $(this).find(".control-label").attr("pdi_id");
			 var pdi_code= $(this).find(".control-label").attr("pdi_code");
			 var pdi_name= $(this).find(".control-label").attr("pdi_name");
			 var ht_type= $(this).find(".control-label").attr("ht_type");
			 var productditvalueslist= $(this).find(".control-label").attr("productditvalueslist");
			 $.ajax({
				 async:false,
				 type : "POST",
				 url : sys.rootPath +'/financialProduct/addLoanFormsAttach.html',
				 data : {
					 "newlfId" : newlfId,
					 "pdi_id" : pdi_id,
					 "pdi_code" : pdi_code,
					 "pdi_name" : pdi_name,
					 "ht_type" : ht_type,
					 "productditvalueslist" : productditvalueslist
				 },
				 dataType : "json",
				 success : function(resultdata) {
					 if(resultdata.success == false){
						 layer.msg("添加数据项失败", {
					            icon : 3
					        });
						 return
					 }
				 }
			 });
		 });
		 
		 if(submitBoolean){
			 $("#financialProduct").css("display","none");
			 $("#loanForms").css("display","none");
			 $(".page-header .headerTop").css("background","url('./resources/images/bg_addpf3.png') no-repeat center center");
			 $("#addSuccess").css("display","inline-block");
			 setTimeOut();
		 }else{
			 layer.msg("添加失败", {
		            icon : 3
		        });
		 }
	 }
 }
 
 var pdi_codes ="";
 function updateLoanFormsAttach1(lfId){//存在就更新,不存在就插入
		var updateEnableBool =false;
		$.each($("#loanFormsForm .row"), function(index){
			var pdi_id= $(this).find(".control-label").attr("pdi_id");
			var pdi_code= $(this).find(".control-label").attr("pdi_code");
			pdi_codes+=pdi_code+",";
			var pdi_name= $(this).find(".control-label").attr("pdi_name");
			var ht_type= $(this).find(".control-label").attr("ht_type");
			var productditvalueslist= $(this).find(".control-label").attr("productditvalueslist");
			$.ajax({
				async:false,
				type : "POST",
				url : sys.rootPath +'/financialProduct/updateLoanFormsAttach.html',
				data : {
					"newlfId" : lfId,
					"pdi_id" : pdi_id,
					"pdi_code" : pdi_code,
					"pdi_name" : pdi_name,
					"ht_type" : ht_type,
					"productditvalueslist" : productditvalueslist
				},
				dataType : "json",
				success : function(resultdata) {
					if(resultdata.success == true){
						updateEnableBool =true;
					}
				}
			});
		});
	 return updateEnableBool;
 }
 function updateLoanFormsAttach(lfId){
	if("undefined" != lfId && undefined !=lfId){
		var updateEnableBool = updateLoanFormsAttach1(lfId);//存在就更新,不存在就插入
		if(updateEnableBool){	//若以前存在,现在不需要的,则更新enabled为N
			$.ajax({
				async:false,
				type : "POST",
				url : sys.rootPath +'/financialProduct/updateLoanFormsAttachEnabled.html',
				data : {
					"lfId" : lfId,
					"pdi_codes" : pdi_codes,
				},
				dataType : "json",
				success : function(resultdata) {
//					alert("newSize"+resultdata.newSize);
//					alert("oldSize"+resultdata.oldSize);
				}
			});
		}else{
			 layer.msg("编辑失败", {
		            icon : 3
		        });
			return
		}
	}
 }
 function submitUpdateAll(fpId){ //修改界面的提交按钮事件
	 var sxxyss =$('#lf_sqxyss option:selected') .val(); //授权协议ID
	 $("#loanForms #sqxyss_label").attr("productditvalueslist",sxxyss); //授权协议ID
	 if(undefined == sxxyss || "undefined" == sxxyss || null == sxxyss || "" == sxxyss){
		 layer.msg("没有选择授权协议", {
	            icon : 3
	        });
		 return;
	 }else{
		 //1.产品保存、申请单基表保存
		 var submitBoolean = false;
		 var lfId =0;
		 var ajax_option={
			async:false,
			url: sys.rootPath + '/financialProduct/edit.html?ptdm=1&sxxyss='+sxxyss,
			dataType : "json",
			success : function(resultdata) {
				submitBoolean = resultdata.success;
				lfId = resultdata.lfId;
				if(submitBoolean){
					updateLoanFormsAttach(lfId); //2.更新申请单副表
				}
			}
		};
		$('#financialProductForm').ajaxSubmit(ajax_option);
		
		if(submitBoolean){
			$("#financialProduct").css("display","none");
			$("#loanForms").css("display","none");
			$(".page-header .headerTop").css("background","url('./resources/images/bg_addpf3.png') no-repeat center center");
			$("#addSuccess").css("display","inline-block");
		}else{
			 layer.msg("添加失败", {
		            icon : 3
		        });
		}
	 }
 }
 
 function setTimeOut(){
 	 timeOut();
  }
 var wait=5;
 var t;
 function timeOut(){
	 t= setTimeout(function(){
		 $('#timeSet').html(wait +'秒后回到产品维护界面。。。');
		 wait--;  
		 timeOut();
	 },1000);
	 if(wait==0){
		 stopCount();
		 webside.common.loadPage('/financialProduct/listUI.html');
	 }
 }
 function stopCount(){
	 clearTimeout(t);
 }
 
 function showHtml(){ //预览贷款申请单的生成效果
	 var sxxyss =$('#lf_sqxyss option:selected') .val(); //授权协议ID
	 if (undefined == sxxyss || "undefined" == sxxyss || null == sxxyss || "" == sxxyss) {
		 layer.msg("请选择授权协议", {
	            icon : 3
	        });
		 return;
	 }
	 $("#lf_sqxyss option[value="+sxxyss+"]").attr("selected","selected");
	 $("#lf_sqxyss").attr("disabled", "disabled");
	 $("#lf_hkfs input[name=rwId]").each(function(){
		 $(this).attr("type","checkbox");
	 });
	 var conentHtml = $(".viewLoanForms").html();
	 $("#lf_hkfs input[name=rwId]").each(function(){ //checkbox切换回来
		 $(this).attr("type","checkbox");
	 });
	 layer.open({
		  title: "申请单预览",
		  skin: 'viewLoanForms',
		  type: 1,
		  area: ['900px', '600px'],
		  fix: false,
		  maxmin: true,
		  content: conentHtml
	});
 }
 function showssContent(){ //查看贷款协议内容
	 var sxxyss =$('#lf_sqxyss option:selected') .val(); //授信协议
	 if (undefined == sxxyss || "undefined" == sxxyss || null == sxxyss || "" == sxxyss) {
		 layer.msg("请选择授权协议", {
	            icon : 3
	        });
	 }else{
		var laContent;
		var laName;
		$.ajax({
			 async:false,
			 type : "POST",
			 url : sys.rootPath +'/financialProduct/findSxxyById.html',
			 dataType : "json",
			 data : {
				 "sxxy" : sxxyss
			 },
			 success : function(data) {
				if (null != data) {
					laContent = data.laContent;
					laName = data.laName;
				}
			 }
		 });
		var conentHtml = "<h2 style='text-align: center;margin-top:30px;'>"+laName+"</h2><div style='margin: 30px;'>"+laContent+"</div>";
		layer.open({
			 title: "协议内容",
			 type: 1,
			 area: ['900px', '600px'],
			 fix: false,
			 maxmin: true,
			 content: conentHtml
		 });
	 }
 }
 function removeF(j){
	 $(this).remove();
	 $(".item"+j).remove(); //生成申请单中的数据项 行删除
 }
 
 function gotosyb(){
	 $("#financialProduct").css("display","inline-block");
	 $("#loanForms").css("display","none");
	 $(".page-header .headerTop").css("background","url('./resources/images/bg_addpf.png') no-repeat center center");
 }