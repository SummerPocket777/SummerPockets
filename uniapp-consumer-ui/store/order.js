import {defineStore} from 'pinia'
import {handlePromise} from '../util/promise.js'
import {article} from '../api/order.js'

export const orderStore = defineStore('order',{
	state:()=>{

	},
	actions:{
		getOrderList(data){
			return new handlePromise(article.getOrderByUserIdAndShopId,data)
		},
	}
})