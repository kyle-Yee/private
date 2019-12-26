/**
 * 贷款审批记录表单验证
 */
function validateForm() {
/*	if($("#lar_credit_quota").val() == null || $("#lar_credit_quota").val() == ""){
		$("#lar_credit_quota").focus();
		 layer.msg("请输入授信额度!", {icon : 0});
		 return;
	}
	*/
	/*if($("#lar_loan_deadline").val() == null || $("#lar_loan_deadline").val() == ""){
		$("#lar_loan_deadline").focus();
		 layer.msg("请输入贷款期限!", {icon : 0});
		 return;
	}*/
	/*if(parseFloat($("#lar_credit_quota").val())>parseFloat($("#approverecAmount").val())){
		
		isNaN($("#lar_credit_quota").val());
		$("#lar_credit_quota").focus();

	 layer.msg("该产品最高授信额度为"+$('#approverecAmount').val()+"万，请修改!", {icon : 0});
		 return;
	}
	*/
	if($("#lar_loan_deadline").val()>$("#approverecdeadline").val()){
		$("#lar_loan_deadline").focus();
		 layer.msg("该产品最高贷款期限为"+$('#approverecdeadline').val()+"个月，请修改!", {icon : 0});
		 return;
	}
	/*if($("#lar_rate").val() == null || $("#lar_rate").val() == ""){
		$("#lar_rate").focus();
		 layer.msg("请输入贷款利率!", {icon : 0});
		 return;
	}*/
	
	/*if($("#lar_begin").val() == null || $("#lar_begin").val() == ""){
		$("#lar_begin").focus();
		 layer.msg("请输入贷款开始日期!", {icon : 0});
		 return;
	}
	
	if($("#lar_end").val() == null || $("#lar_end").val() == ""){
		$("#lar_end").focus();
		 layer.msg("请输入贷款结束日期!", {icon : 0});
		 return;
	}*/
	
//	if($("#lar_bank_name").val() == null || $("#lar_bank_name").val() == ""){
//		$("#lar_bank_name").focus();
//		 layer.msg("请输入借款方开户银行!", {icon : 0});
//		 return;
//	}
//	
//	if($("#lar_bank_account").val() == null || $("#lar_bank_account").val() == ""){
//		$("#lar_bank_account").focus();
//		 layer.msg("请输入借款方银行账号!", {icon : 0});
//		 return;
//	}
//	
//	if($("#lar_contract").val() == null || $("#lar_contract").val() == ""){
//		$("#lar_contract").focus();
//		 layer.msg("请输入借款合同号!", {icon : 0});
//		 return;
//	}

	/*if($("#lar_rate").val() <=0||isNaN($("#lar_rate").val())){
		alert($("#lar_rate").val());
		$("#lar_rate").focus();
		 layer.msg("请输入正确的授信利率!", {icon : 0});
		 return;
	}*/

	 var radio = document.getElementsByName("lac_id");  
     for (var i=0; i<radio.length; i++) {  
         if (radio[i].checked) {  
             if(radio[i].value=='YDKZT06' || radio[i].value=='YDKZT10'){
            		if($("#lar_opinion").val() == null || $("#lar_opinion").val() == ""){
            			$("#lar_opinion").focus();
            			 layer.msg("请输入审批意见!", {icon : 0});
            			 return;
            		}
         	}
         }  
     }
	webside.common.commit("loanApproveForm", "/loanapprove/loanApprove.html", "/loanapprove/listUI.html");
}


/**
 * 功能:
 * 贷款审批时间插件
 */
$(function(){
	$("#lar_begin, #lar_end").datetimepicker({
		lang : "ch",
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
	});
});

