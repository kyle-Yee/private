function tj_3() {
    var myChart = echarts.init(document.getElementById('tj_3'));
    option = {
        title: {
            //text: '占比',
            x: 'center',
            textStyle: {
                color: '#fff',
                fontStyle: 'normal',
                fontWeight: 'bold',
                fontSize: 15
            }
        },
        magrin: [15, 10, 15, 15],//设置标题内边距,上，右，下，左
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)",
            textStyle: {
                color: 'fff'
            }
        },
        color: ['#FF5151', '#4F9D9D'],
        legend: {
            orient: 'vertical',
            left: 'left',
            top: 20,
            left: 20,
            data: ['以获批户数', '未获批户数'],
            textStyle: {
                color: '#fff'
                //fontSize:30
            }
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: 335, name: '以获批户数'},
                    {value: 310, name: '未获批户数'}
                ],
                label: {            //饼图图形上的文本标签
                    normal: {
                        textStyle: {
                            fontSize: 15,   //文字的字体大小,
                            fontWeight: 'bold'
                        }
                    }
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }

                }
            }
        ]
    };

    myChart.setOption(option);
}