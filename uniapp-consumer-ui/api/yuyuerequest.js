import request from '@/http/request.js'

export const article = {
	// 添加预约
	insertYuyue(data){
		return request({
			url:'/book/add',
			method: 'POST',
			data,
		})
	},
}