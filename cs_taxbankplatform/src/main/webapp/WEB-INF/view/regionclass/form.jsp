<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${ctx }/resources/js/customer/regionclass/form.js"></script>
<div class="page-header">
	<h1>
		<c:if test="${empty regionclassEntity}">
		新增区域级别
		</c:if>
		<c:if test="${!empty regionclassEntity}">
		编辑区域级别
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="addForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty regionclassEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" id="regionclassid" name="id" value="${regionclassEntity.id }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcName">区域级别</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="rcName" name="rcName" type="text" value="${regionclassEntity.rcName }" maxlength="25"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">描述</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="rcDescription" name="rcDescription" type="text" value="${regionclassEntity.rcDescription }" maxlength="100"/>
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
		<c:if test="${empty regionclassEntity}">
		添加
		</c:if>
		<c:if test="${!empty regionclassEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/regionclass/listUI.html<c:if test="${!empty regionclassEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>