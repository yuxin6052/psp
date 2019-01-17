import { axios } from './index'

const game = {
  getGameTypeList () {
    var url = '/games/game-types'
    return axios.get(url).then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  },
  createGameType (data) {
    return axios.post('/games/game-types', data)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  deleteGame (id) {
    var url = '/games/game-types/' + id
    return axios.delete(url)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  updateGameType (id, data) {
    var url = '/games/game-types/' + id
    return axios.put(url, data)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  getGamesConfig(id){
    var url = '/games/game-types/'+id+'/config'
    return axios.get(url).then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  },
  createConfigOption(id,data){
    var url = '/games/game-configs/'+id+'/options?value='+data
    return axios.post(url).then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  },
  deleteConfigOption(configId,optionId) {
    var url = '/games/game-configs/'+configId+'/options/'+optionId
    return axios.delete(url)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  updateGameConfig (id, data) {
    var url = '/games/game-configs/' + id+'?inputType='+data.inputType+'&label='+data.label+'&required='+data.required
    return axios.put(url)
      .then(res => {
        return res
      })
      .catch(e => {
        return Promise.reject(e)
      })
  },
  createGameConfig(gameTypeId,data){
    var url = '/games/game-configs/?gameTypeId='+gameTypeId+'&inputType='+data.inputType+'&label='+data.label+'&required='+data.required
    return axios.post(url).then(res => {
      return res
    })
    .catch(e => {
      return Promise.reject(e)
    })
  }
}

export default game