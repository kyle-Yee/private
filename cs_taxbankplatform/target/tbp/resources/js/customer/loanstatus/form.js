$(function() {
	$("#imghead").css({ "margin-left": "-11px"}); 
	validateForm();
});

/**
 * 表单验证
 */
function validateForm()
{    
    $('#loanstatusForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	lsname : {
                required : true,
            },
            lscode : {
                required : true,
            }
        },
        messages : {
        	lsname : {
                required : "请填写担保方式名称",
            },
            lscode : {
                required : "请填写担保方式编码",
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
            var userId = $("#statusid").val();
            var url = "";
            if(userId != undefined)
            {
                url = '/loanstatus/edit.html';
            }else
            {	
                url = '/loanstatus/add.html';
            }
            webside.common.commit('loanstatusForm', url, '/loanstatus/listUI.html');
        }
    });
}
