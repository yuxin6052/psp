<style lang="less">
    @import '../../styles/common.less';
</style>

<template>
    <div class="user">
        <div>
            <Table border :columns="users" :data="userData"></Table>
        </div>
        <div class="common-page">
            <Page :total="dataCount" :page-size="pageSize"  show-total  @on-change="changePage"></Page>

        </div>
    </div>
</template>

<script>

export default {
  name: 'users',
  data () {
    return {
      ajaxUserData: [],
      // 初始化信息总条数
      dataCount: 15,
      // 每页显示多少条
      pageSize: 10,
      users: [
        {
          title: '用户ID',
          key: 'id',
          width: 90,
          align: 'center'
        },
        {
          title: '用户昵称',
          key: 'nickName',
          width: 120,
          align: 'center'
        },
        {
          title: '性别',
          key: 'gender',
          width: 90,
          align: 'center',
          render: (h,params)=>{
            let text=''

            if(params.row.gender == '1'){
              text = '男'
            } else if(params.row.gender == '2'){
              text = '女'
            } else {
              text = '未知'
            }
            return h('div',{
              props:{}
              },text)
            }
        },
        {
          title: '头像',
          key: 'avatarUrl',
          align: 'center',
          render: (h, params) => {
            return h('span', {
              attrs: {
                style: 'width: 40px;height: 40px;'
              },
            }, [
                h('img', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  attrs: {
                    src: params.row.avatarUrl, style: 'width: 40px;height: 40px;border-radius: 2px;'
                  },
                  style: {
                  },
                }),
              ]);
          }
        },
        {
          title: '信用分',
          key: 'creditScore',
          width: 120,
          align: 'center'
        },
      ],
      userData: [],
      pageData: {
        cursor: 1,
        limit: 10
      }
    }
  },
  methods: {
    changePage (index) {
      this.pageData.cursor = index
      this.getUserList(this.pageData)
    },
    getUserList(pageData) {
      this.$api.getUserList(pageData).then(res => {
        this.userData = res.data
        this.dataCount = parseInt(res.headers['x-slice-total-count'])
      })
    }
  },
  created () {
    this.getUserList(this.pageData)
  }
}
</script>
