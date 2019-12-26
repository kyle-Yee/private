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
	.slspdTdTitle {
		color:white;
		font-size:17px;
		height:32px;
		padding-left:10px
	}
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
	#dksqxxTableStyle {
		border : black 1px solid;
		width: 1130px;
	}
	#dksqxxTableStyle tr td {
		border : black 1px solid;
	}
	.firstTr{
		BORDER-RIGHT: black 1px solid;  
		BORDER-TOP:  #000000 0px solid;  
		BORDER-LEFT: black 1px solid;
		BORDER-BOTTOM: black 1px solid;
	}
	#dksqxxTableStyle tr td:nth-child(2) {
		width: 280px;
	}
	#sxsxsjxTable tr td:nth-child(2) {
		width: 280px;
	}
	#sxsxsjxTable tr td:nth-child(3) {
		width: 315px;
	}
	#sxsxsjxTable {
		width: 1130px;   
	}
	.tdClss{
		text-align:center;
		border:1px solid black;
	}
	table tr .tdClss:nth-child(1) {
		width: 250px;
		
	}
	#sxsxsjxTable .trContent td {
		border : black 1px solid;
	}
	#ssxxTableStyle {
		width: 1130px;   
	}
	#ssxxTableStyle .trContent td {
		border : black 1px solid;
	}
	#sljgTableStyle{
		width: 1130px;   
	}
	#sljgTableStyle .trContent td {
		border : black 1px solid;
	}
	#sxsxfjTableStyle {
		width: 1130px;   
	}
</style>

<div class="row" style="margin-top:5px;">
<h2 style="text-align:center">受理审批单</h2>
<div style="height:10px;"></div>
<div style="width:1130px">
<table id="dksqxxTableStyle" >
		<tr style="background-color: #00A2CA;">
			<td class="slspdTdTitle"colspan="4">贷款申请信息</td>
		</tr>
		<tr>
			<td class="slspdTdContent" style="text-align:center;  width:22.2%">企业名称：</td>
			<td class="slspdTdContent" style="text-align:center; width:27.8% ">${loanApproveQueryEntity.nsryhxxEntity.qymc}</td>
			<td class="slspdTdContent" style="text-align:center;  width:22.2%">纳税人识别号：</td>
			<td class="slspdTdContent" style="text-align:center; width:27.8% ">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsrsbh }</td>
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
			<td class="slspdTdContent" style="text-align:center;" >&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.zcdz }</td>
			<td class="slspdTdContent" style="text-align:center;">成立时间：</td>
			<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.djrq}</td>
			<%-- <td class="slspdTdContent">注册资本：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.zczb}<span>万元</span></td> --%>
		</tr>
		<tr>
			<td class="slspdTdContent" style="text-align:center;">行业类型：</td>
			<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.hymc}</td>
			<td class="slspdTdContent" style="text-align:center;">经营范围：</td>
			<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.jyfw}</td>
			<%-- <td class="slspdTdContent">成立时间：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.djrq}</td> --%>
		</tr>
		<%-- <tr>
			<td class="slspdTdContent">行业类型：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.hymc}</td>
			<td class="slspdTdContent">成立时间：</td>
			<td class="slspdTdContent"><fmt:formatDate value="${loanApproveQueryEntity.nsryhxxEntity.djrq}" pattern="yyyy-MM-dd:HH:mm:ss" /></td>
			<td class="slspdTdContent">${loanApproveQueryEntity.nsryhxxEntity.djrq}</td>
		</tr> --%>
		<tr>
			<td class="slspdTdContent">产品名称：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.financialProduct.fpName }</td>
			<td class="slspdTdContent">申请授信额度：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.la_amount}<span>万元</span></td>
		</tr>
		<%-- <tr>
			<td class="slspdTdContent">经营范围：</td>
			<td class="slspdTdContent" colspan="3">${loanApproveQueryEntity.nsryhxxEntity.jyfw}</td>
		</tr> --%>
		<tr>
			<td class="slspdTdContent">期望贷款期限：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.la_repay_loan_deadline }<span>个月</span></td>
			<td class="slspdTdContent">期望还款方式：</td>
			<td class="slspdTdContent">
<!-- 			${loanApproveQueryEntity.repaymentWayEntity.rwname } -->
				<c:forEach items="${repaymentWayList }" var="varWay">
					<c:forEach items="${ hkfsArray}" var="var">
						<c:if test="${varWay.id eq var }">
							 ${varWay.rwname }<br/>
						</c:if>
					</c:forEach>
				</c:forEach>
			</td>
		</tr>
		<%-- <tr>
			<td class="slspdTdContent">产品名称：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.financialProduct.fpName }</td>
			<td class="slspdTdContent">申请授信额度：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.la_amount}<span>万元</span></td>
		</tr>
		<tr>
			<td class="slspdTdContent">期望贷款期限：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.la_repay_loan_deadline }<span>个月</span></td>
			<td class="slspdTdContent">期望还款方式：</td>
			<td class="slspdTdContent">${loanApproveQueryEntity.repaymentWayEntity.rwname }</td>
		</tr> --%>
