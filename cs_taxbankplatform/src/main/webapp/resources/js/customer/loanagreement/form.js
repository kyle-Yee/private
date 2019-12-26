var ue;
$(function() {
	//实例化编辑器 
	ue = UE.getEditor('container');
    validateFinancialProductForm();
});

/**
 * 表单验证
 */
function validateFinancialProductForm()
{    
    $('#loanAgreementForm').validate({
        errorElement : 'label',
        errorClass : 'help-block',
        focusInvalid : true,
        ignore : "",
        rules : {
        	fpName : {
                required : true,
                maxlength:50
            }
        },
        messages : {
        	laName : {
                required : "请填写协议名称",
                maxlength:"协议名称不能大于100个字符"
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
            var laId = $("#laId").val();
            var url = "";
            if(laId != undefined)
            {
                url = '/loanagreement/edit.html';
            }else
            {
                url = '/loanagreement/add.html';
            }
            webside.common.commit('loanAgreementForm', url, '/loanagreement/listUI.html');
        }
    });
}

function submitF(){
	 var validate= validateName();
	 if(validate){
		 $('#loanAgreementForm').submit();
	 }
}
function validateName(){
	 //校验协议名称
	var laNameval = $("#laName").val();
	var orgIdval = $("#orgId").val();
	var regionIdval = $("#regionId").val();
	var checklaName =false;
	if(laNameval!=null && laNameval!="" && laNameval.length > 0){
		
		var laId = $("#laId").val();
		if(laId == undefined){ //添加界面需判断协议名称是否唯一
			var notExist = true;
			$.ajax({
				async:false,
				type : "POST",
				url : sys.rootPath +'/loanagreement/checkLaName.html',
				data : {
					"laName" : laNameval,
					"orgId" : orgIdval,
					"regionId" : regionIdval
				},
				dataType : "json",
				success : function(resultdata) {
					notExist = resultdata;
					if(notExist == false){
						alert("此协议名称已存在!");
						$("#laName").focus();
					}
				}
			});
			checklaName = notExist;
			if(checklaName == false){
				return;
			}
	    }else{ //编辑界面无需判断协议名称是否唯一
	    	checklaName = true;
	    }
	}else{
		alert("请输入产品名称!");
		$("#laName").focus();
		checklaName =  false;
		return;
	}
	if(checklaName ==true){
		return true;
	}
	return false;
}