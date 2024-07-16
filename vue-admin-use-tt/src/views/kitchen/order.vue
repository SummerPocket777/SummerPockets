<template>
  <div >

    <el-container>
  <el-header>
    <el-row style="height: 65px;padding-top: 15px;">
      <el-col :span="5"  style="display: flex;">
        <div style="width: 30%;margin-top: 10px;">菜品名称：</div>
        <el-input
          placeholder="请输入内容"
          v-model="dishInput"
          clearable
          >
        </el-input>
      </el-col>

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
        <div style="width: 30%; margin-top: 10px;">桌号:</div>
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
      :data="tableData"
      border
    >
      <el-table-column
        fixed
        prop="date"
        label="订单号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="name"
        label="菜品名称"
        width="120">
      </el-table-column>
      <el-table-column
        prop="tableid"
        label="桌号"
        width="120">
      </el-table-column>
      <el-table-column
        prop="day"
        label="日期"
        width="120">
      </el-table-column>
      <el-table-column
        prop="time"
        label="时间"
        width="120">
      </el-table-column>
      <el-table-column
        prop="state"
        label="状态"
        width="120">
      </el-table-column>

      <el-table-column

        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">上菜</el-button>
          <el-button type="text" size="small">退菜</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-main>
</el-container>
  </div>
</template>

<script>

  export default {
    methods: {
      handleClick(row) {
        console.log(row);
      },
      findList() {
        this.loading = true
        this.$store.dispatch('kitchen/getOrderList', 1 ).then((res) => {
        console.log(res);
        this.loading = false
        }).catch(() => {
          this.$message({
            message: '查询失败',
            type: 'error'
          })
          this.loading = false
        })

      }
    },
    created() {
    this.findList()
  },

    data() {
      return {
        dishInput:'',
        tableData: [{
          date: '54555',
          name: '汉堡',
          tableid: '1',
          state: '未上菜',
          day: '2020-01-01',
          time: '12:00'

        }],
        options: [{
          value: '1',
          label: '已上菜'
        }, {
          value: '2',
          label: '未上菜'
        }, {
          value: '3',
          label: '已退菜'
        }],
        value: ''
      }
    }
  }
</script>
