<style lang="less">
</style>

<template>
  <div>
    <Form inline>
      <FormItem label="所属机构" :label-width="70">
        <Input v-model="searchForm.organization"></Input>
      </FormItem>
      <FormItem label="司机姓名" :label-width="70">
        <Input v-model="searchForm.driverName"></Input>
      </FormItem>
      <FormItem ><Button type="primary" @click="search">查询</Button></FormItem>
    </Form>
    <Table :columns="columns" :data="data">
      <template slot="header">
        <Button type="primary" shape="circle" @click="show = !show;type=0;" style="margin-left: 1em">新增司机</Button>
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
    <Modal ref="modal" title="司机信息"
      v-model="show"
      @on-cancel="hideModal"
      @on-ok="save">
      <Form ref="addForm" :model="addForm" :rules="type!=1?rules:{}">
        <Row>
          <Col :span="12">
            <FormItem prop="name" label="姓名" :label-width="70">
              <Input v-model="addForm.name" :readonly="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem prop="sex" label="性别" :label-width="70">
              <Select v-model="addForm.sex" :disabled="type==1">
                <Option :value="0">男</Option>
                <Option :value="1">女</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col :span="12">
            <FormItem prop="organization" label="所属机构" :label-width="70">
              <Input v-model="addForm.organization" :readonly="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem label="联系电话" :label-width="70">
              <Input v-model="addForm.phoneNumber" :readonly="type==1"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col :span="12">
            <FormItem label="驾驶证号" :label-width="70">
              <Input v-model="addForm.driving" :readonly="type==1"></Input>
            </FormItem>
          </Col>
          <Col :span="12">
            <FormItem label="资格证号" :label-width="70">
              <Input v-model="addForm.certification" :readonly="type==1"></Input>
            </FormItem>
          </Col>
        </Row>
        <FormItem prop="validity" label="是否有效" :label-width="70">
          <Select v-model="addForm.validity" :disabled="type==1">
            <Option :value="0">有效</Option>
            <Option :value="1">无效</Option>
          </Select>
        </FormItem>
        <FormItem label="备注" :label-width="70">
          <Input type="textarea" v-model="addForm.remark" :readonly="type==1"></Input>
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
  </div>
</template>

<script>
export default {
  name: 'comment',
  data () {
    let self = this;
    return {
      searchForm: {

      },
      addForm: {
        name: "",
        sex: 0,
        organization: "",
        phoneNumber: "",
        driving: "",
        certification: "",
        validity: 0,
        remark: "",
      },
      columns: [
        {
          title: "姓名",
          key: "name",
        },{
          title: "性别",
          key: "sex",
        },{
          title: "所属机构",
          key: "organization",
        },{
          title: "联系电话",
          key: "phoneNumber",
        },{
          title: "是否有效",
          key: "validity",
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
        name: [
          {required: true, message:"请填写姓名"}
        ],
        sex: [
          {required: true, message: "请选择性别"}
        ],
        organization: [
          {required: true, message: "请填写所属机构"}
        ],
        validity: [
          {required: true, message: "请选择有效性"}
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
    addDriver(){
      
    },
    updateDriverInfo() {

    },
    save() {
      let self = this;
      self.loading = true;
      this.$refs.addForm.validate((valid)=>{
        if(valid) {
          if(self.type==0)
            self.addDriver();
          else if(self.type==2)
            self.updateDriverInfo();
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
