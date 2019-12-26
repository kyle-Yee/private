<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/links/list.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/links/form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/upload/ajaxfileupload.js"></script>
<style>
.uploadcss {
	text-align: left;
	margin-bottom: 0;
	padding-top: 10px;
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
    right: 530px;
    top: 0;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}
</style>
<script type="text/javascript">
	$(function() {
	    validate.validateLinksForm();	
	});
</script>
<script type="text/javascript">
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
</script>

<div class="page-header">
	<h1>
		<c:if test="${empty linksEntity}">
		新增友情链接
		</c:if>
		<c:if test="${!empty linksEntity}">
		编辑友情链接
		</c:if>
	</h1>
</div>
<div>
	<input type="hidden" id="editId" value="${linksEntity.id }"/>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="addForm" name="aboutUsForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty linksEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" name="id" value="${linksEntity.id }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="linksName">合作单位名称</label>
				<div class="col-sm-9">
				<div class="clearfix">
					<input class="form-control" id="linksName" name="linksName" type="text" value="${linksEntity.linksName }" maxlength="50"/>
				</div>
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="linksCity">合作单位所属城市：</label>
				<div class="col-sm-9">
				<div class="clearfix">
					<input class="form-control" readonly="readonly" id="linksCity" name="linksCity" type="text" value="${linksEntity.linksCity }"/>
				</div>
				</div>
			</div> --%>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="linksUrl">合作单位链接地址</label>
				<div class="col-sm-9">
				<div class="clearfix">
					<input class="form-control" id="linksUrl" name="linksUrl" type="text" value="${linksEntity.linksUrl }" maxlength="100"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="displayOrder">显示顺序</label>
				<div class="col-sm-9">
				<div class="clearfix">
					<input class="form-control" id="displayOrder" name="displayOrder" type="text" value="${linksEntity.displayOrder }"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="linksPrint">上传图片</label>
					<div class="col-sm-9">
						<div class="clearfix">
							<div class="uploadcss">
							<a href="javascript:;" class="a-upload" id="uploadBasicInfoHead">上传图片（建议大小，长：190px，高：50px）
								<input type="file" onchange="uploadHead()" id="basicInfoHead" name="basicInfoHead" />
							</a>
							<a href="#deleteInfo" id="deletepictrue">删除图片</a>
							</div>
							<div id="layerContent" class="uploadPicture">
								<img id="imgHead" src="" style="width:190px;height:50px" alt="">
								<input type="hidden" id="basicHeadUrl" name="linksPrint" value="${linksEntity.linksPrint}">
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="enabled">状态</label>
				<div class="col-sm-9">
				<div class="clearfix">
					<select class="form-control" id="enabled" name="enabled" value="${linksEntity.enabled }">
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
	<button id="btnAdd" type="button" onclick="javascript:$('#addForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty linksEntity}">
		添加
		</c:if>
		<c:if test="${!empty linksEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/links/listUI.html<c:if test="${!empty linksEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>
