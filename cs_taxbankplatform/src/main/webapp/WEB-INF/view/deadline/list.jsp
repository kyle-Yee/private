<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/deadline/form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/deadline/list.js"></script>
	<script type="text/javascript">
	$(function() {
	    validate.validateDeadlineForm();
	});
</script>
<div class="page-header">
	<shiro:hasPermission name="deadline:add">
	<button id="btnAdd" type="button" onclick="add()" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;新增
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
					<h4 class="widget-title lighter">授信期限维护</h4>				
				</div>
				<div class="input-group" style="float:right;width:30%">
				     <input id="searchKey" type="text" class="input form-control" placeholder="输入贷款期限">
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
	
	<!-- 添加弹出框 -->
	<div id="deadlineDiv" style="display:none">
		<br>
		<div>
			<form id="addForm" name="addForm" class="form-horizontal" role="form" method="post">
				<div>
					<div><label for="deadlineName">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp请填写授信期限名称</label></div>
					<br>
					<div style="display:inline;width:100%">
						<div style="float:left;width:30%;text-align:right"><h4>最长&nbsp</h4></div>
						<div style="float:left;width:40%" class="clearfix"><input name="deadlineName" id="deadlineName" class="input form-control" /></div>
						<div style="float:left;width:30%"><h4>&nbsp个月</h4></div>
					</div>
				</div>
			</form>
		</div>
		<br><br><br><br>
		<div style="text-align:center">
			<button type="button" onclick="javascript:$('#addForm').submit();" style="width:100px" class="btn btn-primary btn-sm" >保存</button>
		</div>
	</div>
</div>


