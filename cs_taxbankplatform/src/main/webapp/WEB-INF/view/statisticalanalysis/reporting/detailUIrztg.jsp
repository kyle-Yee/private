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
		<table class="table table-bordered text-center">
			<thead>
				<tr>
				<td>序号</td>
				<td colspan="2">申请时间</td>
				<td colspan="2">企业名称</td>
				<td colspan="2">法人姓名</td>
				<td colspan="2">法人证件号码</td>
				<td colspan="2">申请金额<br>(万元)</td>
				</tr>
			</thead>
			<c:forEach items="${nsrList }" var="nsrVar" varStatus="index">
				<tbody>
					<td >${index.index+1 }</td>
					<td colspan="2">${nsrVar.zcsj }</td>
					<td colspan="2">${nsrVar.qymc }</td>
					<td colspan="2">${nsrVar.frxm }</td>
					<td colspan="2">${nsrVar.frsjh }</td>
					<td colspan="2">${nsrVar.sqje }</td>
				</tbody>
			</c:forEach>
		</table>
	</div>
