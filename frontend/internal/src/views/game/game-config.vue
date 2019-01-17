<style lang="less">
    @import '../../styles/common.less';
    @import 'game.less';
</style>

<template>
    <div class="feedback">
        <div class="common-div">
            <Card v-show="!addOption">
                <Form ref="gameConfig" :model="gameConfig" :rules="ruleValidate" :label-width="80" >
                    <Row>
                        <Col>
                            <FormItem label="配置名称" prop="name">
                                <Input v-model="gameConfig.label" icon="android-list"/>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem label="是否必选" prop="required">
                                <Select v-model="gameConfig.required">
                                    <Option value="true">是</Option>
                                    <Option value="false">否</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <FormItem label="是否多选">
                                <RadioGroup v-model="gameConfig.inputType">
                                    <Radio label="radio">单选</Radio>
                                    <Radio label="checkbox">多选</Radio>
                                </RadioGroup>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row v-show="updateOption">
                        <Col>
                            <FormItem label="配置选项" prop="options">
                                <Tag v-for="item in gameConfig.options" :key="item.id" :name="item.id" closable @on-close="handleOptionClose">{{ item.value }}</Tag>
                                <Button icon="ios-plus-empty" type="dashed" size="small" @click="handleOptionAdd">添加配置选项</Button>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col class="game-form-btn">
                            <span><Button type="primary" class="common-button" @click="handleSaveGame('gameConfig')" icon="folder">保存</Button></span>
                        </Col>
                    </Row>
                </Form>
            </Card>
            <Card v-show="addOption">
                <Row>
                    <Col>
                        <span class="opt-label">配置选项</span>
                        <Tag v-for="item in gameConfig.options" :key="item.id" :name="item.id" closable @on-close="handleOptionClose">{{ item.value }}</Tag>
                        <Button icon="ios-plus-empty" type="dashed" size="small" @click="handleOptionAdd">添加配置选项</Button>
                    </Col>
                </Row>
                <Row>
                    <Col class="game-form-btn">
                        <span><Button type="primary" class="common-button" @click="handleReturn()" icon="folder">确定</Button></span>
                    </Col>
                </Row>
            </Card>
        </div>
    </div>
</template>
<script>
export default {
  name: 'gameConfig',
  data () {
    return {
        updateOption:true,
        addOption:false,
        optionName:'',
        gameConfig:{
            id:'',
            inputType:'',
            label:'',
            options:[]
        },
        ruleValidate:{
            label:[{required: true, message: '配置名称不能为空', trigger: 'blur'}]
        },
        gameAction: {
            id: '',
            name: '',
            hot: 'true',
            deleted: 'false',
            maxMember: 0,
            logo: '',
            imgUrl: '',
            createTime: '',
            gameConfig:{
                id:'',
                inputType:'',
                label:'',
                options:[]
            }
        },
        ruleValidate: {
            label: [{ required: true, message: '配置名称不能为空', trigger: 'blur' }],
            maxMember:[{ required: true, message: '最大匹配人数不能为空', trigger: 'blur' },
                        { type: 'number', message: '请输入数字格式', trigger: 'blur', transform(value) {
                            return Number(value);
                        }}
                    ],
            logo:[{ required: true, message: 'logo图链接不能为空', trigger: 'blur' }],
            imgUrl:[{ required: true, message: '图片链接不能为空', trigger: 'blur' }]

        },
    }
  },
  methods: {
    handleOptionAdd () {
         this.$Modal.confirm({
            title:'增加配置选项',
            render: (h) => {
                return h('Input', {
                    props: {
                        value: this.optionName,
                        autofocus: true,
                        placeholder: '请输入配置选项名称'
                    },
                    on: {
                        input: (val) => {
                            this.optionName = val;
                        }
                    }
                })
            },
            onOk:()=>{
                if(this.optionName == ''){
                    this.$Message.error('请输入配置选项名称')
                    return
                }
                this.$api.createConfigOption(this.gameConfig.id,this.optionName).then(
                    res =>{
                        this.gameConfig.options.push(res.data)
                        this.$Message.success('添加配置选项成功!')
                    }
                );
            }
        })
    },
    handleOptionClose (event, name) {
        const index = this.gameConfig.options.indexOf(name);
        this.$api.deleteConfigOption(this.gameConfig.id,name).then(res =>{
            this.gameConfig.options.splice(index, 1);
        });
    },
    handleReturn(){
        this.$router.push({
            name: 'games'
        })
    },
    handleSaveGame (gameConfig) {
        this.$refs[gameConfig].validate((valid) => {
        if (valid) {
            if (this.updateOption) {
                  this.$api.updateGameConfig(this.gameConfig.id, this.gameConfig).then(res => {
                      this.$store.commit('removeTag', this.$route.name);
                      this.$router.push({
                        name: 'game'
                      })
                  })
              }else{
                this.$api.createGameConfig(this.$route.params.gameId,this.gameConfig).then(res => {
                    this.gameConfig = res.data
                    this.addOption = true
                    this.$Message.success('保存游戏配置成功!')
                })
              }
        } else {
          this.$Message.error('Fail!')
        }
      })
    },
    getGamesConfig(gameId){
        this.$api.getGamesConfig(gameId).then(res=>{
            this.gameAction = res.data
            if(this.gameAction.gameConfig == null){
                this.updateOption = false
            }else{
                this.gameConfig = this.gameAction.gameConfig
                this.updateOption = true
                this.gameConfig.required = this.gameConfig.required+''
            }
        })
    }
  },
  mounted () {
    this.getGamesConfig(this.$route.params.id)
    this.uploadConfig = this.$api.getUploadToken()
  }
}
</script>
