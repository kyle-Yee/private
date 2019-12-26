<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- basic styles -->
<link rel="stylesheet" href="${ctx}/resources/js/bootstrap/bootstrap.min.css"/>
<script type="text/javascript" src="${ctx }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<style type="text/css">
.lineH{
	height:36px;
	line-height:36px;
}
.lineH .leftR{
	width:100px;
	text-align: right;
	margin-right:20px;
}
</style>
</head>
<body>
<div class="messageDetail" >
	<div class="span9 columns" style="margin:20px 50px;">
	     <h4>${message.title }</h4>
	     <div class="lineH"><span class="leftR">类型:</span>${typeName }</div>
	     <div class="lineH"><span class="leftR">发送人:</span>${userName }</div>
	     <div class="lineH"><span class="leftR">发送时间:</span>${createTime }</div>
	     <div class="lineH"><span class="leftR">附标题:</span>${message.subtitle }</div>
	     <div class="lineH" style="padding:0 10px 20px 10px;">
	       	${message.content }
	     </div>
	</div>
</div>
</body>
</html>