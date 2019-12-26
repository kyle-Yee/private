<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-header">
	<h1>
		常见问题预览
	</h1>
</div>
<br>
<div class="row" style="margin-top: 5px;">
	<div>
		<div><h4>常见问题名称：</h4></div>
		<div>${faqEntity.faqName }</div>
	</div>
	<div>
		<div><h4>常见问题内容：</h4></div>
		<div>${faqEntity.faqContent }</div>
	</div>
</div>
<div class="center">
	<button id="btn" type="button"
			onclick="webside.common.loadPage('/faq/faqUI.html')"
			class="btn btn-info btn-sm">
			<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>