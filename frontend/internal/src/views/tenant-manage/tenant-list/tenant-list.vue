<style lang="less">
    
</style>
<template>
  <div>
    <Row>
      <Form inline>
        <FormItem label="Tenant name" :label-width="130">
          <Input v-model="searchForm.tenantName"></Input>
        </FormItem>
        <FormItem label="Tenant abbreviation" :label-width="130">
          <Input v-model="searchForm.tenantAbbreviation"></Input>
        </FormItem>
        <FormItem label="Tenant Type" :label-width="130">
          <Select v-model="searchForm.tenantType" style="width: 150px" placeholder="Please select" >
            <Option value="-1">All</Option>
            <Option value="1">Standard edition</Option>
            <Option value="2">Professional edition</Option>
          </Select>
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
      title="Tenant information">
      <Form ref="addForm"  :model="addForm" :rules="rules">
        

          <Row>
            <Col :span="12">
            <FormItem prop="tenantName" label="Tenant name" :label-width="100">
          <Input v-model="addForm.tenantName" :readonly="type==1"></Input>
        </FormItem>
          </Col>
          <Col :span="12">
            <FormItem prop="tenantAbbreviation" label="Tenant abbreviation" :label-width="100">
              <Input v-model="addForm.tenantAbbreviation" :readonly="type==1"></Input>
            </FormItem>
          </Col>
           
        </Row>

        <Row>
         
          <Col :span="12">
            <FormItem prop="tenantType" label="Tenant Type" :label-width="100">
              <Select v-model="addForm.tenantType" style="width: 150px" placeholder="Please select">
            <Option value="1">Standard edition</Option>
            <Option value="2">Professional edition</Option>
          </Select>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem prop="endTime" label="Tenant expired time" :label-width="100">
            <DatePicker @on-change="(val)=>{addForm.endTime=val}" :value="addForm.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss"></DatePicker>
             </FormItem>
          </Col>

        </Row>
        <Row>
          <Col :span="12">

          <FormItem prop="responsiblePerson" label="Responsible person" :label-width="100">
          
         <Input v-model="addForm.responsiblePerson" :readonly="type==1"></Input>
        </FormItem>
            
          </Col>
          <Col :span="12">
            <FormItem prop="tel" label="Telephone No" :label-width="100">
              <Input v-model="addForm.tel" :readonly="type==1"></Input>
            </FormItem>
          </Col>
          
        </Row>

        <Row>
          <Col :span="12">
            <FormItem prop="loginName" label="Login name" :label-width="100">
              <Input v-model="addForm.loginName" :readonly="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">

          <FormItem  prop="password" label="Password" :label-width="100">
          
         <Input v-model="addForm.password" :readonly="type==1"></Input>
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
     
  </div>
</template>
<script>
import moment from 'moment'

export default {
  data () {
    var self =this;
    return {
      events: {
                    init: (o) => {
                        this.search();
                    },
      },
      rules: {
        'tenantName':[
          {required: true, message: "Please input tenant name"}
        ],
        'tenantAbbreviation': [
          {required: true, message: 'Please input tenant abbreviation'}
        ],
        'tenantType': [
          {required: true, message: 'Please select tenant type'}
        ],
        'responsiblePerson': [
          {required: true, message: 'Please input responsible person'}
        ],
        'tel': [
          {required: true, message: 'Please input telephone No'}
        ],
        'loginName': [
          {required: true, message: 'Please input Login name'}
        ],
        'password': [
          {required: true, message: 'Please input password'}
        ],
        'endTime': [
          {required: true, message: 'Please select expired time'}
        ]
      },
      searchForm: {
        tenantName: "",
        tenantAbbreviation: "",
        tenantType: "",
        pageSize: 10,
        pageNo: 1,
      },
      addForm: {
        tenantName: "",
        tenantType: "",
        tenantAbbreviation: "",
        responsiblePerson: "",
        tel: "",
        endTime: "",
        loginName: "",
        password: ""
       
      },
      pos_back: [],
      columns: [
        {
          title: "Tenant name",
          key: "tenantName",
        },
        {
          title: "Tenant type",
          key: "tenantType",
          render: (h,params)=>{
            let text = null;
            if(params.row.tenantType == "1"){
              text = "Standard edition";
            }
            else if(params.row.tenantType == "2"){
              text = "Professional edition";
            }
                            return h('span',{
                                props:{}
                            },text)  
                        }
        },
        {
          title: "Responsible person",
          key: "responsiblePerson",
        },
        {
          title: "Login name",
          key: "loginName",
        },
        {
          title: "Telephone No",
          key: "tel",
        },
        {
          title: "Expired time",
          key: "endTime",
          render: (h,params)=>{
            let text = null;
            if(params.row.endTime){
              text = moment(params.row.endTime).format('YYYY-MM-DD HH:mm:ss');
            }
                            return h('span',{
                                props:{}
                            },text)  
                        }
        },
        
        {
          title: "Operation",
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
              /*
              h('Button', {
                props: {
                  size: "small",
                },
                style: {marginRight:'5px'},
                on: {
                  click: ()=>{self.remove(params.row)},
                }
              }, "删除"),
              */
            ])
          }
        }
      ],
      data: [{}],
      total: 10,
      show: false,
      loading: false,
      pageSize:10,
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
        if (key=='endTime'){
          this.addForm[key] = new Date(row[key]);
        }
        if (key=='tenantType'){
          this.addForm[key] = row[key]+"";
        }
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
 


      this.$refs.addForm.resetFields();

      this.addForm = Object.assign(this.addForm, {}, {
                    tenantName: "",
                    tenantType: "",
                    tenantAbbreviation: "",
                    endTime: moment().format("YYYY-MM-DD HH:mm:ss"),
                    responsiblePerson: "",
                    tel:"",
                    loginName:"",
                    password: ""
                });

    },

    hideModal(){
      this.show = false;
      this._clearFeilds();
    },
    save() {
      this.loading = true;
      this.$refs.addForm.validate(async (valid)=>{
                    if(valid) {
                        // 验证通过, 提交运单表单
                        let form = Object.assign({}, this.addForm);
                        form.endTime = moment(this.addForm.endTime).valueOf();
                      
                        let res = await this.$api.addTenant(form);
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
                if(this.searchForm.tenantType == -1)
                    this.searchForm.tenantType = "";
                let res = await this.$api.getTenantList(this.searchForm);
                if(res.errorCode==0) {
                    this.data = res.data.tenants;
                    this.total = res.data.total;
                    return;
                }
                else
                    this.$Message.error(res.errorMsg);
            },
            
}
}
</script>
