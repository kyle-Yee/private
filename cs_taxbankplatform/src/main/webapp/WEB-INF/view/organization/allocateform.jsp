<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache");  
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader ("Expires", 0);    
%>
<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/demo.css" type="text/css">  
<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/zTreeStyle.css" type="text/css">   
<script type="text/javascript" src="${ctx }/resources/js/ztree/js/jquery.ztree.core-3.5.js"></script>  
<script type="text/javascript" src="${ctx }/resources/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/customer/organization/allocateform.js"></script>
<script type="text/javascript">
	var node = null;
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var idList = "";
	var taxNameList = "";
	var array = new Array();  
	<c:forEach items='${taxAuthorityEntity}' var='t'>
		array.push('${t.id}');
		taxNameList +=  '${t.taxName}'+",";
	</c:forEach> 
	if(array.length > 0){
	showMenu();
	for(var i=0;i<array.length;i++){
		node = treeObj.getNodeByParam("id", array[i], null);
		idList += array[i]+",";
		treeObj.checkNode(node, true, true);
	}
	treeObj.expandAll(true);
	}
	$("#tax").val(idList);
	$("#idList").val(idList);
	//$("#regionname").val(taxNameList);
</script>
<div class="page-header">
	<h1>
		<c:if test="${empty organizationEntity}">
		分配区域机关
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="allocateForm" name="allocateForm" class="form-horizontal" role="form" method="post">
		   <c:if test="${!empty organizationEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="orgid" id="orgid" value="${organizationEntity.id }">
			<input type="hidden" name="idList" id="idList" value="">
		  </c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="orgname">所属组织</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      <input class="form-control" name="id" id="id" type = "hidden" value="${organizationEntity.id }" />
		      </div>
		      <input class="form-control" name="orgname" id="orgname" value="${organizationEntity.orgname }" />
		      </div>
		  </div>
		  <div class="form-group">
			  <label class="control-label col-sm-1 no-padding-right" for="tax">税务机关</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
	            <input type="hidden" name="tax" id="tax" value=""/>
		      	<c:if test="${!empty organizationEntity}">
		     	<input id="regionname" name="regionname" class="form-control" type="text" readonly placeholder="填写所属区域..." value="${provinceCitiesEntity.pcname}" onclick="showMenu(); return false;" />
		      	</c:if> 
		      	<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:9999;margin-left:-294px; margin-top: -156px;">
					<ul id="treeDemo" class="ztree" style="margin-top:-20px; width:200px;"></ul>
				</div>
			 </div>
		     </div>
		 </div>
		</form>
	</div>
	<div class="hr hr-dotted"></div>
</div>

<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#allocateForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;开通
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/organization/listUI.html<c:if test="${!empty organizationEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>