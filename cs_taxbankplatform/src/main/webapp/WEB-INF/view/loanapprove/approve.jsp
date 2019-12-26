<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapprove/form.js"></script>
<style type="text/css">
	tr {height: 30px;}
	.tr-height{height: 40px;}
	table tr td:nth-child(1) {
		width: 250px;
		
	}
	.slspdTdContent {
		text-align:center;
		height:25px;
	}
	input{
		height:25px;
	}
	.slspdTdTitle {
		color:white;
		font-size:17px;
		height:32px;
		padding-left:10px
	}
	#dksqxxTableStyle {
		border : black 1px solid;
		width: 1130px;
	}
	#dksqxxTableStyle tr td {
		border : black 1px solid;
	}
	#dksqxxTableStyle tr td:nth-child(2) {
		width: 310px;
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
		width: 1129px;   
	}
	#sxsxsjxTableStyle .trContent td {
		border : black 1px solid;
	}
	.tdClss{
		text-align:center;
		border:1px solid black;
	}
	#sljgTableStyle {
		width: 1130px;   
	}
	#sljgTableStyle .trContent td {
		border : black 1px solid;
	}
	#sqjgTableStyle {
		width: 1130px;   
	}
	#sqjgTableStyle .trContent td {
		border : black 1px solid;
	}

	#ssxxTableStyle {
		width: 1130px;   
	}
	#ssxxTableStyle .trContent td {
		border : black 1px solid;
	}
	#lac_id {
		height:10px;
		margin-left : 150px;
		margin-right:10px;
		padding:0px;
	}
	#lar_contract {
		margin-left : 76px;
	}
</style>

<div class="row" style="margin-top:5px;">
<h2 style="text-align:center">受理审批单</h2>
<div style="height:10px;"></div>
<div style="width:1130px">
<table id="dksqxxTableStyle" >
		<tr style="background-color:  #00A2CA;">
			<td class="slspdTdTitle"colspan="4" >贷款申请信息</td>
		</tr>
		<tr>
			<td class="slspdTdContent" style="text-align:center; width:22.2% ">企业名称：</td>
			<td class="slspdTdContent" style="text-align:center; width:27.8% ">${loanApproveQueryEntity.nsryhxxEntity.qymc}</td>
			<td class="slspdTdContent" style="text-align:center; width:22.2% ">纳税人识别号：</td>
			<td class="slspdTdContent" style="text-align:center; width:27.8% " >&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsrsbh }</td>
			<%-- <td class="slspdTdContent">注册资本：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.zczb}<span>万元</span></td> --%>
		</tr>
		<tr>
			<td class="slspdTdContent" style="text-align:center;">法定代表人名称：</td>
			<td class="slspdTdContent" style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frmc }</td>
			<td class="slspdTdContent" style="text-align:center;">法定代表人手机号码：</td>
			<td class="slspdTdContent" style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frsjh }</td>
		</tr>
		<tr>
			<td class="slspdTdContent" style="text-align:center;">纳税人注册地址：</td>
			<td class="slspdTdContent" style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.zcdz }</td>
			<td class="slspdTdContent" style="text-align:center;">成立时间：</td>
			<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.djrq}</td>
			<%-- <td class="slspdTdContent">注册资本：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.zczb}<span>万元</span></td> --%>
		</tr>
		<tr>
			<td class="slspdTdContent" style="text-align:center;">行业类型：</td>
			<td class="slspdTdContent"style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.hymc}</td>
			<td class="slspdTdContent" style="text-align:center;">经营范围：</td>
			<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.jyfw}</td>
			<%-- <td class="slspdTdContent">成立时间：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.djrq}</td> --%>
		</tr>
		<%-- <tr>
			<td class="slspdTdContent">经营范围：</td>
			<td class="slspdTdContent" colspan="3">${loanApproveQueryEntity.nsryhxxEntity.jyfw}</td>
		</tr> --%>
		<tr>
			<td class="slspdTdContent" style="text-align:center;">产品名称：</td>
			<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.financialProduct.fpName }</td>
			<td class="slspdTdContent" style="text-align:center;">申请授信额度：</td>
			<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.la_amount}<span>万元</span></td>
		</tr>
		<tr>
			<td class="slspdTdContent" style="text-align:center;">期望贷款期限：</td>
			<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.la_repay_loan_deadline }<span>个月</span></td>
			<td class="slspdTdContent" style="text-align:center;">期望还款方式：</td>
			<td class="slspdTdContent" style="text-align:center;">
