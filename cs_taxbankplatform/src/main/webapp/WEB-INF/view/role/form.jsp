<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript">
$(function() {
    webside.form.role.validateRoleForm();
});
</script>
<div class="page-header">
	<h1>
		<c:if test="${empty roleEntity}">
		新增角色
		</c:if>
		<c:if test="${!empty roleEntity}">
		编辑角色
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="roleForm" name="roleForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty roleEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="roleId" value="${roleEntity.id }">
		</c:if>
			<div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="regionid">所属区域</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
				<select id="regionid"  name="regionid" class="form-control" onchange="regionchange()" <c:if test="${!empty roleEntity}">disabled="disabled"</c:if> >
					<c:choose>
						<c:when test="${usertype > '1'}">
							<c:forEach var="regionsList" items="${regionsList }" >
								<option value="${regionsList.id}" ${regionsList.id==roleEntity.regionid?"selected='selected'":""}>${regionsList.regionname}</option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<option value="">请选择已开通的区域</option>
							<c:forEach var="regionsList" items="${regionsList }" >
								<option value="${regionsList.id}" ${regionsList.id==roleEntity.regionid?"selected='selected'":""}>${regionsList.regionname}</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</select>
		      </div>
		      </div>
		   </div>
			<div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="orgid">所属组织</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		           <select id="orgid"  name="orgid" class="form-control" <c:if test="${!empty roleEntity}">disabled="disabled"</c:if> >
		           
					<c:choose>
						<c:when test="${usertype > '2'}">
							<c:forEach var="orgsList" items="${orgsList }" >
								<option value="${orgsList.id}" ${orgsList.id==roleEntity.orgid?"selected='selected'":""}>${orgsList.orgname}</option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<option value="">请选择所属组织</option>
							<c:forEach var="orgsList" items="${orgsList }" >
								<option value="${orgsList.id}" ${orgsList.id==roleEntity.orgid?"selected='selected'":""}>${orgsList.orgname}</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</select>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="name">角色名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="name" id="name" type="text" 
		           value="${roleEntity.name }" placeholder="角色名称..."  maxlength="25"/>
		      </div>
		      </div>
		   </div>
			<div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="key">角色标识</label>
			      <div class="col-sm-10">
			      <div class="clearfix">
			         <input class="form-control" name="key" id="key" type="text"
			          value="${roleEntity.key }" placeholder="角色标识..."  maxlength="25"/>
		      	</div>
		      	</div>
		    </div>  
		     <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">角色状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select id="status"  name="status" class="form-control" >
						<option value="Y" <c:if test="${roleEntity.status == 'Y'}">selected="selected"</c:if> >有效</option>
						<option value="N" <c:if test="${roleEntity.status == 'N'}">selected="selected"</c:if> >无效</option>
					</select>
				</div>
				</div>
			</div>
		    <%-- <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="status">角色状态</label>
		      <div class="col-sm-4">
			      <label>
			         <input class="ace ace-switch ace-switch-6" type="checkbox" name="status" id="status"
			         value="${roleEntity.status }" onclick="checkboxValue(this)"
				     <c:if test="${roleEntity.status == 1}">checked="checked"</c:if>/>
			         <span class="lbl"></span>
			      </label>
		      </div>
		    </div> --%>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="description">角色描述</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="description" id="description" type="text"
		         value="${roleEntity.description }" placeholder="角色描述..." maxlength="50"/>
		      </div>
		      </div>
		   </div> 
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#roleForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty roleEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty roleEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/role/listUI.html<c:if test="${!empty roleEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>