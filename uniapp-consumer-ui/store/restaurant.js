import {
	defineStore
} from 'pinia'


export const useRestaurantStore = defineStore('restaurant', {
	state: () => ({
		businessId:'',
		tableId:''
	}),
	getters: {},
	actions: {
		setBusinessId(businessID){
			this.businessId = businessID
		},
		setTableId(tableID){
			this.tableId = tableID
		},
		reset(){
			this.businessId = ''
			this.tableId = ''
		}
	}
});