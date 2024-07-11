import {
	defineStore
} from 'pinia'
import {
	article
} from '../api/yuyuerequest'
import {handlePromise} from '../util/promise.js'


export const useCounterStore = defineStore('counter', {
	state: () => ({
		data: {}
	}),
	getters: {},
	actions: {
		insertYuyue(data) {
			return new handlePromise(article.insertYuyue, data);
		},
	}
});