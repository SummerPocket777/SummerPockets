import request from '@/http/request.js'

export const article = {
	// 添加预约
	insertYuyue(data){
		return request({
			url:'/consumer-book/book/add',
			method: 'POST',
			data,
		})
	},
	showYuyueStore(data){
		return request({
			url:'/consumer-book/book/getBNameById/{id}',
			method: 'GET',
			data,
		})
	},
	showYuyueInfo(data){
		return request({
			url:'/consumer-book/book/selectPage',
			method: 'GET',
			data,
		})
	},
	
}