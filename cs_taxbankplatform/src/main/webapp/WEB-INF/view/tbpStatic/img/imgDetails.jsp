<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery/jquery-2.1.4.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/upload/ajaxfileupload.js"></script>

<style>
.uploadcss {
	text-align: left;
	margin-bottom: 0;
	padding-top: 10px;
}

.forImgSize {
	width : 1350px;
	height : 300px
}

.logImgSize {
	width : 50px;
	height : 50px
}

.bankImgSize {
	width : 250px;
	height : 200px
}
</style>
<div>
	<input type="hidden" id="imgCode" value="${imgType.imgCode }" />
</div>
<div class="row" style="margin-top: 5px;">
	<div id="layerContent" class="uploadPicture">
		<img id="imgHead" src="" alt="">
		<input type="file" onchange="uploadHead()" id="basicInfoHead" style="display:none;" name="basicInfoHead" />
		<input type="hidden" id="basicHeadUrl" name="imgUrl" value="${imgType.imgUrl}">
	</div>
</div>
<script type="text/javascript">
	$(function(){
	
	var imgCode = $("#imgCode").val();
	if("forImg" == imgCode){
		$("#imgHead").addClass("forImgSize");
	}
	else if("LOG" == imgCode){
		$("#imgHead").addClass("logImgSize");
	}
	else if("bankImg" == imgCode){
		$("#imgHead").addClass("bankImgSize");
	}
	
	var imagePath = $("#basicHeadUrl").val();
	if (imagePath!="") {
		 $("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ imagePath);
	}			
});
</script>