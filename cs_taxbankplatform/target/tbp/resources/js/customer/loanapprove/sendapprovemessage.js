function getCode(o){
	var numb = $("#phone").val();
	$.ajax({
        type : "POST",
        url : sys.rootPath + "/getPhoneCode.html",
        data : {
        	phone : numb,
        	code : "register",
        },
        dataType : "json",
        success : function(resultdata) {
            if (resultdata.success) {
            	//按钮赋值
                setVal(o);
            } else {
                layer.msg("手机验证有误", {
                    icon : 5,
                    offset : ["250px",""]
                });
            }
        }
    });
}