</table>
<table id="sxsxfjTableStyle" >
		<tr class="firstTr" style="background-color: #00A2CA;">
			<td class="slspdTdTitle" colspan="4">授信所需附件</td>
		</tr>
		<tr class="trContent">
			<td class="tdClss">授信所需附件：</td>
			<td class="tdClss">
				<c:forEach var="loanApplyAttach" items="${loanApplyAttachList }">
				<c:if test="${loanApplyAttach.loanProductDataEntity.pdiName != null}">
					<%-- <a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/> --%>
				     <a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/>
				</c:if>
				</c:forEach>
			</td>
		</tr>
</table>
<table id="sxsxsjxTable">
		
		<tr class="firstTr" style="background-color: #00A2CA;">
			<td class="slspdTdTitle" colspan="4" style="text-align: left;">授信所需数据项</td>
		</tr>
		
		<tr class="trContent" style="display: none">
			<td class="slspdTdContent"><b style="color:red;margin-right:5px;">*</b>受理期限起:</td>
			<td class="slspdTdContent"><input id="lar_begin" name="lar_begin" type="text" class="input" style="border:0px;" value=""/></td>
			<td class="slspdTdContent"><b style="color:red;margin-right:5px;">*</b>受理期限止:</td>
			<td class="slspdTdContent"><input id="lar_end" name="lar_end" type="text" class="input" style="border:0px;"  value=""/></td>
		</tr>
	</table>
	<table  id="ssxxTableStyle">
		<tr class="firstTr" style="background-color:  #00A2CA;">
			<td class="slspdTdTitle" colspan="2">企业涉税信息</td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent" >企业涉税信息报告</td>
			<c:choose>
				<c:when test="${loanApproveQueryEntity.la_status =='DKZT01' or loanApproveQueryEntity.la_status == 'DKZT02' or loanApproveQueryEntity.la_status == 'DKZT08'}">
					<td class="slspdTdContent"><a href="#" onclick="downloadFile('${loanApproveQueryEntity.id},${loanApproveQueryEntity.creatorid}')">下载</a></td>
				</c:when>
				<c:otherwise>
					<td class="slspdTdContent">下载</td>
				</c:otherwise>
			</c:choose>
		</tr>
</table>
	<div style="margin-left: auto; margin-right: auto; width: 1160px;">
		<form id="loanApproveForm" name="loanApproveForm">
			<!-- 列表分页参数 -->
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/>
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/>
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }/">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
			<!-- 贷款订单参数 -->
			<input type="hidden" id="la_id" name="la_id" value="${loanApproveQueryEntity.id }"/>
			<input type="hidden" id="fp_id" name="fp_id" value="${loanApproveQueryEntity.fp_id }"/>
			<input type="hidden" id="las_id" name="las_id" value="${loanApproveQueryEntity.la_status }"/>
		    <input type="hidden" id="approverecAmount" value="${approverecAmount}"/>
			<input type="hidden" id="approverecdeadline" value="${approverecdeadline}"/>
				<!-- 下载报告所需要的参数 -->
	<input type="hidden" id="id" name="id" value="${loanApproveQueryEntity.id}"/>
	<input type="hidden" id="creatorid"  name="creatorid" value="${loanApproveQueryEntity.creatorid}"/>
	<input type="hidden" id="lasid"  name="lasid" value="${loanApproveQueryEntity.la_status}"/>		
	<input type="hidden" id="qymc"  name="qymc" value="${loanApproveQueryEntity.nsryhxxEntity.qymc}"/>		
			<table id="sljgTableStyle">
				<tr class="firstTr" style="background-color:  #00A2CA;">
				<td class="slspdTdTitle" colspan="2">贷款受理结果</td>
				</tr>
				<tr class="trContent">
					<td class="slspdTdContent">审核结果：</td>
					<td class="slspdTdContent"><c:if test = "${approverec.lac_id == 'YDKZT02'}">受理通过</c:if><c:if test = "${approverec.lac_id == 'YDKZT06'}">受理不通过</c:if><c:if test = "${approverec.lac_id == 'YDKZT10'}">退单</c:if></td>
				</tr>
				<tr class="trContent" >
					<td class="slspdTdContent">审核意见：</td>
					<td class="slspdTdContent">${approverec.lar_opinion}</td>
				</tr>
			</table>
		</form>
		</div>
	</div><br/>
</div>
<div class="center">
	<button id="btn" type="button" onclick="webside.common.loadPage('/loanapprove/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }&status=${loanApproveQueryEntity.la_status}')" style="background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;"><i class="fa fa-undo"></i>&nbsp;关闭</button>
</div>
<script type="text/javascript"> 
$(function(){
	$('#sxsxsjxTable').find('tr:eq(0)').after('${tbody}');
	$("#sxsxsjxTable tr td").addClass("tdClss"); 
	$("#sxsxsjxTable tr").addClass("trContent");
});
</script>
