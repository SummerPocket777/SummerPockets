import {
	defineStore
} from 'pinia'
import {handlePromise} from '../util/promise.js'
import {
	article
} from '../api/user.js'

export const useUserStore = defineStore('user',{
	state:()=>({
				myUserInfo:{
					name:'',
					image:'',
					id:''
				}
			
	}),
	actions:{
		login(userInfo){

			return new handlePromise(article.login,userInfo)
		},
		islogin(){
			return new handlePromise(article.islogin,'')
		}
	}
})