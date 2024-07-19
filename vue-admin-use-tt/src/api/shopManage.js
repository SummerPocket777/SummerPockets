import request from '@/utils/request'

export function getAllShopList(parentId) {
    return request({
      url: '/shop-user/business/selectById',
      method: 'get',
      params: {
        parentId
      }
    })
  }