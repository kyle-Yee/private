<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.imgLimit img{
		max-width:90%;
	}
</style>
<div class="page-header">
	<h1>
		反馈建议详情
	</h1>
</div>
<br>
<div class="row imgLimit" style="margin-top: 5px;">
	<div>
		<div><h4>反馈建议名称：</h4></div>
		<div>${feedbackEntity.feedbackName }</div>
	</div>
	<div>
		<div><h4>反馈建议内容：</h4></div>
		<div>${feedbackEntity.feedbackContent }</div>
	</div>
</div>
<div class="center">
	<button id="btn" type="button"
			onclick="webside.common.loadPage('/feedback/feedbackUI.html')"
			class="btn btn-info btn-sm">
			<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>