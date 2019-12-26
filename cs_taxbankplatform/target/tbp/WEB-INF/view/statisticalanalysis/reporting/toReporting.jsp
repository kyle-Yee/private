<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%    
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
 <%-- 
<link href="${ctx }/resources/css/customer/count/default.css" type="text/css" rel="stylesheet"/>
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
			/* document.getElementById("province")[0].selected=true;
			document.getElementById("city")[0].selected=true;
			$("#area").append("<option value='"+0+"'>"+'请选择'+"</option>");
			document.getElementById("area").lastChild.selected=true;
			for(i=0;i<$(".input").length;i++){  
		        if($(".input")[i].value!=null&&$(".input")[i].value!=""){  
		        	$(".input")[i].value="";  
		        }  
		    }  */
			var start = "";
			var end = "";
			
			//清空附带查询
			search();
		    staicData();
		});	
		
		setTimeout('search()', 500);
		setTimeout('staicData()', 500);
		
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
		    staicData();
		});
		
	});
	
	//页面静态数据,不随搜索条件变化。只在页面省市区加载完成时调用一次
	function staicData(){
		var data=$('#form').serialize();

		$.ajax({
			type:"post",
			url:sys.rootPath + "/reporting/toReportlistStatic.html",
			data:data,
			dataType:"json",
			success:function(rdata){
				if(rdata.success){
					$("#t31").html(rdata.actEntity.totalquotachange);
					$("#t32").html(rdata.actEntity.microquotachange);
					$("#t33").html(rdata.actEntity.microProportion+"%");
					$("#t41").html(rdata.actEntity.badNum);
					$("#t42").html(rdata.actEntity.badSum);
					$("#t43").html(rdata.actEntity.badRate+"%");
				}else{
					layer.msg("获取数据失败", {icon : 0});
				}
				$("#loader").addClass('hide');
			},
			error:function(){
				layer.msg("系统发生异常，请联系管理员", {icon : 0});
				$("#loader").addClass('hide');
			}
			
		});
	};
	
	
	
	//查询
	function search(){
		var data=$('#form').serialize();
		$.ajax({
			type:"post",
			url:sys.rootPath + "/reporting/toReportlist.html",
			data:data,
			dataType:"json",
			success:function(rdata){
				if(rdata.success){
					$("#t11").html(rdata.exhibitDatas.totalamount);
					$("#t12").html(rdata.exhibitDatas.microamount);
					$("#t13").html(rdata.exhibitDatas.microamountProportion+"%");
					$("#t21").html(rdata.exhibitDatas.totalquota);
					$("#t22").html(rdata.exhibitDatas.microquota);	
					$("#t23").html(rdata.exhibitDatas.quotaProportion+"%");
				}else{
					layer.msg("获取数据失败", {icon : 0});
				}
				$("#loader").addClass('hide');
			},
			error:function(){
				layer.msg("系统发生异常，请联系管理员", {icon : 0});
				$("#loader").addClass('hide');
			}
			
		});
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
	          
	          var a = $("<a></a>").attr("href", uri + base64(format(template, ctx))).attr("download", "银税合作实际发放贷款情况.xls").appendTo("body");
			  a[0].click();
			  a.remove();
				
// 	          window.location.href = uri + base64(format(template, ctx))
	        }
	      })()
	   	
	     tableToExcel('table','银税合作实际发放贷款情况')
		}
	}
</script>

	<div class="title">银税合作实际发放贷款情况 </div>
	
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
				<button type="button" class="button btn-blue" id="btnSearch">查&nbsp;询</button>
				<button type="button" class="button btn-blue" id="btnExport" onclick="javascript:onTableToExcel();">导&nbsp;出</button>
				<button type="reset" class="button btn-blue" id="btnReset">清&nbsp;空</button>
			</div>
		</form>
	</div>

<div class="row">
	<h3 class="mar-bottom-thirty text-center">银税合作实际发放贷款情况</h3>
	
	<div class="box">
		
		<div id="table">
			<table class="table table-bordered text-center">
				<!-- 1 -->
				<thead>
					<tr>
						<td class="col-xs-3"></td>
						<td class="col-xs-3">总笔数（含大中型企业）（笔）</td>
						<td class="col-xs-3">其中：小微企业合计（笔）</td>
						<td class="col-xs-3">占小微企业笔数（%）</td>
					</tr>
				</thead>
				<tr>
					<td>银税合作授信/贷款笔数</td>
					<td id="t11"></td>
					<td id="t12"></td>
					<td id="t13"></td>
				</tr>
			</table>	
			<table class="table table-bordered text-center">
				<!-- 2 -->
				<thead>
					<tr>
						<td class="col-xs-3"></td>
						<td class="col-xs-3">总金额（含大中型企业）（万元）</td>
						<td class="col-xs-3">其中：小微企业合计（万元）</td>
						<td class="col-xs-3">占小微企业贷款（%）</td>
					</tr>
				</thead>
				<tr>
					<td>银税合作授信/贷款金额</td>
					<td id="t21"></td>
					<td id="t22"></td>
					<td id="t23"></td>
				</tr>
			</table>	
			<table class="table table-bordered text-center">
				<!-- 3 -->
				<thead>
					<tr>
						<td class="col-xs-3"></td>
						<td class="col-xs-3">总金额（含大中型企业）（万元）</td>
						<td class="col-xs-3">其中：小微企业合计（万元）</td>
						<td class="col-xs-3">占小微企业余额（%）</td>
					</tr>
				</thead>
				<tr>
					<td>银税合作贷款余额</td>
					<td id="t31"></td>
					<td id="t32"></td>
					<td id="t33"></td>
				</tr>
			</table>	
			<table class="table table-bordered text-center">
				<!-- 4 -->
				<thead>
					<tr>
						<td class="col-xs-3"></td>
						<td class="col-xs-3">笔数（笔）</td>
						<td class="col-xs-3">金额（万元）</td>
						<td class="col-xs-3">不良率（%）</td>
					</tr>
				</thead>
				<tr>
					<td>银税合作不良贷款</td>
					<td id="t41"></td>
					<td id="t42"></td>
					<td id="t43"></td>
				</tr>
			</table>
			<div class="remarks"><strong>备注：</strong></br><span>小微企业：指授信金额3000万以下的企业均定义为小微企业</span></div>
		</div>
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