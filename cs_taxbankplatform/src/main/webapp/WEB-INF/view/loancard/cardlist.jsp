<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapprove/cardlist.js"></script>
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
<!-- 首页统计数据 -->
<div class="content-count">
	<div class="content-count-div">
		<table class="table" >
			<tr class="content-count-divtr">
				<td class="content-count-td text-center" style="background-color: #00A2CA;width:20;heigth:20;" onclick="customSearch(0)">${totalDataEntity.total}<br>全部</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch(2)">${totalDataEntity.dsx}<br>初审已通过</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch(3)">${totalDataEntity.ysx}<br>终审已通过</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch(4)">${totalDataEntity.whdsx}<br>终审未通过</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch(6)">${totalDataEntity.slbtg}<br>初审未通过</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch(7)">${totalDataEntity.sxwc}<br>授信完成</td>
			</tr>
		</table>
	</div>
</div>

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
				<option value="0" >全部</option>
				
<!-- 				<option value="1" >待初审</option> -->
				<option value="2" >初审已通过</option>
				<option value="3" >终审已通过</option>
				<option value="4" >终审未通过</option>
<!-- 				<option value="5" >未受理</option> -->
				<option value="6" >初审未通过</option>
				<option value="7" >授信完成</option>
<!-- 				<option value="8" >待批准撤销</option> -->
<!-- 				<option value="9" >已批准撤销</option> -->
<!-- 				<option value="10" >已退单</option> -->
				
			</select>
		</div>
	</div>
	<div style="border-top-width: 500px;height:20px;"></div>
	<div class="row center-block">
		<div class="pull-left" style = "padding-left: 70px;">
			<%--<button id="btnEmpty" style = "margin-left: -25px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="clearData()">清理
			</button>
			<button id="btnExport" style = "margin-left: 50px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="exportData()">导出
			</button>
			<button id="btnSearch" style = "margin-left: 50px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="customSearch('')">搜索
			</button> --%>
			<button id="btnEmpty" style = "margin-left: 50px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="clearData()">清理
			</button>
			<button id="btnSearch" style = "margin-left: 100px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="customSearch('')">搜索
			</button>
			<%-- <form action="${ctx }/loanapprove/downloadExport.html" target="_blank" style="display:none" method="post" id="exportForm">
				<input type="hidden" name="A_apply_star" id="A_apply_star">
				<input type="hidden" name="A_apply_end" id="A_apply_end">
				<input type="hidden" name="A_nsrmc" id="A_nsrmc">
				<input type="hidden" name="A_status" id="A_status">
			</form> --%>
		</div>
	</div>
</div>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			
			<div class="widget-header">
				<h4 class="widget-title lighter"><!-- 待审批列表 --></h4>
				<div class="widget-toolbar no-border">
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
					<%-- <input id="status" type="hidden" value="${status.status }"> --%>
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
			
		</div>
	</div>	
</div>