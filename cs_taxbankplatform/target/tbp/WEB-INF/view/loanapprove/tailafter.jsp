<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx }/resources/images/webside.ico" rel="icon"/>
<link href="${ctx }/resources/images/webside.ico" type="image/x-icon" rel="bookmark"/>   
<link href="${ctx }/resources/images/webside.ico" type="image/x-icon" rel="shortcut icon"/>  

<link rel="stylesheet" href="${ctx}/resources/js/bootstrap/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctx}/resources/fonts/fontawesome/font-awesome.min.css" media="all"/>
<link rel="stylesheet" href="${ctx}/resources/fonts/opensans/ace-fonts.min.css"/>
<link rel="stylesheet" href="${ctx}/resources/css/ace/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>
<link rel="stylesheet" href="${ctx}/resources/css/customer/webside.min.css"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	if ('ontouchstart' in document.documentElement)document.write("<script src='${ctx}/resources/js/jquery/jquery.mobile.custom.min.js'>" + "<"+"script>");
</script>
<script src="${ctx}/resources/js/jqueryui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jqueryui/jquery.ui.touch-punch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/js/layer-v2.3/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
<script type="text/javascript" src="${ctx}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-validation/localization/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/nicescroll/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/customer/index/index.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/jquery/jquery.datetimepicker.css">
<style type="text/css">

.dlshouwen-grid-headers th{
	   background-color: #00A2CA;
	}
	
    .dlshouwen-grid-header hidden-xs dlshouwen-grid-header can-sort{background-color:red;color:#fff}
   .text-primary{
      color:#00A2CA;
   }
</style>
<script type="text/javascript" type="text/javascript">
var sys = sys || {};
sys.rootPath = "${ctx}";
sys.pageNum = 10;
sys.gridStyle = "Bootstrap";

</script> 

<%    
	response.setHeader("Cache-Control","no-cache");   
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader ("Expires", 0);    
%>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
<!-- 			<div class="widget-header"> -->
<!-- 				<h4 class="widget-title lighter">跟踪列表</h4> -->
<!-- 			</div> -->

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="findid" type="hidden" value="${id}">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container" style="margin-top: -16px;"></div>
<!-- 					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div> -->
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/customer/loanapprove/tailafter.js"></script>

