import request from "@/http/request";

export const Stephen = {
    //查看历史订单
    checkOrders(data){
        return request({
            url: '/orders/history/{id}',
            method:'POST',
            data
        })
    }
}
