var ue;
$(function() {
	//初始化状态框值
	if($("#enabled").attr("value")){$("#enabled").val($("#enabled").attr("value"));}
	
	//表单提交
	submitDate();
	
	$.validator.addMethod("isRightfulString", function(value, element) {       
        return this.optional(element) || /^[A-Za-z0-9]+$/.test(value);       
   }); 
});

/**
 * 表单提交
 */
function submitDate(){
	$('#parametersForm').validate({
    	errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	code : {
                required : true,
                isRightfulString : true,
                remote : {
                    url : sys.rootPath + '/parameters/ckCode.html',
                    data : {
                    	code : function() {return $("#code").val();},
						id : function() {return $("#parametersId").val();},
                    },
                    cache : false,
                }
            },
            name : {
            	required : true,
            },
            value : {
            	required : true,
            }
        },
        messages : {
        	code : {
                required : "类型不能为空",
                isRightfulString : "只可以输入大写或小写的英文和数字",
                remote : "数据已经存在，请重新输入",
            },
            name : {
            	required : "名称不能为空",
            },
            value : {
            	required : "内容不能为空",
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
            if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if (controls.find(':checkbox,:radio').length > 1)
                    controls.append(error);
                else
                    error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            } else if (element.is('.select2')) {
                error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
            } else if (element.is('.chosen-select')) {
                error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
            } else
                error.appendTo(element.parent());
        },
        submitHandler : function(form) {
        	var parametersId = $("#parametersId").val();
            var url = "";
            if (parametersId != "") {
                url = '/parameters/edit.html';
            } else {
                url = '/parameters/add.html';
            }
            //访问
            webside.common.commit('parametersForm', url, '/parameters/parametersUI.html');
            }
	    }); 
}