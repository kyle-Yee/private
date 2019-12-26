<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/productdataitems/list.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/productdataitems/formAdd.js"></script>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
 <script type="text/javascript">	
 $ (function(){
 validateAddForm();
 });
 /*
 *下拉菜单控制增加值
 */
 function Show(id)
{
if(id=="0"){
layer.msg("请选择",{
        				icon : 2,
        			});
        			return;
 document.getElementById("oprea").style.display="none";
}
 else if(id=="TXFS01"||id=="TXFS03"||id=="TXFS05" ){
 document.getElementById("oprea").style.display="";
 }else{
 document.getElementById("oprea").style.display="none";
 }

}
/*
*
*/
  /*
 *增加模板行
 */

 function addRow() {
  var pdivName = document.getElementsByName("pdivName");  
         if (pdivName.length<=10) {  
             var table = document.getElementById("addTable");
             var tbody = document.getElementById("templeteTBody");
             var copyTd=document.getElementById("copyTD");
            //var newTBody = tbody.cloneNode(true);
              var newTBody = copyTd.cloneNode(true);
              newTBody.value="";
              newTBody.style.display="";
              var footTBody = document.getElementById("footTbody");
              return table.insertBefore(newTBody,footTBody);
             }
           else{
              layer.msg("至多填写十个数据项值",{
        				icon : 2,
        			});
        			return;
           }  
           
     }  
 
 /*
 *删除模板行
 */

 function deleteRow(obj) {
 var tbody = obj.parentNode.parentNode;
 var table = document.getElementById("addTable"); 
 table.removeChild(tbody);
 }

/**
 *向模板中填充值
 */
 function setValue(){
	var modelVal = $("#pdivName").val();
 	$("#pdivName").val("");
 	var tbody=addRow();
	$("#pdivName").val(modelVal);
 }

   </script>
   
<div class="page-header">
<c:if test="${!readOnly }">
	<shiro:hasPermission name="productdataitems:add">
	<button type="button" onclick="addLink()" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;新增
	</button>
	</shiro:hasPermission>
</c:if>
</div>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			<div class="widget-header" style="display:inline;width:100%">
				<div style="float:left;width:70%">
					<h4 class="widget-title lighter">产品数据项维护</h4>			
				</div>
				<div class="input-group" style="float:right;width:30%">
				     <input id="searchKey" type="text" class="input form-control" placeholder="输入账户名名称">
				     <span class="input-group-btn">
				         <button id="btnSearch" class="btn btn-primary btn-sm" type="button">搜索</button>
				     </span>
				</div>
			</div>

			<input type="hidden" id="readOnly" value="${readOnly}">
			<input type="hidden" id="regionShow" value="${regionShow }">
			<input type="hidden" id="orgShow" value="${orgShow }">
			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container" style="overflow:hidden;"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>
	<!-- 添加，编辑弹出框 -->
	<div id="addLinkDiv" style="display:none;" >
		<br>
		<div style="margin-left:15px;margin-right:15px">
			<form id="addForm" name="addForm" class="form-horizontal" role="form" method="post">
			       <div>
			   <!--    <input type="hidden" name="id" id="pdiId" value="${record.id}"/> --> 
	     	       </div>
				   <div><label for="">请填写数据项名称</label></div>
				   <div><input type="text" class="form-control" name="pdiName" id="pdiName" value="${pdiName}" onblur="validateNext()"/></div>  
				   <br>
                   <div ><label for="">请选择该项目的填写方式</label></div>
				   <div>
				     <select class="form-control" name="htmlTag.id" id="SEL" onchange="Show(this.value)">
			   	     	<option value="0">--请选择--</option>	
			   	     	<c:forEach var="htmlTagList" items="${htmlTagList}">
			   	     	<option value="${htmlTagList.id}">${htmlTagList.htName}</option>
			   	     	</c:forEach>			
					</select>
				   </div>
				   <br>
				 
				 
				
					<div id="oprea" style="display:none;">
					 <div>
				   <label for="">添加选项</label>
				   </div>
						<table style="width:100%"  id="addTable" >
						<thead>
							<tr style="text-align:center">					
								<td style="width:50%">选项名称</td>
								<td style="width:50%">操作</td>
							</tr>
						</thead>
						<tbody id="templeteTBody">      
							<tr style="height:50px" id="copyTD">
								<td style="text-align:center"><input type="text" name="pdivName" id="pdivName"  /></td> 
								<td style="text-align:center"><input type="button" class="btn btn-primary btn-sm" value="删除" onclick="deleteRow(this)"/></td>
							</tr>
							
						</tbody>
						<tfoot id="footTbody">
							<tr>
								<td colspan="4" style="text-align:center"> <input type="button" style="width:150px;" class="btn btn-primary btn-sm"  value="增加" onclick="setValue()"/></td>
							</tr>
						</tfoot>
						<tr style="height:30px"></tr>
						</table>
					</div>
			</form>
		</div>
		
	</div>
	



