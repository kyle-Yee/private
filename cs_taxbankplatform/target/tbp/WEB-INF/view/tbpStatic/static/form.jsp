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
<script type="text/javascript" src="${ctx }/resources/js/customer/tbpStatic/static/form.js"></script>
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
<div>
	<input type="hidden" id="staticId" value="${staticEntity.id }">
	<input type="hidden" id="code" value="${code }">
</div>
<div class="page-header">
	<h1>
		<c:if test="${empty staticEntity}">
			<c:if test="${!empty statement }">新增免责声明</c:if>
			<c:if test="${!empty message }">新增通知公告</c:if>
			<c:if test="${!empty aboutUs }">新增关于我们</c:if>
			<c:if test="${!empty contactUs }">新增联系我们</c:if>
			<c:if test="${!empty accredit }">新增委托授权书</c:if>
			<c:if test="${!empty faq }">新增门户常见问题</c:if>
			<c:if test="${!empty agreement }">新增用户协议</c:if>
		</c:if>
		<c:if test="${!empty staticEntity}">
			<c:if test="${!empty statement }">编辑免责声明</c:if>
			<c:if test="${!empty message }">编辑通知公告</c:if>
			<c:if test="${!empty aboutUs }">编辑关于我们</c:if>
			<c:if test="${!empty contactUs }">编辑联系我们</c:if>
			<c:if test="${!empty accredit }">编辑委托授权书</c:if>
			<c:if test="${!empty faq }">编辑门户常见问题</c:if>
			<c:if test="${!empty agreement }">编辑用户协议</c:if>
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="staticForm" name="staticForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty staticEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
				<input type="hidden" name="id" value="${staticEntity.id }">
			</c:if>
				<input type="hidden" name="code" value="${code }">
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="codeName">标题</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" disabled="disabled" id="codeName" name="codeName" type="text" value="${staticName }"/>
				</div>
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="cityName">城市</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" disabled="disabled" id="cityName" name="cityName" type="text" value="${staticEntity.cityName }"/>
				</div>
				</div>
			</div> --%>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="content">内容</label>
				<div class="col-sm-10">
				<div class="clearfix">
    				<!-- 加载编辑器的容器 -->
					<script id="container" name="content" type="text/plain" style="height:300px;">
        				${staticEntity.content }
    				</script>
    				<div id="htmlTemp" style="display: none;"></div>
    				<input type="hidden" name="attachmenturls" id="attachmenturls" />
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="enabled" name="enabled" value="${staticEntity.enabled }">
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
	<button id="btnAdd" type="button" onclick="javascript:$('#staticForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;
		<c:if test="${empty staticEntity}">
		添加
		</c:if>
		<c:if test="${!empty staticEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button"
		onclick="webside.common.loadPage('/static/staticUI.html?code=<c:if test="${!empty statement }">statement</c:if><c:if test="${!empty message }">message</c:if><c:if test="${!empty aboutUs }">aboutUs</c:if><c:if test="${!empty contactUs }">contactUs</c:if><c:if test="${!empty accredit }">accredit</c:if><c:if test="${!empty faq }">faq</c:if><c:if test="${!empty agreement }">agreement</c:if>')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
