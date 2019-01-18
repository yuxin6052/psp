// 跟用户有关的API

import { axios } from './index'
import qs from 'qs'


const user = {
  async searchUser(form) {
    let res = await axios.post('/admin/user/v1/searchUser',form);
    return res.data;
},

async updateOtherUserPassword(form) {
  let res = await axios.post("/admin/user/v1/updateOtherUserPassword", form);
  return res.data;
},
   /**
   * 使用密码登录,client_id (返回的token写在store中,然后在用拦截器来给所有请求增加header)
   * @param {String} username 用户名
   * @param {String} password 密码
   * @param {String} client_id 目前是写死,暂无作用但必须提供
   */
  login (username, password) {
    var data = {username, password}
   // data.client_id = 'eivexVfHUVu3IWJQuIE9TmqNk3hfQ3XA'
   // data.grant_type = 'password'
    //data = qs.stringify(data)
    return axios({
      method: 'post',
      url: '/tenant/v1/user/login',
      data: data,
      headers: {
          'Access-Control-Allow-Origin': 'localhost:9092',
          'Content-Type': 'application/json',
      },
      withCredentials: true,
      credentials: 'same-origin',
    }).then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  }, 
}

export default user