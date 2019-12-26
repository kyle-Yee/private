<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapprove/creditextension.js">
	</script><%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<style>
/* 	.sxspdTdTitle {
		color:white;
		font-size:17px;
		height:32px;
		padding-left:10px;
		border-right:1px solid black;
	} */
	.sxspdTdContent {
		text-align:center;
		height:25px;
	}
	
	.sxspdTdContent2 {
		width:200px;
		height:25px;
		padding-left:10px;
	}
	input {
		height:25px;
	}
	#rw_id {
		height:10px;
		margin-left:100px;
		margin-right:10px;
	}
	#hkfsStyle,#shjgStyle {
		text-align:left;
	}
	#lac_id {
		height:10px;
		margin-left:150px;
		margin-right:10px;
		padding:0px;
	}
	.tdClss{
		text-align:center;
		border:1px solid black;
	}
	#lar_credit_quota {
		padding:5px;
	}
	#lar_contract {
		margin-left:102px;
		padding:5px;
	}
	#lar_bank_name {
		margin-left:-10px;
		padding:5px;
	}
	#lar_loan_deadline {
		margin-left:20px;
		padding:5px;
	}
	#lar_bank_account {
		margin-left:-27px;
		padding:5px;
	}
	#lar_rate {
		margin-left:-15px;
		padding:5px;
	}
	#sxsxfjTableStyle {
		width: 1130px;   
	}
	#sxsxfjTableStyle .trContent td {
		border : black 1px solid;
	}
	.firstTr{
		BORDER-RIGHT: black 1px solid;  
		BORDER-TOP:  #000000 0px solid;  
		BORDER-LEFT: black 1px solid;
		BORDER-BOTTOM: black 1px solid;
		border:1px solid;
	}
	
	#sxsxsjxTableStyle {
		width: 1130px;   
	}
	#sxsxsjxTableStyle .trContent td {
		border : black 1px solid;
	}
	
	#sljgTableStyle {
		width: 1130px;   
	}
	#sljgTableStyle .trContent td {
		border : black 1px solid;
	}
	
	#ssxxTableStyle {
		width: 1130px;   
	}
	#ssxxTableStyle .trContent td {
		border : black 1px solid;
	}
	#lar_contract {
		margin-left : 76px;
	}
	
	table tr td:nth-child(1) {
		width: 200px;
	}
	table tr td:nth-child(3) {
		width: 200px;
	}
	#dksqxxTableStyle {
		border : black 1px solid;
		width: 1130px;
	}
	#dksqxxTableStyle tr td {
		border : black 1px solid;
	}
	#dksqxxTableStyle tr td:nth-child(2) {
		width: 368px;
	}
	#sxyjTableStyle {
		width: 1129px;   
	}
	.firstTr{
		BORDER-RIGHT: black 1px solid;  
		BORDER-TOP:  #000000 0px solid;  
		BORDER-LEFT: black 1px solid;
		BORDER-BOTTOM: black 1px solid;
	}
	#sxyjTableStyle .trContent td {
		border : black 1px solid;
	}
	.aaaa{
		color:red;
	}

</style>
<div style="margin-left: auto; margin-right: auto; width: 1160px;">
<td class="slspdTdTitle" colspan="2">授信审批单</td>
<div style="height:10px;"></div>
<div style="width:1130px">
<table id="dksqxxTableStyle" >
	<tr style="background-color:  #00A2CA;">
		<td class="slspdTdTitle" colspan="4">贷款申请信息</td>
	</tr>
	<tr style="text-align: center;">
		<td class="slspdTdContent">企业名称：</td>
		<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.qymc}</td>
		<td class="slspdTdContent">纳税人识别号：</td>
		<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsrsbh }</td>
	</tr>
	<tr style="text-align: center;">
		<td class="slspdTdContent">法定代表人名称：</td>
		<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frmc }</td>
		<td class="slspdTdContent">法定代表人手机号码：</td>
		<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frsjh }</td>
	</tr>
	<tr style="text-align: center;">
		<td class="slspdTdContent">纳税人注册地址：</td>
		<td class="slspdTdContent" >&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.zcdz }</td>
		<td class="slspdTdContent">成立时间：</td>
		<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.djrq}</td>
	</tr>
	<tr style="text-align: center;">
		<td class="slspdTdContent">行业类型：</td>
		<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.hymc}</td>
		<td class="slspdTdContent">经营范围：</td>
		<td class="slspdTdContent" >${loanApproveQueryEntity.nsryhxxEntity.jyfw}</td>
	</tr>
	<tr style="text-align: center;">
		<td class="slspdTdContent">产品名称：</td>
		<td class="slspdTdContent">${loanApproveQueryEntity.financialProduct.fpName }</td>
		<td class="slspdTdContent">申请授信额度：</td>
		<td class="slspdTdContent">${loanApproveQueryEntity.la_amount}<span>万元</span></td>
	</tr>
	<tr style="text-align: center;">
		<td class="slspdTdContent">期望贷款期限：</td>
		<td class="slspdTdContent">${loanApproveQueryEntity.la_repay_loan_deadline }<span>个月</span></td>
		<td class="slspdTdContent">期望还款方式：</td>
		<td class="slspdTdContent">
