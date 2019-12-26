<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ctx }/resources/js/select2/select2.min.css"/>
<script type="text/javascript" src="${ctx }/resources/js/select2/select2.min.js"/>
<script type="text/javascript" src="${ctx }/resources/js/select2/zh-CN.js"/>
<script type="text/javascript" src="${ctx }/resources/js/customer/productdataitems/editList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/productdataitems/formItemEdit.js"/>
<script type="text/javascript">
/* function submitItem(){
    $("#productdataitemvaluesForm").submit();
} */
  $(function() {
	  validateItemEditForm();
	});
  

  </script> 
  <div class="page-header">
	<h1>
		编辑数据项		
	</h1>
</div>
 <div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
	
		<form id="ItemEditForm" name="ItemEditForm" role="form" class="form-horizontal" method="post">
    <%--   	<c:if test="${!empty productdataitemsEdit}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/>
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/>
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/>
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
			<input type="hidden" name="id" id="pdiId" value="${productdataitemsEdit.id }"/>
		</c:if>  --%>
		  
		   <input type="text" name="id" id="pdiId" value="${productdataitemsEdit.id}"/>
		   <input type="text" name="id" id="pdiId" value="${productDataitems.id}"/>
			<div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="pdiName">数据项名称</label>
			      <div class="col-sm-10">
			      <div class="clearfix">
			         <input class="form-control" name="pdiName" id="pdiName" type="text" value="${productdataitemsEdit.pdiName}"/>
		      	</div>
		      	</div>
		    </div>      
		   
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#ItemEditForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"/> 
		保存
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/productdataitems/listUI.html<c:if test="${!empty userEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"/> 返回
	</button>
</div> 