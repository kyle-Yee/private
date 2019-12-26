$(function(){
	
	// 判断整数value是否小于或等于0 
    $.validator.addMethod("isIntLteZero", function(value, element) { 
         value=parseInt(value);      
         return this.optional(element) || 1000>=value>0;       
    });
});

var validate = {
    validateDeadlineForm : function() {
        $('#addForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
            deadlineName : {
                required : true,
                isIntLteZero : true,
                remote : {
                   // param : {
                        url : sys.rootPath + '/deadline/validateDeadlineName.html',
                        cache : false,
                   // },
                    //触发验证的条件
                   /* depends : function() {
                        return typeof ($("#userId").val()) == "undefined";
                    }*/
                }
            },
        },
        messages : {
        	deadlineName : {
	                required : "请填写大于0小于1000的整数",
                isIntLteZero : "请填写大于0小于1000的整数",
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
            webside.common.commit('addForm', '/deadline/add.html', '/deadline/listUI.html',addSuccess());
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
		content : $("#deadlineDiv")
	});
}

