<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/tbpStatic/parameters/form.js"></script>
<div>
	<input type="hidden" id="parametersId" value="${parametersEntity.id }"/>
</div>	
<div class="page-header">
	<h1>
		<c:if test="${empty parametersEntity}">
		网站参数添加
		</c:if>
		<c:if test="${!empty parametersEntity}">
		网站参数维护
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="parametersForm" name="parametersForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty parametersEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" name="id" value="${parametersEntity.id }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="name">名称</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="name" name="name" type="text" value="${parametersEntity.name }" maxlength="25"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="code">类型</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="code" name="code" type="text" value="${parametersEntity.code }" placeholder="输入可代表该名称的唯一标识,为数字和英文的组合" maxlength="50"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="value">内容</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="value" name="value" type="text" value="${parametersEntity.value }" maxlength="50"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="enabled" name="enabled" value="${parametersEntity.enabled }">
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
	<button id="btnAdd" type="button" onclick="javascript:$('#parametersForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty parametersEntity}">
		添加
		</c:if>
		<c:if test="${!empty parametersEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/parameters/parametersUI.html<c:if test="${!empty parametersEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>