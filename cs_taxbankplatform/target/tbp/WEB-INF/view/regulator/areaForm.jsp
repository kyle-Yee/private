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
<link href="${ctx }/resources/css/customer/count/loansdetails/styles.css" type="text/css" rel="stylesheet"/>--%>
<script type="text/javascript" src="${ctx }/resources/js/customer/regionalcascade/area.js">//引用外部文件area.js</script>

<div class="search-box">
	<form id="form" action="">
		<div class="col-md-2">
			<select class="form-control" name="province" id="province">
				<option disabled selected>省/自治区：</option> 
			</select>
		</div>
		<div class="col-md-2">
			<select class="form-control" name="city" id="city">
				<option disabled selected>市/州：</option> 
			</select>
		</div>
		<div class="col-md-2">
			<select class="form-control" name="area" id="area">
				<option disabled selected>区/县：</option> 
			</select>
		</div>
		<div class="col-md-6 mar-bottom-ten middle">
			<button type="button" class="button btn-blue" id="btnSearch">查&nbsp;询</button>
			<button type="reset" class="button btn-blue" id="btnReset">清&nbsp;空</button>
		</div>
	</form>
</div>
	<!-- 				
<div id="u3971_div" class="ax_default _形状" data-label="Rectangle">
	<div style = "height: 30px;"></div>
	<form id="form" action="">
		<table id="formTable">
			<tr height="40">
					<td class="tdFont">省&nbsp;&nbsp;:</td>
					<td id="u4262" class="ax_default _下拉列表框1"><select name="province" id="province" class="input"></select></td>
					<td class="tdFont">市/州&nbsp;&nbsp;:</td>
				 	<td id="u4263" class="ax_default _下拉列表框1"><select name="city" id="city" class="input"></select></td>
				 	<td class="tdFont">区/县&nbsp;&nbsp;:</td>
				  	<td id="u4264" class="ax_default _下拉列表框1"><select name="area" id="area" class="input"></select></td>
			</tr>

			<tr height="80">
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
</div> -->