<template>
  <div >
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
        prop="state"
        label="状态"
        width="120">
      </el-table-column>

      <el-table-column

        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    methods: {
      handleClick(row) {
        console.log(row);
      }
    },

    data() {
      return {
        tableData: [{
          date: '54555',
          name: '汉堡',
          tableid: '1',
          state: '未上菜',
          
        }]
      }
    }
  }
</script>