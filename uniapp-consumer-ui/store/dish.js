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
			return new handlePromise(dish.getDishList,data)
		},
		sumbitOrder(data){
			return new handlePromise(dish.sumbitOrder,data)
		},
	}
})