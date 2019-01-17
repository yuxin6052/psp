<template>
    <div style="width:100%;height:100%;" id="visite_volume_con"></div>
</template>

<script>
import echarts from 'echarts';
export default {
    name: 'visiteVolume',
    data () {
        return {
            //
        };
    },
    mounted () {
        this.$nextTick(() => {
            let visiteVolume = echarts.init(document.getElementById('visite_volume_con'));
            let xAxisData = [];
            let data1 = [];
            let data2 = [];
            for (let i = 0; i < 20; i++) {
                xAxisData.push('Category' + i);
                data1.push((Math.sin(i / 5) * (i / 5 - 10) + i / 6) * 5);
                data2.push((Math.cos(i / 5) * (i / 5 - 10) + i / 6) * 5);
            }

            const option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    top: 0,
                    left: '2%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data: ['Mon', 'Tues', 'Wed', 'Thurs', 'Fri', 'Sat', 'Sun'],
                    nameTextStyle: {
                        color: '#c3c3c3'
                    }
                },
                series: [
                    {
                        name: 'Shipments',
                        type: 'bar',
                        data: [
                            {value: 128, name: 'Mon', itemStyle: {normal: {color: '#2d8cf0'}}},
                            {value: 108, name: 'Tues', itemStyle: {normal: {color: '#2d8cf0'}}},
                            {value: 136, name: 'Wed', itemStyle: {normal: {color: '#2d8cf0'}}},
                            {value: 159, name: 'Thurs', itemStyle: {normal: {color: '#2d8cf0'}}},
                            {value: 208, name: 'Fri', itemStyle: {normal: {color: '#2d8cf0'}}},
                            {value: 67, name: 'Sat', itemStyle: {normal: {color: '#2d8cf0'}}},
                            {value: 55, name: 'Sun', itemStyle: {normal: {color: '#2d8cf0'}}}
                        ]
                    }
                ]
            };

            visiteVolume.setOption(option);

            window.addEventListener('resize', function () {
                visiteVolume.resize();
            });
        });
    }
};
</script>
