import { axios } from './index'

const banner = {
  getBannerList () {
    var url = '/advert'
    return axios.get(url).then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  },

  createBanner (data) {
    return axios.post('/advert', data)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  updateBanner (id, data) {
    var url = '/advert/' + id
    return axios.put(url, data)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  deleteBanner (id) {
    var url = '/advert/' + id
    return axios.delete(url)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  }
  
}

export default banner