<template>
  <div>
    <el-select v-model="selectedShop" placeholder="请选择店铺" @change="updateTableList">
      <el-option
        v-for="shop in shops"
        :key="shop.id"
        :label="shop.name"
        :value="shop.id">
      </el-option>
    </el-select>

    <div style="margin-top: 20px;">
      <el-button @click="addTable">添加桌子</el-button>
      <el-button @click="removeTable">减少桌子</el-button>
    </div>

    <el-row style="margin-top: 20px;">
      <el-col :span="8" v-for="(table, index) in tables" :key="table.id" :offset="index > 0 ? 2 : 0">
        <el-card :body-style="{ padding: '0px' }">
          <div style="padding: 14px;">
            <span>桌号: {{ table.id }}</span>
            <QrCode :id="'qrcode-' + table.id" :text="generateQRCodeText(table.id)" />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import QrCode from './comment/qrCode.vue';

export default {
  name: 'Table',
  components: { QrCode },
  data() {
    return {
      selectedShop: null,
      shops: [
        { id: 1, name: '店铺1' },
        { id: 2, name: '店铺2' }
      ],
      tables: []
    };
  },
  methods: {
    updateTableList() {
      if (this.selectedShop) {
        // 假设每个店铺初始有两个桌子
        this.tables = [
          { id: 1 },
          { id: 2 }
        ];
      } else {
        this.tables = [];
      }
    },
    addTable() {
      const newId = this.tables.length ? this.tables[this.tables.length - 1].id + 1 : 1;
      this.tables.push({ id: newId });
    },
    removeTable() {
      this.tables.pop();
    },
    generateQRCodeText(tableId) {
      return `businessId=${this.selectedShop}&tableId=${tableId}`;
    }
  }
};
</script>

<style scoped>

</style>
