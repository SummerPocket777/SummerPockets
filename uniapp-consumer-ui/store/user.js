import {
	defineStore
} from 'pinia'
import {handlePromise} from '../util/promise.js'
import {
	article
} from '../api/user.js'

export const useUserStore = defineStore('user',{
	state:()=>{

	},
	actions:{
		login(userInfo){
			console.log("user.js userInfo",userInfo)
			return new handlePromise(article.login,userInfo)
		},
		islogin(){
			return new handlePromise(article.islogin,'')
		}
	}
})