<!-- 			${loanApproveQueryEntity.repaymentWayEntity.rwname } -->
			
			<c:forEach items="${repaymentWayList }" var="varWay">
				<c:forEach items="${hkfsArray }" var="var">
					<c:if test="${varWay.id eq var}">
						 ${varWay.rwname }<br/>
					</c:if>
				</c:forEach>
			</c:forEach>
			</td>
		</tr>
</table>
<table id="sxsxfjTableStyle" >
		<tr class="firstTr" style="background-color: #00A2CA;">
			<td class="slspdTdTitle" colspan="2">授信所需附件</td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent">授信所需附件：</td>
			<td class="slspdTdContent">
				<c:forEach var="loanApplyAttach" items="${loanApplyAttachList }">
				<c:if test="${loanApplyAttach.loanProductDataEntity.pdiName != null}">
					<%-- <a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/> --%>
				     <a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/>
				</c:if>
				</c:forEach>
			</td>
		</tr>
</table>
	<form id="loanApproveForm" name="loanApproveForm">
	<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/>
	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/>
	<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }/">
	<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
	<!-- 贷款订单参数 -->
	<input type="hidden" id="la_id" name="la_id" value="${loanApproveQueryEntity.id }"/>
	<input type="hidden" id="fp_id" name="fp_id" value="${loanApproveQueryEntity.fp_id }"/>
	<input type="hidden" id="las_id" name="las_id"  value=
	<c:if test="${loanApproveQueryEntity.la_status != 'DKZT08'}">"${loanApproveQueryEntity.la_status}"</c:if>
	<c:if test="${loanApproveQueryEntity.la_status == 'DKZT08'}">"${loanApproveQueryEntity.las_status}"</c:if>/>
    <input type="hidden" id="approverecAmount" value="${approverecAmount}"/>
	<input type="hidden" id="approverecdeadline" value="${approverecdeadline}"/>
	<!-- 下载报告所需要的参数 -->
	<input type="hidden" id="id" name="id" value="${loanApproveQueryEntity.id}"/>
	<input type="hidden" id="creatorid"  name="creatorid" value="${loanApproveQueryEntity.creatorid}"/>
	<input type="hidden" id="lasid"  name="lasid" value="${loanApproveQueryEntity.la_status}"/>
	<input type="hidden" id="qymc"  name="qymc" value="${loanApproveQueryEntity.nsryhxxEntity.qymc}"/>
	<table id="sxsxsjxTableStyle">	
		<tr class="firstTr" style="background-color:  #00A2CA;">
			<td class="slspdTdTitle" style="text-align: left;" colspan="4">授信所需数据项</td>
		</tr>
		<%-- <tr style="background-color: #00A2CA">
			<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;申请单号：${loanApproveQueryEntity.la_serial_number }</td>
		</tr> --%>
		<%-- 		<tr class="trContent">
			<td class="slspdTdContent">产品名称：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.financialProduct.fpName }</td>
			<td class="slspdTdContent">期望的贷款方式：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.repaymentWayEntity.rwname }</td>
		</tr>
		
		<tr class="trContent">
			<td class="slspdTdContent">申请授信额度：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.la_amount }万</td>
			<td class="slspdTdContent">期望的贷款期限：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;<input id="lar_loan_deadline" readonly="readonly" name="lar_loan_deadline" type="text" class="input" style="border:0px;" value="${loanApproveQueryEntity.la_repay_loan_deadline}" maxlength="11" onblur="changemonth();" />个月</td>
		    <td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.la_repay_loan_deadline}个月</td>
		</tr> --%>
		<tr class="trContent" style="display: none">
			<td class="slspdTdContent"><b style="color:red;margin-right:5px;">*</b>受理期限起:</td>
			<td class="slspdTdContent"><input id="lar_begin" name="lar_begin" type="text" class="input" style="border:0px;" value=""/></td>
			<td class="slspdTdContent"><b style="color:red;margin-right:5px;">*</b>受理期限止:</td>
			<td class="slspdTdContent"><input id="lar_end" name="lar_end" type="text" class="input" style="border:0px;"  value=""/></td>
		</tr>
		<%-- <tr class="trContent">
			<td class="slspdTdContent">纳税人名称：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.nsrmc }</td>
			<td class="slspdTdContent">纳税人识别号：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsrsbh }</td>
		</tr>
		
		<tr class="trContent">
			<td class="slspdTdContent">法定代表人名称：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frmc }</td>
			<td class="slspdTdContent">法定代表人手机号码：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frsjh }</td>
		</tr>--%>
		
		<tr class="trContent" style="display: none">
			<td class="slspdTdContent">电话：</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.zcsjh}</td>
			<td class="slspdTdContent" >贷款利率：&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td class="slspdTdContent" >&nbsp;&nbsp;&nbsp;&nbsp;<input id="lar_rate" name="lar_rate" type="text" class="input"  value="${loanApproveQueryEntity.lar_rate == null ? loanApproveQueryEntity.lar_rate:'0'}"/><span>%</span></td>
			<%-- <td align="right">传真：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.nsrcz }</td> --%>
		</tr>
		<%-- <tr class="trContent" style="display: none">
			<td class="slspdTdContent">借款方开户银行：&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;<input id="lar_bank_name" name="lar_bank_name" type="text" class="input"   value=""/></td>
			<td class="slspdTdContent">借款方银行账号：&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;<input id="lar_bank_account" name="lar_bank_account" type="text" class="input"   value=""/></td>
		</tr>
		<tr class="trContent" style="display: none">
			<td class="slspdTdContent">借款合同号：&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td style="text-align:left;" colspan="3"><input id="lar_contract" name="lar_contract" type="text" class="input"   value=""/></td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent">纳税人注册地址：</td>
			<td class="slspdTdContent" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.zcdz }</td>
		</tr>
		
		<tr class="trContent">
			<td class="slspdTdContent">产品覆盖区域：</td>
			<td class="slspdTdContent" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;<c:forEach var="cityEntities" items="${cityEntities }"><span>${cityEntities.pcname}&nbsp;&nbsp;</span></c:forEach></td>
		</tr>
		<tr>
			<td align="right">授信所需附件：</td>
			<td align="left" colspan="3">
				<c:forEach var="loanApplyAttach" items="${loanApplyAttachList }">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/>
				</c:forEach>
			</td>
		</tr>
		<tr class="trContent" style="display: none">
			<td class="slspdTdContent">主管税务机关名称：</td>
			<td class="slspdTdContent" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;广东省深圳市地方税务局保税分局</td>
		</tr> --%>
		<!-- <tr>
			<td align="center">企业涉税信息：</td>
			<td align="center" colspan="3">
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业税务稽查信息</a><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业近五年信用等级评定信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业纳税申报信息</a><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">财务报表信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业违法违章信息</a>
			</td>
		</tr> -->
	</table>
	<table id="ssxxTableStyle">
		<tr class="firstTr" style="background-color:  #00A2CA;">
			<td class="slspdTdTitle" colspan="2">企业涉税信息</td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent">企业涉税信息报告</td>
			<td class="slspdTdContent"><a href="#" onclick="downloadFile()">下载</a></td>
		</tr>
	</table>
	<!-- 贷款申请审批结果 -->
	<c:if test="${loanApproveQueryEntity.la_status == 'DKZT02'}">
			<div  style="margin-left: auto; margin-right: auto; width: 1160px; border: 1px solid #00A2CA;">
			
				<table id="sqjgTableStyle" >
					<tr class="firstTr" style="background-color: #00A2CA">
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;初审结果</td>
						<td class="slspdTdContent" colspan="2">初审人：${approverec.userEntity.userName }</td>
						<td class="slspdTdContent">初审时间：<fmt:formatDate  value="${approverec.createtime }" type="both" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					
					<tr class="trContent">
						<td class="slspdTdContent">授信额度：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.lar_credit_quota }万</td>
						<td class="slspdTdContent">贷款期限：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.lar_loan_deadline }个月</td>
					</tr>
					
					<tr class="trContent">
						<td class="slspdTdContent">贷款利率：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.lar_rate }%</td>
						<td class="slspdTdContent">贷款期限止：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate  value="${approverec.lar_begin }" type="both" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate  value="${approverec.lar_end }" type="both" pattern="yyyy-MM-dd"/></td>
					</tr>
					
					<tr class="trContent">
						<td class="slspdTdContent">是否在产品覆盖区域：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${approverec.lar_isoverlay_area == 1}">是</c:if><c:if test="${approverec.lar_isoverlay_area == 0}">否</c:if></td>
						<td class="slspdTdContent">还款方式：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.repaymentWayEntity.rwname }</td>
					</tr>
					
					<tr class="trContent">
						<td class="slspdTdContent">借款方开户银行：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.lar_bank_name }</td>
						<td class="slspdTdContent">借款方银行账号：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.lar_bank_account }</td>
					</tr>
					
					<tr class="trContent">
						<td class="slspdTdContent">借款合同号：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.lar_contract }</td>
					</tr>
					
					<tr class="trContent">
						<td class="slspdTdContent">审批结果：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.loanCodeEntity.lac_name }</td>
					</tr>
					
					<tr class="trContent">
						<td class="slspdTdContent">审批意见：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="slspdTdContent" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${approverec.lar_opinion }</td>
					</tr>
				</table>
			</div><br/>
	</c:if>	
	<!-- 贷款申请录入(初审/终审结果表单) -->
	<div style="margin-left: auto; margin-right: auto; width: 1160px;">
		<!-- <form id="loanApproveForm" name="loanApproveForm"> -->
			<!-- 列表分页参数 -->
			<table id="sljgTableStyle" >
				<tr class="firstTr" style="background-color:  #00A2CA;">
				<td class="slspdTdTitle" colspan="2">贷款受理结果</td>
				</tr>
				<tr class="trContent">
					<td class="slspdTdContent">审核结果：</td>
					<td style="text-align:left;">
					<c:if test = "${loanApproveQueryEntity.la_status=='DKZT08'}">
						<label><input id="lac_id" name="lac_id" type="radio" checked="checked" onclick="getFormData()" value="YDKZT09"/>撤销</label>
					</c:if>
					<c:if test = "${loanApproveQueryEntity.la_status !='DKZT08'}">
						<label><input id="lac_id" name="lac_id" type="radio" checked="checked" onclick="getFormData()" value="YDKZT02"/>受理</label>
						<label><input id="lac_id" name="lac_id" type="radio" onclick="getFormData()" value="YDKZT06"/>拒绝受理</label>
						<label><input id="lac_id" name="lac_id" type="radio" onclick="getFormData()" value="YDKZT10"/>退单</label>
					</c:if>
					</td>
				</tr>
				
				<tr class="trContent">
					<td class="slspdTdContent">审核意见：</td>
					<td class="slspdTdContent"><textarea id="lar_opinion" name="lar_opinion" style="height:50px;text-align:center;padding-top:15px;width: 870px;BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;">同意</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
</div><br/>
<div class="center">
	<button id="btnAdd" type="button" onclick="validateForm()" style="background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;"><i class="fa fa-user-plus" ></i>保存</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/loanapprove/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }&status=${loanApproveQueryEntity.la_status}')" style="margin-left:80px;margin-right:60px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;"><i class="fa fa-undo"></i>&nbsp;返回</button>
</div>
<script type="text/javascript"> 
$(function(){
	$('#sxsxsjxTableStyle').find('tr:eq(0)').after('${tbody}');
	$("#sxsxsjxTableStyle tr td").addClass("tdClss");
});
</script>
