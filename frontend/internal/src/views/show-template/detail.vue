<style lang="less">
    .container{
        position: relative;
        width: 100%;
        padding: 0 20px;
        background: #ffffff;
        min-height: 800px;
        overflow: auto;
        .header{
            width: 100%;
            background: #ffffff;
            height: 100px;
            padding-top: 20px;
            text-align: center;
            .title{
                font-size: 24px;
                font-weight: bold;
                line-height: 48px;
                padding-bottom: 20px;
                border-bottom: 2px solid #b3d4fc;
                text-align: left;
            }
            .img{
                padding-top: 20px;
            }
        }
        .content{
            padding: 0 10px;
            width: 100%;
            background: #ffffff;
            height: 300px;
            h4{text-align: center}
        }
    }

</style>
<template>
    <div class="container">
        <div class="header">
            <Row>
                <div class="title">XXX网站</div>

            </Row>
        </div>
        <div class="content">
            <Row :gutter="20" style="margin-top: 10px;">
                <i-col :md="24" :lg="24" style="margin-bottom: 20px;">
                    <Card shadow>
                        <h4>文本标题</h4>
                       <div>
                           浦口区环老山旅游风景道路建设工程现通过南京市中介机构交易平台公开选取招标代理类中介机构。相关选取事项如下:

                           一、项目概况

                           项目名称:浦口区环老山旅游风景道路建设工程

                           建设单位:南京市浦口区建设发展有限公司

                           立项文号:浦发改投资字[2019]46号

                           项目所在辖区:浦口区

                           投资总额(万元):10000.0

                           建安造价估算(万元):8000.0

                           二、选取条件

                           申请选取类别:招标代理

                           工程专业类别:房建市政

                           服务内容:

                           代拟发包方案;

                           发布招标公告（发出投标邀请书）;

                           编制资格预审文件;

                           组织接收投标申请人报名;

                           审查潜在投标人资格，确定潜在投标人;

                           编制招标文件;

                           组织现场踏勘和答疑;

                           组织开标、评标;

                           草拟工程合同;

                           编制招投标情况书面报告;

                           编制工程招标控制价;

                           编制工程量清单;

                           委托代理招标内容:


                       </div>

                    </Card>
                </i-col>

            </Row>
        </div>


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
