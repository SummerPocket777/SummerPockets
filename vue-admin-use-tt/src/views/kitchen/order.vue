<template>
  <div >

    <el-container>
  <el-header>
    <el-row style="height: 65px;padding-top: 15px;">


      <el-col :span="3" :offset="1" style="display: flex ;height: 65px;">
        <div style="width: 50%; margin-top: 10px;">状态选择：</div>
        <el-select v-model="value" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            >
            </el-option>
          </el-select>
        </el-col>



        <el-col :span="3" :offset="1" style="display: flex ;height: 65px;">
          <el-button type="primary" style="height: 65%;"  @click="findList()">查询</el-button>
        </el-col>

    </el-row>

  </el-header>
  <el-main>
    <el-table
      :data="orderList"
      border
    >
      <el-table-column
        fixed
        prop="number"
        label="订单号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="orderDetail.dish.name"
        label="菜品名称"
        width="120">
      </el-table-column>
      <el-table-column
        prop="orderDetail.dishFlavor"
        label="口味"
        width="120">
      </el-table-column>
      <el-table-column
        prop="orderDetail.number"
        label="数量"
        width="120">
      </el-table-column>
      <el-table-column
        prop="tableId"
        label="桌号"
        width="120"
        :filters="getfilterNameItem()"
        >
      </el-table-column>
      <el-table-column
        prop="daytt"
        label="日期"
        width="120">
      </el-table-column>
      <el-table-column
        prop="timett"
        label="时间"
        width="120">
      </el-table-column>
      <el-table-column
        prop="orderDetail.status"
        label="状态"
        width="120">

        <template slot-scope="scope">
            <span v-if="scope.row.orderDetail.status == 1">未上菜</span>
            <span v-if="scope.row.orderDetail.status == 2">已上菜</span>
            <span v-if="scope.row.orderDetail.status == 3">在催单</span>
            <span v-if="scope.row.orderDetail.status == 4">已退菜</span>


        </template>



      </el-table-column>

      <el-table-column
        prop="orderDetail.status"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button v-if="scope.row.orderDetail.status==1 || scope.row.orderDetail.status==3 "  @click="handleClick(scope.row)" type="text" size="small">上菜</el-button>

          <el-button v-if="scope.row.orderDetail.status!=4 " type="text" size="small">退菜</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-main>
</el-container>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
  export default {
    methods: {
      ...mapActions('kitchen', ['getOrderList','updateOrderStatus']),
      handleClick(row) {
        console.log(row);
        row.orderDetail.status=2;
        this.updateOrderStatus(row)
      },

      findList() {
         this.getOrderList(1)

      },

      getfilterNameItem() {
          let apiArr = [  // 从后端获取筛选的字段
            { text: "1", value: "1" },
            { text: "2", value: "2" },
            { text: "3", value: "3" },
            { text: "4", value: "4" },
          ];
          return apiArr; // return返回去
      }


      
    },
    computed: {
    ...mapState('kitchen', ['orderList'])

    },
    created() {
      this.getOrderList(1)

  },

    data() {
      return {
        dishInput:'',

        options: [{
          value: '1',
          label: '已上菜'
        }, {
          value: '2',
          label: '未上菜'
        }, {
          value: '3',
          label: '被催单'
        },{
          value: '4',
          label: '已退菜'
        }
      ],
      tableOptions: [{
          value: '1',
          label: '1'
        }, {
          value: '2',
          label: '2'
        }, {
          value: '3',
          label: '3'
        },{
          value: '4',
          label: '4'
        }
      ],
      tableValue:'',


        value: ''
      }
    }
  }
</script>
