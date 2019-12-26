var ue;
$(function() {
	//实例化编辑器 
	ue = UE.getEditor('container');
	//初始化状态框值
	if($("#enabled").attr("value")){$("#enabled").val($("#enabled").attr("value"));}
	if($("#dept").attr("value")){$("#dept").val($("#dept").attr("value"));}
	
	$("#date").datetimepicker({
		lang : "ch",
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
		
	});
	
	//表单提交
	submitDate();
	
	//目前写固定
//	$("#cityName").val("深圳");
});

/**
 * 表单提交
 */
function submitDate(){
	$('#newsForm').validate({
    	errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	title : {
            	required : true,
            },
            dept : {
            	required : true,
            },
            date : {
            	required : true,
            },
            content : {
            	required : true,
            }
        },
        messages : {
        	title : {
            	required : "请填写标题",
            },
            dept : {
            	required : "请填写发布方",
            },
            date : {
            	required : "请选择发布日期",
            },
            content : {
            	required : "请填写发布内容",
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
        	var newsId = $("#newsId").val();
        	var newsCode = $("#code").val();
            var url = "";
            if (newsId != "") {
                url = '/news/edit.html';
              //访问(编辑页面返回时候带"?page=*"的参数)
	            webside.common.commit('newsForm', url, '/news/editToUI.html?code=' + newsCode);
            } else {
                url = '/news/add.html';
              //访问
	            webside.common.commit('newsForm', url, '/news/newsUI.html?code=' + newsCode);
            }
            }
	    }); 
}