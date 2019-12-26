<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<html>
<head>
    <%
        response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0); //prevents caching at the proxy server
    %>
    <script src="${ctx }/resources/js/echarts.js"></script>
    <!--[if !IE]><!-->
    <script type="text/javascript" src="/tbp/resources/js/jquery/jquery-2.1.4.min.js"></script>
    <!--<![endif]-->
    <!--[if IE]>
    <script type="text/javascript" src="/tbp/resources/js/jquery/jquery-1.11.3.min.js"></script>
    <![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="E=edge;chrome=1"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="renderer" content="ie-stand"/>
    <script>
        //解决IE浏览器不支持console，报错未定义问题
        window.console = window.console || (function () {
            var c = {};
            c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function () {
            };
            return c;
        })();
    </script>
    <style>
        html, body {
            height: 100%;
            width: 100%;
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body style="height:100%">
<div style="background-image:url(<%=request.getContextPath()%>/resources/images/1.png);position:absolute;left:0;top:0;width:100%;height:100%;">
    <img src="<%=request.getContextPath()%>/resources/images/1.png" style="width:100%;position:absolute;left:0;top:0;">
    <div style="width:100%;height:60px;text-align:center;color:#fff;font-weight:bold;font-size:16px;    margin-bottom: 30px;">
        <img src="<%=request.getContextPath()%>/resources/images/2222.png"
             style="width:100%;position:absolute;left:0;top:0;">
    </div>
    <div id="tj_one" style="overflow: hidden;">
        <div id="tj_one_1" style="width:65%;margin-left:20px;height:92%;float:left;">
            <img id="head_img_1" src="<%=request.getContextPath()%>/resources/images/3.png"
                 style="width:100%;height:92%;position: relative;">
            <div id="tj_1" style="width:65%;height:450px;position:absolute;left:1%;top:10%;">
            </div>

        </div>
        <div id="tj_one_2" style="width:30%;height:30%;float:left;margin-left: 20px;">
            <img src="<%=request.getContextPath()%>/resources/images/3.png"
                 style="width:100%;height:100%;position: relative;">
            <div id="tj_2" style="width:30%;height:160px;position:absolute;left:68%;top:70px;">
            </div>

        </div>
        <div id="tj_one_3" style="width:30%;height:30%;float:left;margin-left: 20px;margin-top:10px;">
            <img src="<%=request.getContextPath()%>/resources/images/3.png"
                 style="width:100%;height:100%;position: relative;">
            <div id="tj_3" style="width:30%;height:150px;position:absolute;left:68%;top:42%;">
            </div>

        </div>
        <div id="tj_one_4"
             style="width:30%;height:22%;float:left;margin-left:20px;padding-top:10px;   font-size: 16px; margin-bottom: 10px;">
            <img src="<%=request.getContextPath()%>/resources/images/4.png"
                 style="width:100%;height:100%;position: relative;">
            <table style="position:absolute;left:69%;top:80%;color:#fff;text-align: center;    width: 27%;">
                <tr>
                    <td style="  ">申请金额：</td>
                    <td id="sqje" style="text-align: right;"></td>
                    <td style="text-align: right;padding-left: 20px; ">申请总数量：</td>
                    <td id="sqzs" style="text-align: right;"></td>
                </tr>
                <tr>
                    <td style="padding-top:10px;padding-bottom:10px;">授信金额：</td>
                    <td id="sxje" style="text-align: right;"></td>
                    <td style="text-align: right;  ">已获批数量：</td>
                    <td id="hpsl" style="text-align: right;"></td>
                </tr>
                <tr>
                    <td style="">贷款余额：</td>
                    <td id="dkye" style="text-align: right;"></td>
                    <td style="text-align: right;  ">未获批数量：</td>
                    <td id="whpsl" style="text-align: right;"></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="tj_two" style="visibility:hidden">
        <div id="tj_two_4" style="width:47%;margin-left:30px;height:40%;float:left;">
            <img id="head_img_two_4" src="<%=request.getContextPath()%>/resources/images/4sqje.png"
                 style="width:100%;height:100%;position: relative;">
            <div id="tj_4" style="width:700px;height:300px;position:absolute;left:22px;top:95px;">
            </div>

        </div>
        <div id="tj_two_5" style="width:47%;margin-left:20px;height:40%;float:left;">
            <img id="head_img_two_5" src="<%=request.getContextPath()%>/resources/images/4sxje.png"
                 style="width:100%;height:100%;position: relative;">
            <div id="tj_5" style="width:700px;height:300px;position:absolute;left:50%;top:95px;">
            </div>

        </div>
        <div id="tj_two_6"
             style="width:47%;margin-left:30px;height:40%;float:left;    margin-top: 10px;    margin-bottom: 20px;">
            <img id="head_img_two_6" src="<%=request.getContextPath()%>/resources/images/4dkye.png"
                 style="width:100%;height:100%;position: relative;">
            <div id="tj_6" style="width:700px;height:300px;position:absolute;left:22px;top:53%;">
            </div>

        </div>
        <div id="tj_two_7" style="width:47%;margin-left:20px;height:40%;float:left;margin-top: 10px;">
            <img id="head_img_two_7" src="<%=request.getContextPath()%>/resources/images/4sqsl.png"
                 style="width:100%;height:100%;position: relative;">
            <div id="tj_7" style="width:700px;height:300px;position:absolute;left:50%;top:53%;">
            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctx }/resources/js/tj_1.js"></script>
<script src="${ctx }/resources/js/tj_2.js"></script>
<script src="${ctx }/resources/js/tj_3.js"></script>
<script src="${ctx }/resources/js/tj_4.js"></script>
<script src="${ctx }/resources/js/tj_5.js"></script>
<script src="${ctx }/resources/js/tj_6.js"></script>
<script src="${ctx }/resources/js/tj_7.js"></script>
<script>
    var sqjevalue = new Array();//更新的值
    var sxjevalue = new Array();//更新的值
    var dkyevalue = new Array();//更新的值
    var hpslvalue = new Array();//更新的值
    var whpslvalue = new Array();//更新的值
    var jd = new Array();//更新的值
    var tj4value = new Array();//更新的值
    var tj5value = new Array();//更新的值
    var tj6value = new Array();//更新的值
    var tj7value = new Array();//更新的值
    var dkrq = new Array();
    var divqh = false;
    var hpslsum = 0;
    var whpslsum = 0;
    $(function () {
        var w1 = $("#head_img_1").width();
        var h1 = $("#head_img_1").height();
        $("#tj_1").css("width", w1).css("height", h1);
        var w2 = $("#tj_one_2").width();
        var h2 = $("#tj_one_2").height();
        $("#tj_2").css("width", w2).css("height", h2);
        var w3 = $("#tj_one_3").width();
        var h3 = $("#tj_one_3").height();
        $("#tj_3").css("width", w3).css("height", h3);

        var w4 = $("#head_img_two_4").width();
        var h4 = $("#head_img_two_4").height();
        $("#tj_4").css("width", w4).css("height", h4);

        var w5 = $("#head_img_two_5").width();
        var h5 = $("#head_img_two_5").height();
        $("#tj_5").css("width", w5).css("height", h5);

        var w6 = $("#head_img_two_6").width();
        var h6 = $("#head_img_two_6").height();
        $("#tj_6").css("width", w6).css("height", h6);

        var w7 = $("#head_img_two_7").width();
        var h7 = $("#head_img_two_7").height();
        $("#tj_7").css("width", w7).css("height", h7);

        tj1();
        tj2();
        tj_1();
        tj_2();
        tj_3();
        tj_4();
        tj_5();
        tj_6();
        tj_7();
        window.setInterval(function () {
            if (divqh) {
                tj1();
                $("#tj_two").removeAttr("style");
                //$("#tj_two").css("display","none");
                $("#tj_two").css("visibility", "hidden");
                $("#tj_one").css("display", "block");
                $("#tj_one").show();
                $("#tj_two").hide();
                divqh = false;
            } else {
                tj2();
                $("#tj_two").css("visibility", "inherit");
                $("#tj_one").css("display", "none");
                $("#tj_one").hide();
                $("#tj_two").show();
                divqh = true;
            }
        }, 15000);

    });

    function tj2() {
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/tj/welcomemx.html",
            success: function (data) {
                var dataObj = eval("(" + data + ")");
                for (var i in dataObj) {
                    jd[i] = dataObj[i].jd;
                    tj4value[i] = dataObj[i].sqje;
                    tj5value[i] = dataObj[i].sxje;
                    tj6value[i] = dataObj[i].dkye;
                    tj7value[i] = parseInt(dataObj[i].whpsl) + parseInt(dataObj[i].hpsl);
                }
                tj_4();
                tj_5();
                tj_6();
                tj_7();
            }
        });
    }

    function tj1() {
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/tj/welcome.html",
            success: function (data) {

                var dataObj = eval("(" + data + ")");
                var sqjesum = 0;
                var sxjesum = 0;
                var dkyesum = 0;
                var sqzssum = 0;
                hpslsum = 0;
                whpslsum = 0;
                for (var i in dataObj) {
                    sqjevalue[i] = dataObj[i].sqje;
                    if (dataObj[i].sqje != null && dataObj[i].sqje != "") {
                        sqjesum = parseFloat(dataObj[i].sqje, 10);
                    }

                    sxjevalue[i] = dataObj[i].sxje;
                    if (dataObj[i].sxje != null && dataObj[i].sxje != "") {
                        sxjesum = parseFloat(dataObj[i].sxje, 10);
                    }
                    dkyevalue[i] = dataObj[i].dkye;
                    if (dataObj[i].dkye != null && dataObj[i].dkye != "") {
                        dkyesum = parseFloat(dataObj[i].dkye, 10);
                    }
                    hpslvalue[i] = dataObj[i].hpsl;
                    if (dataObj[i].hpsl != null && dataObj[i].hpsl != "") {
                        hpslsum = parseInt(dataObj[i].hpsl, 10);
                    }
                    whpslvalue[i] = parseInt(dataObj[i].whpsl);
                    if (dataObj[i].whpsl != null && dataObj[i].whpsl != "") {
                        whpslsum = parseInt(dataObj[i].whpsl, 10);
                    }
                    dkrq[i] = dataObj[i].dkrq;
                }
                sqzssum = parseInt(whpslsum, 10) + parseInt(hpslsum, 10);
                $("#sqje").text(sqjesum + '(万)');
                $("#sxje").text(sxjesum + '(万)');
                $("#dkye").text(dkyesum + '(万)');
                $("#sqzs").text(sqzssum);
                $("#hpsl").text(hpslsum);
                $("#whpsl").text(whpslsum);
                tj_1();
                tj_2();
                tj_3();
            }
        });
    }
</script>
</html>