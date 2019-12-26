<html>
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
    .mar-bottom-ten {
        margin-bottom: 10px;
    }
    .mar-bottom-twenty {
        margin-bottom: 20px;
    }
    .dashboard-box {
		height: 250px;
	}
	.dashboard-box > div {
		height: 100%;
	}
	.hiddenDiv {
		display: none;
	}
}
</style>
<body style="height:100%">
<div class="container-fluid">
    <div style="width:100%;height:40px;text-align:center;font-weight:bold;font-size:16px;">
        <h3>银税互动活动开展情况统计</h3>
    </div>

    <div id="tj_one">
        <div class="row">
            <div class="col-sm-8">
            	<div class="dashboard-box" style="height:500px;">
	                <div id="tj_1"></div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="row">
                    <div class="col-sm-12 mar-bottom-ten">
                    	<div class="dashboard-box" style="height:170px;">
                    		<div id="tj_2"></div>
                    	</div>
                    </div>
                    <div class="col-sm-12 mar-bottom-ten">
                    	<div class="dashboard-box" style="height:160px;">
                        	<div id="tj_3"></div>
                        </div>
                    </div>
                    <div class="col-sm-12" style="height:150px;">
                        <div style="border:2px solid #EBBA95;height: 100%;padding: 25px 15px;">
                            <table style="width: 100%;">
                                <tr>
                                    <td style="font-size: 13px;">申请金额：</td>
                                    <td id="sqje" style="text-align: center;font-size: 13px;"></td>
                                    <td style="text-align: center;font-size: 13px;">申请总数量：</td>
                                    <td id="sqzs" style="text-align: right;font-size: 13px;"></td>
                                </tr>
                                <tr>
                                    <td style="font-size: 13px;padding-top:10px;padding-bottom:10px;">授信金额：</td>
                                    <td id="sxje" style="text-align: center;font-size: 13px;"></td>
                                    <td style="font-size: 13px;text-align: center;font-size: 13px;">已获批数量：</td>
                                    <td id="hpsl" style="text-align: right;font-size: 13px;"></td>
                                </tr>
                                <tr>
                                    <td style="font-size: 13px;">贷款余额：</td>
                                    <td id="dkye" style="text-align: center;font-size: 13px;"></td>
                                    <td style="font-size: 13px;text-align: center;">未获批数量：</td>
                                    <td id="whpsl" style="text-align: right;font-size: 13px;"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="tj_two">
        <div class="row">
            <div class="col-md-6 mar-bottom-twenty">
            	<div class="dashboard-box">
	            	<div id="tj_4"></div>
            	</div>
            </div>
            <div class="col-md-6 mar-bottom-twenty">
            	<div class="dashboard-box">
	                <div id="tj_5"></div>
                </div>
            </div>
            <div class="col-md-6">
            	<div class="dashboard-box">
	                <div id="tj_6"></div>
                </div>
            </div>
            <div class="col-md-6">
            	<div class="dashboard-box">
	                <div id="tj_7"></div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

<script src="resources/js/tj.js"></script>
</html>