<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	src="${ctx }/resources/js/upload/ajaxfileupload.js"></script>
<script type="text/javascript" 
	src="${ctx }/resources/js/ueditor/ueditor.all.min.js"></script>
	
<script type="text/javascript" src="${ctx }/resources/js/jquery/jquery-form.js"></script>	
<script type="text/javascript" src="${ctx }/resources/js/customer/financialProduct/formLoan.js"></script>	
<link href="${ctx }/resources/css/customer/financialProduct/form.css" rel="stylesheet" type="text/css"/>
<style>
.uploadcss {
	text-align: left;
	margin-bottom: 0;
	padding-top: 10px;
}
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
.infor{text-align:center; height:60px; line-height: 60px;font-size: 13px;padding-left: 20px;background-color: #eee;}
.viewLoanForms{
	overflow-y:hidden;
	width:900px;
	margin: 0 auto;
}
.viewLoanForms .layui-layer-content {
	margin-left:100px;
}
.viewLoanForms,
.viewLoanForms .layui-layer-content {
	overflow-x: hidden;
}
.viewLoanForms .layui-layer-content button{
	display:none;
}
.viewLoanForms .lf_header{text-align: center;font-size: 14px; height:60px; line-height: 60px; font-weight:bold;}
.viewLoanForms #loanFormsForm .row{
/* 	height:60px; */
	padding: 10px 0 10px 0;
}
.viewLoanForms #loanFormsForm .row input[type="text"]{
	width: 300px;
}
.viewLoanForms #loanFormsForm .rows{
/* 	height:60px; */
	padding: 10px 0 10px 0;
	
}

