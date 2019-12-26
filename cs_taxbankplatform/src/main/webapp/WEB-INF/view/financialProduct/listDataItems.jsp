<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<link rel="stylesheet" href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript" src="${ctx }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript"src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/select2/zh-CN.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
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
.hidden{
	display:none;
}
.dlshouwen-grid-headers{
	display:none;
}
#searchKey{
	pading:0px;
	width: 360px;
	height:40px;
	line-height: 40px;
}
tr.dlshouwen-grid-row{
	height:60px;
	line-height: 60px;
}
.leftI{
	display:inline-block;
	width:60px;
}
.rowItemsHead{
	text-align:center;
}
.rowItems{
	margin-top:20px;
	margin-left:100px;
}
.rowItems .row{
	height:60px;
	line-height:60px;
}
.text-danger{
	display:none;
}
select{
  width: 300px;
  height: 20px;
}
button{border:none;width:120px; height:40px; text-align: center; line-height: 40px; color:#000; font-size:14px;}
</style>
<div class="row financialProduct" style="margin-top: 5px;">
	<div class="col-xs-12">
		<div id="addSjxForm" name="addSjxForm" class="form-horizontal" role="form" method="post">
			<div class="form-group row">
					<div class="rowItemsHead">
					     <input id="searchKey" type="text" placeholder="输入数据项名称"><button id="btnSearch2" >查询</button>
					</div>
					<div class="rowItems" id="rowItems">
					<c:forEach var="items" items="${itemsList }" varStatus="status">
						<div class="row playList" style="text-align:left;">
							<span class="col-md-4 leftI"><input type="checkbox"/></span>
							<span class="col-md-4 col-md-offset-4 rightI ${status.index}">
								<label class="control-label col-sm-1 no-padding-right" pdi_id="${items.id }" pdi_code="${items.pdiCode }" pdi_name="${items.pdiName }" ht_type="${items.htmlTag.htType }" productditvaluesList="${items.productditvaluesList}"><span class="text-danger">*</span>
									<c:choose> 
								     <c:when test="${fn:length(items.pdiName) > 6}"> 
								      <c:out value="${fn:substring(items.pdiName, 0, 6)}..." />：
								     </c:when> 
								     <c:otherwise> 
								      <c:out value="${items.pdiName}" />：
								     </c:otherwise>
								    </c:choose>
							   </label>
									<c:if test="${items.htmlTag.htType eq 'text'}">
										<input readOnly="true" disabled="true" name="${items.pdiCode}" type="text" style="width:300px;"/>
									</c:if>
									
									<c:if test="${items.htmlTag.htType eq 'radio'}">
										<c:set value="${ fn:split(items.productditvaluesList, ',') }" var="htNames" />
											<c:forEach var="htName" items="${htNames }">
												<input readOnly="true" disabled="true" name="${items.pdiCode}" type="radio"/>${htName }
											</c:forEach>
									</c:if>
									
									<c:if test="${items.htmlTag.htType eq 'checkbox'}">
										<c:set value="${ fn:split(items.productditvaluesList, ',') }" var="htNames" />
											<c:forEach var="htName" items="${htNames }">
												<input readOnly="true" disabled="true" name="${items.pdiCode}" type="checkbox"/>${htName }
											</c:forEach>
									</c:if>
									
									<c:if test="${items.htmlTag.htType eq 'file'}">
										<input readOnly="true" disabled="true" name="${items.pdiCode}" type="file" style="display:inline-block;"/>
									</c:if>
									<c:if test="${items.htmlTag.htType eq 'select'}">
										<c:set value="${ fn:split(items.productditvaluesList, ',') }" var="htNames" />
										   
	                                         <select  disabled="disabled" name="${items.pdiCode}" style="width:307px;height:34px; ">
	                                         <c:forEach var="htName"  items="${htNames }">
		                                       <option value="${htName }">${htName }</option>
		                                       </c:forEach>
	                                         </select>
											
											
									</c:if>
							</span>
						</div>
					</c:forEach>
					<div class="btDiv" ><span class="leftI"><input id="selectAll" onclick="checkAll()" type="checkbox">全选</span> <!-- <button  class="rightI" id="btnAdd" type="button" onclick="submitF()" class="btn btn-success btn-sm">添加</button> --></div>
					<div class="hidden" id="result"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="center">
	
</div>
<script type="text/javascript">
function checkAll(){
	$("#selectAll").change(function(){
   		$(".playList .leftI").find(":checkbox").prop("checked", this.checked);
   	});
}
$(function(){
    $("#btnSearch2").click(function(){
    	$.ajax({
    		sync: false,
    		type: "post",
			url : "<%=request.getContextPath()%>/financialProduct/listDataItems.html",
			data : {
				"searchKey" : $("#searchKey").val()
			},
			dataType : "json",
			success : function(data) {
				$('#rowItems').empty();   //清空rowItems里面的所有内容
            	var html = ''; 
                $.each(data, function(commentIndex, comment){
                	html += '<div class="row playList" style="text-align:left;">'+
                	        '<span class="col-md-4 leftI"><input type="checkbox"/></span><span class="col-md-4 col-md-offset-4 rightI '+commentIndex+'"><label class="control-label col-sm-1 no-padding-right" pdi_id="'+comment['id']+'" pdi_code="'+comment['pdiCode']+'" pdi_name="'+comment['pdiName']+'" ht_type="'+comment['htmlTag']['htType']+'" productditvaluesList="'+comment['productditvaluesList']+'"><span class="text-danger">*</span>'+comment['pdiName']+'：</label>';
                	if(comment['htmlTag']['htType']=="text"){
                    	html += '<input name="'+comment['pdiCode']+'" type="text" style="width:300px;"/>';
                    }
                    if(comment['htmlTag']['htType']=="radio"){
                    	var arr = comment['productditvaluesList'].split(',');
                    	$.each(arr, function(i,val){ 
                    		 html+='<input name="'+comment['pdiCode']+'" type="radio"/>'+val;
               		  	});
                    }
                    if(comment['htmlTag']['htType']=="checkbox"){
                    	var arr = comment['productditvaluesList'].split(',');
                    	$.each(arr, function(i,val){ 
                    		 html+='<input name="'+comment['pdiCode']+'" type="checkbox"/>'+val;
               		  	});
                    }
                    if(comment['htmlTag']['htType']=="file"){
                    	html += '<input name="'+comment['pdiCode']+'" type="file" style="display:inline-block;"/>';
                    }
                    if(comment['htmlTag']['htType']=="select"){
                    	var arr = comment['productditvaluesList'].split(',');
                    	html+='<select name="'+comment['pdiCode']+'" disabled="disabled" ">';
                    	$.each(arr, function(i,val){ 

                    		 html+='<option value="'+val+'">'+val;
                    		 html+='</option>';
               		  	});
               		  	html+='</select>';
                    }
                    html += '</span></div>';
                    }
                );
                html += '<div class="btDiv" ><span class="leftI"><input id="selectAll" onclick="checkAll()" type="checkbox">全选</span></div><div class="hidden" id="result"></div>';
              	$('#rowItems').html(html);
             }
		});
    });
});
</script>
