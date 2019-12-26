/**
 * 表单验证
 */
function validateValueAddForm() {    
    $('#addValueForm').validate({
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
        	 var pdivId = $("#pdivId").val();
        	 var pdiId=$("#pdiDIvId").val();
             var url = "";
             if(pdiId != undefined && pdivId=="")
             { 
            	 url = '/productdataitems/addValue.html';
            //	 grid.refresh(true);
            	 
             }else
             {    
                 url = '/productdataitems/editValue.html?pdivId=' + pdivId;
         
             }
             

             webside.common.commit('addValueForm', url, '/productdataitems/editList.html',addSuccess());
        }
    });
    
    
}
function addSuccess(){
	layer.closeAll();
	grid.refresh(true);
}
	
/**
 * 这句没有起到应有的作用，在实体类中使用了默认值
 * @param object
 */
function checkboxValue(object)
{
	object.value=object.checked?1:0;
}
function addLink(){

	var pdiId = $("#pdiId").val();
	$("#pdiDIvId").val(pdiId);
	$("#pdivName").val("");
	layer.open({
		type : 1,
		title : "添加数据项",
		area: ['500px', '250px'],
		skin: 'layui-layer-rim',
    	content : $("#addLinkDiv")
	});
}
/*
function validateNext(){
	 //校该数据项名字已存在
	var pdivNameval = $("#pdivName").val();
	var pdiId = $("#pdiDIvId").val();
	alert(pdiId);
	var checkpdivName =false;
	if(pdivNameval!=null && pdivNameval!="" && pdivNameval.length > 0){
		var notExist = true;
		$.ajax({
			async:false,
			type : "POST",
			url : sys.rootPath +'/productdataitems/checkpdivName.html?pdiId='+pdiId,
			data : {
				"pdivName" : pdivNameval
			},
			dataType : "json",
			success : function(resultdata) {
				notExist = resultdata;
				if(notExist == false){
					alert("该数据项名字已存在!");
					$("#pdivName").focus();
				}
			}
		});
		checkpdivName = notExist;
		if(checkpdivName == false){
			return;
		}
	}else{
		alert("该数据项名字已存在!");
		$("#pdivName").focus();
		checkpdivName =  false;
		return;
	}
}*/
