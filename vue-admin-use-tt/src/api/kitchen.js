import request from '@/utils/request'

export function getOrderList(params) {
  return request({
    url: '/api/consumer-order/order/getOrdersListByShopId',
    method: 'get',
    params
  })
}