$(function(){

	//显示隐藏的DIV
	$("#selectForm").change(function(){
		var selectVal = $("#selectForm").select().val();
		if("to" == selectVal){
			$("#toDiv").show();
		}
		if("up" == selectVal){
			$("#toDiv").hide();
		}
		if("down" == selectVal){
			$("#toDiv").hide();
		}
	});
	
	// 判断浮点数value是否大于0
    $.validator.addMethod("isFloatGteZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || 100>value>0;       
    });
});

var validate = {
    validateRatesForm : function() {
    	//表单部分
        $('#addForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
            selectForm : {
            	required : true,
            },
            ratesVal : {
            	required : true,
            	isFloatGteZero : true,
            },
            toVal : {
            	isFloatGteZero : true,
            },
            ratesName : {
                remote : {
                    param : {
                        url : sys.rootPath + '/rates/validateRatesName.html',
                        cache : false,
                    },
                },
            },
        },
        messages : {
            selectForm : {
            	required : "请选择数据形式",
            },
            ratesVal : {
            	required : "请填写数据",
            	isFloatGteZero : "请输入大于0小于100的数字",
            },
            toVal : {
            	isFloatGteZero : "请输入大于0小于100的数字",
            },
        	ratesName : {
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
                	//提示信息在下边
                    error.appendTo(element.nextAll('.lbl:eq(0)').eq(0));
            } else if (element.is('.select2')) {
                error.appendTo(element.siblings('[class*="select2-container"]:eq(0)'));
            } else if (element.is('.chosen-select')) {
                error.appendTo(element.siblings('[class*="chosen-container"]:eq(0)'));
            } else
            	error.appendTo(element.parent());
            	//提示信息在右侧
              //  error.insertAfter(element.parent());
        },
        submitHandler : function(form) {
        	var toVal = $("#toVal").val();
        	var selectVal = $("#selectForm").select().val();
	        	if("to" == selectVal && "" == toVal){
	        		layer.msg("数值不能为空",{
	        			icon : 2
	        		});
	        		return;
	        	}else{
	                //访问
	                webside.common.commit('addForm', '/rates/add.html','/rates/listUI.html', addSuccess());
	        	}
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
		area: ['700px', '380px'],//宽，高
		skin: 'layui-layer-rim',
		content : $("#ratesDiv")
	});
}

/**
 * 检测和赋值
 */
function checkVal(){
	//验证，赋值
	var ratesName = "";
	var code = "";
	var ratesVal = $("#ratesVal").val();
	var toVal = $("#toVal").val();
	var selectVal = $("#selectForm").select().val();
	if("up" == selectVal){
		ratesName =  ratesVal + "%以上";
		code = ratesVal + selectVal;
	}else if("down" == selectVal){
		ratesName =  ratesVal + "%以下";
		code = ratesVal + selectVal;
	}else if("to" == selectVal){
		ratesName =  ratesVal + "%~" + toVal + "%";
		code = ratesVal + selectVal + toVal;
	}
	if("" != toVal && "" != ratesVal){
		if(parseFloat(ratesVal) >= parseFloat(toVal)){
			layer.msg("起始利率不能大于或等于结束利率", {
	            icon : 0
	        });
			$("#toVal").val("");
			$("#ratesVal").val("");
		}
	}
	$("#ratesName").val(ratesName);
	$("#code").val(code);
}