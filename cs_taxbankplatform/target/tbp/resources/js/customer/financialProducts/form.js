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
        	yhmc : {
                required : true,
                maxlength:25
                
            },
        	yhdm : {
                required : true,
                maxlength:50
            },
            cpmc : {
                required : true,
                maxlength:25
            },
            cpdm : {
                required : true,
                maxlength:25
            }
        },
        messages : {
        	
        	yhmc : {
                required : "请选择银行名称",
                maxlength:"银行名称名称不能大于25个汉字"
            },
        	yhdm : {
                required : "请填写银行代码",
                maxlength:"用户名称不能大于50个字符"
            },
            cpmc : {
                required : "请填写产品名称",
                maxlength:"产品名称不能大于25个汉字"
            },
            cpdm : {
                required : "请填写产品编号",
                maxlength:"产品编号不能大于25个字符"
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
                url = '/financialProducts/edit.html';
            }else
            {	
                url = '/financialProducts/add.html';
            }
            webside.common.commit('addForm', url, '/financialProducts/listUI.html');
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
        url: sys.rootPath +'/financialProducts/findOrgCode.html',  
        error: function () { 
            alert('请求失败');  
        },  
        success:function(map){  
        	$("#yhdm").val(map.data);
        	//document.getElementById("yhmc").value="somethind"; 
        }  
    });
	
}