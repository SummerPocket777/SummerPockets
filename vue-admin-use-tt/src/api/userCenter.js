import request from '@/utils/request'

export function updateUserCenter(data){
    return request({
      url:'/shop-user/shop/userCenter/updateInfo',
      method:'post',
      // data:{
      //   id,
      //   businessName,
      //   phone,
      //   address
      // }
      data:data
    })
}

export function getUserDetail(){
  return request({
    url: '/auth/user/getUserDetail',
    method: 'get'
  })
}