<!-- 		${loanApproveQueryEntity.repaymentWayEntity.rwname } -->

			<c:forEach items="${repaymentWayList }" var="varWay">
				<c:forEach items="${hkfsArray }" var="var">
					<c:if test="${varWay.id eq var }">
						 ${varWay.rwname }<br/>
					</c:if>
				</c:forEach>
			</c:forEach>
		</td>
	</tr>
</table>

<table id="sxsxfjTableStyle" >
		<tr class="firstTr" style="background-color:  #00A2CA;">
			<td class="slspdTdTitle" colspan="2">授信所需附件</td>
		</tr>
		<tr class="trContent" style="text-align: center;">
			<td class="slspdTdContent">授信所需附件：</td>
			<td class="slspdTdContent" style="text-align: center;">
				<c:forEach var="loanApplyAttach" items="${loanApplyAttachList }">
				<c:if test="${loanApplyAttach.loanProductDataEntity.pdiName != null}">
					<%-- <a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/> --%>
				<a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/>
				</c:if>
				</c:forEach>
			</td>
		</tr>
    </table>
    
    <table id="sxsxsjxTableStyle">	
		<tr class="sxsxsfirstTr" style="background-color:  #00A2CA;">
			<td class="slspdTdTitle" style="text-align: left;" colspan="4">授信所需数据项</td>
		</tr>

	</table>
    
	<table  id="ssxxTableStyle">
		<tr class="firstTr" style="background-color:  #00A2CA;">
			<td class="slspdTdTitle" colspan="2">企业涉税信息</td>
		</tr>
		<tr class="trContent" style="text-align: center;">
			<td class="slspdTdContent" >企业涉税信息报告</td>
			<c:choose>
				<c:when test="${loanApproveQueryEntity.la_status =='DKZT01' or loanApproveQueryEntity.la_status == 'DKZT02' or loanApproveQueryEntity.la_status == 'DKZT08'}">
					<td class="slspdTdContent" style="text-align:center;"><a href="#" onclick="downloadFile()">下载</a></td>
				</c:when>
				<c:otherwise>
					<td class="slspdTdContent" style="text-align:center;" >下载</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
		<table id="sljgTableStyle">
		<tr class="firstTr" style="background-color:  #00A2CA;">
		<td class="slspdTdTitle" colspan="2">贷款受理结果</td>
		</tr>
		<tr class="trContent" style="text-align: center;">
			<td class="slspdTdContent">审核结果：</td>
			<td class="slspdTdContent" style="text-align:center;" ><c:if test = "${approverec.lac_id == 'YDKZT02'}">受理通过</c:if><c:if test = "${approverec.lac_id == 'YDKZT06'}">受理不通过</c:if></td>
		</tr>
		<tr class="trContent" style="text-align: center;" >
			<td class="slspdTdContent">审核意见：</td>
			<td class="slspdTdContent" style="text-align:center;">${approverec.lar_opinion}</td>
		</tr>
	</table>
