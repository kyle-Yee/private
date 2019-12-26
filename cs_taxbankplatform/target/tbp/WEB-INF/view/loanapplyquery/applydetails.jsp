<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapplyquery/list.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapplyquery/list.js"></script>
<style type="text/css">
	tr {height: 30px;}
</style>
<div class="row" style="margin-top:5px;">
	<table style="margin-left: auto; margin-right: auto; width: 998px;">
		<tr align="center">
			<td>
				<c:if test="${loanApplyQueryEntity.la_status == 2}">
					<span style="font-size: 20px;">贷款申请单初审</span><br/><br/>
				</c:if>
				<c:if test="${loanApplyQueryEntity.la_status == 3}">
					<span style="font-size: 20px;">贷款申请单终审</span><br/><br/>
				</c:if>
				<c:if test="${loanApplyQueryEntity.la_status == 1}">
					<span style="font-size: 20px;">贷款申请单待审批</span><br/><br/>
				</c:if>
			</td>
		</tr>
	</table>
	<table border="1" style="margin-left: auto; margin-right: auto; width: 998px; border: 1px solid #00A2CA;">	
		<tr style="background-color: #00A2CA">
			<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;申请单号：${loanApplyQueryEntity.la_serial_number }</td>
		</tr>
		
		<tr>
			<td align="right">产品名称：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.financialProduct.fpName }</td>
			<td align="right">期望的贷款方式：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.repaymentWayEntity.rwname }</td>
		</tr>
		
		<tr>
			<td align="right">申请授信额度：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.la_amount }万</td>
			<td align="right">期望的贷款期限：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.la_repay_loan_deadline }个月</td>
		</tr>
		
		<tr>
			<td align="right">企业名称：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.nsryhxxEntity.nsrmc }</td>
			<td align="right">纳税人识别号：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.nsrsbh }</td>
		</tr>
		
		<tr>
			<td align="right">法定代表人名称：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.nsryhxxEntity.frmc }</td>
			<td align="right">法定代表人手机号码：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.nsryhxxEntity.frsjh }</td>
		</tr>
		
		<tr>
			<td align="right">电话：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.nsryhxxEntity.nsrdh }</td>
			<td align="right">传真：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.nsryhxxEntity.nsrcz }</td>
		</tr>
		
		<tr>
			<td align="right">纳税人注册地址：</td>
			<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${loanApplyQueryEntity.nsryhxxEntity.nsrdz }</td>
		</tr>
		
		<tr>
			<td align="right">产品覆盖区域：</td>
			<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;<c:forEach var="cityEntities" items="${cityEntities }"><span>${cityEntities.pcname}&nbsp;&nbsp;</span></c:forEach></td>
		</tr>
		
		<tr>
			<td align="right">附件：</td>
			<td align="left" colspan="3">
				<c:forEach var="loanApplyAttach" items="${loanApplyAttachList }">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/>
				</c:forEach>
			</td>
		</tr>
		
		<tr>
			<td align="right">主管税务机关名称：</td>
			<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;广东省深圳市地方税务局保税分局</td>
		</tr>
		
		<tr>
			<td align="right">企业涉税信息：</td>
			<td align="left" colspan="3">
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业税务稽查信息</a><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业近五年信用等级评定信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业纳税申报信息</a><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">财务报表信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业违法违章信息</a>
			</td>
		</tr>
	</table><br/>
	
	<c:if test="${loanApplyQueryEntity.la_status != 1}">
		<c:forEach var="loanrecord" items="${loanrecord }">
			<div style="margin-left: auto; margin-right: auto; width: 1000px; border: 1px solid #00A2CA;">
				<table border="0" style="margin-left: auto; margin-right: auto; width: 998px;">
					<tr style="background-color: #00A2CA">
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${loanrecord.las_id == 1}">初审</c:if><c:if test="${loanrecord.las_id == 2}">终审</c:if></td>
						<td align="center" colspan="2"><c:if test="${loanrecord.las_id == 1}">初审</c:if><c:if test="${loanrecord.las_id == 2}">终审</c:if>人：${loanrecord.userEntity.userName }</td>
						<td align="right"><c:if test="${loanrecord.las_id == 1}">初审</c:if><c:if test="${loanrecord.las_id == 2}">终审</c:if>时间：<fmt:formatDate  value="${loanrecord.createtime }" type="both" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					
					<tr>
						<td align="right" width="229">授信额度：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left" width="210">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.lar_credit_quota }万</td>
						<td align="right" width="252">贷款期限：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.lar_loan_deadline }个月</td>
					</tr>
					
					<tr>
						<td align="right">贷款利率：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.lar_rate }%</td>
						<td align="right">贷款期限止：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate  value="${loanrecord.lar_begin }" type="both" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate  value="${loanrecord.lar_end }" type="both" pattern="yyyy-MM-dd"/></td>
					</tr>
					
					<tr>
						<td align="right">是否在产品覆盖区域：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${loanrecord.lar_isoverlay_area == 1}">是</c:if><c:if test="${loanrecord.lar_isoverlay_area == 0}">否</c:if></td>
						<td align="right">还款方式：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.repaymentWayEntity.rwname }</td>
					</tr>
					
					<tr>
						<td align="right">借款方开户银行：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.lar_bank_name }</td>
						<td align="right">借款方银行账号：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.lar_bank_account }</td>
					</tr>
					
					<tr>
						<td align="right">借款合同号：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.lar_contract }</td>
					</tr>
					
					<tr>
						<td align="right">审批结果：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.loanCodeEntity.lac_name }</td>
					</tr>
					
					<tr>
						<td align="right">审批意见：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${loanrecord.lar_opinion }</td>
					</tr>
				</table>
			</div><br/>
		</c:forEach>
	</c:if>
</div>
<div class="center">
	<button id="btn" type="button" onclick="webside.common.loadPage('/loanapplyquery/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>


