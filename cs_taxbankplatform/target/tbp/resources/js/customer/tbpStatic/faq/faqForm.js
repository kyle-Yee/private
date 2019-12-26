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
	$('#faqForm').validate({
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
                error.insertAfter(element.parent());
        },
        submitHandler : function(form) {
        	var faqId = $("#faqId").val();
            var url = "";
            if (faqId != undefined) {
                url = '/faq/edit.html';
            } else {
                url = '/faq/add.html';
            }
            //访问
            webside.common.commit('faqForm', url, '/faq/faqUI.html');
            }
	    }); 
}