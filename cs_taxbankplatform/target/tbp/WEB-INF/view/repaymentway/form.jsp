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
	src="${ctx }/resources/js/customer/repaymentway/form.js"></script>
<div class="page-header">

	<h1>
		<c:if test="${empty repaymentWayEntity}">
		新增还款方式
		</c:if>
		<c:if test="${!empty repaymentWayEntity}">
		编辑还款方式
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="repaymentwayForm" name="repaymentwayForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty repaymentWayEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="wayid" value="${repaymentWayEntity.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="rwname">还款方式名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="rwname" id="rwname"
		           value="${repaymentWayEntity.rwname }" placeholder="填写还款方式名称..."/>
		      </div>
		      </div>
		   </div>		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="rwcode">还款方式编码</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="rwcode" id="rwcode"
		           value="${repaymentWayEntity.rwcode }" placeholder="填写还款方式编码..."/>
		      </div>
		      </div>
		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#repaymentwayForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty repaymentWayEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty repaymentWayEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/repaymentway/listUI.html<c:if test="${!empty repaymentWayEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>