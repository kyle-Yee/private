<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapprove/batch.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapprove/ajaxfileupload.js"></script>

<style type="text/css">
	.headBtn{
		float: left;
		width: 146px;
		height: 40px;
		background-color: #00A2CA;
		text-align: center;
		padding: 11px;
		margin-right: 30px;
		color: white;
	}
	
	.headDiv{
/* 		background-color: red; */
		width:70%;
		height: 100px;
		margin-left: 30px;
		float: left;
	}
	
	.downloadTem{
		color:#008CD6;
		text-decoration: underline;
		font-size: 13px;
		font-weight: 400;
		padding: 11px;
		float: left;
	}
	.downloadTem a:hover{
		color: #008CD6;
	}
	
	.dataDiv{
		margin-left: 30px;
		width: 1100px;
		box-sizing: border-box;
   	 	border-width: 1px;
   		border-style: solid;
    	border-color: rgba(204, 204, 204, 1);
    	border-radius: 0px;
    	clear: both;
	}
	
	.headBtn a{
		color: white;
	}
	.headBtn a:hover{
		color: white;
		text-decoration: underline;
	}
	
	#noData{
		font-family: '微软雅黑';
	    font-weight: 400;
	    font-style: normal;
	    font-size: 24px;
	    text-align: center;
	    padding: 100px;
	}
	
	#noData a{
		color: #00A2CA;
	}
	#noData a:hover{
		color: #00A2CA;
		text-decoration: underline;
	}
	
	.thisTable{
		width:1100px;
	  	border:0px;
	}
	
	.thisTable .trFirst{
		background-color: #00A2CA;
	}
	
	.thisTable .trSecond td{
		color: #AE8535;
		padding: 3px;
	}
	
	.thisTable td{
		box-sizing: border-box;
   		border-width: 2px;
   		border-style: solid;
    	border-color: rgba(204, 204, 204, 1);
    	border-radius: 0px;
		text-align: center;
		font-family: '微软雅黑';
	    font-weight: 400;
	    font-style: normal;
	    font-size: 13px;
	    color: #000000;
	    text-align: center;
	    line-height: 20px;
	    padding: 10px;
	}
	
</style>
<!-- 批量审批申请 -->
<div>
	
	<div>
		<span style="font-size: 16px;text-decoration: underline;color:#00A2CA;">信贷申请审批批量</span>
	</div>
	<div style="height: 80px;width: 100%;"></div>
	<div class="headDiv">
		<input type="file" name="excelFile" id="excelFile" style="display: none;" onchange="importData();" />
		<div class="headBtn" id="import"><a href="javascript:void(0);" id="dataNameATag">导入数据</a></div>
		<div class="headBtn" id="check"><a href="javascript:void(0);" onclick="checkApply();">审批</a></div>	
		<div class="headBtn" hidden="hidden" id="successBtn"><a>导出成功名单</a></div>
		<div class="headBtn" hidden="hidden" id="failBtn"><a>导出失败名单</a></div>
	</div> 
	<div class="downloadTem">
		<a href="resources/batchExcel/BatchTemplate.xls"  download="YSPT_applyTemplate.xls">下载模版</a>
	</div>
	
	<div class="dataDiv">
		<div id="noData">没有查到数据，请<a href="javascript:void(0);" id="dataNameATag2">导入数据</a></div>
		<div id="hasData" style="display:none;">
			<div>
				<table class="thisTable">
					<tr class="trFirst"><td colspan="10" style="text-align: left;color: white;height: 25px;padding: 4px;">导入数据</td></tr>
					<tr id="headTr" class="trSecond">
						<td style="width: 175px;">纳税人识别号/<br/>社会信用统一代码</td>
						<td style="width: 100px;">银行申请序号</td>
						<td style="width: 70px;">批贷结果</td>
						<td style="width: 70px;">授信金额<br/>（万元）</td>
						<td style="width: 70px;">授信周期<br/>（月）</td>
						<td style="width: 100px;">审批日期</td>
						<td style="width: 100px;">贷款期限起</td>
						<td style="width: 100px;">贷款期限止</td>
						<td style="width: 70px;">授信利率</td>
						<td>还款方式</td>
					</tr>
				</table>
				<form action="${ctx }/loanapprovebatch/downloadExport.html" target="_blank" style="display:none" method="post" id="export">
					<input type="hidden" id="fileName" name="fileName"><!-- 表格名 -->
					<input type="hidden" id="dataList" name="list"><!-- 数据 -->
	            </form>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
	
	
</script>
