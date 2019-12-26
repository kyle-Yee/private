<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/tbpStatic/bgdjswb/form.js"></script>	
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
	height : 240px
}

.logImgSize {
	width : 300px;
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
<div class="page-header">
	<h1>
		<c:if test="${empty bgdjswbEntity}">
		新增变更登记税务表/模板
		</c:if>
		<c:if test="${!empty bgdjswbEntity}">
		编辑变更登记税务表/模板
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="bgdjswbForm" name="bgdjswbForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty bgdjswbEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" name="id" id="bgdjswbId" value="${bgdjswbEntity.id }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="targetUrl">文件名称</label>
				<div class="col-sm-10">
				<c:if test="${empty bgdjswbEntity}">
					<div class="clearfix">
						<select class="form-control" id="fileName" name="fileLx" value="${bgdjswbEntity.fileLx }">
							<option value="">请选择变更登记税务表/模板</option>
							<option value="B">变更登记税务表</option>
							<option value="M">变更登记税务表模板</option>
						</select>
					</div>
				</c:if>
				<c:if test="${!empty bgdjswbEntity}">	
					<div class="clearfix">
						<select class="form-control" id="fileName" name="fileLx" disabled="disabled" value="${bgdjswbEntity.fileLx }">
							<option value="B">变更登记税务表</option>
							<option value="M">变更登记税务表模板</option>
						</select>
					</div>				
				</c:if>
				</div>
				<input type="hidden" name="fileName" id="fileLxVal"/>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="imgUrl">上传文件</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<div class="uploadcss">
					<a href="javascript:;" id="uploadBasicInfoHead" class="a-upload">上传文件
						<input type="file" onchange="uploadHead()" id="basicInfoHead" name="basicInfoHead" />
					</a>
					<span id="deletepictrue"><a href="#deleteInfo" onclick="deleteWord()">删除文件</a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="onloadWord()">查看文件</a></span>
					</div>
					<div id="layerContent" class="uploadPicture">
						<input type="hidden" id="basicHeadUrl" name="fileUrl" value="${bgdjswbEntity.fileName}">
					</div>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="deleteStatus">状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="enabled" name="enabled" value="${bgdjswbEntity.enabled }">
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
	<button id="btnAdd" type="button" onclick="javascript:$('#bgdjswbForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty bgdjswbEntity}">
		添加
		</c:if>
		<c:if test="${!empty bgdjswbEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/bgdjswb/bgdjswbUI.html<c:if test="${!empty bgdjswbEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>
<script type="text/javascript">
$(function(){	
	$("#fileName").change(function(){
		//有文件把文件删除
		var imagePath = $("#basicHeadUrl").val();
		if (imagePath!="") {
			deleteFile();
		};
		//隐藏域赋值（存储文件名称）
		$("#fileLxVal").val($("#fileName option:selected").text());
	});
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
		
	/* $(".deletepictrue").click(function(){
		var imagePath = $("#basicHeadUrl").val();
		if (imagePath!="") {
			if(confirm("是否删除？")){
				deleteFile();
			};
		}else{
			alert("请先上传文件！");
		};
	}); */
			
});

function uploadHead(){
	var lx = $("#fileName").val();
	if(lx == ""){
		layer.msg("请先选择变更登记税务表/模板", {
            icon : 5,
        });
        return;
	}
	$.ajaxFileUpload({  
	      url:"<%=request.getContextPath()%>/upload/uploadFile.html?fileCode=fileUpload&fileLx="+lx,//需要链接到服务器地址   
	      secureuri:false,  
	      fileElementId:"basicInfoHead",//文件选择框的id属性  
	      dataType: 'json',   //json  
	      success: function (data) { 
	    	 if(data.status=="0"){
	        		$("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ data.savePath);
	// 				$("#imgHead").attr("src","<%=request.getContextPath()%>/"+data.savePath);
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