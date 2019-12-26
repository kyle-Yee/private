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
<script type="text/javascript" src="${ctx }/resources/js/customer/regionalcascade/area.js">//引用外部文件area.js</script>
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
							<div class="col-md-4"><H4>业务明细查询</H4>
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
				<td class="tdFont">省&nbsp;&nbsp;:</td>
				<td id="u3952" class="ax_default _下拉列表框1"><select name="province" id="province" ></select></td>
				<td class="tdFont">市/州&nbsp;&nbsp;:</td>
			 	<td id="u3953" class="ax_default _下拉列表框1"><select name="city" id="city" ></select></td>
			 	<td class="tdFont">区/县&nbsp;&nbsp;:</td>
			  	<td id="u3954" class="ax_default _下拉列表框1"><select name="area" id="area" ></select></td>
			</tr>
			<tr height="40">
				<td class="tdFont">查询时间&nbsp;&nbsp;:</td>
				<td id="u3955" class="ax_default _文本框" data-label="Start Date Field" colspan=3 >
					<span id="search_time1">
						<input id="starttime" name="starttime" type="text" class="input">
						<img  src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
					</span>
					<span style="margin-left:95px;margin-right:20px;"> 
					至</span>
					<span id="search_time2">
						<input id="endtime" name="endtime" type="text" class="input">
						<img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
					</span>
				</td>
				<td class="tdFont">信贷产品&nbsp;&nbsp;:</td>
				<td id="u4266" class="ax_default _下拉列表框1">
					<select name="loanproduct" id="loanproduct" >
						<option value="">请选择</option>
						<c:forEach items="${loanproduct}" var="loanproduct">
							<option value="${loanproduct.fpName }">${loanproduct.fpName }</option>
						</c:forEach>	
					</select>
				</td>	
			</tr>
			<tr height="40">
				<td class="tdFont">分行名称&nbsp;&nbsp;:</td>
				<td id="u3956" class="ax_default _文本框" >
					<input id="subbranch" name="subbranch" type="text" class="input"/>
				</td>
			</tr>
			<tr height="60">
				<div  id="u4270" class="ax_default _形状" >
					<td  colspan="2"  align="right"> 
						<div class="buttonStyle" >
							<button id="btnReset"  type="button"> <i class=""></i>清空</button>							
						</div>
					</td>
					<td colspan="2" align="right"> 
						<div class="buttonStyle" >
							<button id="btnSearch"  type="button"> <i class="fa fa-search"></i> 查询</button>						
						</div>
					</td>
				</div>
			</tr>
		</table>
	</form>
</div>