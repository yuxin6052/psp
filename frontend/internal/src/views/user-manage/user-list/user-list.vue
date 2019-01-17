<style lang="less">
    
</style>
<template>
  <div>
    <Row>
      <Form inline>
        <FormItem label="" :label-width="0">
          <Input v-model="searchForm.param"></Input>
        </FormItem>
       
        <FormItem >
          <Button type="primary" @click="search">Search</Button>
        </FormItem>
      </Form>
    </Row>
    <Table :columns="columns" :data="data">
     
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
      title="Reset password">
     <Form ref="addForm"  :model="addForm" :rules="rules">

          <Row>
            <Col :span="16">
            <FormItem prop="password" label="Please input new password" :label-width="200">
          <Input v-model="addForm.password" :readonly="type==1"></Input>
        </FormItem>
          </Col>
          </Row>
           
        <Row>
          <Col :span="16">
            <FormItem prop="confirmNewPwd" label="Confirm new password" :label-width="200">
              <Input v-model="addForm.confirmNewPwd" :readonly="type==1"></Input>
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
        'password':[
          {required: true, message: "Please input new password"}
        ],
        'confirmNewPwd': [
          {required: true, message: 'Please confirm new password'}
        ]
      },
      searchForm: {
        param: "",
        pageSize: 10,
        pageNo: 1,
      },
      addForm: {
     
        password: "",
        confirmNewPwd: "",
       
      },
      pos_back: [],
      columns: [
        {
          title: "User Id",
          key: "userId",
          
        },
        {
          title: "Username",
          key: "username",
        },
        {
          title: "Tenant name",
          key: "tenantName",
        },
        {
          title: "Name",
          key: "name",
        },
        {
          title: "Role",
          key: "role",
            render: (h,params)=>{
            let text = null;
            if(params.row.role == 'mgr'){
              text =  "Tenant Administrator";
            }else if(params.row.role == 'driver'){
              text = "Driver"
            }else{
              text = params.row.role
            }
                            return h('span',{
                                props:{}
                            },text)  
                        }
        },
        {
          title: "Telephone No",
          key: "telNo",
        },
        {
          title: "Email",
          key: "email",
         
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
                  click: ()=>{self.modifier(params.row)},
                }
              }, "Reset Password")
               
            ])
          }
        }
      ],
      data: [{}],
      total: 10,
        pageSize:10,
      show: false,
      loading: false,
      type: 0,
      userId:-1,
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
  
    modifier(row){
      this.type = 2;
      this.userId = row.userId
      this.show = true;
    },
    
    getList() {

    },
    _clearFeilds(){
 


      this.$refs.addForm.resetFields();

      this.addForm = Object.assign(this.addForm, {}, {
 
                    password: "",
                    confirmNewPwd: "",
                     
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
                        if(form.password != form.confirmNewPwd){
                          alert("The passwords entered do not match");
                          return;
                        }
                        form.userId = this.userId;
                        let res = await this.$api.updateOtherUserPassword(form);
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
           
                let res = await this.$api.searchUser(this.searchForm);
                if(res.errorCode==0) {
                    this.data = res.data.users;
                    this.total = res.data.total;
                    return;
                }
                else
                    this.$Message.error(res.errorMsg);
            },
}
}
</script>
