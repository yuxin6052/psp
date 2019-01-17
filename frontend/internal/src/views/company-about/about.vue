<style lang="less">
    @import '../../styles/common.less';
</style>

<template>
    <div class="about">
        <div class="common-div">
            <Card>
                <Form ref="formAbout" :model="formAbout" :rules="ruleValidate" :label-width="120" >
                    <Row>
                        <Col>
                            <FormItem label="公司名称" prop="company">
                                <Input v-model="formAbout.company" icon="android-list"/>
                            </FormItem>
                        </Col>
                        <Col>
                            <FormItem label="小程序简介" prop="introduction">
                                <Input v-model="formAbout.introduction" type="textarea" :autosize="{minRows: 3,maxRows: 10}" placeholder="Enter something..."></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <FormItem label="LOGO" prop="logo">
                                <img :src="formAbout.logo" style="width: 240px; height: 240px">
                                <Upload
                                    ref="upload"
                                    :show-upload-list="false"
                                    :on-success="handleSuccess"
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
                    </Row>
                    <Row>
                        <Col class="about-form-btn">
                            <span><Button type="primary" class="common-button" @click="handleSaveAbout('formAbout')" icon="folder">保存</Button></span>
                        </Col>
                    </Row>
                </Form>
            </Card>
        </div>
    </div>
</template>
<script>
import Cropper from 'cropperjs'
export default {
  name: 'about',
  data () {
    return {
        uploadConfig:{
            token:'',
            baseUrl:'',
        },
        showPercent:'',
        formAbout: {
            company: '组起',
            introduction: '好友游戏匹配神器',
            logo: ''
        },
        ruleValidate: {
            company: [{required: true, message: '公司名称不能为空', trigger: 'blur'}],
            introduction: [{required: true, message: '小程序简介不能为空', trigger: 'blur'}]
        },
        cropper: {},
        option: {
            showCropedImage: false,
            cropedImg: ''
        }
    }
  },
  methods: {
    handleSuccess(res){
        this.formAbout.logo = this.uploadConfig.baseUrl+res.key
        this.$Message.success('上传logo图成功!')
    },
    handleError(){
        this.$Message.success('上传logo图失败!')
    },
    handleMaxSize(){
        this.$Message.warning( '文件太大，不能超过 4M');
    },
    handleSaveAbout (formAbout) {
        this.$refs[formAbout].validate((valid) => {
            if (valid) {
                this.$api.updateAbout(this.formAbout).then(res => {
                    this.$Message.success('Success!')
                })
            } else {
                this.$Message.error('Fail!')
            }
        })
    }
  },
  mounted () {
    this.$api.getAbout().then(res => {
      this.formAbout = res.data
    })
    this.uploadConfig = this.$api.getUploadToken()
  }
}
</script>
