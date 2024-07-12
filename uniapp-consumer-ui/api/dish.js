import request from '@/http/request.js'

export const dish = {
	//用户登录
	getDishList(data){
		return request({
			url: '/consumer-dish/consumer/dish/cate/getDishList',
			method: 'get',
			data
		})	
	}
}