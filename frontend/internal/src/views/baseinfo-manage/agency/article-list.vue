<style lang="less">
    
</style>
<template>
  <div>
    <Row>
      <Form inline>
        <FormItem label="机构名称" :label-width="80">
          <Input v-model="searchForm.name"></Input>
        </FormItem>
        <FormItem label="联系人" :label-width="80">
          <Input v-model="searchForm.linkman"></Input>
        </FormItem>
        <FormItem label="类型" :label-width="80">
          <Select v-model="searchForm.type" style="width: 150px">
            <Option value="-1">全部</Option>
            <Option value="0">供应商</Option>
            <Option value="1">医疗机构</Option>
          </Select>
        </FormItem>
        <FormItem >
          <Button type="primary" @click="search">查询</Button>
        </FormItem>
      </Form>
    </Row>
    <Table :columns="columns" :data="data">
      <template slot="header">
        <Button type="primary" shape="circle" @click="show=!show" style="margin-left: 1em;">新建机构</Button>
      </template>
      <template slot="footer">
        <Page align="center" :total="total" show-elevator show-total :page-size="20"></Page>
      </template>
    </Table>
    <Modal v-model="show"
      @on-cancel="hideModal"
      title="机构信息">
      <Form ref="form" :rules="rules">
        <FormItem prop="organization" label="机构名称" :label-width="80">
          <Input v-model="addForm.organization" :readonly="type==1"></Input>
        </FormItem>
        <Row>
          <Col :span="12">
            <FormItem label="联系人" :label-width="80">
              <Input v-model="addForm.linkman" :readonly="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem prop="type" label="类型" :label-width="80">
              <Input v-model="addForm.type" :readonly="type==1"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col :span="12">
            <FormItem label="电话" :label-width="80">
              <Input v-model="addForm.phoneNumber" :readonly="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem label="邮箱" :label-width="80">
              <Input v-model="addForm.email" :readonly="type==1"></Input>
            </FormItem>
          </Col>
        </Row>
        <FormItem label="地址" :label-width="80">
          <Row>
            <Col :span="6">
              <Select 
                :disabled="type==1" 
                @on-open-change="(value)=>{if(!value){checkProvince()}}" 
                v-model="provinceId" 
                clearable
                ref="provinceSelect">
                <Option v-for="(p, key) in provinces" 
                  :value="key" :key="p.name+key">{{p.name}}</Option>
              </Select>
            </Col>
            <Col :span="2" align="center">省</Col>
            <Col :span="6">
              <Select :disabled="type==1" 
                @on-open-change="(value)=>{if(!value){checkCity()}}" 
                v-model="cityId" clearable ref="citySelect"
                >
                <Option v-for="(c, key) in cities" :value="key" :key="c.name+key">{{c.name}}</Option>
              </Select>
            </Col>
            <Col :span="2" align="center" >市</Col>
            <Col :span="6">
              <Select :disabled="type==1" 
                v-model="countyId" 
                @on-open-change="(value)=>{if(!value){checkCounty()}}"
                clearable 
                ref="countySelect">
                <Option v-for="(c, key) in counties" :key="c.name+key" :value="key">{{c.name}}</Option>
              </Select>
            </Col>
            <Col :span="2" align="center">县</Col>
          </Row>
          <Row>
            <Input 
              @on-change="getCoordinate(true)" 
              style="margin-top: .5em" 
              v-model="addForm.address" :readonly="type==1">
              <Button slot="append" @click="mapShow=true" icon="ios-location"></Button>
            </Input>
          </Row>
          <Row v-if="addForm.pos.length>0">
            <Icon type="android-pin"></Icon> 
            <span >{{addForm.pos}}</span>
          </Row>
        </FormItem>
        <FormItem label="有效性" :label-width="80"> 
          <Select prop="validity" v-model="addForm.validity" :disabled="type==1">
            <Option value="0">有效</Option>
            <Option value="1">无效</Option>
          </Select>
        </FormItem>
        <FormItem label="备注" :label-width="80">
          <Input type="textarea" v-model="addForm.remark" :rows="3" :readonly="type==1"></Input>
        </FormItem>
      </Form>
      <template slot="footer">
        <div v-if="type!=1">
          <Button @click="hideModal">取消</Button>
          <Button @click="save" type="primary" :loading="loading">保存</Button>
        </div>
        <div v-else>
          <Button @click="hideModal">关闭</Button>
        </div>
      </template>
    </Modal>
    <Modal width="80vw;" v-model="mapShow" @on-cancel="cancel" @on-ok="checkSite()">
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
                <el-amap-marker v-if="addForm.pos.length > 0"
                    :position="addForm.pos" >
                </el-amap-marker>
            </el-amap>
        </Modal>
  </div>
