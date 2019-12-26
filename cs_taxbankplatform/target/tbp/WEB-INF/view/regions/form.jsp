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
	src="${ctx }/resources/js/customer/regions/form.js"></script>
	<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/zTreeStyle.css" type="text/css">
<div class="page-header">

	<h1>
		<c:if test="${empty regionsEntity}">
		新增开通城市
		</c:if>
		<c:if test="${!empty regionsEntity}">
		编辑开通城市
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="regionForm" name="regionForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty regionsEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="regionid" value="${regionsEntity.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="regionname">开通城市名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
<!-- 		         <input class="form-control" name="regionname" id="regionname"  value="${regionsEntity.regionname }" placeholder="填写开通城市名称..."/> -->
		     	<input id="regionname" name="regionname" class="form-control" type="text" readonly placeholder="填写开通城市名称..." value="${regionsEntity.regionname }" onclick="showMenu(); return false;" />
		     	<input type="hidden" name="regioncode" id="regioncode" value="${regionsEntity.regioncode}">
		     	<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:9999;margin-left:-294px; margin-top: -156px;">
					<ul id="treeDemo" class="ztree" style="margin-top:0; width:200px;"></ul>
				</div>
		      </div>
		      </div>
		   </div>
		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="authorityname">税务机关名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">

		     	<input id="authorityname" name="taxname" class="form-control" type="text" readonly placeholder="填写税务机关名称..." value="${regionsEntity.taxname }" onclick="showauthorityMenu(); return false;" />
		     	<input type="hidden" name="taxid" id="taxid" value="${regionsEntity.taxid}">
		     	<div id="authoritymenuContent" class="menuContent" style="display:none; position: absolute;z-index:9999;margin-left:-294px; margin-top: -156px;">
					<ul id="authoritytreeDemo" class="ztree" style="margin-top:-50px; width:300px;"></ul>
				</div>
		      </div>
		      </div>
		   </div>	
		   <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="enabled">开通状态</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select id="enabled"  name="enabled" class="form-control" >
							<option value="Y" <c:if test="${regionsEntity.enabled == 'Y'}">selected="selected"</c:if> >开通</option>
							<option value="N" <c:if test="${regionsEntity.enabled == 'N'}">selected="selected"</c:if> >关闭</option>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="currentlocation">是否开通站点</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select id="currentlocation"  name="currentlocation" class="form-control" >
							<option value="N" <c:if test="${regionsEntity.currentlocation == 'N'}">selected="selected"</c:if> >否</option>
							<option value="Y" <c:if test="${regionsEntity.currentlocation == 'Y'}">selected="selected"</c:if> >是</option>
						</select>
					</div>
				</div>
			</div>
		   <%-- <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="enabled">开通状态</label>
		      <div class="col-sm-4">
			      <label>
			         <input class="ace ace-switch ace-switch-6" type="checkbox" name="enabled" id="enabled"
			         value="${regionsEntity.enabled }" onclick="checkboxValue(this)"
				     <c:if test="${regionsEntity.enabled == 'Y'}">checked="checked"</c:if>/>
			         <span class="lbl"></span>
			      </label>
		      </div>
		    </div>	 --%>   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="remark">描述</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="remark" id="remark" type="text"
		         value="${regionsEntity.remark }" placeholder="描述..." maxlength="100"/>
		      </div>
		      </div>
		   </div> 
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#regionForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty regionsEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty regionsEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/regions/listUI.html<c:if test="${!empty regionsEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
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
			
	  		var zNodest;		
	  		$(function(){  
				var str = "[";
			    $.ajax({  
			        async : false,  
			        cache:false,  
			        type: 'POST',  
			        dataType : "json",  
			        url: '<%=request.getContextPath()%>/taxauthority/findAuthority.html',//请求的action路径  
			        error: function () {//请求失败处理函数  
			            alert('请求失败');  
			        },  
			        success:function(data){
			        	debugger;//请求成功后处理函数。
			        	for(var i=0;i<data.length;i++){
			        		if(data[i].taxPrcId==""){
			        			str = str + "{ id:\""+ data[i].id + "\", open: false , name:\"" + data[i].taxName + "\"},";
			        		}else{
			        			str = str + "{ id:\""+ data[i].id + "\", pId:\""+ data[i].taxPrcId + "\", open: false , name:\"" + data[i].taxName + "\"},";
			        		}
			        	}
// 			        	 jQuery.each(data, function(i, item) {
// 			                 str = str + "{ id:\""+ item.id + "\", pId:" + item.taxPrcId + ", open: false , name:\"" + item.taxName + "\"},";
// 			        	 });
			             str = str.substring(0, str.length - 1);
			             str = str + "]";//此时str是JSON字符串console.log(str);
			             zNodest = eval("(" + str + ")");//这货到底是做神马啊?把JSON字符串转换成JSON对象 

			        }  
			    });
			});
	
			function beforeClick(treeId, treeNode) {
				var check = (treeNode && !treeNode.isParent);
				
			} 
			
			function onClick(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("authoritytreeDemo"),
				nodes = zTree.getSelectedNodes(),
				v = "";
				c = "";
				nodes.sort(function compare(a,b){return a.id-b.id;});
				for (var i=0, l=nodes.length; i<l; i++) {
					v = nodes[i].name;
					c = nodes[i].id;
				}
				var cityObjname = $("#authorityname");
				var cityObjcode = $("#taxid");
				cityObjname.attr("value",v);
				cityObjcode.attr("value",c);
			}
	
			function showauthorityMenu() {
				debugger;
				var cityObj = $("#authorityname");
				var cityOffset = $("#authorityname").offset();
				$("#authoritymenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("body").bind("mousedown", onBodyDown1);
			}
			function hideMenus() {
				$("#menuContent").fadeOut("fast");
				$("#authoritymenuContent").fadeOut("fast");				
				$("body").unbind("mousedown", onBodyDown1);
			}
			
			function onBodyDown1(event) {
				if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#authoritymenuContent").length>0)) {
					hideMenus();
				}
			}
	
			$(document).ready(function(){
				debugger;
				$.fn.zTree.init($("#authoritytreeDemo"), setting, zNodest);
			});
	</script>
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
			        error: function () {//请求失败处理函数  
			            alert('请求失败');  
			        },  
			        success:function(data){ //请求成功后处理函数。
			        	debugger;
			        	 jQuery.each(data, function(i, item) {
			                 str = str + "{ id:" + item.id + ", pId:" + item.pcpid + ", code:"+item.pccode+", open: false , name:\"" + item.pcname + "\"},";
			        	 });
			        	 
			             str = str.substring(0, str.length - 1);
			             str = str + "]";//此时str是JSON字符串//console.log(str);
			             zNodes = eval("(" + str + ")");//这货到底是做神马啊?把JSON字符串转换成JSON对象 
			        }  
			    });
			});
	
			function beforeClick(treeId, treeNode) {
				 /* var provincecities = treeNode.name;
				var cities = provincecities.substr(provincecities.length-1,provincecities.length);
				var cities1 = provincecities.substr(provincecities.length-2,provincecities.length);
				var cities2 = provincecities.substr(provincecities.length-3,provincecities.length);
				 var cities = provincecities.charAt(provincecities.length-1);
				var cities1 = provincecities.charAt(provincecities.length-2);
				var cities2 = provincecities.charAt(provincecities.length-3); 
				alert(cities+cities1+cities2);
				var check = (treeNode && (cities=="市"||cities == "州" ||cities1 == "地区" ||cities2 == "自治区" ||cities == "盟") );
				if (!check) alert("只能选择城市...");
				return check;  */
				var check = (treeNode && !treeNode.isParent);
				
			} 
			
			function onClick(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
				nodes = zTree.getSelectedNodes(),
				v = "";
				c = "";
				nodes.sort(function compare(a,b){return a.id-b.id;});
				for (var i=0, l=nodes.length; i<l; i++) {
					v = nodes[i].name;
					c = nodes[i].code;
				}
				var cityObjname = $("#regionname");
				var cityObjcode = $("#regioncode");
				cityObjname.attr("value",v);
				cityObjcode.attr("value",c);
			}
	
			function showMenu() {
				debugger;
				var cityObj = $("#regionname");
				var cityOffset = $("#regionname").offset();
				$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	
				$("body").bind("mousedown", onBodyDown);
			}
			function hideMenu() {
				debugger;
				$("#authoritymenuContent").fadeOut("fast");
				$("#menuContent").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown);
			}
			function onBodyDown(event) {
				debugger;
				if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0|| $(event.target).parents("#authoritymenuContent").length>0)) {
					hideMenu();
				};
			}
	
			$(document).ready(function(){
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			});
		</script>