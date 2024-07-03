import { getAllCates } from '@/api/goods'

// 共享状态
const state = {
  cates: []

}
// 改变状态的唯一途径
const mutations = {

  set_cates(state, list) {
    state.cates = list
  }

}
// ajax，异步请求，代码都要在这
const actions = {
  getCates({ commit }) {
    getAllCates().then(res => {
      // console.log(res)
      if (res.data) {
        commit('set_cates', res.data)
      }
    })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions

}

