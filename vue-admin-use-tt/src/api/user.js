import request from '@/utils/request'

export function login(data) {
  console.log(data)
  return request({
    url: '/auth/user/doLogin',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/shop-user/shop/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/shop-user/shop/user/logout',
    method: 'post'
  })
}
export function getCode(phone,imageCode){
  return request({
    url:'/auth/business/getCode',
    method: 'get',
    params: {
      phone:phone,
      imgCode:imageCode
    }
  })
}
export function register(data){
  return request({
    url:'/auth/user/register',
    method: 'post',
    data
  })
}
//
// import request from '@/utils/request'
//
// export function login(data) {
//   return request({
//     url: '/vue-admin-template/user/login',
//     method: 'post',
//     data
//   })
// }
//
// export function getInfo(token) {
//   return request({
//     url: '/vue-admin-template/user/info',
//     method: 'get',
//     params: { token }
//   })
// }
//
// export function logout() {
//   return request({
//     url: '/vue-admin-template/user/logout',
//     method: 'post'
//   })
// }
