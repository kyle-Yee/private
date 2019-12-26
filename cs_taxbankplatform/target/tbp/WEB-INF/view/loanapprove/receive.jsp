<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/kanbansystemlist/list.js">
	</script>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<div class="row" style="margin-top:5px;">
	<table style="margin-left: auto; margin-right: auto; width: 1000px; border: 1px solid #00A2CA;">
		<caption>贷款申请信息</caption>
		<tr>
			<td>企业名称：<input id="1" type="text"></td>
			<td>注册资本：<input id="2" type="text"></td>
			<td>申请授信额度：<input id="7" type="text"></td>
		</tr>
		<tr>
			<td>行业类型：<input id="4" type="text"></td>
			<td>经营范围：<input id="5" type="text"></td>
			<td>期望贷款期限：<input id="8" type="text"></td>
		</tr>
		<tr>
			<td>成立时间：<input id="3" type="text"></td>
			<td>产品名称：<input id="6" type="text"></td>
			<td>期望还款方式：<input id="9" type="text"></td>
		</tr>
	</table>
	<table border="1" style="margin-left: auto; margin-right: auto; width: 1000px; border: 1px solid #00A2CA;">
	<caption>授信所需附件</caption>
		<td >附件：</td>
		<td>
			<c:forEach var="loanApplyAttach" items="${loanApplyAttachList }">
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="showAttachment('${loanApplyAttach.loanProductDataEntity.pdiName }', '${loanApplyAttach.pdiValues }')">${loanApplyAttach.loanProductDataEntity.pdiName }</a><br/>
			</c:forEach>
		</td>
	</table>
	<table border="1" style="margin-left: auto; margin-right: auto; width: 1000px; border: 1px solid #00A2CA;">	
		<caption>授信所需数据项</caption>
		<tr style="background-color: #00A2CA">
			<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;申请单号：${loanApproveQueryEntity.la_serial_number }</td>
		</tr>
		
		<tr>
			<td align="right">产品名称：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.financialProduct.fpName }</td>
			<td align="right">期望的贷款方式：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.repaymentWayEntity.rwname }</td>
		</tr>
		
		<tr>
			<td align="right">申请授信额度：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.la_amount }万</td>
			<td align="right">期望的贷款期限：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.la_repay_loan_deadline }个月</td>
		</tr>
		
		<tr>
			<td align="right">企业名称：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.nsrmc }</td>
			<td align="right">纳税人识别号：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsrsbh }</td>
		</tr>
		
		<tr>
			<td align="right">法定代表人名称：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frmc }</td>
			<td align="right">法定代表人手机号码：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.frsjh }</td>
		</tr>
		
		<tr>
			<td align="right">电话：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.nsrdh }</td>
			<td align="right">传真：</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.nsrcz }</td>
		</tr>
		
		<tr>
			<td align="right">纳税人注册地址：</td>
			<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;${loanApproveQueryEntity.nsryhxxEntity.nsrdz }</td>
		</tr>
		
		<tr>
			<td align="right">产品覆盖区域：</td>
			<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;<c:forEach var="cityEntities" items="${cityEntities }"><span>${cityEntities.pcname}&nbsp;&nbsp;</span></c:forEach></td>
		</tr>
		
		<tr>
			<td align="right">主管税务机关名称：</td>
			<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;广东省深圳市地方税务局保税分局</td>
		</tr>
		
		
	</table><br/>
	<table border="1" style="margin-left: auto; margin-right: auto; width: 1000px; border: 1px solid #00A2CA;">
		<caption>企业涉税信息</caption>
		<tr>
			<td align="right">企业涉税信息：</td>
			<td align="left" colspan="3">
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业税务稽查信息</a><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业近五年信用等级评定信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业纳税申报信息</a><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">财务报表信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">企业违法违章信息</a>
			</td>
		</tr>
	</table>
	<table border="1" style="margin-left: auto; margin-right: auto; width: auto; border: 1px solid #00A2CA;">
		<caption>贷款受理结果</caption>
		<tr class="tr-height">
			<td align="right">审核结果：&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td align="left" colspan="3"><input id="lac_id" name="lac_id" type="radio" checked="checked" onclick="getFormData()" value="1"/>同意&nbsp;&nbsp;&nbsp;&nbsp;<input id="lac_id" name="lac_id" type="radio" onclick="getFormData()" value="2"/>不同意&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr class="tr-height">
			<td align="right">审批意见：&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td align="left" colspan="3"><textarea id="lar_opinion" name="lar_opinion" rows="3" cols="107"></textarea></td>
		</tr>
	</table>
</div>
<div class="center" style="margin-top:10px;">
	<button id="btnAdd" type="button" onclick="javascript:$('#taxauthorityForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 添加
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/loanapprove/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }&status=${loanApproveQueryEntity.la_status}')" class="btn btn-info btn-sm"><i class="fa fa-undo"></i>&nbsp;返回</button>
</div>