<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<link href="${ctx }/resources/css/customer/count/default.css" type="text/css" rel="stylesheet"/>
<link href="${ctx }/resources/css/customer/count/styles.css" type="text/css" rel="stylesheet"/>
<!-- <script type="text/javascript" src="${ctx}/resources/js/jquerycharts/highcharts.js"></script> -->
<%-- <link href="${ctx }/resources/js/customer/bankManager/bankList.css" type="text/css" rel="stylesheet"/> --%>
<script type="text/javascript" src="${ctx }/resources/js/customer/bankManager/bankList.js"></script>

<div class="page-header">
	<button id="btnAdd" type="button" onclick="webside.common.addModel('/bankManager/addBankConf.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;添加
	</button>
	<button id="btnApprove" type="button" onclick="updateStatus(1);" class="btn btn-success btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;开通
	</button>
	<button id="btnEdut" type="button" onclick="editBankCfg()" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;修改
	</button>
	<button id="btnDisable" type="button" onclick="updateStatus(2)" class="btn btn-success btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;停用
	</button>
</div>
<div class="input-group">
     <input id="searchKey" type="text" class="input form-control" placeholder="金融产品名称或者产品ID">
     <span class="input-group-btn">
         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
     </span>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h3 class="widget-title lighter">银行管理服务</h3>
				<div class="widget-toolbar no-border">
					<a href="#" data-action="fullscreen" class="orange2"> 
						<i class="ace-icon fa fa-arrows-alt"></i>
					</a> 
					<a href="#" data-action="collapse" class="green"> 
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize}">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>