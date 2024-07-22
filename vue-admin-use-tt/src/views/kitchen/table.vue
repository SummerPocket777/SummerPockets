<!-- src/views/Table.vue -->
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
import { ref } from 'vue';
import QrCode from './comment/qrCode.vue';

export default {
  name: 'Table',
  components: { QrCode },
  setup() {
    const selectedShop = ref(null);
    const shops = ref([
      { id: 1, name: '店铺1' },
      { id: 2, name: '店铺2' }
    ]);
    const tables = ref([]);

    const updateTableList = () => {
      if (selectedShop.value) {
        // 假设每个店铺初始有两个桌子
        tables.value = [
          { id: 1 },
          { id: 2 }
        ];
      } else {
        tables.value = [];
      }
    };

    const addTable = () => {
      const newId = tables.value.length ? tables.value[tables.value.length - 1].id + 1 : 1;
      tables.value.push({ id: newId });
    };

    const removeTable = () => {
      tables.value.pop();
    };

    const generateQRCodeText = (tableId) => {
      return `businessId=${selectedShop.value}&tableId=${tableId}`;
    };

    return {
      selectedShop,
      shops,
      tables,
      updateTableList,
      addTable,
      removeTable,
      generateQRCodeText
    };
  }
};
</script>

<style scoped>
/* Add any necessary styling here */
</style>
