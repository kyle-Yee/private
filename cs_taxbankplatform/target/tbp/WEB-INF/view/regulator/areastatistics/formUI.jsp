<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<%-- <link href="${ctx }/resources/css/customer/count/default.css" type="text/css" rel="stylesheet"/>
<link href="${ctx }/resources/css/customer/count/styles.css" type="text/css" rel="stylesheet"/>
<link href="${ctx }/resources/css/customer/count/loansdetails/styles.css" type="text/css" rel="stylesheet"/> --%>
<link href="${ctx }/resources/css/style/style.css" type="text/css" rel="stylesheet"/>
<!-- <script type="text/javascript" src="${ctx}/resources/js/jquerycharts/highcharts.js"></script> -->
<script type="text/javascript" src="${ctx }/resources/js/customer/regionalcascade/area.js">//引用外部文件area.js </script>
<script type="text/javascript" src="${ctx}/resources/js/customer/regulator/areastatistics/form.js"></script>

<script type="text/javascript">
	/**
	 * 加入时间插件
	 */
	$(function(){
		//查询起止时间
		$("#starttime,#endtime").datetimepicker({
			lang : "ch",
			timepicker : false,
			format : "Y-m-d",
			formatDate : "Y-m-d",
		});
	});
</script>

	<div class="title">按地区查询</div>
	
	<div class="search-box">
		<form id="form" action="">
			<div class="col-md-4">
				<select class="form-control" name="province" id="province">
					<option disabled selected>省/自治区：</option> 
				</select>
			</div>
			<div class="col-md-4">
				<select class="form-control" name="city" id="city">
					<option disabled selected>市/州：</option> 
				</select>
			</div>
			<div class="col-md-4">
				<select class="form-control" name="area" id="area">
					<option disabled selected>区/县：</option> 
				</select>
			</div>
			<div class="col-md-4">
				<div class="ipt-data" id="search_time1">
					<input id="starttime" name="starttime" type="text" class="form-control dateImg"  placeholder="时间起">
				</div>
			</div>
			<div class="col-md-4">
				<div class="ipt-data" id="search_time2">
					<input id="endtime" name="endtime" type="text" class="form-control dateImg" placeholder="时间止">
				</div>
			</div>
			<div class="col-md-12 mar-bottom-ten text-center">
				<button type="button" class="button btn-blue"  id="btnSearch">查&nbsp;询</button>
				<button type="reset" class="button btn-blue"  id="btnReset">清&nbsp;空</button>
			</div>
		</form>
	</div>
	<div class="row" id="dataALL">
		<%-- <div class="col-md-2 mar-bottom-twenty">
			<div class="border-gray box-count-three font-yellow">
				<img class="mar-top-ten" src="${ctx }/resources/images/qy-one.png" />
				<div class="mar-top-fifteen">下辖区域总数</div>
				<div class="font-eighteen mar-top-ten" id="xzqylb">0</div>
			</div>
		</div> --%>
		<%-- <div class="col-md-2 mar-bottom-twenty">
			<div class="border-gray box-count-three font-blue">
				<img class="mar-top-ten" src="${ctx }/resources/images/qy-two.png" />
				<div class="mar-top-fifteen">注册用户（户）</div>
				<div class="font-eighteen mar-top-ten" id="zcyh">0</div>
			</div>
		</div> --%>
		<div class="col-md-3 mar-bottom-twenty">
			<div class="border-gray box-count-three font-red">
				<img class="mar-top-ten" src="${ctx }/resources/images/qy-three.png" />
				<div class="mar-top-fifteen">申贷户数（户）</div>
				<div class="font-eighteen mar-top-ten" id="rzyh">0</div>
			</div>
		</div>
		<div class="col-md-3 mar-bottom-twenty">
			<div class="border-gray box-count-three font-green">
				<img class="mar-top-ten" src="${ctx }/resources/images/qy-four.png" />
				<div class="mar-top-fifteen">申请贷款（笔）</div>
				<div class="font-eighteen mar-top-ten" id="sqdk">0</div>
			</div>
		</div>
		<div class="col-md-3 mar-bottom-twenty">
			<div class="border-gray box-count-three font-young">
				<img class="mar-top-ten" src="${ctx }/resources/images/qy-five.png" />
				<div class="mar-top-fifteen">授信笔数（笔）</div>
				<div class="font-eighteen mar-top-ten" id="sxbs">0</div>
			</div>
		</div>
		<div class="col-md-3 mar-bottom-twenty">
			<div class="border-gray box-count-three font-purple">
				<img class="mar-top-ten" src="${ctx }/resources/images/qy-six.png" />
				<div class="mar-top-fifteen">授信总额（万元）</div>
				<div class="font-eighteen mar-top-ten" id="sxze">0</div>
			</div>
		</div>
		
		<div class="col-xs-12 mar-bottom-twenty" style="display:none;">
			<div class="border-gray chart-box">
				<div id="container1"></div>
			</div>
		</div>
		<div class="col-xs-12 mar-bottom-twenty">
			<div class="border-gray chart-box">
				<div id="container2"></div>
			</div>
		</div>
		<div class="col-xs-12 mar-bottom-twenty">
			<div class="border-gray chart-box">
				<div id="container3"></div>
			</div>
		</div>
		<div class="col-xs-12 mar-bottom-twenty">
			<div class="border-gray chart-box">
				<div id="container4"></div>
			</div>
		</div>
		<div class="col-xs-12 mar-bottom-twenty">
			<div class="border-gray chart-box">
				<div id="container5"></div>
			</div>
		</div>
		<div class="col-xs-12 mar-bottom-twenty">
			<div class="border-gray chart-box">
				<div id="container6" style="min-width:400px;height:320px"></div>
			</div>
		</div>
		<div class="box">
			<table id="table" class="table table-bordered text-center" >
				<thead>
					<td>序号</td>
					<td>下辖区域列表</td>
					<!-- <td>注册用户</td> -->
					<td>通过认证用户</td>
					<td>申请贷款（笔）</td>
					<td>授信笔数（笔）</td>
					<td>授信总额（万元）</td>
				</thead>
				<tfoot>
					<tr class="font-blod">
						<td>合计</td>
						<td id="txzqylb"></td>
						<!-- <td id="tzcyh"></td> -->
						<td id="trzyh"></td>
						<td id="tsqdk"></td>
						<td id="tsxbs"></td>
						<td id="tsxze"></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>

	<div class="alert alert-info" id="dataNo" style="text-align: center;"></div>

	<!-- 加载效果 start  -->
	<div class="loader" id="loader">
		<div class="loader-backdrop loader-fade"></div>
        <div class="loader-inner ball-spin-fade-loader">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
	<!-- 加载效果 end -->