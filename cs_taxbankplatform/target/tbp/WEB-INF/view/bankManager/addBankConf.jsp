<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<link href="${ctx }/resources/css/customer/bankManager/style.css" type="text/css" rel="stylesheet"/>
<link href="${ctx }/resources/css/style/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx }/resources/js/customer/bankManager/bankAdd.js"></script>
<script type="text/javascript">
	/**
	 * 加入时间插件
	 */
	$(function(){
		//查询起止时间
		$('#starttime,#endtime').datetimepicker({
			lang : 'ch',
			format : 'Y/m/d',
			minDate : '2000/01/01',
			maxDate : '2050/01/01',
			timepicker : false,
		});
// 		$('#starttime').datetimepicker({
// 			  lang : "ch",
// 			  format:'Y/m/d',
// 			  onShow:function(ct){
// 			   this.setOptions({
// 			    maxDate:$('#endtime').val()?$('#endtime').val():false,
// 			   })
// 			  },
// 			  timepicker:false,
// 			 });
// 		$('#endtime').datetimepicker({
// 			  lang : "ch",
// 			  format:'Y/m/d',
// 			  onShow:function(ct){
// 			   this.setOptions({
// 			    minDate:$('#starttime').val()?$('#starttime').val():false,
// 			   })
// 			  },
// 			  timepicker:false,
// 			 });
	});
</script>
<div class="row newAddTax" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="taxDataCfgForm" name="taxDataCfgForm" action="" class="form-horizontal" method="post">
			<div class="form-group">
		      <h3 class="newAddTax_text">新增银行税务数据配置</h3>
		   </div>
		   <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">银行名称：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						<select id="bankId" name="bankId" class="form-control" onchange="bankChange();">
							<option value="">请选择银行...</option>
							<option value="">长沙银行</option>
							<c:forEach items="${bankList}" var="perbank">
								<option value="${perbank.bankId}" codeVal="${perbank.bankCode}">${perbank.bankName}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="bankName" id="bankName">
					</div>
				</div>
				<label class="control-label col-sm-1 no-padding-right" for="enabled">银行代码：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						 <input class="form-control" id="bankCodeVal" name="bankCode" type="text" value="${BMListModel.bankCode }" readonly="readonly"/>
					</div>
				</div>
				<label class="control-label col-sm-1 no-padding-right" for="enabled">银行联系部门：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						 <input class="form-control" id="bankdepartment" name="bankdepartment" type="text" value="${BMListModel.bankdepartment }"/>
					</div>
				</div>
			</div>	
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">部门负责人：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						 <input class="form-control" name="leadername" id="leadername" type="text" value="${BMListModel.leadername }"/>
					</div>
				</div>
				<label class="control-label col-sm-1 no-padding-right" for="enabled">移动电话：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						 <input class="form-control" name="leaderphone" id="leaderphone" type="text" value="${BMListModel.leaderphone }" />
					</div>
				</div>
				<label class="control-label col-sm-1 no-padding-right" for="enabled">固定电话：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						 <input class="form-control" name="leadertelephone" id="leadertelephone" type="text" value="${BMListModel.leadertelephone }" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">银行联系人：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						 <input class="form-control" name="contactName" id="contactName" type="text" value="${BMListModel.contactName }"/>
					</div>
				</div>
				<label class="control-label col-sm-1 no-padding-right" for="enabled">移动电话：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						 <input class="form-control" name="contactphone" id="contactphone" type="text" value="${BMListModel.contactphone }" />
					</div>
				</div>
				<label class="control-label col-sm-1 no-padding-right" for="enabled">固定电话：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						 <input class="form-control" name="contacttelephone" id="contacttelephone" type="text" value="${BMListModel.contacttelephone }" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">频次：</label>
				<div class="col-sm-2">
					<div class="clearfix">
						<select id="frequencylimit"  name="frequencylimit" class="form-control" >
							<option value="">请选择...</option>
							<option value="5" >5次/户/月</option>
							<option value="10"> 10次/户/月</option>
							<option value="15">15次/户/月</option>
							<option value="30">30次/户/月</option>						
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right setData" for="enabled">产品信息：</label>
				<div class="col-sm-10">
					<table class="fpTableStyle" id="checkAll">
						<tr>
							<td ><div class="td-mystyle">金融产品名称</div></td>
							<td ><div class="td-mystyle">设置有效期起</div></td>
							<td ><div class="td-mystyle">设置有效期止</div></td>
							<td ><div class="td-mystyle">状态</div></td>
						</tr>
						<tr>
							<td >
								<div class="td-mystyle">
									<select id="fpId" name="fpId" type="text" class="form-control" onchange="fpChang();">
				 						<option value="">请选择产品...</option>
				 						<option value="">养老保险</option>  
									</select>
									<input type="hidden" name="fpName" id="fpName">
								</div>
							</td>
