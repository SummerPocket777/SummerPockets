import request from '@/http/request.js'

export const article = {
	//根据用户id和商铺id获得数据
	getOrderByUserIdAndShopId(data){
		return request({
			url: '/consumer-order/order/getOrdersList',
			method: 'get',
			data:{
				userId:1,
				shopId:1
			}	
		})	
	},

}