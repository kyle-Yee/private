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
<link href="${ctx }/resources/css/customer/count/loansdetails/styles.css" type="text/css" rel="stylesheet"/>
<!-- <script type="text/javascript" src="${ctx}/resources/js/jquerycharts/highcharts.js"></script> -->
<script type="text/javascript" src="${ctx}/resources/js/customer/count/pandect/list.js"></script>
<div class="main-content-inner">
	<div class="">
		<div class="row" >
			<div class="col-xs-12 widget-container-col ui-sortable" >
				<div class="widget-box transparent ui-sortable-handle" >
					<div class="widget-header">
						<div class="form-group row">
							<div class="col-md-4"><H4>总览</H4>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="margin-top:-20px;;padding-top:0px;">
	<div id="detailslist" class="ax_default _表格1">
		<table id="table" class = "table" align="center" >
			<thead  id="title" class="ax_default _单元格1">
				<td id="dataCount" class="border" align="center" 
					 colspan=4  style="font-weight:bold">用户信息统计</td>
			</thead>
		</table>
	</div>
</div>
<div id="data">
	<div>
		<div id="container1" class="chart" style="margin-left:20px;padding-left:5px;"></div>
	</div>
	<div style = "height: 20px;"></div>
	<div>
		<div id="container2" class="chart" style="width: 1140px;height: 400px;margin-left:20px;padding-left:5px;"></div>
	</div>
	<div style = "height: 20px;"></div>
	<div>
		<div id="container3" class="chart" style="width: 1140px;height: 400px;margin-left:20px;padding-left:5px;"></div>
	</div>
	<div style = "height: 20px;"></div>
	<div>
		<div id="container4" class="chart" style="width: 1140px;height: 400px;margin-left:20px;padding-left:5px;"></div>
	</div>
</div>
<div id="nodata"></div>
