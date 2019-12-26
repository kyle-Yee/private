<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%    
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
		response.setHeader("Pragma","no-cache"); //HTTP 1.0    
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
	%>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/financialProduct/listPublish.js"></script>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<div class="form-group row">
					<div class="col-md-4"><H4>待发布产品列表</H4>
					</div>
					<div class="col-md-4 col-md-offset-4">
						<div class="input-group">
						     <input id="searchKey" type="text" class="input form-control" placeholder="输入产品名称">
						     <span class="input-group-btn">
						         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 查询</button>
						     </span>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="faqReadOnly" value="${faqReadOnly }">
			<input type="hidden" id="regionShow" value="${regionShow }">
			<input type="hidden" id="orgShow" value="${orgShow }">
			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" style="overflow:hidden;" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
			<div class="widget-header" style="margin-right:0px; float:right;">
				<c:if test="${!readOnly }">
						<button style="width:120px;height:40px" class="btn btn-success" onclick="updateStatusF()">
						<i class="glyphicon glyphicon-check"></i>
						发布
						</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
