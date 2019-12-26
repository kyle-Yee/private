<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapprove/creditextension.js">
	</script>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<style>
	.sxspdTdTitle {
		color:white;
		font-size:17px;
		height:32px;
		padding-left:10px
	}
	.sxspdTdContent {
		text-align:center;
		height:25px;
	}
	input {
		height:25px;
	}
	#rw_id {
		height:10px;
		margin-left : 100px;
		margin-right:10px;
	}
	#hkfsStyle,#shjgStyle {
		text-align:left;
	}
	#lac_id {
		height:10px;
		margin-left : 200px;
		margin-right:10px;
		padding:0px;
	}
	#lar_credit_quota {
		padding:5px;
	}
	#lar_contract {
		margin-left:102px;
		padding:5px;
	}
	.tdClss{
		text-align:center;
		border:1px solid black;
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
		margin-left:-12px;
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
		border-right: black 1px solid;
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
	#lac_id {
		height:10px;
		margin-left : 200px;
		margin-right:10px;
		padding:0px;
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
		width: 1130px;   
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
</style>
<div style="margin-left: auto; margin-right: auto; width: 1160px;">
<h2 style="text-align:center">授信审批单</h2>
<div style="height:10px;"></div>
<div style="width:1130px">
<table id="dksqxxTableStyle" >
	<tr style="background-color: #00A2CA;">
		<td class="slspdTdTitle" colspan="4">贷款申请信息</td>
	</tr>
	
	
		<tr>
		<td class="slspdTdContent" style="text-align:center;">企业名称：</td>
		<td class="slspdTdContent"style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.qymc}</td>
		<td class="slspdTdContent"style="text-align:center;">纳税人识别号：</td>
		<td class="slspdTdContent"style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsrsbh }</td>
	</tr>
	<tr>
		<td class="slspdTdContent"style="text-align:center;">法定代表人名称：</td>
		<td class="slspdTdContent"style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frmc }</td>
		<td class="slspdTdContent"style="text-align:center;">法定代表人手机号码：</td>
		<td class="slspdTdContent"style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frsjh }</td>
	</tr>
	<tr>
		<td class="slspdTdContent"style="text-align:center;">纳税人注册地址：</td>
		<td class="slspdTdContent" style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.zcdz }</td>
		<td class="slspdTdContent"style="text-align:center;">成立时间：</td>
		<td class="slspdTdContent"style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.djrq}</td>
	</tr>
	<tr>
		<td class="slspdTdContent"style="text-align:center;">行业类型：</td>
		<td class="slspdTdContent"style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.hymc}</td>
		<td class="slspdTdContent"style="text-align:center;">经营范围：</td>
		<td class="slspdTdContent" style="text-align:center;">${loanApproveQueryEntity.nsryhxxEntity.jyfw}</td>
	</tr>
	<tr>
		<td class="slspdTdContent"style="text-align:center;">产品名称：</td>
		<td class="slspdTdContent"style="text-align:center;">${loanApproveQueryEntity.financialProduct.fpName }</td>
		<td class="slspdTdContent"style="text-align:center;">申请授信额度：</td>
		<td class="slspdTdContent"style="text-align:center;">${loanApproveQueryEntity.la_amount}<span>万元</span></td>
	</tr>
	<tr>
		<td class="slspdTdContent"style="text-align:center;">期望贷款期限：</td>
		<td class="slspdTdContent"style="text-align:center;">${loanApproveQueryEntity.la_repay_loan_deadline }<span>个月</span></td>
		<td class="slspdTdContent"style="text-align:center;">期望还款方式：</td>
		<td class="slspdTdContent"style="text-align:center;">
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
		<tr class="firstTr" style="background-color: #00A2CA;">
			<td class="slspdTdTitle" colspan="2">授信所需附件</td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent"style="text-align:center;">授信所需附件：</td>
			<td class="slspdTdContent"style="text-align:center;">
				<c:forEach var="loanApplyAttach" items="${loanApplyAttachList }">
				<c:if test="${loanApplyAttach.loanProductDataEntity.pdiName != null}">
					<a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/>
				</c:if>
				</c:forEach>
			</td>
		</tr>
