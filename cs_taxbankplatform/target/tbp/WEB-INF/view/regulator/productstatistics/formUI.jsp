<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<link href="${ctx }/resources/css/style/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/resources/js/customer/regulator/productstatistics/form.js"></script>

<script type="text/javascript">
    /**
     * 加入时间插件
     */
    $(function () {
        //查询起止时间
        $("#starttime,#endtime").datetimepicker({
            lang: "ch",
            timepicker: false,
            format: "Y-m-d",
            formatDate: "Y-m-d",
        });
    });
</script>

<div class="title">按产品查询</div>

<div class="search-box">
    <form id="form" action="">
        <div class="col-md-3">
            <div class="ipt-data" id="search_time1">
                <input id="starttime" name="starttime" type="text" class="form-control dateImg" placeholder="时间起">
            </div>
        </div>
        <div class="col-md-3">
            <div class="ipt-data" id="search_time2">
                <input id="endtime" name="endtime" type="text" class="form-control dateImg" placeholder="时间止">
            </div>
        </div>
        <div class="col-md-6 mar-bottom-ten middle">
            <button type="button" class="button btn-blue" id="btnSearch">查&nbsp;询</button>
            <button type="reset" class="button btn-blue" id="btnReset">清&nbsp;空</button>
        </div>
    </form>
</div>

<div class="row" id="dataALL">
    <div class="col-md-6 mar-bottom-twenty">
        <div class="border-gray box-count-three font-young">
            <img class="mar-top-ten" src="${ctx }/resources/images/qy-five.png"/>
            <div class="mar-top-fifteen">授信总笔数（笔）</div>
            <div class="font-eighteen mar-top-ten" id="sxzbs">0</div>
        </div>
    </div>
    <div class="col-md-6 mar-bottom-twenty">
        <div class="border-gray box-count-three font-purple">
            <img class="mar-top-ten" src="${ctx }/resources/images/qy-six.png"/>
            <div class="mar-top-fifteen">授信总额（万元）</div>
            <div class="font-eighteen mar-top-ten" id="sxze">0</div>
        </div>
    </div>
    <div class="box">
        <table id="table" class="table table-bordered text-center mar-top-ten">
            <thead>
            <td>序号</td>
            <td>金融机构名称</td>
            <td>金融机构代码</td>
            <td>产品名称</td>
            <td>授信笔数（笔）</td>
            <td>授信金额（万元）</td>
            <td>不良率</td>
            </thead>
            <tfoot>
            <tr class="font-blod" id="caltr">
                <td colspan="4"><strong>合计</strong></td>
                <td id="sxbs"></td>
                <td id="sxje"></td>
                <td>--</td>
            </tr>
            <tr class="font-blod" id="dataNo">
                <td colspan="7">
                    <div id="nodata">
                        <span class="dlshouwen-grid-pager-status text-primary">无查询记录...</span>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<!-- <div class="alert alert-info hide" id="dataNo"></div> -->
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
	