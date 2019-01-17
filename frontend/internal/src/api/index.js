// axios 初始化设置已经拦截器设置
import axios from './http'
import user from './user'
import article from './article'
import banner from './banner'
import discuss from './discuss'
import system from './system'
import game from './game'
import upload from './upload'
import shipment from './shipment'
import tenant from './tenant';
import device from './device';

// axios设置默认值
axios.defaults.timeout = 15000
//axios.defaults.withCredentials = true
//axios.defaults.baseURL = 'http://115.159.114.116:9093'
//axios.defaults.baseURL = 'http://localhost:9093'
axios.defaults.baseURL = 'http://localhost:8082'
//axios.defaults.headers = {
//  'Content-Type': 'application/json;charset=UTF-8'
//}

const api = {
  ...user, // 登录相关api
  ...article,
  ...banner,
  ...discuss,
  ...system,
  ...game,
  ...upload,
  ...shipment,
  ...tenant,
  ...device,
}

// 注册vue插件
api.install = function (Vue, options) {
  if (api.install.installed) {
    return
  }
  api.install.installed = true
  Object.defineProperty(Vue.prototype, '$api', {
    get () {
      return api
    }
  })
}

export { api, axios }
