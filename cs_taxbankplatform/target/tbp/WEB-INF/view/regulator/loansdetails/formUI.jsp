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
<script type="text/javascript" src="${ctx }/resources/js/customer/regulator/loansdetails/form.js"></script>

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
	<div class="title">贷款业务明细查询</div>

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
			<div class="col-md-4">
				<select class="form-control" name="industries" id="industries">
					<option disabled selected>全行业：</option> 
					<c:forEach items="${searchIndustries}" var="industries">
						<option value="${industries.hydm}">${industries.hymc}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-4">
				<div class="ipt-data">
					<input id="enterprisename" name="enterprisename" type="text" class="form-control"  placeholder="企业名称：">
				</div>
			</div>
			<div class="col-md-4">
				<div class="ipt-data">
					<input id="corporate" name="corporate" type="text" class="form-control"  placeholder="银行：">
				</div>
			</div>
			<div class="col-md-4">
				<select class="form-control" name="approve" id="approve">
					<option disabled selected>审批状态：</option> 
					<c:forEach items="${hpzt}" var="hpzt">
						<option value="${hpzt}">${hpzt}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-12 mar-bottom-ten text-center">
				<button type="button" class="button btn-blue"  id="btnSearch">查&nbsp;询</button>
				<button type="reset" class="button btn-blue"  id="btnReset">清&nbsp;空</button>
			</div>
		</form>
	</div>
	
	<div class="row" id = "dataALL">
		<div class="col-md-6 mar-bottom-twenty">
			<div class="border-gray box-count-three font-green">
				<img class="mar-top-ten" src="${ctx }/resources/images/qy-four.png" />
				<div class="mar-top-fifteen">申贷总金额（万元）</div>
				<div class="font-eighteen mar-top-ten" id="sdje">0.0</div>
			</div>
		</div>
		<div class="col-md-6 mar-bottom-twenty">
			<div class="border-gray box-count-three font-purple">
				<img class="mar-top-ten" src="${ctx }/resources/images/qy-six.png" />
				<div class="mar-top-fifteen">授信总金额（万元）</div>
				<div class="font-eighteen mar-top-ten" id="sxje">0.00</div>
			</div>
		</div>
		<div class="box">
			<!-- <table id="table" class="table table-bordered text-center mar-top-ten" >
				<thead>
					<td>序号</td>
					<td>企业名称</td>
					<td>申贷时间</td>
					<td>申贷银行</td>
					<td>申贷金额（万元）</td>
					<td>获批状态</td>
					<td>授信时间</td>
					<td>授信金额（万元）</td>
					<td>授信利率</td>
				</thead>
				<tfoot>
					<tr class="font-blod">
						<td colspan="4"><strong>合计</strong></td>
						<td id="tsdje"></td>
						<td></td>
						<td></td>
						<td id="tsxje"></td>
						<td></td>
					</tr>
				</tfoot>
			</table> -->
		</div>
	</div>
<!-- 增加分页 -->
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
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