<!-- 							<td> -->
<!-- 								<div class="td-mystyle"> -->
<!-- 									<input id="applyRequire" name="applyRequire" type="text" class="form-control" maxlength="30"> -->
<!-- 								</div> -->
<!-- 							</td> -->
							<td>
								<div class="td-mystyle">
									<input id="starttime" name="starttime" type="text" class="form-control"  placeholder="时间起">
								</div>
							</td>
							<td>
								<div class="td-mystyle">
									<input id="endtime" name="endtime" type="text" class="form-control" placeholder="时间止">
								</div>
							</td>
							<td>
								<div class="td-mystyle">
									<input id="status" name="status" type="text" value="待开通" class="form-control">
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right setData" for="enabled">申请条件</label>
				<div class="col-sm-10">
					<div class="td-mystyle"> 
 						<textarea rows="2" cols="20" id="applyRequire" name="applyRequire" class="form-control" maxlength="100">
						</textarea>
 					</div> 
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right setData" for="enabled">设置数据项：</label>
				<div class="col-sm-10">
					<div class="checkAllBox"><input type="checkbox" name="checkedAll" onclick="checkAll(this)" />全选</div>
					<table class="dataTableStyle" id="checkAll">
						<tr>
							<td><div class="td-mystyle">信息名称</div></td>
							<td  colspan="2"><div class="td-mystyle">实质内容项</div></td>
							<td><div class="td-mystyle">非实质内容项</div></td>
<!-- 							<td><div class="td-mystyle">时间口径</div></td> -->
						</tr>
<!-- 						<tr><td class="padTd"></td></tr> -->
						<c:forEach items="${finalMap}" var="map">
							<tr class="taxTableTitle">
								<td class="titleTd">
									<div><input type="checkbox" onchange="checkBoxClick(this)"/><span class="numText">${map.key} </span></div>
 								</td>
								<c:forEach items="${map.value}" var="optMap">
									<c:if test="${optMap.key=='sub1'}">
										<td style="border-right: #eee 0px solid;">
											<c:forEach items="${optMap.value}" var="opt">
												<div><input type="checkbox" id="${opt.pkey}" name="${opt.pkey}"/><span class="numText">${opt.fieldName}</span></div>
											</c:forEach>
										</td>
									</c:if>
									<c:if test="${optMap.key=='sub2'}">
										<td style="border-left: #eee 0px solid;">
											<c:forEach items="${optMap.value}" var="opt">
												<div><input type="checkbox" id="${opt.pkey}" name="${opt.pkey}"/><span class="numText">${opt.fieldName}</span></div>
											</c:forEach>
										</td>
									</c:if>
									<c:if test="${optMap.key=='esub'}">
										<td>
											<c:forEach items="${optMap.value}" var="opt">
								    			<div><input type="checkbox" id="${opt.pkey}" name ="${opt.pkey}"/><span class="numText">${opt.fieldName}</span></div>
											</c:forEach>
										</td>
									</c:if>
<%-- 									<c:if test="${optMap.key=='other'}"> --%>
<!-- 										<td> -->
<%-- 											<c:forEach items="${optMap.value}" var="opt"> --%>
<%-- 												<div><span>${opt.fieldName}</span></div> --%>
<%-- 											</c:forEach> --%>
<!-- 										</td> -->
<%-- 									</c:if> --%>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
					<div class="checkAllBox"><input type="checkbox" name="checkedAll" onclick="checkAll(this)"/>全选</div>
				
				</div>
			</div>
		</form>
		<!-- <div class="hr hr-dotted"></div> -->
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:submitForm();" class="btn btn-success btn-sm">
	   	<i class="fa fa-user-plus"></i>&nbsp;
	  	<%--<c:if test="${empty regionsEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty regionsEntity}"> --%>
		保存
		<%-- </c:if> --%>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/bankManager/bankList.html')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>