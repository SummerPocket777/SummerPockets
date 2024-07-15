import { getOrderList } from '@/api/kitchen'

// 共享状态
const state = {
  orderList: []

}
// 改变状态的唯一途径
const mutations = {

  set_orderList(state, list) {
    state.orderList = list
  }

}
// ajax，异步请求，代码都要在这
const actions = {



  getOrderList({ commit }, shopid) {
    getOrderList({ shopId: shopid }).then(res => {
      console.log(res)
      if (res.data) {
        commit('set_orderList', res.data)
      }
    })


    return new Promise((resolve, reject) => {
      getOrderList({ shopId: shopid }).then(res => {
        console.log(res)
        if (res.data) {
          commit('set_orderList', res.data)
        }
        resolve()
      }).catch(error => {
        reject(error)
      })
    })


  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions

}

