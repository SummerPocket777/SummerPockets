import request from '@/utils/request'

export function getAllCate(shopId) {
  console.log(data)
  return request({
    url: '/consumer-dish/consumer/dish/cate/getAll',
    method: 'get',
    params: {
      shopId
    }
  })
}


export function getDishList(shopId) {
  return request({
    url: '/consumer-dish/dish/getAll',
    method: 'get',
    params: {
      shopId
    }
  })
}