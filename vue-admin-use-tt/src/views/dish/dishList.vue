<template>
  <div class="good-list">
    <!-- 筛选面板 -->
    <el-row>
      <el-col :span="24">
        <el-input style="width: 135px" placeholder="请输入内容" v-model="inputValue" />
        <el-select v-model="cateValue" placeholder="请选择">
          <el-option v-for="item in cateList" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>

        <el-button type="primary" icon="el-icon-search" @click="getList()">搜索</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="openForm()">添加</el-button>
        <el-button type="primary" icon="el-icon-download">导出</el-button>

      </el-col>
    </el-row>

    <!-- 表格 -->
    <el-table :data="dishList" border style="width: 100%; margin-top: 20px">
      <el-table-column prop="id" label="序号" align="center" sortable width="200">
        <template slot-scope="{ row, $index }">
          <div :class=" row ">{{ $index + 1 }}</div>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="商品" align="center" width="200">
        <!-- slot-scope="scope"  scope.row  scope.$index -->
        <!-- 作用域插槽：重很重 -->
        <template slot-scope="{ row, $index }">
          <img :src="row.image" style="width: 60px" alt="">
          <div :class=" $index ">{{ row.name }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="price" label="价格" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ `￥${row.price.toFixed(2)}` }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="cate" label="品类" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.category.name }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="hot" label="是否热销" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.isHot==1 ? "是" : "否" }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="create_time" label="发布时间" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.create_time }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="check_status" label="商品状态" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.status==1 ? "已上架" : "待审核" }}</div>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="230" align="center">
        <template slot-scope="{ row }">
          <el-button type="primary" size="mini">编辑</el-button>
          <el-button v-if="row.status==1" type="primary" size="mini">详情</el-button>
          <el-button v-else type="success" size="mini">审核</el-button>
          <el-button size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination style="margin-top: 20px" 
      background
      layout="prev, pager, next"
      :total="pageData.totalRow" 
      :page-size="pageData.pageSize"
      :current-page="pageData.pageNo"  
      @current-change="handleCurrentChange" />
    <dialog-form ref="dialogForm"></dialog-form>
  </div>

</template>

<script>
import dialogForm from './components/listForm.vue'
import {getDishList,getAllCate} from '@/api/dish'



export default {

  props: [],
  components: { dialogForm },
  data() {
    return {
      cateList:[],
      cateValue:'',
      inputValue:'',

      pageData : {
        pageNo : 1,
        pageSize : 3,
        id:1,
        pageTotalCount:5,
        totalRow:3
      },
      dishList : [],

    }
  },
  methods: {
    openForm(id){
      if(id){
        this.$refs.dialogForm.open(id)
      }else{
        this.$refs.dialogForm.open()
      }           
    },
    // 点击页码触发
    handleCurrentChange(index) {
      this.pageData.pageNo = index
      this.getList()
    },
    getList(){

      this.getListCate()
      const { pageNo, pageSize, id } = this.pageData
      var categoryId = this.cateValue
      var keyword = this.inputValue

      getDishList({ pageNo: pageNo, id: id, pageSize: pageSize,categoryId:categoryId,keyword:keyword }).then(res => {
          if (res.data) {
              this.dishList = res.data.items
              this.pageData.totalRow = res.data.totalRow
          }
          console.log(this.dishList)
        })


    },
    getListCate(){
      getAllCate(1).then(res => {
        this.cateList = res.data
      })
    }

  
  },


  created(){
    this.getList()
    this.getListCate()

    
  },



}
</script>

<!-- scss -->
<style lang="scss" scoped>
.good-list {
  padding: 20px;
  box-sizing: border-box;
}
</style>
