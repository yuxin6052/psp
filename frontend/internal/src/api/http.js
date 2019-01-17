// axios 登录器拦截
import axios from 'axios'
import Cookies from 'js-cookie';
import { EROFS } from 'constants';
// import store from '../store'

// 请求拦截器 如果在store中获取到了token,那么就写入请求头
axios.interceptors.request.use(
  config => {
    if (localStorage.token) {
      config.headers.Authorization = localStorage.token
    }
    return config
  },
  err => {
    return Promise.reject(err)
  }
)

// 响应拦截器,拦截401,未授权的情况
axios.interceptors.response.use(
  response => {
    if (response.status === 200) {
      return response
    } else {
      console.log('状态码不是200')
    }
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // TODO:申明全局状态,而不是直接触发事件,全局状态可以给更多模块(不单单api)知道而执行相对于的处理
          // store.dispatch('logout')
          // Cookies.remove('user');
          localStorage.clear();
          store.commit('LOG_OUT');
          router.replace({
            path: 'login',
            query: {redirect: router.currentRoute.fullPath}
          });
          // window.location.href = '/#/login';
          break
      }
    }
    return Promise.reject(error.response.data)
  }
)


export default axios
