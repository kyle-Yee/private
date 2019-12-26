$(function(){
	//根据用户ID查找所在城市名称
	
//	$("#linksCity").val("深圳");
	//初始化状态框值
	if($("#enabled").attr("value")){$("#enabled").val($("#enabled").attr("value"));};
	
	// 判断浮点数value是否大于0
    $.validator.addMethod("isFloatGteZero", function(value, element) { 
         value=parseFloat(value);      
         return this.optional(element) || 999>value>0;       
    });
    
    $("#displayOrder").change(function(){
    	var orderVal = $("#displayOrder").val();
    	if(!/^\d+(\.\d{0,2})?$/.test(orderVal)){
    		layer.msg("小数位最多2位",{
    			icon : 2
    		});
    		$("#displayOrder").val("");
    	}
    });
    
    //URL链接验证
    var strRegex = "^((https|http|ftp|rtsp|mms)?://)"  
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@   
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184   
        + "|" // 允许IP和DOMAIN（域名）   
        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.   
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名   
        + "[a-z]{2,6})" // first level domain- .com or .museum   
        + "(:[0-9]{1,4})?" // 端口- :80 <br>  
        + "((/?)|" // a slash isn't required if there is no file name   
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
    var re = new RegExp(strRegex); 
    //链接地址判断
    $.validator.addMethod("ckHttp", function(value, element) {   
        return this.optional(element) || re.test(value);       
   });
    
});

var validate = {
    validateLinksForm : function() {
        $('#addForm').validate({
    	errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	linksName : {
            	required : true,
            	remote : {
                    url : sys.rootPath + '/links/ckByName.html',
                    data : {
                    	linksName : function() {return $("#linksName").val();},
                    	id : function() {return $("#editId").val();}
                    },
                    cache : false,
                }
            },
            linksUrl : {
            	ckHttp :true,
            },
            displayOrder : {
            	required : true,
            	isFloatGteZero : true,
            	remote : {
                    url : sys.rootPath + '/links/ckByOrder.html',
                    data : {
                    	displayOrder : function() {return $("#displayOrder").val();},
                    	id : function() {return $("#editId").val();}
                    },
                    cache : false,
                }
            }
        },
        messages : {
        	linksName : {
            	required : "请填写单位名称",
            	remote : "单位已经存在，请重新输入",
            },
            linksUrl : {
            	ckHttp :"请输入正确的URL地址",
            },
            displayOrder : {
            	required : "请输入大于0小于999的数字",
            	isFloatGteZero : "请输入大于0小于999的数字,且最多为2位小数",
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
        	var editId = $("#editId").val();
        	if ($("#basicHeadUrl").val()=="") {
				layer.msg("请选择图片",{
					icon : 2,
				});
				return;
			}
            var url = "";
            if (editId != "") {
                url = '/links/edit.html';
            } else {
                url = '/links/add.html';
            }
            //访问
            webside.common.commit('addForm', url, '/links/listUI.html');
            }
	    }); 
    },
};


function ckLinksName(){
	var linksName = $("#linksName").val();
	$.ajax({
		type : "POST",
		url : sys.rootPath + '/links/ckLinksName.html',
		data : {
			linksName : linksName
		},
		dataType : "json",
		success : function(result){
			if(result.success){
				layer.msg("合作伙伴已经存在,请重新输入！",{
					icon : 2,
					offset : ["250px",""]
				});
				$("#linksName").val("");
			}
		}
	});
}
