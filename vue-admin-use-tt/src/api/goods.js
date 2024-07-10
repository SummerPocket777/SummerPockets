import request from '@/utils/request'

export function getGoodList(params) {
  return request({
    url: '/api/consumer-dish/good/list',
    method: 'get',
    params
  })
}

export function getAllCates(params = {}) {
  return request({
    url: '/api/consumer-dish/consumer/dish/getAllCate',
    method: 'get',
    params
  })
}

export function submitGood(data) {
  return request({
    url: '/api/consumer-dish/consumer/dish/insertDish',
    method: 'POST',
    data
  })
}

export function getGoodInfo(id) {
  return request({
    url: '/api/consumer-dish/good/info',
    method: 'GET',
    params: {
      id
    }
  })
}

export function getCheckGoodList(params) {
  return request({
    url: '/api/consumer-dish/check/good/list',
    method: 'get',
    params
  })
}

export function checkGood(good_id) {
  return request({
    url: '/api/consumer-dish/check/good',
    method: 'POST',
    data: {
      good_id,
      check: 1
    }
  })
}

export function goodDel(ids) {
  return request({
    url: '/api/consumer-dish/good/del',
    method: 'POST',
    data: {
      ids
    }
  })
}
