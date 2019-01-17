import { getArticleList } from '@/api/article'

export default {
  namespaced: true,
  actions: {
    handleArticleList: function () {
      return getArticleList()
        .then(res => {
          console.info(res.data)
          return res.data
        })
        .catch(err => {
          console.info(err)
        })
    }
  }
}
