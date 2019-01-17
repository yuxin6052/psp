<style lang="less">
</style>

<template>
  <div>
    <Form inline>
      <FormItem label="接口名称" :label-width="70">
        <Input v-model="searchForm.interfaceName"></Input>
      </FormItem>
      <FormItem ><Button type="primary" @click="search">查询</Button></FormItem>
    </Form>
    <Table :columns="columns" :data="data">
      <template slot="header">
        <Button type="primary" shape="circle" @click="show = !show;type=0;" style="margin-left: 1em">新增接口密钥</Button>
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
    <Modal ref="modal" title="接口密钥信息"
      v-model="show"
      @on-cancel="hideModal"
      @on-ok="save">
      <Form ref="addForm" :model="addForm" :rules="type!=1?rules:{}">
        <FormItem prop="interfaceName" label="接口名称" :label-width="100">
          <Input v-model="addForm.interfaceName" :disabled="type==1" :readonly="type==1"></Input>
        </FormItem>
        <FormItem prop="api_key" label="API_Key" :label-width="100">
          <Input v-model="addForm.api_key" :disabled="type==1"></Input>
        </FormItem>
        <FormItem label="Secret_key" prop="secret_key" :label-width="100">
          <Input v-model="addForm.secret_key" :readonly="type==1" :disabled="type==1"></Input>
        </FormItem>
        <FormItem label="是否有效" :label-width="100" prop="validity">
          <Select v-model="addForm.validity" :disabled="type==1" >
            <Option :value="0">无效</Option>
            <Option :value="1">有效</Option>
          </Select>
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
        interfaceName: '',
      },
      addForm: {
        interfaceName: '',
        secret_key: '',
        api_key: '',
        validity: 0,
      },
      columns: [
        {
          title: "接口名称",
          key: "interfaceName",
        },{
          title: "API_key",
          key: "api_key",
        },{
          title: "Secret_key",
          key: "secret_key",
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
        interfaceName: [
          {required: true, message:"请填写接口名称"}
        ],
        api_key: [
          {required: true, message: "请填写api_key"}
        ],
        secret_key: [
          {required: true, message: "请填写secret_key"}
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
    addInterface(){
      
    },
    updateInterface() {

    },
    save() {
      let self = this;
      self.loading = true;
      this.$refs.addForm.validate((valid)=>{
        if(valid) {
          if(self.type==0)
            self.addInterface();
          else if(self.type==2)
            self.updateInterface();
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
