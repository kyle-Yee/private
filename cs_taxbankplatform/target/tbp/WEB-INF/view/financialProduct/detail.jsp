<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- basic styles -->
<link rel="stylesheet" href="${ctx}/resources/js/bootstrap/bootstrap.min.css"/>
<script type="text/javascript" src="${ctx }/resources/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/layer-v2.3/layer.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/customer/financialProduct/detail.js"></script>
<style type="text/css">
.applyDetail #loanForms{
	width:800px;
	margin-left:50px;
	margin-top:50px;
}
.applyDetail #loanForms .row1{
	display:block;
	height:40px;
	line-height:40px;
	margin-top:20px;
}
.applyDetail #loanForms .row1 .control-span{
	margin-left:50px;
	text-align:left;
	width:150px;
}
.applyDetail #loanForms .row1 .form-control{
	width:400px;
}
input[type=checkbox], input[type=checkbox],
input[type=checkbox], input[type=radio]{
	height:35px;
}
.lineH{
	min-height:36px;
	line-height:36px;
	height: auto;
}
.lineH .leftR{
	margin-right:20px;
}
</style>
</head>
<body>
<ul class="nav nav-tabs">
  <li  id="showProductPDetail" class="active"><a onclick="showProductPDetail()">金融产品详情</a></li>
  <!-- <li  id="showApplyDetail" ><a onclick="showApplyDetail()">贷款申请单</a></li> -->
</ul>
<div class="financialProductDetail" >
	<div class="span9 columns" style="margin:20px 50px;">
	     <h4>${financialProduct.fpName }</h4>
	     <div class="lineH"><span class="leftR">最高额度:</span> ${amountName}</div>
	     <div class="lineH"><span class="leftR">贷款利率:</span>${ratesName}</div>
	     <div class="lineH"><span class="leftR">还款期限:</span>${deadlineName}</div>
	     <div class="lineH" style="height: auto" ><span>还款方式:</span>
		     <c:forEach var="repaymentWayEntity" items="${repaymentWayEntityEntities }">
				<span class="checkbox-inline">
				  ${repaymentWayEntity.rwname }
				</span>
			 </c:forEach>
		 </div>
	     <div class="lineH"><span>担保方式:</span>
	     	<c:forEach var="guaranteeStyleEntity" items="${guaranteeStyleEntityEntities }">
				<span class="checkbox-inline">
				  ${guaranteeStyleEntity.gsname }
				</span>
			</c:forEach>
	     </div>

	     <div style="line-height:36px;">
	     	<div style="float: left;width: 83px;"><span class="leftR">产品详情: </span></div>
	     	<div style="float: left;width: 700px;"><span>${financialProduct.fpDetails}</span></div>
	     </div>
	     
	</div>
</div>
<div class="applyDetail" style="display: none;">
	<!-- 生成贷款申请单 -->
