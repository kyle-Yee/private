<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapprove/finallist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/regionalcascade/area.js">//引用外部文件area.js</script>
<!-- <div class="input-group">
     <input id="searchKey" type="text" class="input form-control" placeholder="输入产品名称...">
     <span class="input-group-btn">
         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
     </span>
</div> -->

<style type="text/css">
	.inputs{
	 
	 	height:30px;
   	width:180px;

	} 
/*    input {
   	height:30px;
   	width:180px;
   } */
	.layui-layer-title {
	   background-color: #00A2CA;
		height:30px;
		line-height:30px;
		font-size:14px;
		border-color : #bcbcbc;
	}
	.layui-layer-btn{
		text-align:center;
		height : 55px;
	}
	.layui-layer-btn .layui-layer-btn0{
	/* 	height : 24px;
		line-height:20px;
		width : 100px;
		color : #000;
		background-color : #e4e4e4;
		border-color : #000;
		border-radius : 0px; */

    background-color: #00A2CA;

    border-radius: 6.2px;
    box-shadow: none;
    color: #ffffff;
    height: 30px;

    width: 100px;
	}
	.layui-layer-btn .layui-layer-btn1{
	/* 	height : 24px;
		line-height:20px;
		width : 100px;
		color : #000;
		background-color : #e4e4e4;
		border-color : #000;
		border-radius : 0px; */
     background-color: #00A2CA;
    border-radius: 6.2px;
    box-shadow: none;
    color: #ffffff;
    height: 30px;

    width: 100px;
	}
	
	 .layui-layer-btn1:hover {
		background-color: #ffffff;;
		color:#00A2CA;
		 height: 30px;
         width: 100px;
         border-radius: 6.2px;
       box-shadow: none;
	   }
	    .layui-layer-btn0:hover {
		background-color: #ffffff;;
		color:#00A2CA;
		 height: 30px;
         width: 100px;
         border-radius: 6.2px;
       box-shadow: none;
	   }
	.layui-layer{
		border-radius : 0px;
		border : 1px solid #E4E4E4;
	} 
	.btnCLear{
	/* 	height : 24px;
		line-height:20px;
		width : 100px;
		color : #000;
		background-color : #e4e4e4;
		border-color : #000;
		border-radius : 0px; */
     background-color: #00A2CA;
    border-radius: 6.2px;
    box-shadow: none;
    color: #ffffff;
    height: 35px;

    width: 120px;
       
	 }
	  
	.btnSearch{
	/* 	height : 24px;
		line-height:20px;
		width : 100px;
		color : #000;
		background-color : #e4e4e4;
		border-color : #000;
		border-radius : 0px; */
     background-color: #00A2CA;
    border-radius: 6.2px;
    box-shadow: none;
    color: #ffffff;
    height: 35px;

    width: 120px;
	}
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
</style>
<div class="input-group">
	<div  style="width:920px;border-color: rgba(204, 204, 204, 1);
    border-radius: 0;
    border-style: solid;
    border-width: 1px;
    box-shadow: none; 
    margin-left: 100px;" >
		<table  class="table"  style="margin-bottom: 0px;">
		  <tr height="60">
				<td >省:<select name="province" id="province" class="inputs"></select></td>
			 	<td> 市:<select name="city" id="city" class="inputs"></select></td>
			  	<td >区/县:<select name="area" id="area" class="inputs"></select></td>
			</tr>
			<tr height="50">
				<td colspan="3" style="text-align:center;">授信开始时间：<input id="star" type="text" class="inputs"/><img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;"><span style = "padding-left: 20px;">&nbsp;至&nbsp;</span><input id="end" type="text" class="inputs"/><img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;"></td>	
			</tr>
				<tr height="50">
				<td colspan="3" style="text-align:center;">企业名称：<input id="nsrmc" type="text" class="inputs"></td>		
			</tr>
			<tr height="50">
			    <td  style="text-align:right ;" >
			         <button id="btnCLear" class="btnCLear" onclick="btnCLear()"  type="button"> <i ></i> 清空</button>
				</td>
				<td></td>
			   <td style="text-align:left;" >
			         <button id="btnSearch" class="btnSearch" type="button"> <i></i> 搜索</button>
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			
			<div class="widget-header">
				<h4 class="widget-title lighter">贷后业务管理</h4>
				<div class="widget-toolbar no-border">
					<!-- <a href="#" data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a href="#" data-action="collapse" class="green"><i class="ace-icon fa fa-chevron-up"></i></a> -->
					<a data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a data-action="collapse" class="green"><i class="ace-icon fa fa-chevron-up"></i></a>
				</div>
			</div>

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
				<%-- 	<input id="status" type="hidden" value="${status.status }"> --%>
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
	$("#star, #end").datetimepicker({
		lang : "ch",
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
	});
});
</script>
