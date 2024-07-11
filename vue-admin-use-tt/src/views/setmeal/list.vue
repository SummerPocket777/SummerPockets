<template>
  <div class="good-list">
    <!-- 筛选面板 -->
    <el-row>
      <el-col :span="24">
        套餐名称：<el-input style="width: 135px" placeholder="请输入内容" />
        套餐分类：<el-select v-model="cateName" clearable  placeholder="请选择菜品分类" @change="getAllCate">
          <el-option
            v-for="item in cates"
            :key="item.id"
            :label="item.cateName"
            :value="item.id"
          />
        </el-select>
        <el-button type="primary" icon="el-icon-search">搜索</el-button>
        <el-button type="primary" icon="el-icon-edit">添加套餐</el-button>
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
        <template v-slot="{ row, $index }">
          <div :class=" row ">{{ $index + 1 }}</div>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="商品" align="center" width="180">
        <!-- v-slot="scope"  scope.row  scope.$index -->
        <!-- 作用域插槽：重很重 -->
        <template v-slot="{ row, $index }">
          <img :src="row.img" style="width: 60px" alt="">
          <div :class=" $index ">{{ row.name }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="price" label="价格" align="center">
        <template v-slot="{ row, $index }">
          <div :class=" $index ">{{ `￥${row.price.toFixed(2)}` }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="cate" label="品类" align="center">
        <template v-slot="{ row, $index }">
          <div :class=" $index ">{{ row.cate }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="hot" label="是否热销" align="center">
        <template v-slot="{ row, $index }">
          <div :class=" $index ">{{ row.hot ? "是" : "否" }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="create_time" label="发布时间" align="center">
        <template v-slot="{ row, $index }">
          <div :class=" $index ">{{ row.create_time }}</div>
        </template>
      </el-table-column>

      <el-table-column prop="check_status" label="商品状态" align="center">
        <template v-slot="{ row, $index }">
          <div :class=" $index ">{{ row.check_status ? "已上架" : "待审核" }}</div>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="230" align="center">
        <template v-slot="{ row }">
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
export default {
  name: 'Good',
  props: [],

  data() {
    return {
      cateName:"",//分类名
      cates: [
        { id: 1, cateName: '哈哈哈' },
        { id: 2, cateName: '嘻嘻嘻' }
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
    // 改变一页显示多少条数据触发
    handleSizeChange() {},
    // 点击页码触发
    handleCurrentChange() {},
    getAllCate() {
      this.$getRequest("/diagnosis/admin/admin/adminUserPage", params).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data.items;
          this.currentPage = res.data.pageNo;
          this.pageSize = res.data.pageSize;
          this.total = res.data.totalRow;
        } else {
          this.$message.error(res.message || "查询分页数据失败");
        }
      });
    },
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
