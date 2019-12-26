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
<script type="text/javascript" src="${ctx }/resources/js/customer/regulator/enterprisestatistics/form.js"></script>

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

	<div class="title">注册认证明细</div>

	<div class="search-box">
		<form id="form" action="">
			<div class="col-md-3">
				<select class="form-control" name="province" id="province">
					<option disabled selected>省/自治区：</option> 
				</select>
			</div>
			<div class="col-md-3">
				<select class="form-control" name="city" id="city">
					<option disabled selected>市/州：</option> 
				</select>
			</div>
			<div class="col-md-3">
				<select class="form-control" name="area" id="area">
					<option disabled selected>区/县：</option> 
				</select>
			</div>
			<div class="col-md-3">
				<div class="ipt-data">
					<input id="enterprisename" name="enterprisename" type="text" class="form-control"  placeholder="企业名称：">
				</div>
			</div>
			<div class="col-md-3">
				<div class="ipt-data" id="search_time1">
					<input id="starttime" name="starttime" type="text" class="form-control dateImg"  placeholder="时间起">
				</div>
			</div>
			<div class="col-md-3">
				<div class="ipt-data" id="search_time2">
					<input id="endtime" name="endtime" type="text" class="form-control dateImg" placeholder="时间止">
				</div>
			</div>
			<div class="col-md-3">
				<div class="ipt-data">
					<input id="corporate" name="corporate" type="text" class="form-control"  placeholder="法人姓名：">
				</div>
			</div>
			<div class="col-md-3">
				<div class="ipt-data">
					<input id="phonenumber" name="phonenumber" type="text" class="form-control"  placeholder="手机号：" maxlength="11">
				</div>
			</div>
			<div class="col-md-12 mar-bottom-ten text-center">
				<button type="button" class="button btn-blue"  id="btnSearch">查&nbsp;询</button>
				<button type="reset" class="button btn-blue"  id="btnReset">清&nbsp;空</button>
			</div>
		</form>
	</div>
	
	<!-- <div class="row" id = "dataALL"> 
		<div class="box">
				<table id="table" class="table table-bordered text-center mar-top-ten" >
				<thead>
					<td>序号</td>
					<td>注册时间</td>
					<td>企业名称</td>
					<td>法人姓名</td>
					<td>法人手机号</td>
					<td>身份认证</td>
					<td>原因</td>
				</thead>
			</table>
		</div>
	</div> -->
	<!-- 增加分页 -->
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
<!-- 加载效果 start  -->
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