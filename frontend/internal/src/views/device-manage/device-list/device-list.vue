<style lang="less">
    
</style>
<template>
  <div>
    <Row>
      <Form inline>
        <FormItem label="" >
          <Input v-model="searchForm.tagUid"></Input>
        </FormItem>
       
        <FormItem >
          <Button type="primary" @click="search">Search</Button>
        </FormItem>
      </Form>
    </Row>
    <Table :columns="columns" :data="data">
      <template slot="header">
        <Button type="primary" shape="circle" @click="show=!show" style="margin-left: 1em;">Create</Button>
      </template>
      <template slot="footer">
         <div class="common-page" >
                        <Page
                            align="center" 
                            ref="pager"
                            :current.sync="searchForm.pageNo"
                            :total="total" 
                            :page-size="pageSize" 
                            show-total 
                            @on-change="changePage"></Page>
                    </div>
      </template>
    </Table>
    <Modal v-model="show"
      @on-cancel="hideModal"
      title="Device infomation">
      <Form ref="addForm"  :model="addForm" :rules="rules">
        

          <Row>
            <Col :span="12">
            <FormItem prop="deviceType" label="Device type" :label-width="100">
              <Select v-model="addForm.deviceType" style="width: 150px" placeholder="Please select">
                <Option value="1">Gateway</Option>
                <Option value="0">Tag</Option>
              </Select>
            </FormItem>
            </Col>

            <Col :span="12">
            <FormItem prop="sku" label="SKU" :label-width="100">
           

 <Select v-model="addForm.sku" style="width: 150px" placeholder="Please select">
                <Option value="g-tag">g-tag</Option>
                <Option value="gc-tag">gc-tag</Option>
              </Select>

            </FormItem>
            </Col>

            <Col :span="12">
            <FormItem prop="version" label="Version" :label-width="100">
              <Input v-model="addForm.version" :readonly="type==1"></Input>
            </FormItem>
            </Col>
            
          </Row>

          <Row>

            <Col :span="12">
            <FormItem prop="tagUid" label="Device Uid" :label-width="100">
              <Input v-model="addForm.tagUid" :readonly="type==1"></Input>
            </FormItem>
            </Col>
           
            <Col :span="12">
            <FormItem prop="macId" label="MAC" :label-width="100">
              <Input v-model="addForm.macId" :readonly="type==1"></Input>
            </FormItem>
            </Col>
          </Row>

        </Form>
        
      <template slot="footer">
        <div v-if="type!=1">
          <Button @click="hideModal">Cancel</Button>
          <Button @click="save('addFrom')" type="primary" :loading="loading">Confirm</Button>
        </div>
        <div v-else>
          <Button @click="hideModal">Close</Button>
        </div>
      </template>
    </Modal>

    <Modal v-model="showHistoryFlag"
           @on-cancel="hideHistoryModal"
           title="History">
      <Row>
        <Select v-model="currentSiteCheckedTag" transfer
                v-if="detail[0]&&detail[0].respTags"
                style="width:300px"
                @on-change="getEchartsData">
          <Option v-for="(lb, key) in detail[0].respTags" :key="key" :value="lb.tagId">{{"Tag " + lb.tagUid}}</Option>
        </Select>
      </Row>
      <br>
      <Row :gutter="10" class="margin-top-10">
        <Col :md="8" :lg="8" :style="{marginBottom: '10px'}">
        <Card>
          <p slot="title" class="card-title">Temperature</p>
          <div class="line-chart-con" style="height: 20vh;" >
            <service-requests
                    title="Temperature"
                    id="temperature"
                    ref="temperature">
            </service-requests>
          </div>
        </Card>
        </Col>
        <Col :md="8" :lg="8" :style="{marginBottom: '10px'}">
        <Card>
          <p slot="title" class="card-title">Humidity</p>
          <div class="line-chart-con" style="height: 20vh;">
            <service-requests
                    title="Humidity"
                    id="humidity"
                    ref="humidity"></service-requests>
          </div>
        </Card>
        </Col>
        <Col :md="8" :lg="8" >
        <Card>
          <p slot="title" class="card-title">Shock</p>
          <div class="line-chart-con"  style="height: 20vh;">
            <service-requests
                    title="Shock"
                    id="shock"
                    ref="shock"></service-requests>
          </div>
        </Card>
        </Col>
      </Row>

      <Row :gutter="10" class="margin-top-10">
        <Col :md="8" :lg="8" :style="{marginBottom: '10px'}">
        <Card>
          <p slot="title" class="card-title">Tilt</p>
          <div class="line-chart-con"  style="height: 20vh;">
            <service-requests
                    title="Tilt"
                    id="tilt"
                    ref="tilt"></service-requests>
          </div>
        </Card>
        </Col>
        <Col :md="8" :lg="8" :style="{marginBottom: '10px'}">
        <Card>
          <p slot="title" class="card-title">Light</p>
          <div class="line-chart-con"  style="height: 20vh;">
            <service-requests
                    title="Light"
                    id="light"
                    ref="light"></service-requests>
          </div>
        </Card>
        </Col>
        <Col :md="8" :lg="8">
        <Card>
          <p slot="title" class="card-title">Battery</p>
          <div class="line-chart-con" style="height: 20vh;" >
            <service-requests
                    title="Battery"
                    id="battery"
                    ref="battery"></service-requests>
          </div>
        </Card>
        </Col>
      </Row>
    </Modal>
     
  </div>
