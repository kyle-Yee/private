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
<script type="text/javascript" src="${ctx }/resources/js/customer/syptzhbgqk/list.js"></script>

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
<div class="main-content-inner">
	<div class="">
		<div class="row" >
			<div class="col-xs-12 widget-container-col ui-sortable" >
				<div class="widget-box transparent ui-sortable-handle" >
					<div class="widget-header">
						<div class="form-group row">
							<div class="col-md-4"><H4> 银税互动账号报告情况查询</H4>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="u3971_div" class="" >
	<div style = "height: 10px;"></div>
	<form id="form" action="">
		<table id="formTable">
			<tr height="40">
				<td class="tdFont">银行&nbsp;&nbsp;:</td>
				<td id="u3952" class="ax_default _下拉列表框1 col-md-4">
					<select id="bankId" name="bankId" class="form-control" onchange="bankChange();">
					  <option value="">请选择银行...</option>
						<c:forEach items="${bankList}" var="perbank">
						<option value="${perbank.yhdm}" codeVal="${perbank.yhdm}">${perbank.yhmc}</option>
					  </c:forEach>
					</select>
					<input type="hidden" name="bankName" id="bankName">
				</td>
				<td class="tdFont">产品&nbsp;&nbsp;:</td>
			 	<td id="u3953" class="ax_default _下拉列表框1 col-md-4">	
			 		<select id="fpId" name="fpId" class="form-control">
					<option value="">请选择产品...</option>
					</select>
					<input type="hidden" name="fpName" id="fpName">
				</td>
			 	<td class="tdFont">登记注册类型&nbsp;&nbsp;:</td>
			  	<td id="u3952" class="ax_default _下拉列表框1 col-md-4">
					<select id="djclxDm" name="djclxDm" class="form-control"  >
					  <option value="">请选择</option>
						<c:forEach items="${djzclxdmList}" var="djzclxdm">
						<option value="${djzclxdm.djclx_dm}">${djzclxdm.djzclxmc}</option>
					  </c:forEach>
					</select>
				</td>
			</tr>
			
			<tr height="40">
				<td class="tdFont">贷款申请时间开始&nbsp;&nbsp;:</td>
				<td id="u3955" class="ax_default _文本框" data-label="Start Date Field"   >
					<span id="search_time1">
						<input id="starttime" name="starttime" type="text" class="input" style="width:92%;">
						<img  src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
					</span> 
				</td>
				<td class="tdFont">贷款申请时间结束&nbsp;&nbsp;:</td>
				<td id="u3955" class="ax_default _文本框  " data-label="Start Date Field"  >
					 
					<span id="search_time2">
						<input id="endtime" name="endtime" type="text" class="input" style="width:92%;">
						<img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
					</span>
				</td>
				<td class="tdFont">行业&nbsp;&nbsp;:</td>
				<td id="u4266" class="ax_default _下拉列表框1 col-md-4">
					<select name="hydm" id="hydm" >
						<option value="">请选择</option>
						<c:forEach items="${hydmList}" var="hydm">
							<option value="${hydm.hydm }">${hydm.hymc }</option>
						</c:forEach>	
					</select>
				</td>	
			</tr>
			
			<tr height="40">
				<td class="tdFont">企业名称&nbsp;&nbsp;:</td>
				 <td id="u3956" class="ax_default _文本框" >
					<input id="qymc" name="qymc" type="text" class="input" style="width:92%;"/>
				</td>
				<td class="tdFont">社会信用代码&nbsp;&nbsp;:</td>
				<td id="u3956" class="ax_default _文本框" >
					<input id="nsrsbh" name="nsrsbh" type="text" class="input" style="width:92%;"/>
				</td>	
			 
			</tr>
			 
			<tr height="60">
				<div  id="u4270" class="ax_default _形状" >
				<td colspan="2" align="right"> 
						<div class="buttonStyle" >
							<button id="btnSearch"  type="button"> <i class="fa fa-search"></i> 查询</button>						
						</div>
					</td>
					<td  colspan="2"  align="right"> 
						<div class="buttonStyle" >
							<button id="btnReset"  type="reset"> <i class=""></i>清空</button>							
						</div>
					</td>
					
				</div>
			</tr>
		</table>
	</form>
</div>
<div style = "height: 30px;"></div>
<!-- <div id="data">
	<div id="detailslist" class="" >
		<table id="table" class = "table" align="center" >
			<thead id="title" class="" >
				<td class="border" align="center" class="tabletext"><span class="tabletext" >序号</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >企业名称</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >申贷时间</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >办理行</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >申贷金额_(万元)</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >审批状态</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >授信时间</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >授信金额_(万元)</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >批贷利率</span></td>
				<td class="border" align="center" class="tabletext"><span class="tabletext" >报告</span></td>
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