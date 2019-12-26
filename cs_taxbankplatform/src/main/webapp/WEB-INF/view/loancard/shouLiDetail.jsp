<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<style type="text/css">
	.SL1{
		width: 100%;
		height: 100px;
		text-align: center;
		padding-top:70px;
		
		/* border-width: 1px;
    	border-style: solid;
    	border-color: rgba(204, 204, 204, 1); */ 
	}
	
	.SL2{
		width: 100%;
		text-align: center;
		
		/* border-width: 1px;
    	border-style: solid;
    	border-color: rgba(204, 204, 204, 1); */
	}
	
	.SL3{
		text-align: center;
		
		/* border-width: 1px;
    	border-style: solid;
    	border-color: rgba(204, 204, 204, 1); */
	}

	.SL1 span{
		font-family: '微软雅黑';
	    font-weight: 400;
	    font-style: normal;
	    font-size: 16px;
	    text-align: left;
	  }
	  
	  .SlTab{
	  	margin:auto;
		padding-top:10px;
	  	width: 90%;
	  	text-align: center;
	  }
	  
	  .SlTab .td1{
	  	width: 180px;
	  }
	  .SlTab .td2{
	  	width: 320px;
	  }
	  .SlTab .td3{
	  	width: 180px;
	  }
	  
	  
	  .SlTab td{
  		min-width:150px;
  	    font-family: '微软雅黑';
	    font-weight: 400;
	    font-style: normal;
	    font-size: 13px;
	    color: #333333;
        border-width: 2px;
		border-style: solid;
		border-color: rgba(204, 204, 204, 1);
	    padding: 5px;
	  }
	  
	.SlTab .headTd{
		color:white;
		background-color: #438eb9;
		font-family: '微软雅黑';
	    font-weight: 400;
	    font-style: normal;
	    font-size: 16px;
	    text-align: left;
	    padding-left: 5px;
	}

</style>
<script>
	function returnCardList(){
		var nav = "/loancard/listUI.html?returnFlag=returnPage";
		webside.common.loadPage(nav);
	}

</script>

<div>
	<div class="SL1"><span>受理审批单</span></div>
	<div class="SL2">
		<table class="SlTab">
			<tr>
				<td colspan="4" class="headTd">申请信息</td>
			</tr>
			<tr>
				<td class="td1">产品名称</td>
				<td class="td2">${cardDetail.loanCardRecordEntity.xr_cpmc }</td>
				<td class="td3">联系人手机号</td>
				<td>${cardDetail.loanCardRecordEntity.xr_lxrsj }</td>
			</tr>
			<tr>
				<td>联系人地址</td>
				<td>${cardDetail.loanCardRecordEntity.xr_lxrdz}${cardDetail.loanCardRecordEntity.xr_xxdz} </td>
				<td>营销经理推荐代码</td>
				<td>${cardDetail.loanCardRecordEntity.xr_yxjl_tjdm} </td>
			</tr>
			<tr>
				<td>企业名称</td>
				<td>${cardDetail.nsryhxxEntity.qymc }</td>
				<td>纳税人识别号</td>
				<td>${cardDetail.nsryhxxEntity.nsrsbh }</td>
			</tr>
			<tr>
				<td>成立时间</td>
				<td>${fn:substring(cardDetail.nsryhxxEntity.djrq, 0, 10)}</td>
				<td>注册资本</td>
				<td>${cardDetail.nsryhxxEntity.zczb }万元</td>
			</tr>
			<tr>
				<td>法人姓名</td>
				<td>${cardDetail.nsryhxxEntity.frmc }</td>
				<td>法人手机号码</td>
				<td>${cardDetail.nsryhxxEntity.frsjh }</td>
			</tr>
			<tr>
				<td>行业类型</td>
				<td>${cardDetail.nsryhxxEntity.hymc }</td>
				<td>注册地址</td>
				<td>${cardDetail.nsryhxxEntity.zcdz }</td>
			</tr>
			<tr>
				<td>经营范围</td>
				<td colspan="3">${cardDetail.nsryhxxEntity.jyfw }</td>
			</tr>
			
			<!-- ============================初审记录Start=========================== -->
			<tr>
				<td colspan="4" class="headTd">预授信结果</td>
			</tr>
			<c:choose>
				<%-- 初审通过 --%>
				<c:when test="${cardDetail.loanCardRecordEntity.xr_ysxzt_dm eq 0}">
					<tr>
						<td>审核结果</td>
						<td colspan="3">通过</td>
					</tr>
					<tr>
						<td>预授信时间</td>
						<td>${cardDetail.loanCardRecordEntity.xr_ysxsj}</td>
						<td>预授信额度最小值</td>
						<td>${cardDetail.loanCardRecordEntity.xr_ysxedmin }万元</td>
					</tr>
					<tr>
						<td>预授信额度最大值</td>
						<td>${cardDetail.loanCardRecordEntity.xr_ysxedmax }万元</td>
						<td>预授信说明</td>
						<td>${cardDetail.loanCardRecordEntity.xr_ysxsm}</td>
					</tr>
				</c:when>
				<%-- 初审不通过 --%>
				<c:when test="${cardDetail.loanCardRecordEntity.xr_ysxzt_dm eq 1}">
					<tr>
						<td>审核结果</td>
						<td colspan="3">不通过</td>
					</tr>
					<tr>
						<td>预授信时间</td>
						<td colspan="3">${cardDetail.loanCardRecordEntity.xr_ysxsj}</td>
					</tr>
				</c:when>
				<%-- 无记录 --%>
				<c:otherwise>
					<tr>
						<td colspan="4">未找到对应记录</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<!-- ============================初审记录End=========================== -->
			
		</table>
	</div>
	
	<div class="SL3">
		<button style = "margin-top:20px;background-color: #438eb9;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 145px;" onclick="returnCardList()">返回</button>
	</div>
</div>
