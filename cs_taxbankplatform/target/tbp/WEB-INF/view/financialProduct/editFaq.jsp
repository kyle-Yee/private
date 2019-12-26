<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<link rel="stylesheet"
	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript">
    ctx = "${ctx }"; 
	window.UEDITOR_HOME_URL = "${ctx }/resources/js/ueditor/";
</script>
<!-- 配置文件 -->
<script type="text/javascript" src="${ctx }/resources/js/customer/financialProduct/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" 
	src="${ctx }/resources/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/customer/financialProduct/editFaqForm.js"></script>	
<link href="${ctx }/resources/css/customer/financialProduct/form.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">

function changeContent(){
  
  var id=$("#fpId").select().val();//这就是selected的值 
  if(id=="0"){
   	 layer.msg("请选择所属产品", {
	            icon : 3
	        });
  }
  webside.common.loadPage("/financialProduct/editFAQUI.html?id="+id);
}
</script>
<style>
.iconShow
{
	padding-left: 10px; 
	padding-top: 3px;
}
.source-icon
{
	 width:95%;
	 float:left;
}
@media screen and (max-width: 374px){
	.source-icon
	{
		 width:82%;
	}
}
@media screen and (min-width: 375px) and (max-width: 449px){
	.source-icon
	{
		 width:85%;
	}
}
@media screen and (min-width: 450px) and (max-width: 1279px) {
	.source-icon
	{
		 width:90%;
	}
}
@media screen and (min_width: 1280px) {
	.source-icon
	{
		 width:90%;
	}
}
</style>
<div class="page-header">
     <c:if test="${financialProduct.productStatusEntity.id eq 'CPZT01'}">
	<div class="page-header-content">
		<div style="width:80%; text-align: center;">常见问题是产品详情介绍用于解答用户一些常见疑问的内容,若产品发布之后常见问题的内容有修改,请移步"常见问题维护"里面进行修改</div>
	</div>
	</c:if>
	  <c:if test="${empty financialProduct or financialProduct.productStatusEntity.id  eq 'CPZT02'}">
	<div class="page-header-content">
		<div style="width:80%; text-align: center;">您可以收集用户对某一产品最常提出的问题，对问题做出解答后在此编辑，这将会有利于提高申贷率</div>
	</div>
	</c:if>
</div>

<div class="row financialProduct" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="faqForm" name="faqForm" class="form-horizontal" role="form"  method="post">
	
		   <c:if test="${financialProduct.productStatusEntity.id eq 'CPZT01'}">
			<div>
				<label>所属产品   ${financialProduct.fpName}</label>
			</div>
           </c:if>  
            <c:if test="${empty financialProduct or financialProduct.productStatusEntity.id eq 'CPZT02'}">
			<div style="width: 20%">
			     <label>所属产品  </label>
			     <select name="fpId" id="fpId" onchange="changeContent()" class="form-control">
			     <option value="0">请选择</option>
			          <c:forEach var="productName" items="${productName}">
			             
			             <option value="${productName.id}" <c:if test="${productName.id==financialProduct.id}"> selected="selected"</c:if>>${productName.fpName}</option>
			          </c:forEach>
			     </select>
			</div>
			</c:if>
		<div style="height: 50px"></div>
		<c:if test="${financialProduct.productStatusEntity.id eq 'CPZT01'}">	
		<input type="hidden" name="financialProductId" id="financialProductId" value="${financialProduct.id }"> 
	   </c:if>
			<input type="hidden" name="pfId" id="faqId" value="${productFaqEntity.id }">

			<div>
				<label>常见问题编辑</label>
				<div class="clearfix">
				    <c:if test="${empty productFaqEntity.pfContent}">
				       <!-- 加载编辑器的容器 -->
					<script id="container" name="pfContent" type="text/plain" style="height:300px;">
                     
    				</script>
				    </c:if>
				    <c:if test="${! empty productFaqEntity.pfContent}">
					<!-- 加载编辑器的容器 -->
					<script id="container" name="pfContent" type="text/plain" style="height:300px;">                
        				${productFaqEntity.pfContent}
    				</script>
    				</c:if>
    				<div id="htmlTemp" style="display: none;"></div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>

<div class="center">
   <c:if test="${!faqReadOnly}">
	<button id="btnAdd" type="button" onclick="submitF()" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;提交
	</button>
	<button id="btn" type="button"
		onclick="webside.common.loadPage('/financialProduct/listUI.html<c:if test="${!empty financialProduct}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
	</c:if>
</div>
<script type="text/javascript"> 
 function submitF(){
	$('#faqForm').submit();
}
</script>
