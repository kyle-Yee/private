<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/tbpStatic/img/form.js"></script>	
<script type="text/javascript"
	src="${ctx }/resources/js/upload/ajaxfileupload.js"></script>

<style>
.uploadcss {
	text-align: left;
	margin-bottom: 0;
	padding-top: 10px;
}

.forImgSize {
	width : 1920px;
	height : 600px
}

.logImgSize {
	width : 60px;
	height : 60px
}

.bankImgSize {
	width : 300px;
	height : 240px
}
.a-upload {
    height: 20px;
    position: relative;
    cursor: pointer;
    color: #5993c5;
    border: none;
    overflow: hidden;
    display: inline-block;
    *display: inline;
    *zoom: 1
}
.a-upload  input {
    position: absolute;
    font-size: 100px;
    right: 634px;
    top: 0;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}
</style>
<div>
	<input type="hidden" id="imgId" value="${imgEntity.id }"/>
</div>
<div class="page-header">
	<h1>
		<c:if test="${empty imgEntity}">
		图片维护添加
		</c:if>
		<c:if test="${!empty imgEntity}">
		图片维护修改
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="imgForm" name="imgForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty imgEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" name="id" value="${imgEntity.id }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="imgCode">图片类型</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<!-- 这里需要改成动态加载的 -->
					<select class="form-control" id="imgCode" name="imgCode" value="${imgEntity.imgCode }">
						<option value="">请选择上传图片类型</option>
						<c:forEach var="imgType" items="${imgType}">
						<option value="${imgType.imgCode }">${imgType.imgName }</option>
						</c:forEach>
					</select>
				</div>
				</div>
			</div>
			<div id="orderDiv" class="form-group" style="display:none">
				<label class="control-label col-sm-1 no-padding-right" for="order">图片顺序</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="order" name="order" type="text" value="${imgEntity.order }"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="imgUrl">上传图片</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<div class="uploadcss">
					<a href="javascript:;" id="uploadBasicInfoHead" class="a-upload">上传图片
						<span class="forDiv" style="display:none">（建议大小，长：1920px，高：600px）</span>
						<span class="ggDiv" style="display:none">（建议大小，长：300px，高：240px）</span>
						<span class="logoDiv" style="display:none">（建议大小，长：60px，高：60px）</span>
						<input type="file" onchange="uploadHead()" id="basicInfoHead" name="basicInfoHead" />
					</a>
					<a href="#deleteInfo" id="deletepictrue">删除图片</a>
					</div>
					<div id="layerContent" class="uploadPicture">
						<img id="imgHead" src="" alt="">
						<input type="hidden" id="basicHeadUrl" name="imgUrl" value="${imgEntity.imgUrl}">
					</div>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="targetUrl">链接地址</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="targetUrl" name="targetUrl" type="text" value="${imgEntity.targetUrl }" placeholder="点击图片时,可链接到的地址"/>
				</div>
				</div>
			</div>					
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="enabled" name="enabled" value="${imgEntity.enabled }">
						<option value="Y">有效</option>
						<option value="N">无效</option>
					</select>
				</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#imgForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty imgEntity}">
		添加
		</c:if>
		<c:if test="${!empty imgEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/img/imgUI.html<c:if test="${!empty imgEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>
<script type="text/javascript">
	$(function(){
	var imagePath = $("#basicHeadUrl").val();
	if (imagePath!="") {
		<%--  $("#imgHead").attr("src","<%=request.getContextPath()%>/"+ imagePath); --%>
		$("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ imagePath);
		 document.getElementById("deletepictrue").style.display = "block";
		 document.getElementById("uploadBasicInfoHead").style.display = "none";
	}else{
		 document.getElementById("deletepictrue").style.display = "none";
		 document.getElementById("uploadBasicInfoHead").style.display = "block";
	}
		
	$("#deletepictrue").click(function(){
		var imagePath = $("#basicHeadUrl").val();
		if (imagePath!="") {
			if(confirm("是否删除？")){
				deleteImg();
			}
		}else{
			alert("请先上传图片！");
		}
	});
			
});

function deleteImg(){
	var imagePath = $("#basicHeadUrl").val();
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

function uploadHead(){
	var imgCode = $("#imgCode").select().val();
	if("" == imgCode){
		layer.msg("请选择图片类型",{
			icon : 2,
		});
		return;
	}else if("forImg" == imgCode){
		$("#imgHead").addClass("forImgSize");
	}else if("LOG" == imgCode){
		$("#imgHead").addClass("logImgSize");
	}else if("bankImg" == imgCode){
		$("#imgHead").addClass("bankImgSize");
	}

	$.ajaxFileUpload({  
	      url:"<%=request.getContextPath()%>/upload/uploadFile.html",//需要链接到服务器地址   
	      secureuri:false,  
	      fileElementId:"basicInfoHead",//文件选择框的id属性  
	      dataType: 'json',   //json  
	      success: function (data) { 
	    	 if(data.status=="0"){
		        		$("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ data.savePath);
// 						$("#imgHead").attr("src","<%=request.getContextPath()%>/"+data.savePath);
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
</script>