<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<link href="${ctx }/resources/images/webside.ico" rel="icon"/>
<link href="${ctx }/resources/images/webside.ico" type="image/x-icon" rel="bookmark"/>   
<link href="${ctx }/resources/images/webside.ico" type="image/x-icon" rel="shortcut icon"/>  

<link rel="stylesheet" href="${ctx}/resources/js/bootstrap/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctx}/resources/fonts/fontawesome/font-awesome.min.css" media="all"/>
<link rel="stylesheet" href="${ctx}/resources/fonts/opensans/ace-fonts.min.css"/>
<link rel="stylesheet" href="${ctx}/resources/css/ace/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>
<link rel="stylesheet" href="${ctx}/resources/css/customer/webside.min.css"/>
<!--[if lte IE 9]>
	<link rel="stylesheet" href="${ctx}/resources/css/ace/ace-part2.min.css" />
<![endif]-->

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${ctx}/resources/css/ace/ace-ie.min.css" />
<![endif]-->
<!-- JQuery script -->
<!-- 非IE浏览器不会识别IE的条件注释，所以这里判断非IE需要如下写法：参照下面jquery-2.1.4.min.js引入的方式 -->
<!--[if !IE]><!-->
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-2.1.4.min.js"></script>
<!--<![endif]-->
<!--[if IE]>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.11.3.min.js"></script>
<![endif]-->
<!-- basic scripts -->
<script type="text/javascript">
	if ('ontouchstart' in document.documentElement)document.write("<script src='${ctx}/resources/js/jquery/jquery.mobile.custom.min.js'>" + "<"+"script>");
</script>

<script src="${ctx}/resources/js/jqueryui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jqueryui/jquery.ui.touch-punch.min.js" type="text/javascript"></script>

<script type="text/javascript" src="${ctx}/resources/js/layer-v2.3/layer.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
<script type="text/javascript" src="${ctx}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>

<script type="text/javascript" src="${ctx}/resources/js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-validation/localization/messages_zh.js"></script>

<script type="text/javascript" src="${ctx}/resources/js/nicescroll/jquery.nicescroll.min.js"></script>

<script type="text/javascript" src="${ctx}/resources/js/customer/index/index.js"></script>


<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/jquery/jquery.datetimepicker.css">
<style type="text/css">
	.input{
	    border:none;
	    height:30px;
	    width:96px;
	    border-radius:6px;
		-webkit-border-radius: 6px;
		background-color:AE8535;
		color:#FFF;
	}	

	.layui-layer-title {
		background-color:#00A2CA;
		text-align : center;
		height:30px;
		line-height:30px;
		font-size:14px;
		border-color : #bcbcbc;
	}
	.layui-layer-btn{
		text-align:center;
		height : 55px;
	}
	.layui-layer-btn .layui-layer-btn0{
		height : 24px;
		line-height:20px;
		width : 100px;
		color : #000;
		background-color : #00A2CA;
		border-color : #000;
		border-radius : 0px;
	}
	.layui-layer{
		border-radius : 0px;
		border : 1px solid #00A2CA;
	} 
</style>
<script type="text/javascript" type="text/javascript">
var sys = sys || {};
sys.rootPath = "${ctx}";
sys.pageNum = 10;
sys.gridStyle = "Bootstrap";

</script> 

<%-- <script type="text/javascript" src="${ctx }/resources/js/customer/loanapprove/formAdd.js"></script> --%>

<div>

</div>
<div style="margin-left:15px;margin-right:15px">
<form id="addEndForm" name="addEndForm" class="form-horizontal" role="form" method="post">
<div>
	<%-- <input type="text" name="pdiId" value="${id}" /> --%>
		<c:if test="${!empty loanApplyFinalEntity }">
	<input type="hidden" name="lafId" value="${loanApplyFinalEntity.id}" />
	</c:if>
</div>

	<div id="oprea">
		<table style="width:100%" id="addTable" >
		<tbody id="templeteTBody">  
			<c:if test="${!empty loanApplyFinalEntity }">
	        <input type="hidden" name="laf_id" id="laf_id" value="${loanApplyFinalEntity.id}" />
	        <input type="hidden" name="la_id" id="la_id" value="${loanApplyFinalEntity.la_id}" />
	        <input type="hidden" name="lac_id" id="lac_id" value="${loanApplyFinalEntity.lac_id}" />
	        <input type="hidden" name="laname" id="laname" value="${laName}" />
			<tr style="height:50px" id="copyTD">   
			
				<td style="text-align:center;display:none"><input type="text" name="tthNsrmc" id="tthNsrmc" value="${loanApplyFinalEntity.nsryhxxEntity.qymc }"/></td>			
				<td style="text-align:center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				企业名称：${loanApplyFinalEntity.nsryhxxEntity.qymc }</td>
				<td style="text-align:center"></td>
			</tr>
				<tr style="height:50px" id="copyTD">
				<td style="text-align:center;display:none"><input type="text" name="tthNsrsbh" id="tthNsrsbh" value="${loanApplyFinalEntity.nsryhxxEntity.nsrsbh }"/></td>			
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;纳税人识别号/社会信用统一代码：${loanApplyFinalEntity.nsryhxxEntity.nsrsbh}</td>
				<td style="text-align:center"></td>
			</tr>
				<tr style="height:50px" id="copyTD">
				<td style="text-align:center;display:none"><input type="text" name="time" id="time" value="${time}"/></td>			
				<td style="text-align:center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;授信终止时间：${time}</td>
				<td style="text-align:center"></td>
			</tr>
		
				<tr style="height:50px" id="copyTD">
				<td style="text-align:center;display:none"></td>			
				<td style="text-align:center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;授信完成额度   ：<input type="text" name="lae_credit_quota" id="lae_credit_quota" value=""/></td>
				<td style="text-align:center">万元</td>
			</tr>
			
				<tr style="height:50px" id="copyTD">
				<td style="text-align:center;display:none"></td>			
				<td style="text-align:center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				授信期间逾期次数 ：<input type="text"  name="lae_overdue_count" id="lae_overdue_count" value="0"/></td>
				<td style="text-align:center">次</td>
			</tr>
				<tr style="height:50px" id="copyTD">			
				<td style="text-align:center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;授信状态 ：<select class="select" name="bankloan_type" id='SEL'>
	            <option selected value="0">请选择银行五级分类</option>
	            <c:forEach items="${loanBankloanTypeEntities}" var="loanBankloanTypeEntities">
                <option value="${loanBankloanTypeEntities.id}">${loanBankloanTypeEntities.bltName}</option>
                </c:forEach>
	           </select></td>
				<td style="text-align:center">
					
	           </td>
			</tr>
	
			</c:if>
		</tbody>
	  <tfoot id="footTbody">
			<!-- <tr>
			<td style="text-align:center;display:none"></td>	
				<td style="text-align:center"><button id="btnAdd" type="button" onclick="validateAddForm();" class="btn btn-success btn-sm"><i class="fa fa-user-plus"></i>保存</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	           <button id="btn" type="button" onclick="a()" class="btn btn-info btn-sm"><i class="fa fa-undo"></i>&nbsp;返回</button></td>
	           <td style="text-align:center;display:none"></td>	
			</tr> -->
	
		</tfoot> 
			
		<tr style="height:30px"></tr>
		</table>
	</div>  
	</form>
</div>