<form id="loanApproveForm" name="loanApproveForm">
	<div  style="display:none" >
		<input name="qymc" id="qymc" value="${loanApproveQueryEntity.nsryhxxEntity.qymc}" />
		<input name="frsjh" id="frsjh" type="text" value="${loanApproveQueryEntity.nsryhxxEntity.frsjh}" />
		<input name="dksqsj" value ="${loanApproveQueryEntity.la_apply_time}"/>
		<input name="sqyh" value="${loanApproveQueryEntity.financialProduct.orgName}"/>
		<input name="sqcp" value="${loanApproveQueryEntity.financialProduct.fpName}"/>

	</div>
	<!-- 列表分页参数 -->
	<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/>
	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/>
	<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }/">
	<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
	<!-- 贷款订单参数 -->
	<input type="hidden" id="la_id" name="la_id" value="${loanApproveQueryEntity.id }"/>
	<input type="hidden" id="fp_id" name="fp_id" value="${loanApproveQueryEntity.fp_id }"/>
	<input type="hidden" id="las_id" name="las_id" 
	<c:if test="${loanApproveQueryEntity.la_status != 'DKZT08'}"> value="${loanApproveQueryEntity.la_status}"</c:if>
	<c:if test="${loanApproveQueryEntity.la_status == 'DKZT08'}"> value="${loanApproveQueryEntity.las_status}"</c:if>/>
    <input type="hidden" id="approverecAmount" value="${approverecAmount}"/>
	<input type="hidden" id="approverecdeadline" value="${approverecdeadline}"/>
		<!-- 下载报告所需要的参数 -->
	<input type="hidden" id="id" name="id" value="${loanApproveQueryEntity.id}"/>
	<input type="hidden" id="creatorid"  name="creatorid" value="${loanApproveQueryEntity.creatorid}"/>
	<input type="hidden" id="lasid"  name="lasid" value="${loanApproveQueryEntity.la_status}"/>
	<input type="hidden" id="qymc"  name="qymc" value="${loanApproveQueryEntity.nsryhxxEntity.qymc}"/>
	<table id="sxyjTableStyle">
		<tr class="firstTr" style="background-color: #00A2CA;">
			<td class="slspdTdTitle" colspan="4">授信结果</td>
		</tr>
		<tr class="trContent">
			<td class="sxspdTdContent" ><b style="color:red;margin-right:5px;">*</b>审核结果</td>
			<!-- <td id="shjgStyle" class="sxspdTdContent" colspan="3"><label><input id="lac_id" name="lac_id" type="radio" checked="checked" onclick="getFormData()" value="3"/>授信</label><label><input id="lac_id" name="lac_id" type="radio" onclick="getFormData()" value="4"/>拒绝授信</label><label><input id="lac_id" name="lac_id" type="radio" onclick="getFormData()" value="10"/>退单</label></td> -->
		<td id="shjgStyle" class="sxspdTdContent" colspan="3">
			<c:if test = "${loanApproveQueryEntity.la_status=='DKZT08'}">
				<label><input id="lac_id" name="lac_id" type="radio" checked="checked" onclick="getFormData()" value="YDKZT09"/>撤销</label>
			</c:if>
			<c:if test = "${loanApproveQueryEntity.la_status !='DKZT08'}">
				<label><input id="lac_id" name="lac_id" type="radio" checked="checked" onclick="getFormData()" value="YDKZT03"/>受理</label>
				<label><input id="lac_id" name="lac_id" type="radio" onclick="getFormData()" value="YDKZT04"/>拒绝受理</label>
				<label><input id="lac_id" name="lac_id" type="radio" onclick="getFormData()" value="YDKZT10"/>退单</label>
			</c:if>
		</tr>
		<tr class="trContent">
			<td class="sxspdTdContent"  >授信意见</td>
			<td style="text-align:center" colspan="3"><textarea id="lar_opinion" name="lar_opinion" style="height:50px;padding-left:437px;padding-top:15px;width: 900px;BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;">同意</textarea></td>
		</tr>
		<tr class="trContent">
			<td class="sxspdTdContent" ><b style="color:red;margin-right:5px;">*</b>授信额度</td>
			<td class="sxspdTdContent2" ><input id="lar_credit_quota" name="lar_credit_quota" type="text" class="input" style="width: 100px;" value="${approverec.lar_credit_quota == 0 ? loanApproveQueryEntity.la_amount : approverec.lar_credit_quota }" maxlength="11"/>万元</td>
			<td class="sxspdTdContent" ><b style="color:red;margin-right:5px;">*</b>授信利率</td>
			<td class="" ><input id="lar_rate" name="lar_rate" style="width: 100px;margin-left: 100px;" type="text" class="input" value=""  maxlength="5"/>%</td>
		</tr>
		
		<tr class="trContent" style="display: none;">
			<td class="sxspdTdContent"  > 产品名称</td>
			<td class="sxspdTdContent2" >${loanApproveQueryEntity.financialProduct.fpName }</td>
			<td class="sxspdTdContent" ><b style="color:red;margin-right:5px;">*</b>授信额度</td>
			<td class="sxspdTdContent" ><input id="lar_credit_quota" name="lar_credit_quota" style="width: 180px;" type="text" class="input" value="${approverec.lar_credit_quota == 0 ? loanApproveQueryEntity.la_amount : approverec.lar_credit_quota }" maxlength="11"/>万元</td>
			
		</tr>
		<tr class="trContent" style="display: none">
			<td class="sxspdTdContent" >借款方开户银行</td>
			<td class="sxspdTdContent"><input id="lar_bank_name" name="lar_bank_name" type="text" class="input"  value="${approverec.lar_bank_name}"/></td>
			<td class="sxspdTdContent" >借款方银行账号</td>
			<td class="sxspdTdContent" ><input id="lar_bank_account" name="lar_bank_account" type="text" class="input"  value="${approverec.lar_bank_account}"/></td>
		</tr>
		<tr class="trContent" style="display: none">
			<td class="sxspdTdContent" >借款合同号</td>
			<td class="sxspdTdContent"  style="text-align:left;"colspan="3"><input id="lar_contract" name="lar_contract" type="text" class="input"  value="${approverec.lar_contract}"/></td>
		</tr>
		<tr class="trContent">
			<td class="sxspdTdContent" ><b style="color:red;margin-right:5px;">*</b>授信期限起</td>
			<td class="sxspdTdContent2" ><input id="lar_begin" name="lar_begin" type="text" class="input" style="border:0px;width: 100px;" readonly="readonly" value="<fmt:formatDate  value="${approverec.lar_begin }" type="both" pattern="yyyy-MM-dd" />" onblur="changemonth(this)"/></td>
			<td class="sxspdTdContent" ><b style="color:red;margin-right:5px;">*</b>授信期限止</td>
			<td class="" ><input id="lar_end" name="lar_end" type="text" class="input" style="border:0px;width:100px;margin-left:100px;" readonly="readonly" <%-- value="<fmt:formatDate  value="${approverec.lar_end }" type="both" pattern="yyyy-MM-dd" />" --%> onblur="changemonth(this)"/></td>
		</tr>
		<tr class="trContent">
			<td class="sxspdTdContent" >授信期限</td>
			<td class="sxspdTdContent2" ><input id="lar_loan_deadline" name="lar_loan_deadline"  readonly="readonly" type="text" class="input" style="width:100px;margin-left: 0px;" maxlength="11" />&nbsp;个月</td>
					<td  class="sxspdTdContent" >还款方式</td>
			<td id="hkfsStyle" class="sxspdTdContent"  colspan="3">
				<c:forEach var="repaymentway" items="${repaymentWayList }">
					<c:forEach items="${hkfsYhArray }" var="yhVar">
						<c:if test="${repaymentway.id eq yhVar }">
							 <span style="display:inline-block; width:360px; "><label><input id="rw_id" name="rw_id" type="radio"  value="${repaymentway.id}"/>${repaymentway.rwname}</label></span>
						</c:if>
					</c:forEach>
				</c:forEach>
			</td>
		</tr>
	</table>
	</form>
	</div>
</div><br/>

<div class="center" style="margin-top:10px;">
	<button id="btnAdd" type="button" onclick="validateForm()" style="background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;"><i class="fa fa-user-plus"></i>保存</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/loanapprove/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }&status=${loanApproveQueryEntity.la_status}')" style="margin-left:80px;margin-right:60px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;"><i class="fa fa-undo"></i>&nbsp;返回</button>
</div>
<script type="text/javascript"> 
$(function(){
	$('#sxsxsjxTableStyle').find('tr:eq(0)').after('${tbody}');
	$("#sxsxsjxTableStyle tr td").addClass("tdClss");
});
</script>