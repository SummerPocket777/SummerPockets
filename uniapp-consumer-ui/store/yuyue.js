import {
	defineStore
} from 'pinia'
import {
	article
} from '../api/yuyuerequest'
import {
	handlePromise
} from '../util/promise.js'


export const useCounterStore = defineStore('counter', {
	state: () => ({
		businessInfo: {
			name: '',
		},
		allYuyueInfo: []

	}),
	getters: {},
	actions: {
		insertYuyue(data) {
			return new handlePromise(article.insertYuyue, data);
		},
		showYuyueStore(data) {
			return new handlePromise(article.showYuyueStore, data);
		},
		showYuyueInfo(data) {
			return new handlePromise(article.showYuyueInfo, data);
		},
		getAllBusinessin() {
			return new handlePromise(article.getAllBusines);
		},
		getAllYuyueInfoByIdd(data){
			console.log("store --- ",data)
			return new handlePromise(article.getAllYuyueInfoByIddd,data);
		},
		cancelBooke(data){
			return new handlePromise(article.cancelBooks,data);
		},
		setAllYuyueInfo(data){
			this.allYuyueInfo = data
		}
	}
});