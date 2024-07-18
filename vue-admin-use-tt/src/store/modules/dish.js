import { getDishList } from '@/api/dish'

const getDefaultState = () => {
  return {
    dishList: [],

  }
}

const state = getDefaultState()

const mutations = {
  set_dishList(state, data) {
    state.dishList = data
  }
}

const actions = {


  getDishList({ commit }, id) {
    return new Promise((resolve, reject) => {
      getDishList(id).then(res => {

        if (res.data) {
          commit('set_dishList', res.data)
        }
        resolve(res.data)
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

