<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapplyquery/list.js"></script>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">贷款申请查询</h4>
				<div class="widget-toolbar no-border">
					<a href="#" data-action="fullscreen" class="orange2"> <i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a href="#" data-action="collapse" class="green"> <i class="ace-icon fa fa-chevron-up"></i></a>
				</div>
			</div><br/>
			
			<div class="input-group">
				<table>
					<tr height="40">
						<td>产品名称：</td>
						<td><input id="apply_search_fpname" type="text" class="input form-control"></td>
						<td width="200"></td>
						<td>申请人：</td>
						<td><input id="apply_search_applicant" type="text" class="input form-control"></td>
					</tr>
					<tr height="40">
						<td>申请时间：</td>
						<td><input id="apply_search_applytime1" type="text" class="input"><span>&nbsp;--&nbsp;</span><input id="apply_search_applytime2" type="text" class="input"></td>
						<td width="200"></td>
						<td>初审时间：</td>
						<td><input id="apply_search_firsttime1" type="text" class="input"><span>&nbsp;--&nbsp;</span><input id="apply_search_firsttime2" type="text" class="input"></td>
					</tr>
					<tr height="40">
						<td>终审时间：</td>
						<td><input id="apply_search_endtime1" type="text" class="input"><span>&nbsp;--&nbsp;</span><input id="apply_search_endtime2" type="text" class="input"></td>
						<td width="200"></td>
						<td colspan="2" align="right"> <span class="input-group-btn"><button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button></span></td>
					</tr>
				</table>
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


