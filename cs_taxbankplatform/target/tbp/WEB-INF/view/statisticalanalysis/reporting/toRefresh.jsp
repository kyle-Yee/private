<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${ctx }/resources/js/customer/statisticalanalysis/reporting/toRefresh.js"></script>
<script type="text/javascript">
	$(function(){
		TOREFRESH.init();
	});
</script>

<style>
	
	#factLoan table{
		width: 100%;
		margin-top: 20px;
		border-width: 2px;
	    border-style: solid;
	    border-color: rgba(204, 204, 204, 1);
	    border-radius: 0px;
	    white-space: nowrap;
	}
	
	#factLoan table td{
		width:50%;
		height:40px;
		
		border-width: 1px;
	    border-style: solid;
	    border-color: rgba(204, 204, 204, 1);
	    border-radius: 0px;
	    white-space: nowrap;
	    
	    font-family: "微软雅黑";
	    font-style: normal;
	    font-weight: 400;
		text-align: center;
	}
	
	#factLoan .second{
		height:50px;
	    font-size: 13px;
	    font-style: normal;
	    font-weight:bold;
	    color: white;
	    text-align: center;
	    background-color: #00A2CA;
		/* 	    字间距 */
	    letter-spacing:2px;
	}
	
	#factLoan input{
		text-align: center;
		height: 40px;
		width: 100%;
		
	}
	
/* 	#factLoan table td:not(.second){ */
/* 		text-align: left; */
/* 	} */
	
/* 	#factLoan td:not(.second){ */
/* 		background-color: red; */
/* 	} */


</style>
<div class="main-content-inner">
	<div class="">
		<div class="row" >
			<div class="col-xs-12 widget-container-col ui-sortable" >
				<div class="widget-box transparent ui-sortable-handle" >
					<div class="widget-header">
						<div class="form-group row">
							<div class="col-md-4"><H4>银税互动资料更新</H4>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="data">
	<div style="text-align:center;margin-top:17px;"><span style="font-size:23px;">银税互动资料更新</span></div>
	
	<div id="factLoan">
		<form id="dataForm">
			<input type="hidden" id="dr_id" name="dr_id">
			<input type="hidden" id="hasUpdate" name="hasUpdate">
			<table id="table" align="center">
				<!-- 1 -->
				<tr>
					<td class="second"><span>最近一次更新时间</span></td>
					<td class="second"><span>不良贷款笔数</span></td>
				</tr>
				<tr >
					<td  ><span id="updatetime"></span></td>
					<td  ><input type="text" id="bad_num" name="bad_num"></td>
				</tr>
				<tr >
					<td class="second" ><span>不良贷款金额(万元)</span></td>
					<td class="second" ><span>不良率(%)</span></td>
				</tr>
				<!-- 2 -->
				<tr >
					<td ><input type="text" id="bad_sum" name="bad_sum"></td>
					<td  ><input type="text" id="bad_rate" name="bad_rate"></td>
				</tr>
				<tr  >
					<td class="second" ><span>全年计划银税互动授信总金额(万元)</span></td>
					<td class="second" ><span>全年计划银税互动小微企业授信总额(万元)</span></td>
				</tr>
				<tr  >
					<td ><input type="text" id="yearly_plan_sum" name="yearly_plan_sum"></td>
					<td ><input type="text" id="yearly_plan_micro_sum" name="yearly_plan_micro_sum"></td>
				</tr>
				
			</table>
		</form>
		<div style="text-align: center;"><button id="submitBtn" class="btn btn-primary btn-sm" style="width: 100px;margin: 20px;">更新</button></div>
		
	</div>
</div>

