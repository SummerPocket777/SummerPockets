import { defineStore } from 'pinia'
import { article } from '../api/yuyuerequest'

export const useCounterStore = defineStore('counter',{
	state: () => ({
		count:20,
		data:{}
	}),
	getters:{
		double:(state) => state.count * 2
	},
	actions:{
		increment(){
			this.count++
		},
		insertYuyue(data){
			article.insertYuyue(data)
		}
		
	}
})