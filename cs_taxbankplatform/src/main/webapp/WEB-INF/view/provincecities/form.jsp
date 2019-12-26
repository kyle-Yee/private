<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<style>
	.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
</style>
<link rel="stylesheet"
	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/select2/zh-CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/customer/provincecities/form.js"></script>
<div class="page-header">

	<h1>
		<c:if test="${empty provinceCitiesEntity}">
		新增区域
		</c:if>
		<c:if test="${!empty provinceCitiesEntity}">
		编辑区域
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="provincecitiesForm" name="provincecitiesForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty provinceCitiesEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="cities" value="${provinceCitiesEntity.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="pcpid">所属上级</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="pcpid" id="pcpid"
		           value="${provinceCitiesEntity.pcpid }" placeholder="请选择所属上级..."/>
		      </div>
		      </div>
		   </div>		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="pcname">省市区名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="pcname" id="pcname"
		           value="${provinceCitiesEntity.pcname }" placeholder="请填写省市区名称..."/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="pcname">省市区编号</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="pccode" id="pccode"
		           value="${provinceCitiesEntity.pccode }" placeholder="请填写省市区编号..."/>
		      </div>
		      </div>
		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#provincecitiesForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty provinceCitiesEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty provinceCitiesEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/provincecities/listUI.html<c:if test="${!empty provinceCitiesEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>