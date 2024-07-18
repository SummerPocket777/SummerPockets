import request from '@/utils/request'

export function getBookList(shopId) {
  return request({
    url: '/consumer-book/book/selectPage',
    method: 'get',
    params: {
      shopId: shopId
    }
  })
}



export function updateStatus(status, id) {
  return request({
    url: '/consumer-book/book/updateStatus',
    method: 'get',
    params: {
      status,
      id
    }
  })
}
