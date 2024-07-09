import request from '@/http/request.js'

export const article = {
	// 添加预约
	insertYuyue(data){
		console.log(data)
		return request({
			url:'http://127.0.0.1:9999/book/add',
			method: 'POST',
			data,
		})
	},
}