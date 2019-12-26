/**
 * 表单验证
 */
function validateForm() {   
    $('#productdataitemvaluesForm').validate({
    	
    	errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	pdivName : {
                required : true
            },
        },
        messages : {
        	pdivName : {
                required : "请填写数据项值",
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
        	 var pdiId = $("#pdiId").val();
             var url = "";
  
             if(pdiId != undefined)
             {     
                 url = '/productdataitems/edit.html';
                 grid.refresh(true);
             }else
             {   
                 url = '/productdataitems/add.html';
             }

             webside.common.commit('productdataitemvaluesForm', url, '/productdataitems/listUI.html',addSuccess());
        }
    });
}


/**
 * 添加成功返回
 */

function addLink(){

	layer.open({
		type : 1,
		title : "添加数据项",
		area: ['500px', '450px'],
		skin: 'layui-layer-rim',
    	content : $("#addLinkDiv")	
	
	});
}	
function addSuccess(){
	layer.closeAll();
}


/**
 * 这句没有起到应有的作用，在实体类中使用了默认值
 * @param object
 */
function checkboxValue(object)
{
	object.value=object.checked?1:0;
}