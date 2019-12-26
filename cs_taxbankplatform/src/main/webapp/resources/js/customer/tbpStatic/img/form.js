$(function() {	
	//表单提交
	submitDate();
	//初始化状态框值
	if($("#enabled").attr("value")){$("#enabled").val($("#enabled").attr("value"));};
	if($("#imgCode").attr("value")){$("#imgCode").val($("#imgCode").attr("value"));};
	
	var codeVal = $("#imgCode").val();
	if("forImg" == codeVal){
		$("#orderDiv").show();
	}
	$("#imgCode").change(function(){
		var imgCodeVal = $("#imgCode").val();
		if("forImg" == imgCodeVal){
			$("#orderDiv").show();
			$("#order").val("");
		}else{
			$("#orderDiv").hide();
			$("#order").val(1);
		}
		
		//显示不同的提示
		if("" == imgCodeVal){
			$(".forDiv").hide();
			$(".ggDiv").hide();
			$(".logoDiv").hide();
		}else if("forImg" == imgCodeVal){
			$(".forDiv").show();
			$(".ggDiv").hide();
			$(".logoDiv").hide();
		}else if("LOG" == imgCodeVal){
			$(".logoDiv").show();
			$(".forDiv").hide();
			$(".ggDiv").hide();
		}else if("bankImg" == imgCodeVal){
			$(".ggDiv").show();
			$(".forDiv").hide();
			$(".logoDiv").hide();
		}
	});
	$("#imgCode").change(function(){
		var aaa = document.getElementById("imgHead").src;
		var bbb = aaa.split("?imagePath=")[1];
		if(bbb == undefined){
			//没图片
		}else{
			//有图片（删除图片）
			deleteImg();
		}
	});
	// 判断浮点数value是否大于0
    $.validator.addMethod("isFloatGteZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || 999>value>0;       
    });
    
    $("#order").change(function(){
    	var orderVal = $("#order").val();
    	if(!/^\d+(\.\d{0,2})?$/.test(orderVal)){
    		layer.msg("小数位最多2位",{
    			icon : 2
    		});
    		$("#order").val("");
    	}
    });
});

/**
 * 表单提交
 */
function submitDate(){
	$('#imgForm').validate({
    	errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore: 'hidden',
        rules : {
        	imgCode : {
            	required : true,
            	remote : {
                    url : sys.rootPath + '/img/ckByCode.html',
                    data : {
                    	imgCode : function() {return $("#imgCode").select().val();},
						id : function() {return $("#imgId").val();},
                    },
                    cache : false,
                }
            },
            order : {
            	required : true,
            	isFloatGteZero : true,
            	remote : {
                    url : sys.rootPath + '/img/ckByOrder.html',
                    data : {
                    	order : function() {return $("#order").val();},
                    	id : function() {return $("#imgId").val();},
                    	imgCode : function() {return $("#imgCode").select().val();}
                    },
                    cache : false,
                }
            }
        },
        messages : {
        	imgCode : {
            	required : "请选择图片类型",
            	remote : "类型已经存在，请重新输入",
            },
            order : {
            	required : "请输入大于0小于999的数字",
            	isFloatGteZero : "请输入大于0小于999的数字",
            	remote : "数据已经存在，请重新输入",
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
                error.insertAfter(element.parent());
        },
        submitHandler : function(form) {
        	if ($("#basicHeadUrl").val()=="") {
				layer.msg("请选择图片",{
					icon : 2,
				});
				return;
			}
        	var imgId = $("#imgId").val();
            var url = "";
            if (imgId != "") {
                url = '/img/edit.html';
            } else {
                url = '/img/add.html';
            }
            //访问
            webside.common.commit('imgForm', url, '/img/imgUI.html');
            }
	    }); 
}