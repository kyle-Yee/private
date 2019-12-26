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

<script type="text/javascript" type="text/javascript">
var sys = sys || {};
sys.rootPath = "${ctx}";
sys.pageNum = 10;
sys.gridStyle = "Bootstrap";

</script> 

<%-- <script type="text/javascript" src="${ctx }/resources/js/customer/productdataitems/editList.js"></script> --%>
<script type="text/javascript" src="${ctx }/resources/js/customer/productdataitems/formValueEdit.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/customer/productdataitems/formAdd.js"></script>
<script type="text/javascript">

/**
* 更新pdiName
*/
function updatePdiName(){
	$.ajax({
 		type : "POST",
 	//	url : sys.rootPath + "/productdataitems/deleteValue.html",
        success : function(resultdata) {},
 	});
} 
 
/*
 *下拉菜单控制增加值
 */
 function Show(id)
{
if(id=="0"){
 layer.msg("请选择", {
	            icon : 3
	        });
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
               layer.msg("至多填写十个数据项值", {
	            icon : 3
	        });
           }  
           
 }
 /*
 *删除模板行
 */

 function deleteRow(obj) {
 
 var inputs = obj.parentNode.parentNode.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
	 	var idVal = inputs[0].value;
	 	
	 }
	var els =document.getElementsByName("pdivName");
     if(els.length>1){
        if("" != idVal){
	 	$.ajax({
	 		type : "POST",
	 		url : sys.rootPath + "/productdataitems/deleteValue.html",
	 		data : {
	             "pdivId" : idVal,
	         },
	        dataType : "json",
	        success : function(resultdata) {
	        	 //移除节点(原节点)
				var tbody = obj.parentNode.parentNode;
 				tbody.parentNode.removeChild(tbody);
	        },
	 	});
 	}else{
 		//移除节点(新增加的节点)
		var tbody = obj.parentNode.parentNode;
 		tbody.parentNode.removeChild(tbody);
 	}
        
        }else{
         layer.msg("请至少保证有一个数据项", {
	            icon : 3
	        });
        }
       
 	
 }

/**
 *向模板中填充值
 */
 function setValue(){
	var modelVal = $("#pdivName").val();
	var modelIdVal = $("#pdivId").val();
 	$("#pdivName").val("");
 	$("#pdivId").val("");
 	var tbody=addRow();
	$("#pdivName").val(modelVal);
	$("#pdivId").val(modelIdVal);
 }
 
 /**
 *修改或者添加节点数据
 */
 function update(obj){
 	var nameVal;
 	var inputs = obj.parentNode.parentNode.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
	 	var idVal = inputs[0].value;
	 	nameVal = inputs[1].value;
	 }
	 
 	var pdiId = $("#pdiId").val();
	var notExist = true;
	var table = document.getElementById("addTable");
	//数据验证
	$.ajax({
 		type : "POST",
 		async:false,
 		url : sys.rootPath + "/productdataitems/checkpdivName.html?pdiId=" + pdiId + "&pdivName=" + nameVal,
 		dataType : "json",
        success : function(resultdata) {
       		notExist = resultdata;
        	if(notExist == false){
        		layer.msg("数据已经存在，请重新输入",{
        			icon : 2,
        		});
        		//添加一行数据
        		var modelVal = $("#pdivName").val();
				var modelIdVal = $("#pdivId").val();
				$("#pdivName").val("");
 				$("#pdivId").val(idVal);
 				addRow();
 				$("#pdivName").val(modelVal);
				$("#pdivId").val(modelIdVal);
        		
        		//删除该行
        		var tbody = obj.parentNode.parentNode;
 				tbody.parentNode.removeChild(tbody);
        		
        		nameVal = "";
        	}
        },
 	});
 	
 	if("" == idVal){
 		//新添节点'/productdataitems/addValue.html'
 		$.ajax({
	 		type : "POST",
	 		url : sys.rootPath + "/productdataitems/addValue.html?pdiId=" + pdiId,
	 		data : {
	             "pdivName" : nameVal,
	         },
	        dataType : "json",
	        success : function(resultdata) {
	        },
	 	});
 	}
  else if(""==nameVal){
 	  layer.msg("请填写数据项值", {
	            icon : 3
	        });
 	}
 	else if ("" != idVal && "" != nameVal){
 		//修改节点url = '/productdataitems/editValue.html?pdivId=' + pdivId
 		
 		$.ajax({
	 		type : "POST",
	 		url : sys.rootPath + "/productdataitems/editValue.html?pdiId=" + pdiId + "&pdivId=" + idVal,
	 		data : {
	             "pdivName" : nameVal,
	         },
	        dataType : "json",
	        success : function(resultdata) {
	        
	        },
	 	});
 	}
 }
 
 function buttonEdit() {
 		var pdiId = $("#pdiId").val();
 		var pdiName=$("#pdiName").val();
 		if(pdiName!=null&&pdiName!=""){
    		var checkpdiName =false;
	if(pdiName!=null && pdiName!="" && pdiName.length > 0){

		var notExist = true;
		$.ajax({
			async:false,
			type : "POST",
			url : sys.rootPath +'/productdataitems/checkpdiName.html',
			data : {
				"pdiName" : pdiName
			},
			dataType : "json",
			success : function(resultdata) {
				notExist = resultdata;
				if(notExist == false){
        		layer.msg("数据已经存在，请重新输入",{
        			icon : 2,
        		});
   
				}else{
				 $.ajax({
            type : "POST",
            url : sys.rootPath + "/productdataitems/editValue.html?pdiId=" + pdiId,
            data : {
                "pdiId" : pdiId,
                "pdiName":pdiName
                
            },
            dataType : "json",
            success : function(resultdata) {
         
            
			},
            error : function(errorMsg) {
                layer.msg("服务器未响应,请稍后再试", {
                    icon : 3
                });
            }
        });
				}
			}
		});
		checkpdiName = notExist;
		if(checkpdiName == false){
			return;
		}
	}else{
		 layer.msg("该数据项名称已存在", {
	            icon : 3
	        });
		$("#pdiName").focus();
		checkpdiName =  false;
		return;
	}
        }else{
          layer.msg("请输入数据项", {
	            icon : 3
	        });
        }

}



