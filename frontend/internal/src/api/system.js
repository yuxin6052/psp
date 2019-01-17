import { axios } from './index'
import qs from 'qs'

const system = {
  getFeedBackList () {
    var url = '/system/feedbacks'
    return axios.get(url).then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  },
  updateFeedBack(id){
    var url = '/system/feedbacks/' + id
    return axios.put(url)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  getAbout () {
    return axios.get('/system/about').then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  },
  updateAbout (data) {
    var url = '/system/about'
    return axios.put(url, data).then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  }
  
}

export default system