<style lang="less">
    @import '../../styles/common.less';
    @import 'game.less';
</style>

<template>
    <div>
        <div class="common-div">
            <Card>
                <Form ref="gameAction" :model="gameAction" :rules="ruleValidate" :label-width="80" >
                    <Row>
                        <Col span="11">
                            <FormItem label="游戏名称" prop="name">
                                <Input v-model="gameAction.name" icon="android-list"/>
                            </FormItem>
                        </Col>
                        <Col span="11">
                            <FormItem label="最大人数" prop="maxMember">
                                <Input v-model="gameAction.maxMember" icon="android-list"/>
                            </FormItem>
                        </Col>
                        <Col span="11">
                            <FormItem label="logo图" prop="logo">
                                <Img :src="gameAction.logo" style="width: 80px; height: 80px" />
                                <Upload
                                    ref="upload"
                                    :show-upload-list="false"
                                    :on-success="handleLogoSuccess"
                                    :on-error="handleError"
                                    :on-exceeded-size="handleMaxSize"
                                    :format="['jpg','jpeg','png']"
                                    :max-size="4096"
                                    multiple
                                    type="drag"
                                    name="file"                                   
                                    action="//up-z2.qbox.me/"
                                    :data="{token: uploadConfig.token}"
                                    style="display: inline-block;width:58px;"
                                    >
                                    <div class="upload-icon">
                                        <Icon type="ios-cloud-upload" size="30"></Icon>
                                    </div>
                                </Upload>
                            </FormItem>
                        </Col>
                        <Col span="11">
                            <FormItem label="游戏图片" prop="imgUrl">
                                <Img :src="gameAction.imgUrl" style="width: 240px; height: 80px" />
                                <Upload
                                    ref="upload"
                                    :show-upload-list="false"
                                    :on-success="handleImgSuccess"
                                    :on-error="handleError"
                                    :on-exceeded-size="handleMaxSize"
                                    :format="['jpg','jpeg','png']"
                                    :max-size="4096"
                                    multiple
                                    type="drag"
                                    name="file"                                   
                                    action="//up-z2.qbox.me/"
                                    :data="{token: uploadConfig.token}"
                                    style="display: inline-block;width:58px;"
                                    >
                                    <div class="upload-icon">
                                        <Icon type="ios-cloud-upload" size="30"></Icon>
                                    </div>
                                </Upload>
                            </FormItem>
                        </Col>
                        <Col span="11">
                            <FormItem label="是否热门" prop="hot">
                                <Select v-model="gameAction.hot">
                                    <Option value="true">是</Option>
                                    <Option value="false">否</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="11">
                            <FormItem label="是否停用" prop="deleted">
                                <Select v-model="gameAction.deleted">
                                    <Option value="true">是</Option>
                                    <Option value="false">否</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col class="game-form-btn">
                            <span><Button type="primary" class="common-button" @click="handleSaveGame('gameAction')" icon="folder">保存</Button></span>
                        </Col>
                    </Row>
                </Form>
            </Card>
        </div>
    </div>
</template>
<script>
export default {
  name: 'gameAction',
  data () {
    return {
        uploadConfig:{
            token:'',
            baseUrl:'',
        },
        gameAction: {
            name: '',
            hot: 'true',
            deleted: 'false',
            maxMember: 1,
            logo: '',
            imgUrl: ''
        },
        ruleValidate: {
            name: [{ required: true, message: '游戏名称不能为空', trigger: 'blur' }],
            maxMember:[
                        { type: 'number', message: '请输入数字格式', trigger: 'blur', transform(value) {
                            return Number(value);
                        }}
                    ],
            logo:[{ required: true, message: 'logo图链接不能为空', trigger: 'blur' }],
            imgUrl:[{ required: true, message: '图片链接不能为空', trigger: 'blur' }]

        }
    }
  },
  methods: {
    handleSaveGame (gameAction) {
        this.$refs[gameAction].validate((valid) => {
        if (valid) {
            this.$api.createGameType(this.gameAction).then(res => {
                this.$store.commit('removeTag', this.$route.name);
                this.$router.push({
                  name: 'game'
                })
            })
          // 保存广告
        } else {
          this.$Message.error('Fail!')
        }
      })
    },
    handleLogoSuccess(res){
        this.gameAction.logo = this.uploadConfig.baseUrl+res.key
        this.$Message.success('上传logo图成功!')
    },
    handleImgSuccess(res){
        console.log(res)
        this.gameAction.imgUrl = this.uploadConfig.baseUrl+res.key
        this.$Message.success('上传图成功!')
    },
    handleError(){this.$Message.success('上传图失败!')},
    
    handleMaxSize(){
        this.$Message.warning( '文件太大，不能超过 4M');
    },
  },
  mounted () {
    this.uploadConfig = this.$api.getUploadToken()
  }
}
</script>
