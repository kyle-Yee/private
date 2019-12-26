var ue;
$(function() {
	//实例化编辑器 
	ue = UE.getEditor('container');
	//初始化状态框值
	if($("#enabled").attr("value")){$("#enabled").val($("#enabled").attr("value"));}
	
	//表单提交
	submitDate();
	
	//目前写固定
//	$("#cityName").val("深圳");
});

/**
 * 表单提交
 */
function submitDate(){
	$('#staticForm').validate({
    	errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
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
        	var staticId = $("#staticId").val();
        	var staticCode = $("#code").val();
            var url = "";
            if (staticId != "") {
                url = '/static/edit.html';
                var enabled = $("#enabled").val();
                if(enabled == "Y"){
                	//需要验证信息的唯一性
                	var code = $("#code").val();
                	$.ajax({
                		type : "POST",
                		url : sys.rootPath + '/static/searchList.html',
                		data : {
                			code : code,
                			id:staticId
                		},
                		async : false,
                		dataType : "json",
                		success : function(result){
                			if(result.success){
                				layer.msg("信息已经存在！",{
                					icon : 2,
                					offset : ["250px",""]
                				});
                				return;
                			}else{
                                //访问(编辑页面返回时候带"?page=*"的参数)
                                webside.common.commit('staticForm', url, '/static/editToUI.html?code=' + staticCode);
                			}
                		}
                	});
                }else{
                    //访问(编辑页面返回时候带"?page=*"的参数)
                    webside.common.commit('staticForm', url, '/static/editToUI.html?code=' + staticCode);
                }
            } else {
                url = '/static/add.html';
              //访问
                webside.common.commit('staticForm', url, '/static/staticUI.html?code=' + staticCode);
            }
        }
    }); 
}