<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<script type="text/javascript" src="${ctx }/resources/js/customer/acceptdeadline/form.js"></script>
<script type="text/javascript">
 function Show(id)
{
if(id=="0"){
layer.msg("请选择",{
        				icon : 2,
        			});
        			return;
}   
}
</script>
<div class="page-header">
	<h1>
		<c:if test="${empty acceptDeadLineEntity}">
		新增受理期限
		</c:if>
		<c:if test="${!empty acceptDeadLineEntity}">
		编辑受理期限
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="acceptdeadlineForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty acceptDeadLineEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" id="acceptdeadlineid" name="id" value="${acceptDeadLineEntity.id }"/>
			</c:if>
		<%-- 	<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="regionId">区域id</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="regionId" name="regionId" type="text" value="${acceptDeadLineEntity.regionId}"/>
				</div>
				</div>
			</div> --%>
		<%-- 	<input type="hidden" id="regionId" name="regionId" value="${acceptDeadLineEntity.regionId }"/>
	        <input type="hidden" id="laId" name="laId" value="${acceptDeadLineEntity.laId }"/>  --%>
			<input type="hidden" id="createUser" name="createUser" value="${acceptDeadLineEntity.createUser }"/>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="orgId">组织</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<%--  <input class="form-control" id="orgId" name="orgId" type="text" value="${acceptDeadLineEntity.orgId}"/> --%>
					  <select id="orgId"  name="orgId" class="form-control"  onchange=" Show(this.value)"  ${!empty disable?"disabled='disabled'":""}>
						<option value="0">请选择所属组织</option>
							<c:forEach var="orgsList" items="${orgsList }" >
								<option value="${orgsList.id}" ${orgsList.id==acceptDeadLineEntity.orgId?"selected='selected'":""} >${orgsList.orgname}</option>
							</c:forEach>
							</select>
				</div>
				</div>
			</div>
		<%-- 	<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="laId">贷款申请id</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="laId" name="laId" type="text" value="${acceptDeadLineEntity.laId}"/>
				</div>
				</div>
			</div> --%>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="tlTotalDays">贷款受理天数</label>
				<div class="col-sm-10">
				<div class="clearfix">
					 <input class="form-control" id="tlTotalDays" name="tlTotalDays" type="text" value="${acceptDeadLineEntity.tlTotalDays}"
					 onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="5"/>
				</div>
				</div>
			</div>
			
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#acceptdeadlineForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty acceptDeadLineEntity}">
		添加
		</c:if>
		<c:if test="${!empty acceptDeadLineEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/acceptdeadline/listUI.html<c:if test="${!empty acceptDeadLineEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>