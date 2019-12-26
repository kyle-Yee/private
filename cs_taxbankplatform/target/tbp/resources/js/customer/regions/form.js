$(function() {
	validateForm();
	
});

/**
 * 表单验证
 */
function validateForm()
{    
    $('#regionForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	regionname : {
                required : true,
                remote: { //更新时不验证
                  param: {
                    url: sys.rootPath + '/regions/validateRegionName.html',
                    cache :false
                  },
                  depends: function() {
                    return typeof($("#regionid").val()) == "undefined";
                  }
                }
            }
        },
        messages : {
        	regionname : {
                required : "请填写区域名称",
                remote : "该区域已开通,不能重复开通该区域"
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
            var userId = $("#regionid").val();
            var url = "";
            if(userId != undefined)
            {
                url = '/regions/edit.html';
            }else
            {	
                url = '/regions/add.html';
            }
            webside.common.commit('regionForm', url, '/regions/listUI.html');
        }
    });
}

/*function checkboxValue(object)
{
	object.value=object.checked?'Y':'N';
}*/
