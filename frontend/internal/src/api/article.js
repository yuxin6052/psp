import { axios } from './index'
import qs from 'qs'

const article = {
  getArticleList (params) {
    var url = '/articles'
    return axios.get(url, {params:params}).then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  },

  createArticle (data) {
    return axios.post('/articles', data)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  updateArticle (id, data) {
    var url = '/articles/' + id
    return axios.put(url, data)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  deleteArticle (id) {
    var url = '/articles/' + id
    return axios.delete(url)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  getArticle (id) {
    var url = '/articles/' + id
    return axios.get(url)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  }
  
}

export default article