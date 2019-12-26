function tj_1() {
    var myChart = echarts.init(document.getElementById('tj_1'));
    option = {
        title: {
            //text: '金额'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        //color:['#00EC00','#FFFF37','#EA0000'],
        color: ['#ff7575', '#FFFF93', '#BBFFBB'],
        legend: {
            data: [{name: '申请金额', icon: 'rect'},
                {name: '授信金额', icon: 'rect'},
                {name: '贷款余额', icon: 'rect'}],
            //orient: 'vertical',//纵向排列（orient: 'vertical'）
            top: 20,//高度位置
            textStyle: {
                color: '#fff',
                fontSize: 13,
                fontWeight: 'bold'
            }
        },
        // toolbox: {  保持图片
        //   feature: {
        //     saveAsImage: {}
        //}
        //},
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: dkrq,
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff',
                        width: 2
                    }
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff',
                        width: 2
                    }
                }
            }

        ],
        series: [
            {
                name: '贷款余额',
                type: 'line',
                stack: '金额',
                areaStyle: {normal: {}},
                data: dkyevalue
            },
            {
                name: '授信金额',
                type: 'line',
                stack: '金额',
                areaStyle: {normal: {}},
                data: sxjevalue
            },
            {
                name: '申请金额',
                type: 'line',
                stack: '金额',
                areaStyle: {normal: {}},
                data: sqjevalue
            }
        ]
    };

    myChart.setOption(option);
}