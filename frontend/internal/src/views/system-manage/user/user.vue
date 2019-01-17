<style lang="less">
</style>

<template>
  <div>
    <Form inline>
      <FormItem label="用户名" :label-width="70">
        <Input v-model="searchForm.username"></Input>
      </FormItem>
      <FormItem label="姓名" :label-width="70">
        <Input v-model="searchForm.name"></Input>
      </FormItem>
      <FormItem label="机构" :label-width="70">
        <Input v-model="searchForm.organization"></Input>
      </FormItem>
      <FormItem ><Button type="primary" @click="search">查询</Button></FormItem>
    </Form>
    <Table :columns="columns" :data="data">
      <template slot="header">
        <Button type="primary" shape="circle" @click="show = !show;type=0;" style="margin-left: 1em">新增用户</Button>
      </template>
      <template slot="footer">
        <Page :total="total" 
          :page-size="10" 
          show-elevator 
          align="center" 
          show-total 
          @on-change="changePage"></Page>
      </template>
    </Table>
    <Modal ref="modal" title="用户信息"
      :width="650"
      v-model="show"
      @on-cancel="hideModal"
      @on-ok="save">
      <Form ref="addForm" :model="addForm" :rules="type!=1?rules:{}">
        <Row>
          <Col :span="12">
            <FormItem prop="username" label="用户名" :label-width="100">
              <Input v-model="addForm.username" :disabled="type==1" :readonly="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem prop="name" label="姓名" :label-width="100">
              <Input v-model="addForm.name" :disabled="type==1"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col :span="12">
            <FormItem label="性别" prop="sex" :label-width="100">
              <Select v-model="addForm.sex" :readonly="type==1" :disabled="type==1">
                <Option :value="0">男</Option>
                <Option :value="1">女</Option>
              </Select>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem label="所属机构" prop="organization" :label-width="100">
              <Select v-model="addForm.organization" :disabled="type==1">
                <Option :value="0">医疗机构用户</Option>
                <Option :value="1">供应商</Option>
                <Option :value="2">平台</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col :span="12">
            <FormItem label="职务" prop="duty" :label-width="100">
              <Input v-model="addForm.duty" :disabled="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem label="电话" prop="phone" :label-width="100">
              <Input v-model="addForm.phone" :disabled="type==1"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col :span="12">
            <FormItem label="邮箱" prop="email" :label-width="100">
              <Input v-model="addForm.email" :disabled="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem label="是否有效" :label-width="100" prop="validity">
              <Select v-model="addForm.validity" :disabled="type==1" >
                <Option :value="0">无效</Option>
                <Option :value="1">有效</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <FormItem prop="remark" label="备注" :label-width="100" >
          <Input type="textarea" v-model="addForm.remark" :readonly="type==1"></Input>
        </FormItem>
        <Row>
          <Col :span="12">
            <FormItem label="密码" prop="password" :label-width="100">
              <Input v-model="addForm.password" :disabled="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem label="确认密码" :label-width="100" prop="repassword">
              <Input v-model="addForm.repassword" :disabled="type==1" >
              </Input>
            </FormItem>
          </Col>
        </Row>
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
  </div>
</template>

<script>
export default {
  name: 'comment',
  data () {
    let self = this;
    return {
      searchForm: {
        username: '',
        name: '',
        organization: '',
      },
      addForm: {
        username: '',
        name: '',
        sex: '',
        organization: '',
        duty: '',
        phone: '',
        email: '',
        remark: '',
        password: '',
        repassword: '',
        validity: 0,
      },
      columns: [
        {
          title: "用户名",
          key: "username",
        },{
          title: "姓名",
          key: "name",
        },{
          title: "性别",
          key: "sex",
        },{
          title: '机构',
          key: 'organization',
        },{
          title: '电话',
          key: 'phone',
        },{
          title: '邮箱',
          key: 'email'
        },{
          title: '是否有效',
          key: 'validity',
        },{
          title: "操作",
          width: 200,
          align: 'center',
          render: (h, params)=>{
            return h('div', [
              h('Button', {
                props: {size: 'small'},
                style: {marginRight: '5px'},
                on: {
                  click: ()=>{self.showDetail(params.row)}
                }
              }, "查看"),
              h('Button', {
                props: {size: 'small'},
                style: {marginRight: '5px'},
                on: {
                  click: ()=>{self.modify(params.row)}
                }
              }, "修改"),
              h('Button', {
                props: {size: 'small'},
                style: {marginRight: '5px'},
                on: {
                  click: ()=>{self.remove(params.row)}
                }
              }, "删除"),

            ])
          }
        }
      ],
      data: [{
        name: "hahah",
      }],
      rules: {
        username: [
          {required: true, message:"请填写用户名"}
        ],
        name: [
          {required: true, message: "请填写姓名"}
        ],
        sex: [
          {required: true, message: '请选择性别'}
        ],
        organization: [
          {required: true, message: "请填写所属机构"}
        ],
        validity: [
          {required: true, message: "请选择有效性"}
        ],
        password: [
          {required: true, message: '请填写密码'}
        ],
        repassword: [
          {required: true, message: '请确认密码'}
        ]
      },
      show: false,
      loading: false,
      total: 10,
      type: 0, // 0 新增, 1 查看, 2 修改
    }
  },
  methods: {
    search() {
      
    },
    showDetail(row){
      this.type = 1;
      for(let key in this.addForm){
        this.addForm[key] = row[key];
      }
      this.show = true;
    },
    modify(row){
      this.type = 2;
      for(let key in this.addForm){
        this.addForm[key] = row[key];
      }
      this.show = true;
    },
    remove(row){

    },
    changePage(page){

    },
    hideModal(){
      this.show = false;
      this.$refs.addForm.resetFields();
      this.clearAddForm();
      this.loading = false;
    },
    clearAddForm() {
      this.$refs.addForm.resetFields();
      this.addForm = Object.assign(this.addForm, {}, {
        name: "",
        sex: 0,
        organization: "",
        phoneNumber: "",
        driving: "",
        certification: "",
        validity: 0,
        remark: "",
      });
    },
    addUser(){
      
    },
    updateUser() {

    },
    save() {
      let self = this;
      self.loading = true;
      this.$refs.addForm.validate((valid)=>{
        if(valid) {
          if(self.type==0)
            self.addUser();
          else if(self.type==2)
            self.updateUser();
          self.hideModal();
        }
      });
      self.loading = false;
    }
  },
  created () {
    
  }
}
</script>

