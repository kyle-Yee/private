<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${ctx }/resources/js/customer/financialProducts/form.js"></script>
<div class="page-header">
	<h1>
		<c:if test="${empty financialProductsEntity}">
		添加金融产品
		</c:if>
		<c:if test="${!empty financialProductsEntity}">
		编辑金融产品
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="addForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty financialProductsEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" id="regionclassid" name="id" value="${financialProductsEntity.cpdm }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcName">银行名称</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="yhmc" name="yhmc" type="text" value="${financialProductsEntity.yhmc }" maxlength="50"/>
				</div>
				</div>
				<%-- <div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="yhmc" name="yhmc" onchange = "getfunction()">
						<c:if test ="${empty portManagerEntity}">
						<!-- 添加 -->
							<option value="微银总部">请选择银行名称</option>
							<c:forEach var="orgEntity" items="${bankNameList}" >
								<option value="${orgEntity.orgname}">${orgEntity.orgname}</option>
							</c:forEach>
						</c:if>
						<c:if test ="${!empty portManagerEntity}">
						<c:forEach var="orgEntity" items="${bankNameList}" >
						<option value="${orgEntity.orgname}" ${portManagerEntity.yhmc==orgEntity.orgname?"selected='selected'":""}>${orgEntity.orgname}</option>
						</c:forEach>
						</c:if>
					</select>
				</div>
				</div> --%>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">银行代码</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="yhdm" name="yhdm" type="text" value="${financialProductsEntity.yhdm }" maxlength="50"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">产品名称</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="cpmc" name="cpmc" type="text" value="${financialProductsEntity.cpmc }" maxlength="50"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">产品编号</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="cpdm" name="cpdm" type="text" value="${financialProductsEntity.cpdm }" maxlength="10" onchange="if(!/^[0-9a-zA-Z]*$/.test(this.value)){ document.getElementById('zxb').hidden=false; this.value='';}else{document.getElementById('zxb').hidden=true;}"/>
					<input class="form-control" id="cpdmL" name="cpdmL" type="hidden" value="${financialProductsEntity.cpdm }" maxlength="10"  />
					<span id ="zxb" hidden style = "color:#9932CC">只允许输入1-10位字母、数字</span><br/>
				</div>
				</div>
			</div>
			 
			<input class="form-control" id="orgId" name="orgId" type="hidden" value="${financialProductsEntity.orgId }"/>
					 
			<%-- <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">银行端口号</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="yhdkh" name="yhdkh" type="text" value="${portManagerEntity.yhdkh }" maxlength="10"/>
				</div>
				</div>
			</div> --%>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#addForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty financialProductsEntity}">
		添加
		</c:if>
		<c:if test="${!empty financialProductsEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/financialProducts/listUI.html<c:if test="${!empty financialProductsEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>