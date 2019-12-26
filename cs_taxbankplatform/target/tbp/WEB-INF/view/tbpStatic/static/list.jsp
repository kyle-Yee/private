<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/tbpStatic/static/list.js"></script>

<div>
	<input type="hidden" id="code" value="${code}">
</div>

<div class="page-header">
	<shiro:hasPermission name="static:add">
	<button id="btnAdd" type="button" onclick="addUI('/static/addUI.html','${code}')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;新增
	</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="static:edit">
	<button id="btnEdit" type="button" onclick="webside.common.editModel('/static/editUI.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;编辑
	</button>
	</shiro:hasPermission>
</div>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header" style="display:inline;width:100%">
				<div style="float:left;width:70%">
					<c:if test="${!empty statement }">
						<h4 class="widget-title lighter">免责声明维护</h4>
					</c:if>
					<c:if test="${!empty message }">
						<h4 class="widget-title lighter">通知公告维护</h4>
					</c:if>
					<c:if test="${!empty aboutUs }">
						<h4 class="widget-title lighter">关于我们维护</h4>
					</c:if>
					<c:if test="${!empty contactUs }">
						<h4 class="widget-title lighter">联系我们维护</h4>
					</c:if>
					<c:if test="${!empty accredit }">
						<h4 class="widget-title lighter">受托委权书维护</h4>
					</c:if>
					<c:if test="${!empty faq }">
						<h4 class="widget-title lighter">门户常见问题</h4>
					</c:if>
					<c:if test="${!empty agreement }">
						<h4 class="widget-title lighter">用户协议</h4>
					</c:if>
				</div>
				<div class="input-group" style="float:right;width:30%">
				     <input id="searchKey" type="text" class="input form-control" placeholder="输入维护名称">
				     <span class="input-group-btn">
				         <button id="btnSearch" class="btn btn-primary btn-sm" type="button">搜索</button>
				     </span>
				</div>
			</div>

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>



