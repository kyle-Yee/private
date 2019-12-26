<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
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
	src="${ctx }/resources/js/customer/loanstatus/form.js"></script>
<div class="page-header">

	<h1>
		<c:if test="${empty loanStatusEntity}">
		新增担保方式
		</c:if>
		<c:if test="${!empty loanStatusEntity}">
		编辑担保方式
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="loanstatusForm" name="loanstatusForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty loanStatusEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="statusid" value="${loanStatusEntity.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="lsname">担保方式名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="lsname" id="lsname"
		           value="${loanStatusEntity.lsname }" placeholder="担保方式名称..."/>
		      </div>
		      </div>
		   </div>		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="ccode">担保方式编码</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="lscode" id="lscode"
		           value="${loanStatusEntity.lscode }" placeholder="担保方式编码..."/>
		      </div>
		      </div>
		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#loanstatusForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty loanStatusEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty loanStatusEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/loanstatus/listUI.html<c:if test="${!empty loanStatusEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>