</table>
	<table id="sxsxsjxTableStyle">	
		<tr class="sxsxsfirstTr" style="background-color: #00A2CA;">
			<td class="slspdTdTitle" style="text-align: left;" colspan="4">授信所需数据项</td>
		</tr>
		<tr class="trContent" style="display: none">
			<td class="slspdTdContent"style="text-align:center;"><b style="color:red;margin-right:5px;">*</b>受理期限起:</td>
			<td class="slspdTdContent"style="text-align:center;"><input id="lar_begin" name="lar_begin" type="text" class="input" style="border:0px;" value=""/></td>
			<td class="slspdTdContent"style="text-align:center;"><b style="color:red;margin-right:5px;">*</b>受理期限止:</td>
			<td class="slspdTdContent"style="text-align:center;"><input id="lar_end" name="lar_end" type="text" class="input" style="border:0px;"  value=""/></td>
		</tr>
		
		<tr class="trContent" style="display: none">
			<td class="slspdTdContent"style="text-align:center;">电话：</td>
			<td class="slspdTdContent"style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.zcsjh}</td>
			<td class="slspdTdContent"style="text-align:center;" >贷款利率：&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td class="slspdTdContent" style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;<input id="lar_rate" name="lar_rate" type="text" class="input"  value="${loanApproveQueryEntity.lar_rate == null ? loanApproveQueryEntity.lar_rate:'0'}"/><span>%</span></td>
		</tr>
	</table>
	
	<table  id="ssxxTableStyle">
		<tr class="firstTr" style="background-color: #00A2CA;">
			<td class="slspdTdTitle" colspan="2">企业涉税信息</td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent" style="text-align:center;">企业涉税信息报告</td>
			<c:choose>
				<c:when test="${loanApproveQueryEntity.la_status =='DKZT01' or loanApproveQueryEntity.la_status == 'DKZT02' or loanApproveQueryEntity.la_status == 'DKZT08'}">
					<td class="slspdTdContent"style="text-align:center;"><a href="#" onclick="downloadFile('${loanApproveQueryEntity.id},${loanApproveQueryEntity.creatorid}')">下载</a></td>
				</c:when>
				<c:otherwise>
					<td class="slspdTdContent"style="text-align:center;">下载</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
		<table id="sljgTableStyle">
		<tr class="firstTr" style="background-color:  #00A2CA;">
		<td class="slspdTdTitle" colspan="2">贷款受理结果</td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent"style="text-align:center;">审核结果：</td>
			<td class="slspdTdContent"style="text-align:center;"><c:if test = "${approverec.lac_id == 'YDKZT02'}">受理通过</c:if><c:if test = "${approverec.lac_id == 'YDKZT06'}">受理不通过</c:if><c:if test = "${approverec.lac_id == 'YDKZT10'}">退单</c:if></td>
		</tr>
		<tr class="trContent" >
			<td class="slspdTdContent"style="text-align:center;">审核意见：</td>
			<td class="slspdTdContent"style="text-align:center;">${approverec.lar_opinion}</td>
		</tr>
	</table>
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
	<table id="sxyjTableStyle">
		<tr class="firstTr" style="background-color:  #00A2CA;">
			<td class="slspdTdTitle" colspan="4">授信意见</td>
		</tr>
		
		<tr class="trContent">
			<td class="slspdTdContent"style="text-align:center;">授信结果：</td>
			<td class="slspdTdContent" style="text-align:center;"colspan="3"><c:if test = "${approverecSX.lac_id == 'YDKZT03'}">授信通过</c:if><c:if test = "${approverecSX.lac_id == 'YDKZT04'}">授信不通过</c:if><c:if test = "${approverecSX.lac_id == 'YDKZT10'}">退单</c:if></td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent" style="text-align:center;">审核意见：</td>
			<td class="slspdTdContent" style="text-align:center;"colspan="3">${approverecSX.lar_opinion}</td>
		</tr>
		<tr class="trContent" >
			<%-- <td class="slspdTdContent"style="text-align:center;"> 产品名称：</td>
			<td class="slspdTdContent"style="text-align:center;">${loanApproveQueryEntity.financialProduct.fpName }</td> --%>
			<td class="slspdTdContent"style="text-align:center;">授信额度：</td>
			<td class="slspdTdContent"style="text-align:center;">${approverec2.lar_credit_quota}万元</td>
			<td class="slspdTdContent"style="text-align:center;">授信利率：</td>
			<td class="slspdTdContent"style="text-align:center;">${approverec2.lar_rate }%</td>
		</tr>
		<tr class="trContent" >
			<td class="slspdTdContent"style="text-align:center;">*授信期限起:</td>
			<td class="slspdTdContent"style="text-align:center;"><fmt:formatDate  value="${approverec2.lar_begin}" type="both" pattern="yyyy-MM-dd"/>  </td>
			<td class="slspdTdContent"style="text-align:center;">*授信期限止:</td>
			<td class="slspdTdContent"style="text-align:center;"><fmt:formatDate  value="${approverec2.lar_end}" type="both" pattern="yyyy-MM-dd"/> </td>
		</tr>
		<tr class="trContent">
			<td class="slspdTdContent"style="text-align:center;">授信期限：</td>
			<td class="slspdTdContent"style="text-align:center;">${approverec2.lar_loan_deadline}个月</td>
			<td  class="sxspdTdContent" >还款方式</td>
			<td id="hkfsStyle" class="sxspdTdContent" style="text-align:center;" colspan="3">
				<c:forEach items="${repaymentWayList }" var="varWay">
					<c:if test="${varWay.id eq  finalHkfs}">
						${varWay.rwname } 
					</c:if> 
				</c:forEach>
			</td>
		</tr>
		</table>
	</form>
	</div>
</div><br/>

<div class="center" style="margin-top:10px;">
	<button id="btn" type="button" onclick="webside.common.loadPage('/loanapprove/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }&status=${loanApproveQueryEntity.la_status}')" style="background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;"><i class="fa fa-undo"></i>&nbsp;关闭</button>
</div>
<script type="text/javascript">
$(function(){
	//获取当前日期
	var date = new Date();
	var v_now_y = date.getFullYear();
    var v_now_m = (date.getMonth() + 1) >= 1 && (date.getMonth() + 1) <= 9 ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1);
    var v_now_d = date.getDate() >= 1 && date.getDate() <= 9 ? ("0" + date.getDate()) : date.getDate();
    var nowDate = v_now_y + "-" + v_now_m + "-" + v_now_d;
    
    //往当前日期后推几个月
    var month= $("#lar_loan_deadline").val();
    
    var arr = nowDate.split("-");
    var v_new_y = parseInt(arr[0]);
    var v_new_m = parseInt(arr[1]);
    var v_new_d = parseInt(arr[2]);
    v_new_m += parseInt(month);
    if (v_new_m > 12){
    	v_new_y = parseInt(v_new_y + v_new_m / 12);
        v_new_m = v_new_m % 12;
    }

    var newDate = v_new_y + "-" + v_new_m + "-" + v_new_d;
	   	$("#lar_begin").val(nowDate);
	   	$("#lar_end").val(newDate);
});
</script>
<script type="text/javascript"> 
$(function(){
	$('#sxsxsjxTableStyle').find('tr:eq(0)').after('${tbody}');
	$("#sxsxsjxTableStyle tr td").addClass("tdClss");
});
</script>