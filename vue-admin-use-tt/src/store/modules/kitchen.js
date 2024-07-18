import { getOrderList, updateOrderStatus } from '@/api/kitchen'

// 共享状态
const state = {
  orderList: []
}
// 改变状态的唯一途径
const mutations = {
  set_orderList(state, list) {
    state.orderList = list
  },

  update_orderStatus(state, data) {
    state.orderList.forEach(item => {
      if (item.orderDetail.id === data.orderDetail.id) {
        item.orderDetail.status = data.orderDetail.status
      }
    })
  }

}
// ajax，异步请求，代码都要在这
const actions = {



  getOrderList({ commit }, shopid) {

    return new Promise((resolve, reject) => {
      getOrderList(shopid).then(res => {

        if (res.data) {
          commit('set_orderList', res.data)
        }
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },


  updateOrderStatus({ commit }, data) {

    return new Promise((resolve, reject) => {
      commit('update_orderStatus', data)
      updateOrderStatus(data.orderDetail.status, data.orderDetail.id).then(res => {
        resolve(res)
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

