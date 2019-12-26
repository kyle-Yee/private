<html>
<script src="resources/js/echarts.js"></script>
<script>
    //解决IE浏览器不支持console，报错未定义问题
    window.console = window.console || (function () {
        var c = {};
        c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function () {
        };
        return c;
    })();
</script>
<body style="height:100%">
<div style="background-image:url(resources/images/1.png);position:absolute;left:0;top:0;width:100%;height:100%;">
    <img src="resources/images/1.png" style="width:100%;position:absolute;left:0;top:0;">
    <div style="width:100%;height:60px;text-align:center;color:#fff;font-weight:bold;font-size:16px;margin-bottom: 30px;">
        <img src="resources/images/2222.png" style="width:100%;position:absolute;left:0;top:0;">
    </div>
    <div id="tj_one">
        <div style="width:65%;margin-left:20px;height:470px;float:left;">
            <img id="head_img" src="resources/images/3.png" style="width:100%;height:100%;position: relative;">
            <div id="tj_1" style="width:65%;height:470px;position:absolute;left:0;top:70px;">
            </div>
        </div>
        <div style="width:30%;height:160px;float:left;margin-left: 20px;">
            <img src="resources/images/3.png" style="width:100%;height:100%;position: relative;">
            <div id="tj_2" style="width:30%;height:160px;position:absolute;left:68%;top:70px;">
            </div>
        </div>
        <div style="width:30%;height:150px;float:left;margin-left: 20px;margin-top:10px;">
            <img src="resources/images/3.png" style="width:100%;height:100%;position: relative;">
            <div id="tj_3" style="width:30%;height:150px;position:absolute;left:68%;top:250px;">
            </div>
        </div>
        <div style="width:30%;height:150px;float:left;margin-left:20px;padding-top:10px;    margin-bottom: 10px;">
            <img src="resources/images/4.png" style="width:100%;height:100%;position: relative;">
            <table style="position:absolute;left:69%;top:460px;color:#fff;text-align: center;    width: 350px;">
                <tr>
                    <td style="font-size: 13px;  ">申请金额：</td>
                    <td id="sqje" style="font-size: 13px;text-align: right;font-size: 13px;"></td>
                    <td style="text-align: right;padding-left: 20px;font-size: 13px;   ">申请总数量：</td>
                    <td id="sqzs" style="text-align: right;font-size: 13px;"></td>
                </tr>
                <tr>
                    <td style="font-size: 13px;padding-top:10px;padding-bottom:10px;">授信金额：</td>
                    <td id="sxje" style="font-size: 13px;text-align: right;font-size: 13px;"></td>
                    <td style="font-size: 13px;text-align: right;font-size: 13px;  ">已获批数量：</td>
                    <td id="hpsl" style="font-size: 13px;text-align: right;font-size: 13px;"></td>
                </tr>
                <tr>
                    <td style="font-size: 13px; ">贷款余额：</td>
                    <td id="dkye" style="font-size: 13px;text-align: right;font-size: 13px;"></td>
                    <td style="font-size: 13px;text-align: right;font-size: 13px;  ">未获批数量：</td>
                    <td id="whpsl" style="font-size: 13px;text-align: right;font-size: 13px;"></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="tj_two" style="display:none">
        <div style="width:47%;margin-left:30px;height:230px;float:left;">
            <img id="head_img2" src="resources/images/4sqje.png" style="width:100%;height:100%;position: relative;">
            <div id="tj_4" style="width:600px;height:230px;position:absolute;left:22px;top:95px;">
            </div>
        </div>
        <div style="width:47%;margin-left:20px;height:230px;float:left;">
            <img id="head_img3" src="resources/images/4sxje.png" style="width:100%;height:100%;position: relative;">
            <div id="tj_5" style="width:600px;height:230px;position:absolute;left:50%;top:95px;">
            </div>
        </div>
        <div style="width:47%;margin-left:30px;height:230px;float:left;    margin-top: 10px;    margin-bottom: 20px;">
            <img id="head_img4" src="resources/images/4dkye.png" style="width:100%;height:100%;position: relative;">
            <div id="tj_6" style="width:600px;height:230px;position:absolute;left:22px;top:330px;">
            </div>
        </div>
        <div style="width:47%;margin-left:20px;height:230px;float:left;margin-top: 10px;">
            <img id="head_img5" src="resources/images/4sqsl.png" style="width:100%;height:100%;position: relative;">
            <div id="tj_7" style="width:600px;height:230px;position:absolute;left:50%;top:330px;">
            </div>
        </div>
    </div>
</div>
</body>

<script src="resources/js/tj_1.js"></script>
<script src="resources/js/tj_2.js"></script>
<script src="resources/js/tj_3.js"></script>
<script src="resources/js/tj_4.js"></script>
<script src="resources/js/tj_5.js"></script>
<script src="resources/js/tj_6.js"></script>
<script src="resources/js/tj_7.js"></script>
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
                $("#tj_two").css("display", "none");
                $("#tj_one").css("display", "block");
                $("#tj_one").show();
                $("#tj_two").hide();
                divqh = false;
            } else {
                tj2();
                $("#tj_two").css("display", "block");
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