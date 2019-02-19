<style lang="less">

</style>
<template>
    <div>
        <Row>
            <Form inline>
                <FormItem label="统一社会信用代码" :label-width="130">
                    <Input v-model="searchForm.code"></Input>
                </FormItem>
                <FormItem label="单位名称" :label-width="130">
                    <Input v-model="searchForm.company"></Input>
                </FormItem>

                <FormItem >
                    <Button type="primary" @click="search">查询</Button>
                </FormItem>
            </Form>
        </Row>
        <Table :columns="columns" :data="data">
            <template slot="header">
                <Button type="primary" shape="circle" @click="show=!show" style="margin-left: 1em;">新增</Button>
                <Button type="primary" shape="circle" @click="show=!show" style="margin-left: 1em;">删除</Button>
                <Button type="primary" shape="circle" @click="show=!show" style="margin-left: 1em;">查看</Button>
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
                        <FormItem prop="companyName" label="单位名称" :label-width="100">
                            <Input v-model="addForm.companyName" :readonly="type==1"></Input>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem prop="unifiedSocialCreditCode" label="统一社会信用代码" :label-width="100">
                            <Input v-model="addForm.unifiedSocialCreditCode" :readonly="type==1"></Input>
                        </FormItem>
                    </Col>

                </Row>

                <Row>

                    <Col :span="12">
                        <FormItem prop="legalPersonAuthorizer" label="法定代表人授权人" :label-width="100">
                            <Input v-model="addForm.legalPersonAuthorizer" :readonly="type==1"></Input>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem prop="authorizerPhoneNo" label="授权人手机号" :label-width="100">
                            <Input v-model="addForm.authorizerPhoneNo" :readonly="type==1"></Input>
                        </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col :span="24">

                        <FormItem prop="labelIds" label="入库类型" :label-width="100">

                            <CheckboxGroup v-model="addForm.labelIds">
                                <Checkbox label="香蕉"></Checkbox>
                                <Checkbox label="苹果"></Checkbox>
                            </CheckboxGroup>
                        </FormItem>

                    </Col>

                </Row>
                <Row>
                    <Col :span="24">

                        <FormItem prop="remark" label="多行文本域" :label-width="100">

                            <Input v-model="addForm.remark"  type="textarea" :readonly="type==1"></Input>
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
                    'companyName':[
                        {required: true, message: "公司名称不可为空"}
                    ],
                    'legalPersonAuthorizer': [
                        {required: true, message: '法定代表授权人不能为空'}
                    ],
                    'authorizerPhoneNo': [
                        {required: true, message: '联系人电话不能为空'}
                    ],
                    'remark': [
                        {required: true, message: 'Please input responsible person'}
                    ],
                    'unifiedSocialCreditCode': [
                        {required: true, message: '社会信用统一代码不能为空'}
                    ],
                    'labelIds': [
                        {required: true, message: '入库类型不能空缺'}
                    ]
                },
                searchForm: {
                    tenantName: "",
                    unifiedSocialCreditCode: "",
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
                        title: "单位名称",
                        key: "companyName",
                    },
                    {
                        title: "统一社会信用代码",
                        key: "unifiedSocialCreditCode",
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
                        title: "法定代表人授权人",
                        key: "legalPersonAuthorizer",
                    },
                    {
                        title: "授权人手机号",
                        key: "authorizerPhoneNo",
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
                                }, "查看")
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