.viewLoanForms #loanFormsForm .rows input[type="text"]{
	width: 300px;
}
.viewLoanForms #loanFormsForm .control-label{
	width: 150px;
    padding-top: 5px;
}
.viewLoanForms #loanFormsForm .control-no{
	width: 150px;
    padding-top: 5px;
}
.viewLoanForms #loanFormsForm .col-md-4{
	padding:0px;
}
#addSuccess .line1{
	height:60px;
	line-height:60px;
}
button{border:none;width:120px; height:40px; text-align: center; line-height: 40px; color:#000; font-size:14px;}
.a-upload {
		    height: 20px;
		    position: relative;
		    cursor: pointer;
		    color: #5993c5;
		    border: none;
		    overflow: hidden;
		    display: inline-block;
		    *display: inline;
		    *zoom: 1
		}
		.a-upload  input {
		    position: absolute;
		    font-size: 100px;
		    right: 634px;
		    top: 0;
		    opacity: 0;
		    filter: alpha(opacity=0);
		    cursor: pointer
		}
</style>
<div class="page-header">
	<div class="page-header-content">
		<div class="row headerTop">
		  <div class="col-md-4 addInfo">1填写产品信息</div>
		  <div class="col-md-4">2生成贷款申请单</div>
		  <div class="col-md-4">3.完成</div>
		</div>
		<c:if test="${!empty financialProduct}">
		<input type="hidden" id="stopCount" value="1"/>
		</c:if>
		
		<%-- <c:if test="${empty financialProduct}">
		<div class="row headerTop">
		  <div class="col-md-4 addInfo">1填写产品信息</div>
		  <div class="col-md-4">2生成贷款申请单</div>
		  <div class="col-md-4">3.完成</div>
		</div>
		</c:if>
		<c:if test="${!empty financialProduct}">
		<h1>编辑理财产品</h1>
		<input type="hidden" id="stopCount" value="1"/>
		</c:if> --%>
	</div>
</div>

<!-- 信用卡添加和修改页面 -->
<div class="row" id="financialProduct" style="margin-top: 5px;display:inline-block;">
	<div class="col-xs-12">
		<form id="financialProductForm" name="financialProductForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty financialProduct}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
				<input type="hidden" name="id" id="financialProductId" value="${financialProduct.id }">
				<input type="hidden" name="amountEntity.id" id="laId" value="${financialProduct.amountEntity.id}" />
				<input type="hidden" name="deadlineEntity.id" id="ldId" value="${financialProduct.deadlineEntity.id}" />
				<input type="hidden" name="ratesEntity.id" id="lrId" value="${financialProduct.ratesEntity.id}" /> 
		        <c:forEach var="rwEntity" items="${repaymentWayEntityEntities }"> 
		        	<input type="hidden" name="rwId" value="${rwEntity.id}"/>
		        </c:forEach>
			</c:if>
			<div class="form-group row">
				<label class="control-label col-sm-1 no-padding-right" for="fpName"><span class="text-danger">*</span>产品名称</label>
				<div class="clearfix col-md-4">
					<input class="form-control" id="fpName" name="fpName" type="text" <c:if test="${!empty financialProduct.fpName}">readonly="readonly"</c:if> value="${financialProduct.fpName }"
						placeholder="信用卡名称..." />
					</div>
				<input type="hidden" name="orgId" id="orgId" value="${orgId }"/>
				<input type="hidden" name="regionId" id="regionId" value="${regionId }"/>
				<input type="hidden" name="productStatusEntity.id" value="${financialProduct.productStatusEntity.id==null?'CPZT01':financialProduct.productStatusEntity.id}"/>
				<%-- <label class="control-label col-sm-1 no-padding-right" for="laId"><span class="text-danger">*</span>授信额度</label>
				<div class="clearfix col-md-4">
					<select class="form-control" id="laId" name="amountEntity.id" >
						<c:choose>
							<c:when test="${!empty financialProduct}">
								<c:forEach var="amountEntity" items="${amountEntityList }">
								<option value="${amountEntity.id }" <c:if test="${financialProduct.amountEntity.id eq amountEntity.id}">selected="selected"</c:if>>${amountEntity.amountName }</option>
								</c:forEach>							
							</c:when>
							<c:otherwise>
								<c:forEach var="amountEntity" items="${amountEntityList }">
								<option value="${amountEntity.id }">${amountEntity.amountName }</option>
								</c:forEach>	
							</c:otherwise>
						</c:choose>
					</select>
				</div> --%>
			</div>
		<%-- 	<div class="form-group row">
				<label class="control-label col-sm-1 no-padding-right" for="ldId"><span class="text-danger">*</span>授信期限</label>
				<div class="clearfix col-md-4">
					<select class="form-control" id="ldId" name="deadlineEntity.id" >
						<c:choose>
							<c:when test="${!empty financialProduct}">
								<c:forEach var="deadlineEntity" items="${deadlineEntityList }">
								<option value="${deadlineEntity.id }" <c:if test="${financialProduct.deadlineEntity.id eq deadlineEntity.id}">selected="selected"</c:if>>${deadlineEntity.deadlineName }</option>
								</c:forEach>							
							</c:when>
							<c:otherwise>
								<c:forEach var="deadlineEntity" items="${deadlineEntityList }">
								<option value="${deadlineEntity.id }">${deadlineEntity.deadlineName }</option>
								</c:forEach>	
							</c:otherwise>
						</c:choose>
					</select>
				</div>
				 --%>
			<%-- 	<label class="control-label col-sm-1 no-padding-right" for="lrId"><span class="text-danger">*</span>授信利率</label>
				<div class="clearfix col-md-4">
					<select class="form-control" id="lrId" name="ratesEntity.id" >
					  <c:choose>
							<c:when test="${!empty financialProduct}">
								<c:forEach var="ratesEntity" items="${ratesEntityList }">
								<option value="${ratesEntity.id }" <c:if test="${financialProduct.ratesEntity.id eq ratesEntity.id}">selected="selected"</c:if>>${ratesEntity.ratesName }</option>
								</c:forEach>							
							</c:when>
							<c:otherwise>
								<c:forEach var="ratesEntity" items="${ratesEntityList }">
								<option value="${ratesEntity.id }">${ratesEntity.ratesName }</option>
								</c:forEach>	
							</c:otherwise>
						</c:choose>
					</select>
				</div>
			</div> --%>
			
		<%-- 	<div class="form-group row">
				<label class="control-label col-sm-1 no-padding-right" for="rwIds"><span class="text-danger">*</span>还款方式</label>
				<div class="col-sm-10" id="rwIds">
					<input name="rwIds" type="hidden"/>
					<c:choose>
						<c:when test="${!empty financialProduct}">
							<c:forEach var="repaymentWayEntity" items="${repaymentWayEntityList }">
							<label class="checkbox-inline">
								<input type="checkbox" name="rwId" value="${repaymentWayEntity.id }"<c:forEach var="rwEntity" items="${repaymentWayEntityEntities }"> <c:if test="${rwEntity.id eq repaymentWayEntity.id}">checked="checked"</c:if></c:forEach>><span class="rwname">${repaymentWayEntity.rwname }</span>
							</label>
							</c:forEach>							
						</c:when>
						<c:otherwise>
							<c:forEach var="repaymentWayEntity" items="${repaymentWayEntityList }">
							<label class="checkbox-inline">
							  <input type="checkbox" name="rwId" value="${repaymentWayEntity.id}"><span class="rwname">${repaymentWayEntity.rwname }</span>
							</label>
							</c:forEach>	
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			 --%>
			<div class="form-group row">
				<label class="control-label col-sm-1 no-padding-right" for="lrId"><span class="text-danger">*</span>多重授信</label>
				<div class="clearfix col-md-4" style="padding-top:7px;">
					<input  type="radio" name="fpmanyCredit" id="fpmanyCredit" checked="checked" value="${financialProduct.fpmanyCredit==null?'N':financialProduct.fpmanyCredit}" >不允许
				</div>
				
				<label class="control-label col-sm-1 no-padding-right" for="fpIshot">产品状态</label>
				<div class="clearfix col-md-4" >
					<c:choose>
						<c:when test="${!empty financialProduct}">
							<c:forEach var="pstatus" items="${productStatusEntityList}">
								<label class="checkbox-inline">
								<input type="radio" disabled="disabled"  name="productStatusEntity.id" value="${pstatus.id}" <c:if test="${pstatus.id eq financialProduct.pstatusId}">checked="checked"</c:if>>${pstatus.productStatusName }
								</label>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="pstatus" items="${productStatusEntityList}">
								<label class="checkbox-inline">
								<input type="radio" disabled="disabled"  name="productStatusEntity.id" value="${financialProduct.pstatusId==null?'CPZT01':financialProduct.pstatusId}" <c:if test="${pstatus.id eq 'CPZT01'}">checked="checked"</c:if>>${pstatus.productStatusName }
								</label>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="form-group row">
			<%-- 	<label class="control-label col-sm-1 no-padding-right" for="gsIds"><span class="text-danger">*</span>担保方式</label>
				<div class="clearfix col-md-4" id="gsIds">
					<input name="gsIds" type="hidden"/>
					<c:choose>
						<c:when test="${!empty financialProduct}">
							<c:forEach var="guaranteeStyleEntity" items="${guaranteeStyleEntityList }">
								<label class="checkbox-inline">
								<input type="checkbox" name="gsId" value="${guaranteeStyleEntity.id }" <c:forEach var="gsEntity" items="${guaranteeStyleEntityEntities }"> <c:if test="${gsEntity.id eq guaranteeStyleEntity.id}">checked="checked"</c:if></c:forEach>>${guaranteeStyleEntity.gsname }
								</label>
							</c:forEach>							
						</c:when>
						<c:otherwise>
							<c:forEach var="guaranteeStyleEntity" items="${guaranteeStyleEntityList }">
							<label class="checkbox-inline">
							  <input type="checkbox" name="gsId" value="${guaranteeStyleEntity.id}">${guaranteeStyleEntity.gsname }
							</label>
							</c:forEach>	
						</c:otherwise>
					</c:choose>
				</div> --%>
				
				<label class="control-label col-sm-1 no-padding-right" for="fpIshot">是否热门</label>
				<div class="clearfix col-md-4" id="fpIshot" >
					<c:choose>
						<c:when test="${!empty financialProduct}">
							<c:if test="${financialProduct.fpIshot eq 'Y'}">
								<label class="radio-inline">
								  <input type="radio" name="fpIshot" value="Y" checked="checked"> 是
								</label>
								<label class="radio-inline">
								  <input type="radio" name="fpIshot" value="N"> 否
								</label>
							</c:if>
							<c:if test="${financialProduct.fpIshot eq 'N'}">
								<label class="radio-inline">
								  <input type="radio" name="fpIshot" value="Y"/> 是
								</label>
								<label class="radio-inline">
								  <input type="radio" name="fpIshot" value="N" checked="checked"> 否
								</label>
							</c:if>
						</c:when>
						<c:otherwise>
							<label class="radio-inline">
							  <input type="radio" name="fpIshot" value="Y" checked="checked"> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="fpIshot" value="N"> 否
							</label>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rwIds"><span class="text-danger">*</span>覆盖区域</label>
				<div class="col-sm-10 fpOverlayPcId">
					<label class="radio-inline">
					  <input type="radio" id="radioAll" name="all" value="all"> 全部
					</label>
					<input name="fpOverlayPcIds" type="hidden"/>
					<c:choose>
						<c:when test="${!empty financialProduct}">
							<c:forEach var="city" items="${cityList }">
							<label class="checkbox-inline">
							<input type="checkbox" name="fpOverlayPcId" value="${city.id}"<c:forEach var="cityEntity" items="${cityEntities }"> <c:if test="${city.id eq cityEntity.id}">checked="checked"</c:if></c:forEach>>${city.orgname }
							</label>
							</c:forEach>							
						</c:when>
						<c:otherwise>
							<c:forEach var="city" items="${cityList }">
							<label class="checkbox-inline">
							  <input type="checkbox" name="fpOverlayPcId" value="${city.id}">${city.orgname }
							</label>
							</c:forEach>	
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="summary">产品图片</label>
				<div class="col-sm-10">
					<div class="uploadcss">
						<a href="javascript:;" id="uploadBasicInfoHead" class="a-upload">上传图片
							<input type="file" onchange="uploadHead()" id="basicInfoHead" name="basicInfoHead" />
						</a>
						<a href="#deleteInfo" id="deletepictrue">删除图片</a>
					</div>
					<div class="uploadPicture">
						<img id="imgHead" src="" style="width: 290px;height: 40px;" alt="图片">
						<input type="hidden" id="basicHeadUrl" name="fpPrdImgUrl" value="${financialProduct.fpPrdImgUrl }">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="content"><span class="text-danger">*</span>详情描述</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<!-- 加载编辑器的容器 -->
					<script id="container" name="fpDetails" type="text/plain" style="height:300px;">${financialProduct.fpDetails }</script>
    				<div id="htmlTemp" style="display: none;"></div>
    				<input type="hidden" name="attachmenturls" id="attachmenturls" />
				</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
	<div class="center">
		<c:if test="${empty financialProduct}">
			<button id="btnAdd" type="button" onclick="nextF(0)" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>
				下一步
			</button>
		</c:if>
		<c:if test="${!empty financialProduct}">
			<button id="btnAdd" type="button" onclick="nextF('${financialProduct.id}')" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>
				下一步
			</button>
		</c:if>
		<%-- <c:if test="${empty financialProduct}">
			<button id="btnAdd" type="button" onclick="nextF()" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>
				下一步
			</button>
		</c:if>
		<c:if test="${!empty financialProduct}">
			<button id="btnAdd" type="button" onclick="submitF()" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>
				保存
			</button>
		</c:if> --%>
	</div>
</div>

<!-- 生成贷款申请单 -->
<div id="loanForms" style=" display:none; width: 100%;">
	<div class="infor">贷款申请单为企业申请贷款时必须填写的申请资料，基于字段已根据上一步的产品信息自动生成，如需收集企业其它资料，请在下方继续添加字段以完善表单!</div>
	<div class="viewLoanForms">
		<div class="lf_header">贷款申请单</div>
		<form id="loanFormsForm" name="loanFormsForm" onsubmit="return false;" method="post">
			<div class="" style="height: 60px; padding: 10px 0 10px 0;  margin-left: -10px;">
				<label class="control-no col-sm-1 no-padding-right" id="phone_label" ><span class="text-danger">*</span>联系人手机号</label>
				<div class="col-md-4">
					<input readOnly="true" disabled="true" class="form-control" id="lf_phone" name="1" type="text"/>
				</div>
			</div>
			<div class="" style="height: 60px; padding: 10px 0 10px 0;  margin-left: -10px; ">
				<label class="control-no col-sm-1 no-padding-right" id="address_label" for="lf_dkqx"><span class="text-danger">*</span>联系地址</label>
				<div class="col-md-4">
					<input readOnly="true" disabled="true" class="form-control" id="lf_address" name="2" type="text" />
				</div>
			</div>
			<div class=""   style="height: 60px; padding: 10px 0 10px 0;  margin-left: -10px;" id="afterFlag">
				<label class="control-no col-sm-1 no-padding-right" id="code_label" for="lf_code"><span class="text-danger">*</span>营销经理推荐代码</label>
				<div class="col-md-4">
				  <input readOnly="true" disabled="true" class="form-control" id="lf_code" name="2" type="text" />
				</div>
			</div>
			
			<c:if test="${!empty financialProduct}">
				<c:forEach var="items" items="${loanFormsAttachList }" varStatus="status">
					<c:if test="${items.pdi_id ne '0'}">
						<div class="row itemList item${items.pdi_id }">
							<label class="control-label col-sm-1 no-padding-right" pdi_id="${items.pdi_id }" pdi_code="${items.pdi_code }" pdi_name="${items.pdi_name }" ht_type="${items.ht_type }" productditvalueslist="${items.pdi_values }"><span class="text-danger">*</span>
								<c:choose> 
							     <c:when test="${fn:length(items.pdi_name) > 6}"> 
							      <c:out value="${fn:substring(items.pdi_name, 0, 6)}..." />  ：
							     </c:when> 
							     <c:otherwise> 
							      <c:out value="${items.pdi_name}" />  ：
							     </c:otherwise>
							    </c:choose>
							</label>
								<c:if test="${items.ht_type eq 'text'}">
									<div class="col-md-4 rightL" style="width: 558px;">
										<input readOnly="true" disabled="true" class="form-control" name="${items.pdi_code}" type="text"/>
									</div>
								</c:if>
								<c:if test="${items.ht_type eq 'radio'}">
									<div class="col-sm-8 rightL" style="width: 558px;">
									<c:set value="${ fn:split(items.pdi_values, ',') }" var="htNames" />
										<c:forEach var="htName" items="${htNames }">
												<span class="radio-inline">
													<input readOnly="true" disabled="true" name="${items.pdi_code}" type="radio"/>${htName }
												</span>
										</c:forEach>
									</div>
								</c:if>
								
								<c:if test="${items.ht_type eq 'checkbox'}">
									<div class="col-sm-7 rightL" style="width: 558px;">
									<c:set value="${ fn:split(items.pdi_values, ',') }" var="htNames" />
										<c:forEach var="htName" items="${htNames }">
											<span class="checkbox-inline">
												<input readOnly="true" disabled="true" name="${items.pdi_code}" type="checkbox"/>${htName }
											</span>
										</c:forEach>
									</div>
								</c:if>
								
								<c:if test="${items.ht_type eq 'file'}">
									<div class="rightL" style="display:inline-block; width: 558px;">
										<input readOnly="true" disabled="true" name="${items.pdi_code}" type="file" style="display:inline-block;"/>
									</div>
								</c:if>
									<c:if test="${items.ht_type eq 'select'}">
									<div class="col-sm-7 rightL" style="width: 558px;">
									<c:set value="${ fn:split(items.pdi_values, ',') }" var="htNames" />
									<span class="checkbox-inline">
									<%--  <select  name="${items.pdiCode}" style="width:267px;height:34px; "> --%>
									 <select  name="${items.pdi_code}" style="width:300px;height:34px; margin-left: -31px;">
	                                         <c:forEach var="htName"  items="${htNames }">
		                                       <option value="${htName }">${htName }</option>
		                                       </c:forEach>
	                                         </select>
	                               	</span>
							  		
									</div>
								</c:if>
								<button onclick="removeF('${items.pdi_id}')">删除</button>
							
						</div>
					</c:if>
				</c:forEach>	
			</c:if>
			
			
			<div class="row">
				<label class="control-label col-sm-1 no-padding-right" id="sqxyss_label" for="lf_sqxyss" ><span class="text-danger">*</span>授权协议</label>
				<div class="col-md-4">
					<select  style="width:307px;height:34px;" id="lf_sqxyss" name="lf_sqxyss" >
						<option value="">请选择授权协议</option>
						<c:choose>
							<c:when test="${!empty loanAgreementssEntity}">
								<c:forEach var="loanagreement" items="${loanAgreementList}" >
									<option value="${loanagreement.id }" <c:if test="${loanagreement.id eq loanAgreementssEntity.id}">selected="selected"</c:if>>${loanagreement.laName }</option>
									<div id="loanAgreementContent${loanAgreementssEntity.id}" style="display:none">
										<h2 style="text-align: center;margin-top:30px;">${loanAgreementssEntity.laName }</h2>
										<div style="margin: 30px;">${loanAgreementssEntity.laContent }</div>
									</div>
								</c:forEach>							
							</c:when>
							<c:otherwise>
								<c:forEach var="loanagreement" items="${loanAgreementList}" >
								<option value="${loanagreement.id }">${loanagreement.laName }</option>
								<div id="loanAgreementContent${loanagreement.id}" style="display:none">
										<h2 style="text-align: center;margin-top:30px;">${loanagreement.laName }</h2>
										<div style="margin: 30px;">${loanagreement.laContent }</div>
								</div>
								</c:forEach>	
							</c:otherwise>
						</c:choose>	
					</select>
					<!-- <input readOnly="true" disabled="true" class="form-control" id="lf_sqxyss" name="lf_sqxyss" type="text" /> -->
				</div>
				<label class="col-md-2 control-label" style="margin-left: 30px;" onclick="showssContent()" ><a>查看</a></label>
			</div>
			
			
		<%-- 	<c:if test="${fn:length(loanAgreementList) == 1 }">
				<c:forEach var="loanAgreementEntity" items="${loanAgreementList }" varStatus="status">
					<div class="row">
						<label class="control-label col-sm-1 no-padding-right" id="dkxy_label" for="dkxy"></label>
						<div>
							<input readOnly="true" disabled="true" type="checkbox" name="dkxy"/><label>我已阅读并同意<a id="xy" onclick="showContent(${loanAgreementEntity.id })">${loanAgreementEntity.laName }</a>的内容 </label>
							<input readOnly="true" disabled="true" type="hidden" name="dkxyId" id="dkxyId" value="${loanAgreementEntity.id }">
							<div id="loanAgreementContent${loanAgreementEntity.id }" style="display:none">
								<h2 style="text-align: center;margin-top:30px;">${loanAgreementEntity.laName }</h2>
								<div style="margin: 30px;">${loanAgreementEntity.laContent }</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(loanAgreementList) > 1 }">
				<div class="row">
					<label class="control-label col-sm-1 no-padding-right" id="dkxy_label" for="dkxy"></label>
					<div class="loanAgreement2">
						<input id="appendHtmlId" readOnly="true" disabled="true" type="checkbox" name="dkxy"/>
						<c:if test="${!empty financialProduct}">
							<span id="dkxy_old">
								<label>我已阅读并同意<a id="xy" onclick="showContent(${loanAgreementEntity.id })">${loanAgreementEntity.laName }</a>的内容 </label>
								<input readOnly="true" disabled="true" type="hidden" name="dkxyId" id="dkxyId" value="${loanAgreementEntity.id }">
								<div id="loanAgreementContent${loanAgreementEntity.id }" style="display:none">
									<h2 style="text-align: center;margin-top:30px;">${loanAgreementEntity.laName }</h2>
									<div style="margin: 30px;">${loanAgreementEntity.laContent }</div>
								</div>
							</span>
						</c:if>
					</div>
				</div>
			</c:if> --%>
			
			<input type="hidden" id="loanAgreementSize" value="${fn:length(loanAgreementList) }"/>
		</form>
		</div>
		<div>
			<button style="margin-left:120px;margin-top:20px;" onclick="showAddSjx()">添加数据项</button>
		</div>
	<div class="center">
			<%-- <button id="btn" type="button" onclick="webside.common.loadPage('/financialProduct/listUI.html<c:if test="${!empty financialProduct}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" --%>
			<c:if test="${!empty financialProduct}">
				<button  onclick="webside.common.loadPage('/financialProduct/editUI.html?ptdm=2&id=${financialProduct.id }')">上一步</button>
			</c:if>
			<c:if test="${empty financialProduct}">
				<!-- <button  onclick="webside.common.loadPage('/financialProduct/addUI.html?ptdm=2')">上一步</button> -->
				<button  id="gotosyb" onclick="gotosyb()">上一步</button>
			</c:if>
			<button  onclick="showHtml()">预览</button>
			<c:if test="${empty financialProduct}">
			<button  onclick="submitAll()">提交</button>
			</c:if>
			<c:if test="${!empty financialProduct}">
			<button  onclick="submitUpdateAll('${financialProduct.id }')">保存</button>
			</c:if>
	</div>
