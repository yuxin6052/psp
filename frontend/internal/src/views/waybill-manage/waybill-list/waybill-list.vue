<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div class="banner">
        <Row >
            <Form :model="searchForm" inline label-position="left" :label-width="70">
                <FormItem label="开始日期">
                    <DatePicker style="width: 200px"
                        type="date" placeholder="选择一个日期" 
                        @on-change="(val)=>{searchForm.startTime=val}"
                        format="yyyy-MM-dd"></DatePicker>
                </FormItem>
                <FormItem label="结束日期">
                    <DatePicker style="width: 200px"
                        type="date" placeholder="选择一个日期"
                        @on-change="(val)=>{searchForm.endTime = val}"
                        format="yyyy-MM-dd"></DatePicker>
                </FormItem>
                <FormItem label="运单状态">
                    <Select style="width: 200px" v-model="searchForm.status">
                        <Option :value="-1">全部</Option>
                        <Option :value="0">未开始</Option>
                        <Option :value="1">在途</Option>
                        <Option :value="2">已完成</Option>
                    </Select>
                </FormItem>
                <FormItem label="运单编号">
                    <Input clearable style="width: 200px" v-model="searchForm.shipmentId"></Input>
                </FormItem>
                <FormItem label="收货人">
                    <Input clearable style="width: 200px" v-model="searchForm.consignee"></Input>
                </FormItem>
                <FormItem label="车牌号">
                    <Input clearable style="width: 200px" v-model="searchForm.vehicleNo"></Input>
                </FormItem>
                <FormItem label="司机">
                    <Input clearable style="width: 200px" v-model="searchForm.driverId"></Input>
                </FormItem>
                <FormItem>
                    <Button style="margin: 0 0 0 -70px;" type="primary" @click="searchForm.pageNo=1;search()">查询</Button>
                </FormItem>
            </Form>
        </Row>
        <div>
            <Table border :columns="feedbacks" :data="recordsData">
                <template slot="header">
                    <Button type="primary" 
                        icon="plus"
                        shape="circle"
                        style="margin: 0 1em;"
                        @click="addShow = !addShow" 
                        >新建运单</Button>
                </template>
                <template slot="footer">
                    <div class="common-page" >
                        <Page
                            ref="pager"
                            :current.sync="searchForm.pageNo"
                            :total="total" 
                            :page-size="pageSize" 
                            show-total 
                            @on-change="changePage"></Page>
                    </div>
                </template>
            </Table>
        </div>
        <Modal ref="modal" title="新建运单"  
            v-model="addShow"
            @on-cancel="cancel">
            <Form ref="addForm" 
            :rules="rules" 
            :model="addForm" 
            :label-width="100">
                <FormItem prop="fromPosition" label="发货地">
                    <Input @on-keyup="changeStartPlace" v-model="addForm.fromPosition">
                        <Button @click="mapShow=true;flag=0;amapManager.getMap().setCenter(addForm.startPos)" slot="append" icon="ios-location"></Button>
                    </Input>
                </FormItem>
                <FormItem prop="toPosition" label="收货地">
                    <Input @on-keyup="changeEndPlace" v-model="addForm.toPosition">
                        <Button @click="mapShow=true;flag=1;amapManager.getMap().setCenter(addForm.endPos);" slot="append" icon="ios-location"></Button>
                    </Input>
                </FormItem>
                <Row>
                    <Col span="12">
                        <FormItem prop="consignee" label="收货人">
                            <Input v-model="addForm.consignee" clearable></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem prop="driverId" label="司机">
                            <Input v-model="addForm.driverId" clearable></Input>
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="运单编号">
                    <Input v-model="addForm.shipmentNo" clearable></Input>
                </FormItem>
                <FormItem prop="vehicleNo" label="车牌号">
                    <Input v-model="addForm.vehicleNo" clearable></Input>
                </FormItem>
                <!--<FormItem prop="date" label="运单日期">-->
                    <!--<DatePicker @on-change="(val)=>{addForm.date = val}" :value="addForm.date" format="yyyy-MM-dd"></DatePicker>-->
                <!--</FormItem>-->
                <FormItem prop="predictDepartTime" label="预计出发时间">
                    <DatePicker @on-change="(val)=>{addForm.predictDepartTime=val}" :value="addForm.predictDepartTime" type="datetime" format="yyyy-MM-dd HH:mm:ss"></DatePicker>
                </FormItem>
                <FormItem label="备注">
                    <Input v-model="addForm.remark" :rows="3" type="textarea"></Input>
                </FormItem>
            </Form>   
            <template slot="footer">
                <div>
                    <Button @click="cancel();addShow = false;">取消</Button>
                    <Button type="primary" @click="handleSubmit('addForm')" :loading="loading">保存运单</Button>
                </div>
            </template> 
        </Modal>
        <Modal width="80vw;" v-model="mapShow" @on-cancel="mapShow=false" @on-ok="checkSite">
            <el-amap-search-box 
                style="position: absolute;top:25px;left:95px;" 
                :on-search-result="onSearchResult"></el-amap-search-box>
            <el-amap
                style="height: 65vh;" 
                ref="map" 
                vid="amapDemo" 
                :amap-manager="amapManager" 
                :zoom="zoom" 
                :plugin="plugin" 
                :events="events"
                cursor="pointer">
                <el-amap-marker 
                    :position="flag ? addForm.endPos: addForm.startPos" >
                </el-amap-marker>
            </el-amap>

        </Modal>
    </div>
