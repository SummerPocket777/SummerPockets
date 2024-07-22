import request from '@/utils/request'

export function getAllCate(shopId) {
  return request({
    url: '/consumer-dish/consumer/dish/cate/getAll',
    method: 'get',
    params: {
      shopId
    }
  })
}


export function getDishList(data) {
  return request({
    url: '/consumer-dish/dish/getAll',
    method: 'post',
    data
  })
}

export function addDish(data) {
  return request({
    url: '/consumer-dish/dish/add',
    method: 'post',
    data
  })
}