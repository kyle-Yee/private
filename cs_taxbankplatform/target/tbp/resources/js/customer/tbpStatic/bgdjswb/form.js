$(function() {	
	//表单提交
	submitDate();
	//初始化状态框值
	if($("#enabled").attr("value")){$("#enabled").val($("#enabled").attr("value"));};
});

/**
 * 表单提交
 */
function submitDate(){
	$('#bgdjswbForm').validate({
    	errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore: 'hidden',
        highlight : function(e) {
            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
        },
        success : function(e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
            $(e).remove();
        },
        submitHandler : function(form) {
        	if ($("#basicHeadUrl").val()=="") {
				layer.msg("请选择文件",{
					icon : 2,
				});
				return;
			}
        	var enabled = $("#enabled option:selected").val();
        	var bgdjswbId = $("#bgdjswbId").val();
            var url = "";
            var fileLx = $("#fileName").val();
            if(bgdjswbId == undefined || bgdjswbId == "") {
                url = '/bgdjswb/add.html';
            } else {
                url = '/bgdjswb/edit.html';
            }
        	if(enabled == 'Y'){
        		$.ajax({
        			type : "post",
        			url : sys.rootPath + "/bgdjswb/ckByRegionId.html?fileLx="+fileLx,
        	        dataType : "json",
        	        success : function(resultdata) {
        	        	if (resultdata.success || bgdjswbId != undefined || bgdjswbId != "") {
        	        		//数据库中不存在(或者是编辑操作)
        	        		webside.common.commit('bgdjswbForm', url, '/bgdjswb/bgdjswbUI.html');
        	            } else {
        	            	//数据库中存在
        	                layer.msg(resultdata.message, {
        	                    icon : 5,
        	                });
        	                return;
        	            };
        	        }
        		});
        	}else{
        		//访问
                webside.common.commit('bgdjswbForm', url, '/bgdjswb/bgdjswbUI.html');
        	}
    	}
    }); 
}
/**
 * 下载word
 */
function onloadWord(){
	$.ajax({
		type : "POST",
		url : sys.rootPath + "/upload/getOnload.html?id="+$("#bgdjswbId").val(),
		dataType : "json",
		success : function(resultdata){
			if(resultdata.success){
    			var a = $("<a></a>").attr("href", resultdata.data).appendTo("body");
        	    a[0].click();
        	    a.remove();
			}else{
				layer.msg(resultdata.data, {
	                icon : 2,
	                offset : ["250px",""]
	            });
	    		return;
			}
		},
	});
}
/**
 * 删除文件
 */
function deleteWord(){
	layer.confirm('您确定要删除吗？', {
    icon : 3,
    title : '删除提示'
},function(index){
	var imagePath = $("#basicHeadUrl").val();
	$.ajax({
		url:sys.rootPath + "/upload/deleteImage.html",//需要链接到服务器地址   
		data:{
			imagePath:imagePath,
		},
	    dataType: 'json',   //json  
	    success: function (data){ 
	    	 //alert(data.msg);
	    }
	});
	$("#basicHeadUrl").val("");
	$("#imgHead").attr("src","");
	document.getElementById("uploadBasicInfoHead").style.display = "block";
	document.getElementById("deletepictrue").style.display = "none";
    layer.close(index);
});
}