/*  */
</script>
<div>
	<input type="hidden" id="pdiId" value="${id}" />
</div>
<div style="margin-left:15px;margin-right:15px">
<form id="addForm" name="addForm" class="form-horizontal" role="form" method="post">
<div>
	<input type="hidden" name="pdiId" value="${id}" />
</div>
<div>数据项名称</div>
<div><input type="text" class="form-control" name="pdiName" id="pdiName" onchange="buttonEdit()" value="${productDataitems.pdiName}"/></div>  
<br>
<div>填写方式</div>
<div>
	<select class="form-control" disabled="disabled">
		<option value="0">${productDataitems.htmlTag.htName }</option>
	</select>
</div>
<br>
</form>
	<c:if test="${productDataitems.htmlTag.htName=='多选'||productDataitems.htmlTag.htName=='单选'
	||productDataitems.htmlTag.htName=='下拉'}">
	<div id="oprea">
		<table style="width:100%" id="addTable" >
		<thead>
			<tr style="text-align:center">					
				<td style="width:50%">选项名称</td>
				<td style="width:50%">操作</td>
			</tr>
		</thead>
		<tbody id="templeteTBody">  
			<c:if test="${empty productDataitems }">
			<tr style="height:50px" id="copyTD">
				<td style="text-align:center"><input type="text" name="pdivName" id="pdivName"  /></td> 
				<td style="text-align:center"><input type="button" class="btn btn-primary btn-sm" value="删除" onclick="deleteRow(this)"/></td>
			</tr>
			</c:if>
			<c:if test="${!empty productDataitems }">
			<c:forEach items="${pdivNameList}" var="pdivNameList"> 
			<tr style="height:50px" id="copyTD">
				<td style="text-align:center;display:none"><input type="text" name="pdivId" id="pdivId" value="${pdivNameList.id }"/></td>			
				<td style="text-align:center"><input type="text" name="pdivName" id="pdivName" onchange="update(this)"  value="${pdivNameList.pdivName }"/></td>
				<td style="text-align:center"><input type="button" class="btn btn-primary btn-sm" value="删除" onclick="deleteRow(this)"/></td>
			</tr>
			</c:forEach>
			</c:if>
		</tbody>
		     
		
		  <tfoot id="footTbody">
			<tr>
				<td colspan="4" style="text-align:center"> <input type="button" style="width:150px;" class="btn btn-primary btn-sm"  value="增加" onclick="setValue()"/></td>
			</tr>
	
		</tfoot>
			
		<tr style="height:30px"></tr>
		</table>
	</div>
	</c:if>
</div>
