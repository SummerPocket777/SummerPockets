<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-input v-model="businessId" placeholder="输入店铺ID"></el-input>
        <el-button type="primary" @click="fetchData('weekly')">查询本周数据</el-button>
        <el-button type="primary" @click="fetchData('monthly')">查询本月数据</el-button>
      </el-col>
      <el-col :span="24">
        <div class="chart-container">
          <h3>{{ chartTitle }}</h3>
          <div ref="chart" class="chart"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios';
import * as echarts from 'echarts';

export default {
  data() {
    return {
      chart: null,
      businessId: '', // 这里输入店铺ID
      chartTitle: '',
    };
  },
  mounted() {
    this.chart = echarts.init(this.$refs.chart);
  },
  methods: {
    async fetchData(type) {
      if (!this.businessId) {
        alert('请填写店铺ID');
        return;
      }
      try {
        const response = await axios.get(`http://localhost:9990/amount/${type}`, {
          params: { businessId: this.businessId },
        });
        if (response.data && response.data.code === 200) {
          this.chartTitle = type === 'weekly' ? '本周订单数量' : '本月订单数量';
          this.renderChart(response.data.data, type);
        } else {
          console.error('Invalid response format', response.data);
        }
      } catch (error) {
        console.error(`Failed to fetch ${type} data`, error);
      }
    },
    renderChart(data, type) {
      let categories = [];
      let countData = [];

      if (type === 'weekly') {
        categories = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
        const dayCounts = new Array(7).fill(0);
        data.forEach(item => {
          if (item && item.day != null && item.count != null) {
            dayCounts[item.day] = item.count;
          }
        });
        countData = dayCounts;
      } else {
        const daysInMonth = new Date(new Date().getFullYear(), new Date().getMonth() + 1, 0).getDate();
        categories = Array.from({ length: daysInMonth }, (_, i) => `${i + 1}号`);
        const dayCounts = new Array(daysInMonth).fill(0);
        data.forEach(item => {
          if (item && item.day != null && item.count != null) {
            dayCounts[parseInt(item.day) - 1] = item.count;
          }
        });
        countData = dayCounts;
      }

      const option = {
        xAxis: {
          type: 'category',
          data: categories,
        },
        yAxis: {
          type: 'value',
          interval: 1,
        },
        series: [
          {
            data: countData,
            type: 'bar',
          },
        ],
      };
      this.chart.setOption(option);
    },
  },
};
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 400px;
}
.chart {
  width: 100%;
  height: 100%;
}
</style>