</template>
<script>
import { AMapManager, Geolocation, DispatchSearch} from 'vue-amap';

export default {
  data () {
    var self =this;
    return {
      amapManager: "",
      rules: {
        'organization':[
          {required: true, message: "请填写机构名称"}
        ],
        'type': [
          {required: true, message: '请填写类型'}
        ],
        'validity': [
          {required: true, message: '请选择有效性'}
        ]
      },
      searchForm: {
        name: "",
        linkman: "",
        type: "",
      },
      addForm: {
        name: "",
        type: "",
        linkman: "",
        phoneNumber: "",
        email: "",
        pos: [],
        address: "",
       
      },
      pos_back: [],
      columns: [
        {
          title: "机构名称",
          key: "name",
        },
        {
          title: "类型",
          key: "type",
        },
        {
          title: "联系人",
          key: "linkman",
        },
        {
          title: "电话",
          key: "phoneNumber",
        },
        {
          title: "邮箱",
          key: "email",
        },
        {
          title: "地址",
          key: "address",
        },
        {
          title: "是否有效",
          key: "validity",
        },
        {
          title: "操作",
          width: 200,
          align: 'center',
          render: (h, params)=>{
            return h("div", [
              h('Button', {
                props: {
                  size: "small",
                },
                style: {marginRight:'5px'},
                on: {
                  click: ()=>{self.showDetail(params.row);}
                }
              }, "查看"),
              h('Button', {
                props: {
                  size: "small",
                },
                style: {marginRight:'5px'},
                on: {
                  click: ()=>{self.modifier(params.row)},
                }
              }, "修改"),
              h('Button', {
                props: {
                  size: "small",
                },
                style: {marginRight:'5px'},
                on: {
                  click: ()=>{self.remove(params.row)},
                }
              }, "删除"),
            ])
          }
        }
      ],
      data: [{}],
      total: 10,
      show: false,
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
      ],
      events: {
        init:o=>{
          this.districtSearch = new AMap.DistrictSearch({
              // 关键字对应的行政区级别，country表示国家
              level: 'country',
              //  显示下级行政区级数，1表示返回下一级行政区
              subdistrict: 3
          });
          this.districtSearch.search('中国', function(status, result) {
              // 查询成功时，result即为对应的行政区信息
              self.provinces =  result.districtList[0].districtList;
          })

        },
        click: e=>{
          self.addForm.pos = [e.lnglat.lng, e.lnglat.lat];
          self.$refs.map.amapManager.getMap().setCenter(self.addForm.pos);
        }
      }
    }
  },
  methods: {
    showDetail(row) {
      this.type = 1;
      for(let key in this.addForm){
        this.addForm[key] = row[key];
      }
      this.show = true;
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
      this.provinceId = this._provinceIdBack = '';
      this.cityId = this._cityIdBack = '';
      this.countyId = this._countyIdBack = '';
      this.addForm.pos.splice(0);
      this.pos_back.splice(0);
      this.addForm.address = '';
    },
    hideModal(){
      this.show = false;
      this._clearFeilds();
    },
    save() {
      this.loading = true;
      this.$refs.form.validate(v=>{
        if(v){
            
          this.show = false;
        } else {

        }
      })

      this.loading = false;
    },
    search() {

    },
    _convertAddress(info, flag=false){
      this.provinceId = this.provinces.findIndex((pro)=>{
          return pro.name==info.province
      })
      if(!this._isValidIndex(this.provinceId)){
        this.$refs.provinceSelect.clearSingleSelect();
        this.provinceId = '';
        this._provinceIdBack = '';
        this.$refs.citySelect.clearSingleSelect();
        this.cityId = '';
        this._cityIdBack = '';
        this.$refs.countySelect.clearSingleSelect();
        this.countyId = '';
        this._countyIdBack = '';
        return ;
      }else if(this.provinceId !== this._provinceIdBack) {
        this._provinceIdBack = this.provinceId;
        this.getCities(this.provinceId);
      }

      this.cityId = this.cities.findIndex((pro)=>{
        return info.city == '' ? flag : pro.name == info.city;
      })

      if(!this._isValidIndex(this.cityId)){
        this.$refs.citySelect.clearSingleSelect();
        this.cityId = '';
        this._cityIdBack = '';
        this.$refs.countySelect.clearSingleSelect();
        this.countyId = '';
        this._countyIdBack = '';
        return;
      }else if(this.cityId !== this._cityIdBack){
        this._cityIdBack = this.cityId;
        this.getCounties(this.cityId);
      }

      this.countyId = this.counties.findIndex(pro=>{
        return pro.name == info.district
      })

      if(!this._isValidIndex(this.countyId)) {
        this.$refs.countySelect.clearSingleSelect();
        this.countyId = '';
        this._countyIdBack = '';
        return;
      } else {
        this._countyIdBack = this.countyId;
      }
    },
    _isValidIndex(param){
      return parseInt(param) >= 0;
    },
    checkProvince(){
      let self =this;
      
      if(self._isValidIndex(self.provinceId)){
        if(self.provinceId !== self._provinceIdBack){
          self._provinceIdBack = self.provinceId;
          self.getCities(self.provinceId);
          self.addForm.address=self.provinces[self.provinceId].name;
          self.getCoordinate();
        }
      }else{
        self.addForm.address='';
      }
    },
    checkCity(){
      console.log(this.cityId, this._cityIdBack, this.cityId === this._cityIdBack)
      let r = '';
      if(this.cityId === this._cityIdBack)
        return;
      if(this._isValidIndex(this.provinceId)){
        r += this.provinces[this.provinceId].name;
      } else{
        this.addForm.address = r;
        return;
      }

      if (this._isValidIndex(this.cityId)) {
        r += this.cities[this.cityId].name;
        this._cityIdBack = this.cityId;
        this.getCounties(this.cityId);
      } else {
        this.addForm.address = r;
        return;
      }
      this.addForm.address = r;
      this.getCoordinate();
    },
    checkCounty(){
      let r = '';
      if(this._isValidIndex(this.provinceId)){
        r += this.provinces[this.provinceId].name;
      } else {
        this.addForm.address = r;
        return;
      }
      if (this._isValidIndex(this.cityId)) {
        r += this.cities[this.cityId].name;
      } else {
        this.addForm.address = r;
        return;
      }
      if(this._isValidIndex(this.countyId)) {
        r += this.counties[this.countyId].name;
        if(this._countyIdBack !== this.countyId) {
          this._countyIdBack = this.countyId;
          this.addForm.address = r;
          this.getCoordinate();
        }
      }
      
      
    },
    checkSite(){
      let self= this;
      (new AMap.Geocoder()).getAddress(self.addForm.pos, (status, res)=>{
        if(!res.regeocode)
          return ;

        self.addForm.address = res.regeocode.formattedAddress;

        self._convertAddress(res.regeocode.addressComponent, true);
        self.pos_back = [].concat(self.addForm.pos);
      });
    },
    cancel(){
      this.mapShow=false;
      this.addForm.pos=[].concat(this.pos_back);
      this.$refs.map.amapManager.getMap().setCenter(this.addForm.pos);
    },
    onSearchResult(){

    },
    getCities(value) {
      
      if(this._isValidIndex(value)){
        this.$refs.countySelect.clearSingleSelect();
        this.$refs.citySelect.clearSingleSelect();
        this.cities = [].concat(this.provinces[value].districtList);
        this.countyId = '';
        this._countyIdBack = '';
        this.cityId = '';
        this._cityIdBack = '';
      }
    },
    getCounties(value) {
      if(this._isValidIndex(value)){
        this.$refs.countySelect.clearSingleSelect();
        this.counties = [].concat(this.cities[value].districtList);
        this.countyId = '';
        this._countyIdBack = '';
      }

    },
    getCoordinate(flag){
      console.log(this.cityId, this.countyId, this._cityIdBack, this._countyIdBack, 'gcs')
      let self = this;
      let res = {};
      (new AMap.Geocoder()).getLocation(self.addForm.address, (status, result)=>{
        if(status=='complete'){
          let center = [result.geocodes[0].location.lng, result.geocodes[0].location.lat];
          self.addForm.pos = [].concat(center);
          self.pos_back = [].concat(center);
          self.$refs.map.amapManager.getMap().setCenter(self.addForm.pos);
          res = Object.assign(res,result.geocodes[0].addressComponent);
          flag&&self._convertAddress(res, flag);
        } else {
          self.addForm.pos = [];
        }
        console.log(self.cityId, self.countyId, self._cityIdBack, self._countyIdBack, 'gcp')
      });
      console.log(this.cityId, this.countyId, this._cityIdBack, this._countyIdBack, 'gce')

      
    }
  },
  created () {
    this.amapManager = new AMapManager();
    
  },
  mounted(){
  }
}
</script>
