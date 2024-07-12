import {
	defineStore
} from 'pinia'
import {handlePromise} from '../util/promise.js'
import {
	dish
} from '../api/dish.js'

export const dishStore = defineStore('dish',{
	state:()=>{

	},
	actions:{
		getDishList(data){
			console.log("user.js data",data)
			return new handlePromise(dish.getDishList,data)
		}
	}
})