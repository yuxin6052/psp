<template>
    <div style="width:100%;height:100%;" id="data_source_con"></div>
</template>

<script>
import echarts from 'echarts';

export default {
    name: 'dataSourcePie',
    data () {
        return {
            //
        };
    },
    mounted () {
        this.$nextTick(() => {
            var dataSourcePie = echarts.init(document.getElementById('data_source_con'));
            const option = {
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 'right',
                    data: ['Gateway', 'BLE-Tag', 'ZB-Tag', 'GC-Tag']
                },
                series: [
                    {
                        name: 'Device Type',
                        type: 'pie',
                        radius: '66%',
                        center: ['50%', '60%'],
                        data: [
                            {value: 10650, name: 'Gateway', itemStyle: {normal: {color: '#9bd598'}}},
                            {value: 23468, name: 'BLE-Tag', itemStyle: {normal: {color: '#ffd58f'}}},
                            {value: 17650, name: 'ZB-Tag', itemStyle: {normal: {color: '#abd5f2'}}},
                            {value: 9800, name: 'GC-Tag', itemStyle: {normal: {color: '#ab8df2'}}}
                        ],
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
            dataSourcePie.setOption(option);
            window.addEventListener('resize', function () {
                dataSourcePie.resize();
            });
        });
    }
};
</script>
