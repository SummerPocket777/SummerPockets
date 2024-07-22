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
	getAllBusines(){
		return request({
			url:'/consumer-book/book/selectAllBusiness',
			method: 'GET',
		})
	},
	getAllYuyueInfoByIddd(data){
		console.log(data)
		return request({
			url:'/consumer-book/book/getYuyueLists',
			method: 'GET',
			data,
		})
	},
	cancelBooks(data){
		return request({
			url:'/consumer-book/book/cancelBook',
			method: 'GET',
			data,
		})
	}
	
}