<div id="loanForms">
	<div class="viewLoanForms">
		<H4 class="lf_header" style="text-align: center;">贷款申请单</H4>
			<%-- <div class="row1">
				<span class="control-span col-sm-1 no-padding-right"><span class="text-danger">*</span>贷款额度</span>
				<span class="rightL">
					<input readOnly="true"  disabled="true" class="form-control" id="lf_dked" type="text" placeholder="最高${amountName}"/>
				</span>
			</div>
			<div class="row1">
				<span class="control-span col-sm-1 no-padding-right"><span class="text-danger">*</span>贷款期限</span>
				<span class="rightL">
					<input readOnly="true" disabled="true" class="form-control" id="lf_dkqx" type="text"  placeholder="最长${deadlineName}"/>
				</span>
			</div>
			<div class="row1" id="afterFlag">
				<span class="control-span col-sm-1 no-padding-right"><span class="text-danger">*</span>还款方式</span>
				<span class="rightL" id="lf_hkfs">
				<c:forEach var="repaymentWayEntity" items="${repaymentWayEntityEntities }">
					<span class="checkbox-inline"><input readOnly="true" disabled="true" type="checkbox"/>${repaymentWayEntity.rwname }</span>
					 </c:forEach>
				</span>
			</div> --%>
			<!-- 数据项 -->
			<c:forEach var="items" items="${loanFormsAttachList }" varStatus="status">
				<c:if test="${items.pdi_code ne 'sqxyss'}">
						<div class="row1">
							<span class="control-span col-sm-1 no-padding-right"><span class="text-danger">*</span>
								<c:choose> 
							     <c:when test="${fn:length(items.pdi_name) > 6}"> 
							      <c:out value="${fn:substring(items.pdi_name, 0, 6)}..." /> ：
							     </c:when> 
							     <c:otherwise> 
							      <c:out value="${items.pdi_name}" /> ：
							     </c:otherwise>
							    </c:choose>
						   </span>
								<c:if test="${items.ht_type eq 'text'}">
									<span class="rightL">
										<input readOnly="true" disabled="true" class="form-control" name="${items.pdi_code}" type="text"/>
									</span>
								</c:if>
								<c:if test="${items.ht_type eq 'radio'}">
									<span class="rightL">
									<c:set value="${ fn:split(items.pdi_values, ',') }" var="htNames" />
										<c:forEach var="htName" items="${htNames }">
												<span class="radio-inline">
													<input readOnly="true" disabled="true" name="${items.pdi_code}" type="radio"/>${htName }
												</span>
										</c:forEach>
									</span>
								</c:if>
								
								<c:if test="${items.ht_type eq 'checkbox'}">
									<span class="rightL">
									<c:set value="${ fn:split(items.pdi_values, ',') }" var="htNames" />
										<c:forEach var="htName" items="${htNames }">
											<span class="checkbox-inline">
												<input readOnly="true" disabled="true" name="${items.pdi_code}" type="checkbox"/>${htName }
											</span>
										</c:forEach>
									</span>
								</c:if>
								<c:if test="${items.ht_type eq 'select'}">
									<span class="rightL">
										<c:set value="${ fn:split(items.pdi_values, ',') }" var="htNames" />
										   
	                                         <select  disabled="disabled" name="${items.pdi_code}" style="width:396px;height:34px; ">
	                                         <c:forEach var="htName"  items="${htNames }">
		                                       <option value="${htName }">${htName }</option>
		                                       </c:forEach>
	                                         </select>
									</span>
								</c:if>
								<c:if test="${items.ht_type eq 'file'}">
									<span class="rightL">
									<input readOnly="true" disabled="true" name="${items.pdi_code}" type="file" style="display:inline-block;"/>
									</span>
								</c:if>
						</div>
				</c:if>
			</c:forEach>
			<c:forEach var="items" items="${loanFormsAttachList }" varStatus="status">
				<c:if test="${items.pdi_code eq 'sqxyss'}">
					<div class="row1">
						<span class="control-span col-sm-1 no-padding-right"><span class="text-danger">*</span>授信协议</span>
						<!-- <span class="control-span col-sm-2 no-padding-right" style="height: 40px !important;line-height: 40px;"></span> -->
						<span class="rightL" style="height: 40px !important;line-height: 40px;margin:0px;">
							<input style="float:left; height: 40px;line-height: 40px;margin-right:10px;" readOnly="true" disabled="true" type="checkbox" name="sqxyss"/><label style="float:left; height: 40px;line-height: 40px;margin:0px;">我已阅读并同意<a id="sqxyss" onclick="showssContent()">${loanAgreementssEntity.laName }</a>的内容 </label>
							<div id="loanAgreementssContent" style="display:none">
								<h2 style="text-align: center;margin-top:30px;">${loanAgreementssEntity.laName }</h2>
								<div style="margin: 30px;">${loanAgreementssEntity.laContent }</div>
							</div>
						</span>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>
</body>
</html>