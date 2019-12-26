/**
 * 表单验证
 */
function validateAddForm() {    
    $('#addForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	pdiName : {
                required : true
            }
        },
        messages : {
        	pdiName : {
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
        		var selectVal = $("#SEL").select().val();
        		var pdivName = $("#pdivName").val();
        		if("0"== selectVal){
        			layer.msg("请选择项目填写方式",{
        				icon : 2,
        			});
        			return;
        		}

                 url = '/productdataitems/add.html';
  
             if(validateSubmit())
             webside.common.commit('addForm', url, '/productdataitems/listUI.html',addSuccess());
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
    	content : $("#addLinkDiv"),
//		content: sys.rootPath + '/productdataitems/addPdiUI.html',
    	btn : ["保存","取消"],
    	yes : function(index, layero){
    		$('#addForm').submit();
    	},
		cancel : function(index, layero){
			layer.closeAll();
		}
	});
}	
function addSuccess(){
	layer.closeAll();
}
function validateNext(){
	 //校该数据项名字已存在
	var pdiNameval = $("#pdiName").val();
	var checkpdiName =false;
	if(pdiNameval!=null && pdiNameval!="" && pdiNameval.length > 0){
		var notExist = true;
		$.ajax({
			async:false,
			type : "POST",
			url : sys.rootPath +'/productdataitems/checkpdiName.html',
			data : {
				"pdiName" : pdiNameval
			},
			dataType : "json",
			success : function(resultdata) {
				notExist = resultdata;
				if(notExist == false){
					 layer.msg("该数据项名称已存在", {
				            icon : 3
				        });
					$("#pdiName").focus();
				}
			}
		});
		checkpdiName = notExist;
		if(checkpdiName == false){
			return;
		}
	}/*else{
		alert("请填写数据项!");
		$("#pdiName").focus();
		checkpdiName =  false;
		return;
	}*/
}

function validateSubmit(){
	 //校该数据项名字已存在
	var pdiNameval = $("#pdiName").val();
	var notExist = true;
	if(pdiNameval!=null && pdiNameval!="" && pdiNameval.length > 0){
		$.ajax({
			async:false,
			type : "POST",
			url : sys.rootPath +'/productdataitems/checkpdiName.html',
			data : {
				"pdiName" : pdiNameval
			},
			dataType : "json",
			success : function(resultdata) {
				notExist = resultdata;
				if(notExist == false){
					 layer.msg("该数据项名称已存在", {
				            icon : 3
				        });
					$("#pdiName").focus();
				}
			}
		});
	}
	return notExist;
}

/**
 * 这句没有起到应有的作用，在实体类中使用了默认值
 * @param object
 */
function checkboxValue(object)
{
	object.value=object.checked?1:0;
}