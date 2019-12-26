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
<script type="text/javascript" src="${ctx}/resources/js/layer-v2.3/layer.js"></script>
</head>
<body>
<div class="loanagreementDetail" >
	<div id="loanAgreementContent">
		<h2 style="text-align: center;margin-top:30px;">${loanAgreementEntity.laName }</h2>
		<div style="margin: 30px;">${loanAgreementEntity.laContent }</div>
	</div>
</div>
</body>
</html>