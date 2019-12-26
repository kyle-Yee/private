<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%    
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
		response.setHeader("Pragma","no-cache"); //HTTP 1.0    
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
	%>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/message/list.js"></script>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<input type="hidden" id="typeId" value="${messageType.id }">
			<div class="widget-header">
				<div>
				     <div class="widget-toolbar no-border no-padding-right">
						<a href="#" data-action="fullscreen" class="orange2"> 
							<i class="ace-icon fa fa-arrows-alt"></i>
						</a> 
						<a href="#" data-action="collapse" class="green"> 
							<i class="ace-icon fa fa-chevron-up"></i>
						</a>
					</div>
				</div>
				<div class="form-group row">
				<c:choose>
					<c:when test="${!empty messageType.id}">
						<div class="col-md-4"><H4>${messageType.typeName }列表</H4></div>
					</c:when>
					<c:otherwise>
						<div class="col-md-4"><H4>所有消息列表</H4></div>
					</c:otherwise>
				</c:choose>
					<div class="col-md-4 col-md-offset-4">
						<div class="input-group">
						     <input id="searchKey" type="text" class="input form-control" placeholder="输入消息标题">
						     <span class="input-group-btn">
						         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 查询</button>
						     </span>
						</div>
					</div>
				</div>
			</div>

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container" style="overflow:hidden;"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>


