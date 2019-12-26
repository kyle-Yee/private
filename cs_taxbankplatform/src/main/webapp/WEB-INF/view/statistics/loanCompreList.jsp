<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%-- <link href="${ctx }/resources/css/style/style.css" type="text/css" rel="stylesheet"/> --%>
<link href="${ctx }/resources/css/customer/count/default.css" type="text/css" rel="stylesheet"/>
<link href="${ctx }/resources/css/customer/count/styles.css" type="text/css" rel="stylesheet"/>
<link href="${ctx }/resources/css/customer/count/loansdetails/styles.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx }/resources/js/customer/statistics/loanCompreList.js"></script>

<script type="text/javascript">
	/**
	 * 加入时间插件
	 */
	$(function(){
		//查询起止时间
		$("#starttime,#endtime").datetimepicker({
			lang : "ch",
			timepicker : false,
			format : "Y-m-d",
			formatDate : "Y-m-d",
		});
	});
</script>
<div class="main-content-inner">
	<div class="">
		<div class="row" >
			<div class="col-xs-12 widget-container-col ui-sortable" >
				<div class="widget-box transparent ui-sortable-handle" >
					<div class="widget-header">
						<div class="form-group row">
							<div class="col-md-4"><H4>银税互动综合情况查询</H4>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="u3971_div" class="" >
	<div style = "height: 10px;"></div>
	<form id="form" action="">
		<table id="formTable">
			<tr height="40">
				
				<td class="tdFont">银行&nbsp;&nbsp;:</td>
				<td id="u3952" class="ax_default _下拉列表框1 col-md-4">
					<select id="bankId" name="bankId" class="form-control" onchange="bankChange();">
					  <option value="">请选择银行...</option>
						<c:forEach items="${bankList}" var="perbank">
						<option value="${perbank.yhdm}" codeVal="${perbank.yhdm}">${perbank.yhmc}</option>
					  </c:forEach>
					</select>
					<input type="hidden" name="bankName" id="bankName">
				</td>
				<td class="tdFont">产品&nbsp;&nbsp;:</td>
			 	<td id="u3953" class="ax_default _下拉列表框1 col-md-4">	
			 		<select id="fpId" name="fpId" type="text" class="form-control">
						<option value="">请选择产品...</option>
					</select>
				</td>
				<c:if test="${userSession.ssswjDm == '145'}">
				<td class="tdFont">纳税人主管局&nbsp;&nbsp;:</td>
				<td id="u3952" class="ax_default _下拉列表框1">
					<select id="nsrzgswj" name="nsrzgswj"   class="form-control"  >
						<option value="">请选择税局</option>
<%-- 					    <c:forEach items="${SWJDMList}" var="swjdm"> --%>
<%-- 							<option value="${swjdm.dm}">${swjdm.mc}</option> --%>
<%-- 					    </c:forEach> --%>
						<option value="145">国家税务总局广西壮族自治区税务局</option>
						<option value="14501">南宁市税务局</option>
						<option value="14502">柳州市税务局</option>
						<option value="14503">桂林市税务局</option>
						<option value="14504">梧州市税务局</option>
						<option value="14505">北海市税务局</option>
						<option value="14506">防城港市税务局</option>
						<option value="14507">钦州市税务局</option>
						<option value="14508">贵港市税务局</option>
						<option value="14509">玉林市税务局</option>
						<option value="14510">百色市税务局</option>
						<option value="14511">贺州市税务局</option>
						<option value="14512">河池市税务局</option>
						<option value="14513">来宾市税务局</option>
						<option value="14514">崇左市税务局</option>
					</select>
				</td>
				</c:if>
			</tr>
			
			<tr height="40">
				<td class="tdFont">贷款申请时间起&nbsp;&nbsp;:</td>
				<td id="u3955" class="ax_default _文本框" data-label="Start Date Field">
					<span id="search_time1">
						<input id="starttime" name="starttime" type="text" class="input" style="width:90%;">
						<img  src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
					</span>
				</td>
				<td class="tdFont">贷款申请时间止&nbsp;&nbsp;:</td>
				<td id="u3955" class="ax_default _文本框" data-label="Start Date Field"  >
					<span id="search_time2">
						<input id="endtime" name="endtime" type="text" class="input" style="width:90%;">
						<img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
					</span>
				</td>	
			</tr>
			<tr height="60">
				<div  id="u4270" class="ax_default _形状" >
				<td colspan="2" align="right"> 
						<div class="buttonStyle" >
							<button id="btnSearch"  type="button"> <i class="fa fa-search"></i> 查询</button>						
						</div>
					</td>
					<td  colspan="2"  align="right"> 
						<div class="buttonStyle" >
							<button id="btnReset"  type="reset"> <i class=""></i>清空</button>							
						</div>
					</td>
					
				</div>
			</tr>
		</table>
	</form>
</div>
<!-- 	<div class="title">贷款综合情况统计</div> -->

<!-- 	<div class="search-box"> -->
<!-- 		<form id="form" action=""> -->
<!-- 			<div class="col-md-4"> -->
<!-- 				<div class="ipt-data"> -->
<!-- 					<input id="taxpayerManagement" name="taxpayerManagement" type="text" class="form-control"  placeholder="纳税人主管局："> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="col-md-4"> -->
<!-- 				<select id="bankId" name="bankId" class="form-control" onchange="bankChange();"> -->
<!-- 					<option value="">请选择银行...</option> -->
<%-- 					<c:forEach items="${bankList}" var="perbank"> --%>
<%-- 						<option value="${perbank.bankId}" codeVal="${perbank.bankCode}">${perbank.bankName}</option> --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
<!-- 			</div> -->
<!-- 			<div class="col-md-4"> -->
<!-- 				<select id="fpId" name="fpId" type="text" class="form-control"> -->
<!-- 					<option value="">请选择金融产品...</option> -->
<!-- 				</select> -->
<!-- 			</div> -->
<!-- 			<div class="col-md-4"> -->
<!-- 				<div class="ipt-data" id="search_time1"> -->
<!-- 					<input id="starttime" name="starttime" type="text" class="form-control dateImg"  placeholder="贷款申请开始时间"> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="col-md-4"> -->
<!-- 				<div class="ipt-data" id="search_time2"> -->
<!-- 					<input id="endtime" name="endtime" type="text" class="form-control dateImg" placeholder="贷款申请结束时间"> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="col-md-12 mar-bottom-ten text-center"> -->
<!-- 				<button type="button" class="button btn-blue"  id="btnSearch">查&nbsp;询</button> -->
<!-- 				<button type="reset" class="button btn-blue"  id="btnReset">清&nbsp;空</button> -->
<!-- 			</div> -->
<!-- 		</form> -->
<!-- 	</div> -->
	<!-- 增加分页 -->
	<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>
