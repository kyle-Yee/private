<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-header">
	<c:if test="${!empty statement }"><h1>免责声明预览</h1></c:if>
	<c:if test="${!empty message }"><h1>通知公告预览</h1></c:if>
	<c:if test="${!empty aboutUs }"><h1>关于我们预览</h1></c:if>
	<c:if test="${!empty contactUs }"><h1>联系我们预览</h1></c:if>
	<c:if test="${!empty accredit }"><h1>授权委托书预览</h1></c:if>
	<c:if test="${!empty agreement }"><h1>用户协议预览</h1></c:if>
</div>
<div class="row" style="margin-top: 5px;">
	${staticEntity.content }
</div>
<div class="center">
	<button id="btn" type="button"
			onclick="webside.common.loadPage('/static/staticUI.html?code=<c:if test="${!empty statement }">statement</c:if><c:if test="${!empty message }">message</c:if><c:if test="${!empty aboutUs }">aboutUs</c:if><c:if test="${!empty contactUs }">contactUs</c:if><c:if test="${!empty accredit }">accredit</c:if><c:if test="${!empty agreement }">agreement</c:if>')"
			class="btn btn-info btn-sm">
			<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>



