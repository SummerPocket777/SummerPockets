import request from '@/http/request.js'

export const dish = {
	getDishList(data){
		return request({
			url: '/consumer-dish/consumer/dish/cate/getDishList',
			method: 'get',
			data
		})	
	},
	sumbitOrder(data){
		return request({
			url: '/consumer-order/order/submit',
			method: 'post',
			data
		})	
	},
}