<template>
  <div class="good-list">
    <!-- 筛选面板 -->
    <el-row>
      <el-col :span="24">
        <el-input style="width: 135px" placeholder="请输入内容" />
        <el-select v-model="value" placeholder="请选择">
          <el-option v-for="item in cates" :key="item.id" :label="item.cate_zh" :value="item.cate" />
        </el-select>
        <el-date-picker type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" />
        <el-button type="primary" icon="el-icon-search">搜索</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="openForm()">添加</el-button>
        <el-button type="primary" icon="el-icon-download">导出</el-button>

      </el-col>
    </el-row>

    <!-- 表格 -->
    <el-table :data="list" border style="width: 100%; margin-top: 20px">
      <el-table-column prop="id" label="序号" align="center" sortable width="180">
        <template slot-scope="{ row, $index }">
          <div :class=" row ">{{ $index + 1 }}</div>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="商品" align="center" width="180">
        <!-- slot-scope="scope"  scope.row  scope.$index -->
        <!-- 作用域插槽：重很重 -->
        <template slot-scope="{ row, $index }">
          <img :src="row.img" style="width: 60px" alt="">
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
          <div :class=" $index ">{{ row.cate }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="hot" label="是否热销" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.hot ? "是" : "否" }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="create_time" label="发布时间" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.create_time }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="check_status" label="商品状态" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.check_status ? "已上架" : "待审核" }}</div>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="230" align="center">
        <template slot-scope="{ row }">
          <el-button type="primary" size="mini">编辑</el-button>
          <el-button v-if="row.published" type="primary" size="mini">详情</el-button>
          <el-button v-else type="success" size="mini">审核</el-button>
          <el-button size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination style="margin-top: 20px" :current-page="page" :page-sizes="[2, 5, 10, 20]" :page-size="size"
      layout="total, sizes, prev, pager, next, jumper" :total="400" @size-change="handleSizeChange"
      @current-change="handleCurrentChange" />
    <dialog-form ref="dialogForm"></dialog-form>
  </div>

</template>

<script>
import dialogForm from './components/listForm.vue'
export default {
  name: 'Good',
  props: [],
  components: { dialogForm },
  data() {
    return {
      cates: [
        { id: 1, cate_zh: '电器', cate: 'dianqi' },
        { id: 2, cate_zh: '生活', cate: 'shenghuo' }
      ],
      list: [
        {
          id: 1,
          create_time: '2016-05-02',
          name: '猪脚饭',
          address: '111',
          img: 'https://img10.360buyimg.com/mobilecms/s360x360_jfs/t1/132363/40/2790/109217/5ef04734E44252d8a/c9f28f327259059e.jpg!q70.dpg.webp',
          price: 19.9,
          cate: '套餐饭',
          hot: true,
          published: false,
          check_status: false
        },
        {
          id: 2,
          create_time: '2016-05-02',
          name: '鸡腿饭',
          address: '222',
          img: 'https://img10.360buyimg.com/mobilecms/s360x360_jfs/t1/132363/40/2790/109217/5ef04734E44252d8a/c9f28f327259059e.jpg!q70.dpg.webp',
          price: 19.9,
          cate: '套餐饭',
          hot: false,
          published: true,
          check_status: true
        }
      ],
      page: 1,
      size: 2
    }
  },
  methods: {
    openForm(id){
      console.log(this.$refs.dialogForm)
      if(id){
        this.$refs.dialogForm.open(id)
      }else{
        this.$refs.dialogForm.open()
      }           
    },
    // 改变一页显示多少条数据触发
    handleSizeChange() {},
    // 点击页码触发
    handleCurrentChange() {}
  }
}
</script>

<!-- scss -->
<style lang="scss" scoped>
.good-list {
  padding: 20px;
  box-sizing: border-box;
}
</style>