</template>

<script>
import moment from 'moment'
import { AMapManager, Geolocation} from 'vue-amap';
// import axios from 'axios'
    export default {
        name: 'feedback',
        data () {
            let self = this;
            return {
                ajaxFeedbackData: [],
                // 每页显示多少条
                pageSize: 10,
                total: 0,
                feedbacks: [
                    {
                        title: '运单编号',
                        key: 'shipmentId',
                        width: 90,
                        align: 'center'
                    },
                    {
                        title: '车牌号',
                        key: 'vehicleNo',
                        align: 'center'
                    },
                    {
                        title: '司机',
                        key: 'driverName',
                        align: 'center'
                    },
                    {
                        title: '收货人',
                        key: 'consignee',
                        align: 'center'
                    },
                    {
                        title: '运单状态',
                        key: 'status',
                        align: 'center',
                        render: (h,params)=>{
                            let text=''
                            if(params.row.status == 0){text = '未开始'}
                            else if(params.row.status==1){text = '在途'}
                            else {text = '结束'}
                            return h('span',{
                                props:{}
                            },text)
                        }
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 180,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'success',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            let status = params.row.status, 
                                                shipmentId = params.row.shipmentId;
                                            self.checkDetail({status, shipmentId});
                                        }
                                    }
                                }, '运单跟踪')
                            ])
                        }
                    }
                ],
                recordsData : [],
                searchForm: {
                    startTime: "",
                    endTime: "",
                    status: "-1",
                    shipmentId: "",
                    car: "",
                    consignee:"",
                    vehicleNo: "",
                    shipmentNo: "",
                    driverName: "",
                    pageSize: 10,
                    pageNo: 1,
                },
                addForm: {
                    fromPosition: "",
                    toPosition: "",
                    shipmentNo: "",
                    predictDepartTime: moment().format("YYYY-MM-DD HH:mm:ss"),
                    remark: "",
                    vehicleNo:"",
                    consignee:"",
                    driverId: "",
                    fromLongitude: "",
                    toLongitude: "",
                    fromLatitude: "",
                    toLatitude:"",
                    startPos: ["116.368904","39.913423"],
                    endPos: ["116.368904","39.913423"],
                },
                addShow: false,
                loading: false,
                mapShow: false,
                rules: {
                    fromPosition: [
                        {required:true, message: "发货地不能为空", trigger:"blur"}
                    ],
                    toPosition: [
                        {required: true, message: "收货地不能为空", trigger:"blur"}
                    ],
                    plate: [
                        {required: true, message: "车牌号不能为空", trigger:"blur"}
                    ],
                    date: [
                        {required: true, type: "string", message: "运单日期不能为空", trigger: "change"},
                    ],
                    predictDepartTime: [
                        {required: true, type:"string", message: "出发时间不能为空", trigger: "change"}
                    ],
                    driverId: [
                        {required: true, message: "司机不能为空", trigger: "blur"}
                    ],
                    consignee: [
                        {required: true, message: "收货人不能为空", trigger: "blur"}
                    ]
                },
                flag: 0, //选择位置结果给谁, 0: 发货地, 1: 收货地
                amapManager: "",
                zoom: 12,
                events: {
                    init: (o) => {
                        self.addForm.startPos = [o.getCenter().lng, o.getCenter().lat];
                        self.addForm.endPos = [o.getCenter().lng, o.getCenter().lat];
                    },
                    'click': (e) => {
                        if(self.flag){
                            self.addForm.endPos = [e.lnglat.lng, e.lnglat.lat];
                            self.$refs.map.amapManager.getMap().setCenter(self.addForm.endPos);
                        }else{
                            self.addForm.startPos = [e.lnglat.lng, e.lnglat.lat];
                            self.$refs.map.amapManager.getMap().setCenter(self.addForm.startPos);
                        }
                    }
                },
                plugin: ['ToolBar', 'Autocomplete', 'PlaceSearch', 'Geolocation', 'CitySearch', {
                    pName: 'MapType',
                    defaultType: 0,
                    events: {
                        init(o) {
                            console.log(o);
                        }
                    }
                }],
            }
        },
        methods: {
            changePage (index) {
                this.search();
            },
            async search() {
                if(this.searchForm.status == -1)
                    this.searchForm.status = "";
                let res = await this.$api.getShipmentList(this.searchForm);
                if(res.errorCode==0) {
                    this.recordsData = res.data.shipments;
                    this.total = res.data.total;
                    return;
                }
                else
                    this.$Message.error(res.errorMsg);
            },
            clearAddForm() {
                this.$refs.addForm.resetFields();
                this.addForm = Object.assign(this.addForm, {}, {
                    fromPosition: "",
                    toPosition: "",
                    shipmentNo: "",
                    predictDepartTime: moment().format("YYYY-MM-DD HH:mm:ss"),
                    remark: "",
                    vehicleNo:"",
                    consignee:"",
                    driverId: "",
                    fromLongitude: "",
                    toLongitude: "",
                    fromLatitude: "",
                    toLatitude:"",
                    startPos: ["116.368904","39.913423"],
                    endPos: ["116.368904","39.913423"],
                });
            },
            cancel() {
               this.clearAddForm();
            },
            handleSubmit(name) {
                this.loading = true;
                //新建运单
                this.$refs[name].validate(async (valid)=>{
                    if(valid) {
                        // 验证通过, 提交运单表单
                        let form = Object.assign({}, this.addForm);
                        form.predictDepartTime = moment(this.addForm.predictDepartTime).valueOf();
                        let res = await this.$api.addShipment(form);
                        if(res.errorCode == 0){
                            //成功提交
                            this.search();
                            this.searchForm.pageNo = 1;
                            // 还原表单
                            this.clearAddForm();
                            this.addShow = false;
                        } else {
                            this.$Message.error(res.errorMsg);
                        }
                        
                    } 
                })
                this.loading = false;
            },
            onSearchResult(pois) {
                let latSum = 0;
                let lngSum = 0;
                if (pois.length > 0) {
                    pois.forEach(poi => {
                        let {lng, lat} = poi;
                        lngSum += lng;
                        latSum += lat;
                    });
                    let center = {
                        lng: lngSum / pois.length,
                        lat: latSum / pois.length
                    };
                    if(this.flag){
                        this.addForm.endPos = [center.lng, center.lat];
                        this.amapManager.getMap().setCenter(this.addForm.endPos);
                    }
                    else {
                        this.addForm.startPos = [center.lng, center.lat];
                        this.amapManager.getMap().setCenter(this.addForm.startPos);
                    }
                }
            },
            checkSite(){
                let self = this;
                let center = this.flag ? this.addForm.endPos : this.addForm.startPos;
                (new AMap.Geocoder()).getAddress(center, (status, res)=>{
                    if(self.flag == 0) {
                        self.addForm.fromPosition = res.regeocode.formattedAddress;
                        self.addForm.startPos = [center[0], center[1]];
                        self.addForm.fromLongitude = center[0];
                        self.addForm.fromLatitude = center[1];
                    }else if(self.flag == 1) {
                        self.addForm.toPosition = res.regeocode.formattedAddress;
                        self.addForm.endPos = [center[0], center[1]];
                        self.addForm.toLongitude = center[0];
                        self.addForm.toLatitude = center[1];
                    }
                });
            },
            changeStartPlace(val) {
                let self = this;
                (new AMap.Geocoder()).getLocation(self.addForm.dispatchPlace, (status, result)=>{
                    if(status=="complete") {
                        let pos = result.geocodes[0].location;
                        this.addForm.startPos = [pos.lng, pos.lat];
                    }
                })
            },
            changeEndPlace(val) {
                let self = this;
                (new AMap.Geocoder()).getLocation(self.addForm.receiptPlace, (status, result)=>{
                    if(status=="complete") {
                        let pos = result.geocodes[0].location;
                        this.addForm.endPos = [pos.lng, pos.lat];
                    }
                })
            },
            checkDetail(filter) {
                this.$router.push({
                    name: "waybill-track",
                    params: filter,
                });
            }
        },
        created () {
           // this.getFeedbackList()
            this.amapManager = new AMapManager();
            
        },
        mounted() {
            this.search();
            
        }
    }
</script>