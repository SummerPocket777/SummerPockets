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

export function updateLogo(data){
  return request({
    url:'/shop-user/shop/userCenter/updateLogo',
    method:'post',
    data:data
  })
}

export function getUserDetail(){
  return request({
    url: '/auth/user/getUserDetail',
    method: 'get'
  })
}

// export function uploadFile(){
//   return request({
//     url: '/common-fileupload/fileupload/file/upload',
//     method:'post'
//   })
// }

// export function identityUpload(data){
//   return request({
//     url:'https://aip.baidubce.com/rest/2.0/ocr/v1/idcard',
//     method:'post',
//     headers:{
//       'Content-Type':'application/x-www-form-urlencoded'
//     },
//     data:data
//   })
// }
