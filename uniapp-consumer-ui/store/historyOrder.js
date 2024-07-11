import { defineStore } from "pinia";
import { Stephen } from "../api/historyOrderRequest"
export const historyOrder= defineStore('counter',{
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
        checkOrders(data){
            return new Promise((resolve,reject) => {
                Stephen.checkOrders(data)
                    .then(response => {
                        //根据需求处理请求 response
                        this.data = response.data;
                        resolve(response);
                    })
                    .catch(error => {
                        //处理错误
                        console.error("Error in checkOrders:", error);
                        reject(error); //向外抛出错误
                    });
            });
        }
    }
});