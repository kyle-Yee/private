<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${ctx }/resources/js/customer/taxauthority/form.js"></script>
<div class="page-header">
	<h1>
		<c:if test="${empty taxAuthorityEntity}">
		新增税务机关
		</c:if>
		<c:if test="${!empty taxAuthorityEntity}">
		编辑税务机关
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="taxauthorityForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty taxAuthorityEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" id="taxauthorityid" name="id" value="${taxAuthorityEntity.id }"/>
			</c:if>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="taxName">税务机关名称</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="taxName" name="taxName" type="text" value="${taxAuthorityEntity.taxName }" maxlength="50"/>
				</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="id">税务机关ID</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="id" name="id" type="text" value="${taxAuthorityEntity.id }" maxlength="24"/>
				</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="taxPrcName">上级税务机关id</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="taxPrcId" disabled="disabled" name="taxPrcId" type="text" value="${taxAuthorityEntity.taxPrcId }" maxlength="24"/>
				</div>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="taxIs">是否是局</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select id="taxIs"  name="taxIs" class="form-control" >
						<option value="Y" <c:if test="${taxAuthorityEntity.taxIs == 'Y'}">selected="selected"</c:if> >是</option>
						<option value="N" <c:if test="${taxAuthorityEntity.taxIs == 'N'}">selected="selected"</c:if> >否</option>
					</select>
				</div>
				</div>
		   </div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">有效标志</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select id="enabled"  name="enabled" class="form-control" >
						<option value="Y" <c:if test="${taxAuthorityEntity.enabled == 'Y'}">selected="selected"</c:if> >是</option>
						<option value="N" <c:if test="${taxAuthorityEntity.enabled == 'N'}">selected="selected"</c:if> >否</option>
					</select>
				</div>
				</div>
		   </div>
			
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#taxauthorityForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty taxAuthorityEntity}">
		添加
		</c:if>
		<c:if test="${!empty taxAuthorityEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/taxauthority/listUI.html<c:if test="${!empty taxAuthorityEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>