import request from '@/utils/request'

export function getAllCate(data) {
  console.log(data)
  return request({
    url: '/consumer-dish/consumer/dish/cate/getAll',
    method: 'get',
    data
  })
}
