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
    insertYuyue(data) {
         return new Promise((resolve, reject) => {
           article.insertYuyue(data)
             .then(response => {
               // 这里可以根据你的需求处理 response
               this.data = response.data; // 假设 response.data 是你需要的数据
               resolve(response);
             })
             .catch(error => {
               // 处理错误
               console.error("Error in insertYuyue:", error);
               reject(error); // 向外抛出错误
             });
         });
       }
     }
   });