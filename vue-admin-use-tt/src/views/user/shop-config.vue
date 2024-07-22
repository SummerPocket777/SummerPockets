<template>
  <div class="good-list">
    <!-- 筛选面板 -->
    <el-row>
      <el-col :span="24">
        <el-input style="width: 135px" placeholder="请输入内容" />


        <el-button type="primary" icon="el-icon-search">搜索</el-button>
        <el-button type="primary" icon="el-icon-edit">添加</el-button>
        <el-button type="primary" icon="el-icon-download">导出</el-button>

      </el-col>
    </el-row>

    <!-- 表格 -->
    <el-table :data="list" border style="width: 100%; margin-top: 20px">
      <el-table-column
        prop="id"
        label="序号"
        align="center"
        sortable
        width="180"
      >
        <template slot-scope="{ row, $index }">
          <div :class=" row ">{{ $index + 1 }}</div>
        </template>
      </el-table-column>
      <el-table-column prop="businessName" label="店铺名" align="center" width="180">
        <!-- slot-scope="scope"  scope.row  scope.$index -->
        <!-- 作用域插槽：重很重 -->
        <template slot-scope="{ row, $index }">

          <div :class=" $index ">{{ row.businessName }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="avatar" label="logo" align="center">
        <template slot-scope="{ row }">
          <img :src="row.avatar" style="width: 60px" alt="">
        </template>
      </el-table-column>

      <el-table-column prop="phone" label="联系方式" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.phone }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="hot" label="地址信息" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.address }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="createTime" label="创建时间" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.createTime }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="status" label="门店状态" align="center">
        <template slot-scope="{ row, $index }">
          <div :class=" $index ">{{ row.status ? "已上架" : "待审核" }}</div>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="230" align="center">
        <template slot-scope="{ row }">
          <el-button type="primary" size="mini">编辑</el-button>
          <el-button
            v-if="row.published"
            type="primary"
            size="mini"
          >详情</el-button>
          <el-button v-else type="success" size="mini">审核</el-button>
          <el-button size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      style="margin-top: 20px"
      :current-page="page"
      :page-sizes="[2, 5, 10, 20]"
      :page-size="size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="400"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { getAllShopList } from '@/api/shopManage'
export default {
  created() {
    this.getAllShop()
  },
  name: 'Good',
  props: [],
  data() {
    return {
      list: [
        {
          id: 1,
          create_time: '2016-05-02',
          name: '猪脚饭店',
          address: '111',
          img: 'https://img10.360buyimg.com/mobilecms/s360x360_jfs/t1/132363/40/2790/109217/5ef04734E44252d8a/c9f28f327259059e.jpg!q70.dpg.webp',
          price: 19.9,
          phone: '133333333333',
          hot: true,
          published: false,
          check_status: false
        },
        {
          id: 2,
          create_time: '2016-05-02',
          name: '鸡腿饭店',
          address: '222',
          img: 'https://img10.360buyimg.com/mobilecms/s360x360_jfs/t1/132363/40/2790/109217/5ef04734E44252d8a/c9f28f327259059e.jpg!q70.dpg.webp',
          price: 19.9,
          phone: '17777777777',
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
    getAllShop(){
      getAllShopList(19)
      .then(res => {
        this.list = res.data
        console.log(this.list);
      })
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