function changemonth(){
	//获取当前日期
	var date = new Date();
	var v_now_y = date.getFullYear();
    var v_now_m = (date.getMonth() + 1) >= 1 && (date.getMonth() + 1) <= 9 ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1);
    var v_now_d = date.getDate() >= 1 && date.getDate() <= 9 ? ("0" + date.getDate()) : date.getDate();
    var nowDate = v_now_y + "-" + v_now_m + "-" + v_now_d;
    //往当前日期后推几个月
    var month= $("#lar_loan_deadline").val();
    var arr = nowDate.split("-");
    var v_new_y = parseInt(arr[0]);
    var v_new_m = parseInt(arr[1]);
    var v_new_d = parseInt(arr[2]);
    v_new_m += parseInt(month);
    if (v_new_m > 12){
    	v_new_y = parseInt(v_new_y + v_new_m / 12);
        v_new_m = v_new_m % 12;
    }
    var newDate = v_new_y + "-" + v_new_m + "-" + v_new_d;
    if($("#las_id").val() == 'DKZT01'||$("#las_id").val() == 'DKZT02'){
    	$("#lar_begin").val(nowDate);
    	$("#lar_end").val(newDate);
    }
	
}
/**
 * 功能:
 * 贷款期限起止计算
 */
$(function(){
	//获取当前日期
	var date = new Date();
	var v_now_y = date.getFullYear();
    var v_now_m = (date.getMonth() + 1) >= 1 && (date.getMonth() + 1) <= 9 ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1);
    var v_now_d = date.getDate() >= 1 && date.getDate() <= 9 ? ("0" + date.getDate()) : date.getDate();
    var nowDate = v_now_y + "-" + v_now_m + "-" + v_now_d;
    
    //往当前日期后推几个月
    var month= $("#lar_loan_deadline").val();
    var arr = nowDate.split("-");
    var v_new_y = parseInt(arr[0]);
    var v_new_m = parseInt(arr[1]);
    var v_new_d = parseInt(arr[2]);
    v_new_m += parseInt(month);
    if (v_new_m > 12){
    	v_new_y = parseInt(v_new_y + v_new_m / 12);
        v_new_m = v_new_m % 12;
    }

    var newDate = v_new_y + "-" + v_new_m + "-" + v_new_d;
    if($("#lasid").val() == "DKZT01" || $("#lasid").val() == "DKZT08" || $("#lasid").val() == "DKZT10"){
    	$("#lar_begin").val(nowDate);
    	$("#lar_end").val(nowDate);
    }
});


/**
 * 功能:
 * 贷款审批结果(不同意/退单)自动设置审批表单的默认值
 */
function getFormData(){
    if($('input[name="lac_id"]:checked').val() == 'YDKZT02'){
    	$("#lar_opinion").val("同意");
    }else if($('input[name="lac_id"]:checked').val() == 'YDKZT06' || $('input[name="lac_id"]:checked').val() == 'YDKZT10'){
    	$("#lar_opinion").val("不同意");
    }
}

function changemonth(){
	//获取当前日期
	var date = new Date();
	var v_now_y = date.getFullYear();
    var v_now_m = (date.getMonth() + 1) >= 1 && (date.getMonth() + 1) <= 9 ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1);
    var v_now_d = date.getDate() >= 1 && date.getDate() <= 9 ? ("0" + date.getDate()) : date.getDate();
    var nowDate = v_now_y + "-" + v_now_m + "-" + v_now_d;
    //往当前日期后推几个月
    var month= $("#lar_loan_deadline").val();
    var arr = nowDate.split("-");
    var v_new_y = parseInt(arr[0]);
    var v_new_m = parseInt(arr[1]);
    var v_new_d = parseInt(arr[2]);
    v_new_m += parseInt(month);
    if (v_new_m > 12){
    	v_new_y = parseInt(v_new_y + v_new_m / 12);
        v_new_m = v_new_m % 12;
    }
    var newDate = v_new_y + "-" + v_new_m + "-" + v_new_d;
    if($("#las_id").val() == 'DKZT01'||$("#las_id").val() == 'DKZT02'){
    	$("#lar_begin").val(nowDate);
    	$("#lar_end").val(newDate);
    }
	
}

//查看附件信息
function showAttachment(title, path){
	layer.open({
		type : 2,
        scrollbar : false,
		title : title,
		maxmin: true,
		area: ["750px", "500px"],
		content : sys.rootPath + "/loanapprove/showAttachment.html?path="+path
	});
}