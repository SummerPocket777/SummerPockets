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
      :data="bookList"
      border
    >
      <el-table-column
        fixed
        prop="bookId"
        label="预约号"
    >
      </el-table-column>
      <el-table-column
        prop="bookNumber"
        label="人数"
      >
      </el-table-column>
      <el-table-column
        prop="bookName"
        label="预约人姓名"
       >
      </el-table-column>
      <el-table-column
        prop="bookPhone"
        label="预约人电话"
      >
      </el-table-column>
      <el-table-column
        prop="bookDate"
        label="预约时间"
        >
      </el-table-column>
      <el-table-column
        prop="isStatus"
        label="状态"
       >
      </el-table-column>
      <el-table-column
        prop="isStatus"
        label="状态"
        >
        <template v-slot="scope">
            <span v-if="scope.row.isStatus == 1">未到店</span>
            <span v-if="scope.row.isStatus == 2">已到店</span>
            <span v-if="scope.row.isStatus == 3">已超时</span>
        </template>

      </el-table-column>
      <el-table-column

        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button  v-if="scope.row.isStatus==1 " @click="handleClick(scope.row)" type="text" size="small">到店</el-button>
          <el-button type="text" size="small">编辑</el-button>

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
      ...mapActions('book', ['getBookList','updateStatus']),
      handleClick(row) {
        console.log(row);
        row.isStatus=2;
        this.updateStatus(row)
      },

      findList(){
        this.getBookList(1)
        console.log(this.bookList)
      }
    },
    computed: {
    ...mapState('book', ['bookList'])
    },

    created() {
      this.getBookList(1)
      console.log(this.bookList)
    },

    data() {
      return {
        tableData: [{
          bookid: '54555',
          num: '6',
          tableid: '1',
          state: '未到',
          day: '2020-01-01',
          time: '12:00'

        }],
        options: [{
          value: '1',
          label: '已到'
        }, {
          value: '2',
          label: '未到'
        }, {
          value: '3',
          label: '超时'
        }],
        value: ''
      }
    }
  }
</script>
