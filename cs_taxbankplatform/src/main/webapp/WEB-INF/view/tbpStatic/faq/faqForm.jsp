<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet"
	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript">
    ctx = "${ctx }"; 
	window.UEDITOR_HOME_URL = "${ctx }/resources/js/ueditor/";
</script>
<!-- 配置文件 -->
<script type="text/javascript" 
	src="${ctx }/resources/js/customer/financialProduct/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" 
	src="${ctx }/resources/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/customer/tbpStatic/faq/faqForm.js"></script>	

<style>
.iconShow
{
	padding-left: 10px; 
	padding-top: 3px;
}
.source-icon
{
	 width:95%;
	 float:left;
}
@media screen and (max-width: 374px){
	.source-icon
	{
		 width:82%;
	}
}
@media screen and (min-width: 375px) and (max-width: 449px){
	.source-icon
	{
		 width:85%;
	}
}
@media screen and (min-width: 450px) and (max-width: 1279px) {
	.source-icon
	{
		 width:90%;
	}
}
@media screen and (min_width: 1280px) {
	.source-icon
	{
		 width:90%;
	}
}
</style>
<div class="page-header">
	<h1>
		<c:if test="${empty faqEntity}">
		常见问题公告
		</c:if>
		<c:if test="${!empty faqEntity}">
		常见问题公告
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="faqForm" name="faqForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty faqEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" name="id" id="faqId" value="${faqEntity.id }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="faqName">问题</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="faqName" name="faqName" type="text" readonly="readonly" value="页面常见问题"/>
				</div>
				</div>
			</div>
			<!-- <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="cityName">城市</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" disabled="disabled" id="cityName" name="cityName" type="text"/>
				</div>
				</div>
			</div> -->
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="faqContent">内容</label>
				<div class="col-sm-10">
				<div class="clearfix">
					
					<script id="container" name="faqContent" type="text/plain" style="height:300px;">
        				${faqEntity.faqContent }
    				</script>
    				<div id="htmlTemp" style="display: none;"></div>
    				<input type="hidden" name="attachmenturls" id="attachmenturls"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="deleteStatus">状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="enabled" name="enabled" value="${faqEntity.enabled }">
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
	<button id="btnAdd" type="button" onclick="javascript:$('#faqForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty faqEntity}">
		添加
		</c:if>
		<c:if test="${!empty faqEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/faq/faqUI.html<c:if test="${!empty faqEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>