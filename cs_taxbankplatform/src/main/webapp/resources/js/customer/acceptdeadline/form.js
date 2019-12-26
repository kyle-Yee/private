$(function() {
	
	// 判断整数value是否小于或等于0 
    $.validator.addMethod("isIntLteZero", function(value, element) { 
    	
         var pattern = /^[1-9]{1}[0-9]{0,2}$/;     
//         return this.optional(element) || 1000>=value>0; 
         return this.optional(element) || pattern.test(value);
    });

	validateForm();
});

/**
 * 表单验证
 */
function validateForm()
{    debugger;
    $('#acceptdeadlineForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	regionId : {
                required : true,
            },
            orgId : {
                required : false,
            },
            
            tlTotalDays : {
                required : true,
                isIntLteZero : true,
            }
        },
        messages : {
        	regionId : {
                required : "请填写区域id",
            },
            orgId : {
                required : "请填写组织id",
            },
            
            tlTotalDays : {
                required : "请填写贷款受理天数",
                isIntLteZero : "请填写大于0小于1000的整数",
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
            var regionclassid = $("#acceptdeadlineid").val();
            var url = "";
            var selects = $("#orgId").val(); 
            if(selects=="0"){
            	return;
            }
            if(regionclassid != undefined)
            {   
                url = '/acceptdeadline/edit.html?orgId='+selects;
            }else
            {	
                url = '/acceptdeadline/add.html';
            }
            webside.common.commit('acceptdeadlineForm', url, '/acceptdeadline/listUI.html');
        }
    });
}