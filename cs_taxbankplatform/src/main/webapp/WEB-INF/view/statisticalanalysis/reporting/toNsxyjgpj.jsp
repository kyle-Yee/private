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

<script type="text/javascript" src="${ctx }/resources/js/customer/regionalcascade/area.js">//引用外部文件area.js</script>
<script type="text/javascript" src="${ctx }/resources/js/customer/statisticalanalysis/reporting/tableToExcelFact.js"></script>
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
		
		/**
		 * 清空按钮
		 */
		$("#btnReset").click(function(){
			/* 
			for(i=0;i<$(".input").length;i++){  
		        if($(".input")[i].value!=null&&$(".input")[i].value!=""){  
		        	$(".input")[i].value="";  
		        }  
		    } 
			document.getElementById("hy")[0].selected=true;
			document.getElementById("province")[0].selected=true;
			document.getElementById("city")[0].selected=true;
			$("#area").append("<option value='"+0+"'>"+'请选择'+"</option>");
			document.getElementById("area").lastChild.selected=true; */
			
			//清空附带查询
			//search();
		});	
		
		setTimeout('search()', 500);
		
		/**
		 * 查询按钮
		 */
		$("#btnSearch").click(function(){
			var start = $("#starttime").val();
			var end = $("#endtime").val();
			if((start!='' && end == '')||(start=='' && end !='')){
				layer.msg("请同时选择起止时间！", {icon : 0});
				return;
			}
			
			if (start>end) {
				layer.msg("请选择正确的起止时间！", {icon : 0});
				return;
			}
		    
		    search();
		});
		
	});
	
	//查询
	function search(){
		var data=$('#form').serialize();
		$.post(sys.rootPath + "/nsxypjyy/toNsxyjgpjlist.html",data,function(sdata){
			var code = sdata.success;
			var rdata ;
			if(code == null || code == "" || typeof code === "undefined"){
				rdata = JSON.parse(sdata); 
			}else{
				rdata= sdata;
			}
			if(rdata.success){
				$("#t11").html(rdata.exhibitDatas.swjgtsyhA);
				$("#t12").html(rdata.exhibitDatas.swjgtsyhB);
				$("#t13").html(rdata.exhibitDatas.swjgtsyhC);
				$("#t14").html(rdata.exhibitDatas.swjgtsyhD);
				$("#t15").html(rdata.exhibitDatas.swjgtsyhW);
				$("#t16").html(rdata.exhibitDatas.swjgtsyhH );
				$("#t21").html(rdata.exhibitDatas.qzsxhsA);
				$("#t22").html(rdata.exhibitDatas.qzsxhsB);	
				$("#t23").html(rdata.exhibitDatas.qzsxhsC);
				$("#t24").html(rdata.exhibitDatas.qzsxhsD );
				$("#t25").html(rdata.exhibitDatas.qzsxhsW );
				$("#t26").html(rdata.exhibitDatas.qzsxhsH );
				$("#t31").html(rdata.exhibitDatas.qztzxhsA);
				$("#t32").html(rdata.exhibitDatas.qztzxhsB);
				$("#t33").html(rdata.exhibitDatas.qztzxhsC );
				$("#t34").html(rdata.exhibitDatas.qztzxhsD );
				$("#t35").html(rdata.exhibitDatas.qztzxhsW );
				$("#t36").html(rdata.exhibitDatas.qztzxhsH );
				$("#t41").html(rdata.exhibitDatas.nsxypjjgyyl+"%");
				$("#t42").html(rdata.exhibitDatas.qyhtzl+"%");
				$("#t43").html(rdata.exhibitDatas.jjjyrs);
			}else{
				layer.msg("获取数据失败", {icon : 0});
			}
			$("#loader").addClass('hide');
		},"json");
		 
	}//search();
			
