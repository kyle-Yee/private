var Productstatistics = (function ($, undefined) {

    function search() {
        var data = $('#form').serialize();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: sys.rootPath + "/productstatistics/list_new.html",
            data: data,
            success: function (result) {
                var productstatistics = result.productstatistics;
                if (productstatistics && productstatistics.length != 0) {
                    $("#dataNo").hide();
                    $("#dataALL").show();
                    $("#caltr").show();
                    //清除原有数据，防止快速重复点击按钮造成数据重复
                    $("#table tbody").remove();
                    $.each(productstatistics, function (i, n) {
                        var tbBody = "";
                        tbBody += "<tr>" +
                            "<td>" + i + "</td>" +
                            "<td>" + n.tbpname + "</td>" +
                            "<td>" + n.tbpcode + "</td>" +
                            "<td>" + n.businesstype + "</td>" +
                            "<td>" + (n.sxbs==undefined ? 0 : n.sxbs) + "</td>" +
                            "<td>" + (n.sxje==undefined ? 0 : n.sxje) + "</td>" +
                            "<td>" + (n.bll==undefined ? "0.00%" : n.bll) + "</td>" +
                            "</tr>";
                        $("#table").append(tbBody);
                    });
                    var len = productstatistics.length;
                    var h1 = 0;
                    var h2 = 0;
                    for (var i = 0; i < len; i++) {
                        $('#table tbody tr:eq(' + i + ') td:first').text(i + 1);
                        $('#table tbody tr:eq(' + i + ')').each(function () {
                            $(this).find('td:eq(4)').each(function () {
                                h1 += parseInt($(this).text());
                            });
                            $(this).find('td:eq(5)').each(function () {
                                h2 += parseFloat($(this).text());
                            });
                        });
                    }
                    $("#sxbs, #sxzbs").text(h1);
                    $("#sxje, #sxze").text(h2.toFixed(2));
                } else {
                    $("#caltr").hide();
                    $("#dataNo").show();
                }
                $("#loader").addClass('hide');
            },
            error: function (json) {
                $("#caltr").hide();
                $("#loader").addClass('hide');
                layer.msg("系统发生异常，请联系管理员", {icon: 0});
            }
        });
    }

    return {
        search: search
    }
})(window.jQuery);

/**
 * 查询按钮
 */
$("#btnSearch").click(function () {
    var starttime = $("#starttime").val();
    var endtime = $("#endtime").val();
    if (starttime > endtime) {
        layer.msg("请选择正确的起止时间！", {icon: 0});
        return;
    }
    if (endtime != "" && starttime == "") {
        layer.msg("请输入正确的开始时间!", {icon: 0});
        return;

    }
    if (starttime != "" && endtime == "") {
        layer.msg("请输入正确的结束时间!", {icon: 0});
        return;
    }
    $("#table tbody").html("");
    $("#sxbs, #tsxbs").text("0");
    $("#sxje, #tsxje").text("0");

    Productstatistics.search();
});

/**
 * 清空按钮
 */
$("#btnReset").click(function () {
    Productstatistics.search();
});

$(function () {
    setTimeout('Productstatistics.search()', 500);
});