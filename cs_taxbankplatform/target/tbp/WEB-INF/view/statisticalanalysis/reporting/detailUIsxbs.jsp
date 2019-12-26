<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<!-- <script type="text/javascript" src="${ctx }/resources/js/customer/statisticalanalysis/reporting/copyHtmlToExcel.js"></script>
 -->
 <script type="text/javascript">
 	function returnReport(){
 		webside.common.loadPage('/reporting/listUI.html');
 	}
 
 </script>
 

<div class="title">“银税互动”推进情况</div>
	<h4 class="mar-top-twenty"><a class="mar-right-fifteen" href="javascript:void(0);" onClick="returnReport();"><img alt="" src="${ctx }/resources/images/returnReport.png"></a>统计分析&nbsp;&nbsp;&nbsp;&nbsp;“银税互动”推进情况统计表</h4>
	<div class="box" >
		<table class="table table-bordered text-center">>
			<thead>
				<tr>
				<td>序号</td>
				<td colspan="2">企业名称</td>
				<td colspan="2">申贷时间</td>
				<td colspan="2">申贷银行</td>
				<td colspan="2">申贷金额<br>(万元)</td>
				<td colspan="2">贷款期限</td>
				<td colspan="2">授信时间</td>
				<td colspan="2">授信金额<br>(万元)</td>
				<td colspan="2">贷款余额<br>(万元)</td>
				</tr>
			</thead>
			<c:forEach items="${nsrList }" var="nsrVar" varStatus="index">
				<tbody>
					<td >${index.index+1 }</td>
					<td colspan="2">${nsrVar.qymc }</td>
					<td colspan="2">${nsrVar.sdsj }</td>
					<td colspan="2">${nsrVar.bankName }</td>
					<td colspan="2">${nsrVar.sqed }</td>
					<td colspan="2">${nsrVar.spzt }</td>
					<td colspan="2">${nsrVar.sxsj }</td>
					<td colspan="2">${nsrVar.sxed }</td>
					<td colspan="2">${nsrVar.sxll }</td>
				</tbody>
			</c:forEach>
		</table>
	</div>
