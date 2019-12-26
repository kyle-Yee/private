<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${ctx }/resources/js/ueditor/third-party/codemirror/codemirror.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/ueditor/third-party/zeroclipboard/ZeroClipboard.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>扩大覆盖区域</title>
<link rel="stylesheet" href="${ctx}/resources/js/bootstrap/bootstrap.min.css"/>
<style type="text/css">
#updateFpOverlayPcIdsForm{
	width:800px;
	margin-top:20px;
	margin-left:50px;
	text-align: left;
}
#updateFpOverlayPcIdsForm .checkbox-inline{
	margin-left:10px;
}
</style>
</head>
<body>
<div class="">
	<div id="updateFpOverlayPcIdsForm"  class="form-horizontal">
		<input name="ids" type="hidden" value="${financialProduct.id }">
		<div>产品覆盖区域:</div>
		<div>
	     <c:forEach var="city" items="${cityList }">
			<label class="checkbox-inline">
			  <input type="checkbox" name="city.id" class="checkBoxItem" value="${city.id}" <c:forEach var="cityEntity" items="${cityEntities }"> <c:if test="${city.id eq cityEntity.id}">checked="checked" disabled="disabled"</c:if></c:forEach>>${city.orgname }
			</label>
		</c:forEach>
		</div>
	</div>
</div>

</body>
</html>