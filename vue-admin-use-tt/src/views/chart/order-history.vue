<template>
  <div>
    <el-table :data="orderINfoList" style="width: 100%;">
        <el-table-column label="订单号" prop="number"></el-table-column>
        <el-table-column label="桌号" prop="tableId"></el-table-column>
        <el-table-column label="创建时间" prop="orderTime">
        </el-table-column>
        <el-table-column label="状态" prop="status">
            <template slot-scope="scope">
                <span v-if="scope.row.status === 1">待付款</span>
                <span v-else-if="scope.row.status === 2">已取消</span>
                <span v-else-if="scope.row.status === 5">已完成</span>
            </template>
        </el-table-column>
        <el-table-column  fixed="right" label="操作">
            <template slot-scope="scope">
                <el-button @click="handleClick(scope.row,scope.$index)" type="text" size="small">查看信息</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-dialog  :visible.sync="centerDialogVisible" width="30%" center>
        <el-table :data="foodList" style="100%">
            <el-table-column label="菜品" prop="dish.name"></el-table-column>
            <el-table-column label="口味" prop="dishFlavor"></el-table-column>
            <el-table-column label="单价" prop="dish.price"></el-table-column>
            <el-table-column label="数量" prop="number"></el-table-column>
        </el-table>
        <span style="font-size: 20px;text-align: right;display: block;margin-right: 50px;margin-top: 30px;">总金额：{{ totalAmount }}</span>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
        </span>
        </el-dialog>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
    data() {
        return {
        //    id:1 ,
           orderINfoList:[],
           centerDialogVisible:false,
           foodList:[]
        }
    },
    computed: {
    ...mapState('user',['id']),
    totalAmount() {
      // 计算总金额
      return this.foodList.reduce((total, item) => {
        return total + item.dish.price * item.number;
      }, 0);
    }
    

    },
    created() {     
        this.getHistoryListByID()
    },
    methods: {
        ...mapActions('kitchen', ['getOrderList','getHistoryOrdersListByShop']),
        getHistoryListByID(){
            this.getHistoryOrdersListByShop(this.id)
            for(var i = 0;i<this.$store.state.kitchen.historyorderList.length;i++){
                let date = new Date(this.$store.state.kitchen.historyorderList[i].orderTime);
                let year = date.getFullYear();
                let month = String(date.getMonth() + 1).padStart(2, '0'); // Month is zero-indexed
                let day = String(date.getDate()).padStart(2, '0');
                let hours = String(date.getHours()).padStart(2, '0');
                let minutes = String(date.getMinutes()).padStart(2, '0');
                let seconds = String(date.getSeconds()).padStart(2, '0');

                this.$store.state.kitchen.historyorderList[i].orderTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
            }
            this.orderINfoList = this.$store.state.kitchen.historyorderList
        },
        handleClick(data,index){
            console.log(index);
            this.foodList = data.orderDetailList
            console.log(this.foodList,11111111111111111);
            this.centerDialogVisible = true
        }
    }
}
</script>

<style>

</style>