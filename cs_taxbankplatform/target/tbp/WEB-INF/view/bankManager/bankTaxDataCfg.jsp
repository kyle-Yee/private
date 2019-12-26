<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>

<link rel="stylesheet" href="${ctx}/resources/css/page/rest.css"/> 
<link rel="stylesheet" href="${ctx}/resources/js/bootstrap/bootstrap.min.css"/>
<style>
body{font-family:'微软雅黑';}
.font-twenty {
	font-size: 20px;
}
.font-grey{color:#999;}
.font-blue{color:#4390b9;}
.border-left{border-left:4px solid #4390b9;margin-bottom:5px;margin-right:20px;}
.mar-tb-thirty{margin:30px 0px;}
.mar-top-ten{margin-top:10px;}
.table{font-size:14px;}
.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th{padding:8px 20px;}
.table>tbody>tr>td:nth-of-type(1){min-width:260px;}
</style>
	<div class="col-xs-12">
			<div class="mar-tb-thirty"><span class="border-left"></span><strong class="font-twenty font-blue">数据配置项</strong></div>
			<table class="table table-bordered text-center">
				<c:forEach items="${businessRegInfo }" var="opt" varStatus="status">
					<tr>
						<td>
							${opt.gsbmc}<div class="font-grey mar-top-ten">（共${opt.allCount}项,已选${opt.perCount}项）</div>
						
						</td>
						<td colspan="2" class="text-left">
							${opt.cftOptions}
						</td>
					</tr>
				</c:forEach>
			</table>
	</div>
