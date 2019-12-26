<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet"
	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript">
    ctx = "${ctx }"; 
	window.UEDITOR_HOME_URL = "${ctx }/resources/js/ueditor/";
</script>
<!-- 配置文件 -->
<script type="text/javascript" 
	src="${ctx }/resources/js/customer/financialProduct/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" 
	src="${ctx }/resources/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/customer/tbpStatic/feedback/feedbackForm.js"></script>	

<style>
.iconShow
{
	padding-left: 10px; 
	padding-top: 3px;
}
.source-icon
{
	 width:95%;
	 float:left;
}
@media screen and (max-width: 374px){
	.source-icon
	{
		 width:82%;
	}
}
@media screen and (min-width: 375px) and (max-width: 449px){
	.source-icon
	{
		 width:85%;
	}
}
@media screen and (min-width: 450px) and (max-width: 1279px) {
	.source-icon
	{
		 width:90%;
	}
}
@media screen and (min_width: 1280px) {
	.source-icon
	{
		 width:90%;
	}
}

.imgLimit img{
	max-width:90%;
}
</style>
<div class="page-header">
	<h1>
		反馈意见维护
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="feedbackForm" name="feedbackForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty feedbackEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"/> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"/> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }"/>
				<input type="hidden" name="id" id="feedbackId" value="${feedbackEntity.id }"/>
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="feedbackName">问题</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<input class="form-control" id="feedbackName" disabled="disabled" name="feedbackName" type="text" value="${feedbackEntity.feedbackName }"/>
				</div>
				</div>
			</div>
			<!-- 展示dialog对话 -->
			<div class="form-group" style="margin-left: 60px;">
				<input type="hidden" id="canAnswer" value="${canAnswer }">
				全部回答
				<br><hr>
				<table style="width: 880px;margin-left: 40px;">
						<c:forEach items="${dialogList }" var="dialogVar">
							<tr>
								<td style="width: 60px;">
									<c:if test="${dialogVar.type == 1 }"><!-- 回答 -->
										回答者：
									</c:if>
									<c:if test="${dialogVar.type == 2 }"><!-- 追问 -->
										追问：
									</c:if>
								</td>
								<td>${dialogVar.createName } &nbsp;&nbsp;<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dialogVar.createtime }" /></td>
							</tr>
							<tr class="imgLimit"><td colspan="2"><p>${dialogVar.f_content }</p></td></tr>
						</c:forEach>
				</table>
				
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="feedbackContent">内容</label>
				<div class="col-sm-10">
				<div class="clearfix">
					
<!--         				${feedbackEntity.feedbackContent } -->
					<script id="container" name="feedbackContent" type="text/plain" style="height:300px;">
    					
					</script>
    				<div id="htmlTemp" style="display: none;"></div>
    				<input type="hidden" name="attachmenturls" id="attachmenturls"/>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="enabled" name="enabled" value="${feedbackEntity.enabled }">
						<option value="Y">有效</option>
						<option value="N">无效</option>
					</select>
				</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#feedbackForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i> 
		<c:if test="${empty feedbackEntity}">
		添加
		</c:if>
		<c:if test="${!empty feedbackEntity}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" onclick="webside.common.loadPage('/feedback/feedbackUI.html<c:if test="${!empty feedbackEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i> 返回
	</button>
</div>