<template>
    <div style="width:100%;height:100%;" :id="id"></div>
</template>

<script>
import echarts from 'echarts';
import moment from 'moment'

export default {
    name: 'serviceRequests',
    props: {
        'title': {
            type: String,
            default: '',
        },
        'id': {
            type: String,
            required: true,
        }
    },
    data() {
        return {
            option: {},
            serviceRequestCharts: null,
        }
    },
    methods: {
        render(xData, yData){
            this.option = Object.assign({}, this.option, {
                xAxis: {data: (xData && xData.map((item)=>moment(item).format('YYYY-MM-DD HH:mm:ss'))) || []},
                yAxis: {data: yData || [], type: 'value'},
                series: [
                    {
                        name: this.title,
                        type: 'line',
                        stack: '总量',
                        areaStyle: {normal: {
                            color: '#398DBF'
                        }},
                        data: yData || [],
                    },
                ]
            })
            console.log(this.option)
            this.serviceRequestCharts.setOption(this.option);
        }
    },
    mounted () {
        this.option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            grid: {
                top: '3%',
                left: '1.2%',
                right: '1%',
                bottom: '3%',
                containLabel: true
            },
        };
        this.serviceRequestCharts = echarts.init(document.getElementById(this.id));
        
        window.addEventListener('resize', function () {
            this.serviceRequestCharts.resize();
        });
    },
};
</script>