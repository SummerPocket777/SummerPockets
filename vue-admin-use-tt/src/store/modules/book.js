import { getBookList, updateStatus } from "@/api/book"


// 共享状态
const state = {
  bookList: []
}
// 改变状态的唯一途径
const mutations = {
  set_bookList(state, list) {
    state.bookList = list
  },

  update_status(state, data) {
    state.bookList.forEach(item => {
      if (item.bookId === data.bookId) {
        item.isStatus = data.isStatus
      }
    })
  }




}
// ajax，异步请求，代码都要在这
const actions = {
  getBookList({ commit }, id) {

    return new Promise((resolve, reject) => {
      getBookList(id).then(res => {

        if (res.data) {
          commit('set_bookList', res.data)
        }
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },



  updateStatus({ commit }, data) {

    return new Promise((resolve, reject) => {
      commit('update_status', data)
      updateStatus(data.isStatus, data.bookId).then(res => {
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

