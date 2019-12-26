<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<style>
	.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
</style>
<link rel="stylesheet"
	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/select2/zh-CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/customer/organization/form.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/upload/ajaxfileupload.js"></script>
	<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/zTreeStyle.css" type="text/css">
	<style>
		.uploadcss {
			text-align: left;
			margin-bottom: 0;
			padding-top: 10px;
		}
		#imgHead{
			width : 150px;
			height : 57px;
		}
		.a-upload {
		    height: 20px;
		    position: relative;
		    cursor: pointer;
		    color: #5993c5;
		    border: none;
		    overflow: hidden;
		    display: inline-block;
		    *display: inline;
		    *zoom: 1
		}
		.a-upload  input {
		    position: absolute;
		    font-size: 100px;
		    right: 634px;
		    top: 0;
		    opacity: 0;
		    filter: alpha(opacity=0);
		    cursor: pointer
		}
	</style>
<div class="page-header">

	<h1>
		<c:if test="${empty organizationEntity}">
		新增组织
		</c:if>
		<c:if test="${!empty organizationEntity}">
		编辑组织
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="orgForm" name="orgForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty organizationEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="orgid" value="${organizationEntity.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="regionid">已开通区域</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
				<select id="regionid"  name="regionid" class="form-control" onchange = "getfunction();" <c:if test="${!empty organizationEntity}">disabled="disabled"</c:if>>
					<option value="">请选择已开通的区域</option>
					<c:forEach var="regionsList" items="${regionsList }" >
						<option value="${regionsList.id}" ${regionsList.id==organizationEntity.regionid?"selected='selected'":""}>${regionsList.regionname}</option>
					</c:forEach>
				</select>
		      </div>
		      </div>
		   </div>
		   
		  
		   	
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="orgname">组织名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="orgname" id="orgname"
		           value="${organizationEntity.orgname }" placeholder="填写组织名称..." maxlength="50"/>
		      </div>
		      </div>
		   </div>	   
		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="otid">组织类型</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      	 <select id="otid"  name="otid" class="form-control" onchange = "getfunction();" <c:if test="${!empty organizationEntity}">disabled="disabled"</c:if> >
					<option value="">请选择组织类型</option>
					<c:forEach var="organizationstypeList" items="${organizationstypeList }" >
						<option value="${organizationstypeList.id}" ${organizationstypeList.id==organizationEntity.otid?"selected='selected'":""}>${organizationstypeList.ot_name}</option>
					</c:forEach>
				 </select>
		      </div>
		      </div>
		   </div>
		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="regionCode">区域级别</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      	 <select id="rcid"  name="rcid" class="form-control" onchange = "getfunction();" > 
					<c:if test ="${empty organizationEntity}">
					<!-- 添加 -->
						<option value="">请选择区域级别</option>
						<c:forEach var="regionclassEntityList" items="${regionclassEntityList}" >
							<option value="${regionclassEntityList.id}">${regionclassEntityList.rcName}</option>
						</c:forEach>
					</c:if>
					<c:if test ="${!empty organizationEntity}">
					<c:forEach var="regionclassEntityList" items="${regionclassEntityList}" >
					<option value="${regionclassEntityList.id}" ${organizationEntity.rcid==regionclassEntityList.id?"selected='selected'":""}>${regionclassEntityList.rcName}</option>
					</c:forEach>
					</c:if>
				 </select>
		      </div>
		      </div>
		   </div>
		   
		    <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="uporgid">上级组织</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      	 <select id="upOrgId"  name="upOrgId" class="form-control"> 
		      <c:if test="${!empty organizationEntity}">
		      <c:forEach var="upOrgIdEntityList" items="${upOrgIdEntityList}" >
					<option value="${upOrgIdEntityList.id}" ${upOrgIdEntityList.id==organizationEntity.upOrgId?"selected='selected'":""}>${upOrgIdEntityList.orgname}</option>
					</c:forEach>
		      <!--  <option value="${organizationEntity.id}" >${organizationEntity.upOrgId}</option> -->
		      </c:if>
				
				 </select>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="regionname">所属区域</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      	<input type="hidden" name="pcid" id="pcid" value="${organizationEntity.pcid}"/>
		      	<c:if test="${!empty organizationEntity}">
		     	<input id="regionname" name="regionname" class="form-control" type="text" readonly placeholder="填写所属区域..." value="${provinceCitiesEntity.pcname}" onclick="showMenu(); return false;" />
		      	</c:if>
		     	<c:if test="${empty organizationEntity}">
		     	<input id="regionname" name="regionname" class="form-control" type="text" readonly placeholder="填写所属区域..." value="" onclick="showMenu(); return false;" />
		     	</c:if>
		     	<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:9999;margin-left:-294px; margin-top: -156px;">
					<ul id="treeDemo" class="ztree" style="margin-top:-245px; width:200px;"></ul>
				</div>
		      </div>
		      </div>
		   </div>
		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="orgcode">组织编码</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="orgcode" id="orgcode" type="text"
		         value="${organizationEntity.orgcode }" placeholder="组织编码..." maxlength="25"/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="orgcode">组织logo</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <%-- <input class="form-control" name="orgcode" id="orgcode" type="text"
		         value="${organizationEntity.orgcode }" placeholder="组织编码..."/> --%>
		         <div class="uploadcss">
					<a href="javascript:void(0);" id="uploadBasicInfoHead" class="a-upload">上传图片（建议大小，长：190px，高：50px）
						<input type="file" onchange="uploadHead()" id="basicInfoHead" name="basicInfoHead" />
					</a>
					<a href="#deleteInfo" id="deletepictrue">删除图片</a>
					</div>
					<div id="layerContent" class="uploadPicture">
						<img id="imgHead" src="" alt="">
						<input type="hidden" id="basicHeadUrl" name="orglogourl" value="${organizationEntity.orglogourl}">
					</div>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">开通状态</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select id="enabled"  name="enabled" class="form-control" >
						<option value="Y" <c:if test="${organizationEntity.enabled == 'Y'}">selected="selected"</c:if> >开通</option>
						<option value="N" <c:if test="${organizationEntity.enabled == 'N'}">selected="selected"</c:if> >关闭</option>
					</select>
				</div>
				</div>
			</div>
		   <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="accredit">授权方式</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select id="accredit"  name="accredit" class="form-control" onchange="changeaccredit();" <c:if test="${organizationEntity.accredit!=''}">disabled="disabled"</c:if>> 
					    <option value="">请选择授权方式</option> 
						<option value="onetimeaccredit" <c:if test="${organizationEntity.accredit == 'onetimeaccredit'}">selected="selected"</c:if> >一次性授权</option>
						<option value="eachloanaccredit" <c:if test="${organizationEntity.accredit == 'eachloanaccredit'}">selected="selected"</c:if> >每笔贷款授权</option>
					</select>
				</div>
				</div>
			</div>
	 	  <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="rzcid">认证协议类型</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      	 <select id="rzcid"  name="rzcid" class="form-control"  <c:if test="${organizationEntity.rzcid!=''||organizationEntity.sqcid!=''}">disabled="disabled"</c:if>>
					<option value="">请选择认证协议类型</option>
					<c:forEach var="rzxyList" items="${rzxyList}" >
						<option value="${rzxyList.id}" ${rzxyList.id eq organizationEntity.rzcid?"selected='selected'":""}>${rzxyList.rzcname}</option>
					</c:forEach>
				 </select>
		      </div>
		      </div>
		   </div> 
		     <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="sqcid">税务协议类型</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      	 <select id="sqcid"  name="sqcid" class="form-control"   <c:if test="${organizationEntity.rzcid!=''||organizationEntity.sqcid!=''}">disabled="disabled"</c:if>>
					<option value="">请选择税务授信协议类型</option>
					<c:forEach var="sqxyList" items="${sqxyList}" >
						<option value="${sqxyList.id}" ${sqxyList.id  eq organizationEntity.sqcid?"selected='selected'":""}>${sqxyList.sqcname}</option>
					</c:forEach>
				 </select>
		      </div>
		      </div>
		   </div> 
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="remark">描述</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="remark" id="remark" type="text"
		         value="${organizationEntity.remark}" placeholder="描述..." maxlength="100"/>
		      </div>
		      </div>
		   </div> 
		  <%--  <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="enabled">有效标志</label>
		      <div class="col-sm-4">
			      <label>
			         <input class="ace ace-switch ace-switch-6" type="checkbox" name="enabled" id="enabled"
			         value="${organizationEntity.enabled }" onclick="checkboxValue(this)"
				     <c:if test="${organizationEntity.enabled == 'Y'}">checked="checked"</c:if>/>
			         <span class="lbl"></span>
			      </label>
		      </div>
		    </div> --%>	
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#orgForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty organizationEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty organizationEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/organization/listUI.html<c:if test="${!empty organizationEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>

<script type="text/javascript">
$(function(){
	debugger;
	var otid = $("#otid").val();
	var accredit = $("#accredit").val();
				var regionLevel = $("#rcid").val();
			    if(otid=="ZZLX002"){
			       if(accredit!=''){
			           $("#accredit").attr("disabled","disabled");
			            $("#sqcid").attr("disabled","disabled");
			            $("#rzcid").attr("disabled","disabled");
			       }else{
			          $("#accredit").removeAttr("disabled");
			       }
			      
				/*   if(accredit=="onetimeaccredit"){
				      $("#rzcid").removeAttr("disabled");
				      $("#sqcid").attr("disabled","disabled");
				  }else if(accredit=="eachloanaccredit"){
				   $("#sqcid").removeAttr("disabled");
				   $("#rzcid").attr("disabled","disabled");
				  }  */
				  
				   
				}else {
				/* 	var rzxy=document.getElementById("rzxy"); */
				    $("#accredit").attr("disabled","disabled");
				    $("#rzcid").attr("disabled","disabled");
				    $("#sqcid").attr("disabled","disabled");
				}
	var imagePath = $("#basicHeadUrl").val();
	if (imagePath!="") {
		<%--  $("#imgHead").attr("src","<%=request.getContextPath()%>/"+ imagePath); --%>
		$("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ imagePath);
		 document.getElementById("deletepictrue").style.display = "block";
		 document.getElementById("uploadBasicInfoHead").style.display = "none";
	}else{
		 document.getElementById("deletepictrue").style.display = "none";
		 document.getElementById("uploadBasicInfoHead").style.display = "block";
	}
		
	$("#deletepictrue").click(function(){
		var imagePath = $("#basicHeadUrl").val();
		if (imagePath!="") {
			if(confirm("是否删除？")){
				deleteImg();
			}
		}else{
			alert("请先上传图片！");
		}
	});
			
});

function deleteImg(){
	var imagePath = $("#basicHeadUrl").val();
	$.ajax({
		url:"<%=request.getContextPath()%>/upload/deleteImage.html",//需要链接到服务器地址   
		data:{
			imagePath:imagePath
		},
	    dataType: 'json',   //json  
	    success: function (data) { 
	    	 //alert(data.msg);
	    }
	});
	$("#basicHeadUrl").val("");
	$("#imgHead").attr("src","");
	document.getElementById("uploadBasicInfoHead").style.display = "block";
	document.getElementById("deletepictrue").style.display = "none";
}
	
function uploadHead(){
		$.ajaxFileUpload({  
		      url:"<%=request.getContextPath()%>/upload/uploadFile.html",//需要链接到服务器地址   
		      secureuri:false,  
		      fileElementId:"basicInfoHead",//文件选择框的id属性  
		      dataType: 'json',   //json  
		      success: function (data) { 
		    	 if(data.status=="0"){
 		        		$("#imgHead").attr("src","<%=request.getContextPath()%>/upload/readImageFile.html?imagePath="+ data.savePath);
// 						$("#imgHead").attr("src","<%=request.getContextPath()%>/"+data.savePath);
						$("#basicHeadUrl").val(data.savePath);
						document.getElementById("uploadBasicInfoHead").style.display = "none";
						document.getElementById("deletepictrue").style.display = "block";
					}
					if (data.status == "1") {
						alert(data.msg);
					}
					if (data.status == "2") {
						alert(data.msg);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('上传失败！');
				}
			});
	};
</script>

<script type="text/javascript" src="${ctx }/resources/js/ztree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript">
		var setting = {
				view: {
					dblClickExpand: false
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeClick: beforeClick,
					onClick: onClick
				}
			};
			
	  		var zNodes;		
	  		$(function(){  
				var str = "[";
			    $.ajax({  
			        async : false,  
			        cache:false,  
			        type: 'POST',  
			        dataType : "json",  
			        url: '<%=request.getContextPath()%>/provincecities/findCities.html',//请求的action路径  
			        error: function () {
			            alert('请求失败');  
			        },  
			        success:function(data){     
			        	 jQuery.each(data, function(i, item) {
			                 str = str + "{ id:" + item.id + ", pId:" + item.pcpid + ", open: false , name:\"" + item.pcname + "\"},";
			             });
			             str = str.substring(0, str.length - 1);
			             str = str + "]";
			             zNodes = eval("(" + str + ")");
			        }  
			    });
			});
	
			function beforeClick(treeId, treeNode) {
				/* var provincecities = treeNode.name;
				var cities = provincecities.substr(provincecities.length-1,provincecities.length);
				var cities1 = provincecities.substr(provincecities.length-2,provincecities.length);
				var cities2 = provincecities.substr(provincecities.length-3,provincecities.length);
				var check = (treeNode && (cities=="市"||cities == "州" ||cities1 == "地区" ||cities2 == "自治区" ||cities == "盟") );
				if (!check) alert("只能选择城市...");
				return check; */
				
			} 
			
			function onClick(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
				nodes = zTree.getSelectedNodes(),
				v = "";
				nodes.sort(function compare(a,b){return a.id-b.id;});
				for (var i=0, l=nodes.length; i<l; i++) {
					v += nodes[i].name + ",";
				}
				if (v.length > 0 ) v = v.substring(0, v.length-1);
				var cityObj = $("#regionname");
				cityObj.attr("value", v);
				var rcid = $("#pcid");
				rcid.attr("value",treeNode.id);
				
			}
	
			function showMenu() {
				var cityObj = $("#regionname");
				var cityOffset = $("#regionname").offset();
				$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	
				$("body").bind("mousedown", onBodyDown);
			}
			function hideMenu() {
				$("#menuContent").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown);
			}
			function onBodyDown(event) {
				if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
					hideMenu();
				}
			}
	
			$(document).ready(function(){
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			});
			
			
			function getfunction(){
				debugger;
				var result = null;
				var regionid = $("#regionid").val();
				var otid = $("#otid").val();
				var regionLevel = $("#rcid").val();
			    if(otid=="ZZLX002"){
				  // var rzxy=document.getElementById("rzxy");
		              $("#accredit").removeAttr("disabled");
		              $("#rzcid").attr("disabled","disabled");
				      $("#sqcid").attr("disabled","disabled");
				   
				}else {
				/* 	var rzxy=document.getElementById("rzxy"); */
				     $("#accredit").attr("disabled","disabled");
				       $("#rzcid").attr("disabled","disabled");
				      $("#sqcid").attr("disabled","disabled");
				     
				}
				if(/* regionid == "" ||  */otid == "" || regionLevel == ""){
					return;
				}
			 
			    $.ajax({   
			        type: 'POST',  
			        dataType : "json", 
			        data : {
			        	regionid : regionid,
			        	otid : otid,
			        	regionLevel : regionLevel,
			        },
			        url: '<%=request.getContextPath()%>/organization/findOrangeLevel.html',  
			        error: function () { 
			            alert('请求失败');  
			        },  
			        success:function(data){  
			        	var obj = document.getElementById("upOrgId");
			        	$("#upOrgId").empty();
			        	for (var i=0; i<data.length; i++){
						obj.add(new Option(data[i].orgname,data[i].id));
			        	}
			        }  
			    });
			  
				
			}
			function changeaccredit(){
              
			   	var accredit = $("#accredit").val();
				var otid = $("#otid").val();
			    if(otid=="ZZLX002"){
			    	   
			       $("#accredit").removeAttr("disabled");
				  // var rzxy=document.getElementById("rzxy");
			
				  if(accredit=="onetimeaccredit"){
				      $("#rzcid").removeAttr("disabled");
				      $("#sqcid").attr("disabled","disabled");
				  }else if(accredit=="eachloanaccredit"){
				   $("#sqcid").removeAttr("disabled");
				   $("#rzcid").attr("disabled","disabled");
				  }else{
				   $("#rzcid").attr("disabled","disabled");
				    $("#sqcid").attr("disabled","disabled");
				  }
				   //$("#rzcid").removeAttr("disabled");
				  
				   
				}else {
				/* 	var rzxy=document.getElementById("rzxy"); */
				    $("#rzcid").attr("disabled","disabled");
				    $("#sqcid").attr("disabled","disabled");
				}
				if(/* regionid == "" ||  */otid == "" ){
					return;
				}
			}
		</script>