</div>

<!-- 添加成功界面 -->
<div id="addSuccess" style="display:none; width: 100%;">
	<div class="addSucess_content" style="text-align: center; width:800px; margin: 0 auto; padding-top:120px; font-size: 14px;">
		<c:if test="${!empty financialProduct}">
			<div class="line1">修改成功!你可能需要以下操作</div>
		</c:if>
		<c:if test="${empty financialProduct}">
			<div class="line1">添加成功!你可能需要以下操作</div>
		</c:if>
		<div class="line1" id="timeSet"></div>
		<div class="line1"><button style="margin-right: 40px;" onClick="stopCount()" id="btn1">发布金融产品</button><button onClick="stopCount()" id="btn2">继续添加产品</button> </div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
	var imagePath = $("#basicHeadUrl").val();
	if (imagePath!="") {
		 $("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ imagePath);
		 document.getElementById("deletepictrue").style.display = "block";
		 document.getElementById("uploadBasicInfoHead").style.display = "none";
	}else{
		 document.getElementById("deletepictrue").style.display = "none";
		 document.getElementById("uploadBasicInfoHead").style.display = "block";
	}
	
	$("#uploadBasicInfoHead").click(function(){
		$("#basicInfoHead").click(); 
	});
	
	$("#deletepictrue").click(function(){
		var imagePath = $("#basicHeadUrl").val();
		if (imagePath!="") {
			if(confirm("是否删除？")){
			$.ajax({
				url:"<%=request.getContextPath()%>/upload/deleteImage.html",//需要链接到服务器地址   
				data:{
					imagePath:imagePath
				},
			    dataType: 'json',   //json  
			    success: function (data) { 
			    	 //alert(data.msg);
			    }
			});
				$("#basicHeadUrl").val("");
				$("#imgHead").attr("src","");
				document.getElementById("uploadBasicInfoHead").style.display = "block";
				document.getElementById("deletepictrue").style.display = "none";
			}
		}else{
		   	 layer.msg("请先上传图片", {
	            icon : 3
	        });
		
		}
	});
			
});

function uploadHead(){
		$.ajaxFileUpload({  
		      url:"<%=request.getContextPath()%>/upload/uploadFile.html",//需要链接到服务器地址   
		      secureuri:false,  
		      fileElementId:"basicInfoHead",//文件选择框的id属性  
		      dataType: 'json',   //json  
		      success: function (data) { 
		    	 if(data.status=="0"){
		        		$("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ data.savePath);
						$("#basicHeadUrl").val(data.savePath);
						document.getElementById("uploadBasicInfoHead").style.display = "none";
						document.getElementById("deletepictrue").style.display = "block";
					}
					if (data.status == "1") {
						alert(data.msg);
					}
					if (data.status == "2") {
						alert(data.msg);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					 	 layer.msg("上传失败", {
	            icon : 2
	        });
				}
			});
	};
</script>