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
//        rules : {
//        	yhmc : {
//                required : true,
//                maxlength:25
//                
//            },
//        	yhdm : {
//                required : true,
//                maxlength:50
//            },
//            zxyys : {
//                required : true,
//                maxlength:25
//            },
//            zxbh : {
//                required : true
//            },
//            yhdkh : {
//                required : true,
//                digits:true,
//                maxlength:10
//            },
//            yhfwqipdz : {
//                required : true
//            },
//            yhzxlldz : {
//                required : true
//            },
//            sjdkh : {
//                required : true,
//                digits:true,
//                maxlength:10
//            },
//            sjfwqipdz : {
//                required : true
//            },
//            sjzxlldz : {
//                required : true
//            },
//            // 3.0v modify by Sigua.Huang 2018/06/19 begin
//            bankaccount : {
//                required : true
//            },
//            password : {
//                required : true,
//                minlength:6,
//                maxlength:25
//            },
//            // 3.0v modify by Sigua.Huang 2018/06/19 end
//            bz : {
//                required : true,
//                maxlength:25
//            }
//        },
        messages : {
        	
        	yhmc : {
                required : "请选择银行名称",
                maxlength:"银行名称名称不能大于25个汉字"
            },
        	yhdm : {
                required : "请填写银行代码",
                maxlength:"用户名称不能大于50个字符"
            },
            zxyys : {
                required : "请填写专线运营商",
                maxlength:"专线运营商名称不能大于25个汉字"
            },
            zxbh : {
                required : "请填写专线编号"
            },
            yhdkh : {
                required : "请填写银行端口号",
                digits:"必须输入数字",
                maxlength:"填写税局端口号不能大于10个数字"
            },
            yhfwqipdz : {
                required : "请填写银行服务器IP地址"
            },
            yhzxlldz : {
                required : "请填写银行专线链路地址",
                digits:"必须输入数字",
                maxlength:"填写银行专线链路地址不能大于25个汉字"
            },
            sjdkh : {
                required : "请填写税局端口号",
                digits:"必须输入数字",
                maxlength:"填写税局端口号不能大于10数字"
            },
            sjfwqipdz : {
                required : "请填写税局服务器IP地址"
            },
            sjzxlldz : {
                required : "请填写税局专线链路地址",
                digits:"必须输入数字",
                maxlength:"填写税局专线链路地址不能大于25个汉字"
            },
            bankaccount : {
                required : "请配置行方请求账号"
            },
            password : {
                required : "请配置行方请求密码",
                minlength: "行方密码长度不能小于6",
                maxlength: "行方密码长度不能大于25"
            },
            bz : {
                required : "请填写备注",
                maxlength:"填写备注不能大于25个汉字"
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
                url = '/portManager/edit.html';
            }else
            {	
                url = '/portManager/add.html';
            }
            webside.common.commit('addForm', url, '/portManager/listUI.html');
        }
    });
}

function getfunction(){
	var options=$("#yhmc option:selected");
	var orgName = options.val();
	if(orgName == ""){
		return;
	}
    $.ajax({   
        type: 'POST',  
        dataType : "json", 
        data : {
        	orgName : orgName
        },
        url: sys.rootPath +'/portManager/findOrgCode.html',  
        error: function () { 
            alert('请求失败');  
        },  
        success:function(map){  
        	$("#yhdm").val(map.data);
        	//document.getElementById("yhmc").value="somethind"; 
        }  
    });
	
}