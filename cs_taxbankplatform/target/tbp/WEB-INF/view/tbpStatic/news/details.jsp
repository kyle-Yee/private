<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-header">
	<c:if test="${!empty bank }"><h1>银行动态预览</h1></c:if>
	<c:if test="${!empty policy }"><h1>政策动态预览</h1></c:if>
</div>
<br>
<div class="row" style="margin-top: 5px;">
	<div>
		<div><h4>动态名称：</h4></div>
		<div>${newsEntity.title }</div>
	</div>
	<div>
		<div><h4>动态发布方：</h4></div>
		<div>${newsEntity.dept }</div>
	</div>
	<div>
		<div><h4>动态发布日期：</h4></div>
		<div>${newsEntity.date }</div>
	</div>
	<div>
		<div><h4>动态内容：</h4></div>
		<div>${newsEntity.content }</div>
	</div>
</div>
<div class="center">
	<button id="btn" type="button"
			onclick="webside.common.loadPage('/news/newsUI.html?code=<c:if test="${!empty bank }">bank</c:if><c:if test="${!empty policy }">policy</c:if>')"
			class="btn btn-info btn-sm">
			<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>