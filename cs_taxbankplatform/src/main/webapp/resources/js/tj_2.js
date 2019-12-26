function tj_2() {
    var myChart = echarts.init(document.getElementById('tj_2'));
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
        color: ['#4F9D9D', '#FF8040'],
        legend: {
            data: [
                {name: '获批户数', icon: 'rect'},
                {name: '未获批户数', icon: 'rect'}],
            top: 20,//高度位置
            textStyle: {
                color: '#fff',
                fontSize: 13,
                fontWeight: 'bold'
            }
        },
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
                name: '获批户数',
                type: 'line',
                stack: '金额',
                areaStyle: {normal: {}},
                data: hpslvalue
            },
            {
                name: '未获批户数',
                type: 'line',
                stack: '金额',
                areaStyle: {normal: {}},
                data: whpslvalue
            }
        ]
    };

    myChart.setOption(option);
}