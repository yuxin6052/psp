<style lang="less">
    @import '../../../styles/common.less';
</style>
<template>
    <div class="article">
        <Table :columns="columns" :data="detail"></Table>
        <el-amap v-if="detail.length >0"
            style="height: 60vh;margin: 1em 0;" 
            ref="map" 
            vid="amapDemo" 
            :amap-manager="amapManager" 
            :plugin="plugin" 
            :events="events"
            cursor="pointer">
        </el-amap>
        <Row :gutter="16">
            <Col span="8">
                <Table :columns="columnsList1" :data="list1">
                    <template slot="footer">
                        <Page 
                            align="center"
                            simple
                            size="small" 
                            :page-size="10"
                            :total="list1.length"></Page>
                    </template>
                </Table>
            </Col>
            <Col span="8">
                <Table :columns="columnsList2" :data="list2">
                    <template slot="footer">
                        <Page 
                            align="center"
                            simple
                            size="small" 
                            :page-size="10"
                            :total="list2.length"></Page>
                    </template>
                </Table>
            </Col>
            <Col span="8">
                <Table :columns="columnsList3" :data="list3">
                    <template slot="footer">
                        <Page 
                            align="center"
                            simple
                            size="small" 
                            :page-size="10"
                            :total="list3.length"></Page>
                    </template> 
                </Table>
            </Col>
        </Row>
    </div>
</template>
<script>
import { AMapManager, TruckDriving } from 'vue-amap'
export default {
    data () {
        let self = this;
        return {
            columns: [
                {title: "运单编号", key: 'shipmentId'},
                {title: "收货人", key: 'consignee'},
                {title: "运单状态", key: 'status'},
                {title: "司机", key: 'driverName'},
                {title: "车牌号", key: 'vehicleNo'},
                {title: "运单创建时间", key: 'predictDepartTime'},
                {title: "运单完成时间", key: 'predictDepartTime'},
            ],
            detail: [], 
            columnsList1: [
                { title: "传感器编号", key: 'index'},
                {title: "传感器类型", key: 'type'},
                {title: '操作', width: 80, render:(h, params)=>{
                    h('div', [
                        h('Button', {
                            props: {
                                type: 'primary',
                                size: 'small'
                            },
                            on: {
                                click: ()=>{

                                }
                            }
                        }, "导出")
                    ])
                }},
            ],
            list1: [],
            columnsList2: [
                {title: "时间", key: "time", align: 'center'},
                {title: "值/℃", key: "value", align: 'center'},
            ],
            list2: [],
            columnsList3: [
                {title: "时间", key: "time", align: 'center'},
                {title: "事件", key: "event", align: 'center'},
            ],
            list3: [],
            events: {
                init: (o) => {
                    let path = [];
                    path.push({lnglat: self.detail[0].startPos});
                    path.push({lnglat: self.detail[0].endPos});
                    let truckdriving = new AMap.TruckDriving({
                        size: 2 ||self.detail[0].size,
                        map: self.$refs.map.$$getInstance(),
                        autoFitView: true,
                    })
                    truckdriving.search(path, (status, result)=>{
                        console.log(status, result)
                    });
                }
            },
            plugin: ['ToolBar', 'PlaceSearch', 'TruckDriving', {
                pName: 'MapType',
                defaultType: 0,
                events: {
                    init(o) {
                        console.log(o);
                    }
                }
            }],
            amapManager: "",
        }
    },
    methods: {
        async getDetail(){
            if (this.detail.length > 0)
                this.detail.splice(0);
            let res = await this.$api.getShipmentDetail(this.$route.params);
            if (res.errorCode!=0)
                this.$Message.error(res.errorMsg);
            else
            {
                let d = this.detail;
                this.detail.push(res.data.shipment);
                d[0].startPos = [d[0].fromLongitude, d[0].fromLatitude];
                d[0].endPos = [d[0].toLongitude, d[0].toLatitude];
                console.log(this.detail);
            }
        },
        getList1(){

        },
        getList2(){

        },
        getList3(){
            
        }
    },
    created () {
        this.amapManager = new AMapManager();
        //this.getDetail();
    },
    mounted() {
        this.getDetail();

    }
}
</script>
