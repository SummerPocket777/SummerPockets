import request from '@/http/request.js'

export const article = {
	//用户登录
	login(data){
		return request({
			url: '/auth/user/wxLogin',
			method: 'POST',
			data	
		})	
	},
	islogin(){
		return request({
			url: '/auth/user/isLogin',
			method: 'GET',
		})
	}
}