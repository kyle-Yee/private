<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${ctx }/resources/js/customer/portManager/form.js"></script>
<div class="page-header">
	<h1>
		<c:if test="${empty portManagerEntity}">
		添加银行端口
		</c:if>
		<c:if test="${!empty portManagerEntity}">
		编辑银行端口
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="addForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty portManagerEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" id="regionclassid" name="id" value="${portManagerEntity.id }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcName">银行名称</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="yhmc" name="yhmc" onchange = "getfunction()">
						<c:if test ="${empty portManagerEntity}">
						<!-- 添加 -->
							<option value="微银总部">请选择银行名称</option>
							<c:forEach var="orgEntity" items="${bankNameList}" >
								<option value="${orgEntity.orgname}">${orgEntity.orgname}</option>
							</c:forEach>
						</c:if>
						<c:if test ="${!empty portManagerEntity}">
						<c:forEach var="orgEntity" items="${bankNameList}" >
						<option value="${orgEntity.orgname}" ${portManagerEntity.yhmc==orgEntity.orgname?"selected='selected'":""}>${orgEntity.orgname}</option>
						</c:forEach>
						</c:if>
					</select>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">银行代码</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="yhdm" name="yhdm" type="text" readonly="readonly" value="${portManagerEntity.yhdm }" maxlength="50"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">专线运营商</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="zxyys" name="zxyys" type="text" value="${portManagerEntity.zxyys }" maxlength="50"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">专线编号</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="zxbh" name="zxbh" type="text" value="${portManagerEntity.zxbh }" maxlength="10"/><!--  onchange="if(!/^[0-9a-zA-Z]*$/.test(this.value)){ document.getElementById('zxb').hidden=false; this.value='';}else{document.getElementById('zxb').hidden=true;}" -->
					<span id ="zxb" hidden style = "color:#9932CC">只允许输入1-10位字母、数字</span><br/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">银行端口号</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="yhdkh" name="yhdkh" type="text" value="${portManagerEntity.yhdkh }" maxlength="10"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">银行服务器IP地址</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="yhfwqipdz" name="yhfwqipdz" type="text" value="${portManagerEntity.yhfwqipdz }" /><!-- - onchange="if(!/^(?:(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:1[0-9][0-9]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:2[0-5][0-5])|(?:25[0-5])|(?:1[0-9][0-9])|(?:[1-9][0-9])|(?:[0-9]))$/.test(this.value)){ document.getElementById('yhfwqip').hidden=false; this.value='';}else{document.getElementById('yhfwqip').hidden=true;}" -->
					<span id ="yhfwqip" hidden style = "color:#9932CC">只允许0.0.0.0至255.255.255.255</span><br/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">银行专线链路地址</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="yhzxlldz" name="yhzxlldz" type="text" value="${portManagerEntity.yhzxlldz }"/><!--  onchange="if(!/(([1-9]\d{1,2}|\d\.)|([1-9]\d{1,2}|\d\.)|([1-9]\d{1,2}|\d\.)|([1-9]\d{1,2}|\d))/.test(this.value)){ document.getElementById('yhzxll').hidden=false; this.value='';}else{document.getElementById('yhzxll').hidden=true;}" -->
					<span id ="yhzxll" hidden style = "color:#9932CC">只允许0.0.0.0至999.999.999.999</span><br/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">税局端口号</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="sjdkh" name="sjdkh" type="text" value="${portManagerEntity.sjdkh }" maxlength="10"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">税局服务器IP地址</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="sjfwqipdz" name="sjfwqipdz" type="text" value="${portManagerEntity.sjzxlldz }"/><!--  onchange="if(!/^(?:(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:1[0-9][0-9]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:2[0-5][0-5])|(?:25[0-5])|(?:1[0-9][0-9])|(?:[1-9][0-9])|(?:[0-9]))$/.test(this.value)){ document.getElementById('sjfwqip').hidden=false; this.value='';}else{document.getElementById('sjfwqip').hidden=true;}" -->
					<span id ="sjfwqip" hidden style = "color:#9932CC">只允许0.0.0.0至255.255.255.255</span><br/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">税局专线链路地址</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="sjzxlldz" name="sjzxlldz" type="text" value="${portManagerEntity.sjfwqipdz }"/><!--  onchange="if(!/(([1-9]\d{1,2}|\d\.)|([1-9]\d{1,2}|\d\.)|([1-9]\d{1,2}|\d\.)|([1-9]\d{1,2}|\d))/.test(this.value)){ document.getElementById('sjzxll').hidden=false; this.value='';}else{document.getElementById('sjzxll').hidden=true;}" -->
					<span id ="sjzxll" hidden style = "color:#9932CC">只允许0.0.0.0至999.999.999.999</span><br/>
				</div>
				</div>
			</div>
			<!-- 3.0v modify by Sigua.Huang 2018/06/19 begin-->
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">行方账号</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="bankaccount" name="bankaccount" type="text" value="${portManagerEntity.bankaccount }"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">行方密码</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="password" name="password" type="text" value="${portManagerEntity.password }" maxlength="50" placeholder="长度为6~25"/>
					</div>
				</div>
			</div>
			<!-- 3.0v modify by Sigua.Huang 2018/06/19 end-->
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">备注</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="bz" name="bz" type="text" value="${portManagerEntity.bz }" maxlength="50" placeholder="如主线，备用线等"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="rcDescription">银行接口地址</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="callbackurl" name="callbackurl" type="text" value="${portManagerEntity.callbackurl }" maxlength="50" placeholder="银行回调接口"/>
				</div>
				</div>
			</div>
			
			<%-- <div class="form-group">
			<c:if test ="${!empty xlztList}">
				<label class="control-label col-sm-1 no-padding-right" for="rcName">线路状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="xlzt" name="xlzt">
						<c:if test ="${empty portManagerEntity}">
							<option value="">请选择线路状态</option>
							<c:forEach var="xlztEntity" items="${xlztList}" >
								<option value="${xlztEntity.xlztmc}">${xlztEntity.xlztmc}</option>
							</c:forEach>
						</c:if>
						<c:if test ="${!empty portManagerEntity}">
						<c:forEach var="xlztEntity" items="${xlztList}" >
						<option value="${xlztEntity.xlztmc}" ${portManagerEntity.xlzt==xlztEntity.xlztmc?"selected='selected'":""}>${xlztEntity.xlztmc}</option>
						</c:forEach>
						</c:if>
					</select>
				</div>
				</div>
			</c:if>
			</div> --%>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#addForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty portManagerEntity}">
		添加
		</c:if>
		<c:if test="${!empty portManagerEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/portManager/listUI.html<c:if test="${!empty portManagerEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>