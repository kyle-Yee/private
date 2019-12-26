<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/kanbansystemlist/list.js">
	</script>
	<style type="text/css">

.dlshouwen-grid-headers th{
	   background-color: rgba(174, 133, 53, 1);
	}
	
    .dlshouwen-grid-header hidden-xs dlshouwen-grid-header can-sort{background-color:red;color:#fff}
   .text-primary{
      color:rgba(174, 133, 53, 1);
   }
</style>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>

<div class="page-header">
	<div>
		<button id="btnAdd" type="button"  class="btn btn-primary btn-sm">
		  	<i class="fa fa-user-plus"></i>&nbsp;添加
		</button>
		<button id="btnEdit" type="button" class="btn btn-success btn-sm">
			 <i class="fa fa-pencil-square-o"></i>&nbsp;编辑
		</button>
		<button id="btnEdit" type="button" class="btn btn-danger btn-sm">
			 <i class="fa fa-trash-o"></i>&nbsp;删除
		</button>
	</div>
</div>
<div class="index-content-middle">
<!-- 首页统计数据 -->
	<div class="content-count">
		<input type="hidden" id="switchTime" name="switchTime" value="${countEntity.switchTime }">
		<div class="content-count-div">
			<table  class="table">
				<tr class="content-count-divtr">
					<td class="content-count-td"><span class="content-count-fontstyle">${countEntity.loanTotal }</span><br/>全部</td>
					<td class="content-count-td"><span class="content-count-fontstyle">${countEntity.loanTotal }</span><br/>待受理</td>
					<td class="content-count-td"><span class="content-count-fontstyle">${countEntity.loanTotal }</span><br/>已授信</td>
					<td class="content-count-td"><span class="content-count-fontstyle">${countEntity.loanTotal }</span><br/>待授信</td>
					<td class="content-count-td"><span class="content-count-fontstyle">${countEntity.loanTotal }</span><br/>未授信</td>
					<td class="content-count-td"><span class="content-count-fontstyle">${countEntity.loanTotal }</span><br/>未受理</td>
					<td class="content-count-td"><span class="content-count-fontstyle">${countEntity.loanTotal }</span><br/>代办事项</td>
				</tr>
			</table>
		</div>
	</div>
</div>


<div class="input-group">
	<div class="content-count-div">
		<table  class="table">
			<tr class="content-count-divtr">
				<td>申请时间：<input id="apply_search_firsttime1" type="text"><span>&nbsp;至&nbsp;</span><input id="apply_search_firsttime2" type="text"></td>
				<td class="content-count-td">企业名称：<input id="1" type="text"></td>
				<td class="content-count-td">
				<div class="form-group">
				<label class="control-label col-sm-1" for="enabled"></label>
				<div class="col-sm-10">
				<div class="clearfix"><span>状态:</span>
					<select id="taxIs"  name="taxIs">
						<option value="1" >全部</option>
						<option value="2" >待受理</option>
						<option value="3" >带授信</option>
						<option value="4" >已授信</option>
						<option value="5" >未获得授信</option>
						<option value="6" >未受理</option>
					</select>
				</div>
				</div>
			</div>
				</td>
				<td class="content-count-td">
			         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
				</td>
			</tr>
		</table>
	</div>
</div>


<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			
			<div class="widget-header">
				<h4 class="widget-title lighter">待审批列表</h4>
				<div class="widget-toolbar no-border">
					<!-- <a href="#" data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a href="#" data-action="collapse" class="green"><i class="ace-icon fa fa-chevron-up"></i></a> -->
					<a  data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a data-action="collapse" class="green"><i class="ace-icon fa fa-chevron-up"></i></a>
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


<script type="text/javascript">
//加入时间插件
$(function(){
	//(申请/初审/终审)时间
	$("#apply_search_firsttime1, #apply_search_firsttime2").datetimepicker({
		lang : "ch",
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
	});
});
</script>