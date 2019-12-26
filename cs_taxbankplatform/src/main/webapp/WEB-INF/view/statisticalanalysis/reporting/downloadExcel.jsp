<%
response.reset();
response.setContentType("application/vnd.ms-excel;charset=GBK");
%>
<html>
<head>
	<title>刷卡消费情况</title>	
	<style type="text/css">
		table.common1 { width: 100%;
		font-size: 9pt;
		style-align: center;
		background-color: #ffffff;
		}
		td.formtitle { font-size: 9pt;
		background:#a480b2;
		color:#
		ffffff;
		height:30px;
		text-align: center;}
	</style>
</head>

<body>
<table class="common1" cellpadding="5" cellspacing="1" align="center" >
<tr>
<td class=formtitle colspan="4"><CENTER> 清单</CENTER> </td>
</tr>
<tr>
<td class=formtitle align="center" nowrap style="width:13%">姓名</td>
<td class=formtitle align="center" nowrap style="width:13%">年龄</td>
<td class=formtitle align="center" nowrap style="width:13%">性别</td>
<td class=formtitle align="center" nowrap style="width:13%">家庭住址</td>
</tr>
<tr>
<td align="center" nowrap style="width:13%">张三</td>
<td align="center" nowrap style="width:13%">25</td>
<td align="center" nowrap style="width:13%">男</td>
<td align="center" nowrap style="width:13%">北京中关村</td>
</tr>
</table>
</body>
</html>  