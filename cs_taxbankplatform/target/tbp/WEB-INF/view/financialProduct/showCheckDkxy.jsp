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
<script type="text/javascript" src="${ctx}/resources/js/layer-v2.3/layer.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择贷款协议</title>
<link rel="stylesheet" href="${ctx}/resources/js/bootstrap/bootstrap.min.css"/>
<style type="text/css">
#updateFpOverlayPcIdsForm{
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
		<div>选择贷款协议:</div>
		<div>
	     <c:forEach var="loanAgreement" items="${loanAgreementList }">
			<label class="checkbox-inline">
			  <input type="radio" name="laId" class="checkBoxItem" value="${loanAgreement.id}" <c:forEach var="loanAgreementEntity" items="${loanAgreementEntities }"> <c:if test="${loanAgreement.id eq loanAgreementEntity.id}">checked="checked"</c:if></c:forEach>><a id="laName${loanAgreement.id }" onclick="showContent(${loanAgreement.id })">${loanAgreement.laName }</a> 
			  	<div id="loanAgreementContent${loanAgreement.id }" style="display:none">
					<h2 style="text-align: center;margin-top:30px;">${loanAgreement.laName }</h2>
					<div style="margin: 30px;">${loanAgreement.laContent }</div>
				</div>
			</label>
		</c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript">
function showContent(laId){ //查看贷款协议内容
	 var conentHtml = $("#loanAgreementContent"+laId).html();
	 layer.open({
		 title: "协议内容",
		 type: 1,
		 area: ['800px', '500px'],
		 fix: true,
		 maxmin: true,
		 content: conentHtml
	 });
};
</script>
</body>
</html>