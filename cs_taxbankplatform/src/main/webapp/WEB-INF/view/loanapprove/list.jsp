<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loanapprove/list.js"></script>
<style type="text/css">
	.dlshouwen-grid-headers th{
	   background-color: #00A2CA;
	}
	
    .dlshouwen-grid-header hidden-xs dlshouwen-grid-header can-sort{background-color:red;color:#fff}
   .text-primary{
      color:#00A2CA;
   }
    .pagination > li.active > a, .pagination > li.active > a:hover, .pagination > li.active > a:focus{
     background-color: #00A2CA;
   }
   input {
   	height:30px;
   	width:180px;
   }
</style>
<!-- 首页统计数据 -->
<div class="content-count">
	<input type="hidden" id="switchTime" name="switchTime" value="${countEntity.switchTime }">
	<div class="content-count-div">
		<table class="table" >
			<tr class="content-count-divtr">
				<td rowspan ='2' class="content-count-td text-center" style="background-color: #00A2CA;width:20;heigth:20;" onclick="customSearch(0)">${totalDataEntity.total}<br>全部</td>
	      		<td class="content-count-td text-center" onclick="customSearch('DKZT01')">${totalDataEntity.dsl}<br>待初审</td>
	      		<td class="content-count-td text-center" onclick="customSearch('DKZT02')">${totalDataEntity.dsx}<br>初审已通过</td>
	      		<td class="content-count-td text-center" onclick="customSearch('DKZT03')">${totalDataEntity.ysx}<br>终审已通过</td>
	      		<td class="content-count-td text-center" onclick="customSearch('DKZT04')">${totalDataEntity.whdsx}<br>终审未通过</td>
	      		<td class="content-count-td text-center" onclick="customSearch('DKZT05')">${totalDataEntity.wsl}<br>未受理</td>
			</tr>
			<tr >
				<!-- <td class="content-count-td text-center" ></td> -->
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch('DKZT06')">${totalDataEntity.slbtg}<br>初审未通过</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch('DKZT07')">${totalDataEntity.sxwc}<br>授信完成</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch('DKZT08')">${totalDataEntity.dpzcx}<br>待批准撤销</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch('DKZT09')">${totalDataEntity.ypzcx}<br>已撤销</td>
	      		<td class="content-count-td text-center" style="border-bottom:1px solid #ddd" onclick="customSearch('DKZT10')">${totalDataEntity.ytd}<br>已退单</td>
			</tr>
			<!-- <tr class="content-count-divtr">
				<td class="content-count-td text-center">全部</td>
				<td class="content-count-td text-center">待受理</td>
				<td class="content-count-td text-center">待授信</td>
				<td class="content-count-td text-center">已授信</td>
				<td class="content-count-td text-center">受理不通过</td>
				<td class="content-count-td text-center">未授信</td>
				<td class="content-count-td text-center">未受理</td>
			</tr> -->
		</table>
	</div>
</div>

<div class="row" style = "padding-left: 300px;">
	<div class="row center-block" >
		<div ><span>查询时间&nbsp;&nbsp;:&nbsp;</span><input id="apply_star" type="text"><img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
		<span style = "padding-left: 30px;">&nbsp;&nbsp;至&nbsp;&nbsp;</span><span style = "padding-left: 25px;"></span><input id="apply_end" type="text"><img src="resources/images/rili.png" style="outline: none;width:25px;height:25px; margin-left: -32px;margin-top:-3px;">
		</div>
	</div>
	<div style="width: 500px;height:20px;"></div>
	<div class="row center-block" style="border-top-width: 700px;">
		<div class="pull-left"><span>企业名称&nbsp;&nbsp;:&nbsp;</span><input id="nsrmc" type="text">
			<span style = "padding-left: 30px;">状态&nbsp;&nbsp;:&nbsp;</span>
			<select id="status"  name="status" style = "height:30px;width:180px;">
				<!-- <option value="8" ></option> -->
				<option value="0" >全部</option>
				<!-- 2017/4/6更具新需求 修改银行端显示状态 -->
				<!-- <option value="1" >待受理</option>
				<option value="2" >待授信</option>
				<option value="3" >已授信</option>
				<option value="4" >未授信</option>
				<option value="5" >未受理</option>
				<option value="6" >受理不通过</option>
				<option value="7" >授信完成</option> -->
				
				<option value="DKZT01" >待初审</option>
				<option value="DKZT02" >初审已通过</option>
				<option value="DKZT03" >终审已通过</option>
				<option value="DKZT04" >终审未通过</option>
				<option value="DKZT05" >未受理</option>
				<option value="DKZT06" >初审未通过</option>
				<option value="DKZT07" >授信完成</option>
				<option value="DKZT08" >待批准撤销</option>
				<option value="DKZT09" >已撤销</option>
				<option value="DKZT10" >已退单</option>
				
			</select>
		</div>
	</div>
	<div style="border-top-width: 500px;height:20px;"></div>
	<div class="row center-block">
		<div class="pull-left" style = "padding-left: 70px;">
			<button id="btnEmpty" style = "margin-left: -25px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="clearData()">清理
			</button>
			<button id="btnExport" style = "margin-left: 50px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="exportData()">导出
			</button>
			<button id="btnSearch" style = "margin-left: 50px;background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;" onclick="customSearch('')">搜索
			</button>
			<form action="${ctx }/loanapprove/downloadExport.html" target="_blank" style="display:none" method="post" id="exportForm">
				<input type="hidden" name="A_apply_star" id="A_apply_star">
				<input type="hidden" name="A_apply_end" id="A_apply_end">
				<input type="hidden" name="A_nsrmc" id="A_nsrmc">
				<input type="hidden" name="A_status" id="A_status">
			</form>
		</div>
	</div>
</div>

<!-- <div class="input-group">
	<div class="content-count-div">
		<table  class="table">
		
		<tr class="content-count-divtr">
			<td>查询时间：</td>
			<td><input id="apply_star" type="text"><img src="resources/images/u1537.png"></td>
			<td>至</td>
			<td><input id="apply_end" type="text"><img src="resources/images/u1537.png"></td>
		</tr>
		<tr>
			<td>企业名称：</td>
			<td><input id="nsrmc" type="text"></td>
			<td>状态：</td>
			<td>
				<select id="status"  name="status">
					<option value="0" >全部</option>
					<option value="1" >待受理</option>
					<option value="2" >未受理</option>
					<option value="3" >带授信</option>
					<option value="4" >已授信</option>
					<option value="5" >未获得授信</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan ='2'>
			<button id="btnEmpty" class="btn btn-primary btn-sm" type="button">
				 	<i class="fa fa-search"></i>清空
				</button>
			</td>
			<td colspan ='2' class="content-count-td">
				<button id="btnSearch" class="btn btn-primary btn-sm" type="button">
				 	<i class="fa fa-search"></i>搜索
				</button>
			</td>
		</tr>
			<tr class="content-count-divtr">
				<td>申请时间：<input id="apply_star" type="text"><span>&nbsp;至&nbsp;</span><input id="apply_end" type="text"></td>
				<td class="content-count-td">企业名称：<input id="nsrmc" type="text"></td>
				<td class="content-count-td">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span>状态:</span>
					<select id="status"  name="status">
						<option value="0" >全部</option>
						<option value="1" >待受理</option>
						<option value="2" >未受理</option>
						<option value="3" >带授信</option>
						<option value="4" >已授信</option>
						<option value="5" >未获得授信</option>
					</select>
				</td>
				<td class="content-count-td" >
			         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
				</td>
			</tr>
		</table>
	</div>
</div> -->

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			
			<div class="widget-header">
				<h4 class="widget-title lighter"><!-- 待审批列表 --></h4>
				<div class="widget-toolbar no-border">
					<!-- <a href="#" data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a href="#" data-action="collapse" class="green"><i class="ace-icon fa fa-chevron-up"></i></a> -->
					<a href="#" data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-arrows-alt"></i></a> 
					<a href="#" data-action="collapse" class="green"><i class="ace-icon fa fa-chevron-up"></i></a>
				</div>
			</div>

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<input id="status" type="hidden" value="${status.status }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
			
		</div>
	</div>	
</div>
<div style = "height: 30px;"></div>
<!-- <div id="data">
	<div id="detailslist" class="" >
		<table id="table" class = "table" >
			<thead id="title" class="" >
				<tr>
					<td class="border" ><span class="tabletext" >申请时间</span></td>
					<td class="border" ><span class="tabletext" >企业名称</span></td>
					<td class="border" ><span class="tabletext" >产品名称</span></td>
					<td class="border" ><span class="tabletext" >状态</span></td>
					<td class="border"  ><span class="tabletext" >操作</span></td>
				</tr>
			</thead>
		</table>
	<button id="btnAdd" type="button" onclick="search()" style="background-color: #00A2CA;border-radius: 6.2px;box-shadow: none;color: #ffffff;height: 35px;width: 120px;">保存</button>
</div>
	</div>	
<div id="nodata">

</div> -->

<!-- <script  type="text/javascript">
function search(){
	alert("oooooo");
	var status = 1;
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/loanapprove/list.html?status="+status,
		data:data,
		success:function(data){
		alert("it is ok");
			var list = eval("(" + data + ")");
			var typeData = list.exhibitDatas;  
            if (typeData.length != 0){
            	$("#nodata").hide();
            	$("#data").show();
            	$.each(typeData, function(i, dataList) {  
                    var tbBody = ""  
                    var trColor;  
                    alert(dataList.la_status);
                    /*var td_report = n.report == "有"? 
                    				"<td class='border1'><span onclick='downloadFile(" +n.id +
                    				")' span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad'>" + n.report + "</span></td>":
                    				"<td class='border1'><span onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad'>" + n.report + "</span></td>";
                    tbBody += "<tr >" +
                    		"<td class='border1'>" + "" + "</td>" + 
                    		"<td class='border1' ><span id='"+n.id+"' onclick='toapprove("+n.id+");' onmouseover='shubiaoover(this);' onmouseout='shubiaoout(this);' class='forwad' >" + n.nsrmc + "</span></td>" + 
                    		"<td class='border1'>" + n.sdsj + "</td>" +
                    		"<td class='border1'>" + n.bankname + "</td>" +
                    		"<td class='border1'>" + n.laamount + "</td>" +
                    		"<td class='border1'>" + n.lastatus + "</td>" +
                    		"<td class='border1'>" + n.sxsj + "</td>" +
                    		"<td class='border1'>" + n.creditquota + "</td>" +
                    		"<td class='border1'>" + n.larrate + "</td>" +
                    		td_report+"</tr>"; */
              
                    $("#table").append("<tr><td>"+ dataList.la_status +"</td></tr>"); 
                });  
                var len = exhibitDatas.length;
                for(var i = 0;i<len;i++){
                    $('#table tbody tr:eq('+i+') td:first').text(i+1);
                }
            }else {
            	$("#data").hide();
            	$("#nodata").show().html("<div style='width:1100px;'>" +
						"<span style='margin-left:440px;width:100px;color:gray;font-size:16px;'>" +
						"<img  src='resources/images/uimg01.png' " +
						"style='outline: none;width:70px;height:50px; '/>查询无数据</span></div>");
            }
            
        },  
        error: function(json) {  
            alert("加载失败");  
		}
	});
    
}

</script> -->