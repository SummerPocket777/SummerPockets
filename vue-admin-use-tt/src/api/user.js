import request from '@/utils/request'

export function login(data) {
  console.log(data)
  return request({
    url: '/consumer-user/consumer/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/consumer-user/consumer/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/consumer-user/consumer/user/logout',
    method: 'post'
  })
}
