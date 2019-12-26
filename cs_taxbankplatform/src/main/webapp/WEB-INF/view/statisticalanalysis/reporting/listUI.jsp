<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
 
<%-- <link href="${ctx }/resources/css/customer/count/default.css" type="text/css" rel="stylesheet"/>
<link href="${ctx }/resources/css/customer/count/styles.css" type="text/css" rel="stylesheet"/>
<link href="${ctx }/resources/css/customer/count/loansdetails/styles.css" type="text/css" rel="stylesheet"/> --%>
<link href="${ctx }/resources/css/style/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx }/resources/js/customer/regionalcascade/area.js">//引用外部文件area.js</script>
<script type="text/javascript" src="${ctx }/resources/js/customer/statisticalanalysis/reporting/list.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/customer/statisticalanalysis/reporting/tableToExcel.js"></script>
<!-- <script type="text/javascript" src="${ctx }/resources/js/customer/statisticalanalysis/reporting/copyHtmlToExcel.js"></script>
 -->
<script type="text/javascript">
	/**
	 * 加入时间插件
	 */
	$(function(){
		//查询起止时间
		$("#starttime,#endtime").datetimepicker({
			lang : "ch",
			timepicker : false,
			format : "Y-m-d",
			formatDate : "Y-m-d",
		});
	});
			
</script>
<script type="text/javascript">
	
	function onTableToExcel() {
		/* if(window.navigator.userAgent.indexOf('360')==-1){
			layer.msg("请选择360极速模式！", {icon : 0});
		} */
		if (getExplorer() == 'ie' || getExplorer() == 'other') {
			saveAsExcel($("#table").attr('id'));
		} else {
			var tableToExcel = (function() {
	        var uri = 'data:application/vnd.ms-excel;base64,',
	        template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
	          base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
	          format = function(s, c) {
	              return s.replace(/{(\w+)}/g,
	              function(m, p) { return c[p]; }) }
	
	          return function(table, name) {
	          if (!table.nodeType) table = document.getElementById(table)
	          var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
// 	          window.location.href = uri + base64(format(template, ctx))
	          var a = $("<a></a>").attr("href", uri + base64(format(template, ctx))).attr("download", "银税互动服务平台统计分析报表.xls").appendTo("body");
			  a[0].click();
			  a.remove();
	        }
	      })()
	   	
	     tableToExcel('table','银税互动服务平台统计分析报表')
		}
	}
</script>

	<div class="title">“银税互动”推进情况</div>
	
	<div class="search-box">
		<form id="form" action="">
			<div class="col-md-4">
				<select class="form-control" name="province" id="province">
					<option disabled selected>省/自治区：</option> 
				</select>
			</div>
			<div class="col-md-4">
				<select class="form-control" name="city" id="city">
					<option disabled selected>市/州：</option> 
				</select>
			</div>
			<div class="col-md-4">
				<select class="form-control" name="area" id="area">
					<option disabled selected>区/县：</option> 
				</select>
			</div>
			<div class="col-md-4">
				<div class="ipt-data" id="search_time1">
					<input id="starttime" name="starttime" type="text" class="form-control dateImg"  placeholder="时间起">
				</div>
			</div>
			<div class="col-md-4">
				<div class="ipt-data" id="search_time2">
					<input id="endtime" name="endtime" type="text" class="form-control dateImg" placeholder="时间止">
				</div>
			</div>
			<div class="col-md-12 mar-bottom-ten text-center">
				<button type="button" class="button btn-blue" id="btnSearch">查&nbsp;询</button>
				<button type="button" class="button btn-blue" id="btnExport" onclick="javascript:exportData();">导&nbsp;出</button>
				<button type="reset" class="button btn-blue" id="btnReset">清&nbsp;空</button>
			</div>
		</form>
		<form action="${ctx }/reporting/downloadExport.html" target="_blank" style="display:none" method="post" id="exportForm">
			<input type="hidden" name="exportData" id="exportData">
		</form>
	</div>

<div class="row" id="dataALL">
	<h3 class="mar-bottom-thirty text-center">银税互动服务平台统计分析报表</h3>
	<div class="box">
		<!-- <table id="table" class="table table-bordered text-center">
			<thead>
				<tr>
					<td rowspan="2" class="col-xs-4"><strong>税务机关名称</strong></td>
					<td colspan="3" class="col-xs-4"><strong>平台用户</strong></td>
					<td colspan="2" class="col-xs-4"><strong>贷款情况</strong></td>
				</tr>
				<tr>
					<td class="col-xq-11">注册用户</td>
					<td class="col-xq-11">占企业总数比例</td>
					<td class="col-xq-11">认证通过数</td>
					<td class="col-xs-2">授信笔数</td>
					<td class="col-xs-2">授信总额（万元）</td>
				</tr>
			</thead>
			<tfoot>
				<tr class="font-blod">
					<td>合计</td>
					<td id="zcyh"></td>
					<td id="zqyzsbl"></td>
					<td id="rztgs"></td>
					<td id="sxbs"></td>
					<td id="sxze"></td>
				</tr>
			</tfoot>
		</table> -->
<!-- 增加分页	 -->	
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
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
<div id="nodata"></div>
<!-- <div class="box">
		<table id="tableCal" class="table table-bordered text-center">
		<tfoot>
				<tr class="font-blod">
					<td>合计</td>
					<td id="zcyh"></td>
					<td id="zqyzsbl"></td>
					<td id="rztgs"></td>
					<td id="sxbs"></td>
					<td id="sxze"></td>
				</tr>
			</tfoot>
		</table>
</div> -->

		
</div>
	
	
</div>

 	<div class="alert alert-info hide" id="dataNo"></div>
	<!-- 加载效果 start -->
	<div class="loader" id="loader">
		<div class="loader-backdrop loader-fade"></div>
        <div class="loader-inner ball-spin-fade-loader">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
	<!-- 加载效果 end -->