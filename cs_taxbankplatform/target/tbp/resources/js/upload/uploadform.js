$(function(){
	var imagePath = $("#basicHeadUrl").val();
	if (imagePath!="") {
		 $("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ imagePath);
		 document.getElementById("deletepictrue").style.display = "block";
		 document.getElementById("uploadBasicInfoHead").style.display = "none";
	}else{
		 document.getElementById("deletepictrue").style.display = "none";
		 document.getElementById("uploadBasicInfoHead").style.display = "block";
	}
	
	$("#uploadBasicInfoHead").click(function(){
		$("#basicInfoHead").click(); 
	});
	
	$("#deletepictrue").click(function(){
		var imagePath = $("#basicHeadUrl").val();
		if (imagePath!="") {
			if(confirm("是否删除？")){
			$.ajax({
				url:"<%=request.getContextPath()%>/upload/deleteImage.html",//需要链接到服务器地址   
				data:{
					imagePath:imagePath
				},
			    dataType: 'json',   //json  
			    success: function (data) { 
			    	 //alert(data.msg);
			    }
			});
				$("#basicHeadUrl").val("");
				$("#imgHead").attr("src","");
				document.getElementById("uploadBasicInfoHead").style.display = "block";
				document.getElementById("deletepictrue").style.display = "none";
			}
		}else{
			alert("请先上传图片！");
		}
	});
			
});

function uploadHead(){
		$.ajaxFileUpload({  
		      url:"<%=request.getContextPath()%>/upload/uploadFile.html",//需要链接到服务器地址   
		      secureuri:false,  
		      fileElementId:"basicInfoHead",//文件选择框的id属性  
		      dataType: 'json',   //json  
		      success: function (data) { 
		    	 if(data.status=="0"){
		        		$("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ data.savePath);
						$("#basicHeadUrl").val(data.savePath);
						document.getElementById("uploadBasicInfoHead").style.display = "none";
						document.getElementById("deletepictrue").style.display = "block";
					}
					if (data.status == "1") {
						alert(data.msg);
					}
					if (data.status == "2") {
						alert(data.msg);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('上传失败！');
				}
			});
	};