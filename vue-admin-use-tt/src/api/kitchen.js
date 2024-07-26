import request from '@/utils/request'

export function getOrderList(shopId) {
  return request({
    url: '/consumer-order/order/getOrdersListByShopId',
    method: 'get',
    params: {
      shopId: shopId
    }
  })
}

export function getHistoryOrdersListByShop(shopId) {
  return request({
    url: '/consumer-order/order/getHistoryOrdersListByShop',
    method: 'get',
    params: {
      shopId: shopId
    }
  })
}


export function updateOrderStatus(status, id) {
  return request({
    url: '/consumer-order/order/updateOrderStatus',
    method: 'get',
    params: {
      status,
      id
    }
  })
}

