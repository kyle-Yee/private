<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/test/list.js"></script>
<style type="text/css">
	.dlshouwen-grid-headers th{
	   background-color: #00A2CA;
	}
	
    .dlshouwen-grid-header hidden-xs dlshouwen-grid-header can-sort{background-color:red;color:#fff}
   .text-primary{
      color:#00A2CA;
   }
    .pagination > li.active > a, .pagination > li.active > a:hover, .pagination > li.active > a:focus{
     background-color: #00A2CA;
   }
   input {
   	height:30px;
   	width:180px;
   }
</style>

<div class="row" style = "padding-left: 300px;">
	<div class="row center-block" >
		<div ><span>查询时间&nbsp;&nbsp;:&nbsp;</span><input id="apply_star" type="text"><img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
		<span style = "padding-left: 30px;">&nbsp;&nbsp;至&nbsp;&nbsp;</span><span style = "padding-left: 25px;"></span><input id="apply_end" type="text"><img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
		</div>
	</div>
	<div style="width: 500px;height:20px;"></div>
	<div class="row center-block" style="border-top-width: 700px;">
		<div class="pull-left"><span>企业名称&nbsp;&nbsp;:&nbsp;</span><input id="nsrmc" type="text">
			<span style = "padding-left: 30px;">状态&nbsp;&nbsp;:&nbsp;</span>
			<select id="status"  name="status" style = "height:30px;width:180px;">
				<!-- <option value="8" ></option> -->
				<option value="0" >全部</option>
				<!-- 2017/4/6更具新需求 修改银行端显示状态 -->
				<!-- <option value="1" >待受理</option>
				<option value="2" >待授信</option>
				<option value="3" >已授信</option>
				<option value="4" >未授信</option>
				<option value="5" >未受理</option>
				<option value="6" >受理不通过</option>
				<option value="7" >授信完成</option> -->
				
				<option value="DKZT01" >待初审</option>
				<option value="DKZT02" >初审已通过</option>
				<option value="DKZT03" >终审已通过</option>
				<option value="DKZT04" >终审未通过</option>
				<option value="DKZT05" >未受理</option>
				<option value="DKZT06" >初审未通过</option>
				<option value="DKZT07" >授信完成</option>
				<option value="DKZT08" >待批准撤销</option>
				<option value="DKZT09" >已撤销</option>
				<option value="DKZT10" >已退单</option>
				
			</select>
		</div>
	</div>
	<div style="border-top-width: 500px;height:20px;"></div>
	<div class="row center-block">
		<div class="pull-left" style = "padding-left: 70px;">
			<button id="btnEmpty" style = "margin-left: -25px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="clearData()">清理
			</button>
			
			<button id="btnSearch" style = "margin-left: 50px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="customSearch('')">搜索
			</button>
			<form action="${ctx }/loanapprove/downloadExport.html" target="_blank" style="display:none" method="post" id="exportForm">
				<input type="hidden" name="A_apply_star" id="A_apply_star">
				<input type="hidden" name="A_apply_end" id="A_apply_end">
				<input type="hidden" name="A_nsrmc" id="A_nsrmc">
				<input type="hidden" name="A_status" id="A_status">
			</form>
		</div>
	</div>
</div>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			
			<div class="widget-header">
				<h4 class="widget-title lighter"><!-- 待审批列表 --></h4>
				<div class="widget-toolbar no-border">
					<!-- <a href="#" data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a href="#" data-action="collapse" class="green"><i class="ace-icon fa fa-chevron-up"></i></a> -->
					<a href="#" data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a href="#" data-action="collapse" class="green"><i class="ace-icon fa fa-chevron-up"></i></a>
				</div>
			</div>

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<input id="status" type="hidden" value="${status.status }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
			
		</div>
	</div>	
</div>
<div style = "height: 30px;"></div>