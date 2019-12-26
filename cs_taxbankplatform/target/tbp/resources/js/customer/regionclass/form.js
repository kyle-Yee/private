$(function() {
	validateForm();
});

/**
 * 表单验证
 */
function validateForm()
{    
    $('#addForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	rcName : {
                required : true,
            },
            rcDescription : {
                required : false,
            }
        },
        messages : {
        	rcName : {
                required : "请填写区域级别",
            },
            rcDescription : {
                required : "请填写描述",
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
            var regionclassid = $("#regionclassid").val();
            var url = "";
            if(regionclassid != undefined)
            {
                url = '/regionclass/edit.html';
            }else
            {	
                url = '/regionclass/add.html';
            }
            webside.common.commit('addForm', url, '/regionclass/listUI.html');
        }
    });
}