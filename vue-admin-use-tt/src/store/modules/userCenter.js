import { getUserDetail, updateUserCenter } from "@/api/userCenter";



const state = {
    userInfo: {},
    userDetail: {}
}

const mutations = {
    updateInfo(state, userInfo) {
        state.userInfo = userInfo
    },
    getUserDetail(state, userDetail) {
        state.userDetail = userDetail
    }
}

const actions = {
    updateUserInfo({ commit }, dataInfo) {
        return new Promise((resolve, reject) => {
            updateUserCenter(dataInfo).then(res => {
                console.log("修改的用户信息: ", res)

                commit('updateInfo', dataInfo)

                resolve()
            }).catch(error => {
                console.log("error: ", error)
                reject()
            })
        })
    },
    getUserDetail({ commit }) {
        return new Promise((resolve, reject) => {
            getUserDetail().then(res => {
                // console.log("获取到用户信息: ", res.data)
                commit('getUserDetail', res.data)
                console.log("getUserDetail: ", state.userDetail)
                resolve()
            }).catch(error => {
                console.log("获取用户信息error: ", error)
                reject()
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