</script>
<script type="text/javascript">
	
	function onTableToExcel() {
// 		if(window.navigator.userAgent.indexOf('360')==-1){
// 			layer.msg("请选择360极速模式！", {icon : 0});
// 		}
		if (getExplorer() == 'ie' || getExplorer() == 'other') {
			saveAsExcel($("#table").attr('id'));
		} else {
			var tableToExcel = (function() {
	        var uri = 'data:application/vnd.ms-excel;base64,',
	        template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
	          base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
	          format = function(s, c) {
	              return s.replace(/{(\w+)}/g,
	              function(m, p) { return c[p]; }) }
	
	          return function(table, name) {
	          if (!table.nodeType) table = document.getElementById(table)
	          var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
// 	          window.location.href = uri + base64(format(template, ctx))

	          var a = $("<a></a>").attr("href", uri + base64(format(template, ctx))).attr("download", "纳税信用评价结果运用情况.xls").appendTo("body");
			  a[0].click();
			  a.remove();
	        }
	      })()
	   	
	     tableToExcel('table','纳税信用评价结果运用情况')
		}
	}
</script>

	<div class="title">纳税信用评价结果运用情况</div>

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
			<div class="col-md-4">
				<select class="form-control" name="hy" id="hy" >
					<option disabled selected>全行业：</option> 
					<c:forEach items="${hyList }" var="hyz">
						<option value="${hyz.hy_dm}">${hyz.hy_mc}</option>
					</c:forEach>
					<option value="qt">其他</option>
				</select>
			</div>
			<div class="col-md-12 mar-bottom-ten text-center">
				<button type="button" class="button btn-blue" id="btnSearch">查&nbsp;询</button>
				<button type="button" class="button btn-blue" id="btnExport" onclick="javascript:onTableToExcel();">导&nbsp;出</button>
				<button type="reset" class="button btn-blue" id="btnReset">清&nbsp;空</button>
			</div>
		</form>
	</div>

<div class="row">
	<h3 class="mar-bottom-thirty text-center">纳税信用评价结果运用情况</h3>
	
	<div class="box" id="table">
		<table class="table table-bordered text-center">
			<thead>
				<tr>
					<td></td>
					<td>A级</td>
					<td>B级</td>
					<td>C级</td>
					<td>D级</td>
					<td>未评级</td>
					<td>合计</td>
				</tr>
			</thead>
			<!-- 1 -->
			<tr>
				<td>税务机关推送户数（户）</td>
				<td id="t11"></td>
				<td id="t12"></td>
				<td id="t13"></td>
				<td id="t14"></td>
				<td id="t15"></td>
				<td id="t16"></td>
			</tr>
			<!-- 2 -->
			<tr>
				<td>其中：授信户数（户）</td>
				<td id="t21"></td>
				<td id="t22"></td>
				<td id="t23"></td>
				<td id="t24"></td>
				<td id="t25"></td>
				<td id="t26"></td>
			</tr>
			<!-- 3 -->
			<tr>
				<td>其中：拓展新户数（户）</td>
				<td id="t31"></td>
				<td id="t32"></td>
				<td id="t33"></td>
				<td id="t34"></td>
				<td id="t35"></td>
				<td id="t36"></td>
			</tr>
			
			<!-- 4 -->
		</table>
		<table class="table table-bordered text-center">
			<thead>
				<tr>
					<td colspan="2">纳税信用评价结果运用率（%）</td>
					<td colspan="2">新客户拓展率（%）</td>
					<td colspan="2">解决就业人数</td>
				</tr>
			</thead>
			<tr>
				<td colspan="2" id="t41"></td>
				<td colspan="2" id="t42"></td>
				<td colspan="2" id="t43"></td>
			</tr>
		</table>
		<div class="remarks"><strong>备注：</strong></br><span>1. 税务机关推送户数：指企业主动授权平台，平台向银行推送企业税务数据。</span><span>2. 纳税信用评价结果运用率（%）=授信户数合计/税务机关推送户数合计。</span><span>3. 新客户拓展率（%）=拓展新客户合计数/授信户数合计</span></div>
	</div>
</div>

	<!-- 加载效果 start -->
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