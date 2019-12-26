<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
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
	src="${ctx }/resources/js/upload/ajaxfileupload.js"></script>
<script type="text/javascript" 
	src="${ctx }/resources/js/ueditor/ueditor.all.min.js"></script>
	
<script type="text/javascript" src="${ctx }/resources/js/jquery/jquery-form.js"></script>	
<script type="text/javascript" src="${ctx }/resources/js/customer/loanagreement/form.js"></script>	
<style>
button{border:none;width:120px; height:40px; text-align: center; line-height: 40px; color:#000; font-size:14px;}
</style>
<div class="page-header">
	<div class="page-header-content">
		<c:if test="${empty loanAgreement}">
		<h1>添加贷款协议</h1>
		</c:if>
		<c:if test="${!empty loanAgreement}">
		<h1>修改贷款协议</h1>
		</c:if>
	</div>
</div>

<!-- 贷款协议添加和修改页面 -->
<div class="row" id="loanAgreement" style="margin-top: 5px;display:inline-block;">
	<form id="loanAgreementForm" name="loanAgreementForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty loanAgreement}">
			<input type="hidden" name="id" id="laId" value="${loanAgreement.id }">
		</c:if>
		 <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="laxyid">协议类型</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      	 <select id="laxyid"  name="laxyid" class="form-control" <c:if test="${!empty loanAgreement}">disabled="disabled"</c:if> >
					<option value="">产品协议类型</option>
					<c:forEach var="list" items="${list}" >
						<option value="${list.xykey}" ${list.xykey==loanAgreement.laxyid?"selected='selected'":""}>${list.xyvalue}</option>
					</c:forEach>
				 </select>
		      </div>
		      </div>
		   </div>
		<div class="form-group">
			  
			<input type="hidden" name="enable" value="Y"/>
			<input type="hidden" name="regionId" id="regionId" value="${regionId }"/>
			
			<label class="control-label col-sm-1 no-padding-right" for="laName">协议名称</label>
			<div class="col-sm-10">
				<input class="form-control" id="laName" name="laName" type="text" <c:if test="${!empty loanAgreement.laName}">readonly="readonly"</c:if> value="${loanAgreement.laName }"
					placeholder="协议名称..." />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-1 no-padding-right" for="content">协议内容<span class="text-danger">*</span></label>
			<div class="col-sm-10">
			<div class="clearfix">
				<!-- 加载编辑器的容器 -->
				<script id="container" name="laContent" type="text/plain" style="height:300px;">${loanAgreement.laContent }</script>
   				<div id="htmlTemp" style="display: none;"></div>
			</div>
			</div>
		</div>
	</form>
	<div class="center">
		<c:if test="${empty loanAgreement}">
			<button id="btnAdd" type="button" onclick="submitF()" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>
				添加
			</button>
		</c:if>
		<c:if test="${!empty loanAgreement}">
			<button id="btnAdd" type="button" onclick="submitF()" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>
				保存
			</button>
		</c:if>
	</div>
</div>