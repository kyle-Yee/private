$(function() {
	validateForm();
});

/**
 * 表单验证
 */
function validateForm()
{    debugger;

    jQuery.validator.addMethod('istaxId', function(value, element) {
        return this.optional(element) || /^\d{10,13}$/.test(value);
    }, "税务机关ID长度应为10至13位的数字");
    $('#taxauthorityForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	taxName : {
                required : true,
            },
            taxPrcId : {
                required : false,
            },
            id : {
            	required : true,
            	istaxId : true
            }
        },
        messages : {
        	rcName : {
                required : "请填写税务机关",
            },
            taxPrcId : {
                required : "请填写上级税务机关id",
            },
            id : {
            	required : "请填写税务机关id"
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
            var regionclassid = $("#taxauthorityid").val();
            var url = "";
            if(regionclassid != undefined)
            {
                url = '/taxauthority/edit.html';
            }else
            {	
                url = '/taxauthority/add.html';
            }
            webside.common.commit('taxauthorityForm', url, '/taxauthority/listUI.html');
        }
    });
}