<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>

<!-- <script type="text/javascript" src="${ctx}/resources/js/jquerycharts/highcharts.js"></script> -->
<button onclick="search()">查询</button>
<div>
	<label>用户注册认证情况分析</label>
	<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>
</div>
