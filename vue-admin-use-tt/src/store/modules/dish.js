// import { getDishList } from '@/api/dish'

// const state = {
//   dishList: [],
//   pageNo: 1,
//   pageSize: 3,
//   pageTotalCount: 1
// }



// const mutations = {
//   set_dishList(state, list) {
//     console.log(state.dishList)
//     console.log(list)
//     state.dishList = list
//   },

//   set_pageNo(state, data) {
//     state.pageNo = data
//   },

//   set_pageSize(state, data) {
//     state.pageSize = data
//   },

//   set_pageTotalCount(state, data) {
//     state.pageTotalCount = data
//   }
// }

// const actions = {


//   getDishList({ commit, state }, data) {
//     const { pageNo, pageSize, id } = data

//     return new Promise((resolve, reject) => {
//       getDishList({ pageNo: pageNo, id: id, pageSize: pageSize }).then(res => {
//         console.log(res.data.items)
//         if (res.data) {
//           commit('set_dishList', res.data.items)
//           commit('set_pageTotalCount', res.data.totalCount)
//           commit('set_pageNo', res.data.pageNo)
//           commit('set_pageSize', res.data.pageSize)
//         }

//         resolve(res.data)
//       }).catch(error => {
//         reject(error)
//       })
//     })

//   }
// }


// export default {
//   namespaced: true,
//   state,
//   mutations,
//   actions

// }