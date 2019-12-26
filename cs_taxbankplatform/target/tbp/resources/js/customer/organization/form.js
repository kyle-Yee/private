$(function() {
	validateForm();
});

/**
 * 表单验证
 */
function validateForm()
{    
    $('#orgForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	regionid : {
                required : true,
            },
            orgname : {
                required : true,
            },
            orgcode : {
                required : true,
            },
            orglogourl:{
            	required : true,
            },
            otid : {
                required : true,
            },
            regionClass : {
                required : true,
            },
            /*upOrgId : {
                required : true,
            },*/
            regionname : {
                required : true,
            }
        },
        messages : {
        	regionid : {
                required : "请选择区域",
            },
            orgname : {
                required : "请填写组织名称",
            },
            orgcode : {
                required : "请填写组织编码",
            },
            orglogourl:{
            	required : "请上传组织logo图片",
            },
            otid : {
                required : "请填写组织类型",
            },
            regionClass : {
                required : "请选择区域级别",
            },
            /*upOrgId : {
                required : "请选择上级组织",
            },*/
            regionname : {
                required : "请选择所属区域",
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
            var userId = $("#orgid").val();
            var otid=$("#otid").val();
            if(otid=="ZZLX002"){
            	 var rzcid=$("#rzcid").val();
            	 var sqcid=$("#sqcid").val();
            	 var accredit = $("#accredit").val();
				  if(accredit=="onetimeaccredit"){
					  if(rzcid==undefined||rzcid==''){
	            		 	layer.msg("请选择认证协议类型");
	            		 	return;
	            	 }
				  }else if(accredit=="eachloanaccredit"){
						 if(sqcid==undefined||sqcid==''){
			         		 	layer.msg("请选择税务协议类型");
			         		 	return;
			         	 
				  }
            	
                }else if(accredit==""||accredit==undefined){
					layer.msg("请选择授权类型");
         		 	return;
			  } 
			  
           
            }
            var url = "";
            if(userId != undefined)
            {
                url = '/organization/edit.html';
            }else
            {	
                url = '/organization/add.html';
            }
            webside.common.commit('orgForm', url, '/organization/listUI.html');
        }
    });
}


/*function checkboxValue(object)
{
	object.value=object.checked?'Y':'N';
}*/