</template>
<script>
import moment from 'moment'
import serviceRequests from './components/serviceRequests'

export default {
  components: {
    serviceRequests,
  },
  data () {
    var self =this;
    return {
      currentsite:null,
      currentSiteCheckedLabel: 0,
      currentSiteCheckedTag: null,
      detail: [],
      events: {
                    init: (o) => {
                        this.search();
                    },
      },
      rules: {
        'deviceType':[
          {required: true, message: "Please select device type"}
        ],
        'versionType':[
          {required: true, message: "Please select version"}
        ],
        'tagUid': [
          {required: true, message: 'Please input device Uid'}
        ],
        'macId': [
          {required: true, message: 'Please input MAC'}
        ],
      },
      searchForm: {
        tagUid: "",
        pageSize: 10,
        pageNo: 1,
      },
      addForm: {
        deviceType: "",
        version: "",
        sku: "",
        tagUid: "",
        macId: "",
       
      },
      pos_back: [],
      columns: [
        {
          title: "Device Uid",
          key: "tagUid",
          width: 110,
        },
        {
          title: "Device type",
          key: "deviceType",
          width: 130,
           render: (h,params)=>{
            let text = null;
            if(params.row.deviceType == 1){
              text =  "Gateway";
            }else if(params.row.deviceType == 0){
              text = "Tag"
            }else{
              text = params.row.deviceType
            }
                            return h('span',{
                                props:{}
                            },text)  
                        }
        },
        {
          title: "SKU",
          key: "sku",
          width: 70,
        },
        {
          width: 100,
          title: "Version",
          key: "version",
        },
        {
          title: "Create time",
          key: "createTime",
          width: 160,
            render: (h,params)=>{
            let text = null;
            if(params.row.createTime){
              text = moment(params.row.createTime).format('YYYY-MM-DD HH:mm:ss');
            }
                            return h('span',{
                                props:{}
                            },text)  
                        }
        },
        {
          title: "MAC",
          key: "macId",
          width: 110,
        },
        {
          title: "Last tenant name",
          key: "lastTenantName",
          width: 200,
        },
        {
          title: "Last site",
          key: "lastSite",
          width: 100,
          render: (h,params)=>{
            let text = null;
            if(params.row.lastSite){
              text = params.row.lastSite.siteName;
            }
                            return h('span',{
                                props:{}
                            },text)  
                        }
        },
        {
          title: "Last upload time",
          key: "lastUploadTime",
          width: 160,
          render: (h,params)=>{
            let text = null;
            if(params.row.lastUploadTime){
              text = moment(params.row.lastUploadTime).format('YYYY-MM-DD HH:mm:ss');
            }
                            return h('span',{
                                props:{}
                            },text)  
                        }
        },
        {
          title: "Last battery value",
          key: "lastBatteryValue",
           width: 130,
        },
        {
          title: "Operation",
          width: 250,
          align: 'center',
          render: (h, params)=>{
            return h("div",[
                h('Button', {
                  props: {
                    size: "small",
                  },
                  style: {marginRight:'5px'},
                  on: {
                    click: ()=>{self.showHistory(params.row);}
                  }
                }, "History"),
              h('Button', {
                props: {
                  size: "small",
                },
                style: {marginRight:'5px'},
                on: {
                  click: ()=>{self.showDetail(params.row);}
                }
              }, "Detail"),
              h('Button', {
                props: {
                  size: "small",
                },
                style: {marginRight:'5px'},
                on: {
                  click: ()=>{self.modifier(params.row)},
                }
              }, "Edit"),
              h('Button', {
                props: {
                  size: "small",
                },
                style: {marginRight:'5px'},
                on: {
                  click: ()=>{self.remove(params.row)},
                }
              }, "Delete"),
            ])
          }
        }
      ],
      data: [{}],
      total: 10,
      pageSize:10,
      show: false,
      showHistoryFlag: false,
      loading: false,
      type: 0,
      zoom: 14,
      mapShow: false,
      provinces: [],
      cities: [],
      counties: [],
      provinceId: "",
      _provinceIdBack: '',
      cityId: "",
      _cityIdBack: '',
      countyId: "",
      _countyIdBack: '',
      districtSearch: '',
      plugin: [
        'DistrictSearch'
      ]
       
    }
  },
    mounted(){
     	 	this.search();

    	},
  methods: {
    showDetail(row) {
      this.type = 1;
      for(let key in this.addForm){
        this.addForm[key] = row[key];
         if (key=='deviceType'){
          this.addForm[key] = row[key]+"";
        }
      }
      this.show = true;
    },
    showHistory(row) {
      this.getEchartsData(row.tagUid);
      this.showHistoryFlag = true;
    },
    modifier(row){
      this.type = 2;
      for(let key in this.addForm){
        this.addForm[key] = row[key];
      }
      this.show = true;
    },
    remove(row) {

    },
    getList() {

    },
    _clearFeilds(){
 


      this.$refs.addForm.resetFields();

      this.addForm = Object.assign(this.addForm, {}, {
                    deviceType: "",
                    tagUid: "",
                    macId: "",
                });

    },

    hideModal(){
      this.show = false;
      this._clearFeilds();
    },
    hideHistoryModal(){
      this.showHistory = false;
    },
    save() {
      this.loading = true;
      this.$refs.addForm.validate(async (valid)=>{
                    if(valid) {
                        // 验证通过, 提交运单表单
                        let form = Object.assign({}, this.addForm);
                       
                      
                        let res = await this.$api.addDevice(form);
                        if(res.errorCode == 0){
                            //成功提交
                            this.search();
                            this.searchForm.pageNo = 1;
                            // 还原表单
                            this.hideModal();
                            
                        } else {
                            this.$Message.error(res.errorMsg);
                        }
                        
                    } 
                })

      this.loading = false;
    },
      changePage (index) {
                this.search();
            },
   async search() {
                
                let res = await this.$api.searchDevice(this.searchForm);
                if(res.errorCode==0) {
                    this.data = res.data.tags;
                      
                    this.total = res.data.total;
                    return;
                }
                else
                    this.$Message.error(res.errorMsg);
            },






    async getEchartsData(tagId){
      let res = await this.$api.getMonitorDataByShipmentIdAndTagId(150, tagId);

      if (res.data.errorCode!=0)
        this.$Message.error(res.data.errorMsg);
      else
      {
        this.tagData = res.data.data;
        if(this.tagData.time){
          if(this.tagData.tempValue)
            this.$refs.temperature.render(this.tagData.time, this.tagData.tempValue);
          if(this.tagData.humidityValue)
            this.$refs.humidity.render(this.tagData.time, this.tagData.humidityValue);
          if(this.tagData.shockValue)
            this.$refs.shock.render(this.tagData.time, this.tagData.shockValue);
          if(this.tagData.tiltValue)
            this.$refs.tilt.render(this.tagData.time, this.tagData.tiltValue);
          if(this.tagData.lightValue)
            this.$refs.light.render(this.tagData.time, this.tagData.lightValue);
          if(this.tagData.batteryValue)
            this.$refs.battery.render(this.tagData.time, this.tagData.batteryValue);
        }
      }
    },

}
}
</script>
