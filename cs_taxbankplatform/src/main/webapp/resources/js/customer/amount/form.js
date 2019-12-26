$(function(){
	
	// 判断浮点数value是否大于0
    $.validator.addMethod("isFloatGteZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || value>0;       
    });
});

var validate = {
    validateAmountForm : function() {
        $('#addForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
            amountName : {
                required : true,
                isFloatGteZero : true,
                remote : {
                   // param : {
                        url : sys.rootPath + '/amount/validateAmountName.html',
                        cache : false,
                   // },
                    //触发验证的条件
                    /*depends : function() {
                        return typeof ($("#userId").val()) == "undefined";
                    }*/
                }
            },
        },
        messages : {
        	amountName : {
                required : "请填写名称",
                isFloatGteZero : "请填写大于0的数字",
                remote : "数据已经存在，请重新输入",
            },
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
                    error.appendTo(element.nextAll('.lbl:eq(0)').eq(0));
            } else if (element.is('.select2')) {
                error.appendTo(element.siblings('[class*="select2-container"]:eq(0)'));
            } else if (element.is('.chosen-select')) {
                error.appendTo(element.siblings('[class*="chosen-container"]:eq(0)'));
            } else
                error.appendTo(element.parent());
        },
        submitHandler : function(form) {
            //访问
            webside.common.commit('addForm', '/amount/add.html', '/amount/listUI.html',addSuccess());
            }
        });
    },
};

/**
 * 添加成功返回
 */
function addSuccess(){
	layer.closeAll();
}

/**
 * 添加
 */
function add(){
	layer.open({
		type : 1,
		title : "添加数据项",
		area: ['500px', '300px'],//宽，高
		skin: 'layui-layer-rim',
		content : $("#amountDiv")
	});
}

